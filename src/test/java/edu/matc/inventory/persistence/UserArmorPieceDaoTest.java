package edu.matc.inventory.persistence;

import edu.matc.inventory.entity.ArmorSlot;
import edu.matc.inventory.entity.ArmorType;
import edu.matc.inventory.entity.UserArmorPiece;
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
        assertEquals(id, inserted.getId());
        assertEquals(1, inserted.getUserId());

        assertNotNull(inserted.getArmorType());
        assertEquals(leather.getId(), inserted.getArmorType().getId());
        assertEquals(leather.getTypeName(), inserted.getArmorType().getTypeName());

        assertNotNull(inserted.getArmorSlot());
        assertEquals(leftArm.getId(), inserted.getArmorSlot().getId());
        assertEquals(leftArm.getSlotName(), inserted.getArmorSlot().getSlotName());

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

        assertNotNull(updated);
        assertNotNull(updated.getArmorSlot());
        assertEquals(torso.getId(), updated.getArmorSlot().getId());
    }

    @Test
    void deleteSuccess() {
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

        dao.delete(inserted);

        UserArmorPiece afterDelete = dao.getById(id);
        assertNull(afterDelete);
    }

    @Test
    void getAllSuccess() {
        List<UserArmorPiece> pieces = dao.getAll();

        assertNotNull(pieces);
        assertFalse(pieces.isEmpty());
    }

    @Test
    void getByPropertyEqualSuccess() {
        ArmorType leather = armorTypeDao.getById(8);
        ArmorType combat = armorTypeDao.getById(5);
        ArmorSlot leftArm = armorSlotDao.getById(1);

        assertNotNull(leather);
        assertNotNull(combat);
        assertNotNull(leftArm);

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

        List<UserArmorPiece> leatherPieces = dao.getByPropertyEqual("armorType", leather);

        assertNotNull(leatherPieces);
        assertEquals(2, leatherPieces.size());

        assertEquals(leather.getId(), leatherPieces.get(0).getArmorType().getId());
    }
}