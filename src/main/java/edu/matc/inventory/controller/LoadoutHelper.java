package edu.matc.inventory.controller;

import edu.matc.inventory.entity.*;
import edu.matc.inventory.persistence.GenericDao;

import java.util.*;

/**
 * Utility class for loadout-related helper methods.
 */
public final class LoadoutHelper {

    private static final int RESISTANCE_COUNT = 6;
    private static final int DAMAGE_INDEX = 0;
    private static final int ENERGY_INDEX = 1;
    private static final int RADIATION_INDEX = 2;
    private static final int POISON_INDEX = 3;
    private static final int FIRE_INDEX = 4;
    private static final int CRYO_INDEX = 5;

    /**
     * Utility class prevent instantiation.
     */
    private LoadoutHelper() {

    }

    /**
     * Groups user armor pieces by slot name.
     *
     * @param pieces the list of user armor pieces
     * @return map of slot name to list of armor pieces
     */
    public static Map<String, List<UserArmorPiece>> groupPiecesBySlot(List<UserArmorPiece> pieces) {
        Map<String, List<UserArmorPiece>> piecesBySlot = new LinkedHashMap<>();
        piecesBySlot.put("Left Arm",  new ArrayList<>());
        piecesBySlot.put("Right Arm", new ArrayList<>());
        piecesBySlot.put("Torso", new ArrayList<>());
        piecesBySlot.put("Left Leg", new ArrayList<>());
        piecesBySlot.put("Right Leg", new ArrayList<>());

        for (UserArmorPiece piece : pieces) {
            String slot = piece.getArmorSlot().getSlotName();
            if (piecesBySlot.containsKey(slot)) {
                piecesBySlot.get(slot).add(piece);
            }
        }
        return piecesBySlot;
    }

    /**
     * Resolves base resistances for each standard armor piece.
     *
     * @param pieces the list of user armor pieces
     * @return map of piece id to resistance array
     */
    public static Map<Integer, int[]> resolveArmorResistances(List<UserArmorPiece> pieces) {
        Map<Integer, int[]> resolvedResistances = new HashMap<>();
        for (UserArmorPiece piece : pieces) {
            int[] res = new int[RESISTANCE_COUNT];
            for (ArmorBaseResistance r : piece.getArmorType().getBaseResistances()) {
                if (r.getId().getSlotGroup().equals(piece.getArmorSlot().getSlotGroup().name())) {
                    res = buildResistanceArray(
                            r.getDamageResistance(), r.getEnergyResistance(), r.getRadiationResistance(),
                            r.getPoisonResistance(), r.getFireResistance(), r.getCryoResistance()
                    );
                    break;
                }
            }
            resolvedResistances.put(piece.getId(), res);
        }
        return resolvedResistances;
    }

    /**
     * Resolves base resistances for each PA piece across all frames.
     *
     * @param frames the list of user PA frames
     * @return map of PA piece id to resistance array
     */
    public static Map<Integer, int[]> resolvePaResistances(List<UserPaFrame> frames) {
        Map<Integer, int[]> resolvedPaResistances = new HashMap<>();
        for (UserPaFrame frame : frames) {
            for (UserPaPiece paPiece : frame.getPieces()) {
                for (PaBaseResistance r : paPiece.getPaType().getBaseResistances()) {
                    if (r.getId().getPaSlotId() == paPiece.getPaSlot().getId()) {
                        resolvedPaResistances.put(paPiece.getId(), buildResistanceArray(
                                r.getDamageResistance(), r.getEnergyResistance(), r.getRadiationResistance(),
                                r.getPoisonResistance(), r.getFireResistance(), r.getCryoResistance()
                        ));
                        break;
                    }
                }
            }
        }
        return resolvedPaResistances;
    }

    /**
     * Builds a resistance array from individual resistance values.
     *
     * @param damage the damage resistance
     * @param energy the energy resistance
     * @param radiation the radiation resistance
     * @param poison the poison resistance
     * @param fire the fire resistance
     * @param cryo the cryo resistance
     * @return resistance array
     */
    public static int[] buildResistanceArray(int damage, int energy, int radiation,
                                             int poison, int fire, int cryo) {
        int[] res = new int[RESISTANCE_COUNT];
        res[DAMAGE_INDEX] = damage;
        res[ENERGY_INDEX] = energy;
        res[RADIATION_INDEX] = radiation;
        res[POISON_INDEX] = poison;
        res[FIRE_INDEX] = fire;
        res[CRYO_INDEX] = cryo;
        return res;
    }

    /**
     * Updates a Loadout object from request parameters.
     *
     * @param loadout the loadout to update
     * @param name the loadout name
     * @param notes the loadout notes
     * @param type the loadout type string
     */
    public static void updateLoadout(Loadout loadout, String name, String notes, String type) {
        loadout.setName(name);
        loadout.setNotes(notes);
        loadout.setType(type != null ? LoadoutType.valueOf(type) : LoadoutType.STANDARD);
    }

    /**
     * Resolves a list of UserArmorPiece objects from an array of id strings.
     *
     * @param pieceIds array of piece id strings
     * @param pieceDao the dao to fetch pieces with
     * @return list of resolved UserArmorPiece objects
     */
    public static List<UserArmorPiece> resolveSelectedPieces(String[] pieceIds, GenericDao<UserArmorPiece> pieceDao) {
        List<UserArmorPiece> selectedPieces = new ArrayList<>();
        if (pieceIds != null) {
            for (String idStr : pieceIds) {
                UserArmorPiece piece = pieceDao.getById(Integer.parseInt(idStr));
                if (piece != null) selectedPieces.add(piece);
            }
        }
        return selectedPieces;
    }

    /**
     * Resolves a list of UserPaFrame objects from an array of id strings.
     *
     * @param frameIds array of frame id strings
     * @param paFrameDao the dao to fetch frames with
     * @return list of resolved UserPaFrame objects
     */
    public static List<UserPaFrame> resolveSelectedFrames(String[] frameIds, GenericDao<UserPaFrame> paFrameDao) {
        List<UserPaFrame> selectedFrames = new ArrayList<>();
        if (frameIds != null) {
            for (String idStr : frameIds) {
                UserPaFrame frame = paFrameDao.getById(Integer.parseInt(idStr));
                if (frame != null) selectedFrames.add(frame);
            }
        }
        return selectedFrames;
    }
}