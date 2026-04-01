package edu.matc.inventory.entity;

import jakarta.persistence.*;

import java.util.List;

/**
 * Represents an armor type (e.g., Leather armor, Combat armor).
 */
@Entity
@Table(name = "armor_type")
public class ArmorType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "armor_type_id")
    private int id;

    @Column(name = "type_name", nullable = false)
    private String typeName;

    @Column(name = "weight_class")
    private String weightClass;

    @OneToMany(mappedBy = "armorType", fetch = FetchType.EAGER)
    private List<ArmorBaseResistance> baseResistances;

    /**
     * Gets id.
     *
     * @return armor type id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id armor type id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets type name.
     *
     * @return armor type name
     */
    public String getTypeName() {
        return typeName;
    }

    /**
     * Sets type name.
     *
     * @param typeName armor type name
     */
    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    /**
     * Gets weight class.
     *
     * @return weight class (Light, Sturdy, or Heavy)
     */
    public String getWeightClass() {
        return weightClass;
    }

    /**
     * Sets weight class.
     *
     * @param weightClass weight class (Light, Sturdy, or Heavy)
     */
    public void setWeightClass(String weightClass) {
        this.weightClass = weightClass;
    }

    /**
     * Gets base resistances.
     *
     * @return the base resistances
     */
    public List<ArmorBaseResistance> getBaseResistances() {
        return baseResistances;
    }

    /**
     * Sets base resistances.
     *
     * @param baseResistances the base resistances
     */
    public void setBaseResistances(List<ArmorBaseResistance> baseResistances) {
        this.baseResistances = baseResistances;
    }
}

