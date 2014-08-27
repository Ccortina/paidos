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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

/**
 *
 * @author Carlos Cortina
 */
@Entity
@Table(name = "drug")
@NamedQueries({
    @NamedQuery(name = "Drug.findAll", query = "SELECT d FROM Drug d")})
public class Drug implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idDrug")
    private Integer idDrug;
    @Size(max = 65535)
    @Column(name = "drug")
    private String drug;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "concentration")
    private Double concentration;
    @Column(name = "treatmentDays")
    private Integer treatmentDays;
    @Size(max = 65535)
    @Column(name = "applicationSchedule")
    private String applicationSchedule;
    @Column(name = "dailyFrequency")
    private Integer dailyFrequency;
    @Size(max = 65535)
    @Column(name = "notes")
    private String notes;
    @Basic(optional = false)
    @NotNull
    @Column(name = "active")
    private int active;
    @JoinColumn(name = "drugPresentationId", referencedColumnName = "drugPresentationId")
    @ManyToOne(optional = false)
    private Drugpresentation drugPresentationId;
    @JoinColumn(name = "doseCalculationCriteriaId", referencedColumnName = "idDoseCalculationCriteria")
    @ManyToOne(optional = false)
    private Dosecalculationcriteria doseCalculationCriteriaId;
    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idDrug")
    private List<Drugdose> drugdoseList;
    @JoinColumn(name = "applicationMethodId", referencedColumnName = "idApplicationMethod")
    @ManyToOne(optional = false)
    private Applicationmethod applicationMethodId;
    @JoinColumn(name = "administrationUnitId", referencedColumnName = "idAdministrationUnit")
    @ManyToOne(optional = false)
    private Administrationunit administrationUnitId;
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinTable(name = "treatmentdrug", joinColumns = {
        @JoinColumn(name = "drugId", referencedColumnName = "idDrug")}, inverseJoinColumns = {
        @JoinColumn(name = "treatmentId", referencedColumnName = "IdTreatment")})
    @ManyToMany
    private List<Treatment> treatmentList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "drug")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Incompatibledrugs> incompatibledrugsList;
    @JoinTable(name = "patientdrugalergic", joinColumns = {
        @JoinColumn(name = "idDrug", referencedColumnName = "idDrug")}, inverseJoinColumns = {
        @JoinColumn(name = "idPatient", referencedColumnName = "idPatient")})
    @ManyToMany
    @JsonIgnore
    private List<Patient> patientList;

    public Drug() {
    }

    public Drug(Integer idDrug) {
        this.idDrug = idDrug;
    }

    public Drug(Integer idDrug, int active) {
        this.idDrug = idDrug;
        this.active = active;
    }

    public Integer getIdDrug() {
        return idDrug;
    }

    public void setIdDrug(Integer idDrug) {
        this.idDrug = idDrug;
    }

    public String getDrug() {
        return drug;
    }

    public void setDrug(String drug) {
        this.drug = drug;
    }

    public Double getConcentration() {
        return concentration;
    }

    public void setConcentration(Double concentration) {
        this.concentration = concentration;
    }

    public Integer getTreatmentDays() {
        return treatmentDays;
    }

    public void setTreatmentDays(Integer treatmentDays) {
        this.treatmentDays = treatmentDays;
    }

    public String getApplicationSchedule() {
        return applicationSchedule;
    }

    public void setApplicationSchedule(String applicationSchedule) {
        this.applicationSchedule = applicationSchedule;
    }

    public Integer getDailyFrequency() {
        return dailyFrequency;
    }

    public void setDailyFrequency(Integer dailyFrequency) {
        this.dailyFrequency = dailyFrequency;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public Drugpresentation getDrugPresentationId() {
        return drugPresentationId;
    }

    public void setDrugPresentationId(Drugpresentation drugPresentationId) {
        this.drugPresentationId = drugPresentationId;
    }

    public Dosecalculationcriteria getDoseCalculationCriteriaId() {
        return doseCalculationCriteriaId;
    }

    public void setDoseCalculationCriteriaId(Dosecalculationcriteria doseCalculationCriteriaId) {
        this.doseCalculationCriteriaId = doseCalculationCriteriaId;
    }

    public List<Drugdose> getDrugdoseList() {
        return drugdoseList;
    }

    public void setDrugdoseList(List<Drugdose> drugdoseList) {
        this.drugdoseList = drugdoseList;
    }

    public Applicationmethod getApplicationMethodId() {
        return applicationMethodId;
    }

    public void setApplicationMethodId(Applicationmethod applicationMethodId) {
        this.applicationMethodId = applicationMethodId;
    }

    public Administrationunit getAdministrationUnitId() {
        return administrationUnitId;
    }

    public void setAdministrationUnitId(Administrationunit administrationUnitId) {
        this.administrationUnitId = administrationUnitId;
    }

    public List<Incompatibledrugs> getIncompatibledrugsList() {
        return incompatibledrugsList;
    }

    public void setIncompatibledrugsList(List<Incompatibledrugs> incompatibledrugsList) {
        this.incompatibledrugsList = incompatibledrugsList;
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
        hash += (idDrug != null ? idDrug.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Drug)) {
            return false;
        }
        Drug other = (Drug) object;
        if ((this.idDrug == null && other.idDrug != null) || (this.idDrug != null && !this.idDrug.equals(other.idDrug))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.carloscortina.demo.model.Drug[ idDrug=" + idDrug + " ]";
    }

    public List<Treatment> getTreatmentList() {
        return treatmentList;
    }

    public void setTreatmentList(List<Treatment> treatmentList) {
        this.treatmentList = treatmentList;
    }
    
}
