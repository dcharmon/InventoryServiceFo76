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
 * Displays UserArmorPiece records.
 */
@WebServlet(
        urlPatterns = {"/viewUserArmorPieces"}
)
public class ViewUserArmorPieces extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req,
                         HttpServletResponse resp)
            throws ServletException, IOException {

        GenericDao<UserArmorPiece> dao =
                new GenericDao<>(UserArmorPiece.class);

        req.setAttribute("pieces", dao.getAll());

        RequestDispatcher dispatcher =
                req.getRequestDispatcher("/userArmorPieces.jsp");

        dispatcher.forward(req, resp);
    }
}