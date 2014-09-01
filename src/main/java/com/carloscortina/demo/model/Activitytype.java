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
@Table(name = "activitytype")
@NamedQueries({
    @NamedQuery(name = "Activitytype.findAll", query = "SELECT a FROM Activitytype a")})
public class Activitytype implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IdActivityType")
    private Integer idActivityType;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "type")
    private String type;
    @Basic(optional = false)
    @NotNull
    @Column(name = "active")
    private int active;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idActivityType")
    private List<Activity> activityList;

    public Activitytype() {
    }

    public Activitytype(Integer idActivityType) {
        this.idActivityType = idActivityType;
    }

    public Activitytype(Integer idActivityType, String type, int active) {
        this.idActivityType = idActivityType;
        this.type = type;
        this.active = active;
    }

    public Integer getIdActivityType() {
        return idActivityType;
    }

    public void setIdActivityType(Integer idActivityType) {
        this.idActivityType = idActivityType;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public List<Activity> getActivityList() {
        return activityList;
    }

    public void setActivityList(List<Activity> activityList) {
        this.activityList = activityList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idActivityType != null ? idActivityType.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Activitytype)) {
            return false;
        }
        Activitytype other = (Activitytype) object;
        if ((this.idActivityType == null && other.idActivityType != null) || (this.idActivityType != null && !this.idActivityType.equals(other.idActivityType))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.carloscortina.demo.model.Activitytype[ idActivityType=" + idActivityType + " ]";
    }
    
}
