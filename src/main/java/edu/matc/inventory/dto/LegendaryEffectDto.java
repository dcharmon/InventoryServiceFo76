package edu.matc.inventory.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * The type Legendary effect dto.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class LegendaryEffectDto {
    private int id;
    private String name;
    private String description;
    private int star;
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