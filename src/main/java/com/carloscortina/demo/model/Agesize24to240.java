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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Carlos Cortina
 */
@Entity
@Table(name = "agesize24to240")
@NamedQueries({
    @NamedQuery(name = "Agesize24to240.findAll", query = "SELECT a FROM Agesize24to240 a")})
public class Agesize24to240 implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idAgesize24to240")
    private Integer idAgesize24to240;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "months")
    private Double months;
    @Column(name = "p3")
    private Double p3;
    @Column(name = "p5")
    private Double p5;
    @Column(name = "p10")
    private Double p10;
    @Column(name = "p25")
    private Double p25;
    @Column(name = "p50")
    private Double p50;
    @Column(name = "p75")
    private Double p75;
    @Column(name = "p90")
    private Double p90;
    @Column(name = "p95")
    private Double p95;
    @Column(name = "p97")
    private Double p97;
    @JoinColumn(name = "gender", referencedColumnName = "idGender")
    @ManyToOne(optional = false)
    private Gender gender;

    public Agesize24to240() {
    }

    public Agesize24to240(Integer idAgesize24to240) {
        this.idAgesize24to240 = idAgesize24to240;
    }

    public Integer getIdAgesize24to240() {
        return idAgesize24to240;
    }

    public void setIdAgesize24to240(Integer idAgesize24to240) {
        this.idAgesize24to240 = idAgesize24to240;
    }

    public Double getMonths() {
        return months;
    }

    public void setMonths(Double months) {
        this.months = months;
    }

    public Double getP3() {
        return p3;
    }

    public void setP3(Double p3) {
        this.p3 = p3;
    }

    public Double getP5() {
        return p5;
    }

    public void setP5(Double p5) {
        this.p5 = p5;
    }

    public Double getP10() {
        return p10;
    }

    public void setP10(Double p10) {
        this.p10 = p10;
    }

    public Double getP25() {
        return p25;
    }

    public void setP25(Double p25) {
        this.p25 = p25;
    }

    public Double getP50() {
        return p50;
    }

    public void setP50(Double p50) {
        this.p50 = p50;
    }

    public Double getP75() {
        return p75;
    }

    public void setP75(Double p75) {
        this.p75 = p75;
    }

    public Double getP90() {
        return p90;
    }

    public void setP90(Double p90) {
        this.p90 = p90;
    }

    public Double getP95() {
        return p95;
    }

    public void setP95(Double p95) {
        this.p95 = p95;
    }

    public Double getP97() {
        return p97;
    }

    public void setP97(Double p97) {
        this.p97 = p97;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAgesize24to240 != null ? idAgesize24to240.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Agesize24to240)) {
            return false;
        }
        Agesize24to240 other = (Agesize24to240) object;
        if ((this.idAgesize24to240 == null && other.idAgesize24to240 != null) || (this.idAgesize24to240 != null && !this.idAgesize24to240.equals(other.idAgesize24to240))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.carloscortina.demo.model.Agesize24to240[ idAgesize24to240=" + idAgesize24to240 + " ]";
    }
    
}
