package br.com.giselepm.healthish.controller;

import br.com.giselepm.healthish.entity.Secretary;
import br.com.giselepm.healthish.service.ICrudService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.ui.ModelMap;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SecretaryControllerTest {

    ICrudService crudServiceMock;
    SecretaryController secretaryController;

    @Before
    public void setUp() throws Exception {
        crudServiceMock = mock(ICrudService.class);
        secretaryController = new SecretaryController(crudServiceMock);
    }

    @Test
    public void listRetrievesAllSecretariesAndRedirectsToTheListPage() throws Exception {
        when(crudServiceMock.findAll(Secretary.class)).thenReturn(new ArrayList<Secretary>() {{
            add(new Secretary(1, "Phil"));
            add(new Secretary(2, "Arnold"));
        }});

        ModelMap modelMap = new ModelMap();
        String page = secretaryController.list(modelMap);

        assertEquals(page, "secretary/list");

        assertEquals(modelMap.get("allSecretaries"), new ArrayList<Secretary>() {{
            add(new Secretary(1, "Phil"));
            add(new Secretary(2, "Arnold"));
        }});
    }

    @Test
    public void listRetrievesEmptyListWhenDbIsEmptyAndRedirectsToTheListPage() throws Exception {
        when(crudServiceMock.findAll(Secretary.class)).thenReturn(new ArrayList<Secretary>());

        ModelMap modelMap = new ModelMap();
        String page = secretaryController.list(modelMap);

        assertEquals(page, "secretary/list");

        assertEquals(modelMap.get("allSecretaries"), new ArrayList<Secretary>());
    }

}