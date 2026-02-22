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
 * Adds a UserArmorPiece record.
 */
@WebServlet(
        urlPatterns = {"/addUserArmorPiece"}
)
public class AddUserArmorPiece extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req,
                         HttpServletResponse resp)
            throws ServletException, IOException {

        RequestDispatcher dispatcher =
                req.getRequestDispatcher("/addUserArmorPiece.jsp");

        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req,
                          HttpServletResponse resp)
            throws ServletException, IOException {

        int userId = Integer.parseInt(req.getParameter("userId"));
        int armorTypeId = Integer.parseInt(req.getParameter("armorTypeId"));
        int armorSlotId = Integer.parseInt(req.getParameter("armorSlotId"));

        UserArmorPiece piece = new UserArmorPiece();
        piece.setUserId(userId);
        piece.setArmorTypeId(armorTypeId);
        piece.setArmorSlotId(armorSlotId);

        GenericDao<UserArmorPiece> dao =
                new GenericDao<>(UserArmorPiece.class);

        dao.insert(piece);

        resp.sendRedirect(req.getContextPath() + "/viewUserArmorPieces");
    }
}