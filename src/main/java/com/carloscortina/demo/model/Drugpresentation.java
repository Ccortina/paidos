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
@Table(name = "drugpresentation")
@NamedQueries({
    @NamedQuery(name = "Drugpresentation.findAll", query = "SELECT d FROM Drugpresentation d")})
public class Drugpresentation implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "drugPresentationId")
    private Integer drugPresentationId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "presentation")
    private String presentation;
    @Basic(optional = false)
    @NotNull
    @Column(name = "active")
    private int active;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "drugPresentationId")
    private List<Drug> drugList;

    public Drugpresentation() {
    }

    public Drugpresentation(Integer drugPresentationId) {
        this.drugPresentationId = drugPresentationId;
    }

    public Drugpresentation(Integer drugPresentationId, String presentation, int active) {
        this.drugPresentationId = drugPresentationId;
        this.presentation = presentation;
        this.active = active;
    }

    public Drugpresentation(String presentation, int active) {
        this.presentation = presentation;
        this.active = active;
    }

    public Integer getDrugPresentationId() {
        return drugPresentationId;
    }

    public void setDrugPresentationId(Integer drugPresentationId) {
        this.drugPresentationId = drugPresentationId;
    }

    public String getPresentation() {
        return presentation;
    }

    public void setPresentation(String presentation) {
        this.presentation = presentation;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public List<Drug> getDrugList() {
        return drugList;
    }

    public void setDrugList(List<Drug> drugList) {
        this.drugList = drugList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (drugPresentationId != null ? drugPresentationId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Drugpresentation)) {
            return false;
        }
        Drugpresentation other = (Drugpresentation) object;
        if ((this.drugPresentationId == null && other.drugPresentationId != null) || (this.drugPresentationId != null && !this.drugPresentationId.equals(other.drugPresentationId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.carloscortina.demo.model.Drugpresentation[ drugPresentationId=" + drugPresentationId + " ]";
    }
    
}
