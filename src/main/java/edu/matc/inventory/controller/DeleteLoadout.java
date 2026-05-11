package edu.matc.inventory.controller;

import edu.matc.inventory.entity.AppUser;
import edu.matc.inventory.entity.Loadout;
import edu.matc.inventory.persistence.GenericDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Deletes a Loadout record.
 */
@WebServlet(
        urlPatterns = {"/deleteLoadout"}
)
public final class DeleteLoadout extends HttpServlet {

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

        String idParam = req.getParameter("id");

        if (idParam != null && !idParam.trim().isEmpty()) {
            int id = Integer.parseInt(idParam);

            logger.info("Attempting to delete Loadout with id {}", id);

            GenericDao<Loadout> dao = new GenericDao<>(Loadout.class);

            Loadout loadout = dao.getById(id);

            if (loadout != null) {
                dao.deleteLoadout(loadout);
                logger.info("Deleted Loadout with id {}", id);
                req.getSession().setAttribute("flashMessage", "Loadout deleted successfully.");
            } else {
                logger.warn("Loadout with id {} not found", id);
            }
        } else {
            logger.warn("Delete request received without a valid id parameter");
        }

        resp.sendRedirect(req.getContextPath() + "/viewLoadouts");
    }
}