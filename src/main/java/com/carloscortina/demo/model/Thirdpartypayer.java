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
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author Carlos Cortina
 */
@Entity
@Table(name = "thirdpartypayer")
@NamedQueries({
    @NamedQuery(name = "Thirdpartypayer.findAll", query = "SELECT t FROM Thirdpartypayer t")})
public class Thirdpartypayer implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idThirdPartyPayer")
    private Integer idThirdPartyPayer;
    @Size(max = 400)
    @Column(name = "name")
    private String name;
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
    @Size(max = 60)
    @Column(name = "rfc")
    private String rfc;
    @Column(name = "active")
    private Integer active;
    @Size(max = 45)
    @Column(name = "tempClavePagadorTercero")
    private String tempClavePagadorTercero;
    @JsonIgnore
    @OneToMany(mappedBy = "idThirdPartyPayer")
    private List<Consultationpaymentreceipt> consultationpaymentreceiptList;

    public Thirdpartypayer() {
    }

    public Thirdpartypayer(Integer idThirdPartyPayer) {
        this.idThirdPartyPayer = idThirdPartyPayer;
    }

    public Integer getIdThirdPartyPayer() {
        return idThirdPartyPayer;
    }

    public void setIdThirdPartyPayer(Integer idThirdPartyPayer) {
        this.idThirdPartyPayer = idThirdPartyPayer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Integer getActive() {
        return active;
    }

    public void setActive(Integer active) {
        this.active = active;
    }

    public String getTempClavePagadorTercero() {
        return tempClavePagadorTercero;
    }

    public void setTempClavePagadorTercero(String tempClavePagadorTercero) {
        this.tempClavePagadorTercero = tempClavePagadorTercero;
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
        hash += (idThirdPartyPayer != null ? idThirdPartyPayer.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Thirdpartypayer)) {
            return false;
        }
        Thirdpartypayer other = (Thirdpartypayer) object;
        if ((this.idThirdPartyPayer == null && other.idThirdPartyPayer != null) || (this.idThirdPartyPayer != null && !this.idThirdPartyPayer.equals(other.idThirdPartyPayer))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.carloscortina.demo.model.Thirdpartypayer[ idThirdPartyPayer=" + idThirdPartyPayer + " ]";
    }
    
}
