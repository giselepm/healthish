package br.com.giselepm.healthish.dao.impl;

import br.com.giselepm.healthish.DatabaseUnitTest;
import br.com.giselepm.healthish.dao.ICrudDao;
import br.com.giselepm.healthish.entity.Appointment;
import br.com.giselepm.healthish.entity.Doctor;
import br.com.giselepm.healthish.entity.Patient;
import br.com.giselepm.healthish.entity.Secretary;
import org.apache.commons.beanutils.BeanToPropertyValueTransformer;
import org.apache.commons.collections.CollectionUtils;
import org.hibernate.SessionFactory;
import org.junit.Ignore;
import org.junit.Test;
import org.unitils.spring.annotation.SpringBeanByType;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class CrudDaoTest extends DatabaseUnitTest {

    @SpringBeanByType
    ICrudDao crudDao;

    @SpringBeanByType
    SessionFactory sessionFactory;

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
    @Ignore
    public void whenIGetByClassAppointmentAndId1ItReturnsAppointment1() throws Exception {
        Appointment a = crudDao.get(Appointment.class, 1L);

        assertNotNull(a);
        assertEquals(1L, a.getId().longValue());
        assertEquals("1", a.getDoctor().getId());
        assertEquals("2", a.getPatient().getId());
        assertEquals("2015-04-20 15:30", a.getAppointmentDate().toString());

    }

    @Test
    @Ignore
    public void whenIGetByClassAppointmentAndId4ItReturnsNull() throws Exception {
        Appointment a = crudDao.get(Appointment.class, 4L);

        assertNull(a);
    }


    @Test
    @Ignore
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

    @Test
    public void saveCreatesARecordInDB() throws Exception {
        Doctor d = new Doctor();

        d.setName("name");
        d.setStreet("street");
        d.setStreetNo("streetNo");
        d.setCity("city");
        d.setZip("10829");

        crudDao.save(d);

        List<Doctor> doctorsInDB = sessionFactory.getCurrentSession().createCriteria(Doctor.class).list();
        Collection<String> allDoctorsNamesInDB = CollectionUtils.collect(doctorsInDB, new BeanToPropertyValueTransformer("name"));

        assertEquals(3, doctorsInDB.size());
        assertTrue(allDoctorsNamesInDB.containsAll(new ArrayList<Object>() {{
            add("PHIL");
            add("ARNOLD");
            add("name");
        }}));
    }

    @Test
    public void deleteRemovesARecordFromDB() throws Exception {
        crudDao.delete(new Doctor(1, "PHIL"));

        Doctor d = crudDao.get(Doctor.class, 1L);

        assertNull(d);

        List<Doctor> doctorsInDB = sessionFactory.getCurrentSession().createCriteria(Doctor.class).list();
        Collection<Long> allIds = CollectionUtils.collect(doctorsInDB, new BeanToPropertyValueTransformer("id"));

        assertEquals(1, doctorsInDB.size());
        assertTrue(allIds.containsAll(new ArrayList<Object>() {{
            add(2L);
        }}));

    }

    @Test
    @Ignore
    public void whenIDeleteARecordInexistentInDB() throws Exception {
        crudDao.delete(new Doctor(3, "JAMES")); //como testo isso?? Pq ele t[a dando erro?

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
    public void updateSaveTheNewInformationInDB() throws Exception {
        Doctor d = new Doctor();

        d.setId(1L);
        d.setName("New name");

        crudDao.update(d);

        List<Doctor> doctorsInDB = sessionFactory.getCurrentSession().createCriteria(Doctor.class).list();
        Collection<String> allDoctorsNamesInDB = CollectionUtils.collect(doctorsInDB, new BeanToPropertyValueTransformer("name"));

        assertEquals(2, doctorsInDB.size());
        assertTrue(allDoctorsNamesInDB.containsAll(new ArrayList<Object>() {{
            add("New name");
            add("ARNOLD");
        }}));
    }

    @Test
    @Ignore
    public void whenITrytoUpdateARecordInexistentInDB() throws Exception {
        Doctor d = new Doctor(); //como eu testo isso??

        d.setId(3L);
        d.setName("New name");

        crudDao.update(d);

        List<Doctor> doctorsInDB = sessionFactory.getCurrentSession().createCriteria(Doctor.class).list();
        Collection<String> allDoctorsNamesInDB = CollectionUtils.collect(doctorsInDB, new BeanToPropertyValueTransformer("name"));

        assertEquals(2, doctorsInDB.size());
        assertTrue(allDoctorsNamesInDB.containsAll(new ArrayList<Object>() {{
            add("PHIL");
            add("ARNOLD");
        }}));
    }
}