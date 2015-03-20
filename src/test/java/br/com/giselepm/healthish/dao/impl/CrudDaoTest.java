package br.com.giselepm.healthish.dao.impl;

import br.com.giselepm.healthish.DatabaseUnitTest;
import br.com.giselepm.healthish.dao.ICrudDao;
import br.com.giselepm.healthish.entity.Appointment;
import br.com.giselepm.healthish.entity.Doctor;
import br.com.giselepm.healthish.entity.Patient;
import br.com.giselepm.healthish.entity.Secretary;
import org.apache.commons.beanutils.BeanToPropertyValueTransformer;
import org.apache.commons.collections.CollectionUtils;
import org.junit.Test;
import org.unitils.spring.annotation.SpringBeanByType;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
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

    @Test
    public void whenIGetByClassPatientAndId1ItReturnsPatient1() throws Exception {
        Patient p = crudDao.get(Patient.class, 1L);

        assertNotNull(p);
        assertEquals(1L, p.getId().longValue());
        assertEquals("Patient 1", p.getName());
    }

    @Test
    public void whenIGetByClassPatientAndId4ItReturnsNull() throws Exception {
        Patient p = crudDao.get(Patient.class, 4L);

        assertNull(p);
    }


    @Test
    public void findAllPatientsReturns2Instances() throws Exception {
        List<Patient> allPatients = crudDao.findAll(Patient.class);
        Collection<Long> allIds = CollectionUtils.collect(allPatients, new BeanToPropertyValueTransformer("id"));
        Collection<Long> allNames = CollectionUtils.collect(allPatients, new BeanToPropertyValueTransformer("name"));

        assertEquals(2, allPatients.size());
        assertTrue(allIds.containsAll(new ArrayList<Object>() {{
            add(1L);
            add(2L);
        }}));
        assertTrue(allNames.containsAll(new ArrayList<Object>(){{
            add("Patient 1");
            add("Patient 2");
        }}));
    }

    @Test
    public void whenIGetByClassAppointmentAndId1ItReturnsAppointment1() throws Exception {
        Appointment a = crudDao.get(Appointment.class, 1L);

        assertNotNull(a);
        assertEquals(1L, a.getId().longValue());
        assertEquals("1", a.getDoctor().getId());
        assertEquals("2", a.getPatient().getId());
        assertEquals("2015-04-20 15:30", a.getAppointmentDate().toString());

    }

    @Test
    public void whenIGetByClassAppointmentAndId4ItReturnsNull() throws Exception {
        Appointment a = crudDao.get(Appointment.class, 4L);

        assertNull(a);
    }


    @Test
    public void findAllAppointmentsReturns2Instances() throws Exception {
        List<Appointment> allAppointments = crudDao.findAll(Appointment.class);
        Collection<Long> allIds = CollectionUtils.collect(allAppointments, new BeanToPropertyValueTransformer("id"));
        Collection<Long> allDoctors = CollectionUtils.collect(allAppointments, new BeanToPropertyValueTransformer("doctor"));
        Collection<Long> allPatients = CollectionUtils.collect(allAppointments, new BeanToPropertyValueTransformer("patient"));
        Collection<Long> allAppointmentDates = CollectionUtils.collect(allAppointments, new BeanToPropertyValueTransformer("appointmentDate"));

        assertEquals(2, allAppointments.size());
        assertTrue(allIds.containsAll(new ArrayList<Object>() {{
            add(1L);
            add(2L);
        }}));
        assertTrue(allDoctors.containsAll(new ArrayList<Object>() {{
            add("1");
            add("2");
        }}));
        assertTrue(allPatients.containsAll(new ArrayList<Object>() {{
            add("2");
            add("2");
        }}));
        assertTrue(allAppointmentDates.containsAll(new ArrayList<Object>() {{
            add("2015-04-20 15:30");
            add("2014-09-13 08:40");
        }}));
    }
}