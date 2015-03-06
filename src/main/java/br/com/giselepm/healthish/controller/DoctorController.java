package br.com.giselepm.healthish.controller;

import br.com.giselepm.healthish.dao.IDoctorDao;
import br.com.giselepm.healthish.entity.Doctor;
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
@RequestMapping("/")
public class DoctorController {

    private Logger log = LoggerFactory.getLogger(DoctorController.class);
    private IDoctorDao doctorDao;

    @Autowired
    public DoctorController(IDoctorDao doctorDao) {
        this.doctorDao = doctorDao;
    }

    @RequestMapping(method = RequestMethod.GET)
    @Transactional(readOnly = true)
    public String printWelcome(ModelMap model) {
        log.info("ENTER printWelcome");
        List<Doctor> allDoctors = doctorDao.findAll();
        model.addAttribute("allDoctors", allDoctors);
        return "hello";
    }

}