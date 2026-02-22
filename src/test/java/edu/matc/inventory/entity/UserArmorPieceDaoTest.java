package edu.matc.inventory.entity;

import edu.matc.inventory.entity.UserArmorPiece;
import edu.matc.inventory.persistence.GenericDao;
import edu.matc.inventory.testutils.Database;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserArmorPieceDaoTest {

    private GenericDao<UserArmorPiece> dao;

    @BeforeEach
    void setUp() {
        Database database = Database.getInstance();
        database.runSQL("cleandb.sql");
        dao = new GenericDao<>(UserArmorPiece.class);
    }

    @Test
    void insertSuccess() {
        UserArmorPiece piece = new UserArmorPiece();
        piece.setUserId(1);
        piece.setArmorTypeId(8);
        piece.setArmorSlotId(1);

        int id = dao.insert(piece);
        assertTrue(id > 0);

        UserArmorPiece inserted = dao.getById(id);
        assertNotNull(inserted);
        assertEquals(1, inserted.getUserId());
        assertEquals(8, inserted.getArmorTypeId());
        assertEquals(1, inserted.getArmorSlotId());
        assertNotNull(inserted.getCreatedAt());
    }

    @Test
    void updateSuccess() {
        UserArmorPiece piece = new UserArmorPiece();
        piece.setUserId(1);
        piece.setArmorTypeId(8);
        piece.setArmorSlotId(1);

        int id = dao.insert(piece);

        UserArmorPiece inserted = dao.getById(id);
        inserted.setArmorSlotId(3); // example slot id that exists (Torso)
        dao.update(inserted);

        UserArmorPiece updated = dao.getById(id);
        assertEquals(3, updated.getArmorSlotId());
    }

    @Test
    void deleteSuccess() {
        UserArmorPiece piece = new UserArmorPiece();
        piece.setUserId(1);
        piece.setArmorTypeId(8);
        piece.setArmorSlotId(1);

        int id = dao.insert(piece);

        UserArmorPiece inserted = dao.getById(id);
        dao.delete(inserted);

        UserArmorPiece afterDelete = dao.getById(id);
        assertNull(afterDelete);
    }

    @Test
    void getAllSuccess() {
        List<UserArmorPiece> pieces = dao.getAll();
        assertNotNull(pieces);

    }

    @Test
    void getByPropertyEqualSuccess() {

        UserArmorPiece p1 = new UserArmorPiece();
        p1.setUserId(1);
        p1.setArmorTypeId(8);
        p1.setArmorSlotId(1);
        dao.insert(p1);

        UserArmorPiece p2 = new UserArmorPiece();
        p2.setUserId(1);
        p2.setArmorTypeId(5);
        p2.setArmorSlotId(1);
        dao.insert(p2);

        List<UserArmorPiece> leather = dao.getByPropertyEqual("armorTypeId", 8);
        assertFalse(leather.isEmpty());
        assertTrue(leather.stream().allMatch(p -> p.getArmorTypeId() == 8));
    }
}