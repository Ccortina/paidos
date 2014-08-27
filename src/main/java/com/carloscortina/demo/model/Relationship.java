/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Carlos Cortina
 */
@Entity
@Table(name = "relationship")
@NamedQueries({
    @NamedQuery(name = "Relationship.findAll", query = "SELECT r FROM Relationship r")})
public class Relationship implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idRelationship")
    private Integer idRelationship;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "Relationship")
    private String relationship;
    @Basic(optional = false)
    @NotNull
    @Column(name = "active")
    private int active;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idRelationship")
    private List<PatientRelative> patientRelativeList;

    public Relationship() {
    }

    public Relationship(Integer idRelationship) {
        this.idRelationship = idRelationship;
    }

    public Relationship(Integer idRelationship, String relationship, int active) {
        this.idRelationship = idRelationship;
        this.relationship = relationship;
        this.active = active;
    }

    public Relationship(String relationship, int active) {
        this.relationship = relationship;
        this.active = active;
    }

    public Integer getIdRelationship() {
        return idRelationship;
    }

    public void setIdRelationship(Integer idRelationship) {
        this.idRelationship = idRelationship;
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public List<PatientRelative> getPatientRelativeList() {
        return patientRelativeList;
    }

    public void setPatientRelativeList(List<PatientRelative> patientRelativeList) {
        this.patientRelativeList = patientRelativeList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRelationship != null ? idRelationship.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Relationship)) {
            return false;
        }
        Relationship other = (Relationship) object;
        if ((this.idRelationship == null && other.idRelationship != null) || (this.idRelationship != null && !this.idRelationship.equals(other.idRelationship))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.carloscortina.demo.model.Relationship[ idRelationship=" + idRelationship + " ]";
    }
    
}
