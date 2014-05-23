/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
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
 * @author Ccortina_Mac
 */
@Entity
@Table(name = "consultationDiagnostic")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ConsultationDiagnostic.findAll", query = "SELECT c FROM ConsultationDiagnostic c"),
    @NamedQuery(name = "ConsultationDiagnostic.findByIdConsultation", query = "SELECT c FROM ConsultationDiagnostic c WHERE c.consultationDiagnosticPK.idConsultation = :idConsultation"),
    @NamedQuery(name = "ConsultationDiagnostic.findByIdDiagnostic", query = "SELECT c FROM ConsultationDiagnostic c WHERE c.consultationDiagnosticPK.idDiagnostic = :idDiagnostic")})
public class ConsultationDiagnostic implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ConsultationDiagnosticPK consultationDiagnosticPK;
    @JoinColumn(name = "idDiagnostic", referencedColumnName = "idDiagnostic", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Diagnostic diagnostic;

    public ConsultationDiagnostic() {
    }

    public ConsultationDiagnostic(ConsultationDiagnosticPK consultationDiagnosticPK) {
        this.consultationDiagnosticPK = consultationDiagnosticPK;
    }

    public ConsultationDiagnostic(int idConsultation, int idDiagnostic) {
        this.consultationDiagnosticPK = new ConsultationDiagnosticPK(idConsultation, idDiagnostic);
    }

    public ConsultationDiagnosticPK getConsultationDiagnosticPK() {
        return consultationDiagnosticPK;
    }

    public void setConsultationDiagnosticPK(ConsultationDiagnosticPK consultationDiagnosticPK) {
        this.consultationDiagnosticPK = consultationDiagnosticPK;
    }

    public Diagnostic getDiagnostic() {
        return diagnostic;
    }

    public void setDiagnostic(Diagnostic diagnostic) {
        this.diagnostic = diagnostic;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (consultationDiagnosticPK != null ? consultationDiagnosticPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ConsultationDiagnostic)) {
            return false;
        }
        ConsultationDiagnostic other = (ConsultationDiagnostic) object;
        if ((this.consultationDiagnosticPK == null && other.consultationDiagnosticPK != null) || (this.consultationDiagnosticPK != null && !this.consultationDiagnosticPK.equals(other.consultationDiagnosticPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pruebas1.ConsultationDiagnostic[ consultationDiagnosticPK=" + consultationDiagnosticPK + " ]";
    }
    
}
