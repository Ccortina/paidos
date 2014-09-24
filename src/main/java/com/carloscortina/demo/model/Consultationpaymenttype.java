/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.carloscortina.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Carlos Cortina
 */
@Entity
@Table(name = "consultationpaymenttype")
@NamedQueries({
    @NamedQuery(name = "Consultationpaymenttype.findAll", query = "SELECT c FROM Consultationpaymenttype c")})
public class Consultationpaymenttype implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idConsultationPaymentType")
    private Integer idConsultationPaymentType;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "type")
    private String type;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPaymentType")
    private List<Consultationpayment> consultationpaymentList;

    public Consultationpaymenttype() {
    }

    public Consultationpaymenttype(Integer idConsultationPaymentType) {
        this.idConsultationPaymentType = idConsultationPaymentType;
    }

    public Consultationpaymenttype(Integer idConsultationPaymentType, String type) {
        this.idConsultationPaymentType = idConsultationPaymentType;
        this.type = type;
    }

    public Integer getIdConsultationPaymentType() {
        return idConsultationPaymentType;
    }

    public void setIdConsultationPaymentType(Integer idConsultationPaymentType) {
        this.idConsultationPaymentType = idConsultationPaymentType;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Consultationpayment> getConsultationpaymentList() {
        return consultationpaymentList;
    }

    public void setConsultationpaymentList(List<Consultationpayment> consultationpaymentList) {
        this.consultationpaymentList = consultationpaymentList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idConsultationPaymentType != null ? idConsultationPaymentType.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Consultationpaymenttype)) {
            return false;
        }
        Consultationpaymenttype other = (Consultationpaymenttype) object;
        if ((this.idConsultationPaymentType == null && other.idConsultationPaymentType != null) || (this.idConsultationPaymentType != null && !this.idConsultationPaymentType.equals(other.idConsultationPaymentType))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.carloscortina.demo.model.Consultationpaymenttype[ idConsultationPaymentType=" + idConsultationPaymentType + " ]";
    }
    
}
