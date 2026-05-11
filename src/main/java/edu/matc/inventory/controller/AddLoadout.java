package edu.matc.inventory.controller;

import edu.matc.inventory.entity.*;
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
import java.util.*;

/**
 * Adds a new loadout.
 */
@WebServlet(
        urlPatterns = {"/addLoadout"}
)
public final class AddLoadout extends HttpServlet {

    private final Logger logger = LogManager.getLogger(this.getClass());
    private final GenericDao<Loadout> loadoutDao = new GenericDao<>(Loadout.class);
    private final GenericDao<UserArmorPiece> pieceDao = new GenericDao<>(UserArmorPiece.class);
    private final GenericDao<UserPaFrame> paFrameDao = new GenericDao<>(UserPaFrame.class);



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        AppUser appUser = (AppUser) req.getSession().getAttribute("user");

        if (appUser == null) {
            resp.sendRedirect(req.getContextPath() + "/index.jsp");
            return;
        }

        List<UserArmorPiece> userPieces = pieceDao.getByPropertyEqual("user", appUser);
        List<UserPaFrame> userFrames = paFrameDao.getByPropertyEqual("user", appUser);

        Map<Integer, int[]> resistances = LoadoutHelper.resolveArmorResistances(userPieces);
        logger.debug("Resolved resistances map size: {}", resistances.size());

        req.setAttribute("piecesBySlot", LoadoutHelper.groupPiecesBySlot(userPieces));
        req.setAttribute("resolvedResistances", LoadoutHelper.resolveArmorResistances(userPieces));
        req.setAttribute("resolvedPaResistances", LoadoutHelper.resolvePaResistances(userFrames));
        req.setAttribute("userPieces", userPieces);
        req.setAttribute("userFrames", userFrames);

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
        String type = req.getParameter("type");
        String[] pieceIds = req.getParameterValues("armorPieceIds");
        String[] frameIds = req.getParameterValues("paFrameIds");

        logger.info("Adding loadout '{}' of type {} for user {}", name, type, appUser.getDisplayName());

        Loadout loadout = new Loadout();
        loadout.setUser(appUser);
        LoadoutHelper.updateLoadout(loadout, name, notes, type);

        loadout.setArmorPieces(LoadoutHelper.resolveSelectedPieces(pieceIds, pieceDao));
        loadout.setPaFrames(LoadoutHelper.resolveSelectedFrames(frameIds, paFrameDao));

        int id = loadoutDao.insert(loadout);
        logger.info("Added loadout with id {}", id);
        req.getSession().setAttribute("flashMessage", "Loadout added successfully");
        resp.sendRedirect(req.getContextPath() + "/viewLoadouts");
    }
}