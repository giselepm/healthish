package br.com.giselepm.healthish.entity;

import java.io.Serializable;

public interface IEntity<ID extends Serializable> extends Serializable {
    void setId(ID id);
    ID getId();
}
