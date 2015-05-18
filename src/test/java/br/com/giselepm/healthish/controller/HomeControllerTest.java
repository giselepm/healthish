package br.com.giselepm.healthish.controller;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class HomeControllerTest {

    HomeController homeController;

    @Before
    public void setUp() throws Exception {
        homeController = new HomeController();
    }

    @Test
    public void indexRedirectsToTheIndexPage() throws Exception {
        String page = homeController.index();

        assertEquals(page, "index");
    }

}