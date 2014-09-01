/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.carloscortina.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Carlos Cortina
 */
@Entity
@Table(name = "drugdose")
@NamedQueries({
    @NamedQuery(name = "Drugdose.findAll", query = "SELECT d FROM Drugdose d")})
public class Drugdose implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idDrugDose")
    private Integer idDrugDose;
    @Basic(optional = false)
    @NotNull
    @Column(name = "age")
    private int age;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dose")
    private double dose;
    @JsonIgnore
    @JoinColumn(name = "idDrug", referencedColumnName = "idDrug")
    @ManyToOne(optional = false)
    private Drug idDrug;

    public Drugdose() {
    }

    public Drugdose(Integer idDrugDose) {
        this.idDrugDose = idDrugDose;
    }

    public Drugdose(Integer idDrugDose, int age, double dose) {
        this.idDrugDose = idDrugDose;
        this.age = age;
        this.dose = dose;
    }

    public Drugdose(int age, double dose, Drug idDrug) {
        this.age = age;
        this.dose = dose;
        this.idDrug = idDrug;
    }

    public Integer getIdDrugDose() {
        return idDrugDose;
    }

    public void setIdDrugDose(Integer idDrugDose) {
        this.idDrugDose = idDrugDose;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getDose() {
        return dose;
    }

    public void setDose(double dose) {
        this.dose = dose;
    }

    public Drug getIdDrug() {
        return idDrug;
    }

    public void setIdDrug(Drug idDrug) {
        this.idDrug = idDrug;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDrugDose != null ? idDrugDose.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Drugdose)) {
            return false;
        }
        Drugdose other = (Drugdose) object;
        if ((this.idDrugDose == null && other.idDrugDose != null) || (this.idDrugDose != null && !this.idDrugDose.equals(other.idDrugDose))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.carloscortina.demo.model.Drugdose[ idDrugDose=" + idDrugDose + " ]";
    }
    
}
