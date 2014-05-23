package com.carloscortina.demo.model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
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
    @NamedQuery(name = "Diagnostic.findByIdDiagnostic", query = "SELECT d FROM Diagnostic d WHERE d.idDiagnostic = :idDiagnostic"),
    @NamedQuery(name = "Diagnostic.findByIdCommercialName", query = "SELECT d FROM Diagnostic d WHERE d.idCommercialName = :idCommercialName")})
public class Diagnostic implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idDiagnostic")
    private Integer idDiagnostic;
    @Basic(optional = false)
    @Column(name = "IdCommercialName")
    private int idCommercialName;
    @JoinColumn(name = "idTreatment", referencedColumnName = "IdTreatment")
    @ManyToOne(optional = false)
    private Treatment idTreatment;
    @JoinColumn(name = "idPatient", referencedColumnName = "idPatient")
    @ManyToOne(optional = false)
    private Patient idPatient;
    @JoinColumn(name = "idMedecine", referencedColumnName = "idDrug")
    @ManyToOne(optional = false)
    private Drug idMedecine;
    @JoinColumn(name = "idCIE10", referencedColumnName = "idCIE10")
    @ManyToOne(optional = false)
    private Cie10 idCIE10;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "diagnostic")
    private Collection<ConsultationDiagnostic> consultationDiagnosticCollection;

    public Diagnostic() {
    }

    public Diagnostic(Integer idDiagnostic) {
        this.idDiagnostic = idDiagnostic;
    }

    public Diagnostic(Integer idDiagnostic, int idCommercialName, Treatment idTreatment, Patient idPatient, Drug idMedecine, Cie10 idCIE10, Collection<ConsultationDiagnostic> consultationDiagnosticCollection) {
        this.idDiagnostic = idDiagnostic;
        this.idCommercialName = idCommercialName;
        this.idTreatment = idTreatment;
        this.idPatient = idPatient;
        this.idMedecine = idMedecine;
        this.idCIE10 = idCIE10;
        this.consultationDiagnosticCollection = consultationDiagnosticCollection;
    }

    public Integer getIdDiagnostic() {
        return idDiagnostic;
    }

    public void setIdDiagnostic(Integer idDiagnostic) {
        this.idDiagnostic = idDiagnostic;
    }

    public int getIdCommercialName() {
        return idCommercialName;
    }

    public void setIdCommercialName(int idCommercialName) {
        this.idCommercialName = idCommercialName;
    }

    public Treatment getIdTreatment() {
        return idTreatment;
    }

    public void setIdTreatment(Treatment idTreatment) {
        this.idTreatment = idTreatment;
    }

    public Patient getIdPatient() {
        return idPatient;
    }

    public void setIdPatient(Patient idPatient) {
        this.idPatient = idPatient;
    }

    public Drug getIdMedecine() {
        return idMedecine;
    }

    public void setIdMedecine(Drug idMedecine) {
        this.idMedecine = idMedecine;
    }

    public Cie10 getIdCIE10() {
        return idCIE10;
    }

    public void setIdCIE10(Cie10 idCIE10) {
        this.idCIE10 = idCIE10;
    }

    public Collection<ConsultationDiagnostic> getConsultationDiagnosticCollection() {
        return consultationDiagnosticCollection;
    }

    public void setConsultationDiagnosticCollection(Collection<ConsultationDiagnostic> consultationDiagnosticCollection) {
        this.consultationDiagnosticCollection = consultationDiagnosticCollection;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + (this.idDiagnostic != null ? this.idDiagnostic.hashCode() : 0);
        hash = 37 * hash + this.idCommercialName;
        hash = 37 * hash + (this.idTreatment != null ? this.idTreatment.hashCode() : 0);
        hash = 37 * hash + (this.idPatient != null ? this.idPatient.hashCode() : 0);
        hash = 37 * hash + (this.idMedecine != null ? this.idMedecine.hashCode() : 0);
        hash = 37 * hash + (this.idCIE10 != null ? this.idCIE10.hashCode() : 0);
        hash = 37 * hash + (this.consultationDiagnosticCollection != null ? this.consultationDiagnosticCollection.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Diagnostic other = (Diagnostic) obj;
        if (this.idDiagnostic != other.idDiagnostic && (this.idDiagnostic == null || !this.idDiagnostic.equals(other.idDiagnostic))) {
            return false;
        }
        if (this.idCommercialName != other.idCommercialName) {
            return false;
        }
        if (this.idTreatment != other.idTreatment && (this.idTreatment == null || !this.idTreatment.equals(other.idTreatment))) {
            return false;
        }
        if (this.idPatient != other.idPatient && (this.idPatient == null || !this.idPatient.equals(other.idPatient))) {
            return false;
        }
        if (this.idMedecine != other.idMedecine && (this.idMedecine == null || !this.idMedecine.equals(other.idMedecine))) {
            return false;
        }
        if (this.idCIE10 != other.idCIE10 && (this.idCIE10 == null || !this.idCIE10.equals(other.idCIE10))) {
            return false;
        }
        if (this.consultationDiagnosticCollection != other.consultationDiagnosticCollection && (this.consultationDiagnosticCollection == null || !this.consultationDiagnosticCollection.equals(other.consultationDiagnosticCollection))) {
            return false;
        }
        return true;
    }

    
    
}
