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
public class AppointmentVaccinePK implements Serializable {
    @Basic(optional = false)
    @Column(name = "idAppointment")
    private int idAppointment;
    @Basic(optional = false)
    @Column(name = "idVaccine")
    private int idVaccine;
    @Basic(optional = false)
    @Column(name = "idPatient")
    private int idPatient;

    public AppointmentVaccinePK() {
    }

    public AppointmentVaccinePK(int idAppointment, int idVaccine, int idPatient) {
        this.idAppointment = idAppointment;
        this.idVaccine = idVaccine;
        this.idPatient = idPatient;
    }

    public int getIdAppointment() {
        return idAppointment;
    }

    public void setIdAppointment(int idAppointment) {
        this.idAppointment = idAppointment;
    }

    public int getIdVaccine() {
        return idVaccine;
    }

    public void setIdVaccine(int idVaccine) {
        this.idVaccine = idVaccine;
    }

    public int getIdPatient() {
        return idPatient;
    }

    public void setIdPatient(int idPatient) {
        this.idPatient = idPatient;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idAppointment;
        hash += (int) idVaccine;
        hash += (int) idPatient;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AppointmentVaccinePK)) {
            return false;
        }
        AppointmentVaccinePK other = (AppointmentVaccinePK) object;
        if (this.idAppointment != other.idAppointment) {
            return false;
        }
        if (this.idVaccine != other.idVaccine) {
            return false;
        }
        if (this.idPatient != other.idPatient) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pruebas1.AppointmentVaccinePK[ idAppointment=" + idAppointment + ", idVaccine=" + idVaccine + ", idPatient=" + idPatient + " ]";
    }
    
}
