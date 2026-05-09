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
 * Adds a UserArmorPiece record.
 */
@WebServlet(
        urlPatterns = {"/addUserArmorPiece"}
)
public class AddUserArmorPiece extends HttpServlet {

    private final Logger logger = LogManager.getLogger(this.getClass());
    GenericDao<UserArmorPiece> dao = new GenericDao<>(UserArmorPiece.class);
    private final GenericDao<ArmorType> armorTypeDao = new GenericDao<>(ArmorType.class);
    private final GenericDao<ArmorSlot> armorSlotDao = new GenericDao<>(ArmorSlot.class);
    private final GenericDao<LegendaryEffect> legendaryEffectDao = new GenericDao<>(LegendaryEffect.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        logger.debug("Loading add armor piece form");

        req.setAttribute("star1Effects", legendaryEffectDao.getByPropertyEqual("star", 1));
        req.setAttribute("star2Effects", legendaryEffectDao.getByPropertyEqual("star", 2));
        req.setAttribute("star3Effects", legendaryEffectDao.getByPropertyEqual("star", 3));
        req.setAttribute("star4Effects", legendaryEffectDao.getByPropertyEqual("star", 4));

        req.setAttribute("armorTypes", armorTypeDao.getAll());
        req.setAttribute("armorSlots", armorSlotDao.getAll());

        RequestDispatcher dispatcher = req.getRequestDispatcher("/addUserArmorPiece.jsp");

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

        int armorTypeId = Integer.parseInt(req.getParameter("armorTypeId"));
        int armorSlotId = Integer.parseInt(req.getParameter("armorSlotId"));

        String star1IdParam = req.getParameter("star1EffectId");
        String star2IdParam = req.getParameter("star2EffectId");
        String star3IdParam = req.getParameter("star3EffectId");
        String star4IdParam = req.getParameter("star4EffectId");

        logger.info("Adding armor piece for user {}", appUser.getDisplayName());
        logger.debug("armorTypeId: {}, armorSlotId: {}", armorTypeId, armorSlotId);

        UserArmorPiece piece = new UserArmorPiece();
        piece.setUser(appUser);

        piece.setArmorType(armorTypeDao.getById(armorTypeId));
        piece.setArmorSlot(armorSlotDao.getById(armorSlotId));

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


        int id = dao.insert(piece);
        logger.info("Added UserArmorPiece with id {}", id);

        resp.sendRedirect(req.getContextPath() + "/viewUserArmorPieces");
    }
}