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
@Table(name = "gender")
@NamedQueries({
    @NamedQuery(name = "Gender.findAll", query = "SELECT g FROM Gender g")})
public class Gender implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idGender")
    private Integer idGender;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "gender")
    private String gender;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sex")
    private List<Patient> patientList;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "gender")
    private List<Ageweight0to240> ageweight0to240List;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "gender")
    private List<Sizeweight> sizeweightList;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "gender")
    private List<Heightweight> heightweightList;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "gender")
    private List<Agebmi> agebmiList;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "gender")
    private List<Agepc> agepcList;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "gender")
    private List<Agesize24to240> agesize24to240List;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "gender")
    private List<Agesize0to36> agesize0to36List;
    @Basic(optional = false)
    @NotNull
    @Column(name = "active")
    private int active;

    public Gender() {
    }

    public Gender(Integer idGender) {
        this.idGender = idGender;
    }

    public Gender(Integer idGender, String gender) {
        this.idGender = idGender;
        this.gender = gender;
    }

    public Integer getIdGender() {
        return idGender;
    }

    public void setIdGender(Integer idGender) {
        this.idGender = idGender;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public List<Patient> getPatientList() {
        return patientList;
    }

    public void setPatientList(List<Patient> patientList) {
        this.patientList = patientList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idGender != null ? idGender.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Gender)) {
            return false;
        }
        Gender other = (Gender) object;
        if ((this.idGender == null && other.idGender != null) || (this.idGender != null && !this.idGender.equals(other.idGender))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.carloscortina.demo.model.Gender[ idGender=" + idGender + " ]";
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public List<Ageweight0to240> getAgeweight0to240List() {
        return ageweight0to240List;
    }

    public void setAgeweight0to240List(List<Ageweight0to240> ageweight0to240List) {
        this.ageweight0to240List = ageweight0to240List;
    }

    public List<Sizeweight> getSizeweightList() {
        return sizeweightList;
    }

    public void setSizeweightList(List<Sizeweight> sizeweightList) {
        this.sizeweightList = sizeweightList;
    }

    public List<Heightweight> getHeightweightList() {
        return heightweightList;
    }

    public void setHeightweightList(List<Heightweight> heightweightList) {
        this.heightweightList = heightweightList;
    }

    public List<Agebmi> getAgebmiList() {
        return agebmiList;
    }

    public void setAgebmiList(List<Agebmi> agebmiList) {
        this.agebmiList = agebmiList;
    }

    public List<Agepc> getAgepcList() {
        return agepcList;
    }

    public void setAgepcList(List<Agepc> agepcList) {
        this.agepcList = agepcList;
    }

    public List<Agesize24to240> getAgesize24to240List() {
        return agesize24to240List;
    }

    public void setAgesize24to240List(List<Agesize24to240> agesize24to240List) {
        this.agesize24to240List = agesize24to240List;
    }

    public List<Agesize0to36> getAgesize0to36List() {
        return agesize0to36List;
    }

    public void setAgesize0to36List(List<Agesize0to36> agesize0to36List) {
        this.agesize0to36List = agesize0to36List;
    }
    
}
