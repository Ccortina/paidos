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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Carlos Cortina
 */
@Entity
@Table(name = "consultationpayment")
@NamedQueries({
    @NamedQuery(name = "Consultationpayment.findAll", query = "SELECT c FROM Consultationpayment c")})
public class Consultationpayment implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idConsultationPayment")
    private Integer idConsultationPayment;
    @Basic(optional = false)
    @NotNull
    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    private Date date;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "cash")
    private Double cash;
    @Column(name = "check")
    private Double check;
    @Size(max = 45)
    @Column(name = "checkDigits")
    private String checkDigits;
    @Column(name = "card")
    private Double card;
    @Size(max = 45)
    @Column(name = "cardDigits")
    private String cardDigits;
    @Column(name = "Other")
    private Double other;
    @Size(max = 45)
    @Column(name = "otherDescription")
    private String otherDescription;
    @Column(name = "paymentTotal")
    private Double paymentTotal;
    @Column(name = "change")
    private Double change;
    @Size(max = 255)
    @Column(name = "note")
    private String note;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPayment")
    private List<Consultationpaymentreceipt> consultationpaymentreceiptList;
    @JoinColumn(name = "idPaymentType", referencedColumnName = "idConsultationPaymentType")
    @ManyToOne(optional = false)
    private Consultationpaymenttype idPaymentType;
    @JoinColumn(name = "idConsultationCostAbstract", referencedColumnName = "idConsultationCostAbstract")
    @ManyToOne(optional = false)
    private Consultationcostabstract idConsultationCostAbstract;

    public Consultationpayment() {
    }

    public Consultationpayment(Integer idConsultationPayment) {
        this.idConsultationPayment = idConsultationPayment;
    }

    public Consultationpayment(Integer idConsultationPayment, Date date) {
        this.idConsultationPayment = idConsultationPayment;
        this.date = date;
    }

    public Consultationpayment(Date date, Double cash, Double check, String checkDigits, Double card, String cardDigits, Double other, String otherDescription, Double paymentTotal, Double change, String note, Consultationpaymenttype idPaymentType, Consultationcostabstract idConsultationCostAbstract) {
        this.date = date;
        this.cash = cash;
        this.check = check;
        this.checkDigits = checkDigits;
        this.card = card;
        this.cardDigits = cardDigits;
        this.other = other;
        this.otherDescription = otherDescription;
        this.paymentTotal = paymentTotal;
        this.change = change;
        this.note = note;
        this.idPaymentType = idPaymentType;
        this.idConsultationCostAbstract = idConsultationCostAbstract;
    }

    public Integer getIdConsultationPayment() {
        return idConsultationPayment;
    }

    public void setIdConsultationPayment(Integer idConsultationPayment) {
        this.idConsultationPayment = idConsultationPayment;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getCash() {
        return cash;
    }

    public void setCash(Double cash) {
        this.cash = cash;
    }

    public Double getCheck() {
        return check;
    }

    public void setCheck(Double check) {
        this.check = check;
    }

    public String getCheckDigits() {
        return checkDigits;
    }

    public void setCheckDigits(String checkDigits) {
        this.checkDigits = checkDigits;
    }

    public Double getCard() {
        return card;
    }

    public void setCard(Double card) {
        this.card = card;
    }

    public String getCardDigits() {
        return cardDigits;
    }

    public void setCardDigits(String cardDigits) {
        this.cardDigits = cardDigits;
    }

    public Double getOther() {
        return other;
    }

    public void setOther(Double other) {
        this.other = other;
    }

    public String getOtherDescription() {
        return otherDescription;
    }

    public void setOtherDescription(String otherDescription) {
        this.otherDescription = otherDescription;
    }

    public Double getPaymentTotal() {
        return paymentTotal;
    }

    public void setPaymentTotal(Double paymentTotal) {
        this.paymentTotal = paymentTotal;
    }

    public Double getChange() {
        return change;
    }

    public void setChange(Double change) {
        this.change = change;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public List<Consultationpaymentreceipt> getConsultationpaymentreceiptList() {
        return consultationpaymentreceiptList;
    }

    public void setConsultationpaymentreceiptList(List<Consultationpaymentreceipt> consultationpaymentreceiptList) {
        this.consultationpaymentreceiptList = consultationpaymentreceiptList;
    }

    public Consultationpaymenttype getIdPaymentType() {
        return idPaymentType;
    }

    public void setIdPaymentType(Consultationpaymenttype idPaymentType) {
        this.idPaymentType = idPaymentType;
    }

    public Consultationcostabstract getIdConsultationCostAbstract() {
        return idConsultationCostAbstract;
    }

    public void setIdConsultationCostAbstract(Consultationcostabstract idConsultationCostAbstract) {
        this.idConsultationCostAbstract = idConsultationCostAbstract;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idConsultationPayment != null ? idConsultationPayment.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Consultationpayment)) {
            return false;
        }
        Consultationpayment other = (Consultationpayment) object;
        if ((this.idConsultationPayment == null && other.idConsultationPayment != null) || (this.idConsultationPayment != null && !this.idConsultationPayment.equals(other.idConsultationPayment))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.carloscortina.demo.model.Consultationpayment[ idConsultationPayment=" + idConsultationPayment + " ]";
    }
    
}
