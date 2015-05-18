package br.com.giselepm.healthish.controller;

import br.com.giselepm.healthish.entity.Secretary;
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

/**
 * Created by gisele on 4/4/15.
 */
@Controller
@RequestMapping("/secretary")
public class SecretaryController {

    private Logger log = LoggerFactory.getLogger(SecretaryController.class);
    private ICrudService crudService;

    @Autowired
    public SecretaryController(ICrudService crudService) {
        this.crudService = crudService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/list")
    @Transactional(readOnly = true)
    public String list(ModelMap model) {
        List<Secretary> allSecretaries = crudService.findAll(Secretary.class);
        model.addAttribute("allSecretaries", allSecretaries);
        return "secretary/list";
    }

}
