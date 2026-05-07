package edu.matc.inventory.controller;

import edu.matc.inventory.entity.AppUser;
import edu.matc.inventory.entity.UserPaFrame;
import edu.matc.inventory.persistence.GenericDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Adds a UserPaFrame record.
 */
@WebServlet(
        urlPatterns = {"/addUserPaFrame"}
)
public class AddUserPaFrame extends HttpServlet {

    private final Logger logger = LogManager.getLogger(this.getClass());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        AppUser appUser = (AppUser) req.getSession().getAttribute("user");

        if (appUser == null) {
            resp.sendRedirect(req.getContextPath() + "/index.jsp");
            return;
        }

        logger.debug("Loading add PA frame form");

        RequestDispatcher dispatcher = req.getRequestDispatcher("/addUserPaFrame.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        AppUser appUser = (AppUser) req.getSession().getAttribute("user");

        if (appUser == null) {
            resp.sendRedirect(req.getContextPath() + "/index.jsp");
            return;
        }

        String frameName = req.getParameter("frameName");

        logger.info("Adding PA frame for user {}", appUser.getDisplayName());
        logger.debug("frameName: {}", frameName);

        UserPaFrame frame = new UserPaFrame();
        frame.setUser(appUser);

        // Frame name is optional — leave null if blank
        if (frameName != null && !frameName.trim().isEmpty()) {
            frame.setFrameName(frameName.trim());
        }

        GenericDao<UserPaFrame> dao = new GenericDao<>(UserPaFrame.class);
        int id = dao.insert(frame);
        logger.info("Added UserPaFrame with id {}", id);

        resp.sendRedirect(req.getContextPath() + "/viewUserPaPieces");
    }
}