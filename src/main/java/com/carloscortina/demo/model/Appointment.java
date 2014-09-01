/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Carlos Cortina
 */
@Entity
@Table(name = "appointment")
@NamedQueries({
    @NamedQuery(name = "Appointment.findAll", query = "SELECT a FROM Appointment a")})
public class Appointment implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idAppointment")
    private Integer idAppointment;
    @Basic(optional = false)
    @NotNull
    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    private Date date;
    @Basic(optional = false)
    @NotNull
    @Column(name = "startTime")
    @Temporal(TemporalType.TIME)
    private Date startTime;
    @Size(max = 60)
    @Column(name = "motive")
    private String motive;
    @Size(max = 65535)
    @Column(name = "notes")
    private String notes;
    @Basic(optional = false)
    @NotNull
    @Column(name = "immunization")
    private int immunization;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
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
    @Basic(optional = false)
    @NotNull
    @Column(name = "programmedBySystem")
    private int programmedBySystem;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idAppointment")
    private List<Consultation> consultationList;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "appointment")
    private List<Appointmentvaccine> appointmentvaccineList;
    @JoinColumn(name = "idDoctor", referencedColumnName = "idUser")
    @ManyToOne(optional = false)
    private User idDoctor;
    @JoinColumn(name = "registeredBy", referencedColumnName = "idUser")
    @ManyToOne(optional = false)
    private User registeredBy;
    @JoinColumn(name = "idStatus", referencedColumnName = "idAppointmentStatus")
    @ManyToOne(optional = false)
    private Appointmentstatus idStatus;
    @JoinColumn(name = "idPatient", referencedColumnName = "idPatient")
    @ManyToOne(optional = false)
    private Patient idPatient;
    @Column(name = "tempClaveCita")
    private Integer tempClaveCita;

    public Appointment() {
    }

    public Appointment(Integer idAppointment) {
        this.idAppointment = idAppointment;
    }

    public Appointment(Integer idAppointment, Date date, Date startTime, int immunization, int programmedBySystem) {
        this.idAppointment = idAppointment;
        this.date = date;
        this.startTime = startTime;
        this.immunization = immunization;
        this.programmedBySystem = programmedBySystem;
    }

    public Appointment(Date date, Date startTime, String motive, String notes, int immunization, Double weight, Double temperature, Double size, Double ta, Double ta2, Double taAverage, Double pc, int programmedBySystem, User idDoctor, User registeredBy, Appointmentstatus idStatus, Patient idPatient) {
        this.date = date;
        this.startTime = startTime;
        this.motive = motive;
        this.notes = notes;
        this.immunization = immunization;
        this.weight = weight;
        this.temperature = temperature;
        this.size = size;
        this.ta = ta;
        this.ta2 = ta2;
        this.taAverage = taAverage;
        this.pc = pc;
        this.programmedBySystem = programmedBySystem;
        this.idDoctor = idDoctor;
        this.registeredBy = registeredBy;
        this.idStatus = idStatus;
        this.idPatient = idPatient;
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

    public int getImmunization() {
        return immunization;
    }

    public void setImmunization(int immunization) {
        this.immunization = immunization;
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

    public int getProgrammedBySystem() {
        return programmedBySystem;
    }

    public void setProgrammedBySystem(int programmedBySystem) {
        this.programmedBySystem = programmedBySystem;
    }

    public List<Consultation> getConsultationList() {
        return consultationList;
    }

    public void setConsultationList(List<Consultation> consultationList) {
        this.consultationList = consultationList;
    }

    public List<Appointmentvaccine> getAppointmentvaccineList() {
        return appointmentvaccineList;
    }

    public void setAppointmentvaccineList(List<Appointmentvaccine> appointmentvaccineList) {
        this.appointmentvaccineList = appointmentvaccineList;
    }

    public User getIdDoctor() {
        return idDoctor;
    }

    public void setIdDoctor(User idDoctor) {
        this.idDoctor = idDoctor;
    }

    public User getRegisteredBy() {
        return registeredBy;
    }

    public void setRegisteredBy(User registeredBy) {
        this.registeredBy = registeredBy;
    }

    public Appointmentstatus getIdStatus() {
        return idStatus;
    }

    public void setIdStatus(Appointmentstatus idStatus) {
        this.idStatus = idStatus;
    }

    public Patient getIdPatient() {
        return idPatient;
    }

    public void setIdPatient(Patient idPatient) {
        this.idPatient = idPatient;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAppointment != null ? idAppointment.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Appointment)) {
            return false;
        }
        Appointment other = (Appointment) object;
        if ((this.idAppointment == null && other.idAppointment != null) || (this.idAppointment != null && !this.idAppointment.equals(other.idAppointment))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.carloscortina.demo.model.Appointment[ idAppointment=" + idAppointment + " ]";
    }

    public Integer getTempClaveCita() {
        return tempClaveCita;
    }

    public void setTempClaveCita(Integer tempClaveCita) {
        this.tempClaveCita = tempClaveCita;
    }
    
}
