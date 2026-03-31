package edu.matc.inventory.controller;

import edu.matc.inventory.entity.AppUser;
import edu.matc.inventory.entity.ArmorSlot;
import edu.matc.inventory.entity.ArmorType;
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
 * Adds a UserArmorPiece record.
 */
@WebServlet(
        urlPatterns = {"/addUserArmorPiece"}
)
public class AddUserArmorPiece extends HttpServlet {

    private final Logger logger = LogManager.getLogger(this.getClass());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        logger.debug("Loading add armor piece form");

        GenericDao<ArmorType> armorTypeDao = new GenericDao<>(ArmorType.class);
        GenericDao<ArmorSlot> armorSlotDao = new GenericDao<>(ArmorSlot.class);

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

        int userId = appUser.getUserId();
        int armorTypeId = Integer.parseInt(req.getParameter("armorTypeId"));
        int armorSlotId = Integer.parseInt(req.getParameter("armorSlotId"));

        logger.info("Adding armor piece for userId {}", userId);
        logger.debug("armorTypeId: {}, armorSlotId: {}", armorTypeId, armorSlotId);

        UserArmorPiece piece = new UserArmorPiece();
        piece.setUserId(userId);

        GenericDao<ArmorType> armorTypeDao = new GenericDao<>(ArmorType.class);
        GenericDao<ArmorSlot> armorSlotDao = new GenericDao<>(ArmorSlot.class);

        ArmorType armorType = armorTypeDao.getById(armorTypeId);
        ArmorSlot armorSlot = armorSlotDao.getById(armorSlotId);

        piece.setArmorType(armorType);
        piece.setArmorSlot(armorSlot);

        GenericDao<UserArmorPiece> dao = new GenericDao<>(UserArmorPiece.class);

        int id = dao.insert(piece);
        logger.info("Added UserArmorPiece with id {}", id);

        resp.sendRedirect(req.getContextPath() + "/viewUserArmorPieces");
    }
}