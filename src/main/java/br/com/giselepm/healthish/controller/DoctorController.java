package br.com.giselepm.healthish.controller;

import br.com.giselepm.healthish.entity.Doctor;
import br.com.giselepm.healthish.service.ICrudService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/doctor")
public class DoctorController {

    private Logger log = LoggerFactory.getLogger(DoctorController.class);
    private ICrudService crudService;

    @Autowired
    public DoctorController(ICrudService crudService) {
        this.crudService = crudService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/list")
    @Transactional(readOnly = true)
    public String list(ModelMap model) {
        List<Doctor> allDoctors = crudService.findAll(Doctor.class);
        model.addAttribute("allDoctors", allDoctors);
        return "doctor/list";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/create")
    @Transactional(readOnly = true)
    public String create(ModelMap model) {
        model.addAttribute("action", "Create");
        return "doctor/show";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/saveCreate")
    @Transactional
    public String saveCreate(@ModelAttribute Doctor doctor, ModelMap model) {
        crudService.save(doctor);
        return list(model);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/delete")
    @Transactional
    public String delete(Long id, ModelMap model) {
        Doctor doctor = crudService.get(Doctor.class, id);
        model.addAttribute("doctor", doctor);
        model.addAttribute("action", "Delete");
        return "doctor/show";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/saveDelete")
    @Transactional
    public String saveDelete(Long id, ModelMap model) {
        Doctor doctor = crudService.get(Doctor.class, id);
        if (doctor != null) {
            crudService.delete(doctor);
            model.addAttribute("doctorSuccessMsg", "The doctor was successfully deleted.");
        } else {
            model.addAttribute("doctorErrorMsg", "The doctor you're trying to delete doesn't exist any more.");
        }
        return list(model);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/edit")
    @Transactional
    public String edit(Long id, ModelMap model) {
        Doctor doctor = crudService.get(Doctor.class, id);
        model.addAttribute("doctor", doctor);
        model.addAttribute("action", "Edit");
        return "doctor/show";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/saveEdit")
    @Transactional
    public String saveEdit(@ModelAttribute Doctor doctor, ModelMap model) {
        if (crudService.get(Doctor.class, doctor.getId()) != null) {
            crudService.update(doctor);
            model.addAttribute("doctorSuccessMsg", "The doctor was successfully updated.");
        } else {
            model.addAttribute("doctorErrorMsg", "The doctor you're trying to edit doesn't exist any more.");
        }

        return list(model);
    }
}