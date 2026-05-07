package edu.matc.inventory.entity;

import jakarta.persistence.*;

import java.util.List;

/**
 * Represents a power armor type (e.g., T-60, Ultracite, Excavator).
 */
@Entity
@Table(name = "pa_type")
public class PaType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pa_type_id")
    private int id;

    @Column(name = "type_name", nullable = false)
    private String typeName;

    @OneToMany(mappedBy = "paType", fetch = FetchType.EAGER)
    private List<PaBaseResistance> baseResistances;

    /**
     * Gets id.
     *
     * @return power armor type id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id power armor type id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets type name.
     *
     * @return power armor type name
     */
    public String getTypeName() {
        return typeName;
    }

    /**
     * Sets type name.
     *
     * @param typeName power armor type name
     */
    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    /**
     * Gets base resistances.
     *
     * @return the base resistances
     */
    public List<PaBaseResistance> getBaseResistances() {
        return baseResistances;
    }

    /**
     * Sets base resistances.
     *
     * @param baseResistances the base resistances
     */
    public void setBaseResistances(List<PaBaseResistance> baseResistances) {
        this.baseResistances = baseResistances;
    }
}