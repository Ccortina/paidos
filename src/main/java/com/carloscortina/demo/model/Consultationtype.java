/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.carloscortina.demo.model;

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
@Table(name = "consultationtype")
@NamedQueries({
    @NamedQuery(name = "Consultationtype.findAll", query = "SELECT c FROM Consultationtype c")})
public class Consultationtype implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idConsultationType")
    private Integer idConsultationType;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "type")
    private String type;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "type")
    private List<Consultation> consultationList;

    public Consultationtype() {
    }

    public Consultationtype(Integer idConsultationType) {
        this.idConsultationType = idConsultationType;
    }

    public Consultationtype(Integer idConsultationType, String type) {
        this.idConsultationType = idConsultationType;
        this.type = type;
    }

    public Integer getIdConsultationType() {
        return idConsultationType;
    }

    public void setIdConsultationType(Integer idConsultationType) {
        this.idConsultationType = idConsultationType;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Consultation> getConsultationList() {
        return consultationList;
    }

    public void setConsultationList(List<Consultation> consultationList) {
        this.consultationList = consultationList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idConsultationType != null ? idConsultationType.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Consultationtype)) {
            return false;
        }
        Consultationtype other = (Consultationtype) object;
        if ((this.idConsultationType == null && other.idConsultationType != null) || (this.idConsultationType != null && !this.idConsultationType.equals(other.idConsultationType))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.carloscortina.demo.model.Consultationtype[ idConsultationType=" + idConsultationType + " ]";
    }
    
}
