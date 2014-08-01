package com.carloscortina.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="appointmentStatus")
public class AppointmentStatus implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idAppointmentStatus")
    private Integer idAppointmentStatus;

    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "status")
    private String status;
  
    @Size(max = 65535)
    @Column(name = "description")
    private String description;
    @OneToMany(mappedBy = "idStatus")
    @JsonIgnore
    private List<Appointment> appointmentCollection;

    public AppointmentStatus() {
    }

    public AppointmentStatus(Integer idAppointmentStatus) {
        this.idAppointmentStatus = idAppointmentStatus;
    }

    public AppointmentStatus(Integer idAppointmentStatus, String status) {
        this.idAppointmentStatus = idAppointmentStatus;
        this.status = status;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
	
}
