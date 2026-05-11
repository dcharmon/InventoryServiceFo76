package edu.matc.inventory.controller;

import edu.matc.inventory.entity.UserPaPiece;
import edu.matc.inventory.persistence.GenericDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Deletes a UserPaPiece record.
 */
@WebServlet(
        urlPatterns = {"/deleteUserPaPiece"}
)
public final class DeleteUserPaPiece extends HttpServlet {

    private final Logger logger = LogManager.getLogger(this.getClass());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String idParam = req.getParameter("id");

        if (idParam != null && !idParam.trim().isEmpty()) {
            int id = Integer.parseInt(idParam);

            logger.info("Attempting to delete UserPaPiece with id {}", id);

            GenericDao<UserPaPiece> dao = new GenericDao<>(UserPaPiece.class);
            UserPaPiece piece = dao.getById(id);

            if (piece != null) {
                dao.delete(piece);
                logger.info("Deleted UserPaPiece with id {}", id);
                req.getSession().setAttribute("flashMessage", "PA piece deleted successfully.");
            } else {
                logger.warn("UserPaPiece with id {} not found", id);
            }
        } else {
            logger.warn("Delete request received without a valid id parameter");
        }

        resp.sendRedirect(req.getContextPath() + "/viewUserPaPieces");
    }
}
