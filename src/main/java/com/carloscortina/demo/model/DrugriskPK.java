/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.carloscortina.demo.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Carlos Cortina
 */
@Embeddable
public class DrugriskPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "idDrugMain")
    private int idDrugMain;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idDrugIncompatible")
    private int idDrugIncompatible;

    public DrugriskPK() {
    }

    public DrugriskPK(int idDrugMain, int idDrugIncompatible) {
        this.idDrugMain = idDrugMain;
        this.idDrugIncompatible = idDrugIncompatible;
    }

    public int getIdDrugMain() {
        return idDrugMain;
    }

    public void setIdDrugMain(int idDrugMain) {
        this.idDrugMain = idDrugMain;
    }

    public int getIdDrugIncompatible() {
        return idDrugIncompatible;
    }

    public void setIdDrugIncompatible(int idDrugIncompatible) {
        this.idDrugIncompatible = idDrugIncompatible;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idDrugMain;
        hash += (int) idDrugIncompatible;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DrugriskPK)) {
            return false;
        }
        DrugriskPK other = (DrugriskPK) object;
        if (this.idDrugMain != other.idDrugMain) {
            return false;
        }
        if (this.idDrugIncompatible != other.idDrugIncompatible) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.carloscortina.model.DrugriskPK[ idDrugMain=" + idDrugMain + ", idDrugIncompatible=" + idDrugIncompatible + " ]";
    }
    
}
