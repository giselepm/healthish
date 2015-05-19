package br.com.giselepm.healthish.controller;

import br.com.giselepm.healthish.entity.Doctor;
import br.com.giselepm.healthish.service.ICrudService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.ui.ModelMap;

import java.util.ArrayList;

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
        ModelMap modelMap = new ModelMap();
        String page = doctorController.create(modelMap);
        assertEquals(modelMap.get("action"), "Create");
        assertEquals(page, "doctor/show");
    }

    @Test
    public void saveCreatePersistsDoctorAndRedirectsToTheListPage() throws Exception {

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
    public void deleteReceivesDoctorIdGetsDoctorAndRedirectsToTheDeletePage() throws Exception {

        when(crudServiceMock.get(Doctor.class, 1L)).thenReturn(new Doctor(1, "Doctor Name"));

        ModelMap modelMap = new ModelMap();
        String page = doctorController.delete(1L, modelMap);

        assertEquals(modelMap.get("doctor"), new Doctor(1, "Doctor Name"));
        assertEquals(modelMap.get("action"), "Delete");
        assertEquals(page, "doctor/show");

    }

    @Test
    public void saveDeleteReceiveDoctorIdGetsDoctorRemoveItAndRedirectsToTheListPage() throws Exception {

        Doctor d = new Doctor(1, "Doctor Name");
        when(crudServiceMock.get(Doctor.class, 1L)).thenReturn(d);

        when(crudServiceMock.findAll(Doctor.class)).thenReturn(new ArrayList<Doctor>() {{
            add(new Doctor(2, "Doctor Name 2"));
            add(new Doctor(3, "Doctor Name 3"));
        }});

        ModelMap modelMap = new ModelMap();
        String page = doctorController.saveDelete(1L, modelMap);

        verify(crudServiceMock, times(1)).delete(d);
        assertEquals(modelMap.get("doctorSuccessMsg"), "The doctor was successfully deleted.");

        assertEquals(page, "doctor/list");

        assertEquals(modelMap.get("allDoctors"), new ArrayList<Doctor>() {{
            add(new Doctor(2, "Doctor Name 2"));
            add(new Doctor(3, "Doctor Name 3"));
        }});

    }

    @Test
    public void saveDeleteReceiveInexistentDoctorIdTriesToGetDoctorSetsErroMessageAndRedirectsToTheListPage() throws Exception {

        when(crudServiceMock.get(Doctor.class, 1L)).thenReturn(null);

        when(crudServiceMock.findAll(Doctor.class)).thenReturn(new ArrayList<Doctor>() {{
            add(new Doctor(2, "Doctor Name 2"));
            add(new Doctor(3, "Doctor Name 3"));
        }});

        ModelMap modelMap = new ModelMap();
        String page = doctorController.saveDelete(1L, modelMap);

        verify(crudServiceMock, times(0)).delete(any(Doctor.class));
        assertEquals(modelMap.get("doctorErrorMsg"), "The doctor you're trying to delete doesn't exist any more.");

        assertEquals(page, "doctor/list");
        assertEquals(modelMap.get("allDoctors"), new ArrayList<Doctor>() {{
            add(new Doctor(2, "Doctor Name 2"));
            add(new Doctor(3, "Doctor Name 3"));
        }});

    }

    @Test
    public void editReceivesDoctorIdGetsDoctorAndRedirectsToTheEditPage() throws Exception {

        when(crudServiceMock.get(Doctor.class, 1L)).thenReturn(new Doctor(1, "Doctor Name"));

        ModelMap modelMap = new ModelMap();
        String page = doctorController.edit(1L, modelMap);

        assertEquals(modelMap.get("doctor"), new Doctor(1, "Doctor Name"));
        assertEquals(modelMap.get("action"), "Edit");
        assertEquals(page, "doctor/show");

    }

    @Test
    public void saveEditReceivesDoctorUpdateItAndRedirectsToTheListPage() throws Exception {
        Doctor d = new Doctor(2, "Doctor Name");
        when(crudServiceMock.get(Doctor.class, 2L)).thenReturn(d);

        when(crudServiceMock.findAll(Doctor.class)).thenReturn(new ArrayList<Doctor>() {{
            add(new Doctor(1, "Phil"));
            add(new Doctor(2, "Doctor Name"));
        }});

        ModelMap modelMap = new ModelMap();

        String page = doctorController.saveEdit(d, modelMap);

        verify(crudServiceMock, times(1)).update(any(Doctor.class));
        assertEquals(modelMap.get("doctorSuccessMsg"), "The doctor was successfully updated.");

        assertEquals(page, "doctor/list");

        assertEquals(modelMap.get("allDoctors"), new ArrayList<Doctor>() {{
            add(new Doctor(1, "Phil"));
            add(new Doctor(2, "Doctor Name"));
        }});

    }

    @Test
    public void saveEditReceivesDoctorInexistentSetsErrorMessageAndRedirectsToTheListPage() throws Exception {

        when(crudServiceMock.get(Doctor.class, 50L)).thenReturn(null);

        when(crudServiceMock.findAll(Doctor.class)).thenReturn(new ArrayList<Doctor>() {{
            add(new Doctor(1, "Phil"));
            add(new Doctor(2, "Arnold"));
        }});

        ModelMap modelMap = new ModelMap();

        String page = doctorController.saveEdit(new Doctor(), modelMap);

        verify(crudServiceMock, times(0)).update(any(Doctor.class));

        assertEquals(page, "doctor/list");

        assertEquals(modelMap.get("doctorErrorMsg"), "The doctor you're trying to edit doesn't exist any more.");

        assertEquals(modelMap.get("allDoctors"), new ArrayList<Doctor>() {{
            add(new Doctor(1, "Phil"));
            add(new Doctor(2, "Arnold"));
        }});

    }
}