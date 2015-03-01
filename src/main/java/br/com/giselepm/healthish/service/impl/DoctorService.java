package br.com.giselepm.healthish.service.impl;

import br.com.giselepm.healthish.dao.IDoctorDao;
import br.com.giselepm.healthish.entity.Doctor;
import br.com.giselepm.healthish.service.IDoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DoctorService implements IDoctorService {

    private IDoctorDao doctorDao;

    @Autowired
    public DoctorService(IDoctorDao doctorDao) {
        this.doctorDao = doctorDao;
    }

    @Override
    public List<Doctor> findAll() {
        return doctorDao.findAll();
    }
}
