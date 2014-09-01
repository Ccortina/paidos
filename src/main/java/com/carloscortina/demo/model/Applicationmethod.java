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
@Table(name = "applicationmethod")
@NamedQueries({
    @NamedQuery(name = "Applicationmethod.findAll", query = "SELECT a FROM Applicationmethod a")})
public class Applicationmethod implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idApplicationMethod")
    private Integer idApplicationMethod;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "applicationMethod")
    private String applicationMethod;
    @Basic(optional = false)
    @NotNull
    @Column(name = "active")
    private int active;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "applicationMethodId")
    private List<Drug> drugList;

    public Applicationmethod() {
    }

    public Applicationmethod(Integer idApplicationMethod) {
        this.idApplicationMethod = idApplicationMethod;
    }

    public Applicationmethod(Integer idApplicationMethod, String applicationMethod, int active) {
        this.idApplicationMethod = idApplicationMethod;
        this.applicationMethod = applicationMethod;
        this.active = active;
    }

    public Applicationmethod(String applicationMethod, int active) {
        this.applicationMethod = applicationMethod;
        this.active = active;
    }

    public Integer getIdApplicationMethod() {
        return idApplicationMethod;
    }

    public void setIdApplicationMethod(Integer idApplicationMethod) {
        this.idApplicationMethod = idApplicationMethod;
    }

    public String getApplicationMethod() {
        return applicationMethod;
    }

    public void setApplicationMethod(String applicationMethod) {
        this.applicationMethod = applicationMethod;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public List<Drug> getDrugList() {
        return drugList;
    }

    public void setDrugList(List<Drug> drugList) {
        this.drugList = drugList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idApplicationMethod != null ? idApplicationMethod.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Applicationmethod)) {
            return false;
        }
        Applicationmethod other = (Applicationmethod) object;
        if ((this.idApplicationMethod == null && other.idApplicationMethod != null) || (this.idApplicationMethod != null && !this.idApplicationMethod.equals(other.idApplicationMethod))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.carloscortina.demo.model.Applicationmethod[ idApplicationMethod=" + idApplicationMethod + " ]";
    }
    
}
