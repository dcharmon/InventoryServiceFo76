package edu.matc.inventory.persistence;

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
        assertEquals(1, loadout.getUserId());
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
        List<Loadout> loadouts = dao.getByPropertyEqual("userId", 1);
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
        loadout.setUserId(1);
        loadout.setName("New Loadout");
        loadout.setNotes("Some notes");
        loadout.setArmorPieces(pieces);

        int id = dao.insert(loadout);
        assertTrue(id > 0);

        Loadout inserted = dao.getById(id);
        assertNotNull(inserted);
        assertEquals("New Loadout", inserted.getName());
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

        dao.delete(existing);

        Loadout afterDelete = dao.getById(1);
        assertNull(afterDelete);
    }
}