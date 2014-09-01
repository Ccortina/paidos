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
@Table(name = "vaccinetype")
@NamedQueries({
    @NamedQuery(name = "Vaccinetype.findAll", query = "SELECT v FROM Vaccinetype v")})
public class Vaccinetype implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IdvaccineType")
    private Integer idvaccineType;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "type")
    private String type;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idVaccineType")
    private List<Vaccine> vaccineList;

    public Vaccinetype() {
    }

    public Vaccinetype(Integer idvaccineType) {
        this.idvaccineType = idvaccineType;
    }

    public Vaccinetype(Integer idvaccineType, String type) {
        this.idvaccineType = idvaccineType;
        this.type = type;
    }

    public Integer getIdvaccineType() {
        return idvaccineType;
    }

    public void setIdvaccineType(Integer idvaccineType) {
        this.idvaccineType = idvaccineType;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Vaccine> getVaccineList() {
        return vaccineList;
    }

    public void setVaccineList(List<Vaccine> vaccineList) {
        this.vaccineList = vaccineList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idvaccineType != null ? idvaccineType.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Vaccinetype)) {
            return false;
        }
        Vaccinetype other = (Vaccinetype) object;
        if ((this.idvaccineType == null && other.idvaccineType != null) || (this.idvaccineType != null && !this.idvaccineType.equals(other.idvaccineType))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.carloscortina.demo.model.Vaccinetype[ idvaccineType=" + idvaccineType + " ]";
    }
    
}
