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
import javax.validation.constraints.NotNull;

/**
 *
 * @author Carlos Cortina
 */
@Entity
@Table(name = "agepc")
@NamedQueries({
    @NamedQuery(name = "Agepc.findAll", query = "SELECT a FROM Agepc a")})
public class Agepc implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idAgepc")
    private Integer idAgepc;
    @Basic(optional = false)
    @NotNull
    @Column(name = "months")
    private double months;
    @Basic(optional = false)
    @NotNull
    @Column(name = "p3")
    private double p3;
    @Basic(optional = false)
    @NotNull
    @Column(name = "p5")
    private double p5;
    @Basic(optional = false)
    @NotNull
    @Column(name = "p10")
    private double p10;
    @Basic(optional = false)
    @NotNull
    @Column(name = "p25")
    private double p25;
    @Basic(optional = false)
    @NotNull
    @Column(name = "p50")
    private double p50;
    @Basic(optional = false)
    @NotNull
    @Column(name = "p75")
    private double p75;
    @Basic(optional = false)
    @NotNull
    @Column(name = "p90")
    private double p90;
    @Basic(optional = false)
    @NotNull
    @Column(name = "p95")
    private double p95;
    @Basic(optional = false)
    @NotNull
    @Column(name = "p97")
    private double p97;
    @JoinColumn(name = "gender", referencedColumnName = "idGender")
    @ManyToOne(optional = false)
    private Gender gender;

    public Agepc() {
    }

    public Agepc(Integer idAgepc) {
        this.idAgepc = idAgepc;
    }

    public Agepc(Integer idAgepc, double months, double p3, double p5, double p10, double p25, double p50, double p75, double p90, double p95, double p97) {
        this.idAgepc = idAgepc;
        this.months = months;
        this.p3 = p3;
        this.p5 = p5;
        this.p10 = p10;
        this.p25 = p25;
        this.p50 = p50;
        this.p75 = p75;
        this.p90 = p90;
        this.p95 = p95;
        this.p97 = p97;
    }

    public Integer getIdAgepc() {
        return idAgepc;
    }

    public void setIdAgepc(Integer idAgepc) {
        this.idAgepc = idAgepc;
    }

    public double getMonths() {
        return months;
    }

    public void setMonths(double months) {
        this.months = months;
    }

    public double getP3() {
        return p3;
    }

    public void setP3(double p3) {
        this.p3 = p3;
    }

    public double getP5() {
        return p5;
    }

    public void setP5(double p5) {
        this.p5 = p5;
    }

    public double getP10() {
        return p10;
    }

    public void setP10(double p10) {
        this.p10 = p10;
    }

    public double getP25() {
        return p25;
    }

    public void setP25(double p25) {
        this.p25 = p25;
    }

    public double getP50() {
        return p50;
    }

    public void setP50(double p50) {
        this.p50 = p50;
    }

    public double getP75() {
        return p75;
    }

    public void setP75(double p75) {
        this.p75 = p75;
    }

    public double getP90() {
        return p90;
    }

    public void setP90(double p90) {
        this.p90 = p90;
    }

    public double getP95() {
        return p95;
    }

    public void setP95(double p95) {
        this.p95 = p95;
    }

    public double getP97() {
        return p97;
    }

    public void setP97(double p97) {
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
        hash += (idAgepc != null ? idAgepc.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Agepc)) {
            return false;
        }
        Agepc other = (Agepc) object;
        if ((this.idAgepc == null && other.idAgepc != null) || (this.idAgepc != null && !this.idAgepc.equals(other.idAgepc))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.carloscortina.demo.model.Agepc[ idAgepc=" + idAgepc + " ]";
    }
    
}
