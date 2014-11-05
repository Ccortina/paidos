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
import javax.validation.constraints.NotNull;

/**
 *
 * @author Carlos Cortina
 */
@Embeddable
public class ConsultationmeasurePK implements Serializable {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idConsultation")
    private int idConsultation;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idMeasure")
    private int idMeasure;

    public ConsultationmeasurePK() {
    }

    public ConsultationmeasurePK(int idConsultation, int idMeasure) {
        this.idConsultation = idConsultation;
        this.idMeasure = idMeasure;
    }

    public int getIdConsultation() {
        return idConsultation;
    }

    public void setIdConsultation(int idConsultation) {
        this.idConsultation = idConsultation;
    }

    public int getIdMeasure() {
        return idMeasure;
    }

    public void setIdMeasure(int idMeasure) {
        this.idMeasure = idMeasure;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idConsultation;
        hash += (int) idMeasure;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ConsultationmeasurePK)) {
            return false;
        }
        ConsultationmeasurePK other = (ConsultationmeasurePK) object;
        if (this.idConsultation != other.idConsultation) {
            return false;
        }
        if (this.idMeasure != other.idMeasure) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.carloscortina.demo.model.ConsultationmeasurePK[ idConsultation=" + idConsultation + ", idMeasure=" + idMeasure + " ]";
    }
    
}
