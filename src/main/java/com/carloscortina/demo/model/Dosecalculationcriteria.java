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
@Table(name = "dosecalculationcriteria")
@NamedQueries({
    @NamedQuery(name = "Dosecalculationcriteria.findAll", query = "SELECT d FROM Dosecalculationcriteria d")})
public class Dosecalculationcriteria implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idDoseCalculationCriteria")
    private Integer idDoseCalculationCriteria;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "criteria")
    private String criteria;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "doseCalculationCriteriaId")
    private List<Drug> drugList;

    public Dosecalculationcriteria() {
    }

    public Dosecalculationcriteria(Integer idDoseCalculationCriteria) {
        this.idDoseCalculationCriteria = idDoseCalculationCriteria;
    }

    public Dosecalculationcriteria(Integer idDoseCalculationCriteria, String criteria) {
        this.idDoseCalculationCriteria = idDoseCalculationCriteria;
        this.criteria = criteria;
    }

    public Integer getIdDoseCalculationCriteria() {
        return idDoseCalculationCriteria;
    }

    public void setIdDoseCalculationCriteria(Integer idDoseCalculationCriteria) {
        this.idDoseCalculationCriteria = idDoseCalculationCriteria;
    }

    public String getCriteria() {
        return criteria;
    }

    public void setCriteria(String criteria) {
        this.criteria = criteria;
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
        hash += (idDoseCalculationCriteria != null ? idDoseCalculationCriteria.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Dosecalculationcriteria)) {
            return false;
        }
        Dosecalculationcriteria other = (Dosecalculationcriteria) object;
        if ((this.idDoseCalculationCriteria == null && other.idDoseCalculationCriteria != null) || (this.idDoseCalculationCriteria != null && !this.idDoseCalculationCriteria.equals(other.idDoseCalculationCriteria))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.carloscortina.demo.model.Dosecalculationcriteria[ idDoseCalculationCriteria=" + idDoseCalculationCriteria + " ]";
    }
    
}
