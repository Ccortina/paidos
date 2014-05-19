/*
 * To change this template, choose Tools | Templates
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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Ccortina_Mac
 */
@Entity
@Table(name = "activityType")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ActivityType.findAll", query = "SELECT a FROM ActivityType a"),
    @NamedQuery(name = "ActivityType.findByIdActivityType", query = "SELECT a FROM ActivityType a WHERE a.idActivityType = :idActivityType"),
    @NamedQuery(name = "ActivityType.findByType", query = "SELECT a FROM ActivityType a WHERE a.type = :type")})
public class ActivityType implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IdActivityType")
    private Integer idActivityType;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "type")
    private String type;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idActivityType")
    @JsonIgnore
    private List<Activity> activityList;

    public ActivityType() {
    }

    public ActivityType(Integer idActivityType) {
        this.idActivityType = idActivityType;
    }

    public ActivityType(Integer idActivityType, String type) {
        this.idActivityType = idActivityType;
        this.type = type;
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

    @XmlTransient
    @JsonIgnore
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
        if (!(object instanceof ActivityType)) {
            return false;
        }
        ActivityType other = (ActivityType) object;
        if ((this.idActivityType == null && other.idActivityType != null) || (this.idActivityType != null && !this.idActivityType.equals(other.idActivityType))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.carloscortina.demo.model.ActivityType[ idActivityType=" + idActivityType + " ]";
    }
    
}
