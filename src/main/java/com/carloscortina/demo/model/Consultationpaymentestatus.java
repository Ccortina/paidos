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
@Table(name = "consultationpaymentestatus")
@NamedQueries({
    @NamedQuery(name = "Consultationpaymentestatus.findAll", query = "SELECT c FROM Consultationpaymentestatus c")})
public class Consultationpaymentestatus implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idConsultationPaymentEstatus")
    private Integer idConsultationPaymentEstatus;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "estatus")
    private String estatus;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idConsultationPaymentStatus")
    private List<Consultationcostabstract> consultationcostabstractList;

    public Consultationpaymentestatus() {
    }

    public Consultationpaymentestatus(Integer idConsultationPaymentEstatus) {
        this.idConsultationPaymentEstatus = idConsultationPaymentEstatus;
    }

    public Consultationpaymentestatus(Integer idConsultationPaymentEstatus, String estatus) {
        this.idConsultationPaymentEstatus = idConsultationPaymentEstatus;
        this.estatus = estatus;
    }

    public Integer getIdConsultationPaymentEstatus() {
        return idConsultationPaymentEstatus;
    }

    public void setIdConsultationPaymentEstatus(Integer idConsultationPaymentEstatus) {
        this.idConsultationPaymentEstatus = idConsultationPaymentEstatus;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public List<Consultationcostabstract> getConsultationcostabstractList() {
        return consultationcostabstractList;
    }

    public void setConsultationcostabstractList(List<Consultationcostabstract> consultationcostabstractList) {
        this.consultationcostabstractList = consultationcostabstractList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idConsultationPaymentEstatus != null ? idConsultationPaymentEstatus.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Consultationpaymentestatus)) {
            return false;
        }
        Consultationpaymentestatus other = (Consultationpaymentestatus) object;
        if ((this.idConsultationPaymentEstatus == null && other.idConsultationPaymentEstatus != null) || (this.idConsultationPaymentEstatus != null && !this.idConsultationPaymentEstatus.equals(other.idConsultationPaymentEstatus))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.carloscortina.demo.model.Consultationpaymentestatus[ idConsultationPaymentEstatus=" + idConsultationPaymentEstatus + " ]";
    }
    
}
