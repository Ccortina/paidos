/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.carloscortina.demo.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Ccortina_Mac
 */
@Embeddable
public class ConsultationDiagnosticPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "idConsultation")
    private int idConsultation;
    @Basic(optional = false)
    @Column(name = "idDiagnostic")
    private int idDiagnostic;

    public ConsultationDiagnosticPK() {
    }

    public ConsultationDiagnosticPK(int idConsultation, int idDiagnostic) {
        this.idConsultation = idConsultation;
        this.idDiagnostic = idDiagnostic;
    }

    public int getIdConsultation() {
        return idConsultation;
    }

    public void setIdConsultation(int idConsultation) {
        this.idConsultation = idConsultation;
    }

    public int getIdDiagnostic() {
        return idDiagnostic;
    }

    public void setIdDiagnostic(int idDiagnostic) {
        this.idDiagnostic = idDiagnostic;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idConsultation;
        hash += (int) idDiagnostic;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ConsultationDiagnosticPK)) {
            return false;
        }
        ConsultationDiagnosticPK other = (ConsultationDiagnosticPK) object;
        if (this.idConsultation != other.idConsultation) {
            return false;
        }
        if (this.idDiagnostic != other.idDiagnostic) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pruebas1.ConsultationDiagnosticPK[ idConsultation=" + idConsultation + ", idDiagnostic=" + idDiagnostic + " ]";
    }
    
}
