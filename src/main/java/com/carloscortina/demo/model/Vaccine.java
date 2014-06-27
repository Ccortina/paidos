/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.carloscortina.demo.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.CascadeType;

/**
 *
 * @author Ccortina_Mac
 */
@Entity
@Table(name = "vaccine")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Vaccine.findAll", query = "SELECT v FROM Vaccine v"),
    @NamedQuery(name = "Vaccine.findByIdVaccine", query = "SELECT v FROM Vaccine v WHERE v.idVaccine = :idVaccine"),
    @NamedQuery(name = "Vaccine.findByVaccine", query = "SELECT v FROM Vaccine v WHERE v.vaccine = :vaccine"),
    @NamedQuery(name = "Vaccine.findByApplicationOrder", query = "SELECT v FROM Vaccine v WHERE v.applicationOrder = :applicationOrder"),
    @NamedQuery(name = "Vaccine.findByYearApply", query = "SELECT v FROM Vaccine v WHERE v.yearApply = :yearApply"),
    @NamedQuery(name = "Vaccine.findByMonthApply", query = "SELECT v FROM Vaccine v WHERE v.monthApply = :monthApply"),
    @NamedQuery(name = "Vaccine.findByDayApply", query = "SELECT v FROM Vaccine v WHERE v.dayApply = :dayApply"),
    @NamedQuery(name = "Vaccine.findByYearLimit", query = "SELECT v FROM Vaccine v WHERE v.yearLimit = :yearLimit"),
    @NamedQuery(name = "Vaccine.findByMonthLimit", query = "SELECT v FROM Vaccine v WHERE v.monthLimit = :monthLimit"),
    @NamedQuery(name = "Vaccine.findByDayLimit", query = "SELECT v FROM Vaccine v WHERE v.dayLimit = :dayLimit"),
    @NamedQuery(name = "Vaccine.findByMultipleShots", query = "SELECT v FROM Vaccine v WHERE v.multipleShots = :multipleShots"),
    @NamedQuery(name = "Vaccine.findByActive", query = "SELECT v FROM Vaccine v WHERE v.active = :active")})
public class Vaccine implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IdVaccine")
    private Integer idVaccine;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "vaccine")
    private String vaccine;
    @Basic(optional = false)
    @NotNull
    @Column(name = "applicationOrder")
    private int applicationOrder;
    @Basic(optional = false)
    @NotNull
    @Column(name = "yearApply")
    private int yearApply;
    @Basic(optional = false)
    @NotNull
    @Column(name = "monthApply")
    private int monthApply;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dayApply")
    private int dayApply;
    @Basic(optional = false)
    @NotNull
    @Column(name = "yearLimit")
    private int yearLimit;
    @Basic(optional = false)
    @NotNull
    @Column(name = "monthLimit")
    private int monthLimit;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dayLimit")
    private int dayLimit;
    @Basic(optional = false)
    @NotNull
    @Column(name = "multipleShots")
    private int multipleShots;
    @Basic(optional = false)
    @NotNull
    @Column(name = "active")
    private int active;
    @JoinTable(name = "equivalentVaccine", joinColumns = {
        @JoinColumn(name = "IdEquivalentVaccine", referencedColumnName = "IdVaccine")}, inverseJoinColumns = {
        @JoinColumn(name = "IdVaccine", referencedColumnName = "IdVaccine")})
    @ManyToMany
    @JsonIgnore
    private List<Vaccine> vaccineList;
    @ManyToMany(mappedBy = "vaccineList")
    @JsonIgnore
    private List<Vaccine> vaccineList1;
    @OneToMany(mappedBy = "idVaccine")
    private List<Activity> activityList;
    @JoinColumn(name = "IdVaccineType", referencedColumnName = "IdvaccineType")
    @ManyToOne(optional = false)
    private VaccineType idVaccineType;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "vaccine")
    @JsonIgnore
    private List<PatientVaccine> patientVaccineList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "vaccine")
    @JsonIgnore
    private List<AppointmentVaccine> appointmentVaccineList;

    public Vaccine() {
    }

    public Vaccine(Integer idVaccine) {
        this.idVaccine = idVaccine;
    }

    public Vaccine(Integer idVaccine, String vaccine, int applicationOrder, int yearApply, int monthApply, int dayApply, int yearLimit, int monthLimit, int dayLimit, int multipleShots, int active) {
        this.idVaccine = idVaccine;
        this.vaccine = vaccine;
        this.applicationOrder = applicationOrder;
        this.yearApply = yearApply;
        this.monthApply = monthApply;
        this.dayApply = dayApply;
        this.yearLimit = yearLimit;
        this.monthLimit = monthLimit;
        this.dayLimit = dayLimit;
        this.multipleShots = multipleShots;
        this.active = active;
    }

    public Integer getIdVaccine() {
        return idVaccine;
    }

    public void setIdVaccine(Integer idVaccine) {
        this.idVaccine = idVaccine;
    }

    public String getVaccine() {
        return vaccine;
    }

    public void setVaccine(String vaccine) {
        this.vaccine = vaccine;
    }

    public int getApplicationOrder() {
        return applicationOrder;
    }

    public void setApplicationOrder(int applicationOrder) {
        this.applicationOrder = applicationOrder;
    }

    public int getYearApply() {
        return yearApply;
    }

    public void setYearApply(int yearApply) {
        this.yearApply = yearApply;
    }

    public int getMonthApply() {
        return monthApply;
    }

    public void setMonthApply(int monthApply) {
        this.monthApply = monthApply;
    }

    public int getDayApply() {
        return dayApply;
    }

    public void setDayApply(int dayApply) {
        this.dayApply = dayApply;
    }

    public int getYearLimit() {
        return yearLimit;
    }

    public void setYearLimit(int yearLimit) {
        this.yearLimit = yearLimit;
    }

    public int getMonthLimit() {
        return monthLimit;
    }

    public void setMonthLimit(int monthLimit) {
        this.monthLimit = monthLimit;
    }

    public int getDayLimit() {
        return dayLimit;
    }

    public void setDayLimit(int dayLimit) {
        this.dayLimit = dayLimit;
    }

    public int getMultipleShots() {
        return multipleShots;
    }

    public void setMultipleShots(int multipleShots) {
        this.multipleShots = multipleShots;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    @XmlTransient
    @JsonIgnore
    public List<Vaccine> getVaccineList() {
        return vaccineList;
    }

    public void setVaccineList(List<Vaccine> vaccineList) {
        this.vaccineList = vaccineList;
    }

    @XmlTransient
    @JsonIgnore
    public List<Vaccine> getVaccineList1() {
        return vaccineList1;
    }

    public void setVaccineList1(List<Vaccine> vaccineList1) {
        this.vaccineList1 = vaccineList1;
    }

    @XmlTransient
    @JsonIgnore
    public List<Activity> getActivityList() {
        return activityList;
    }

    public void setActivityList(List<Activity> activityList) {
        this.activityList = activityList;
    }

    public VaccineType getIdVaccineType() {
        return idVaccineType;
    }

    public void setIdVaccineType(VaccineType idVaccineType) {
        this.idVaccineType = idVaccineType;
    }

    public List<PatientVaccine> getPatientVaccineList() {
        return patientVaccineList;
    }

    public void setPatientVaccineList(List<PatientVaccine> patientVaccineList) {
        this.patientVaccineList = patientVaccineList;
    }

    public List<AppointmentVaccine> getAppointmentVaccineList() {
        return appointmentVaccineList;
    }

    public void setAppointmentVaccineList(List<AppointmentVaccine> appointmentVaccineList) {
        this.appointmentVaccineList = appointmentVaccineList;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idVaccine != null ? idVaccine.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Vaccine)) {
            return false;
        }
        Vaccine other = (Vaccine) object;
        if ((this.idVaccine == null && other.idVaccine != null) || (this.idVaccine != null && !this.idVaccine.equals(other.idVaccine))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.carloscortina.demo.model.Vaccine[ idVaccine=" + idVaccine + " ]";
    }
    
}
