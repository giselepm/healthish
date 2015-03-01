package br.com.giselepm.healthish.dao.impl;

import br.com.giselepm.healthish.dao.IDoctorDao;
import br.com.giselepm.healthish.entity.Doctor;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class DoctorDao implements IDoctorDao {

    private SessionFactory sessionFactory;

    @Autowired
    public DoctorDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Doctor> findAll() {
        return sessionFactory.getCurrentSession().createCriteria(Doctor.class).list();
    }

}
