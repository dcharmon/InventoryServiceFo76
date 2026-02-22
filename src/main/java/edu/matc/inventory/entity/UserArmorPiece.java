package edu.matc.inventory.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

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

    @Column(name = "user_id")
    private int userId;

    @Column(name = "armor_type_id")
    private int armorTypeId;

    @Column(name = "armor_slot_id")
    private int armorSlotId;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    /**
     * Gets id.
     *
     * @return the id
     */
    public long getId() {
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
     * Gets user id.
     *
     * @return the user id
     */
    public long getUserId() {
        return userId;
    }

    /**
     * Sets user id.
     *
     * @param userId the user id
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * Gets armor type id.
     *
     * @return the armor type id
     */
    public int getArmorTypeId() {
        return armorTypeId;
    }

    /**
     * Sets armor type id.
     *
     * @param armorTypeId the armor type id
     */
    public void setArmorTypeId(int armorTypeId) {
        this.armorTypeId = armorTypeId;
    }

    /**
     * Gets armor slot id.
     *
     * @return the armor slot id
     */
    public int getArmorSlotId() {
        return armorSlotId;
    }

    /**
     * Sets armor slot id.
     *
     * @param armorSlotId the armor slot id
     */
    public void setArmorSlotId(int armorSlotId) {
        this.armorSlotId = armorSlotId;
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
     * Sets created at.
     *
     * @param createdAt the created at
     */
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}