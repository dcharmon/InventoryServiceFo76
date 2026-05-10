package edu.matc.inventory.controller;

import edu.matc.inventory.util.PropertiesLoader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import java.util.Properties;

@WebServlet(urlPatterns = "/startup", loadOnStartup = 1)
public final class StartupServlet extends HttpServlet implements PropertiesLoader {

    private final Logger logger = LogManager.getLogger(this.getClass());

    @Override
    public void init() throws ServletException {
        Properties properties;

        try {
            properties = loadProperties("/cognito.properties");

            getServletContext().setAttribute("CLIENT_ID", properties.getProperty("client.id"));
            getServletContext().setAttribute("CLIENT_SECRET", properties.getProperty("client.secret"));
            getServletContext().setAttribute("OAUTH_URL", properties.getProperty("oauthURL"));
            getServletContext().setAttribute("LOGIN_URL", properties.getProperty("loginURL"));
            getServletContext().setAttribute("REDIRECT_URL", properties.getProperty("redirectURL"));
            getServletContext().setAttribute("REGION", properties.getProperty("region"));
            getServletContext().setAttribute("POOL_ID", properties.getProperty("poolId"));

            logger.info("Cognito properties loaded into application scope");
        } catch (Exception e) {
            logger.error("Cannot load properties", e);
            throw new ServletException("Unable to load cognito properties", e);
        }
    }
}