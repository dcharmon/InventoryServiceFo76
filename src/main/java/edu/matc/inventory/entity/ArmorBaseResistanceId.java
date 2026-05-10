package edu.matc.inventory.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

/**
 * Composite primary key for ArmorBaseResistance.
 */
@Embeddable
public final class ArmorBaseResistanceId implements Serializable {

    @Column(name = "armor_type_id")
    private int armorTypeId;

    @Column(name = "slot_group")
    private String slotGroup;

    /**
     * Instantiates a new Armor base resistance id.
     */
    public ArmorBaseResistanceId() {}

    /**
     * Instantiates a new Armor base resistance id.
     *
     * @param armorTypeId the armor type id
     * @param slotGroup   the slot group
     */
    public ArmorBaseResistanceId(int armorTypeId, String slotGroup) {
        this.armorTypeId = armorTypeId;
        this.slotGroup = slotGroup;
    }

    /**
     * Gets armor type id.
     *
     * @return the armor type id
     */
    public int getArmorTypeId() {
        return armorTypeId;
    }

    /**
     * Sets armor type id.
     *
     * @param armorTypeId the armor type id
     */
    public void setArmorTypeId(int armorTypeId) {
        this.armorTypeId = armorTypeId;
    }

    /**
     * Gets slot group.
     *
     * @return the slot group
     */
    public String getSlotGroup() {
        return slotGroup;
    }

    /**
     * Sets slot group.
     *
     * @param slotGroup the slot group
     */
    public void setSlotGroup(String slotGroup) {
        this.slotGroup = slotGroup;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ArmorBaseResistanceId)) return false;
        ArmorBaseResistanceId that = (ArmorBaseResistanceId) o;
        return armorTypeId == that.armorTypeId && Objects.equals(slotGroup, that.slotGroup);
    }

    @Override
    public int hashCode() {
        return Objects.hash(armorTypeId, slotGroup);
    }
}