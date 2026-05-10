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
 * Edits a UserPaFrame record.
 */
@WebServlet(
        urlPatterns = {"/editUserPaFrame"}
)
public final class EditUserPaFrame extends HttpServlet {

    private final Logger logger = LogManager.getLogger(this.getClass());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        AppUser appUser = (AppUser) req.getSession().getAttribute("user");

        if (appUser == null) {
            resp.sendRedirect(req.getContextPath() + "/index.jsp");
            return;
        }

        int id = Integer.parseInt(req.getParameter("id"));
        logger.debug("Loading edit form for UserPaFrame id {}", id);

        GenericDao<UserPaFrame> dao = new GenericDao<>(UserPaFrame.class);
        UserPaFrame frame = dao.getById(id);

        req.setAttribute("frame", frame);

        RequestDispatcher dispatcher = req.getRequestDispatcher("/editUserPaFrame.jsp");
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

        int id = Integer.parseInt(req.getParameter("id"));
        String frameName = req.getParameter("frameName");

        logger.info("Updating UserPaFrame id {} for user {}", id, appUser.getDisplayName());

        GenericDao<UserPaFrame> dao = new GenericDao<>(UserPaFrame.class);
        UserPaFrame frame = dao.getById(id);

        if (frame != null) {
            // Allow clearing the name by submitting a blank value
            frame.setFrameName((frameName != null && !frameName.trim().isEmpty())
                    ? frameName.trim()
                    : null);

            dao.update(frame);
            logger.info("UserPaFrame id {} successfully updated", id);
        } else {
            logger.warn("UserPaFrame id {} not found for update", id);
        }

        resp.sendRedirect(req.getContextPath() + "/viewUserPaPieces");
    }
}