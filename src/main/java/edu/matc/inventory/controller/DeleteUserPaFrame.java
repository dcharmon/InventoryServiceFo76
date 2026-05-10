package edu.matc.inventory.controller;

import edu.matc.inventory.entity.UserPaFrame;
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
import java.util.List;

/**
 * Deletes a UserPaFrame record, unassigning any pieces from it first.
 */
@WebServlet(
        urlPatterns = {"/deleteUserPaFrame"}
)
public final class DeleteUserPaFrame extends HttpServlet {

    private final Logger logger = LogManager.getLogger(this.getClass());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String idParam = req.getParameter("id");

        if (idParam != null && !idParam.trim().isEmpty()) {
            int id = Integer.parseInt(idParam);

            logger.info("Attempting to delete UserPaFrame with id {}", id);

            GenericDao<UserPaFrame> frameDao = new GenericDao<>(UserPaFrame.class);
            GenericDao<UserPaPiece> pieceDao = new GenericDao<>(UserPaPiece.class);

            UserPaFrame frame = frameDao.getById(id);

            if (frame != null) {
                // Unassign all pieces from this frame before deleting it
                // so they remain in the user's inventory as unassigned pieces
                List<UserPaPiece> assignedPieces = frame.getPieces();
                for (UserPaPiece piece : assignedPieces) {
                    piece.setPaFrame(null);
                    pieceDao.update(piece);
                }

                frameDao.delete(frame);
                logger.info("Deleted UserPaFrame with id {}", id);
            } else {
                logger.warn("UserPaFrame with id {} not found", id);
            }
        } else {
            logger.warn("Delete request received without a valid id parameter");
        }

        resp.sendRedirect(req.getContextPath() + "/viewUserPaPieces");
    }
}