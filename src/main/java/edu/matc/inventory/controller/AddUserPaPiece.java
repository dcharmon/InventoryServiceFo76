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
import java.util.List;

/**
 * Adds a UserPaPiece record, optionally assigning it to a user-owned frame.
 */
@WebServlet(
        urlPatterns = {"/addUserPaPiece"}
)
public class AddUserPaPiece extends HttpServlet {

    private final Logger logger = LogManager.getLogger(this.getClass());
    private final GenericDao<UserPaPiece> dao = new GenericDao<>(UserPaPiece.class);
    private final GenericDao<PaType> paTypeDao = new GenericDao<>(PaType.class);
    private final GenericDao<PaSlot> paSlotDao = new GenericDao<>(PaSlot.class);
    private final GenericDao<LegendaryEffect> legendaryEffectDao = new GenericDao<>(LegendaryEffect.class);
    private final GenericDao<UserPaFrame> paFrameDao = new GenericDao<>(UserPaFrame.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        logger.debug("Loading add PA piece form");

        AppUser appUser = (AppUser) req.getSession().getAttribute("user");

        if (appUser == null) {
            resp.sendRedirect(req.getContextPath() + "/index.jsp");
            return;
        }

        req.setAttribute("paTypes", paTypeDao.getAll());
        req.setAttribute("paSlots", paSlotDao.getAll());
        req.setAttribute("userFrames", paFrameDao.getByPropertyEqual("user", appUser));
        req.setAttribute("star1Effects", legendaryEffectDao.getByPropertyEqual("star", 1));
        req.setAttribute("star2Effects", legendaryEffectDao.getByPropertyEqual("star", 2));
        req.setAttribute("star3Effects", legendaryEffectDao.getByPropertyEqual("star", 3));
        req.setAttribute("star4Effects", legendaryEffectDao.getByPropertyEqual("star", 4));

        RequestDispatcher dispatcher = req.getRequestDispatcher("/addUserPaPiece.jsp");
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

        int paTypeId = Integer.parseInt(req.getParameter("paTypeId"));
        int paSlotId = Integer.parseInt(req.getParameter("paSlotId"));

        String frameIdParam = req.getParameter("paFrameId");
        String star1IdParam = req.getParameter("star1EffectId");
        String star2IdParam = req.getParameter("star2EffectId");
        String star3IdParam = req.getParameter("star3EffectId");
        String star4IdParam = req.getParameter("star4EffectId");

        logger.info("Adding PA piece for user {}", appUser.getDisplayName());
        logger.debug("paTypeId: {}, paSlotId: {}", paTypeId, paSlotId);

        PaSlot slot = paSlotDao.getById(paSlotId);

        UserPaPiece piece = new UserPaPiece();
        piece.setUser(appUser);
        piece.setPaType(paTypeDao.getById(paTypeId));
        piece.setPaSlot(slot);

        if (frameIdParam != null && !frameIdParam.isEmpty()) {
            piece.setPaFrame(paFrameDao.getById(Integer.parseInt(frameIdParam)));
        }

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

        int id = dao.insert(piece);
        logger.info("Added UserPaPiece with id {}", id);

        resp.sendRedirect(req.getContextPath() + "/viewUserPaPieces");
    }
}