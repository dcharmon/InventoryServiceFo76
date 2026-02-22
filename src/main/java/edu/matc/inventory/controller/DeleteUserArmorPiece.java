package edu.matc.inventory.controller;

import edu.matc.inventory.entity.UserArmorPiece;
import edu.matc.inventory.persistence.GenericDao;

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

    @Override
    protected void doGet(HttpServletRequest req,
                         HttpServletResponse resp)
            throws ServletException, IOException {

        String idParam = req.getParameter("id");

        if (idParam != null && !idParam.trim().isEmpty()) {
            int id = Integer.parseInt(idParam);

            GenericDao<UserArmorPiece> dao =
                    new GenericDao<>(UserArmorPiece.class);

            UserArmorPiece piece = dao.getById(id);

            if (piece != null) {
                dao.delete(piece);
            }
        }

        resp.sendRedirect(req.getContextPath() + "/viewUserArmorPieces");
    }
}