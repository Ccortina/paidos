/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.carloscortina.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Carlos Cortina
 */
@Entity
@Table(name = "appointmentstatus")
@NamedQueries({
    @NamedQuery(name = "Appointmentstatus.findAll", query = "SELECT a FROM Appointmentstatus a")})
public class Appointmentstatus implements Serializable {
    @Column(name = "tempClaveEstatus")
    private Integer tempClaveEstatus;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idAppointmentStatus")
    private Integer idAppointmentStatus;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "status")
    private String status;
    @Basic(optional = false)
    @NotNull
    @Column(name = "active")
    private int active;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idStatus")
    private List<Appointment> appointmentList;

    public Appointmentstatus() {
    }

    public Appointmentstatus(Integer idAppointmentStatus) {
        this.idAppointmentStatus = idAppointmentStatus;
    }

    public Appointmentstatus(Integer idAppointmentStatus, String status, int active) {
        this.idAppointmentStatus = idAppointmentStatus;
        this.status = status;
        this.active = active;
    }

    public Appointmentstatus(String status, int active) {
        this.status = status;
        this.active = active;
    }

    public Integer getIdAppointmentStatus() {
        return idAppointmentStatus;
    }

    public void setIdAppointmentStatus(Integer idAppointmentStatus) {
        this.idAppointmentStatus = idAppointmentStatus;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public List<Appointment> getAppointmentList() {
        return appointmentList;
    }

    public void setAppointmentList(List<Appointment> appointmentList) {
        this.appointmentList = appointmentList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAppointmentStatus != null ? idAppointmentStatus.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Appointmentstatus)) {
            return false;
        }
        Appointmentstatus other = (Appointmentstatus) object;
        if ((this.idAppointmentStatus == null && other.idAppointmentStatus != null) || (this.idAppointmentStatus != null && !this.idAppointmentStatus.equals(other.idAppointmentStatus))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.carloscortina.demo.model.Appointmentstatus[ idAppointmentStatus=" + idAppointmentStatus + " ]";
    }

    public Integer getTempClaveEstatus() {
        return tempClaveEstatus;
    }

    public void setTempClaveEstatus(Integer tempClaveEstatus) {
        this.tempClaveEstatus = tempClaveEstatus;
    }
    
}
