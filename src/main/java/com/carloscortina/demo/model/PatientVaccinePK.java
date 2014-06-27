/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.carloscortina.demo.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Ccortina_Mac
 */
@Embeddable
public class PatientVaccinePK implements Serializable {
    @Basic(optional = false)
    @Column(name = "idPatient")
    private int idPatient;
    @Basic(optional = false)
    @Column(name = "idVaccine")
    private int idVaccine;

    public PatientVaccinePK() {
    }

    public PatientVaccinePK(int idPatient, int idVaccine) {
        this.idPatient = idPatient;
        this.idVaccine = idVaccine;
    }

    public int getIdPatient() {
        return idPatient;
    }

    public void setIdPatient(int idPatient) {
        this.idPatient = idPatient;
    }

    public int getIdVaccine() {
        return idVaccine;
    }

    public void setIdVaccine(int idVaccine) {
        this.idVaccine = idVaccine;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idPatient;
        hash += (int) idVaccine;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PatientVaccinePK)) {
            return false;
        }
        PatientVaccinePK other = (PatientVaccinePK) object;
        if (this.idPatient != other.idPatient) {
            return false;
        }
        if (this.idVaccine != other.idVaccine) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pruebas1.PatientVaccinePK[ idPatient=" + idPatient + ", idVaccine=" + idVaccine + " ]";
    }
    
}
