package edu.matc.inventory.persistence;

import edu.matc.inventory.entity.ArmorType;
import edu.matc.inventory.entity.WeightClass;
import edu.matc.inventory.testutils.Database;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class ArmorTypeDaoTest {

    private GenericDao<ArmorType> dao;

    @BeforeEach
    void setUp() {
        Database database = Database.getInstance();
        database.runSQL("cleandb.sql");
        dao = new GenericDao<>(ArmorType.class);
    }

    @Test
    void getByIdSuccess() {
        ArmorType arcticMarine = dao.getById(1);
        assertNotNull(arcticMarine);
        assertEquals("Arctic Marine Armor", arcticMarine.getTypeName());
        assertEquals(WeightClass.Sturdy, arcticMarine.getWeightClass());
    }

    @Test
    void getAllSuccess() {
        List<ArmorType> types = dao.getAll();
        assertNotNull(types);
        assertEquals(28, types.size());
    }
}