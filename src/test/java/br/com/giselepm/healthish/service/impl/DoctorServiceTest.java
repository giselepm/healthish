package br.com.giselepm.healthish.service.impl;

import br.com.giselepm.healthish.dao.IDoctorDao;
import br.com.giselepm.healthish.entity.Doctor;
import br.com.giselepm.healthish.service.IDoctorService;
import org.apache.commons.beanutils.BeanToPropertyValueTransformer;
import org.apache.commons.collections.CollectionUtils;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DoctorServiceTest {

    IDoctorService doctorService;
    IDoctorDao doctorDaoMock;

    @Before
    public void setup(){
        doctorDaoMock = mock(IDoctorDao.class);
        doctorService = new DoctorService(doctorDaoMock);
    }

    @Test
    public void whenDaoReturns2DoctorsServiceReturnsTheSame(){
        when(doctorDaoMock.findAll()).thenReturn(new ArrayList<Doctor>(){{
            add(new Doctor(1, "Phil"));
            add(new Doctor(2, "Arnold"));
        }});

        List<Doctor> allDoctors = doctorService.findAll();
        Collection<Long> allIds = CollectionUtils.collect(allDoctors, new BeanToPropertyValueTransformer("id"));
        Collection<Long> allNames = CollectionUtils.collect(allDoctors, new BeanToPropertyValueTransformer("name"));

        assertEquals(2, allDoctors.size());
        assertTrue(allIds.containsAll(new ArrayList<Object>() {{
            add(1L);
            add(2L);
        }}));
        assertTrue(allNames.containsAll(new ArrayList<Object>(){{
            add("Phil");
            add("Arnold");
        }}));
    }

    @Test
    public void whenDaoReturnsNoDoctorsServiceReturnsTheSame(){
        when(doctorDaoMock.findAll()).thenReturn(new ArrayList<Doctor>());

        List<Doctor> allDoctors = doctorService.findAll();

        assertTrue(allDoctors.isEmpty());
    }

}
