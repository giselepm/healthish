package br.com.giselepm.healthish.service.impl;

import br.com.giselepm.healthish.dao.ICrudDao;
import br.com.giselepm.healthish.entity.IEntity;
import br.com.giselepm.healthish.service.ICrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

@Component
@Transactional(readOnly = true)
public class CrudService implements ICrudService {

    private ICrudDao crudDao;

    @Autowired
    public CrudService(ICrudDao crudDao) {
        this.crudDao = crudDao;
    }

    @Override
    public <T extends IEntity<? extends Serializable>> T get(Class<T> entityClass, Serializable id) {
        return crudDao.get(entityClass, id);
    }

    @Override
    public <T extends IEntity<? extends Serializable>> List<T> findAll(Class<T> entityClass) {
        return crudDao.findAll(entityClass);
    }

    @Override
    @Transactional(readOnly = false)
    public <T extends IEntity<? extends Serializable>> T save(T entityClass) {
        return crudDao.save(entityClass);
    }

 @Override
    @Transactional(readOnly = false)
    public <T extends IEntity<? extends Serializable>> T update(T entityClass) {
        return crudDao.update(entityClass);
    }

    @Override
    @Transactional(readOnly = false)
    public <T extends IEntity<? extends Serializable>> void delete(T entityClass) {
        crudDao.delete(entityClass);
    }
}
