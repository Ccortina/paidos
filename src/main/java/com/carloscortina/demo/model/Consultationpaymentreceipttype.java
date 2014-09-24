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
@Table(name = "consultationpaymentreceipttype")
@NamedQueries({
    @NamedQuery(name = "Consultationpaymentreceipttype.findAll", query = "SELECT c FROM Consultationpaymentreceipttype c")})
public class Consultationpaymentreceipttype implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idConsultationPaymentReceipttype")
    private Integer idConsultationPaymentReceipttype;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "type")
    private String type;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idConsultatioPaymentReceiptType")
    private List<Consultationpaymentreceipt> consultationpaymentreceiptList;

    public Consultationpaymentreceipttype() {
    }

    public Consultationpaymentreceipttype(Integer idConsultationPaymentReceipttype) {
        this.idConsultationPaymentReceipttype = idConsultationPaymentReceipttype;
    }

    public Consultationpaymentreceipttype(Integer idConsultationPaymentReceipttype, String type) {
        this.idConsultationPaymentReceipttype = idConsultationPaymentReceipttype;
        this.type = type;
    }

    public Integer getIdConsultationPaymentReceipttype() {
        return idConsultationPaymentReceipttype;
    }

    public void setIdConsultationPaymentReceipttype(Integer idConsultationPaymentReceipttype) {
        this.idConsultationPaymentReceipttype = idConsultationPaymentReceipttype;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Consultationpaymentreceipt> getConsultationpaymentreceiptList() {
        return consultationpaymentreceiptList;
    }

    public void setConsultationpaymentreceiptList(List<Consultationpaymentreceipt> consultationpaymentreceiptList) {
        this.consultationpaymentreceiptList = consultationpaymentreceiptList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idConsultationPaymentReceipttype != null ? idConsultationPaymentReceipttype.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Consultationpaymentreceipttype)) {
            return false;
        }
        Consultationpaymentreceipttype other = (Consultationpaymentreceipttype) object;
        if ((this.idConsultationPaymentReceipttype == null && other.idConsultationPaymentReceipttype != null) || (this.idConsultationPaymentReceipttype != null && !this.idConsultationPaymentReceipttype.equals(other.idConsultationPaymentReceipttype))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.carloscortina.demo.model.Consultationpaymentreceipttype[ idConsultationPaymentReceipttype=" + idConsultationPaymentReceipttype + " ]";
    }
    
}
