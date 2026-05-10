package edu.matc.inventory.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.matc.inventory.auth.CognitoJWTParser;
import edu.matc.inventory.auth.CognitoTokenHeader;
import edu.matc.inventory.auth.Keys;
import edu.matc.inventory.auth.KeysItem;
import edu.matc.inventory.auth.TokenResponse;
import edu.matc.inventory.util.PropertiesLoader;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPublicKeySpec;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import edu.matc.inventory.entity.AppUser;
import edu.matc.inventory.persistence.GenericDao;
import java.util.List;

@WebServlet(
        urlPatterns = {"/auth"}
)

/**
 * Handles the Cognito callback, exchanges the authorization code for tokens,
 * validates the ID token, and stores user identity values in the session.
 */
public final class Auth extends HttpServlet implements PropertiesLoader {

    private String clientId;
    private String clientSecret;
    private String oauthUrl;
    private String loginUrl;
    private String redirectUrl;
    private String region;
    private String poolId;
    private Keys jwks;

    private final Logger logger = LogManager.getLogger(this.getClass());

    @Override
    public void init() throws ServletException {
        super.init();
        clientId = (String) getServletContext().getAttribute("CLIENT_ID");
        clientSecret = (String) getServletContext().getAttribute("CLIENT_SECRET");
        oauthUrl = (String) getServletContext().getAttribute("OAUTH_URL");
        loginUrl = (String) getServletContext().getAttribute("LOGIN_URL");
        redirectUrl = (String) getServletContext().getAttribute("REDIRECT_URL");
        region = (String) getServletContext().getAttribute("REGION");
        poolId = (String) getServletContext().getAttribute("POOL_ID");

        loadKey();
    }

    /**
     * Receives the authorization code from Cognito, exchanges it for tokens,
     * validates the ID token, and stores user info in the session.
     *
     * @param req servlet request
     * @param resp servlet response
     * @throws ServletException if servlet forwarding fails
     * @throws IOException if token exchange or forwarding fails
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String authCode = req.getParameter("code");

        if (authCode == null) {
            logger.error("Authorization code missing from request.");
            forwardToError(req, resp);
            return;
        }

        try {
            HttpRequest authRequest = buildAuthRequest(authCode);
            TokenResponse tokenResponse = getToken(authRequest);
            DecodedJWT jwt = validate(tokenResponse);

            String authSubject = jwt.getClaim("sub").asString();
            String email = jwt.getClaim("email").asString();
            String cognitoUsername = jwt.getClaim("cognito:username").asString();

            logger.info("Successful Cognito login for subject: {}", authSubject);
            logger.debug("Email claim: {}", email);
            logger.debug("Cognito username claim: {}", cognitoUsername);

            GenericDao<AppUser> appUserDao = new GenericDao<>(AppUser.class);
            List<AppUser> users = appUserDao.getByPropertyEqual("authSubject", authSubject);

            AppUser appUser;

            if (users.isEmpty()) {
                String displayName = email;

                if (cognitoUsername != null && !cognitoUsername.isBlank()) {
                    displayName = cognitoUsername;
                }

                appUser = new AppUser(authSubject, email, displayName);
                appUserDao.insert(appUser);

                logger.info("Created new app user for auth subject: {}", authSubject);
            } else {
                appUser = users.get(0);
                logger.info("Found existing app user for auth subject: {}", authSubject);
            }

            HttpSession session = req.getSession();
            session.setAttribute("user", appUser);
            session.setAttribute("authSubject", authSubject);
            session.setAttribute("email", email);
            session.setAttribute("cognitoUsername", cognitoUsername);

            resp.sendRedirect(req.getContextPath() + "/index.jsp");

        } catch (IOException exception) {
            logger.error("Error getting or validating the token.", exception);
            forwardToError(req, resp);
        } catch (InterruptedException exception) {
            logger.error("Interrupted while requesting token from Cognito.", exception);
            Thread.currentThread().interrupt();
            forwardToError(req, resp);
        } catch (RuntimeException exception) {
            logger.error("Authentication processing failed.", exception);
            forwardToError(req, resp);
        }
    }

    /**
     * Sends the token request to Cognito and maps the JSON response to TokenResponse.
     *
     * @param authRequest request to Cognito oauth2/token endpoint
     * @return token response containing ID token, access token, and refresh token
     * @throws IOException if the response cannot be read
     * @throws InterruptedException if the HTTP request is interrupted
     */
    private TokenResponse getToken(HttpRequest authRequest)
            throws IOException, InterruptedException {

        HttpClient client = HttpClient.newHttpClient();
        HttpResponse<String> response =
                client.send(authRequest, HttpResponse.BodyHandlers.ofString());

        logger.debug("Token response status: {}", response.statusCode());

        ObjectMapper mapper = new ObjectMapper();
        TokenResponse tokenResponse =
                mapper.readValue(response.body(), TokenResponse.class);

        if (tokenResponse.getIdToken() == null || tokenResponse.getIdToken().isEmpty()) {
            logger.error("ID token missing from Cognito token response.");
            throw new RuntimeException("ID token missing from token response.");
        }

        return tokenResponse;
    }

    /**
     * Validates the ID token using the JWKS public key from Cognito.
     *
     * @param tokenResponse token response from Cognito
     * @return verified decoded JWT
     * @throws IOException if the JWT header cannot be parsed
     */
    private DecodedJWT validate(TokenResponse tokenResponse) throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        CognitoTokenHeader tokenHeader = mapper.readValue(
                CognitoJWTParser.getHeader(tokenResponse.getIdToken()).toString(),
                CognitoTokenHeader.class
        );

        String keyId = tokenHeader.getKid();

        KeysItem key = null;
        for (KeysItem item : jwks.getKeys()) {
            if (item.getKid().equals(keyId)) {
                key = item;
                break;
            }
        }

        if (key == null) {
            throw new RuntimeException("Matching key not found in JWKS.");
        }

        BigInteger modulus = new BigInteger(
                1, org.apache.commons.codec.binary.Base64.decodeBase64(key.getN())
        );
        BigInteger exponent = new BigInteger(
                1, org.apache.commons.codec.binary.Base64.decodeBase64(key.getE())
        );

        PublicKey publicKey;
        try {
            publicKey = KeyFactory.getInstance("RSA")
                    .generatePublic(new RSAPublicKeySpec(modulus, exponent));
        } catch (InvalidKeySpecException | NoSuchAlgorithmException exception) {
            logger.error("Error creating RSA public key.", exception);
            throw new RuntimeException(
                    "Unable to create public key for token verification.",
                    exception
            );
        }

        Algorithm algorithm = Algorithm.RSA256((RSAPublicKey) publicKey, null);

        String issuer = String.format(
                "https://cognito-idp.%s.amazonaws.com/%s",
                region,
                poolId
        );

        JWTVerifier verifier = JWT.require(algorithm)
                .withIssuer(issuer)
                .withClaim("token_use", "id")
                .build();

        DecodedJWT jwt = verifier.verify(tokenResponse.getIdToken());

        logger.debug("Token successfully verified.");
        logger.debug("Available claims: {}", jwt.getClaims().keySet());

        return jwt;
    }

    /**
     * Builds the request that exchanges the authorization code for tokens.
     *
     * @param authCode authorization code received from Cognito
     * @return constructed HTTP request for the token endpoint
     */
    private HttpRequest buildAuthRequest(String authCode) {
        String credentials = clientId + ":" + clientSecret;

        Map<String, String> parameters = new HashMap<>();
        parameters.put("grant_type", "authorization_code");
        parameters.put("client_id", clientId);
        parameters.put("code", authCode);
        parameters.put("redirect_uri", redirectUrl);

        String form = parameters.keySet().stream()
                .map(key -> key + "=" + URLEncoder.encode(
                        parameters.get(key),
                        StandardCharsets.UTF_8
                ))
                .collect(Collectors.joining("&"));

        String encodedCredentials = Base64.getEncoder()
                .encodeToString(credentials.getBytes(StandardCharsets.UTF_8));

        return HttpRequest.newBuilder()
                .uri(URI.create(oauthUrl))
                .headers(
                        "Content-Type", "application/x-www-form-urlencoded",
                        "Authorization", "Basic " + encodedCredentials
                )
                .POST(HttpRequest.BodyPublishers.ofString(form))
                .build();
    }

    /**
     * Loads the JSON Web Key Set (JWKS) from Cognito.
     */
    private void loadKey() {
        ObjectMapper mapper = new ObjectMapper();

        try {
            URL jwksUrl = new URL(String.format(
                    "https://cognito-idp.%s.amazonaws.com/%s/.well-known/jwks.json",
                    region,
                    poolId
            ));

            File jwksFile = new File("jwks.json");
            FileUtils.copyURLToFile(jwksUrl, jwksFile);
            jwks = mapper.readValue(jwksFile, Keys.class);

            logger.debug("JWKS loaded successfully.");
        } catch (IOException ioException) {
            logger.error("Cannot load JWKS JSON.", ioException);
        } catch (Exception exception) {
            logger.error("Unexpected error loading JWKS.", exception);
        }
    }

    /**
     * Forward to the generic error page.
     *
     * @param req servlet request
     * @param resp servlet response
     * @throws ServletException if forwarding fails
     * @throws IOException if forwarding fails
     */
    private void forwardToError(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        RequestDispatcher dispatcher = req.getRequestDispatcher("/error.jsp");
        dispatcher.forward(req, resp);
    }
}