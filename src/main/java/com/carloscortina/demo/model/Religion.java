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
@Table(name = "religion")
@NamedQueries({
    @NamedQuery(name = "Religion.findAll", query = "SELECT r FROM Religion r")})
public class Religion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idReligion")
    private Integer idReligion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "Religion")
    private String religion;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "religion")
    private List<Relative> relativeList;
    @Basic(optional = false)
    @NotNull
    @Column(name = "active")
    private int active;

    public Religion() {
    }

    public Religion(Integer idReligion) {
        this.idReligion = idReligion;
    }

    public Religion(Integer idReligion, String religion) {
        this.idReligion = idReligion;
        this.religion = religion;
    }

    public Integer getIdReligion() {
        return idReligion;
    }

    public void setIdReligion(Integer idReligion) {
        this.idReligion = idReligion;
    }

    public String getReligion() {
        return religion;
    }

    public void setReligion(String religion) {
        this.religion = religion;
    }

    public List<Relative> getRelativeList() {
        return relativeList;
    }

    public void setRelativeList(List<Relative> relativeList) {
        this.relativeList = relativeList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idReligion != null ? idReligion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Religion)) {
            return false;
        }
        Religion other = (Religion) object;
        if ((this.idReligion == null && other.idReligion != null) || (this.idReligion != null && !this.idReligion.equals(other.idReligion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.carloscortina.demo.model.Religion[ idReligion=" + idReligion + " ]";
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }
    
}
