package edu.matc.inventory.entity;

import jakarta.persistence.*;

/**
 * The type Legendary effect.
 */
@Entity
@Table(name = "legendary_effect")
public class LegendaryEffect {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "legendary_effect_id")
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "star", nullable = false)
    private int star;

    @Column(name = "armor_category", nullable = false)
    private String armorCategory;

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets description.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets description.
     *
     * @param description the description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets star.
     *
     * @return the star
     */
    public int getStar() {
        return star;
    }

    /**
     * Sets star.
     *
     * @param star the star
     */
    public void setStar(int star) {
        this.star = star;
    }

    /**
     * Gets armor category.
     *
     * @return the armor category
     */
    public String getArmorCategory() {
        return armorCategory;
    }

    /**
     * Sets armor category.
     *
     * @param armorCategory the armor category
     */
    public void setArmorCategory(String armorCategory) {
        this.armorCategory = armorCategory;
    }
}