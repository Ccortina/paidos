package com.carloscortina.demo.model;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author carloscortina
 */
@Entity
@Table(name = "diagnostic")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Diagnostic.findAll", query = "SELECT d FROM Diagnostic d"),
    @NamedQuery(name = "Diagnostic.findByIdConsultation", query = "SELECT d FROM Diagnostic d WHERE d.diagnosticPK.idConsultation = :idConsultation"),
    @NamedQuery(name = "Diagnostic.findByIdPatient", query = "SELECT d FROM Diagnostic d WHERE d.diagnosticPK.idPatient = :idPatient"),
    @NamedQuery(name = "Diagnostic.findByIdCIE10", query = "SELECT d FROM Diagnostic d WHERE d.diagnosticPK.idCIE10 = :idCIE10"),
    @NamedQuery(name = "Diagnostic.findByIdTreatment", query = "SELECT d FROM Diagnostic d WHERE d.diagnosticPK.idTreatment = :idTreatment"),
    @NamedQuery(name = "Diagnostic.findByIdMedecine", query = "SELECT d FROM Diagnostic d WHERE d.diagnosticPK.idMedecine = :idMedecine"),
    @NamedQuery(name = "Diagnostic.findByIdCommercialName", query = "SELECT d FROM Diagnostic d WHERE d.diagnosticPK.idCommercialName = :idCommercialName")})
public class Diagnostic implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected DiagnosticPK diagnosticPK;
    @JoinColumn(name = "IdCommercialName", referencedColumnName = "idcommercialName", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private CommercialName commercialName;
    @JoinColumn(name = "idMedecine", referencedColumnName = "idDrug", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Drug drug;
    @JoinColumn(name = "idTreatment", referencedColumnName = "IdTreatment", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Treatment treatment;
    @JoinColumn(name = "idCIE10", referencedColumnName = "idCIE10", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Cie10 cie10;
    @JoinColumn(name = "idPatient", referencedColumnName = "idPatient", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Patient patient;
    @JoinColumn(name = "idConsultation", referencedColumnName = "idConsultation", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Consultation consultation;

    public Diagnostic() {
    }

    public Diagnostic(DiagnosticPK diagnosticPK) {
        this.diagnosticPK = diagnosticPK;
    }

    public Diagnostic(int idConsultation, int idPatient, int idCIE10, int idTreatment, int idMedecine, int idCommercialName) {
        this.diagnosticPK = new DiagnosticPK(idConsultation, idPatient, idCIE10, idTreatment, idMedecine, idCommercialName);
    }

    public DiagnosticPK getDiagnosticPK() {
        return diagnosticPK;
    }

    public void setDiagnosticPK(DiagnosticPK diagnosticPK) {
        this.diagnosticPK = diagnosticPK;
    }

    public CommercialName getCommercialName() {
        return commercialName;
    }

    public void setCommercialName(CommercialName commercialName) {
        this.commercialName = commercialName;
    }

    public Drug getDrug() {
        return drug;
    }

    public void setDrug(Drug drug) {
        this.drug = drug;
    }

    public Treatment getTreatment() {
        return treatment;
    }

    public void setTreatment(Treatment treatment) {
        this.treatment = treatment;
    }

    public Cie10 getCie10() {
        return cie10;
    }

    public void setCie10(Cie10 cie10) {
        this.cie10 = cie10;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Consultation getConsultation() {
        return consultation;
    }

    public void setConsultation(Consultation consultation) {
        this.consultation = consultation;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (diagnosticPK != null ? diagnosticPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Diagnostic)) {
            return false;
        }
        Diagnostic other = (Diagnostic) object;
        if ((this.diagnosticPK == null && other.diagnosticPK != null) || (this.diagnosticPK != null && !this.diagnosticPK.equals(other.diagnosticPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "diagnostic.Diagnostic[ diagnosticPK=" + diagnosticPK + " ]";
    }
    
}
