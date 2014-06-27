/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.carloscortina.demo.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ccortina_Mac
 */
@Entity
@Table(name = "measureConsultation")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MeasureConsultation.findAll", query = "SELECT m FROM MeasureConsultation m"),
    @NamedQuery(name = "MeasureConsultation.findByIdmeasureConsultation", query = "SELECT m FROM MeasureConsultation m WHERE m.idmeasureConsultation = :idmeasureConsultation"),
    @NamedQuery(name = "MeasureConsultation.findByValue", query = "SELECT m FROM MeasureConsultation m WHERE m.value = :value")})
public class MeasureConsultation implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idmeasureConsultation")
    private Integer idmeasureConsultation;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "value")
    private String value;
    @JoinColumn(name = "idMeasure", referencedColumnName = "idMeasures")
    @ManyToOne(optional = false)
    private Measures idMeasure;
    @JoinColumn(name = "idConsultation", referencedColumnName = "idConsultation")
    @ManyToOne(optional = false)
    private Consultation idConsultation;

    public MeasureConsultation() {
    }

    public MeasureConsultation(Integer idmeasureConsultation) {
        this.idmeasureConsultation = idmeasureConsultation;
    }

    public MeasureConsultation(Integer idmeasureConsultation, String value) {
        this.idmeasureConsultation = idmeasureConsultation;
        this.value = value;
    }

    public MeasureConsultation(String value, Measures idMeasure, Consultation idConsultation) {
        this.value = value;
        this.idMeasure = idMeasure;
        this.idConsultation = idConsultation;
    }
    
    

    public Integer getIdmeasureConsultation() {
        return idmeasureConsultation;
    }

    public void setIdmeasureConsultation(Integer idmeasureConsultation) {
        this.idmeasureConsultation = idmeasureConsultation;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Measures getIdMeasure() {
        return idMeasure;
    }

    public void setIdMeasure(Measures idMeasure) {
        this.idMeasure = idMeasure;
    }

    public Consultation getIdConsultation() {
        return idConsultation;
    }

    public void setIdConsultation(Consultation idConsultation) {
        this.idConsultation = idConsultation;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idmeasureConsultation != null ? idmeasureConsultation.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MeasureConsultation)) {
            return false;
        }
        MeasureConsultation other = (MeasureConsultation) object;
        if ((this.idmeasureConsultation == null && other.idmeasureConsultation != null) || (this.idmeasureConsultation != null && !this.idmeasureConsultation.equals(other.idmeasureConsultation))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.carloscortina.demo.model.MeasureConsultation[ idmeasureConsultation=" + idmeasureConsultation + " ]";
    }
    
}
