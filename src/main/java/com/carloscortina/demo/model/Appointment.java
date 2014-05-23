package com.carloscortina.demo.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="appointment")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idAppointment")
    private Integer idAppointment;

    @NotNull
    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    private Date date;
    
    @NotNull
    @Column(name = "time")
    @Temporal(TemporalType.TIME)
    private Date time;
    
    @Size(max = 45)
    @Column(name = "motive")
    private String motive;

    @Size(max = 65535)
    @Column(name = "notes")
    private String notes;
    
    @Column(name = "immunization")
    private boolean immunization;
    
    @JoinColumn(name = "idStatus", referencedColumnName = "idAppointmentStatus")
    @ManyToOne(optional = false)
    private AppointmentStatus idStatus;
    
    @JoinColumn(name = "registeredBy", referencedColumnName = "idStaffMember")
    @ManyToOne(optional = false)
    private StaffMember registeredBy;
    
    @JoinColumn(name = "idPatient", referencedColumnName = "idPatient")
    @ManyToOne(optional = false)
    private Patient idPatient;

    public Appointment() {
    }

    public Appointment(Integer idAppointment) {
        this.idAppointment = idAppointment;
    }

    public Appointment(Integer idAppointment, Date date, Date time) {
        this.idAppointment = idAppointment;
        this.date = date;
        this.time = time;
    }

    public Integer getIdAppointment() {
        return idAppointment;
    }

    public void setIdAppointment(Integer idAppointment) {
        this.idAppointment = idAppointment;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getMotive() {
        return motive;
    }

    public void setMotive(String motive) {
        this.motive = motive;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public boolean isImmunization() {
        return immunization;
    }

    public void setImmunization(boolean immunization) {
        this.immunization = immunization;
    }

    public AppointmentStatus getIdStatus() {
        return idStatus;
    }

    public void setIdStatus(AppointmentStatus idStatus) {
        this.idStatus = idStatus;
    }

    public StaffMember getRegisteredBy() {
        return registeredBy;
    }

    public void setRegisteredBy(StaffMember registeredBy) {
        this.registeredBy = registeredBy;
    }

    public Patient getIdPatient() {
        return idPatient;
    }

    public void setIdPatient(Patient idPatient) {
        this.idPatient = idPatient;
    }
}
