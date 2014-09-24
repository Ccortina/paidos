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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "activity")
@NamedQueries({
    @NamedQuery(name = "Activity.findAll", query = "SELECT a FROM Activity a")})
public class Activity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IdActivity")
    private Integer idActivity;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "activity")
    private String activity;
    @Basic(optional = false)
    @NotNull
    @Column(name = "activityCost")
    private double activityCost;
    @Basic(optional = false)
    @NotNull
    @Column(name = "consultationDefault")
    private int consultationDefault;
    @Basic(optional = false)
    @NotNull
    @Column(name = "active")
    private int active;
    @JoinColumn(name = "IdVaccine", referencedColumnName = "IdVaccine")
    @ManyToOne
    private Vaccine idVaccine;
    @JoinColumn(name = "IdActivityType", referencedColumnName = "IdActivityType")
    @ManyToOne(optional = false)
    private Activitytype idActivityType;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "activity")
    private List<Consultationactivity> consultationactivityList;

    public Activity() {
    }

    public Activity(Integer idActivity) {
        this.idActivity = idActivity;
    }

    public Activity(Integer idActivity, String activity, double activityCost, int consultationDefault, int active) {
        this.idActivity = idActivity;
        this.activity = activity;
        this.activityCost = activityCost;
        this.consultationDefault = consultationDefault;
        this.active = active;
    }

    public Activity(Integer idActivity, String activity, double activityCost, int consultationDefault, int active, Activitytype idActivityType) {
        this.idActivity = idActivity;
        this.activity = activity;
        this.activityCost = activityCost;
        this.consultationDefault = consultationDefault;
        this.active = active;
        this.idActivityType = idActivityType;
    }

    public Activity(String activity, double activityCost, int consultationDefault, int active, Vaccine idVaccine, Activitytype idActivityType) {
        this.activity = activity;
        this.activityCost = activityCost;
        this.consultationDefault = consultationDefault;
        this.active = active;
        this.idVaccine = idVaccine;
        this.idActivityType = idActivityType;
    }

    public Integer getIdActivity() {
        return idActivity;
    }

    public void setIdActivity(Integer idActivity) {
        this.idActivity = idActivity;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public double getActivityCost() {
        return activityCost;
    }

    public void setActivityCost(double activityCost) {
        this.activityCost = activityCost;
    }

    public int getConsultationDefault() {
        return consultationDefault;
    }

    public void setConsultationDefault(int consultationDefault) {
        this.consultationDefault = consultationDefault;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public Vaccine getIdVaccine() {
        return idVaccine;
    }

    public void setIdVaccine(Vaccine idVaccine) {
        this.idVaccine = idVaccine;
    }

    public Activitytype getIdActivityType() {
        return idActivityType;
    }

    public void setIdActivityType(Activitytype idActivityType) {
        this.idActivityType = idActivityType;
    }

    public List<Consultationactivity> getConsultationactivityList() {
        return consultationactivityList;
    }

    public void setConsultationactivityList(List<Consultationactivity> consultationactivityList) {
        this.consultationactivityList = consultationactivityList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idActivity != null ? idActivity.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Activity)) {
            return false;
        }
        Activity other = (Activity) object;
        if ((this.idActivity == null && other.idActivity != null) || (this.idActivity != null && !this.idActivity.equals(other.idActivity))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.carloscortina.demo.model.Activity[ idActivity=" + idActivity + " ]";
    }
    
}
