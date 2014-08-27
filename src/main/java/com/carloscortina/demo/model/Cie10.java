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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
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
@Table(name = "cie10")
@NamedQueries({
    @NamedQuery(name = "Cie10.findAll", query = "SELECT c FROM Cie10 c")})
public class Cie10 implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idCIE10")
    private Integer idCIE10;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "cieCode")
    private String cieCode;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 65535)
    @Column(name = "diagnostic")
    private String diagnostic;
    @Basic(optional = false)
    @NotNull
    @Column(name = "active")
    private int active;
    @JsonIgnore
    @JoinTable(name = "diagnostictreatment", joinColumns = {
        @JoinColumn(name = "diagnosticId", referencedColumnName = "idCIE10")}, inverseJoinColumns = {
        @JoinColumn(name = "treatmentId", referencedColumnName = "IdTreatment")})
    @ManyToMany
    private List<Treatment> treatmentList;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cie10")
    private List<Cie10doctor> cie10doctorList;
    @JsonIgnore
    @OneToMany(mappedBy = "idCIE10")
    private List<Diagnostic> diagnosticList;

    public Cie10() {
    }

    public Cie10(Integer idCIE10) {
        this.idCIE10 = idCIE10;
    }

    public Cie10(Integer idCIE10, String cieCode, String diagnostic, int active) {
        this.idCIE10 = idCIE10;
        this.cieCode = cieCode;
        this.diagnostic = diagnostic;
        this.active = active;
    }

    public Integer getIdCIE10() {
        return idCIE10;
    }

    public void setIdCIE10(Integer idCIE10) {
        this.idCIE10 = idCIE10;
    }

    public String getCieCode() {
        return cieCode;
    }

    public void setCieCode(String cieCode) {
        this.cieCode = cieCode;
    }

    public String getDiagnostic() {
        return diagnostic;
    }

    public void setDiagnostic(String diagnostic) {
        this.diagnostic = diagnostic;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public List<Treatment> getTreatmentList() {
        return treatmentList;
    }

    public void setTreatmentList(List<Treatment> treatmentList) {
        this.treatmentList = treatmentList;
    }

    public List<Cie10doctor> getCie10doctorList() {
        return cie10doctorList;
    }

    public void setCie10doctorList(List<Cie10doctor> cie10doctorList) {
        this.cie10doctorList = cie10doctorList;
    }

    public List<Diagnostic> getDiagnosticList() {
        return diagnosticList;
    }

    public void setDiagnosticList(List<Diagnostic> diagnosticList) {
        this.diagnosticList = diagnosticList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCIE10 != null ? idCIE10.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cie10)) {
            return false;
        }
        Cie10 other = (Cie10) object;
        if ((this.idCIE10 == null && other.idCIE10 != null) || (this.idCIE10 != null && !this.idCIE10.equals(other.idCIE10))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.carloscortina.demo.model.Cie10[ idCIE10=" + idCIE10 + " ]";
    }
    
}
