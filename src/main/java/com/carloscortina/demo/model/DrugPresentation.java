package com.carloscortina.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Collection;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author carloscortina
 */
@Entity
@Table(name = "drugPresentation")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DrugPresentation.findAll", query = "SELECT d FROM DrugPresentation d"),
    @NamedQuery(name = "DrugPresentation.findByDrugPresentationId", query = "SELECT d FROM DrugPresentation d WHERE d.drugPresentationId = :drugPresentationId"),
    @NamedQuery(name = "DrugPresentation.findByPresentation", query = "SELECT d FROM DrugPresentation d WHERE d.presentation = :presentation"),
    @NamedQuery(name = "DrugPresentation.findByActive", query = "SELECT d FROM DrugPresentation d WHERE d.active = :active")})
public class DrugPresentation implements Serializable {
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
    private short active;
    @JsonIgnore
    @OneToMany(mappedBy = "drugPresentationId")
    private Collection<Drug> drugCollection;

    public DrugPresentation() {
    }

    public DrugPresentation(Integer drugPresentationId) {
        this.drugPresentationId = drugPresentationId;
    }

    public DrugPresentation(Integer drugPresentationId, String presentation, short active) {
        this.drugPresentationId = drugPresentationId;
        this.presentation = presentation;
        this.active = active;
    }

    public DrugPresentation(String presentation, short active) {
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

    public short getActive() {
        return active;
    }

    public void setActive(short active) {
        this.active = active;
    }

    @XmlTransient
    public Collection<Drug> getDrugCollection() {
        return drugCollection;
    }

    public void setDrugCollection(Collection<Drug> drugCollection) {
        this.drugCollection = drugCollection;
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
        if (!(object instanceof DrugPresentation)) {
            return false;
        }
        DrugPresentation other = (DrugPresentation) object;
        if ((this.drugPresentationId == null && other.drugPresentationId != null) || (this.drugPresentationId != null && !this.drugPresentationId.equals(other.drugPresentationId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "diagnostic.DrugPresentation[ drugPresentationId=" + drugPresentationId + " ]";
    }
    
}

