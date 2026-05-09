package edu.matc.inventory.persistence;

import edu.matc.inventory.entity.AppUser;
import edu.matc.inventory.entity.Loadout;
import edu.matc.inventory.entity.UserArmorPiece;
import edu.matc.inventory.testutils.Database;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LoadoutDaoTest {

    private GenericDao<Loadout> dao;
    private GenericDao<UserArmorPiece> pieceDao;

    @BeforeEach
    void setUp() {
        Database database = Database.getInstance();
        database.runSQL("cleandb.sql");

        dao = new GenericDao<>(Loadout.class);
        pieceDao = new GenericDao<>(UserArmorPiece.class);
    }

    @Test
    void getByIdSuccess() {
        Loadout loadout = dao.getById(1);
        assertNotNull(loadout);
        assertEquals("New Loadout", loadout.getName());
        assertEquals("Some notes", loadout.getNotes());
        assertNotNull(loadout.getUser());
        assertEquals(1, loadout.getUser().getUserId());
        assertEquals(1, loadout.getArmorPieces().size());
    }

    @Test
    void getAllSuccess() {
        List<Loadout> loadouts = dao.getAll();

        assertNotNull(loadouts);
        assertEquals(1, loadouts.size());
    }

    @Test
    void getByPropertyEqualSuccess() {
        GenericDao<AppUser> userDao = new GenericDao<>(AppUser.class);
        AppUser user = userDao.getById(1);
        List<Loadout> loadouts = dao.getByPropertyEqual("user", user);
        assertNotNull(loadouts);
        assertEquals(1, loadouts.size());
        assertEquals("New Loadout", loadouts.get(0).getName());
    }

    @Test
    void insertSuccess() {
        UserArmorPiece piece = pieceDao.getById(1);

        List<UserArmorPiece> pieces = new ArrayList<>();
        pieces.add(piece);

        Loadout loadout = new Loadout();
        GenericDao<AppUser> userDao = new GenericDao<>(AppUser.class);
        AppUser user = userDao.getById(1);
        loadout.setUser(user);
        loadout.setName("New Loadout");
        loadout.setNotes("Some notes");
        loadout.setType("STANDARD");
        loadout.setArmorPieces(pieces);

        int id = dao.insert(loadout);
        assertTrue(id > 0);

        Loadout inserted = dao.getById(id);
        assertNotNull(inserted);
        assertEquals("New Loadout", inserted.getName());
        assertEquals("STANDARD", inserted.getType());
        assertEquals(1, inserted.getArmorPieces().size());
    }

    @Test
    void updateSuccess() {
        Loadout existing = dao.getById(1);
        assertNotNull(existing);

        existing.setName("Updated Loadout");
        dao.update(existing);

        Loadout updated = dao.getById(1);
        assertNotNull(updated);
        assertEquals("Updated Loadout", updated.getName());
    }

    @Test
    void deleteSuccess() {
        Loadout existing = dao.getById(1);
        assertNotNull(existing);

        AppUser user = existing.getUser();
        user.getLoadouts().remove(existing);
        GenericDao<AppUser> userDao = new GenericDao<>(AppUser.class);
        userDao.update(user);

        dao.delete(existing);

        Loadout afterDelete = dao.getById(1);
        assertNull(afterDelete);
    }
}