package com.carloscortina.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="appointment")
public class Appointment implements Serializable{

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idAppointment")
    private Integer idAppointment;

    @NotNull
    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    private Date date;
    
    @NotNull
    @Column(name = "startTime")
    @Temporal(TemporalType.TIME)
    private Date startTime;
    
    @Basic(optional = false)
    @Column(name = "endTime")
    @Temporal(TemporalType.TIME)
    private Date endTime;
    
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
    
    @JoinColumn(name = "registeredBy", referencedColumnName = "idUser")
    @ManyToOne(optional = false)
    private User registeredBy;
    
    @JoinColumn(name = "idPatient", referencedColumnName = "idPatient")
    @ManyToOne(optional = false)
    private Patient idPatient;
    
    @JoinColumn(name = "idDoctor", referencedColumnName = "idUser")
    @ManyToOne(optional = false)
    private User idDoctor;
    
    @Column(name = "weight")
    private Double weight;
    @Column(name = "temperature")
    private Double temperature;
    @Column(name = "size")
    private Double size;
    @Column(name = "ta")
    private Double ta;
    @Column(name = "ta2")
    private Double ta2;
    @Column(name = "taAverage")
    private Double taAverage;
    @Column(name = "pc")
    private Double pc;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "appointment")
    private List<AppointmentVaccine> appointmentVaccineList;
    @Basic(optional = false)
    @Column(name = "programmedBySystem")
    private short programmedBySystem;

    public Appointment() {
    }

    public Appointment(Integer idAppointment) {
        this.idAppointment = idAppointment;
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

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
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

    public User getRegisteredBy() {
        return registeredBy;
    }

    public void setRegisteredBy(User registeredBy) {
        this.registeredBy = registeredBy;
    }

    public Patient getIdPatient() {
        return idPatient;
    }

    public void setIdPatient(Patient idPatient) {
        this.idPatient = idPatient;
    }

    public User getIdDoctor() {
        return idDoctor;
    }

    public void setIdDoctor(User idDoctor) {
        this.idDoctor = idDoctor;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public Double getSize() {
        return size;
    }

    public void setSize(Double size) {
        this.size = size;
    }

    public Double getTa() {
        return ta;
    }

    public void setTa(Double ta) {
        this.ta = ta;
    }

    public Double getTa2() {
        return ta2;
    }

    public void setTa2(Double ta2) {
        this.ta2 = ta2;
    }

    public Double getTaAverage() {
        return taAverage;
    }

    public void setTaAverage(Double taAverage) {
        this.taAverage = taAverage;
    }

    public Double getPc() {
        return pc;
    }

    public void setPc(Double pc) {
        this.pc = pc;
    }

    public List<AppointmentVaccine> getAppointmentVaccineList() {
        return appointmentVaccineList;
    }

    public void setAppointmentVaccineList(List<AppointmentVaccine> appointmentVaccineList) {
        this.appointmentVaccineList = appointmentVaccineList;
    }
    
    public short getProgrammedBySystem() {
        return programmedBySystem;
    }

    public void setProgrammedBySystem(short programmedBySystem) {
        this.programmedBySystem = programmedBySystem;
    }
}
