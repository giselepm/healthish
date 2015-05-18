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
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
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
    public String create() {
        return "doctor/create";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/saveCreate")
    @Transactional
    public String saveCreate(@ModelAttribute Doctor doctor, ModelMap model) {
        crudService.save(doctor);
        return list(model);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/delete")
    @Transactional
    public String delete(Doctor doctor, ModelMap model) { //como eu passo o doctor inteiro na jsp?
        crudService.delete(doctor);
        return list(model);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/delete")
    @Transactional
    public String delete(Long id, ModelMap model) {
        Doctor doctor = crudService.get(Doctor.class, id);
        crudService.delete(doctor);
        return list(model);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/edit")
    @Transactional
    public String edit(Long id, ModelMap model) {
        Doctor doctor = crudService.get(Doctor.class, id);
        model.addAttribute("doctor", doctor);

        return "doctor/edit";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/saveEdit")
    @Transactional
    public String saveEdit(@ModelAttribute Doctor doctor, ModelMap model) {
        crudService.update(doctor);
        return list(model);
    }


}