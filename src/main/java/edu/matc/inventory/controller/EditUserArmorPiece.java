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
 * Edits a UserArmorPiece record.
 */
@WebServlet(
        urlPatterns = {"/editUserArmorPiece"}
)
public class EditUserArmorPiece extends HttpServlet {

    private final Logger logger = LogManager.getLogger(this.getClass());

    @Override
    protected void doGet(HttpServletRequest req,
                         HttpServletResponse resp)
            throws ServletException, IOException {

        int id = Integer.parseInt(req.getParameter("id"));
        logger.debug("Loading edit form for UserArmorPiece id {}", id);

        GenericDao<UserArmorPiece> pieceDao = new GenericDao<>(UserArmorPiece.class);
        GenericDao<ArmorType> armorTypeDao = new GenericDao<>(ArmorType.class);
        GenericDao<ArmorSlot> armorSlotDao = new GenericDao<>(ArmorSlot.class);

        UserArmorPiece piece = pieceDao.getById(id);

        req.setAttribute("piece", piece);
        req.setAttribute("armorTypes", armorTypeDao.getAll());
        req.setAttribute("armorSlots", armorSlotDao.getAll());

        RequestDispatcher dispatcher =
                req.getRequestDispatcher("/editUserArmorPiece.jsp");

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

        logger.info("Updating UserArmorPiece id {} for user {}", id, appUser.getDisplayName());
        logger.debug("New values - armorTypeId: {}, armorSlotId: {}", armorTypeId, armorSlotId);

        GenericDao<UserArmorPiece> dao = new GenericDao<>(UserArmorPiece.class);
        UserArmorPiece piece = dao.getById(id);

        if (piece != null) {
            piece.setUser(appUser);

            GenericDao<ArmorType> armorTypeDao = new GenericDao<>(ArmorType.class);
            GenericDao<ArmorSlot> armorSlotDao = new GenericDao<>(ArmorSlot.class);

            piece.setArmorType(armorTypeDao.getById(armorTypeId));
            piece.setArmorSlot(armorSlotDao.getById(armorSlotId));

            dao.update(piece);

            logger.info("UserArmorPiece id {} successfully updated", id);
        } else {
            logger.warn("UserArmorPiece id {} not found for update", id);
        }

        resp.sendRedirect(req.getContextPath() + "/viewUserArmorPieces");
    }
}