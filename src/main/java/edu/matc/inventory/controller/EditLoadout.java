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
 * Edits a user Loadout.
 */
@WebServlet(
        urlPatterns = {"/editLoadout"}
)
public final class EditLoadout extends HttpServlet {

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

        int id = Integer.parseInt(req.getParameter("id"));
        logger.debug("Loading edit form for Loadout id {}", id);

        Loadout loadout = loadoutDao.getById(id);
        List<UserArmorPiece> userPieces = pieceDao.getByPropertyEqual("user", appUser);
        List<UserPaFrame> userFrames = paFrameDao.getByPropertyEqual("user", appUser);

        Set<Integer> selectedPieceIds = new HashSet<>();
        Map<String, Integer> selectedBySlot = new HashMap<>();
        resolveSelectedPieces(loadout, selectedPieceIds, selectedBySlot);
        Set<Integer> selectedFrameIds = resolveSelectedFrameIds(loadout);

        req.setAttribute("loadout", loadout);
        req.setAttribute("loadoutType", loadout.getType());
        req.setAttribute("userPieces", userPieces);
        req.setAttribute("piecesBySlot", LoadoutHelper.groupPiecesBySlot(userPieces));
        req.setAttribute("resolvedResistances", LoadoutHelper.resolveArmorResistances(userPieces));
        req.setAttribute("resolvedPaResistances", LoadoutHelper.resolvePaResistances(userFrames));
        req.setAttribute("selectedPieceIds", selectedPieceIds);
        req.setAttribute("selectedBySlot", selectedBySlot);
        req.setAttribute("userFrames", userFrames);
        req.setAttribute("selectedFrameIds", selectedFrameIds);

        RequestDispatcher dispatcher = req.getRequestDispatcher("/editLoadout.jsp");
        dispatcher.forward(req, resp);
    }

    /**
     * Populates selected piece ids and slot mapping from the loadout's current armor pieces.
     *
     * @param loadout the loadout
     * @param selectedPieceIds the set to populate with selected piece ids
     * @param selectedBySlot the map to populate with slot name to piece id
     */
    private void resolveSelectedPieces(Loadout loadout, Set<Integer> selectedPieceIds,
                                       Map<String, Integer> selectedBySlot) {
        for (UserArmorPiece piece : loadout.getArmorPieces()) {
            selectedPieceIds.add(piece.getId());
            selectedBySlot.put(piece.getArmorSlot().getSlotName(), piece.getId());
        }
    }

    /**
     * Builds a set of selected PA frame ids from the loadout.
     *
     * @param loadout the loadout
     * @return set of selected frame ids
     */
    private Set<Integer> resolveSelectedFrameIds(Loadout loadout) {
        Set<Integer> selectedFrameIds = new HashSet<>();
        for (UserPaFrame frame : loadout.getPaFrames()) {
            selectedFrameIds.add(frame.getId());
        }
        return selectedFrameIds;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        AppUser appUser = (AppUser) req.getSession().getAttribute("user");

        if (appUser == null) {
            resp.sendRedirect(req.getContextPath() + "/index.jsp");
            return;
        }

        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        String notes = req.getParameter("notes");
        String type = req.getParameter("type");
        String[] pieceIds = req.getParameterValues("armorPieceIds");
        String[] frameIds = req.getParameterValues("paFrameIds");

        logger.info("Updating Loadout id {} for user {}", id, appUser.getDisplayName());

        Loadout loadout = loadoutDao.getById(id);

        if (loadout != null) {
            LoadoutHelper.updateLoadout(loadout, name, notes, type);
            loadout.setArmorPieces(LoadoutHelper.resolveSelectedPieces(pieceIds, pieceDao));
            loadout.setPaFrames(LoadoutHelper.resolveSelectedFrames(frameIds, paFrameDao));

            loadoutDao.update(loadout);
            logger.info("Loadout id {} successfully updated", id);
        } else {
            logger.warn("Loadout id {} not found for update", id);
        }

        resp.sendRedirect(req.getContextPath() + "/viewLoadouts");
    }
}