package edu.matc.inventory.controller;

import edu.matc.inventory.entity.UserArmorPiece;
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
import java.util.List;

/**
 * Displays UserArmorPiece records.
 */
@WebServlet(
        urlPatterns = {"/viewUserArmorPieces"}
)
public class ViewUserArmorPieces extends HttpServlet {

    private final Logger logger = LogManager.getLogger(this.getClass());

    @Override
    protected void doGet(HttpServletRequest req,
                         HttpServletResponse resp)
            throws ServletException, IOException {

        logger.debug("Loading user armor pieces list");

        GenericDao<UserArmorPiece> dao = new GenericDao<>(UserArmorPiece.class);

        List<UserArmorPiece> pieces = dao.getAll();

        logger.debug("Retrieved {} armor pieces", pieces.size());

        req.setAttribute("pieces", pieces);

        RequestDispatcher dispatcher = req.getRequestDispatcher("/userArmorPieces.jsp");

        dispatcher.forward(req, resp);
    }
}