package br.com.giselepm.healthish.dao.impl;

import br.com.giselepm.healthish.DatabaseUnitTest;
import br.com.giselepm.healthish.dao.ICrudDao;
import br.com.giselepm.healthish.entity.Doctor;
import org.apache.commons.beanutils.BeanToPropertyValueTransformer;
import org.apache.commons.collections.CollectionUtils;
import org.junit.Test;
import org.unitils.spring.annotation.SpringBeanByType;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.*;

public class CrudDaoTest extends DatabaseUnitTest {

    @SpringBeanByType
    ICrudDao crudDao;

    @Test
    public void whenIGetByClassDoctorAndId1ItReturnsPhil() throws Exception {
        Doctor d = crudDao.get(Doctor.class, 1L);

        assertNotNull(d);
        assertEquals(1L, d.getId().longValue());
        assertEquals("PHIL", d.getName());
    }

    @Test
    public void whenIGetByClassDoctorAndId4ItReturnsNull() throws Exception {
        Doctor d = crudDao.get(Doctor.class, 4L);

        assertNull(d);
    }

    @Test
    public void findAllDoctorsReturns2Instances() throws Exception {
        List<Doctor> allDoctors = crudDao.findAll(Doctor.class);
        Collection<Long> allIds = CollectionUtils.collect(allDoctors, new BeanToPropertyValueTransformer("id"));
        Collection<Long> allNames = CollectionUtils.collect(allDoctors, new BeanToPropertyValueTransformer("name"));

        assertEquals(2, allDoctors.size());
        assertTrue(allIds.containsAll(new ArrayList<Object>() {{
            add(1L);
            add(2L);
        }}));
        assertTrue(allNames.containsAll(new ArrayList<Object>(){{
            add("PHIL");
            add("ARNOLD");
        }}));
    }
}