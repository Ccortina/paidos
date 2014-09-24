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
@Table(name = "commercialname")
@NamedQueries({
    @NamedQuery(name = "Commercialname.findAll", query = "SELECT c FROM Commercialname c")})
public class Commercialname implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "active")
    private int active;
    @Column(name = "tempClaveNombreComercial")
    private Integer tempClaveNombreComercial;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idcommercialName")
    private Integer idcommercialName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "commercialName")
    private String commercialName;
    @JsonIgnore
    @OneToMany(mappedBy = "idCommercialName")
    private List<Diagnostic> diagnosticList;
    @JoinColumn(name = "drugId", referencedColumnName = "idDrug")
    @ManyToOne(optional = false)
    private Drug drugId;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "commercialname")
    private List<Incompatibledrugs> incompatibledrugsList;

    public Commercialname() {
    }

    public Commercialname(Integer idcommercialName) {
        this.idcommercialName = idcommercialName;
    }

    public Commercialname(Integer idcommercialName, String commercialName, Drug drugId) {
        this.idcommercialName = idcommercialName;
        this.commercialName = commercialName;
        this.drugId = drugId;
    }

    public Commercialname(Integer idcommercialName, String commercialName) {
        this.idcommercialName = idcommercialName;
        this.commercialName = commercialName;
    }

    public Commercialname(String commercialName, Drug drugId) {
        this.commercialName = commercialName;
        this.drugId = drugId;
    }

    public Integer getIdcommercialName() {
        return idcommercialName;
    }

    public void setIdcommercialName(Integer idcommercialName) {
        this.idcommercialName = idcommercialName;
    }

    public String getCommercialName() {
        return commercialName;
    }

    public void setCommercialName(String commercialName) {
        this.commercialName = commercialName;
    }

    public List<Diagnostic> getDiagnosticList() {
        return diagnosticList;
    }

    public void setDiagnosticList(List<Diagnostic> diagnosticList) {
        this.diagnosticList = diagnosticList;
    }

    public Drug getDrugId() {
        return drugId;
    }

    public void setDrugId(Drug drugId) {
        this.drugId = drugId;
    }

    public List<Incompatibledrugs> getIncompatibledrugsList() {
        return incompatibledrugsList;
    }

    public void setIncompatibledrugsList(List<Incompatibledrugs> incompatibledrugsList) {
        this.incompatibledrugsList = incompatibledrugsList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcommercialName != null ? idcommercialName.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Commercialname)) {
            return false;
        }
        Commercialname other = (Commercialname) object;
        if ((this.idcommercialName == null && other.idcommercialName != null) || (this.idcommercialName != null && !this.idcommercialName.equals(other.idcommercialName))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.carloscortina.demo.model.Commercialname[ idcommercialName=" + idcommercialName + " ]";
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public Integer getTempClaveNombreComercial() {
        return tempClaveNombreComercial;
    }

    public void setTempClaveNombreComercial(Integer tempClaveNombreComercial) {
        this.tempClaveNombreComercial = tempClaveNombreComercial;
    }
    
}
