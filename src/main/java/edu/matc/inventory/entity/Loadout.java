package edu.matc.inventory.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Represents a user's armor loadout.
 */
@Entity
@Table(name = "loadout")
public class Loadout {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "loadout_id")
    private int id;

    @Column(name = "user_id", nullable = false)
    private int userId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "notes")
    private String notes;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "loadout_armor_piece",
            joinColumns = @JoinColumn(name = "loadout_id"),
            inverseJoinColumns = @JoinColumn(name = "user_armor_piece_id")
    )
    private List<UserArmorPiece> armorPieces;

    /**
     * Gets id.
     *
     * @return loadout id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id loadout id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets user id.
     *
     * @return user id
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Sets user id.
     *
     * @param userId user id
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * Gets name.
     *
     * @return loadout name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name loadout name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets notes.
     *
     * @return loadout notes
     */
    public String getNotes() {
        return notes;
    }

    /**
     * Sets notes.
     *
     * @param notes loadout notes
     */
    public void setNotes(String notes) {
        this.notes = notes;
    }

    /**
     * Gets created at.
     *
     * @return created at timestamp
     */
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    /**
     * Gets armor pieces.
     *
     * @return list of armor pieces in this loadout
     */
    public List<UserArmorPiece> getArmorPieces() {
        return armorPieces;
    }

    /**
     * Sets armor pieces.
     *
     * @param armorPieces list of armor pieces in this loadout
     */
    public void setArmorPieces(List<UserArmorPiece> armorPieces) {
        this.armorPieces = armorPieces;
    }
}