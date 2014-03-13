package com.carloscortina.demo.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author carloscortina
 */
@Embeddable
public class DiagnosticPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "idConsultation")
    private int idConsultation;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idPatient")
    private int idPatient;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idCIE10")
    private int idCIE10;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idTreatment")
    private int idTreatment;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idMedecine")
    private int idMedecine;
    @Basic(optional = false)
    @NotNull
    @Column(name = "IdCommercialName")
    private int idCommercialName;

    public DiagnosticPK() {
    }

    public DiagnosticPK(int idConsultation, int idPatient, int idCIE10, int idTreatment, int idMedecine, int idCommercialName) {
        this.idConsultation = idConsultation;
        this.idPatient = idPatient;
        this.idCIE10 = idCIE10;
        this.idTreatment = idTreatment;
        this.idMedecine = idMedecine;
        this.idCommercialName = idCommercialName;
    }

    public int getIdConsultation() {
        return idConsultation;
    }

    public void setIdConsultation(int idConsultation) {
        this.idConsultation = idConsultation;
    }

    public int getIdPatient() {
        return idPatient;
    }

    public void setIdPatient(int idPatient) {
        this.idPatient = idPatient;
    }

    public int getIdCIE10() {
        return idCIE10;
    }

    public void setIdCIE10(int idCIE10) {
        this.idCIE10 = idCIE10;
    }

    public int getIdTreatment() {
        return idTreatment;
    }

    public void setIdTreatment(int idTreatment) {
        this.idTreatment = idTreatment;
    }

    public int getIdMedecine() {
        return idMedecine;
    }

    public void setIdMedecine(int idMedecine) {
        this.idMedecine = idMedecine;
    }

    public int getIdCommercialName() {
        return idCommercialName;
    }

    public void setIdCommercialName(int idCommercialName) {
        this.idCommercialName = idCommercialName;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idConsultation;
        hash += (int) idPatient;
        hash += (int) idCIE10;
        hash += (int) idTreatment;
        hash += (int) idMedecine;
        hash += (int) idCommercialName;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DiagnosticPK)) {
            return false;
        }
        DiagnosticPK other = (DiagnosticPK) object;
        if (this.idConsultation != other.idConsultation) {
            return false;
        }
        if (this.idPatient != other.idPatient) {
            return false;
        }
        if (this.idCIE10 != other.idCIE10) {
            return false;
        }
        if (this.idTreatment != other.idTreatment) {
            return false;
        }
        if (this.idMedecine != other.idMedecine) {
            return false;
        }
        if (this.idCommercialName != other.idCommercialName) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "diagnostic.DiagnosticPK[ idConsultation=" + idConsultation + ", idPatient=" + idPatient + ", idCIE10=" + idCIE10 + ", idTreatment=" + idTreatment + ", idMedecine=" + idMedecine + ", idCommercialName=" + idCommercialName + " ]";
    }
    
}

