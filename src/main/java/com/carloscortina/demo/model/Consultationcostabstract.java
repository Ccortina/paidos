/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.carloscortina.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

/**
 *
 * @author Carlos Cortina
 */
@Entity
@Table(name = "consultationcostabstract")
@NamedQueries({
    @NamedQuery(name = "Consultationcostabstract.findAll", query = "SELECT c FROM Consultationcostabstract c")})
public class Consultationcostabstract implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idConsultationCostAbstract")
    private Integer idConsultationCostAbstract;
    @Basic(optional = false)
    @NotNull
    @Column(name = "total")
    private double total;
    @Basic(optional = false)
    @NotNull
    @Column(name = "rest")
    private double rest;
    @Size(max = 65535)
    @Column(name = "concept")
    private String concept;
    @Size(max = 45)
    @Column(name = "tempClaveConsulta")
    private String tempClaveConsulta;
    @JoinColumn(name = "idConsultationPaymentStatus", referencedColumnName = "idConsultationPaymentEstatus")
    @ManyToOne(optional = false)
    private Consultationpaymentestatus idConsultationPaymentStatus;
    @JsonIgnore
    @JoinColumn(name = "idConsultation", referencedColumnName = "idConsultation")
    @ManyToOne
    private Consultation idConsultation;
    @JsonIgnore
    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idConsultationCostAbstract")
    private List<Consultationpayment> consultationpaymentList;
    //Auxiliar Fields not originally from the DataBase
    @Transient
    private Date consultationDate;
    @Transient
    private Patient consultationPatient;
    @Transient
    private List<Consultationactivity> activities;

    public Consultationcostabstract() {
    }

    public Consultationcostabstract(Integer idConsultationCostAbstract) {
        this.idConsultationCostAbstract = idConsultationCostAbstract;
    }

    public Consultationcostabstract(Integer idConsultationCostAbstract, double total, double rest) {
        this.idConsultationCostAbstract = idConsultationCostAbstract;
        this.total = total;
        this.rest = rest;
    }

    public Consultationcostabstract(double total, double rest, String concept, Consultationpaymentestatus idConsultationPaymentStatus, Consultation idConsultation) {
        this.total = total;
        this.rest = rest;
        this.concept = concept;
        this.idConsultationPaymentStatus = idConsultationPaymentStatus;
        this.idConsultation = idConsultation;
    }
    
    public Consultationcostabstract(double total, double rest, String concept, Consultationpaymentestatus idConsultationPaymentStatus, Consultation idConsultation,List<Consultationactivity> activities) {
        this.total = total;
        this.rest = rest;
        this.concept = concept;
        this.idConsultationPaymentStatus = idConsultationPaymentStatus;
        this.idConsultation = idConsultation;
        this.activities = activities;
    }

    public Integer getIdConsultationCostAbstract() {
        return idConsultationCostAbstract;
    }

    public void setIdConsultationCostAbstract(Integer idConsultationCostAbstract) {
        this.idConsultationCostAbstract = idConsultationCostAbstract;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getRest() {
        return rest;
    }

    public void setRest(double rest) {
        this.rest = rest;
    }

    public String getConcept() {
        return concept;
    }

    public void setConcept(String concept) {
        this.concept = concept;
    }

    public String getTempClaveConsulta() {
        return tempClaveConsulta;
    }

    public void setTempClaveConsulta(String tempClaveConsulta) {
        this.tempClaveConsulta = tempClaveConsulta;
    }

    public Consultationpaymentestatus getIdConsultationPaymentStatus() {
        return idConsultationPaymentStatus;
    }

    public void setIdConsultationPaymentStatus(Consultationpaymentestatus idConsultationPaymentStatus) {
        this.idConsultationPaymentStatus = idConsultationPaymentStatus;
    }

    public Consultation getIdConsultation() {
        return idConsultation;
    }

    public void setIdConsultation(Consultation idConsultation) {
        this.idConsultation = idConsultation;
    }

    public List<Consultationpayment> getConsultationpaymentList() {
        return consultationpaymentList;
    }

    public void setConsultationpaymentList(List<Consultationpayment> consultationpaymentList) {
        this.consultationpaymentList = consultationpaymentList;
    }

    public Date getConsultationDate() {
        return consultationDate;
    }

    public void setConsultationDate(Date consultationDate) {
        this.consultationDate = consultationDate;
    }

    public Patient getConsultationPatient() {
        return consultationPatient;
    }

    public void setConsultationPatient(Patient consultationPatient) {
        this.consultationPatient = consultationPatient;
    }

    public List<Consultationactivity> getActivities() {
        return activities;
    }

    public void setActivities(List<Consultationactivity> activities) {
        this.activities = activities;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idConsultationCostAbstract != null ? idConsultationCostAbstract.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Consultationcostabstract)) {
            return false;
        }
        Consultationcostabstract other = (Consultationcostabstract) object;
        if ((this.idConsultationCostAbstract == null && other.idConsultationCostAbstract != null) || (this.idConsultationCostAbstract != null && !this.idConsultationCostAbstract.equals(other.idConsultationCostAbstract))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.carloscortina.demo.model.Consultationcostabstract[ idConsultationCostAbstract=" + idConsultationCostAbstract + " ]";
    }
    
}
