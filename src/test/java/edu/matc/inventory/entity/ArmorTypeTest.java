package edu.matc.inventory.entity;

import edu.matc.inventory.persistence.GenericDao;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArmorTypeTest {

    @Test
    public void getByIdSuccess() {
        GenericDao<ArmorType> dao = new GenericDao<>(ArmorType.class);

        ArmorType leather = dao.getById(7);

        assertNotNull(leather);
    }
}