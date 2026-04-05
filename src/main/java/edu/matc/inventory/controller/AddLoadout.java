package edu.matc.inventory.controller;

import edu.matc.inventory.entity.AppUser;
import edu.matc.inventory.entity.Loadout;
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
import java.util.ArrayList;
import java.util.List;

/**
 * Adds a new loadout.
 */
@WebServlet(
        urlPatterns = {"/addLoadout"}
)
public class AddLoadout extends HttpServlet {

    private final Logger logger = LogManager.getLogger(this.getClass());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        AppUser appUser = (AppUser) req.getSession().getAttribute("user");

        if (appUser == null) {
            resp.sendRedirect(req.getContextPath() + "/index.jsp");
            return;
        }

        GenericDao<UserArmorPiece> pieceDao = new GenericDao<>(UserArmorPiece.class);
        List<UserArmorPiece> userPieces = pieceDao.getByPropertyEqual("user", appUser);

        req.setAttribute("userPieces", userPieces);

        RequestDispatcher dispatcher = req.getRequestDispatcher("/addLoadout.jsp");
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

        String name = req.getParameter("name");
        String notes = req.getParameter("notes");
        String[] pieceIds = req.getParameterValues("armorPieceIds");

        logger.info("Adding loadout '{}' for user {}", name, appUser.getDisplayName());

        Loadout loadout = new Loadout();
        loadout.setUser(appUser);
        loadout.setName(name);
        loadout.setNotes(notes);

        List<UserArmorPiece> selectedPieces = new ArrayList<>();

        if (pieceIds != null) {
            GenericDao<UserArmorPiece> pieceDao = new GenericDao<>(UserArmorPiece.class);
            for (String idStr : pieceIds) {
                UserArmorPiece piece = pieceDao.getById(Integer.parseInt(idStr));
                if (piece != null) {
                    selectedPieces.add(piece);
                }
            }
        }

        loadout.setArmorPieces(selectedPieces);

        GenericDao<Loadout> loadoutDao = new GenericDao<>(Loadout.class);
        int id = loadoutDao.insert(loadout);
        logger.info("Added loadout with id {}", id);

        resp.sendRedirect(req.getContextPath() + "/viewLoadouts");
    }
}