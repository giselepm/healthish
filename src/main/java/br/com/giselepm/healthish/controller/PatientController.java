package br.com.giselepm.healthish.controller;

import br.com.giselepm.healthish.entity.Doctor;
import br.com.giselepm.healthish.entity.Patient;
import br.com.giselepm.healthish.service.ICrudService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/patient")
public class PatientController {

    private Logger log = LoggerFactory.getLogger(PatientController.class);
    private ICrudService crudService;

    @Autowired
    public PatientController(ICrudService crudService) {
        this.crudService = crudService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/list")
    @Transactional(readOnly = true)
    public String printWelcome(ModelMap model) {
        log.info("ENTER printWelcome");
        List<Patient> allPatients = crudService.findAll(Patient.class);
        model.addAttribute("allPatients", allPatients);
        return "patient";
    }

}