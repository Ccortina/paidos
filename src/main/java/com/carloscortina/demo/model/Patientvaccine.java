/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
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
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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
@Table(name = "patientvaccine")
@NamedQueries({
    @NamedQuery(name = "Patientvaccine.findAll", query = "SELECT p FROM Patientvaccine p")})
public class Patientvaccine implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PatientvaccinePK patientvaccinePK;
    @Column(name = "programedDate")
    @Temporal(TemporalType.DATE)
    private Date programedDate;
    @Column(name = "applicationDate")
    @Temporal(TemporalType.DATE)
    private Date applicationDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "suspended")
    private int suspended;
    @Column(name = "suspensionDate")
    @Temporal(TemporalType.DATE)
    private Date suspensionDate;
    @Size(max = 65535)
    @Column(name = "notes")
    private String notes;
    @Basic(optional = false)
    @NotNull
    @Column(name = "programManual")
    private int programManual;
    @Size(max = 100)
    @Column(name = "name")
    private String name;
    @Size(max = 45)
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

    public Patientvaccine() {
    }

    public Patientvaccine(PatientvaccinePK patientvaccinePK) {
        this.patientvaccinePK = patientvaccinePK;
    }

    public Patientvaccine(PatientvaccinePK patientvaccinePK, int suspended, int programManual) {
        this.patientvaccinePK = patientvaccinePK;
        this.suspended = suspended;
        this.programManual = programManual;
    }

    public Patientvaccine(Vaccine vaccine, Patient patient) {
        this.vaccine = vaccine;
        this.patient = patient;
    }

    public Patientvaccine(int idPatient, int idVaccine) {
        this.patientvaccinePK = new PatientvaccinePK(idPatient, idVaccine);
    }

    public PatientvaccinePK getPatientvaccinePK() {
        return patientvaccinePK;
    }

    public void setPatientvaccinePK(PatientvaccinePK patientvaccinePK) {
        this.patientvaccinePK = patientvaccinePK;
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

    public int getSuspended() {
        return suspended;
    }

    public void setSuspended(int suspended) {
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

    public int getProgramManual() {
        return programManual;
    }

    public void setProgramManual(int programManual) {
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
        hash += (patientvaccinePK != null ? patientvaccinePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Patientvaccine)) {
            return false;
        }
        Patientvaccine other = (Patientvaccine) object;
        if ((this.patientvaccinePK == null && other.patientvaccinePK != null) || (this.patientvaccinePK != null && !this.patientvaccinePK.equals(other.patientvaccinePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.carloscortina.demo.model.Patientvaccine[ patientvaccinePK=" + patientvaccinePK + " ]";
    }
    
}
