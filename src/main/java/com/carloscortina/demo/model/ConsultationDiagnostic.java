/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.carloscortina.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @NamedQuery(name = "ConsultationDiagnostic.findByIdConsultationDiagnostic", query = "SELECT c FROM ConsultationDiagnostic c WHERE c.idConsultationDiagnostic = :idConsultationDiagnostic")})
public class ConsultationDiagnostic implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idConsultationDiagnostic")
    private Integer idConsultationDiagnostic;
    @JoinColumn(name = "idDiagnostic", referencedColumnName = "idDiagnostic")
    @ManyToOne(optional = false)
    private Diagnostic idDiagnostic;
    @JoinColumn(name = "idConsultation", referencedColumnName = "idConsultation")
    @ManyToOne(optional = false)
    @JsonIgnore
    private Consultation idConsultation;

    public ConsultationDiagnostic() {
    }

    public ConsultationDiagnostic(Integer idConsultationDiagnostic) {
        this.idConsultationDiagnostic = idConsultationDiagnostic;
    }

    public Integer getIdConsultationDiagnostic() {
        return idConsultationDiagnostic;
    }

    public void setIdConsultationDiagnostic(Integer idConsultationDiagnostic) {
        this.idConsultationDiagnostic = idConsultationDiagnostic;
    }

    public Diagnostic getIdDiagnostic() {
        return idDiagnostic;
    }

    public void setIdDiagnostic(Diagnostic idDiagnostic) {
        this.idDiagnostic = idDiagnostic;
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
        hash += (idConsultationDiagnostic != null ? idConsultationDiagnostic.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ConsultationDiagnostic)) {
            return false;
        }
        ConsultationDiagnostic other = (ConsultationDiagnostic) object;
        if ((this.idConsultationDiagnostic == null && other.idConsultationDiagnostic != null) || (this.idConsultationDiagnostic != null && !this.idConsultationDiagnostic.equals(other.idConsultationDiagnostic))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pruebas1.ConsultationDiagnostic[ idConsultationDiagnostic=" + idConsultationDiagnostic + " ]";
    }
    
}
