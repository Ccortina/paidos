/*
 * To change this template, choose Tools | Templates
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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ccortina_Mac
 */
@Entity
@Table(name = "AppointmentVaccine")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AppointmentVaccine.findAll", query = "SELECT a FROM AppointmentVaccine a"),
    @NamedQuery(name = "AppointmentVaccine.findByIdAppointment", query = "SELECT a FROM AppointmentVaccine a WHERE a.appointmentVaccinePK.idAppointment = :idAppointment"),
    @NamedQuery(name = "AppointmentVaccine.findByIdVaccine", query = "SELECT a FROM AppointmentVaccine a WHERE a.appointmentVaccinePK.idVaccine = :idVaccine"),
    @NamedQuery(name = "AppointmentVaccine.findByIdPatient", query = "SELECT a FROM AppointmentVaccine a WHERE a.appointmentVaccinePK.idPatient = :idPatient")})
public class AppointmentVaccine implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected AppointmentVaccinePK appointmentVaccinePK;
    @JoinColumn(name = "idPatient", referencedColumnName = "idPatient", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Patient patient;
    @JoinColumn(name = "idVaccine", referencedColumnName = "IdVaccine", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Vaccine vaccine;
    @JoinColumn(name = "idAppointment", referencedColumnName = "idAppointment", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Appointment appointment;

    public AppointmentVaccine() {
    }

    public AppointmentVaccine(AppointmentVaccinePK appointmentVaccinePK) {
        this.appointmentVaccinePK = appointmentVaccinePK;
    }

    public AppointmentVaccine(int idAppointment, int idVaccine, int idPatient) {
        this.appointmentVaccinePK = new AppointmentVaccinePK(idAppointment, idVaccine, idPatient);
    }

    public AppointmentVaccinePK getAppointmentVaccinePK() {
        return appointmentVaccinePK;
    }

    public void setAppointmentVaccinePK(AppointmentVaccinePK appointmentVaccinePK) {
        this.appointmentVaccinePK = appointmentVaccinePK;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Vaccine getVaccine() {
        return vaccine;
    }

    public void setVaccine(Vaccine vaccine) {
        this.vaccine = vaccine;
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
        hash += (appointmentVaccinePK != null ? appointmentVaccinePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AppointmentVaccine)) {
            return false;
        }
        AppointmentVaccine other = (AppointmentVaccine) object;
        if ((this.appointmentVaccinePK == null && other.appointmentVaccinePK != null) || (this.appointmentVaccinePK != null && !this.appointmentVaccinePK.equals(other.appointmentVaccinePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pruebas1.AppointmentVaccine[ appointmentVaccinePK=" + appointmentVaccinePK + " ]";
    }
    
}
