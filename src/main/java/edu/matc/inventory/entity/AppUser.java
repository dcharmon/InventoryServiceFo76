package edu.matc.inventory.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * The type App user.
 */
@Entity
@Table(name = "app_user")
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int userId;

    @Column(name = "auth_subject")
    private String authSubject;

    @Column(name = "email")
    private String email;

    @Column(name = "display_name")
    private String displayName;

    @Column(name = "created_at", insertable = false, updatable = false)
    private Timestamp createdAt;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<UserArmorPiece> armorPieces = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Loadout> loadouts = new ArrayList<>();

    /**
     * Instantiates a new App user.
     */
    public AppUser() {
    }

    /**
     * Instantiates a new App user.
     *
     * @param authSubject the auth subject
     * @param email       the email
     * @param displayName the display name
     */
    public AppUser(String authSubject, String email, String displayName) {
        this.authSubject = authSubject;
        this.email = email;
        this.displayName = displayName;
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
     * Gets auth subject.
     *
     * @return the auth subject
     */
    public String getAuthSubject() {
        return authSubject;
    }

    /**
     * Sets auth subject.
     *
     * @param authSubject the auth subject
     */
    public void setAuthSubject(String authSubject) {
        this.authSubject = authSubject;
    }

    /**
     * Gets email.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets email.
     *
     * @param email the email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets display name.
     *
     * @return the display name
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     * Sets display name.
     *
     * @param displayName the display name
     */
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    /**
     * Gets created at.
     *
     * @return the created at
     */
    public Timestamp getCreatedAt() {
        return createdAt;
    }

    /**
     * Gets armor pieces.
     *
     * @return the armor pieces
     */
    public List<UserArmorPiece> getArmorPieces() {
        return armorPieces;
    }

    /**
     * Sets armor pieces.
     *
     * @param armorPieces the armor pieces
     */
    public void setArmorPieces(List<UserArmorPiece> armorPieces) {
        this.armorPieces = armorPieces;
    }

    /**
     * Gets loadouts.
     *
     * @return the loadouts
     */
    public List<Loadout> getLoadouts() {
        return loadouts;
    }

    /**
     * Sets loadouts.
     *
     * @param loadouts the loadouts
     */
    public void setLoadouts(List<Loadout> loadouts) {
        this.loadouts = loadouts;
    }
}