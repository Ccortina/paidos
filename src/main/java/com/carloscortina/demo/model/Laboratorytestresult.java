/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.carloscortina.demo.model;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Carlos Cortina
 */
@Entity
@Table(name = "laboratorytestresult")
@NamedQueries({
    @NamedQuery(name = "Laboratorytestresult.findAll", query = "SELECT l FROM Laboratorytestresult l")})
public class Laboratorytestresult implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idLaboratoryTestResult")
    private Integer idLaboratoryTestResult;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 65535)
    @Column(name = "result")
    private String result;
    @Basic(optional = false)
    @NotNull
    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    private Date date;
    @JoinColumn(name = "idPatient", referencedColumnName = "idPatient")
    @ManyToOne(optional = false)
    private Patient idPatient;
    @JoinColumn(name = "idLaboratoryTest", referencedColumnName = "idLaboratoryTest")
    @ManyToOne(optional = false)
    private Laboratorytest idLaboratoryTest;

    public Laboratorytestresult() {
    }

    public Laboratorytestresult(Integer idLaboratoryTestResult) {
        this.idLaboratoryTestResult = idLaboratoryTestResult;
    }

    public Laboratorytestresult(Integer idLaboratoryTestResult, String result, Date date) {
        this.idLaboratoryTestResult = idLaboratoryTestResult;
        this.result = result;
        this.date = date;
    }

    public Laboratorytestresult(String result, Date date, Patient idPatient, Laboratorytest idLaboratoryTest) {
        this.result = result;
        this.date = date;
        this.idPatient = idPatient;
        this.idLaboratoryTest = idLaboratoryTest;
    }

    public Integer getIdLaboratoryTestResult() {
        return idLaboratoryTestResult;
    }

    public void setIdLaboratoryTestResult(Integer idLaboratoryTestResult) {
        this.idLaboratoryTestResult = idLaboratoryTestResult;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Patient getIdPatient() {
        return idPatient;
    }

    public void setIdPatient(Patient idPatient) {
        this.idPatient = idPatient;
    }

    public Laboratorytest getIdLaboratoryTest() {
        return idLaboratoryTest;
    }

    public void setIdLaboratoryTest(Laboratorytest idLaboratoryTest) {
        this.idLaboratoryTest = idLaboratoryTest;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idLaboratoryTestResult != null ? idLaboratoryTestResult.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Laboratorytestresult)) {
            return false;
        }
        Laboratorytestresult other = (Laboratorytestresult) object;
        if ((this.idLaboratoryTestResult == null && other.idLaboratoryTestResult != null) || (this.idLaboratoryTestResult != null && !this.idLaboratoryTestResult.equals(other.idLaboratoryTestResult))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.carloscortina.demo.model.Laboratorytestresult[ idLaboratoryTestResult=" + idLaboratoryTestResult + " ]";
    }
    
}
