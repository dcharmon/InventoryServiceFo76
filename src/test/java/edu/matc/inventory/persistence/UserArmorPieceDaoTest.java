package edu.matc.inventory.persistence;

import edu.matc.inventory.entity.AppUser;
import edu.matc.inventory.entity.ArmorSlot;
import edu.matc.inventory.entity.ArmorType;
import edu.matc.inventory.entity.LegendaryEffect;
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
    private GenericDao<LegendaryEffect> legendaryEffectDao;
    private GenericDao<AppUser> userDao;

    @BeforeEach
    void setUp() {
        Database database = Database.getInstance();
        database.runSQL("cleandb.sql");

        dao = new GenericDao<>(UserArmorPiece.class);
        armorTypeDao = new GenericDao<>(ArmorType.class);
        armorSlotDao = new GenericDao<>(ArmorSlot.class);
        legendaryEffectDao = new GenericDao<>(LegendaryEffect.class);
        userDao = new GenericDao<>(AppUser.class);
    }

    @Test
    void insertSuccess() {
        ArmorType covertScout = armorTypeDao.getById(5);   // Combat armor
        ArmorSlot torso = armorSlotDao.getById(3);         // Torso
        LegendaryEffect unyielding = legendaryEffectDao.getById(19);  // Unyielding, 1-star
        AppUser user = userDao.getById(1);

        UserArmorPiece piece = new UserArmorPiece();
        piece.setUser(user);
        piece.setArmorType(covertScout);
        piece.setArmorSlot(torso);
        piece.setStar1Effect(unyielding);

        int id = dao.insert(piece);
        assertTrue(id > 0);

        UserArmorPiece inserted = dao.getById(id);
        assertNotNull(inserted);
        assertEquals(covertScout.getId(), inserted.getArmorType().getId());
        assertEquals(torso.getId(), inserted.getArmorSlot().getId());
        assertEquals(unyielding.getId(), inserted.getStar1Effect().getId());
        assertNotNull(inserted.getCreatedAt());
    }

    @Test
    void updateSuccess() {
        UserArmorPiece existing = dao.getById(5);
        assertNotNull(existing);

        ArmorSlot torso = armorSlotDao.getById(3);
        existing.setArmorSlot(torso);
        dao.update(existing);

        UserArmorPiece updated = dao.getById(5);
        assertNotNull(updated);
        assertEquals(3, updated.getArmorSlot().getId());
    }

    @Test
    void deleteSuccess() {
        UserArmorPiece existing = dao.getById(5);
        assertNotNull(existing);

        AppUser user = existing.getUser();
        user.getArmorPieces().remove(existing);
        userDao.update(user);

        dao.delete(existing);

        UserArmorPiece afterDelete = dao.getById(5);
        assertNull(afterDelete);
    }

    @Test
    void getAllSuccess() {
        List<UserArmorPiece> pieces = dao.getAll();
        assertNotNull(pieces);
        assertEquals(4, pieces.size());
    }

    @Test
    void getByPropertyEqualSuccess() {
        AppUser user = userDao.getById(1);
        List<UserArmorPiece> pieces = dao.getByPropertyEqual("user", user);
        assertNotNull(pieces);
        assertEquals(4, pieces.size());
        assertEquals(1, pieces.get(0).getUser().getUserId());
    }
}