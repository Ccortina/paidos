/*
 * To change this template, choose Tools | Templates
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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

/**
 *
 * @author Ccortina_Mac
 */
@Entity
@Table(name = "drug")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Drug.findAll", query = "SELECT d FROM Drug d"),
    @NamedQuery(name = "Drug.findByIdDrug", query = "SELECT d FROM Drug d WHERE d.idDrug = :idDrug"),
    @NamedQuery(name = "Drug.findByConcentration", query = "SELECT d FROM Drug d WHERE d.concentration = :concentration"),
    @NamedQuery(name = "Drug.findByTreatmentDays", query = "SELECT d FROM Drug d WHERE d.treatmentDays = :treatmentDays"),
    @NamedQuery(name = "Drug.findByDailyFrequency", query = "SELECT d FROM Drug d WHERE d.dailyFrequency = :dailyFrequency"),
    @NamedQuery(name = "Drug.findByActive", query = "SELECT d FROM Drug d WHERE d.active = :active")})
public class Drug implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idDrug")
    private Integer idDrug;
    @Column(name = "drug")
    private String drug;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "concentration")
    private Double concentration;
    @Column(name = "treatmentDays")
    private Integer treatmentDays;
    @Column(name = "applicationSchedule")
    private String applicationSchedule;
    @Column(name = "dailyFrequency")
    private Integer dailyFrequency;
    @Column(name = "notes")
    private String notes;
    @Column(name = "active")
    private Short active;
    @JoinTable(name = "doctordrugcatalog", joinColumns = {
    @JoinColumn(name = "IdDrug", referencedColumnName = "idDrug")}, inverseJoinColumns = {
    @JoinColumn(name = "idUser", referencedColumnName = "idUser")})
    @ManyToMany
    @JsonIgnore
    private List<User> userList;
    @JoinTable(name = "treatmentdrug", joinColumns = {
        @JoinColumn(name = "drugId", referencedColumnName = "idDrug")}, inverseJoinColumns = {
        @JoinColumn(name = "treatmentId", referencedColumnName = "IdTreatment")})
    @ManyToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Treatment> treatmentList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "drugId")
    @JsonIgnore
    private List<CommercialName> commercialnameList;
    @JoinColumn(name = "drugPresentationId", referencedColumnName = "drugPresentationId")
    @ManyToOne
    private DrugPresentation drugPresentationId;
    @JoinColumn(name = "doseCalculationCriteriaId", referencedColumnName = "idDoseCalculationCriteria")
    @ManyToOne
    private DoseCalculationCriteria doseCalculationCriteriaId;
    @JoinColumn(name = "applicationMethodId", referencedColumnName = "idApplicationMethod")
    @ManyToOne
    private ApplicationMethod applicationMethodId;
    @JoinColumn(name = "administrationUnitId", referencedColumnName = "idAdministrationUnit")
    @ManyToOne
    private AdministrationUnit administrationUnitId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idDrug")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<DrugDose> drugDoseList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "drugId")
    @JsonIgnore
    private List<CommercialName> incompatibleCommercialNameList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "drug")
    @JsonIgnore
    private List<Drugrisk> drugriskList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "drug1")
    @JsonIgnore
    private List<Drugrisk> drugriskList1;

    public Drug() {
    }

    public Drug(Integer idDrug) {
        this.idDrug = idDrug;
    }

    public Drug(Integer idDrug, String drug) {
        this.idDrug = idDrug;
        this.drug = drug;
    }

    public Drug(Integer idDrug, String drug, Double concentration, DrugPresentation drugPresentationId, DoseCalculationCriteria doseCalculationCriteriaId, AdministrationUnit administrationUnitId) {
        this.idDrug = idDrug;
        this.drug = drug;
        this.concentration = concentration;
        this.drugPresentationId = drugPresentationId;
        this.doseCalculationCriteriaId = doseCalculationCriteriaId;
        this.administrationUnitId = administrationUnitId;
    }

    public Drug(Integer idDrug, String drug, Double concentration, Integer treatmentDays, String applicationSchedule, Integer dailyFrequency, String notes, Short active, DrugPresentation drugPresentationId, DoseCalculationCriteria doseCalculationCriteriaId, ApplicationMethod applicationMethodId, AdministrationUnit administrationUnitId) {
        this.idDrug = idDrug;
        this.drug = drug;
        this.concentration = concentration;
        this.treatmentDays = treatmentDays;
        this.applicationSchedule = applicationSchedule;
        this.dailyFrequency = dailyFrequency;
        this.notes = notes;
        this.active = active;
        this.drugPresentationId = drugPresentationId;
        this.doseCalculationCriteriaId = doseCalculationCriteriaId;
        this.applicationMethodId = applicationMethodId;
        this.administrationUnitId = administrationUnitId;
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

    public List<CommercialName> getCommercialnameList1() {
        return incompatibleCommercialNameList;
    }

    public void setCommercialnameList1(List<CommercialName> commercialnameList1) {
        this.incompatibleCommercialNameList = commercialnameList1;
    }

    public void setConcentration(Double concentration) {
        this.concentration = concentration;
    }

    public List<Drugrisk> getDrugriskList() {
        return drugriskList;
    }

    public void setDrugriskList(List<Drugrisk> drugriskList) {
        this.drugriskList = drugriskList;
    }

    public List<Drugrisk> getDrugriskList1() {
        return drugriskList1;
    }

    public void setDrugriskList1(List<Drugrisk> drugriskList1) {
        this.drugriskList1 = drugriskList1;
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

    public Short getActive() {
        return active;
    }

    public void setActive(Short active) {
        this.active = active;
    }

    public List<CommercialName> getIncompatibleCommercialNameList() {
        return incompatibleCommercialNameList;
    }

    public void setIncompatibleCommercialNameList(List<CommercialName> incompatibleCommercialNameList) {
        this.incompatibleCommercialNameList = incompatibleCommercialNameList;
    }

    @XmlTransient
    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    @XmlTransient
    public List<Treatment> getTreatmentList() {
        return treatmentList;
    }

    public void setTreatmentList(List<Treatment> treatmentList) {
        this.treatmentList = treatmentList;
    }

    @XmlTransient
    public List<CommercialName> getCommercialnameList() {
        return commercialnameList;
    }

    public void setCommercialnameList(List<CommercialName> commercialnameList) {
        this.commercialnameList = commercialnameList;
    }

    public DrugPresentation getDrugPresentationId() {
        return drugPresentationId;
    }

    public void setDrugPresentationId(DrugPresentation drugPresentationId) {
        this.drugPresentationId = drugPresentationId;
    }

    public DoseCalculationCriteria getDoseCalculationCriteriaId() {
        return doseCalculationCriteriaId;
    }

    public void setDoseCalculationCriteriaId(DoseCalculationCriteria doseCalculationCriteriaId) {
        this.doseCalculationCriteriaId = doseCalculationCriteriaId;
    }

    public ApplicationMethod getApplicationMethodId() {
        return applicationMethodId;
    }

    public void setApplicationMethodId(ApplicationMethod applicationMethodId) {
        this.applicationMethodId = applicationMethodId;
    }

    public AdministrationUnit getAdministrationUnitId() {
        return administrationUnitId;
    }

    public void setAdministrationUnitId(AdministrationUnit administrationUnitId) {
        this.administrationUnitId = administrationUnitId;
    }

    public List<DrugDose> getDrugDoseList() {
        return drugDoseList;
    }

    public void setDrugDoseList(List<DrugDose> drugDoseList) {
        this.drugDoseList = drugDoseList;
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
        return "pruebas1.Drug[ idDrug=" + idDrug + " ]";
    }
    
}
