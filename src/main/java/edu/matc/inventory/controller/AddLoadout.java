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
public class AddLoadout extends HttpServlet {

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

        // Standard armor: group pieces by slot
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

        // Standard armor: resolve base resistances per piece
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

        // PA: resolve base resistances per piece across all frames
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

        req.setAttribute("resolvedResistances", resolvedResistances);
        req.setAttribute("resolvedPaResistances", resolvedPaResistances);
        req.setAttribute("userPieces", userPieces);
        req.setAttribute("piecesBySlot", piecesBySlot);
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

        int id = loadoutDao.insert(loadout);
        logger.info("Added loadout with id {}", id);

        resp.sendRedirect(req.getContextPath() + "/viewLoadouts");
    }
}