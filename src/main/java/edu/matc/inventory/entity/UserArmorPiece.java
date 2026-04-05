package edu.matc.inventory.entity;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * The type User armor piece.
 */
@Entity
@Table(name = "user_armor_piece")
public class UserArmorPiece {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_armor_piece_id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private AppUser user;

    @ManyToOne
    @JoinColumn(name = "armor_type_id", nullable = false)
    private ArmorType armorType;

    @ManyToOne
    @JoinColumn(name = "armor_slot_id", nullable = false)
    private ArmorSlot armorSlot;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "star1_effect_id")
    private LegendaryEffect star1Effect;

    @ManyToOne
    @JoinColumn(name = "star2_effect_id")
    private LegendaryEffect star2Effect;

    @ManyToOne
    @JoinColumn(name = "star3_effect_id")
    private LegendaryEffect star3Effect;

    @ManyToOne
    @JoinColumn(name = "star4_effect_id")
    private LegendaryEffect star4Effect;

    @ManyToMany(mappedBy = "armorPieces", fetch = FetchType.EAGER)
    private List<Loadout> loadouts = new ArrayList<>();

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
     * Gets user.
     *
     * @return the user
     */
    public AppUser getUser() {
        return user;
    }

    /**
     * Sets user.
     *
     * @param user the user
     */
    public void setUser(AppUser user) {
        this.user = user;
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
     * Gets armor slot.
     *
     * @return the armor slot
     */
    public ArmorSlot getArmorSlot() {
        return armorSlot;
    }

    /**
     * Sets armor slot.
     *
     * @param armorSlot the armor slot
     */
    public void setArmorSlot(ArmorSlot armorSlot) {
        this.armorSlot = armorSlot;
    }

    /**
     * Gets created at.
     *
     * @return the created at
     */
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    /**
     * Gets star 1 effect.
     *
     * @return the star 1 effect
     */
    public LegendaryEffect getStar1Effect() {
        return star1Effect;
    }

    /**
     * Sets star 1 effect.
     *
     * @param star1Effect the star 1 effect
     */
    public void setStar1Effect(LegendaryEffect star1Effect) {
        this.star1Effect = star1Effect;
    }

    /**
     * Gets star 2 effect.
     *
     * @return the star 2 effect
     */
    public LegendaryEffect getStar2Effect() {
        return star2Effect;
    }

    /**
     * Sets star 2 effect.
     *
     * @param star2Effect the star 2 effect
     */
    public void setStar2Effect(LegendaryEffect star2Effect) {
        this.star2Effect = star2Effect;
    }

    /**
     * Gets star 3 effect.
     *
     * @return the star 3 effect
     */
    public LegendaryEffect getStar3Effect() {
        return star3Effect;
    }

    /**
     * Sets star 3 effect.
     *
     * @param star3Effect the star 3 effect
     */
    public void setStar3Effect(LegendaryEffect star3Effect) {
        this.star3Effect = star3Effect;
    }

    /**
     * Gets star 4 effect.
     *
     * @return the star 4 effect
     */
    public LegendaryEffect getStar4Effect() {
        return star4Effect;
    }

    /**
     * Sets star 4 effect.
     *
     * @param star4Effect the star 4 effect
     */
    public void setStar4Effect(LegendaryEffect star4Effect) {
        this.star4Effect = star4Effect;
    }

    /**
     * Gets loadouts.
     *
     * @return the loadouts
     */
    public List<Loadout> getLoadouts() { return loadouts; }

    /**
     * Sets loadouts.
     *
     * @param loadouts the loadouts
     */
    public void setLoadouts(List<Loadout> loadouts) { this.loadouts = loadouts; }
}