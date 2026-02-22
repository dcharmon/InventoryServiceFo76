package edu.matc.inventory.entity;

import edu.matc.inventory.testutils.Database;
import edu.matc.inventory.entity.UserArmorPiece;
import edu.matc.inventory.persistence.GenericDao;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class UserArmorPieceDaoTest {

    private GenericDao<UserArmorPiece> dao;

    @Before
    public void setUp() {
        Database database = Database.getInstance();
        database.runSQL("cleandb.sql");
        dao = new GenericDao<>(UserArmorPiece.class);
    }

    @Test
    public void insertUserArmorPieceSuccess() {
        UserArmorPiece piece = new UserArmorPiece();
        piece.setUserId(1);
        piece.setArmorTypeId(7);
        piece.setArmorSlotId(1);

        int id = dao.insert(piece);

        UserArmorPiece inserted = dao.getById(id);
        assertNotNull(inserted);
    }
}