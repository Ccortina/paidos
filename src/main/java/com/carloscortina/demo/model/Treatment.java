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
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

/**
 *
 * @author Carlos Cortina
 */
@Entity
@Table(name = "treatment")
@NamedQueries({
    @NamedQuery(name = "Treatment.findAll", query = "SELECT t FROM Treatment t")})
public class Treatment implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IdTreatment")
    private Integer idTreatment;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 65535)
    @Column(name = "treatment")
    private String treatment;
    @Size(max = 65535)
    @Column(name = "directions")
    private String directions;
    @Basic(optional = false)
    @NotNull
    @Column(name = "active")
    private int active;
    @JsonIgnore
    @ManyToMany(mappedBy = "treatmentList")
    private List<Drug> drugList;
    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToMany(mappedBy = "treatmentList")
    private List<Cie10> cie10List;
    @JsonIgnore
    @OneToMany(mappedBy = "idTreatment")
    private List<Diagnostic> diagnosticList;

    public Treatment() {
    }

    public Treatment(Integer idTreatment) {
        this.idTreatment = idTreatment;
    }

    public Treatment(Integer idTreatment, String treatment, int active) {
        this.idTreatment = idTreatment;
        this.treatment = treatment;
        this.active = active;
    }

    public Integer getIdTreatment() {
        return idTreatment;
    }

    public void setIdTreatment(Integer idTreatment) {
        this.idTreatment = idTreatment;
    }

    public String getTreatment() {
        return treatment;
    }

    public void setTreatment(String treatment) {
        this.treatment = treatment;
    }

    public String getDirections() {
        return directions;
    }

    public void setDirections(String directions) {
        this.directions = directions;
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

    public List<Cie10> getCie10List() {
        return cie10List;
    }

    public void setCie10List(List<Cie10> cie10List) {
        this.cie10List = cie10List;
    }

    public List<Diagnostic> getDiagnosticList() {
        return diagnosticList;
    }

    public void setDiagnosticList(List<Diagnostic> diagnosticList) {
        this.diagnosticList = diagnosticList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTreatment != null ? idTreatment.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Treatment)) {
            return false;
        }
        Treatment other = (Treatment) object;
        if ((this.idTreatment == null && other.idTreatment != null) || (this.idTreatment != null && !this.idTreatment.equals(other.idTreatment))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.carloscortina.demo.model.Treatment[ idTreatment=" + idTreatment + " ]";
    }
    
}
