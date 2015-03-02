package br.com.giselepm.healthish.dao;

import br.com.giselepm.healthish.entity.IEntity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by gisele on 3/2/15.
 */

public interface ICrudDao {

    <T extends IEntity<? extends Serializable>> T get(
            final Class<T> entityClass, final Serializable id);

    <T extends IEntity<? extends Serializable>> List<T> findAll(
            final Class<T> entityClass);
}
