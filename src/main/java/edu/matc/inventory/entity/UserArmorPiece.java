package edu.matc.inventory.entity;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.*;
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

    @Column(name = "user_id", nullable = false)
    private int userId;

    @ManyToOne
    @JoinColumn(name = "armor_type_id", nullable = false)
    private ArmorType armorType;

    @ManyToOne
    @JoinColumn(name = "armor_slot_id", nullable = false)
    private ArmorSlot armorSlot;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

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
     * Gets user id.
     *
     * @return the user id
     */
    public int getUserId() {
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
}