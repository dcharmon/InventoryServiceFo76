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
public class EditLoadout extends HttpServlet {

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

        //Standard armor: group pieces by slot
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

        //Standard armor: resolve base resistances per piece
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

        //PA: resolve base resistances per piece across all frames
        Map<Integer, int[]> resolvedPaResistances = new HashMap<>();
        for (UserPaFrame frame : userFrames) {
            for (UserPaPiece paPiece : frame.getPieces()) {
                int[] res = new int[]{0, 0, 0, 0, 0, 0};
                for (PaBaseResistance r : paPiece.getPaType().getBaseResistances()) {
                    if (r.getId().getPaSlotId() == paPiece.getPaSlot().getId()) {
                        res[0] = r.getDamageResistance();
                        res[1] = r.getEnergyResistance();
                        res[2] = r.getRadiationResistance();
                        res[3] = r.getPoisonResistance();
                        res[4] = r.getFireResistance();
                        res[5] = r.getCryoResistance();
                        break;
                    }
                }
                resolvedPaResistances.put(paPiece.getId(), res);
            }
        }

        //Track which armor pieces are currently selected
        Set<Integer> selectedPieceIds = new HashSet<>();
        Map<String, Integer> selectedBySlot = new HashMap<>();
        for (UserArmorPiece piece : loadout.getArmorPieces()) {
            selectedPieceIds.add(piece.getId());
            selectedBySlot.put(piece.getArmorSlot().getSlotName(), piece.getId());
        }

        //Track which PA frames are currently selected
        Set<Integer> selectedFrameIds = new HashSet<>();
        for (UserPaFrame frame : loadout.getPaFrames()) {
            selectedFrameIds.add(frame.getId());
        }

        req.setAttribute("loadout", loadout);
        req.setAttribute("loadoutType", loadout.getType());
        req.setAttribute("userPieces", userPieces);
        req.setAttribute("piecesBySlot", piecesBySlot);
        req.setAttribute("resolvedResistances", resolvedResistances);
        req.setAttribute("resolvedPaResistances", resolvedPaResistances);
        req.setAttribute("selectedPieceIds", selectedPieceIds);
        req.setAttribute("selectedBySlot", selectedBySlot);
        req.setAttribute("userFrames", userFrames);
        req.setAttribute("selectedFrameIds", selectedFrameIds);

        RequestDispatcher dispatcher = req.getRequestDispatcher("/editLoadout.jsp");
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

        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        String notes = req.getParameter("notes");
        String type = req.getParameter("type");
        String[] pieceIds = req.getParameterValues("armorPieceIds");
        String[] frameIds = req.getParameterValues("paFrameIds");

        logger.info("Updating Loadout id {} for user {}", id, appUser.getDisplayName());

        Loadout loadout = loadoutDao.getById(id);

        if (loadout != null) {
            loadout.setName(name);
            loadout.setNotes(notes);
            loadout.setType(type != null ? type : "STANDARD");

            List<UserArmorPiece> selectedPieces = new ArrayList<>();
            if (pieceIds != null) {
                for (String idStr : pieceIds) {
                    UserArmorPiece piece = pieceDao.getById(Integer.parseInt(idStr));
                    if (piece != null) selectedPieces.add(piece);
                }
            }

            List<UserPaFrame> selectedFrames = new ArrayList<>();
            if (frameIds != null) {
                for (String idStr : frameIds) {
                    UserPaFrame frame = paFrameDao.getById(Integer.parseInt(idStr));
                    if (frame != null) selectedFrames.add(frame);
                }
            }

            loadout.setArmorPieces(selectedPieces);
            loadout.setPaFrames(selectedFrames);

            loadoutDao.update(loadout);
            logger.info("Loadout id {} successfully updated", id);
        } else {
            logger.warn("Loadout id {} not found for update", id);
        }

        resp.sendRedirect(req.getContextPath() + "/viewLoadouts");
    }
}