package br.com.giselepm.healthish.service;

import br.com.giselepm.healthish.entity.IEntity;

import java.io.Serializable;
import java.util.List;

public interface ICrudService {

    <T extends IEntity<? extends Serializable>> T get(
            final Class<T> entityClass, final Serializable id);

    <T extends IEntity<? extends Serializable>> List<T> findAll(
            final Class<T> entityClass);

    <T extends IEntity<? extends Serializable>> T save(final T entityClass);

    <T extends IEntity<? extends Serializable>> T update(final T entityClass);

    <T extends IEntity<? extends Serializable>> void delete(final T entityClass);

}
