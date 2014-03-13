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
    private Date date;
    
    @NotNull
    @Column(name = "time")
    private Date time;
    
    @Size(max = 45)
    @Column(name = "motive")
    private String motive;

    @Size(max = 65535)
    @Column(name = "notes")
    private String notes;
    
    @Size(max = 45)
    @Column(name = "weigth")
    private String weigth;
    
    @Size(max = 45)
    @Column(name = "size")
    private String size;
    
    @Size(max = 45)
    @Column(name = "pc")
    private String pc;
    
    @Size(max = 45)
    @Column(name = "ta")
    private String ta;
    
    @Size(max = 45)
    @Column(name = "ta2")
    private String ta2;
    
    @Size(max = 45)
    @Column(name = "averageTa")
    private String averageTa;
    
    @Size(max = 45)
    @Column(name = "temperature")
    private String temperature;
    
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

    public String getWeigth() {
        return weigth;
    }

    public void setWeigth(String weigth) {
        this.weigth = weigth;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getPc() {
        return pc;
    }

    public void setPc(String pc) {
        this.pc = pc;
    }

    public String getTa() {
        return ta;
    }

    public void setTa(String ta) {
        this.ta = ta;
    }

    public String getTa2() {
        return ta2;
    }

    public void setTa2(String ta2) {
        this.ta2 = ta2;
    }

    public String getAverageTa() {
        return averageTa;
    }

    public void setAverageTa(String averageTa) {
        this.averageTa = averageTa;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
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
