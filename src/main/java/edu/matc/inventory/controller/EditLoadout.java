package edu.matc.inventory.controller;

import edu.matc.inventory.entity.AppUser;
import edu.matc.inventory.entity.Loadout;
import edu.matc.inventory.entity.UserArmorPiece;
import edu.matc.inventory.entity.ArmorBaseResistance;
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
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.HashSet;
import java.util.Set;
/**
 * Edits a user Loadout.
 */
@WebServlet(
        urlPatterns = {"/editLoadout"}
)
public class EditLoadout extends HttpServlet {

    private final Logger logger = LogManager.getLogger(this.getClass());

    @Override
    protected void doGet(HttpServletRequest req,
                         HttpServletResponse resp)
            throws ServletException, IOException {

        AppUser appUser = (AppUser) req.getSession().getAttribute("user");

        if (appUser == null) {
            resp.sendRedirect(req.getContextPath() + "/index.jsp");
            return;
        }

        int id = Integer.parseInt(req.getParameter("id"));
        logger.debug("Loading edit form for Loadout id {}", id);

        GenericDao<Loadout> loadoutDao = new GenericDao<>(Loadout.class);
        GenericDao<UserArmorPiece> pieceDao = new GenericDao<>(UserArmorPiece.class);

        Loadout loadout = loadoutDao.getById(id);
        List<UserArmorPiece> userPieces = pieceDao.getByPropertyEqual("user", appUser);
        Map<String, List<UserArmorPiece>> piecesBySlot = new LinkedHashMap<>();
        piecesBySlot.put("Left Arm",  new ArrayList<>());
        piecesBySlot.put("Right Arm", new ArrayList<>());
        piecesBySlot.put("Torso",     new ArrayList<>());
        piecesBySlot.put("Left Leg",  new ArrayList<>());
        piecesBySlot.put("Right Leg", new ArrayList<>());

        for (UserArmorPiece piece : userPieces) {
            String slot = piece.getArmorSlot().getSlotName();
            if (piecesBySlot.containsKey(slot)) {
                piecesBySlot.get(slot).add(piece);
            }
        }

        Map<Integer, int[]> resolvedResistances = new HashMap<>();
        for (UserArmorPiece piece : userPieces) {
            int[] res = new int[]{0, 0, 0, 0, 0, 0};
            for (ArmorBaseResistance r : piece.getArmorType().getBaseResistances()) {
                if (r.getId().getSlotGroup().equals(piece.getArmorSlot().getSlotGroup())) {
                    res[0] = r.getDamageResistance();
                    res[1] = r.getEnergyResistance();
                    res[2] = r.getRadiationResistance();
                    res[3] = r.getPoisonResistance();
                    res[4] = r.getFireResistance();
                    res[5] = r.getCryoResistance();
                    break;
                }
            }
            resolvedResistances.put(piece.getId(), res);
        }

        Set<Integer> selectedPieceIds = new HashSet<>();
        for (UserArmorPiece piece : loadout.getArmorPieces()) {
            selectedPieceIds.add(piece.getId());
        }

        Map<String, Integer> selectedBySlot = new HashMap<>();
        for (UserArmorPiece piece : loadout.getArmorPieces()) {
            selectedBySlot.put(piece.getArmorSlot().getSlotName(), piece.getId());
        }
        req.setAttribute("selectedBySlot", selectedBySlot);
        req.setAttribute("selectedPieceIds", selectedPieceIds);
        req.setAttribute("piecesBySlot", piecesBySlot);
        req.setAttribute("resolvedResistances", resolvedResistances);

        req.setAttribute("loadout", loadout);
        req.setAttribute("userPieces", userPieces);

        RequestDispatcher dispatcher = req.getRequestDispatcher("/editLoadout.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req,
                          HttpServletResponse resp)
            throws ServletException, IOException {

        AppUser appUser = (AppUser) req.getSession().getAttribute("user");

        if (appUser == null) {
            resp.sendRedirect(req.getContextPath() + "/index.jsp");
            return;
        }

        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        String notes = req.getParameter("notes");
        String[] pieceIds = req.getParameterValues("armorPieceIds");

        logger.info("Updating Loadout id {} for user {}", id, appUser.getDisplayName());

        GenericDao<Loadout> dao = new GenericDao<>(Loadout.class);
        Loadout loadout = dao.getById(id);

        if (loadout != null) {
            loadout.setName(name);
            loadout.setNotes(notes);

            List<UserArmorPiece> selectedPieces = new ArrayList<>();
            if (pieceIds != null) {
                GenericDao<UserArmorPiece> pieceDao = new GenericDao<>(UserArmorPiece.class);
                for (String idStr : pieceIds) {
                    UserArmorPiece piece = pieceDao.getById(Integer.parseInt(idStr));
                    if (piece != null) selectedPieces.add(piece);
                }
            }

            loadout.setArmorPieces(selectedPieces);
            dao.update(loadout);
            logger.info("Loadout id {} successfully updated", id);
        } else {
            logger.warn("Loadout id {} not found for update", id);
        }

        resp.sendRedirect(req.getContextPath() + "/viewLoadouts");
    }
}