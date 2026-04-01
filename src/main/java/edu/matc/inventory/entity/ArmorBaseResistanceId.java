package edu.matc.inventory.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

/**
 * Composite primary key for ArmorBaseResistance.
 */
@Embeddable
public class ArmorBaseResistanceId implements Serializable {

    @Column(name = "armor_type_id")
    private int armorTypeId;

    @Column(name = "slot_group")
    private String slotGroup;

    public ArmorBaseResistanceId() {}

    public ArmorBaseResistanceId(int armorTypeId, String slotGroup) {
        this.armorTypeId = armorTypeId;
        this.slotGroup = slotGroup;
    }

    public int getArmorTypeId() {
        return armorTypeId;
    }

    public void setArmorTypeId(int armorTypeId) {
        this.armorTypeId = armorTypeId;
    }

    public String getSlotGroup() {
        return slotGroup;
    }

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