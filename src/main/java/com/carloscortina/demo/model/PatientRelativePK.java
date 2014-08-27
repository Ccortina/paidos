/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.carloscortina.demo.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Carlos Cortina
 */
@Embeddable
public class PatientRelativePK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "idPatient")
    private int idPatient;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idRelative")
    private int idRelative;

    public PatientRelativePK() {
    }

    public PatientRelativePK(int idPatient, int idRelative) {
        this.idPatient = idPatient;
        this.idRelative = idRelative;
    }

    public int getIdPatient() {
        return idPatient;
    }

    public void setIdPatient(int idPatient) {
        this.idPatient = idPatient;
    }

    public int getIdRelative() {
        return idRelative;
    }

    public void setIdRelative(int idRelative) {
        this.idRelative = idRelative;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idPatient;
        hash += (int) idRelative;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PatientRelativePK)) {
            return false;
        }
        PatientRelativePK other = (PatientRelativePK) object;
        if (this.idPatient != other.idPatient) {
            return false;
        }
        if (this.idRelative != other.idRelative) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.carloscortina.demo.model.PatientRelativePK[ idPatient=" + idPatient + ", idRelative=" + idRelative + " ]";
    }
    
}
