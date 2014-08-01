/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.carloscortina.demo.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
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
 * @author Carlos Cortina
 */
@Entity
@Table(name = "consultationmeasure")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Consultationmeasure.findAll", query = "SELECT c FROM Consultationmeasure c"),
    @NamedQuery(name = "Consultationmeasure.findByIdConsultation", query = "SELECT c FROM Consultationmeasure c WHERE c.consultationmeasurePK.idConsultation = :idConsultation"),
    @NamedQuery(name = "Consultationmeasure.findByIdMeasure", query = "SELECT c FROM Consultationmeasure c WHERE c.consultationmeasurePK.idMeasure = :idMeasure"),
    @NamedQuery(name = "Consultationmeasure.findByValue", query = "SELECT c FROM Consultationmeasure c WHERE c.value = :value")})
public class Consultationmeasure implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ConsultationmeasurePK consultationmeasurePK;
    @Basic(optional = false)
    @Column(name = "value")
    private String value;
    @JoinColumn(name = "idMeasure", referencedColumnName = "idMeasures", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Measures measures;
    @JoinColumn(name = "idConsultation", referencedColumnName = "idConsultation", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Consultation consultation;

    public Consultationmeasure() {
    }

    public Consultationmeasure(ConsultationmeasurePK consultationmeasurePK) {
        this.consultationmeasurePK = consultationmeasurePK;
    }

    public Consultationmeasure(ConsultationmeasurePK consultationmeasurePK, String value) {
        this.consultationmeasurePK = consultationmeasurePK;
        this.value = value;
    }

    public Consultationmeasure(int idConsultation, int idMeasure) {
        this.consultationmeasurePK = new ConsultationmeasurePK(idConsultation, idMeasure);
    }

    public Consultationmeasure(String value, Measures measures, Consultation consultation) {
        this.value = value;
        this.measures = measures;
        this.consultation = consultation;
    }

    public ConsultationmeasurePK getConsultationmeasurePK() {
        return consultationmeasurePK;
    }

    public void setConsultationmeasurePK(ConsultationmeasurePK consultationmeasurePK) {
        this.consultationmeasurePK = consultationmeasurePK;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Measures getMeasures() {
        return measures;
    }

    public void setMeasures(Measures measures) {
        this.measures = measures;
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
        hash += (consultationmeasurePK != null ? consultationmeasurePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Consultationmeasure)) {
            return false;
        }
        Consultationmeasure other = (Consultationmeasure) object;
        if ((this.consultationmeasurePK == null && other.consultationmeasurePK != null) || (this.consultationmeasurePK != null && !this.consultationmeasurePK.equals(other.consultationmeasurePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelos.Consultationmeasure[ consultationmeasurePK=" + consultationmeasurePK + " ]";
    }
    
}
