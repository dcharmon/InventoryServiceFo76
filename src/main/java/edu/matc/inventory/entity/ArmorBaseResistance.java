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

    /**
     * Gets id.
     *
     * @return the id
     */
    public ArmorBaseResistanceId getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(ArmorBaseResistanceId id) {
        this.id = id;
    }

    /**
     * Gets armor type.
     *
     * @return the armor type
     */
    public ArmorType getArmorType() {
        return armorType;
    }

    /**
     * Sets armor type.
     *
     * @param armorType the armor type
     */
    public void setArmorType(ArmorType armorType) {
        this.armorType = armorType;
    }

    /**
     * Gets damage resistance.
     *
     * @return the damage resistance
     */
    public int getDamageResistance() {
        return damageResistance;
    }

    /**
     * Sets damage resistance.
     *
     * @param damageResistance the damage resistance
     */
    public void setDamageResistance(int damageResistance) {
        this.damageResistance = damageResistance;
    }

    /**
     * Gets energy resistance.
     *
     * @return the energy resistance
     */
    public int getEnergyResistance() {
        return energyResistance;
    }

    /**
     * Sets energy resistance.
     *
     * @param energyResistance the energy resistance
     */
    public void setEnergyResistance(int energyResistance) {
        this.energyResistance = energyResistance;
    }

    /**
     * Gets radiation resistance.
     *
     * @return the radiation resistance
     */
    public int getRadiationResistance() {
        return radiationResistance;
    }

    /**
     * Sets radiation resistance.
     *
     * @param radiationResistance the radiation resistance
     */
    public void setRadiationResistance(int radiationResistance) {
        this.radiationResistance = radiationResistance;
    }

    /**
     * Gets poison resistance.
     *
     * @return the poison resistance
     */
    public int getPoisonResistance() {
        return poisonResistance;
    }

    /**
     * Sets poison resistance.
     *
     * @param poisonResistance the poison resistance
     */
    public void setPoisonResistance(int poisonResistance) {
        this.poisonResistance = poisonResistance;
    }

    /**
     * Gets fire resistance.
     *
     * @return the fire resistance
     */
    public int getFireResistance() {
        return fireResistance;
    }

    /**
     * Sets fire resistance.
     *
     * @param fireResistance the fire resistance
     */
    public void setFireResistance(int fireResistance) {
        this.fireResistance = fireResistance;
    }

    /**
     * Gets cryo resistance.
     *
     * @return the cryo resistance
     */
    public int getCryoResistance() {
        return cryoResistance;
    }

    /**
     * Sets cryo resistance.
     *
     * @param cryoResistance the cryo resistance
     */
    public void setCryoResistance(int cryoResistance) {
        this.cryoResistance = cryoResistance;
    }
}