package edu.matc.inventory.controller;

import edu.matc.inventory.entity.AppUser;
import edu.matc.inventory.entity.UserPaFrame;
import edu.matc.inventory.entity.UserPaPiece;
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
 * Displays UserPaPiece and UserPaFrame records for the current user.
 */
@WebServlet(
        urlPatterns = {"/viewUserPaPieces"}
)
public class ViewUserPaPieces extends HttpServlet {

    private final Logger logger = LogManager.getLogger(this.getClass());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        logger.debug("Loading user PA pieces list");

        AppUser appUser = (AppUser) req.getSession().getAttribute("user");

        if (appUser == null) {
            resp.sendRedirect(req.getContextPath() + "/index.jsp");
            return;
        }

        GenericDao<UserPaPiece> paPieceDao = new GenericDao<>(UserPaPiece.class);
        GenericDao<UserPaFrame> paFrameDao = new GenericDao<>(UserPaFrame.class);

        List<UserPaPiece> pieces = paPieceDao.getByPropertyEqual("user", appUser);
        List<UserPaFrame> frames = paFrameDao.getByPropertyEqual("user", appUser);

        logger.debug("Retrieved {} PA pieces and {} frames for user {}",
                pieces.size(), frames.size(), appUser.getDisplayName());

        req.setAttribute("pieces", pieces);
        req.setAttribute("frames", frames);

        RequestDispatcher dispatcher = req.getRequestDispatcher("/userPaPieces.jsp");
        dispatcher.forward(req, resp);
    }
}