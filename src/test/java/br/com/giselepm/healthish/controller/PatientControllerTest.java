package br.com.giselepm.healthish.controller;

import br.com.giselepm.healthish.entity.Patient;
import br.com.giselepm.healthish.service.ICrudService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.ui.ModelMap;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PatientControllerTest {

    ICrudService crudServiceMock;
    PatientController patientController;

    @Before
    public void setUp() throws Exception {
        crudServiceMock = mock(ICrudService.class);
        patientController = new PatientController(crudServiceMock);
    }

    @Test
    public void listRetrievesAllPatientsAndRedirectsToTheListPage() throws Exception {
        when(crudServiceMock.findAll(Patient.class)).thenReturn(new ArrayList<Patient>() {{
            add(new Patient(1, "Phil"));
            add(new Patient(2, "Arnold"));
        }});

        ModelMap modelMap = new ModelMap();
        String page = patientController.list(modelMap);

        assertEquals(page, "patient/list");

        assertEquals(modelMap.get("allPatients"), new ArrayList<Patient>() {{
            add(new Patient(1, "Phil"));
            add(new Patient(2, "Arnold"));
        }});
    }

    @Test
    public void listRetrievesEmptyListWhenDbIsEmptyAndRedirectsToTheListPage() throws Exception {
        when(crudServiceMock.findAll(Patient.class)).thenReturn(new ArrayList<Patient>());

        ModelMap modelMap = new ModelMap();
        String page = patientController.list(modelMap);

        assertEquals(page, "patient/list");

        assertEquals(modelMap.get("allPatients"), new ArrayList<Patient>());
    }

}