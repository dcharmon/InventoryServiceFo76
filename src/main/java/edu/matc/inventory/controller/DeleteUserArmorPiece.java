package edu.matc.inventory.controller;

import edu.matc.inventory.entity.UserArmorPiece;
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
 * Deletes a UserArmorPiece record.
 */
@WebServlet(
        urlPatterns = {"/deleteUserArmorPiece"}
)
public class DeleteUserArmorPiece extends HttpServlet {

    private final Logger logger = LogManager.getLogger(this.getClass());

    @Override
    protected void doGet(HttpServletRequest req,
                         HttpServletResponse resp)
            throws ServletException, IOException {

        String idParam = req.getParameter("id");

        if (idParam != null && !idParam.trim().isEmpty()) {
            int id = Integer.parseInt(idParam);

            logger.info("Attempting to delete UserArmorPiece with id {}", id);

            GenericDao<UserArmorPiece> dao = new GenericDao<>(UserArmorPiece.class);

            UserArmorPiece piece = dao.getById(id);

            if (piece != null) {
                dao.delete(piece);
                logger.info("Deleted UserArmorPiece with id {}", id);
            } else {
                logger.warn("UserArmorPiece with id {} not found", id);
            }
        } else {
            logger.warn("Delete request received without a valid id parameter");
        }

        resp.sendRedirect(req.getContextPath() + "/viewUserArmorPieces");
    }
}