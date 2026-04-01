package edu.matc.inventory.persistence;

import edu.matc.inventory.entity.ArmorBaseResistance;
import edu.matc.inventory.entity.ArmorBaseResistanceId;
import edu.matc.inventory.entity.ArmorType;
import edu.matc.inventory.testutils.Database;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ArmorBaseResistanceDaoTest {

    private GenericDao<ArmorBaseResistance> dao;
    private GenericDao<ArmorType> armorTypeDao;

    @BeforeEach
    void setUp() {
        Database database = Database.getInstance();
        database.runSQL("cleandb.sql");

        dao = new GenericDao<>(ArmorBaseResistance.class);
        armorTypeDao = new GenericDao<>(ArmorType.class);
    }

    @Test
    void getByIdSuccess() {
        ArmorBaseResistanceId id = new ArmorBaseResistanceId(1, "TORSO");
        ArmorBaseResistance resistance = dao.getById(id);

        assertNotNull(resistance);
        assertEquals(71, resistance.getDamageResistance());
        assertEquals(63, resistance.getEnergyResistance());
        assertEquals(39, resistance.getRadiationResistance());
        assertEquals(24, resistance.getPoisonResistance());
        assertEquals(39, resistance.getFireResistance());
        assertEquals(71, resistance.getCryoResistance());
    }

    @Test
    void getAllSuccess() {
        List<ArmorBaseResistance> resistances = dao.getAll();

        assertNotNull(resistances);
        // 28 armor types x 3 slot groups = 84 rows
        assertEquals(84, resistances.size());
    }

    @Test
    void getByPropertyEqualSuccess() {
        // All ARM resistances for Arctic Marine Armor (id=1)
        ArmorType arcticMarine = armorTypeDao.getById(1);
        List<ArmorBaseResistance> resistances = dao.getByPropertyEqual("armorType", arcticMarine);

        assertNotNull(resistances);
        assertEquals(3, resistances.size());  // ARM, LEG, TORSO
    }
}