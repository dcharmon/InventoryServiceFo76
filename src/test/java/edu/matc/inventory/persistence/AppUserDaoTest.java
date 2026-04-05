package edu.matc.inventory.persistence;

import edu.matc.inventory.entity.AppUser;
import edu.matc.inventory.entity.Loadout;
import edu.matc.inventory.entity.UserArmorPiece;
import edu.matc.inventory.testutils.Database;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AppUserDaoTest {

    private GenericDao<AppUser> dao;

    @BeforeEach
    void setUp() {
        Database database = Database.getInstance();
        database.runSQL("cleandb.sql");

        dao = new GenericDao<>(AppUser.class);
    }

    @Test
    void getByIdSuccess() {
        AppUser user = dao.getById(1);
        assertNotNull(user);
        assertEquals(1, user.getUserId());
        assertNotNull(user.getEmail());
    }

    @Test
    void getAllSuccess() {
        List<AppUser> users = dao.getAll();
        assertNotNull(users);
        assertFalse(users.isEmpty());
    }

    @Test
    void getByPropertyEqualSuccess() {
        AppUser user = dao.getById(1);
        List<AppUser> results = dao.getByPropertyEqual("email", user.getEmail());
        assertNotNull(results);
        assertEquals(1, results.size());
        assertEquals(user.getEmail(), results.get(0).getEmail());
    }

    @Test
    void insertSuccess() {
        AppUser user = new AppUser("auth|newuser123", "newuser@test.com", "New User");

        int id = dao.insert(user);
        assertTrue(id > 0);

        AppUser inserted = dao.getById(id);
        assertNotNull(inserted);
        assertEquals("newuser@test.com", inserted.getEmail());
        assertEquals("New User", inserted.getDisplayName());
    }

    @Test
    void updateSuccess() {
        AppUser existing = dao.getById(1);
        assertNotNull(existing);

        existing.setDisplayName("Updated Name");
        dao.update(existing);

        AppUser updated = dao.getById(1);
        assertNotNull(updated);
        assertEquals("Updated Name", updated.getDisplayName());
    }

    @Test
    void deleteSuccess() {
        AppUser existing = dao.getById(1);
        assertNotNull(existing);

        dao.delete(existing);

        AppUser afterDelete = dao.getById(1);
        assertNull(afterDelete);
    }


    @Test
    void getArmorPiecesThroughUser() {
        AppUser user = dao.getById(1);
        assertNotNull(user.getArmorPieces());
        assertFalse(user.getArmorPieces().isEmpty());
    }

    @Test
    void getLoadoutsThroughUser() {
        AppUser user = dao.getById(1);
        assertNotNull(user.getLoadouts());
        assertFalse(user.getLoadouts().isEmpty());
    }


    @Test
    void deleteUserAlsoDeletesArmorPieces() {
        AppUser user = dao.getById(1);
        int armorPieceId = user.getArmorPieces().get(0).getId();

        dao.delete(user);

        GenericDao<UserArmorPiece> pieceDao = new GenericDao<>(UserArmorPiece.class);
        assertNull(pieceDao.getById(armorPieceId));
    }

    @Test
    void deleteUserAlsoDeletesLoadouts() {
        AppUser user = dao.getById(1);
        int loadoutId = user.getLoadouts().get(0).getId();

        dao.delete(user);

        GenericDao<Loadout> loadoutDao = new GenericDao<>(Loadout.class);
        assertNull(loadoutDao.getById(loadoutId));
    }

    @Test
    void deleteArmorPieceDoesNotDeleteUser() {
        AppUser user = dao.getById(1);
        int userId = user.getUserId();
        UserArmorPiece piece = user.getArmorPieces().get(0);

        // Remove from user's list so AppUser cascade doesn't re-save it
        user.getArmorPieces().remove(piece);
        dao.update(user);

        // DB cascade handles the loadout_armor_piece join table cleanup
        GenericDao<UserArmorPiece> pieceDao = new GenericDao<>(UserArmorPiece.class);
        pieceDao.delete(piece);

        assertNotNull(dao.getById(userId));
    }

    @Test
    void deleteLoadoutDoesNotDeleteUser() {
        AppUser user = dao.getById(1);
        int userId = user.getUserId();
        Loadout loadout = user.getLoadouts().get(0);

        // Remove loadout from user's list before deleting
        user.getLoadouts().remove(loadout);
        dao.update(user);

        GenericDao<Loadout> loadoutDao = new GenericDao<>(Loadout.class);
        loadoutDao.delete(loadout);

        assertNotNull(dao.getById(userId));
    }
}