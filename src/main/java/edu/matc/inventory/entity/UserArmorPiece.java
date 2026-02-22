package edu.matc.inventory.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

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

    @Column(name = "armor_type_id", nullable = false)
    private int armorTypeId;

    @Column(name = "armor_slot_id", nullable = false)
    private int armorSlotId;

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

    public int getArmorTypeId() {
        return armorTypeId;
    }

    public void setArmorTypeId(int armorTypeId) {
        this.armorTypeId = armorTypeId;
    }

    public int getArmorSlotId() {
        return armorSlotId;
    }

    public void setArmorSlotId(int armorSlotId) {
        this.armorSlotId = armorSlotId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}