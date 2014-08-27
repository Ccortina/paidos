/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.carloscortina.demo.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Carlos Cortina
 */
@Entity
@Table(name = "holyday")
@NamedQueries({
    @NamedQuery(name = "Holyday.findAll", query = "SELECT h FROM Holyday h")})
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
    @Column(name = "mont")
    private int mont;
    @Column(name = "day")
    private Integer day;

    public Holyday() {
    }

    public Holyday(Integer idHolydays) {
        this.idHolydays = idHolydays;
    }

    public Holyday(Integer idHolydays, String holyday, int mont) {
        this.idHolydays = idHolydays;
        this.holyday = holyday;
        this.mont = mont;
    }

    public Holyday(String holyday, int mont, Integer day) {
        this.holyday = holyday;
        this.mont = mont;
        this.day = day;
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

    public int getMont() {
        return mont;
    }

    public void setMont(int mont) {
        this.mont = mont;
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
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
