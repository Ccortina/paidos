/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.carloscortina.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ccortina_Mac
 */
@Entity
@Table(name = "patientVaccine")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PatientVaccine.findAll", query = "SELECT p FROM PatientVaccine p"),
    @NamedQuery(name = "PatientVaccine.findByIdPatient", query = "SELECT p FROM PatientVaccine p WHERE p.patientVaccinePK.idPatient = :idPatient"),
    @NamedQuery(name = "PatientVaccine.findByIdVaccine", query = "SELECT p FROM PatientVaccine p WHERE p.patientVaccinePK.idVaccine = :idVaccine"),
    @NamedQuery(name = "PatientVaccine.findByProgramedDate", query = "SELECT p FROM PatientVaccine p WHERE p.programedDate = :programedDate"),
    @NamedQuery(name = "PatientVaccine.findByApplicationDate", query = "SELECT p FROM PatientVaccine p WHERE p.applicationDate = :applicationDate"),
    @NamedQuery(name = "PatientVaccine.findBySuspended", query = "SELECT p FROM PatientVaccine p WHERE p.suspended = :suspended"),
    @NamedQuery(name = "PatientVaccine.findBySuspensionDate", query = "SELECT p FROM PatientVaccine p WHERE p.suspensionDate = :suspensionDate"),
    @NamedQuery(name = "PatientVaccine.findByNotes", query = "SELECT p FROM PatientVaccine p WHERE p.notes = :notes"),
    @NamedQuery(name = "PatientVaccine.findByProgramManual", query = "SELECT p FROM PatientVaccine p WHERE p.programManual = :programManual"),
    @NamedQuery(name = "PatientVaccine.findByName", query = "SELECT p FROM PatientVaccine p WHERE p.name = :name"),
    @NamedQuery(name = "PatientVaccine.findByBatch", query = "SELECT p FROM PatientVaccine p WHERE p.batch = :batch"),
    @NamedQuery(name = "PatientVaccine.findByExpirationDate", query = "SELECT p FROM PatientVaccine p WHERE p.expirationDate = :expirationDate")})
public class PatientVaccine implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PatientVaccinePK patientVaccinePK;
    @Column(name = "programedDate")
    @Temporal(TemporalType.DATE)
    private Date programedDate;
    @Column(name = "applicationDate")
    @Temporal(TemporalType.DATE)
    private Date applicationDate;
    @Basic(optional = false)
    @Column(name = "suspended")
    private short suspended;
    @Column(name = "suspensionDate")
    @Temporal(TemporalType.DATE)
    private Date suspensionDate;
    @Column(name = "notes")
    private String notes;
    @Basic(optional = false)
    @Column(name = "programManual")
    private short programManual;
    @Column(name = "name")
    private String name;
    @Column(name = "batch")
    private String batch;
    @Column(name = "expirationDate")
    @Temporal(TemporalType.DATE)
    private Date expirationDate;
    @JoinColumn(name = "idVaccine", referencedColumnName = "IdVaccine", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Vaccine vaccine;
    @JoinColumn(name = "idPatient", referencedColumnName = "idPatient", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Patient patient;

    public PatientVaccine() {
    }

    public PatientVaccine(PatientVaccinePK patientVaccinePK) {
        this.patientVaccinePK = patientVaccinePK;
    }

    public PatientVaccine(PatientVaccinePK patientVaccinePK, short suspended, short programManual) {
        this.patientVaccinePK = patientVaccinePK;
        this.suspended = suspended;
        this.programManual = programManual;
    }

    public PatientVaccine(int idPatient, int idVaccine) {
        this.patientVaccinePK = new PatientVaccinePK(idPatient, idVaccine);
    }

    public PatientVaccinePK getPatientVaccinePK() {
        return patientVaccinePK;
    }

    public void setPatientVaccinePK(PatientVaccinePK patientVaccinePK) {
        this.patientVaccinePK = patientVaccinePK;
    }

    public Date getProgramedDate() {
        return programedDate;
    }

    public void setProgramedDate(Date programedDate) {
        this.programedDate = programedDate;
    }

    public Date getApplicationDate() {
        return applicationDate;
    }

    public void setApplicationDate(Date applicationDate) {
        this.applicationDate = applicationDate;
    }

    public short getSuspended() {
        return suspended;
    }

    public void setSuspended(short suspended) {
        this.suspended = suspended;
    }

    public Date getSuspensionDate() {
        return suspensionDate;
    }

    public void setSuspensionDate(Date suspensionDate) {
        this.suspensionDate = suspensionDate;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public short getProgramManual() {
        return programManual;
    }

    public void setProgramManual(short programManual) {
        this.programManual = programManual;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public Vaccine getVaccine() {
        return vaccine;
    }

    public void setVaccine(Vaccine vaccine) {
        this.vaccine = vaccine;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (patientVaccinePK != null ? patientVaccinePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PatientVaccine)) {
            return false;
        }
        PatientVaccine other = (PatientVaccine) object;
        if ((this.patientVaccinePK == null && other.patientVaccinePK != null) || (this.patientVaccinePK != null && !this.patientVaccinePK.equals(other.patientVaccinePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pruebas1.PatientVaccine[ patientVaccinePK=" + patientVaccinePK + " ]";
    }
    
}
