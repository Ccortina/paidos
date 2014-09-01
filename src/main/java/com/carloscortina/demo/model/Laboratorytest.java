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
@Table(name = "laboratorytest")
@NamedQueries({
    @NamedQuery(name = "Laboratorytest.findAll", query = "SELECT l FROM Laboratorytest l")})
public class Laboratorytest implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idLaboratoryTest")
    private Integer idLaboratoryTest;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "laboratoryTest")
    private String laboratoryTest;
    @Basic(optional = false)
    @NotNull
    @Column(name = "active")
    private int active;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idLaboratoryTest")
    private List<Laboratorytestresult> laboratorytestresultList;

    public Laboratorytest() {
    }

    public Laboratorytest(Integer idLaboratoryTest) {
        this.idLaboratoryTest = idLaboratoryTest;
    }

    public Laboratorytest(Integer idLaboratoryTest, String laboratoryTest, int active) {
        this.idLaboratoryTest = idLaboratoryTest;
        this.laboratoryTest = laboratoryTest;
        this.active = active;
    }

    public Laboratorytest(String laboratoryTest, int active) {
        this.laboratoryTest = laboratoryTest;
        this.active = active;
    }

    public Integer getIdLaboratoryTest() {
        return idLaboratoryTest;
    }

    public void setIdLaboratoryTest(Integer idLaboratoryTest) {
        this.idLaboratoryTest = idLaboratoryTest;
    }

    public String getLaboratoryTest() {
        return laboratoryTest;
    }

    public void setLaboratoryTest(String laboratoryTest) {
        this.laboratoryTest = laboratoryTest;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public List<Laboratorytestresult> getLaboratorytestresultList() {
        return laboratorytestresultList;
    }

    public void setLaboratorytestresultList(List<Laboratorytestresult> laboratorytestresultList) {
        this.laboratorytestresultList = laboratorytestresultList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idLaboratoryTest != null ? idLaboratoryTest.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Laboratorytest)) {
            return false;
        }
        Laboratorytest other = (Laboratorytest) object;
        if ((this.idLaboratoryTest == null && other.idLaboratoryTest != null) || (this.idLaboratoryTest != null && !this.idLaboratoryTest.equals(other.idLaboratoryTest))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.carloscortina.demo.model.Laboratorytest[ idLaboratoryTest=" + idLaboratoryTest + " ]";
    }
    
}
