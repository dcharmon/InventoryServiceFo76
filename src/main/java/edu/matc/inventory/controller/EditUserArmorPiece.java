package edu.matc.inventory.controller;

import edu.matc.inventory.entity.AppUser;
import edu.matc.inventory.entity.ArmorSlot;
import edu.matc.inventory.entity.ArmorType;
import edu.matc.inventory.entity.LegendaryEffect;
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

/**
 * Edits a UserArmorPiece record.
 */
@WebServlet(
        urlPatterns = {"/editUserArmorPiece"}
)
public final class EditUserArmorPiece extends HttpServlet {

    private static final int STAR_1 = 1;
    private static final int STAR_2 = 2;
    private static final int STAR_3 = 3;
    private static final int STAR_4 = 4;

    private final Logger logger = LogManager.getLogger(this.getClass());
    private final GenericDao<UserArmorPiece> dao = new GenericDao<>(UserArmorPiece.class);
    private final GenericDao<ArmorType> armorTypeDao = new GenericDao<>(ArmorType.class);
    private final GenericDao<ArmorSlot> armorSlotDao = new GenericDao<>(ArmorSlot.class);
    private final GenericDao<LegendaryEffect> legendaryEffectDao = new GenericDao<>(LegendaryEffect.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        int id = Integer.parseInt(req.getParameter("id"));
        logger.debug("Loading edit form for UserArmorPiece id {}", id);

        req.setAttribute("piece", dao.getById(id));
        populateFormAttributes(req);

        RequestDispatcher dispatcher = req.getRequestDispatcher("/editUserArmorPiece.jsp");
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
        int armorTypeId = Integer.parseInt(req.getParameter("armorTypeId"));
        int armorSlotId = Integer.parseInt(req.getParameter("armorSlotId"));

        String star1IdParam = req.getParameter("star1EffectId");
        String star2IdParam = req.getParameter("star2EffectId");
        String star3IdParam = req.getParameter("star3EffectId");
        String star4IdParam = req.getParameter("star4EffectId");

        logger.info("Updating UserArmorPiece id {} for user {}", id, appUser.getDisplayName());
        logger.debug("New values - armorTypeId: {}, armorSlotId: {}", armorTypeId, armorSlotId);

        UserArmorPiece piece = dao.getById(id);

        if (piece != null) {
            piece.setUser(appUser);
            piece.setArmorType(armorTypeDao.getById(armorTypeId));
            piece.setArmorSlot(armorSlotDao.getById(armorSlotId));
            applyStarEffects(piece, star1IdParam, star2IdParam, star3IdParam, star4IdParam);
            dao.update(piece);
            logger.info("UserArmorPiece id {} successfully updated", id);
        } else {
            logger.warn("UserArmorPiece id {} not found for update", id);
        }

        resp.sendRedirect(req.getContextPath() + "/viewUserArmorPieces");
    }

    /**
     * Clears and re-applies star legendary effects to an armor piece.
     *
     * @param piece the armor piece to update
     * @param star1IdParam the star 1 effect id string
     * @param star2IdParam the star 2 effect id string
     * @param star3IdParam the star 3 effect id string
     * @param star4IdParam the star 4 effect id string
     */
    private void applyStarEffects(UserArmorPiece piece, String star1IdParam, String star2IdParam,
                                  String star3IdParam, String star4IdParam) {
        piece.setStar1Effect(null);
        piece.setStar2Effect(null);
        piece.setStar3Effect(null);
        piece.setStar4Effect(null);

        if (star1IdParam != null && !star1IdParam.isEmpty()) {
            piece.setStar1Effect(legendaryEffectDao.getById(Integer.parseInt(star1IdParam)));
        }
        if (star2IdParam != null && !star2IdParam.isEmpty()) {
            piece.setStar2Effect(legendaryEffectDao.getById(Integer.parseInt(star2IdParam)));
        }
        if (star3IdParam != null && !star3IdParam.isEmpty()) {
            piece.setStar3Effect(legendaryEffectDao.getById(Integer.parseInt(star3IdParam)));
        }
        if (star4IdParam != null && !star4IdParam.isEmpty()) {
            piece.setStar4Effect(legendaryEffectDao.getById(Integer.parseInt(star4IdParam)));
        }
    }

    /**
     * Populates form attributes for the edit armor piece form.
     *
     * @param req the HTTP request
     */
    private void populateFormAttributes(HttpServletRequest req) {
        req.setAttribute("armorTypes", armorTypeDao.getAll());
        req.setAttribute("armorSlots", armorSlotDao.getAll());
        req.setAttribute("star1Effects", legendaryEffectDao.getByPropertyEqual("star", STAR_1));
        req.setAttribute("star2Effects", legendaryEffectDao.getByPropertyEqual("star", STAR_2));
        req.setAttribute("star3Effects", legendaryEffectDao.getByPropertyEqual("star", STAR_3));
        req.setAttribute("star4Effects", legendaryEffectDao.getByPropertyEqual("star", STAR_4));
    }
}
