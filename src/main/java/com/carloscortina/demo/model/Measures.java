/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.carloscortina.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Ccortina_Mac
 */
@Entity
@Table(name = "measures")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Measures.findAll", query = "SELECT m FROM Measures m"),
    @NamedQuery(name = "Measures.findByIdMeasures", query = "SELECT m FROM Measures m WHERE m.idMeasures = :idMeasures"),
    @NamedQuery(name = "Measures.findByMeasure", query = "SELECT m FROM Measures m WHERE m.measure = :measure"),
    @NamedQuery(name = "Measures.findByUnits", query = "SELECT m FROM Measures m WHERE m.units = :units")})
public class Measures implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idMeasures")
    private Integer idMeasures;
    @Basic(optional = false)
    @Column(name = "measure")
    private String measure;
    @Basic(optional = false)
    @Column(name = "units")
    private String units;
    @Basic(optional = false)
    @Column(name = "includePrescription")
    private short includePrescription;
    @Basic(optional = false)
    @NotNull
    @Column(name = "active")
    private short active;
    @JoinColumn(name = "idUser", referencedColumnName = "idUser")
    @ManyToOne(optional = false)
    @JsonIgnore
    private User idUser;

    public Measures() {
    }

    public Measures(Integer idMeasures) {
        this.idMeasures = idMeasures;
    }

    public Measures(String measure, String units) {
        this.measure = measure;
        this.units = units;
    }

    public Measures(String measure, String units, short includePrescription, short active, User idUser) {
        this.measure = measure;
        this.units = units;
        this.includePrescription = includePrescription;
        this.active = active;
        this.idUser = idUser;
    }

    public Measures(Integer idMeasures, String measure, String units) {
        this.idMeasures = idMeasures;
        this.measure = measure;
        this.units = units;
    }

    public Measures(String measure, String units, User idUser) {
        this.measure = measure;
        this.units = units;
        this.idUser = idUser;
    }

    public Integer getIdMeasures() {
        return idMeasures;
    }

    public void setIdMeasures(Integer idMeasures) {
        this.idMeasures = idMeasures;
    }

    public String getMeasure() {
        return measure;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }

    public String getUnits() {
        return units;
    }

    public void setUnits(String units) {
        this.units = units;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMeasures != null ? idMeasures.hashCode() : 0);
        return hash;
    }

    public short getActive() {
        return active;
    }

    public void setActive(short active) {
        this.active = active;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Measures)) {
            return false;
        }
        Measures other = (Measures) object;
        if ((this.idMeasures == null && other.idMeasures != null) || (this.idMeasures != null && !this.idMeasures.equals(other.idMeasures))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.carloscortina.demo.model.Measures[ idMeasures=" + idMeasures + " ]";
    }

    public short getIncludePrescription() {
        return includePrescription;
    }

    public void setIncludePrescription(short includePrescription) {
        this.includePrescription = includePrescription;
    }

    public User getIdUser() {
        return idUser;
    }

    public void setIdUser(User idUser) {
        this.idUser = idUser;
    }
}
