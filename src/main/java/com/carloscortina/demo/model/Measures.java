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
@Table(name = "measures")
@NamedQueries({
    @NamedQuery(name = "Measures.findAll", query = "SELECT m FROM Measures m")})
public class Measures implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idMeasures")
    private Integer idMeasures;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "measure")
    private String measure;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "units")
    private String units;
    @Basic(optional = false)
    @NotNull
    @Column(name = "includePrescription")
    private int includePrescription;
    @Basic(optional = false)
    @NotNull
    @Column(name = "active")
    private int active;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "measures")
    private List<Consultationmeasure> consultationmeasureList;

    public Measures() {
    }

    public Measures(Integer idMeasures) {
        this.idMeasures = idMeasures;
    }

    public Measures(Integer idMeasures, String measure, String units, int includePrescription, int active) {
        this.idMeasures = idMeasures;
        this.measure = measure;
        this.units = units;
        this.includePrescription = includePrescription;
        this.active = active;
    }

    public Measures(String measure, String units, int includePrescription, int active) {
        this.measure = measure;
        this.units = units;
        this.includePrescription = includePrescription;
        this.active = active;
    }

    public Integer getIdMeasures() {
        return idMeasures;
    }

    public void setIdMeasures(Integer idMeasures) {
        this.idMeasures = idMeasures;
    }

    public String getMeasure() {
        return measure;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }

    public String getUnits() {
        return units;
    }

    public void setUnits(String units) {
        this.units = units;
    }

    public int getIncludePrescription() {
        return includePrescription;
    }

    public void setIncludePrescription(int includePrescription) {
        this.includePrescription = includePrescription;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public List<Consultationmeasure> getConsultationmeasureList() {
        return consultationmeasureList;
    }

    public void setConsultationmeasureList(List<Consultationmeasure> consultationmeasureList) {
        this.consultationmeasureList = consultationmeasureList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMeasures != null ? idMeasures.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Measures)) {
            return false;
        }
        Measures other = (Measures) object;
        if ((this.idMeasures == null && other.idMeasures != null) || (this.idMeasures != null && !this.idMeasures.equals(other.idMeasures))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.carloscortina.demo.model.Measures[ idMeasures=" + idMeasures + " ]";
    }
    
}
