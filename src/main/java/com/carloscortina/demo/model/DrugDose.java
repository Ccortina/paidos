/*
 * To change this template, choose Tools | Templates
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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ccortina_Mac
 */
@Entity
@Table(name = "DrugDose")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DrugDose.findAll", query = "SELECT d FROM DrugDose d"),
    @NamedQuery(name = "DrugDose.findByIdDrugDose", query = "SELECT d FROM DrugDose d WHERE d.idDrugDose = :idDrugDose"),
    @NamedQuery(name = "DrugDose.findByAge", query = "SELECT d FROM DrugDose d WHERE d.age = :age"),
    @NamedQuery(name = "DrugDose.findByDose", query = "SELECT d FROM DrugDose d WHERE d.dose = :dose")})
public class DrugDose implements Serializable {
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
    private float dose;
    @ManyToOne(optional = false)
    @JoinColumn(name="idDrug")
    @JsonIgnore
    private Drug idDrug;

    public DrugDose() {
    }

    public DrugDose(Integer idDrugDose) {
        this.idDrugDose = idDrugDose;
    }

    public DrugDose(Integer idDrugDose, int age, float dose) {
        this.idDrugDose = idDrugDose;
        this.age = age;
        this.dose = dose;
    }

    public DrugDose(int age, float dose, Drug idDrug) {
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

    public float getDose() {
        return dose;
    }
    
    public Drug getIdDrug() {
        return idDrug;
    }
    
    public void setIdDrug(Drug idDrug) {
        this.idDrug = idDrug;
    }

    public void setDose(float dose) {
        this.dose = dose;
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
        if (!(object instanceof DrugDose)) {
            return false;
        }
        DrugDose other = (DrugDose) object;
        if ((this.idDrugDose == null && other.idDrugDose != null) || (this.idDrugDose != null && !this.idDrugDose.equals(other.idDrugDose))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.carloscortina.demo.model.DrugDose[ idDrugDose=" + idDrugDose + " ]";
    }
    
}
