package edu.matc.inventory.entity;

import jakarta.persistence.*;

/**
 * Represents a power armor slot (e.g., Helmet, Torso, Left Arm).
 */
@Entity
@Table(name = "pa_slot")
public class PaSlot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pa_slot_id")
    private int id;

    @Column(name = "slot_name", nullable = false)
    private String slotName;

    @Column(name = "slot_group", nullable = false)
    private String slotGroup;

    @Column(name = "allows_legendary", nullable = false)
    private boolean allowsLegendary;

    /**
     * Gets id.
     *
     * @return power armor slot id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id power armor slot id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets slot name.
     *
     * @return slot name
     */
    public String getSlotName() {
        return slotName;
    }

    /**
     * Sets slot name.
     *
     * @param slotName slot name
     */
    public void setSlotName(String slotName) {
        this.slotName = slotName;
    }

    /**
     * Gets slot group.
     *
     * @return slot group (HEAD, ARM, TORSO, LEG)
     */
    public String getSlotGroup() {
        return slotGroup;
    }

    /**
     * Sets slot group.
     *
     * @param slotGroup slot group (HEAD, ARM, TORSO, LEG)
     */
    public void setSlotGroup(String slotGroup) {
        this.slotGroup = slotGroup;
    }

    /**
     * Gets allows legendary.
     *
     * @return true if this slot can have legendary effects
     */
    public boolean isAllowsLegendary() {
        return allowsLegendary;
    }

    /**
     * Sets allows legendary.
     *
     * @param allowsLegendary true if this slot can have legendary effects
     */
    public void setAllowsLegendary(boolean allowsLegendary) {
        this.allowsLegendary = allowsLegendary;
    }
}