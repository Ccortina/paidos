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
@Table(name = "administrationunit")
@NamedQueries({
    @NamedQuery(name = "Administrationunit.findAll", query = "SELECT a FROM Administrationunit a")})
public class Administrationunit implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idAdministrationUnit")
    private Integer idAdministrationUnit;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "administrationUnit")
    private String administrationUnit;
    @Basic(optional = false)
    @NotNull
    @Column(name = "active")
    private int active;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "administrationUnitId")
    private List<Drug> drugList;

    public Administrationunit() {
    }

    public Administrationunit(Integer idAdministrationUnit) {
        this.idAdministrationUnit = idAdministrationUnit;
    }

    public Administrationunit(Integer idAdministrationUnit, String administrationUnit, int active) {
        this.idAdministrationUnit = idAdministrationUnit;
        this.administrationUnit = administrationUnit;
        this.active = active;
    }

    public Administrationunit(String administrationUnit, int active) {
        this.administrationUnit = administrationUnit;
        this.active = active;
    }

    public Integer getIdAdministrationUnit() {
        return idAdministrationUnit;
    }

    public void setIdAdministrationUnit(Integer idAdministrationUnit) {
        this.idAdministrationUnit = idAdministrationUnit;
    }

    public String getAdministrationUnit() {
        return administrationUnit;
    }

    public void setAdministrationUnit(String administrationUnit) {
        this.administrationUnit = administrationUnit;
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
        hash += (idAdministrationUnit != null ? idAdministrationUnit.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Administrationunit)) {
            return false;
        }
        Administrationunit other = (Administrationunit) object;
        if ((this.idAdministrationUnit == null && other.idAdministrationUnit != null) || (this.idAdministrationUnit != null && !this.idAdministrationUnit.equals(other.idAdministrationUnit))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.carloscortina.demo.model.Administrationunit[ idAdministrationUnit=" + idAdministrationUnit + " ]";
    }
    
}
