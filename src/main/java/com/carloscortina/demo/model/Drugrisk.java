/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.carloscortina.demo.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Carlos Cortina
 */
@Entity
@Table(name = "drugrisk")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Drugrisk.findAll", query = "SELECT d FROM Drugrisk d"),
    @NamedQuery(name = "Drugrisk.findByIdDrugMain", query = "SELECT d FROM Drugrisk d WHERE d.drugriskPK.idDrugMain = :idDrugMain"),
    @NamedQuery(name = "Drugrisk.findByIdDrugIncompatible", query = "SELECT d FROM Drugrisk d WHERE d.drugriskPK.idDrugIncompatible = :idDrugIncompatible")})
public class Drugrisk implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected DrugriskPK drugriskPK;
    @Size(max = 65535)
    @Column(name = "risk")
    private String risk;
    @JoinColumn(name = "idDrugIncompatible", referencedColumnName = "idDrug", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Drug drug;
    @JoinColumn(name = "idDrugMain", referencedColumnName = "idDrug", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Drug drug1;

    public Drugrisk() {
    }

    public Drugrisk(DrugriskPK drugriskPK) {
        this.drugriskPK = drugriskPK;
    }

    public Drugrisk(int idDrugMain, int idDrugIncompatible) {
        this.drugriskPK = new DrugriskPK(idDrugMain, idDrugIncompatible);
    }

    public Drugrisk(String risk, Drug drug, Drug drug1) {
        this.risk = risk;
        this.drug = drug;
        this.drug1 = drug1;
    }

    public DrugriskPK getDrugriskPK() {
        return drugriskPK;
    }

    public void setDrugriskPK(DrugriskPK drugriskPK) {
        this.drugriskPK = drugriskPK;
    }

    public String getRisk() {
        return risk;
    }

    public void setRisk(String risk) {
        this.risk = risk;
    }

    public Drug getDrug() {
        return drug;
    }

    public void setDrug(Drug drug) {
        this.drug = drug;
    }

    public Drug getDrug1() {
        return drug1;
    }

    public void setDrug1(Drug drug1) {
        this.drug1 = drug1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (drugriskPK != null ? drugriskPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Drugrisk)) {
            return false;
        }
        Drugrisk other = (Drugrisk) object;
        if ((this.drugriskPK == null && other.drugriskPK != null) || (this.drugriskPK != null && !this.drugriskPK.equals(other.drugriskPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.carloscortina.model.Drugrisk[ drugriskPK=" + drugriskPK + " ]";
    }
    
}
