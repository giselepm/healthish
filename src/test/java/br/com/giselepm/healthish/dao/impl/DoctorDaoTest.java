package br.com.giselepm.healthish.dao.impl;

import br.com.giselepm.healthish.DatabaseUnitTest;
import br.com.giselepm.healthish.dao.IDoctorDao;
import br.com.giselepm.healthish.entity.Doctor;
import org.apache.commons.beanutils.BeanToPropertyValueTransformer;
import org.apache.commons.collections.CollectionUtils;
import org.junit.Test;
import org.unitils.spring.annotation.SpringBeanByType;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DoctorDaoTest extends DatabaseUnitTest {

    @SpringBeanByType
    IDoctorDao doctorDao;

    @Test
    public void findAllReturns2InstancesBecauseThereAre2RowsInDB(){
        List<Doctor> allDoctors = doctorDao.findAll();
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
