/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.carloscortina.demo.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Carlos Cortina
 */
@Entity
@Table(name = "cie10doctor")
@NamedQueries({
    @NamedQuery(name = "Cie10doctor.findAll", query = "SELECT c FROM Cie10doctor c")})
public class Cie10doctor implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected Cie10doctorPK cie10doctorPK;
    @Basic(optional = false)
    @Column(name = "lastUsed")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUsed;
    @JoinColumn(name = "idCIE10", referencedColumnName = "idCIE10", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Cie10 cie10;
    @JoinColumn(name = "idUser", referencedColumnName = "idUser", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private User user;

    public Cie10doctor() {
    }

    public Cie10doctor(Cie10doctorPK cie10doctorPK) {
        this.cie10doctorPK = cie10doctorPK;
    }

    public Cie10doctor(Cie10doctorPK cie10doctorPK, Date lastUsed) {
        this.cie10doctorPK = cie10doctorPK;
        this.lastUsed = lastUsed;
    }

    public Cie10doctor(int idCIE10, int idUser) {
        this.cie10doctorPK = new Cie10doctorPK(idCIE10, idUser);
    }

    public Cie10doctor(Date lastUsed, Cie10 cie10) {
        this.lastUsed = lastUsed;
        this.cie10 = cie10;
    }

    public Cie10doctorPK getCie10doctorPK() {
        return cie10doctorPK;
    }

    public void setCie10doctorPK(Cie10doctorPK cie10doctorPK) {
        this.cie10doctorPK = cie10doctorPK;
    }

    public Date getLastUsed() {
        return lastUsed;
    }

    public void setLastUsed(Date lastUsed) {
        this.lastUsed = lastUsed;
    }

    public Cie10 getCie10() {
        return cie10;
    }

    public void setCie10(Cie10 cie10) {
        this.cie10 = cie10;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cie10doctorPK != null ? cie10doctorPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cie10doctor)) {
            return false;
        }
        Cie10doctor other = (Cie10doctor) object;
        if ((this.cie10doctorPK == null && other.cie10doctorPK != null) || (this.cie10doctorPK != null && !this.cie10doctorPK.equals(other.cie10doctorPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.carloscortina.demo.model.Cie10doctor[ cie10doctorPK=" + cie10doctorPK + " ]";
    }
    
}
