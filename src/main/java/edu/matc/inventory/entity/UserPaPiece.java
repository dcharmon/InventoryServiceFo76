package edu.matc.inventory.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

/**
 * Represents a single user-owned power armor piece, optionally
 * installed on a frame and optionally bearing legendary effects.
 */
@Entity
@Table(name = "user_pa_piece")
public class UserPaPiece {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_pa_piece_id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private AppUser user;

    @ManyToOne
    @JoinColumn(name = "pa_type_id", nullable = false)
    private PaType paType;

    @ManyToOne
    @JoinColumn(name = "pa_slot_id", nullable = false)
    private PaSlot paSlot;

    @ManyToOne
    @JoinColumn(name = "user_pa_frame_id")
    private UserPaFrame paFrame;

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

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    /**
     * Gets id.
     *
     * @return the user pa piece id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the user pa piece id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets user.
     *
     * @return the user who owns this piece
     */
    public AppUser getUser() {
        return user;
    }

    /**
     * Sets user.
     *
     * @param user the user who owns this piece
     */
    public void setUser(AppUser user) {
        this.user = user;
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
     * Gets pa frame.
     *
     * @return the frame this piece is installed on, or null if unassigned
     */
    public UserPaFrame getPaFrame() {
        return paFrame;
    }

    /**
     * Sets pa frame.
     *
     * @param paFrame the frame this piece is installed on, or null to unassign
     */
    public void setPaFrame(UserPaFrame paFrame) {
        this.paFrame = paFrame;
    }

    /**
     * Gets star 1 effect.
     *
     * @return the star 1 legendary effect
     */
    public LegendaryEffect getStar1Effect() {
        return star1Effect;
    }

    /**
     * Sets star 1 effect.
     *
     * @param star1Effect the star 1 legendary effect
     */
    public void setStar1Effect(LegendaryEffect star1Effect) {
        this.star1Effect = star1Effect;
    }

    /**
     * Gets star 2 effect.
     *
     * @return the star 2 legendary effect
     */
    public LegendaryEffect getStar2Effect() {
        return star2Effect;
    }

    /**
     * Sets star 2 effect.
     *
     * @param star2Effect the star 2 legendary effect
     */
    public void setStar2Effect(LegendaryEffect star2Effect) {
        this.star2Effect = star2Effect;
    }

    /**
     * Gets star 3 effect.
     *
     * @return the star 3 legendary effect
     */
    public LegendaryEffect getStar3Effect() {
        return star3Effect;
    }

    /**
     * Sets star 3 effect.
     *
     * @param star3Effect the star 3 legendary effect
     */
    public void setStar3Effect(LegendaryEffect star3Effect) {
        this.star3Effect = star3Effect;
    }

    /**
     * Gets star 4 effect.
     *
     * @return the star 4 legendary effect
     */
    public LegendaryEffect getStar4Effect() {
        return star4Effect;
    }

    /**
     * Sets star 4 effect.
     *
     * @param star4Effect the star 4 legendary effect
     */
    public void setStar4Effect(LegendaryEffect star4Effect) {
        this.star4Effect = star4Effect;
    }

    /**
     * Gets created at.
     *
     * @return the created at timestamp
     */
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    /**
     * Gets formatted created at.
     *
     * @return the formatted created at timestamp
     */
    public String getFormattedCreatedAt() {
        if (createdAt == null) return "--";
        return createdAt.format(java.time.format.DateTimeFormatter.ofPattern("MM/dd/yyyy h:mm a"));
    }
}