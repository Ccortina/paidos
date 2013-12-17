package com.carloscortina.demo.model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 *
 * @author carloscortina
 */
@Entity
@Table(name = "treatment")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Treatment.findAll", query = "SELECT t FROM Treatment t"),
    @NamedQuery(name = "Treatment.findByIdTreatment", query = "SELECT t FROM Treatment t WHERE t.idTreatment = :idTreatment"),
    @NamedQuery(name = "Treatment.findByActive", query = "SELECT t FROM Treatment t WHERE t.active = :active")})
public class Treatment implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IdTreatment")
    private Integer idTreatment;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "treatment")
    private String treatment;
    @Lob
    @Size(max = 65535)
    @Column(name = "directions")
    private String directions;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "active")
    private String active;
    //fetch=FetchType.EAGER
    @JsonIgnore
    @JoinTable(name = "diagnosticTreatment", joinColumns = {
        @JoinColumn(name = "treatmentId", referencedColumnName = "IdTreatment")}, inverseJoinColumns = {
        @JoinColumn(name = "diagnosticId", referencedColumnName = "idCIE10")})
    @ManyToMany()
    private Collection<Cie10> cie10Collection;

    public Treatment() {
    }

    public Treatment(Integer idTreatment) {
        this.idTreatment = idTreatment;
    }

    public Treatment(Integer idTreatment, String treatment, String active) {
        this.idTreatment = idTreatment;
        this.treatment = treatment;
        this.active = active;
    }

    public Integer getIdTreatment() {
        return idTreatment;
    }

    public void setIdTreatment(Integer idTreatment) {
        this.idTreatment = idTreatment;
    }

    public String getTreatment() {
        return treatment;
    }

    public void setTreatment(String treatment) {
        this.treatment = treatment;
    }

    public String getDirections() {
        return directions;
    }

    public void setDirections(String directions) {
        this.directions = directions;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    @XmlTransient
    public Collection<Cie10> getCie10Collection() {
        return cie10Collection;
    }

    public void setCie10Collection(Collection<Cie10> cie10Collection) {
        this.cie10Collection = cie10Collection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTreatment != null ? idTreatment.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Treatment)) {
            return false;
        }
        Treatment other = (Treatment) object;
        if ((this.idTreatment == null && other.idTreatment != null) || (this.idTreatment != null && !this.idTreatment.equals(other.idTreatment))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "diagnostic.Treatment[ idTreatment=" + idTreatment + " ]";
    }
    
}