/*
 * To change this template, choose Tools | Templates
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
 * @author Ccortina_Mac
 */
@Entity
@Table(name = "holyday")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Holyday.findAll", query = "SELECT h FROM Holyday h"),
    @NamedQuery(name = "Holyday.findByIdHolydays", query = "SELECT h FROM Holyday h WHERE h.idHolydays = :idHolydays"),
    @NamedQuery(name = "Holyday.findByHolyday", query = "SELECT h FROM Holyday h WHERE h.holyday = :holyday"),
    @NamedQuery(name = "Holyday.findByDate", query = "SELECT h FROM Holyday h WHERE h.date = :date")})
public class Holyday implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idHolydays")
    private Integer idHolydays;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "holyday")
    private String holyday;
    @Basic(optional = false)
    @NotNull
    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    private Date date;

    public Holyday() {
    }

    public Holyday(Integer idHolydays) {
        this.idHolydays = idHolydays;
    }

    public Holyday(Integer idHolydays, String holyday, Date date) {
        this.idHolydays = idHolydays;
        this.holyday = holyday;
        this.date = date;
    }

    public Integer getIdHolydays() {
        return idHolydays;
    }

    public void setIdHolydays(Integer idHolydays) {
        this.idHolydays = idHolydays;
    }

    public String getHolyday() {
        return holyday;
    }

    public void setHolyday(String holyday) {
        this.holyday = holyday;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idHolydays != null ? idHolydays.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Holyday)) {
            return false;
        }
        Holyday other = (Holyday) object;
        if ((this.idHolydays == null && other.idHolydays != null) || (this.idHolydays != null && !this.idHolydays.equals(other.idHolydays))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.carloscortina.demo.model.Holyday[ idHolydays=" + idHolydays + " ]";
    }
    
}
