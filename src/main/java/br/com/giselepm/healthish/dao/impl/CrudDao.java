package br.com.giselepm.healthish.dao.impl;

import br.com.giselepm.healthish.dao.ICrudDao;
import br.com.giselepm.healthish.entity.IEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

@Component
public class CrudDao implements ICrudDao {

    private SessionFactory sessionFactory;

    @Autowired
    public CrudDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public <T extends IEntity<? extends Serializable>> T get(Class<T> entityClass, Serializable id) {
        return (T) getCurrentSession().get(entityClass, id);
    }

    @Override
    public <T extends IEntity<? extends Serializable>> List<T> findAll(Class<T> entityClass) {
        return getCurrentSession().createCriteria(entityClass).list();
    }

    private Session getCurrentSession(){
        return sessionFactory.getCurrentSession();
    }
}