package edu.matc.inventory.controller;

import edu.matc.inventory.entity.Loadout;
import edu.matc.inventory.entity.LoadoutType;
import edu.matc.inventory.entity.UserArmorPiece;
import edu.matc.inventory.entity.UserPaFrame;
import edu.matc.inventory.entity.UserPaPiece;
import edu.matc.inventory.persistence.GenericDao;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet that exports a loadout as a CSV file download.
 */
@WebServlet("/exportLoadout")
public final class ExportLoadout extends HttpServlet {

    private final Logger logger = LogManager.getLogger(this.getClass());
    private final GenericDao<Loadout> dao = new GenericDao<>(Loadout.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idParam = request.getParameter("id");
        if (idParam == null) {
            response.sendRedirect(request.getContextPath() + "/viewLoadouts");
            return;
        }

        Loadout loadout = dao.getById(Integer.parseInt(idParam));

        if (loadout == null) {
            response.sendRedirect(request.getContextPath() + "/viewLoadouts");
            return;
        }

        String filename = loadout.getName().replaceAll("[^a-zA-Z0-9_\\-]", "_") + ".csv";
        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + filename + "\"");

        PrintWriter writer = response.getWriter();
        writeHeader(writer, loadout);

        if (loadout.getType() == LoadoutType.STANDARD) {
            writeStandardArmor(writer, loadout);
        } else if (loadout.getType() == LoadoutType.POWER_ARMOR) {
            writePowerArmor(writer, loadout);
        }

        writer.flush();
        logger.info("Exported loadout {} as CSV", loadout.getId());
    }

    /**
     * Writes the loadout header to the CSV.
     *
     * @param writer the print writer
     * @param loadout the loadout
     */
    private void writeHeader(PrintWriter writer, Loadout loadout) {
        writer.println("Loadout: " + loadout.getName());
        if (loadout.getNotes() != null && !loadout.getNotes().isEmpty()) {
            writer.println("Notes: " + loadout.getNotes());
        }
        writer.println("Type: " + loadout.getType());
        writer.println();
    }

    /**
     * Writes standard armor pieces to the CSV.
     *
     * @param writer the print writer
     * @param loadout the loadout
     */
    private void writeStandardArmor(PrintWriter writer, Loadout loadout) {
        writer.println("Slot,Armor Type,1-Star,2-Star,3-Star,4-Star");
        for (UserArmorPiece piece : loadout.getArmorPieces()) {
            writer.println(String.join(",",
                    piece.getArmorSlot().getSlotName(),
                    piece.getArmorType().getTypeName(),
                    piece.getStar1Effect() != null ? piece.getStar1Effect().getName() : "--",
                    piece.getStar2Effect() != null ? piece.getStar2Effect().getName() : "--",
                    piece.getStar3Effect() != null ? piece.getStar3Effect().getName() : "--",
                    piece.getStar4Effect() != null ? piece.getStar4Effect().getName() : "--"
            ));
        }
    }

    /**
     * Writes power armor frames and pieces to the CSV.
     *
     * @param writer the print writer
     * @param loadout the loadout
     */
    private void writePowerArmor(PrintWriter writer, Loadout loadout) {
        for (UserPaFrame frame : loadout.getPaFrames()) {
            String frameName = frame.getFrameName() != null ? frame.getFrameName() : "Frame #" + frame.getId();
            writer.println("Frame: " + frameName);
            writer.println("Slot,PA Type,1-Star,2-Star,3-Star,4-Star");
            for (UserPaPiece piece : frame.getPieces()) {
                writer.println(String.join(",",
                        piece.getPaSlot().getSlotName(),
                        piece.getPaType().getTypeName(),
                        piece.getStar1Effect() != null ? piece.getStar1Effect().getName() : "--",
                        piece.getStar2Effect() != null ? piece.getStar2Effect().getName() : "--",
                        piece.getStar3Effect() != null ? piece.getStar3Effect().getName() : "--",
                        piece.getStar4Effect() != null ? piece.getStar4Effect().getName() : "--"
                ));
            }
            writer.println();
        }
    }
}