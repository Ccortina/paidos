package com.carloscortina.demo.model;

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
@Table(name = "doseCalculationCriteria")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DoseCalculationCriteria.findAll", query = "SELECT d FROM DoseCalculationCriteria d"),
    @NamedQuery(name = "DoseCalculationCriteria.findByIdDoseCalculationCriteria", query = "SELECT d FROM DoseCalculationCriteria d WHERE d.idDoseCalculationCriteria = :idDoseCalculationCriteria"),
    @NamedQuery(name = "DoseCalculationCriteria.findByCriteria", query = "SELECT d FROM DoseCalculationCriteria d WHERE d.criteria = :criteria")})
public class DoseCalculationCriteria implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idDoseCalculationCriteria")
    private Integer idDoseCalculationCriteria;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "criteria")
    private String criteria;
    @OneToMany(mappedBy = "doseCalculationCriteriaId")
    private Collection<Drug> drugCollection;

    public DoseCalculationCriteria() {
    }

    public DoseCalculationCriteria(Integer idDoseCalculationCriteria) {
        this.idDoseCalculationCriteria = idDoseCalculationCriteria;
    }

    public DoseCalculationCriteria(Integer idDoseCalculationCriteria, String criteria) {
        this.idDoseCalculationCriteria = idDoseCalculationCriteria;
        this.criteria = criteria;
    }

    public Integer getIdDoseCalculationCriteria() {
        return idDoseCalculationCriteria;
    }

    public void setIdDoseCalculationCriteria(Integer idDoseCalculationCriteria) {
        this.idDoseCalculationCriteria = idDoseCalculationCriteria;
    }

    public String getCriteria() {
        return criteria;
    }

    public void setCriteria(String criteria) {
        this.criteria = criteria;
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
        hash += (idDoseCalculationCriteria != null ? idDoseCalculationCriteria.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DoseCalculationCriteria)) {
            return false;
        }
        DoseCalculationCriteria other = (DoseCalculationCriteria) object;
        if ((this.idDoseCalculationCriteria == null && other.idDoseCalculationCriteria != null) || (this.idDoseCalculationCriteria != null && !this.idDoseCalculationCriteria.equals(other.idDoseCalculationCriteria))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "diagnostic.DoseCalculationCriteria[ idDoseCalculationCriteria=" + idDoseCalculationCriteria + " ]";
    }
    
}
