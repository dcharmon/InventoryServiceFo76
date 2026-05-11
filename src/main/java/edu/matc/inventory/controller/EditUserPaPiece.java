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

/**
 * Edits a UserPaPiece record.
 */
@WebServlet(
        urlPatterns = {"/editUserPaPiece"}
)
public final class EditUserPaPiece extends HttpServlet {

    private static final int STAR_1 = 1;
    private static final int STAR_2 = 2;
    private static final int STAR_3 = 3;
    private static final int STAR_4 = 4;

    private final Logger logger = LogManager.getLogger(this.getClass());
    private final GenericDao<UserPaPiece> dao = new GenericDao<>(UserPaPiece.class);
    private final GenericDao<PaType> paTypeDao = new GenericDao<>(PaType.class);
    private final GenericDao<PaSlot> paSlotDao = new GenericDao<>(PaSlot.class);
    private final GenericDao<LegendaryEffect> legendaryEffectDao = new GenericDao<>(LegendaryEffect.class);
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
        logger.debug("Loading edit form for UserPaPiece id {}", id);

        req.setAttribute("piece", dao.getById(id));
        populateFormAttributes(req, appUser);

        RequestDispatcher dispatcher = req.getRequestDispatcher("/editUserPaPiece.jsp");
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
        int paTypeId = Integer.parseInt(req.getParameter("paTypeId"));
        int paSlotId = Integer.parseInt(req.getParameter("paSlotId"));

        String frameIdParam = req.getParameter("paFrameId");
        String star1IdParam = req.getParameter("star1EffectId");
        String star2IdParam = req.getParameter("star2EffectId");
        String star3IdParam = req.getParameter("star3EffectId");
        String star4IdParam = req.getParameter("star4EffectId");

        logger.info("Updating UserPaPiece id {} for user {}", id, appUser.getDisplayName());
        logger.debug("New values - paTypeId: {}, paSlotId: {}", paTypeId, paSlotId);

        UserPaPiece piece = dao.getById(id);

        if (piece != null) {
            piece.setUser(appUser);
            piece.setPaType(paTypeDao.getById(paTypeId));

            PaSlot slot = paSlotDao.getById(paSlotId);
            piece.setPaSlot(slot);

            if (frameIdParam != null && !frameIdParam.isEmpty()) {
                UserPaFrame frame = paFrameDao.getById(Integer.parseInt(frameIdParam));

                boolean slotTaken = frame.getPieces().stream()
                        .anyMatch(p -> p.getPaSlot().getId() == paSlotId && p.getId() != id);

                if (slotTaken) {
                    logger.warn("Slot {} already occupied on frame {} during edit of piece {}", paSlotId, frame.getId(), id);
                    req.setAttribute("errorMessage", "That slot is already occupied on this frame.");
                    req.setAttribute("piece", piece);
                    populateFormAttributes(req, appUser);
                    req.getRequestDispatcher("/editUserPaPiece.jsp").forward(req, resp);
                    return;
                }

                piece.setPaFrame(frame);
            } else {
                piece.setPaFrame(null);
            }

            applyStarEffects(piece, slot, star1IdParam, star2IdParam, star3IdParam, star4IdParam);

            dao.update(piece);
            logger.info("UserPaPiece id {} successfully updated", id);
            req.getSession().setAttribute("flashMessage", "PA piece updated successfully");
        } else {
            logger.warn("UserPaPiece id {} not found for update", id);
        }

        resp.sendRedirect(req.getContextPath() + "/viewUserPaPieces");
    }

    /**
     * Populates form attributes for the edit PA piece form.
     *
     * @param req the HTTP request
     * @param appUser the authenticated app user
     */
    private void populateFormAttributes(HttpServletRequest req, AppUser appUser) {
        req.setAttribute("paTypes", paTypeDao.getAll());
        req.setAttribute("paSlots", paSlotDao.getAll());
        req.setAttribute("userFrames", paFrameDao.getByPropertyEqual("user", appUser));
        req.setAttribute("star1Effects", legendaryEffectDao.getByPropertyEqual("star", STAR_1));
        req.setAttribute("star2Effects", legendaryEffectDao.getByPropertyEqual("star", STAR_2));
        req.setAttribute("star3Effects", legendaryEffectDao.getByPropertyEqual("star", STAR_3));
        req.setAttribute("star4Effects", legendaryEffectDao.getByPropertyEqual("star", STAR_4));
    }

    /**
     * Clears and re-applies star legendary effects to a PA piece if the slot allows it.
     *
     * @param piece the PA piece to update
     * @param slot the PA slot to check for legendary eligibility
     * @param star1IdParam the star 1 effect id string
     * @param star2IdParam the star 2 effect id string
     * @param star3IdParam the star 3 effect id string
     * @param star4IdParam the star 4 effect id string
     */
    private void applyStarEffects(UserPaPiece piece, PaSlot slot, String star1IdParam, String star2IdParam,
                                  String star3IdParam, String star4IdParam) {
        piece.setStar1Effect(null);
        piece.setStar2Effect(null);
        piece.setStar3Effect(null);
        piece.setStar4Effect(null);

        if (slot.isAllowsLegendary()) {
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
    }
}