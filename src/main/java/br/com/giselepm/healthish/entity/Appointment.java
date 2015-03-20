package br.com.giselepm.healthish.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table
public class Appointment implements IEntity<Long> {

    @Id
    @Column
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column
    private Doctor doctor;

    @Column
    private Patient patient;

    @Column
    private Date appointmentDate;


    public Appointment() {
    }

    public Appointment(Integer id, Doctor doctor, Patient patient, Date appointmentDate) {
        this.id = id.longValue();
        this.doctor = doctor;
        this.patient = patient;
        this.appointmentDate = appointmentDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @ManyToOne(cascade=CascadeType.ALL)
    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    @ManyToOne(cascade=CascadeType.ALL)
    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Date getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(Date appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Appointment patient = (Appointment) o;

        if (id != null ? !id.equals(patient.id) : patient.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
