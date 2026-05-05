package edu.matc.inventory.controller;

import edu.matc.inventory.dto.LegendaryEffectDto;
import edu.matc.inventory.persistence.LegendaryEffectDao;
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

@WebServlet(urlPatterns = {"/viewLegendaryEffects"})
public class ViewLegendaryEffects extends HttpServlet {

    private final Logger logger = LogManager.getLogger(this.getClass());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String starParam = req.getParameter("star");
        LegendaryEffectDao dao = new LegendaryEffectDao();
        List<LegendaryEffectDto> effects;

        try {
            if (starParam != null && !starParam.isEmpty()) {
                int star = Integer.parseInt(starParam);
                logger.debug("Fetching legendary effects filtered by star: {}", star);
                effects = dao.getLegendaryEffectsByStar(star);
            } else {
                logger.debug("Fetching all legendary effects");
                effects = dao.getLegendaryEffects();
            }

            req.setAttribute("effects", effects);
            req.setAttribute("selectedStar", starParam);

        } catch (Exception e) {
            logger.error("Error fetching legendary effects", e);
            req.setAttribute("effects", null);
        }

        RequestDispatcher dispatcher = req.getRequestDispatcher("/viewLegendaryEffects.jsp");
        dispatcher.forward(req, resp);
    }
}