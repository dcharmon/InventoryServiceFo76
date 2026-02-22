package edu.matc.inventory.controller;

import edu.matc.inventory.entity.UserArmorPiece;
import edu.matc.inventory.persistence.GenericDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Edits a UserArmorPiece record.
 */
@WebServlet(
        urlPatterns = {"/editUserArmorPiece"}
)
public class EditUserArmorPiece extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req,
                         HttpServletResponse resp)
            throws ServletException, IOException {

        String idParam = req.getParameter("id");

        if (idParam == null || idParam.trim().isEmpty()) {
            resp.sendRedirect(req.getContextPath() + "/viewUserArmorPieces");
            return;
        }

        int id = Integer.parseInt(idParam);

        GenericDao<UserArmorPiece> dao =
                new GenericDao<>(UserArmorPiece.class);

        UserArmorPiece piece = dao.getById(id);

        if (piece == null) {
            resp.sendRedirect(req.getContextPath() + "/viewUserArmorPieces");
            return;
        }

        req.setAttribute("piece", piece);

        RequestDispatcher dispatcher =
                req.getRequestDispatcher("/editUserArmorPiece.jsp");

        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req,
                          HttpServletResponse resp)
            throws ServletException, IOException {

        int id = Integer.parseInt(req.getParameter("id"));
        int userId = Integer.parseInt(req.getParameter("userId"));
        int armorTypeId = Integer.parseInt(req.getParameter("armorTypeId"));
        int armorSlotId = Integer.parseInt(req.getParameter("armorSlotId"));

        GenericDao<UserArmorPiece> dao =
                new GenericDao<>(UserArmorPiece.class);

        UserArmorPiece piece = dao.getById(id);

        if (piece != null) {
            piece.setUserId(userId);
            piece.setArmorTypeId(armorTypeId);
            piece.setArmorSlotId(armorSlotId);

            dao.update(piece);
        }

        resp.sendRedirect(req.getContextPath() + "/viewUserArmorPieces");
    }
}