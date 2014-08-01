/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.carloscortina.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ccortina_Mac
 */
@Entity
@Table(name = "CIE10Doctor")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CIE10Doctor.findAll", query = "SELECT c FROM CIE10Doctor c"),
    @NamedQuery(name = "CIE10Doctor.findByIdCIE10", query = "SELECT c FROM CIE10Doctor c WHERE c.cIE10DoctorPK.idCIE10 = :idCIE10"),
    @NamedQuery(name = "CIE10Doctor.findByIdUser", query = "SELECT c FROM CIE10Doctor c WHERE c.cIE10DoctorPK.idUser = :idUser"),
    @NamedQuery(name = "CIE10Doctor.findByLastUsed", query = "SELECT c FROM CIE10Doctor c WHERE c.lastUsed = :lastUsed")})
public class CIE10Doctor implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CIE10DoctorPK cIE10DoctorPK;
    @Basic(optional = false)
    @Column(name = "lastUsed")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUsed;
    @JoinColumn(name = "idCIE10", referencedColumnName = "idCIE10", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Cie10 cie10;
    @JoinColumn(name = "idUser", referencedColumnName = "idUser", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    @JsonIgnore
    private User user;

    public CIE10Doctor() {
    }

    public CIE10Doctor(CIE10DoctorPK cIE10DoctorPK) {
        this.cIE10DoctorPK = cIE10DoctorPK;
    }

    public CIE10Doctor(CIE10DoctorPK cIE10DoctorPK, Date lastUsed) {
        this.cIE10DoctorPK = cIE10DoctorPK;
        this.lastUsed = lastUsed;
    }

    public CIE10Doctor(int idCIE10, int idUser) {
        this.cIE10DoctorPK = new CIE10DoctorPK(idCIE10, idUser);
    }

    public CIE10Doctor(Date lastUsed, Cie10 cie10, User user) {
        this.lastUsed = lastUsed;
        this.cie10 = cie10;
        this.user = user;
    }
    
    public CIE10Doctor(Date lastUsed, Cie10 cie10) {
        this.lastUsed = lastUsed;
        this.cie10 = cie10;
    }

    public CIE10DoctorPK getCIE10DoctorPK() {
        return cIE10DoctorPK;
    }

    public void setCIE10DoctorPK(CIE10DoctorPK cIE10DoctorPK) {
        this.cIE10DoctorPK = cIE10DoctorPK;
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
        hash += (cIE10DoctorPK != null ? cIE10DoctorPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CIE10Doctor)) {
            return false;
        }
        CIE10Doctor other = (CIE10Doctor) object;
        if ((this.cIE10DoctorPK == null && other.cIE10DoctorPK != null) || (this.cIE10DoctorPK != null && !this.cIE10DoctorPK.equals(other.cIE10DoctorPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pruebas1.CIE10Doctor[ cIE10DoctorPK=" + cIE10DoctorPK + " ]";
    }
    
}
