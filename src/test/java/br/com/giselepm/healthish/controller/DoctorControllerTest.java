package br.com.giselepm.healthish.controller;

import br.com.giselepm.healthish.dao.ICrudDao;
import br.com.giselepm.healthish.entity.Doctor;
import br.com.giselepm.healthish.service.ICrudService;
import br.com.giselepm.healthish.service.impl.CrudService;
import org.apache.commons.beanutils.BeanToPropertyValueTransformer;
import org.apache.commons.collections.CollectionUtils;
import org.junit.Before;
import org.junit.Test;
import org.springframework.ui.ModelMap;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collection;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

public class DoctorControllerTest {

    ICrudService crudServiceMock;
    DoctorController doctorController;

    @Before
    public void setUp() throws Exception {
        crudServiceMock = mock(ICrudService.class);
        doctorController = new DoctorController(crudServiceMock);
    }

    @Test
    public void listRetrievesAllDoctorsAndRedirectsToTheListPage() throws Exception {
        when(crudServiceMock.findAll(Doctor.class)).thenReturn(new ArrayList<Doctor>() {{
            add(new Doctor(1, "Phil"));
            add(new Doctor(2, "Arnold"));
        }});

        ModelMap modelMap = new ModelMap();
        String page = doctorController.list(modelMap);

        assertEquals(page, "doctor/list");

        assertEquals(modelMap.get("allDoctors"), new ArrayList<Doctor>() {{
            add(new Doctor(1, "Phil"));
            add(new Doctor(2, "Arnold"));
        }});
    }

    @Test
    public void listRetrievesEmptyListWhenDbIsEmptyAndRedirectsToTheListPage() throws Exception {
        when(crudServiceMock.findAll(Doctor.class)).thenReturn(new ArrayList<Doctor>());

        ModelMap modelMap = new ModelMap();
        String page = doctorController.list(modelMap);

        assertEquals(page, "doctor/list");

        assertEquals(modelMap.get("allDoctors"), new ArrayList<Doctor>());
    }

    @Test
    public void createRedirectsToTheCreatePage() throws Exception {
        String page = doctorController.create();

        assertEquals(page, "doctor/create");
    }

    @Test
    public void saveSavesDoctorAndRedirectsToTheListPage() throws Exception {

        when(crudServiceMock.findAll(Doctor.class)).thenReturn(new ArrayList<Doctor>() {{
            add(new Doctor(1, "Phil"));
            add(new Doctor(2, "Doctor Name"));
        }});

        ModelMap modelMap = new ModelMap();
        String page = doctorController.saveCreate(new Doctor(), modelMap);

        verify(crudServiceMock, times(1)).save(any(Doctor.class));

        assertEquals(page, "doctor/list");

        assertEquals(modelMap.get("allDoctors"), new ArrayList<Doctor>() {{
            add(new Doctor(1, "Phil"));
            add(new Doctor(2, "Doctor Name"));
        }});
    }

    @Test
    public void deleteReceiveDoctorRemovesItAndRedirectsToTheListPage() throws Exception {

        when(crudServiceMock.findAll(Doctor.class)).thenReturn(new ArrayList<Doctor>() {{
            add(new Doctor(1, "Phil"));
            add(new Doctor(2, "Doctor Name"));
        }});

        ModelMap modelMap = new ModelMap();
        String page = doctorController.delete(new Doctor(), modelMap);

        verify(crudServiceMock, times(1)).delete(any(Doctor.class));

        assertEquals(page, "doctor/list");

        assertEquals(modelMap.get("allDoctors"), new ArrayList<Doctor>() {{
            add(new Doctor(1, "Phil"));
            add(new Doctor(2, "Doctor Name"));
        }});

    }
    @Test
    public void deleteReceiveDoctorIdGetsDoctorRemoveItAndRedirectsToTheListPage() throws Exception {

        when(crudServiceMock.get(Doctor.class, 1L)).thenReturn(any(Doctor.class));

        when(crudServiceMock.findAll(Doctor.class)).thenReturn(new ArrayList<Doctor>() {{
            add(new Doctor(2, "Doctor Name 2"));
            add(new Doctor(3, "Doctor Name 3"));
        }});

        ModelMap modelMap = new ModelMap();
        String page = doctorController.delete(1L, modelMap);

        verify(crudServiceMock, times(1)).delete(any(Doctor.class));

        assertEquals(page, "doctor/list");

        assertEquals(modelMap.get("allDoctors"), new ArrayList<Doctor>() {{
            add(new Doctor(2, "Doctor Name 2"));
            add(new Doctor(3, "Doctor Name 3"));
        }});

    }

    @Test
    public void editReceiveDoctorIdGetsDoctorAndRedirectsToTheEditPage() throws Exception {

        when(crudServiceMock.get(Doctor.class, 1L)).thenReturn(new Doctor(1, "Doctor Name"));

        ModelMap modelMap = new ModelMap();
        String page = doctorController.edit(1L, modelMap);

        assertEquals(modelMap.get("doctor"), new Doctor(1, "Doctor Name"));
        assertEquals(page, "doctor/edit");

    }

    @Test
    public void saveEditReceiveDoctorUpdateItAndRedirectsToTheListPage() throws Exception {

        when(crudServiceMock.findAll(Doctor.class)).thenReturn(new ArrayList<Doctor>() {{
            add(new Doctor(1, "Phil"));
            add(new Doctor(2, "Doctor Name"));
        }});

        ModelMap modelMap = new ModelMap();
        String page = doctorController.saveEdit(new Doctor(), modelMap);

        verify(crudServiceMock, times(1)).update(any(Doctor.class));

        assertEquals(page, "doctor/list");

        assertEquals(modelMap.get("allDoctors"), new ArrayList<Doctor>() {{
            add(new Doctor(1, "Phil"));
            add(new Doctor(2, "Doctor Name"));
        }});

    }

}