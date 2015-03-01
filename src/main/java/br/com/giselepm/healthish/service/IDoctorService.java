package br.com.giselepm.healthish.service;

import br.com.giselepm.healthish.entity.Doctor;

import java.util.List;

public interface IDoctorService {
    List<Doctor> findAll();
}
