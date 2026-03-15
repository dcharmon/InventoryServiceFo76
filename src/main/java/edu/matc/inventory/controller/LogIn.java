package edu.matc.inventory.controller;

import edu.matc.inventory.util.PropertiesLoader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@WebServlet(
        urlPatterns = {"/logIn"}
)

/**
 * Begins the authentication process using AWS Cognito.
 */
public class LogIn extends HttpServlet implements PropertiesLoader {

    private String clientId;
    private String loginUrl;
    private String redirectUrl;

    private final Logger logger = LogManager.getLogger(this.getClass());

    @Override
    public void init() throws ServletException {
        super.init();
        clientId = (String) getServletContext().getAttribute("CLIENT_ID");
        loginUrl = (String) getServletContext().getAttribute("LOGIN_URL");
        redirectUrl = (String) getServletContext().getAttribute("REDIRECT_URL");
    }

    /**
     * Redirects the user to the AWS-hosted Cognito login page.
     *
     * @param req servlet request
     * @param resp servlet response
     * @throws ServletException if forwarding fails
     * @throws IOException if redirecting fails
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        if (clientId == null || loginUrl == null || redirectUrl == null) {
            logger.error("Cognito properties not loaded correctly.");
            req.getRequestDispatcher("/error.jsp").forward(req, resp);
            return;
        }

        String url = loginUrl
                + "?response_type=code"
                + "&client_id=" + URLEncoder.encode(clientId, StandardCharsets.UTF_8)
                + "&scope=" + URLEncoder.encode("openid email", StandardCharsets.UTF_8)
                + "&redirect_uri=" + URLEncoder.encode(redirectUrl, StandardCharsets.UTF_8);

        logger.debug("Redirecting to Cognito hosted login page.");
        resp.sendRedirect(url);
    }
}