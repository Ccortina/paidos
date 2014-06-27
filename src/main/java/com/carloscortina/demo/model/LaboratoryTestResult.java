/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.carloscortina.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ccortina_Mac
 */
@Entity
@Table(name = "LaboratoryTestResult")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LaboratoryTestResult.findAll", query = "SELECT l FROM LaboratoryTestResult l"),
    @NamedQuery(name = "LaboratoryTestResult.findByIdLaboratoryTestResult", query = "SELECT l FROM LaboratoryTestResult l WHERE l.idLaboratoryTestResult = :idLaboratoryTestResult"),
    @NamedQuery(name = "LaboratoryTestResult.findByResult", query = "SELECT l FROM LaboratoryTestResult l WHERE l.result = :result")})
public class LaboratoryTestResult implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    private Date date;
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idLaboratoryTestResult")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idLaboratoryTestResult;
    @Basic(optional = false)
    @Column(name = "result")
    private String result;
    @JoinColumn(name = "idPatient", referencedColumnName = "idPatient")
    @ManyToOne(optional = false)
    @JsonIgnore
    private Patient idPatient;
    @JoinColumn(name = "idLaboratoryTest", referencedColumnName = "idLaboratoryTest")
    @ManyToOne(optional = false)
    private LaboratoryTest idLaboratoryTest;

    public LaboratoryTestResult() {
    }

    public LaboratoryTestResult(Integer idLaboratoryTestResult) {
        this.idLaboratoryTestResult = idLaboratoryTestResult;
    }

    public LaboratoryTestResult(Integer idLaboratoryTestResult, String result) {
        this.idLaboratoryTestResult = idLaboratoryTestResult;
        this.result = result;
    }

    public LaboratoryTestResult(Date date, String result, Patient idPatient, LaboratoryTest idLaboratoryTest) {
        this.date = date;
        this.result = result;
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

    public Patient getIdPatient() {
        return idPatient;
    }

    public void setIdPatient(Patient idPatient) {
        this.idPatient = idPatient;
    }

    public LaboratoryTest getIdLaboratoryTest() {
        return idLaboratoryTest;
    }

    public void setIdLaboratoryTest(LaboratoryTest idLaboratoryTest) {
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
        if (!(object instanceof LaboratoryTestResult)) {
            return false;
        }
        LaboratoryTestResult other = (LaboratoryTestResult) object;
        if ((this.idLaboratoryTestResult == null && other.idLaboratoryTestResult != null) || (this.idLaboratoryTestResult != null && !this.idLaboratoryTestResult.equals(other.idLaboratoryTestResult))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pruebas1.LaboratoryTestResult[ idLaboratoryTestResult=" + idLaboratoryTestResult + " ]";
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
}
