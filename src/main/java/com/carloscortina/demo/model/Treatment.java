package com.carloscortina.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;

import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.Lob;
import javax.persistence.ManyToMany;

import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;


/**
 *
 * @author Ccortina_Mac
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
    @Lob
    @Column(name = "treatment")
    private String treatment;
    @Lob
    @Column(name = "directions")
    private String directions;
    @Basic(optional = false)
    @Column(name = "active")
    private String active;
    @ManyToMany(mappedBy = "treatmentList")
    @JsonIgnore
    private List<User> userList;
    @ManyToMany(mappedBy = "treatmentList")
    @JsonIgnore
    private List<Drug> drugList;
    @JsonIgnore
    @ManyToMany(mappedBy = "treatmentList")
    private List<Cie10> cie10List;

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
    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    @XmlTransient
    public List<Drug> getDrugList() {
        return drugList;
    }

    public void setDrugList(List<Drug> drugList) {
        this.drugList = drugList;
    }

    public List<Cie10> getCie10List() {
        return cie10List;
    }

    public void setCie10List(List<Cie10> cie10List) {
        this.cie10List = cie10List;
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
        return "pruebas1.Treatment[ idTreatment=" + idTreatment + " ]";
    }
    
}