package com.carloscortina.demo.model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author carloscortina
 */
@Entity
@Table(name = "Drug")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Drug.findAll", query = "SELECT d FROM Drug d"),
    @NamedQuery(name = "Drug.findByIdDrug", query = "SELECT d FROM Drug d WHERE d.idDrug = :idDrug"),
    @NamedQuery(name = "Drug.findByDrug", query = "SELECT d FROM Drug d WHERE d.drug = :drug"),
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
    @Size(max = 45)
    @Column(name = "drug")
    private String drug;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "concentration")
    private Double concentration;
    @Column(name = "treatmentDays")
    private Integer treatmentDays;
    @Lob
    @Size(max = 65535)
    @Column(name = "applicationSchedule")
    private String applicationSchedule;
    @Column(name = "dailyFrequency")
    private Integer dailyFrequency;
    @Lob
    @Size(max = 65535)
    @Column(name = "notes")
    private String notes;
    @Column(name = "active")
    private Short active;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "drug")
    private Collection<Diagnostic> diagnosticCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "drugId")
    private Collection<CommercialName> commercialNameCollection;
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

    public Drug() {
    }

    public Drug(Integer idDrug) {
        this.idDrug = idDrug;
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

    public Short getActive() {
        return active;
    }

    public void setActive(Short active) {
        this.active = active;
    }

    @XmlTransient
    public Collection<Diagnostic> getDiagnosticCollection() {
        return diagnosticCollection;
    }

    public void setDiagnosticCollection(Collection<Diagnostic> diagnosticCollection) {
        this.diagnosticCollection = diagnosticCollection;
    }

    @XmlTransient
    public Collection<CommercialName> getCommercialNameCollection() {
        return commercialNameCollection;
    }

    public void setCommercialNameCollection(Collection<CommercialName> commercialNameCollection) {
        this.commercialNameCollection = commercialNameCollection;
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
        return "diagnostic.Drug[ idDrug=" + idDrug + " ]";
    }
    
}