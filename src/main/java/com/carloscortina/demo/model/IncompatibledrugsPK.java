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
public class IncompatibledrugsPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "idDrug")
    private int idDrug;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idIncompatibleDrug")
    private int idIncompatibleDrug;

    public IncompatibledrugsPK() {
    }

    public IncompatibledrugsPK(int idDrug, int idIncompatibleDrug) {
        this.idDrug = idDrug;
        this.idIncompatibleDrug = idIncompatibleDrug;
    }

    public int getIdDrug() {
        return idDrug;
    }

    public void setIdDrug(int idDrug) {
        this.idDrug = idDrug;
    }

    public int getIdIncompatibleDrug() {
        return idIncompatibleDrug;
    }

    public void setIdIncompatibleDrug(int idIncompatibleDrug) {
        this.idIncompatibleDrug = idIncompatibleDrug;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idDrug;
        hash += (int) idIncompatibleDrug;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof IncompatibledrugsPK)) {
            return false;
        }
        IncompatibledrugsPK other = (IncompatibledrugsPK) object;
        if (this.idDrug != other.idDrug) {
            return false;
        }
        if (this.idIncompatibleDrug != other.idIncompatibleDrug) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.carloscortina.demo.model.IncompatibledrugsPK[ idDrug=" + idDrug + ", idIncompatibleDrug=" + idIncompatibleDrug + " ]";
    }
    
}
