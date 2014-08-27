/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.carloscortina.demo.model;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Carlos Cortina
 */
@Entity
@Table(name = "patient_relative")
@NamedQueries({
    @NamedQuery(name = "PatientRelative.findAll", query = "SELECT p FROM PatientRelative p")})
public class PatientRelative implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PatientRelativePK patientRelativePK;
    @JoinColumn(name = "idRelative", referencedColumnName = "idRelative", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Relative relative;
    @JoinColumn(name = "idRelationship", referencedColumnName = "idRelationship")
    @ManyToOne(optional = false)
    private Relationship idRelationship;
    @JoinColumn(name = "idPatient", referencedColumnName = "idPatient", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Patient patient;

    public PatientRelative() {
    }

    public PatientRelative(PatientRelativePK patientRelativePK) {
        this.patientRelativePK = patientRelativePK;
    }

    public PatientRelative(int idPatient, int idRelative) {
        this.patientRelativePK = new PatientRelativePK(idPatient, idRelative);
    }

    public PatientRelative(PatientRelativePK patientRelativePK, Relationship idRelationship) {
        this.patientRelativePK = patientRelativePK;
        this.idRelationship = idRelationship;
    }

    public PatientRelativePK getPatientRelativePK() {
        return patientRelativePK;
    }

    public void setPatientRelativePK(PatientRelativePK patientRelativePK) {
        this.patientRelativePK = patientRelativePK;
    }

    public Relative getRelative() {
        return relative;
    }

    public void setRelative(Relative relative) {
        this.relative = relative;
    }

    public Relationship getIdRelationship() {
        return idRelationship;
    }

    public void setIdRelationship(Relationship idRelationship) {
        this.idRelationship = idRelationship;
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
        hash += (patientRelativePK != null ? patientRelativePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PatientRelative)) {
            return false;
        }
        PatientRelative other = (PatientRelative) object;
        if ((this.patientRelativePK == null && other.patientRelativePK != null) || (this.patientRelativePK != null && !this.patientRelativePK.equals(other.patientRelativePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.carloscortina.demo.model.PatientRelative[ patientRelativePK=" + patientRelativePK + " ]";
    }
    
}
