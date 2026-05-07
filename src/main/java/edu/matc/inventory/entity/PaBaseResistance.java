package edu.matc.inventory.entity;

import jakarta.persistence.*;

/**
 * Base resistance stats for a power armor type per slot.
 */
@Entity
@Table(name = "pa_base_resistance")
public class PaBaseResistance {

    @EmbeddedId
    private PaBaseResistanceId id;

    @ManyToOne
    @MapsId("paTypeId")
    @JoinColumn(name = "pa_type_id")
    private PaType paType;

    @ManyToOne
    @MapsId("paSlotId")
    @JoinColumn(name = "pa_slot_id")
    private PaSlot paSlot;

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
     * @return the composite id
     */
    public PaBaseResistanceId getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the composite id
     */
    public void setId(PaBaseResistanceId id) {
        this.id = id;
    }

    /**
     * Gets pa type.
     *
     * @return the power armor type
     */
    public PaType getPaType() {
        return paType;
    }

    /**
     * Sets pa type.
     *
     * @param paType the power armor type
     */
    public void setPaType(PaType paType) {
        this.paType = paType;
    }

    /**
     * Gets pa slot.
     *
     * @return the power armor slot
     */
    public PaSlot getPaSlot() {
        return paSlot;
    }

    /**
     * Sets pa slot.
     *
     * @param paSlot the power armor slot
     */
    public void setPaSlot(PaSlot paSlot) {
        this.paSlot = paSlot;
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