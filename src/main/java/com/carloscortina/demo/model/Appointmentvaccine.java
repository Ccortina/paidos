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
@Table(name = "appointmentvaccine")
@NamedQueries({
    @NamedQuery(name = "Appointmentvaccine.findAll", query = "SELECT a FROM Appointmentvaccine a")})
public class Appointmentvaccine implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected AppointmentvaccinePK appointmentvaccinePK;
    @JoinColumn(name = "idVaccine", referencedColumnName = "IdVaccine", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Vaccine vaccine;
    @JoinColumn(name = "idPatient", referencedColumnName = "idPatient", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Patient patient;
    @JoinColumn(name = "idAppointment", referencedColumnName = "idAppointment", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Appointment appointment;

    public Appointmentvaccine() {
    }

    public Appointmentvaccine(AppointmentvaccinePK appointmentvaccinePK) {
        this.appointmentvaccinePK = appointmentvaccinePK;
    }

    public Appointmentvaccine(int idAppointment, int idVaccine, int idPatient) {
        this.appointmentvaccinePK = new AppointmentvaccinePK(idAppointment, idVaccine, idPatient);
    }

    public AppointmentvaccinePK getAppointmentvaccinePK() {
        return appointmentvaccinePK;
    }

    public void setAppointmentvaccinePK(AppointmentvaccinePK appointmentvaccinePK) {
        this.appointmentvaccinePK = appointmentvaccinePK;
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

    public Appointment getAppointment() {
        return appointment;
    }

    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (appointmentvaccinePK != null ? appointmentvaccinePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Appointmentvaccine)) {
            return false;
        }
        Appointmentvaccine other = (Appointmentvaccine) object;
        if ((this.appointmentvaccinePK == null && other.appointmentvaccinePK != null) || (this.appointmentvaccinePK != null && !this.appointmentvaccinePK.equals(other.appointmentvaccinePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.carloscortina.demo.model.Appointmentvaccine[ appointmentvaccinePK=" + appointmentvaccinePK + " ]";
    }
    
}
