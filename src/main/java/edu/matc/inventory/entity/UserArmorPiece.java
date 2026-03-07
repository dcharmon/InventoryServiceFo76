package edu.matc.inventory.entity;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.*;
import java.time.LocalDateTime;

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public ArmorType getArmorType() {
        return armorType;
    }

    public void setArmorType(ArmorType armorType) {
        this.armorType = armorType;
    }

    public ArmorSlot getArmorSlot() {
        return armorSlot;
    }

    public void setArmorSlot(ArmorSlot armorSlot) {
        this.armorSlot = armorSlot;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}