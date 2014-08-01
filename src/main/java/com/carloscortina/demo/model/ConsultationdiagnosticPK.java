/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.carloscortina.demo.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Carlos Cortina
 */
@Embeddable
public class ConsultationdiagnosticPK implements Serializable{
    @Basic(optional = false)
    @Column(name = "idConsultation")
    private int idConsultation;
    @Basic(optional = false)
    @Column(name = "idDiagnostic")
    private int idDiagnostic;

    public ConsultationdiagnosticPK() {
    }

    public ConsultationdiagnosticPK(int idConsultation, int idDiagnostic) {
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
}
