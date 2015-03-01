package br.com.giselepm.healthish.dao;

import br.com.giselepm.healthish.entity.Doctor;
import java.util.List;

public interface IDoctorDao {
    List<Doctor> findAll();
}
