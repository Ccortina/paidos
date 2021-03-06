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
public class Cie10doctorPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "idCIE10")
    private int idCIE10;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idUser")
    private int idUser;

    public Cie10doctorPK() {
    }

    public Cie10doctorPK(int idCIE10, int idUser) {
        this.idCIE10 = idCIE10;
        this.idUser = idUser;
    }

    public int getIdCIE10() {
        return idCIE10;
    }

    public void setIdCIE10(int idCIE10) {
        this.idCIE10 = idCIE10;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idCIE10;
        hash += (int) idUser;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cie10doctorPK)) {
            return false;
        }
        Cie10doctorPK other = (Cie10doctorPK) object;
        if (this.idCIE10 != other.idCIE10) {
            return false;
        }
        if (this.idUser != other.idUser) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.carloscortina.demo.model.Cie10doctorPK[ idCIE10=" + idCIE10 + ", idUser=" + idUser + " ]";
    }
    
}
