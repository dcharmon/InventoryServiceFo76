package edu.matc.inventory.entity;

import edu.matc.inventory.persistence.GenericDao;
import edu.matc.inventory.testutils.Database;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserArmorPieceDaoTest {

    private GenericDao<UserArmorPiece> dao;
    private GenericDao<ArmorType> armorTypeDao;
    private GenericDao<ArmorSlot> armorSlotDao;

    @BeforeEach
    void setUp() {
        Database database = Database.getInstance();
        database.runSQL("cleandb.sql");

        dao = new GenericDao<>(UserArmorPiece.class);
        armorTypeDao = new GenericDao<>(ArmorType.class);
        armorSlotDao = new GenericDao<>(ArmorSlot.class);
    }

    @Test
    void insertSuccess() {
        ArmorType leather = armorTypeDao.getById(8);
        ArmorSlot leftArm = armorSlotDao.getById(1);

        assertNotNull(leather);
        assertNotNull(leftArm);

        UserArmorPiece piece = new UserArmorPiece();
        piece.setUserId(1);
        piece.setArmorType(leather);
        piece.setArmorSlot(leftArm);

        int id = dao.insert(piece);
        assertTrue(id > 0);

        UserArmorPiece inserted = dao.getById(id);
        assertNotNull(inserted);
        assertEquals(1, inserted.getUserId());
        assertNotNull(inserted.getArmorType());
        assertEquals(8, inserted.getArmorType().getId());
        assertNotNull(inserted.getArmorSlot());
        assertEquals(1, inserted.getArmorSlot().getId());
        assertNotNull(inserted.getCreatedAt());
    }

    @Test
    void updateSuccess() {
        ArmorType leather = armorTypeDao.getById(8);
        ArmorSlot leftArm = armorSlotDao.getById(1);
        ArmorSlot torso = armorSlotDao.getById(3);

        UserArmorPiece piece = new UserArmorPiece();
        piece.setUserId(1);
        piece.setArmorType(leather);
        piece.setArmorSlot(leftArm);

        int id = dao.insert(piece);

        UserArmorPiece inserted = dao.getById(id);
        inserted.setArmorSlot(torso);
        dao.update(inserted);

        UserArmorPiece updated = dao.getById(id);
        assertNotNull(updated.getArmorSlot());
        assertEquals(3, updated.getArmorSlot().getId());
    }

    @Test
    void deleteSuccess() {
        ArmorType leather = armorTypeDao.getById(8);
        ArmorSlot leftArm = armorSlotDao.getById(1);

        UserArmorPiece piece = new UserArmorPiece();
        piece.setUserId(1);
        piece.setArmorType(leather);
        piece.setArmorSlot(leftArm);

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
        ArmorType leather = armorTypeDao.getById(8);
        ArmorType combat = armorTypeDao.getById(5);
        ArmorSlot leftArm = armorSlotDao.getById(1);

        UserArmorPiece p1 = new UserArmorPiece();
        p1.setUserId(1);
        p1.setArmorType(leather);
        p1.setArmorSlot(leftArm);
        dao.insert(p1);

        UserArmorPiece p2 = new UserArmorPiece();
        p2.setUserId(1);
        p2.setArmorType(combat);
        p2.setArmorSlot(leftArm);
        dao.insert(p2);

        List<UserArmorPiece> leatherPieces =
                dao.getByPropertyEqual("armorType", leather);

        assertFalse(leatherPieces.isEmpty());
        assertTrue(leatherPieces.stream()
                .allMatch(p -> p.getArmorType().getId() == 8));
    }
}