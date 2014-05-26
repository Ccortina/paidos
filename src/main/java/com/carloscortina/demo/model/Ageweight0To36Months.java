/*
 * To change this template, choose Tools | Templates
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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ccortina_Mac
 */
@Entity
@Table(name = "ageweight0To36Months")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ageweight0To36Months.findAll", query = "SELECT a FROM Ageweight0To36Months a"),
    @NamedQuery(name = "Ageweight0To36Months.findById", query = "SELECT a FROM Ageweight0To36Months a WHERE a.id = :id"),
    @NamedQuery(name = "Ageweight0To36Months.findByGender", query = "SELECT a FROM Ageweight0To36Months a WHERE a.gender = :gender"),
    @NamedQuery(name = "Ageweight0To36Months.findByAgeInMonths", query = "SELECT a FROM Ageweight0To36Months a WHERE a.ageInMonths = :ageInMonths"),
    @NamedQuery(name = "Ageweight0To36Months.findByP3", query = "SELECT a FROM Ageweight0To36Months a WHERE a.p3 = :p3"),
    @NamedQuery(name = "Ageweight0To36Months.findByP5", query = "SELECT a FROM Ageweight0To36Months a WHERE a.p5 = :p5"),
    @NamedQuery(name = "Ageweight0To36Months.findByP10", query = "SELECT a FROM Ageweight0To36Months a WHERE a.p10 = :p10"),
    @NamedQuery(name = "Ageweight0To36Months.findByP25", query = "SELECT a FROM Ageweight0To36Months a WHERE a.p25 = :p25"),
    @NamedQuery(name = "Ageweight0To36Months.findByP50", query = "SELECT a FROM Ageweight0To36Months a WHERE a.p50 = :p50"),
    @NamedQuery(name = "Ageweight0To36Months.findByP75", query = "SELECT a FROM Ageweight0To36Months a WHERE a.p75 = :p75"),
    @NamedQuery(name = "Ageweight0To36Months.findByP90", query = "SELECT a FROM Ageweight0To36Months a WHERE a.p90 = :p90"),
    @NamedQuery(name = "Ageweight0To36Months.findByP95", query = "SELECT a FROM Ageweight0To36Months a WHERE a.p95 = :p95"),
    @NamedQuery(name = "Ageweight0To36Months.findByP97", query = "SELECT a FROM Ageweight0To36Months a WHERE a.p97 = :p97")})
public class Ageweight0To36Months implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "gender")
    private String gender;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ageInMonths")
    private double ageInMonths;
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

    public Ageweight0To36Months() {
    }

    public Ageweight0To36Months(Integer id) {
        this.id = id;
    }

    public Ageweight0To36Months(Integer id, String gender, double ageInMonths, double p3, double p5, double p10, double p25, double p50, double p75, double p90, double p95, double p97) {
        this.id = id;
        this.gender = gender;
        this.ageInMonths = ageInMonths;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public double getAgeInMonths() {
        return ageInMonths;
    }

    public void setAgeInMonths(double ageInMonths) {
        this.ageInMonths = ageInMonths;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ageweight0To36Months)) {
            return false;
        }
        Ageweight0To36Months other = (Ageweight0To36Months) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.carloscortina.demo.model.Ageweight0To36Months[ id=" + id + " ]";
    }
    
}
