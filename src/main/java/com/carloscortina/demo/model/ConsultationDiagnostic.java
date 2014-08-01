/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.carloscortina.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
 * @author Ccortina_Mac
 */
@Entity
@Table(name = "consultationDiagnostic")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ConsultationDiagnostic.findAll", query = "SELECT c FROM ConsultationDiagnostic c")})
public class ConsultationDiagnostic implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ConsultationdiagnosticPK consultationdiagnosticPK;
    @JoinColumn(name = "idDiagnostic", referencedColumnName = "idDiagnostic", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Diagnostic idDiagnostic;
    @JsonIgnore
    @JoinColumn(name = "idConsultation", referencedColumnName = "idConsultation", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Consultation consultation;

    public ConsultationDiagnostic() {
    }

    public ConsultationDiagnostic(ConsultationdiagnosticPK consultationdiagnosticPK) {
        this.consultationdiagnosticPK = consultationdiagnosticPK;
    }

    public ConsultationDiagnostic(Diagnostic idDiagnostic, Consultation consultation) {
        this.idDiagnostic = idDiagnostic;
        this.consultation = consultation;
    }

    public ConsultationdiagnosticPK getConsultationdiagnosticPK() {
        return consultationdiagnosticPK;
    }

    public void setConsultationdiagnosticPK(ConsultationdiagnosticPK consultationdiagnosticPK) {
        this.consultationdiagnosticPK = consultationdiagnosticPK;
    }
    
    public Diagnostic getIdDiagnostic() {
        return idDiagnostic;
    }

    public void setIdDiagnostic(Diagnostic idDiagnostic) {
        this.idDiagnostic = idDiagnostic;
    }

    public Consultation getConsultation() {
        return consultation;
    }

    public void setConsultation(Consultation consultation) {
        this.consultation = consultation;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 71 * hash + (this.consultationdiagnosticPK != null ? this.consultationdiagnosticPK.hashCode() : 0);
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
        final ConsultationDiagnostic other = (ConsultationDiagnostic) obj;
        if (this.consultationdiagnosticPK != other.consultationdiagnosticPK && (this.consultationdiagnosticPK == null || !this.consultationdiagnosticPK.equals(other.consultationdiagnosticPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ConsultationDiagnostic{" + "consultationdiagnosticPK=" + consultationdiagnosticPK + '}';
    }
    
}
