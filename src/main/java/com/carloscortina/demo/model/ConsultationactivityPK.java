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
public class ConsultationactivityPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "idConsultation")
    private int idConsultation;
    @Basic(optional = false)
    @Column(name = "idActivity")
    private int idActivity;

    public ConsultationactivityPK() {
    }

    public ConsultationactivityPK(int idConsultation, int idActivity) {
        this.idConsultation = idConsultation;
        this.idActivity = idActivity;
    }

    public int getIdConsultation() {
        return idConsultation;
    }

    public void setIdConsultation(int idConsultation) {
        this.idConsultation = idConsultation;
    }

    public int getIdActivity() {
        return idActivity;
    }

    public void setIdActivity(int idActivity) {
        this.idActivity = idActivity;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idConsultation;
        hash += (int) idActivity;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ConsultationactivityPK)) {
            return false;
        }
        ConsultationactivityPK other = (ConsultationactivityPK) object;
        if (this.idConsultation != other.idConsultation) {
            return false;
        }
        if (this.idActivity != other.idActivity) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelos.ConsultationactivityPK[ idConsultation=" + idConsultation + ", idActivity=" + idActivity + " ]";
    }
    
}
