package edu.matc.inventory.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

/**
 * Composite primary key for PaBaseResistance.
 */
@Embeddable
public class PaBaseResistanceId implements Serializable {

    @Column(name = "pa_type_id")
    private int paTypeId;

    @Column(name = "pa_slot_id")
    private int paSlotId;

    /**
     * Instantiates a new Pa base resistance id.
     */
    public PaBaseResistanceId() {}

    /**
     * Instantiates a new Pa base resistance id.
     *
     * @param paTypeId the pa type id
     * @param paSlotId the pa slot id
     */
    public PaBaseResistanceId(int paTypeId, int paSlotId) {
        this.paTypeId = paTypeId;
        this.paSlotId = paSlotId;
    }

    /**
     * Gets pa type id.
     *
     * @return the pa type id
     */
    public int getPaTypeId() {
        return paTypeId;
    }

    /**
     * Sets pa type id.
     *
     * @param paTypeId the pa type id
     */
    public void setPaTypeId(int paTypeId) {
        this.paTypeId = paTypeId;
    }

    /**
     * Gets pa slot id.
     *
     * @return the pa slot id
     */
    public int getPaSlotId() {
        return paSlotId;
    }

    /**
     * Sets pa slot id.
     *
     * @param paSlotId the pa slot id
     */
    public void setPaSlotId(int paSlotId) {
        this.paSlotId = paSlotId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PaBaseResistanceId)) return false;
        PaBaseResistanceId that = (PaBaseResistanceId) o;
        return paTypeId == that.paTypeId && paSlotId == that.paSlotId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(paTypeId, paSlotId);
    }
}