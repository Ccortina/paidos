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
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Carlos Cortina
 */
@Entity
@Table(name = "consultationmotive")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Consultationmotive.findAll", query = "SELECT c FROM Consultationmotive c"),
    @NamedQuery(name = "Consultationmotive.findByIdconsultationmotive", query = "SELECT c FROM Consultationmotive c WHERE c.idconsultationmotive = :idconsultationmotive"),
    @NamedQuery(name = "Consultationmotive.findByMotive", query = "SELECT c FROM Consultationmotive c WHERE c.motive = :motive"),
    @NamedQuery(name = "Consultationmotive.findByLastUsed", query = "SELECT c FROM Consultationmotive c WHERE c.lastUsed = :lastUsed")})
public class Consultationmotive implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idconsultationmotive")
    private Integer idconsultationmotive;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "motive")
    private String motive;
    @Basic(optional = false)
    @NotNull
    @Column(name = "lastUsed")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUsed;

    public Consultationmotive() {
    }

    public Consultationmotive(Integer idconsultationmotive) {
        this.idconsultationmotive = idconsultationmotive;
    }

    public Consultationmotive(Integer idconsultationmotive, String motive, Date lastUsed) {
        this.idconsultationmotive = idconsultationmotive;
        this.motive = motive;
        this.lastUsed = lastUsed;
    }

    public Integer getIdconsultationmotive() {
        return idconsultationmotive;
    }

    public void setIdconsultationmotive(Integer idconsultationmotive) {
        this.idconsultationmotive = idconsultationmotive;
    }

    public String getMotive() {
        return motive;
    }

    public void setMotive(String motive) {
        this.motive = motive;
    }

    public Date getLastUsed() {
        return lastUsed;
    }

    public void setLastUsed(Date lastUsed) {
        this.lastUsed = lastUsed;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idconsultationmotive != null ? idconsultationmotive.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Consultationmotive)) {
            return false;
        }
        Consultationmotive other = (Consultationmotive) object;
        if ((this.idconsultationmotive == null && other.idconsultationmotive != null) || (this.idconsultationmotive != null && !this.idconsultationmotive.equals(other.idconsultationmotive))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.carloscortina.demo.model.Consultationmotive[ idconsultationmotive=" + idconsultationmotive + " ]";
    }
    
}
