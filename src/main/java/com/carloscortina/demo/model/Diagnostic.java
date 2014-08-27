/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.carloscortina.demo.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Carlos Cortina
 */
@Entity
@Table(name = "diagnostic")
@NamedQueries({
    @NamedQuery(name = "Diagnostic.findAll", query = "SELECT d FROM Diagnostic d")})
public class Diagnostic implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idDiagnostic")
    private Integer idDiagnostic;
    @ManyToMany(mappedBy = "diagnosticList")
    private List<Consultation> consultationList;
    @JoinColumn(name = "idTreatment", referencedColumnName = "IdTreatment")
    @ManyToOne
    private Treatment idTreatment;
    @JoinColumn(name = "idMedecine", referencedColumnName = "idDrug")
    @ManyToOne
    private Drug idMedecine;
    @JoinColumn(name = "IdCommercialName", referencedColumnName = "idcommercialName")
    @ManyToOne
    private Commercialname idCommercialName;
    @JoinColumn(name = "idCIE10", referencedColumnName = "idCIE10")
    @ManyToOne
    private Cie10 idCIE10;

    public Diagnostic() {
    }

    public Diagnostic(Integer idDiagnostic) {
        this.idDiagnostic = idDiagnostic;
    }

    public Integer getIdDiagnostic() {
        return idDiagnostic;
    }

    public void setIdDiagnostic(Integer idDiagnostic) {
        this.idDiagnostic = idDiagnostic;
    }

    public List<Consultation> getConsultationList() {
        return consultationList;
    }

    public void setConsultationList(List<Consultation> consultationList) {
        this.consultationList = consultationList;
    }

    public Treatment getIdTreatment() {
        return idTreatment;
    }

    public void setIdTreatment(Treatment idTreatment) {
        this.idTreatment = idTreatment;
    }

    public Drug getIdMedecine() {
        return idMedecine;
    }

    public void setIdMedecine(Drug idMedecine) {
        this.idMedecine = idMedecine;
    }

    public Commercialname getIdCommercialName() {
        return idCommercialName;
    }

    public void setIdCommercialName(Commercialname idCommercialName) {
        this.idCommercialName = idCommercialName;
    }

    public Cie10 getIdCIE10() {
        return idCIE10;
    }

    public void setIdCIE10(Cie10 idCIE10) {
        this.idCIE10 = idCIE10;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDiagnostic != null ? idDiagnostic.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Diagnostic)) {
            return false;
        }
        Diagnostic other = (Diagnostic) object;
        if ((this.idDiagnostic == null && other.idDiagnostic != null) || (this.idDiagnostic != null && !this.idDiagnostic.equals(other.idDiagnostic))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.carloscortina.demo.model.Diagnostic[ idDiagnostic=" + idDiagnostic + " ]";
    }
    
}
