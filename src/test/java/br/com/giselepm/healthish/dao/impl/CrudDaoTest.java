package br.com.giselepm.healthish.dao.impl;

import br.com.giselepm.healthish.DatabaseUnitTest;
import br.com.giselepm.healthish.dao.ICrudDao;
import br.com.giselepm.healthish.entity.Doctor;
import br.com.giselepm.healthish.entity.Secretary;
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

    @Test
    public void whenIGetByClassSecretaryAndId1ItReturnsMaria() throws Exception {
        Secretary s = crudDao.get(Secretary.class, 1L);

        assertNotNull(s);
        assertEquals(1L, s.getId().longValue());
        assertEquals("MARIA", s.getName());
    }

    @Test
    public void whenIGetByClassSecretaryAndId4ItReturnsNull() throws Exception {
        Secretary s = crudDao.get(Secretary.class, 4L);

        assertNull(s);
    }


    @Test
    public void findAllSecretariesReturns2Instances() throws Exception {
        List<Secretary> allSecretaries = crudDao.findAll(Secretary.class);
        Collection<Long> allIds = CollectionUtils.collect(allSecretaries, new BeanToPropertyValueTransformer("id"));
        Collection<Long> allNames = CollectionUtils.collect(allSecretaries, new BeanToPropertyValueTransformer("name"));

        assertEquals(2, allSecretaries.size());
        assertTrue(allIds.containsAll(new ArrayList<Object>() {{
            add(1L);
            add(2L);
        }}));
        assertTrue(allNames.containsAll(new ArrayList<Object>(){{
            add("MARIA");
            add("LUANA");
        }}));
    }
}