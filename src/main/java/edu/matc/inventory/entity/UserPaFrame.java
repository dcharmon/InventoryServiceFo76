package edu.matc.inventory.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a user-owned power armor frame, optionally named,
 * which holds up to six power armor pieces.
 */
@Entity
@Table(name = "user_pa_frame")
public class UserPaFrame {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_pa_frame_id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private AppUser user;

    @Column(name = "frame_name")
    private String frameName;

    @OneToMany(mappedBy = "paFrame", fetch = FetchType.EAGER)
    private List<UserPaPiece> pieces = new ArrayList<>();

    @ManyToMany(mappedBy = "paFrames", fetch = FetchType.EAGER)
    private List<Loadout> loadouts = new ArrayList<>();

    /**
     * Gets id.
     *
     * @return the user pa frame id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the user pa frame id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets user.
     *
     * @return the user who owns this frame
     */
    public AppUser getUser() {
        return user;
    }

    /**
     * Sets user.
     *
     * @param user the user who owns this frame
     */
    public void setUser(AppUser user) {
        this.user = user;
    }

    /**
     * Gets frame name.
     *
     * @return the optional display name for this frame
     */
    public String getFrameName() {
        return frameName;
    }

    /**
     * Sets frame name.
     *
     * @param frameName the optional display name for this frame
     */
    public void setFrameName(String frameName) {
        this.frameName = frameName;
    }

    /**
     * Gets pieces.
     *
     * @return the power armor pieces installed on this frame
     */
    public List<UserPaPiece> getPieces() {
        return pieces;
    }

    /**
     * Sets pieces.
     *
     * @param pieces the power armor pieces installed on this frame
     */
    public void setPieces(List<UserPaPiece> pieces) {
        this.pieces = pieces;
    }

    /**
     * Gets loadouts.
     *
     * @return the loadouts that include this frame
     */
    public List<Loadout> getLoadouts() {
        return loadouts;
    }

    /**
     * Sets loadouts.
     *
     * @param loadouts the loadouts that include this frame
     */
    public void setLoadouts(List<Loadout> loadouts) {
        this.loadouts = loadouts;
    }
}