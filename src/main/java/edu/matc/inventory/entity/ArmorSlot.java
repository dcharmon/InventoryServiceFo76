package edu.matc.inventory.entity;

import jakarta.persistence.*;

/**
 * Represents an armor slot (e.g., Left Arm, Torso).
 */
@Entity
@Table(name = "armor_slot")
public class ArmorSlot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "armor_slot_id")
    private int id;

    @Column(name = "slot_name", nullable = false)
    private String slotName;

    @Enumerated(EnumType.STRING)
    @Column(name = "slot_group", nullable = false)
    private SlotGroup slotGroup;

    /**
     * Gets id.
     *
     * @return armor slot id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id armor slot id
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
     * @return slot group (ARM, LEG, TORSO)
     */
    public SlotGroup getSlotGroup() {
        return slotGroup;
    }

    /**
     * Sets slot group.
     *
     * @param slotGroup slot group (ARM, LEG, TORSO)
     */
    public void setSlotGroup(SlotGroup slotGroup) {
        this.slotGroup = slotGroup;
    }
}