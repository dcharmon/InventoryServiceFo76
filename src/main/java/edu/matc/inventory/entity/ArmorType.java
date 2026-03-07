package edu.matc.inventory.entity;

import jakarta.persistence.*;

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
}