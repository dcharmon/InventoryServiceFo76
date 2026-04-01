package edu.matc.inventory.entity;

import jakarta.persistence.*;

/**
 * Base resistance stats for an armor type per slot group.
 */
@Entity
@Table(name = "armor_base_resistance")
public class ArmorBaseResistance {

    @EmbeddedId
    private ArmorBaseResistanceId id;

    @ManyToOne
    @MapsId("armorTypeId")
    @JoinColumn(name = "armor_type_id")
    private ArmorType armorType;

    @Column(name = "damage_resistance", nullable = false)
    private int damageResistance;

    @Column(name = "energy_resistance", nullable = false)
    private int energyResistance;

    @Column(name = "radiation_resistance", nullable = false)
    private int radiationResistance;

    @Column(name = "poison_resistance", nullable = false)
    private int poisonResistance;

    @Column(name = "fire_resistance", nullable = false)
    private int fireResistance;

    @Column(name = "cryo_resistance", nullable = false)
    private int cryoResistance;

    public ArmorBaseResistanceId getId() {
        return id;
    }

    public void setId(ArmorBaseResistanceId id) {
        this.id = id;
    }

    public ArmorType getArmorType() {
        return armorType;
    }

    public void setArmorType(ArmorType armorType) {
        this.armorType = armorType;
    }

    public int getDamageResistance() {
        return damageResistance;
    }

    public void setDamageResistance(int damageResistance) {
        this.damageResistance = damageResistance;
    }

    public int getEnergyResistance() {
        return energyResistance;
    }

    public void setEnergyResistance(int energyResistance) {
        this.energyResistance = energyResistance;
    }

    public int getRadiationResistance() {
        return radiationResistance;
    }

    public void setRadiationResistance(int radiationResistance) {
        this.radiationResistance = radiationResistance;
    }

    public int getPoisonResistance() {
        return poisonResistance;
    }

    public void setPoisonResistance(int poisonResistance) {
        this.poisonResistance = poisonResistance;
    }

    public int getFireResistance() {
        return fireResistance;
    }

    public void setFireResistance(int fireResistance) {
        this.fireResistance = fireResistance;
    }

    public int getCryoResistance() {
        return cryoResistance;
    }

    public void setCryoResistance(int cryoResistance) {
        this.cryoResistance = cryoResistance;
    }
}