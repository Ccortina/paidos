/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.carloscortina.demo.model;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.Size;

/**
 *
 * @author Carlos Cortina
 */
@Entity
@Table(name = "consultationpaymentreceipt")
@NamedQueries({
    @NamedQuery(name = "Consultationpaymentreceipt.findAll", query = "SELECT c FROM Consultationpaymentreceipt c")})
public class Consultationpaymentreceipt implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idConsultationPaymentReceipt")
    private Integer idConsultationPaymentReceipt;
    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    private Date date;
    @Column(name = "receiptNumber")
    private Integer receiptNumber;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "total")
    private Double total;
    @Column(name = "retention")
    private Integer retention;
    @Column(name = "isr")
    private Double isr;
    @Size(max = 65535)
    @Column(name = "totalText")
    private String totalText;
    @Size(max = 255)
    @Column(name = "payerName")
    private String payerName;
    @Size(max = 255)
    @Column(name = "street")
    private String street;
    @Size(max = 255)
    @Column(name = "colony")
    private String colony;
    @Size(max = 255)
    @Column(name = "city")
    private String city;
    @Size(max = 255)
    @Column(name = "state")
    private String state;
    @Size(max = 45)
    @Column(name = "zip")
    private String zip;
    @Size(max = 255)
    @Column(name = "country")
    private String country;
    @Size(max = 255)
    @Column(name = "rfc")
    private String rfc;
    @Size(max = 300)
    @Column(name = "concept")
    private String concept;
    @Size(max = 255)
    @Column(name = "claveTempRecibo")
    private String claveTempRecibo;
    @Size(max = 65535)
    @Column(name = "notes")
    private String notes;
    @JoinColumn(name = "idThirdPartyPayer", referencedColumnName = "idThirdPartyPayer")
    @ManyToOne
    private Thirdpartypayer idThirdPartyPayer;
    @JoinColumn(name = "idRelative", referencedColumnName = "idRelative")
    @ManyToOne
    private Relative idRelative;
    @JoinColumn(name = "idPayment", referencedColumnName = "idConsultationPayment")
    @ManyToOne(optional = false)
    private Consultationpayment idPayment;
    @JoinColumn(name = "idExpeditor", referencedColumnName = "idUser")
    @ManyToOne(optional = false)
    private User idExpeditor;
    @JoinColumn(name = "idConsultatioPaymentReceiptType", referencedColumnName = "idConsultationPaymentReceipttype")
    @ManyToOne(optional = false)
    private Consultationpaymentreceipttype idConsultatioPaymentReceiptType;
    @Transient
    private Patient patient;
    @Transient
    private Date consultationDate;
    @Transient
    private int fromReceiptNumber;
    @Transient
    private int toReceiptNumber;
    @Transient
    private double sumTotal;
    @Transient
    private int totalReceipts;
    
    public Consultationpaymentreceipt() {
    }

    public Consultationpaymentreceipt(Integer idConsultationPaymentReceipt) {
        this.idConsultationPaymentReceipt = idConsultationPaymentReceipt;
    }

    public Integer getIdConsultationPaymentReceipt() {
        return idConsultationPaymentReceipt;
    }

    public void setIdConsultationPaymentReceipt(Integer idConsultationPaymentReceipt) {
        this.idConsultationPaymentReceipt = idConsultationPaymentReceipt;
    }

    public Consultationpaymentreceipt(User idExpeditor, int fromReceiptNumber, int toReceiptNumber, double sumTotal) {
        this.idExpeditor = idExpeditor;
        this.fromReceiptNumber = fromReceiptNumber;
        this.toReceiptNumber = toReceiptNumber;
        this.sumTotal = sumTotal;
    }

    public Consultationpaymentreceipt(Date date, Integer receiptNumber, Double total, Integer retention, 
            Double isr, String totalText, String payerName, String street, String colony, String city, 
            String state, String country, String rfc, String concept, String notes,
            Consultationpayment idPayment, User idExpeditor, Consultationpaymentreceipttype idConsultatioPaymentReceiptType) {
        this.date = date;
        this.receiptNumber = receiptNumber;
        this.total = total;
        this.retention = retention;
        this.isr = isr;
        this.totalText = totalText;
        this.payerName = payerName;
        this.street = street;
        this.colony = colony;
        this.city = city;
        this.state = state;
        this.country = country;
        this.rfc = rfc;
        this.concept = concept;
        this.notes = notes;
        this.idPayment = idPayment;
        this.idExpeditor = idExpeditor;
        this.idConsultatioPaymentReceiptType = idConsultatioPaymentReceiptType;
    }
    
    public Consultationpaymentreceipt(Date date, Integer receiptNumber, Double total, Integer retention, 
            Double isr, String totalText, String payerName, String street, String colony, String city, 
            String state, String country, String rfc, String concept, String notes,
            Relative idRelative, Consultationpayment idPayment, User idExpeditor, Consultationpaymentreceipttype idConsultatioPaymentReceiptType) {
        this.date = date;
        this.receiptNumber = receiptNumber;
        this.total = total;
        this.retention = retention;
        this.isr = isr;
        this.totalText = totalText;
        this.payerName = payerName;
        this.street = street;
        this.colony = colony;
        this.city = city;
        this.state = state;
        this.country = country;
        this.rfc = rfc;
        this.concept = concept;
        this.notes = notes;
        this.idRelative = idRelative;
        this.idPayment = idPayment;
        this.idExpeditor = idExpeditor;
        this.idConsultatioPaymentReceiptType = idConsultatioPaymentReceiptType;
    }
    
    public Consultationpaymentreceipt(Date date, Integer receiptNumber, Double total, Integer retention, 
            Double isr, String totalText, String payerName, String street, String colony, String city, 
            String state, String country, String rfc, String concept, String notes,
            Thirdpartypayer thirdpartypayer, Consultationpayment idPayment, User idExpeditor, Consultationpaymentreceipttype idConsultatioPaymentReceiptType) {
        this.date = date;
        this.receiptNumber = receiptNumber;
        this.total = total;
        this.retention = retention;
        this.isr = isr;
        this.totalText = totalText;
        this.payerName = payerName;
        this.street = street;
        this.colony = colony;
        this.city = city;
        this.state = state;
        this.country = country;
        this.rfc = rfc;
        this.concept = concept;
        this.notes = notes;
        this.idThirdPartyPayer = thirdpartypayer;
        this.idPayment = idPayment;
        this.idExpeditor = idExpeditor;
        this.idConsultatioPaymentReceiptType = idConsultatioPaymentReceiptType;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getReceiptNumber() {
        return receiptNumber;
    }

    public void setReceiptNumber(Integer receiptNumber) {
        this.receiptNumber = receiptNumber;
    }

    public int getTotalReceipts() {
        return totalReceipts;
    }

    public void setTotalReceipts(int totalReceipts) {
        this.totalReceipts = totalReceipts;
    }

    public Date getConsultationDate() {
        return consultationDate;
    }

    public void setConsultationDate(Date consultationDate) {
        this.consultationDate = consultationDate;
    }

    public double getFromReceiptNumber() {
        return fromReceiptNumber;
    }

    public void setFromReceiptNumber(int fromReceiptNumber) {
        this.fromReceiptNumber = fromReceiptNumber;
    }

    public double getToReceiptNumber() {
        return toReceiptNumber;
    }

    public void setToReceiptNumber(int toReceiptNumber) {
        this.toReceiptNumber = toReceiptNumber;
    }

    public double getSumTotal() {
        return sumTotal;
    }

    public void setSumTotal(double sumTotal) {
        this.sumTotal = sumTotal;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Integer getRetention() {
        return retention;
    }

    public void setRetention(Integer retention) {
        this.retention = retention;
    }

    public Double getIsr() {
        return isr;
    }

    public void setIsr(Double isr) {
        this.isr = isr;
    }

    public String getTotalText() {
        return totalText;
    }

    public void setTotalText(String totalText) {
        this.totalText = totalText;
    }

    public String getPayerName() {
        return payerName;
    }

    public void setPayerName(String payerName) {
        this.payerName = payerName;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getColony() {
        return colony;
    }

    public void setColony(String colony) {
        this.colony = colony;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public String getConcept() {
        return concept;
    }

    public void setConcept(String concept) {
        this.concept = concept;
    }

    public String getClaveTempRecibo() {
        return claveTempRecibo;
    }

    public void setClaveTempRecibo(String claveTempRecibo) {
        this.claveTempRecibo = claveTempRecibo;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Thirdpartypayer getIdThirdPartyPayer() {
        return idThirdPartyPayer;
    }

    public void setIdThirdPartyPayer(Thirdpartypayer idThirdPartyPayer) {
        this.idThirdPartyPayer = idThirdPartyPayer;
    }

    public Relative getIdRelative() {
        return idRelative;
    }

    public void setIdRelative(Relative idRelative) {
        this.idRelative = idRelative;
    }

    public Consultationpayment getIdPayment() {
        return idPayment;
    }

    public void setIdPayment(Consultationpayment idPayment) {
        this.idPayment = idPayment;
    }

    public User getIdExpeditor() {
        return idExpeditor;
    }

    public void setIdExpeditor(User idExpeditor) {
        this.idExpeditor = idExpeditor;
    }

    public Consultationpaymentreceipttype getIdConsultatioPaymentReceiptType() {
        return idConsultatioPaymentReceiptType;
    }

    public void setIdConsultatioPaymentReceiptType(Consultationpaymentreceipttype idConsultatioPaymentReceiptType) {
        this.idConsultatioPaymentReceiptType = idConsultatioPaymentReceiptType;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idConsultationPaymentReceipt != null ? idConsultationPaymentReceipt.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Consultationpaymentreceipt)) {
            return false;
        }
        Consultationpaymentreceipt other = (Consultationpaymentreceipt) object;
        if ((this.idConsultationPaymentReceipt == null && other.idConsultationPaymentReceipt != null) || (this.idConsultationPaymentReceipt != null && !this.idConsultationPaymentReceipt.equals(other.idConsultationPaymentReceipt))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.carloscortina.demo.model.Consultationpaymentreceipt[ idConsultationPaymentReceipt=" + idConsultationPaymentReceipt + " ]";
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
    
}
