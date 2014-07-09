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
@Table(name = "administrationUnit")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AdministrationUnit.findAll", query = "SELECT a FROM AdministrationUnit a"),
    @NamedQuery(name = "AdministrationUnit.findByIdAdministrationUnit", query = "SELECT a FROM AdministrationUnit a WHERE a.idAdministrationUnit = :idAdministrationUnit"),
    @NamedQuery(name = "AdministrationUnit.findByAdministrationUnit", query = "SELECT a FROM AdministrationUnit a WHERE a.administrationUnit = :administrationUnit"),
    @NamedQuery(name = "AdministrationUnit.findByActive", query = "SELECT a FROM AdministrationUnit a WHERE a.active = :active")})
public class AdministrationUnit implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idAdministrationUnit")
    private Integer idAdministrationUnit;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "administrationUnit")
    private String administrationUnit;
    @Basic(optional = false)
    @NotNull
    @Column(name = "active")
    private short active;
    @OneToMany(mappedBy = "administrationUnitId")
    @JsonIgnore
    private Collection<Drug> drugCollection;

    public AdministrationUnit() {
    }

    public AdministrationUnit(Integer idAdministrationUnit) {
        this.idAdministrationUnit = idAdministrationUnit;
    }

    public AdministrationUnit(Integer idAdministrationUnit, String administrationUnit, short active) {
        this.idAdministrationUnit = idAdministrationUnit;
        this.administrationUnit = administrationUnit;
        this.active = active;
    }

    public Integer getIdAdministrationUnit() {
        return idAdministrationUnit;
    }

    public void setIdAdministrationUnit(Integer idAdministrationUnit) {
        this.idAdministrationUnit = idAdministrationUnit;
    }

    public String getAdministrationUnit() {
        return administrationUnit;
    }

    public void setAdministrationUnit(String administrationUnit) {
        this.administrationUnit = administrationUnit;
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
        hash += (idAdministrationUnit != null ? idAdministrationUnit.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AdministrationUnit)) {
            return false;
        }
        AdministrationUnit other = (AdministrationUnit) object;
        if ((this.idAdministrationUnit == null && other.idAdministrationUnit != null) || (this.idAdministrationUnit != null && !this.idAdministrationUnit.equals(other.idAdministrationUnit))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "diagnostic.AdministrationUnit[ idAdministrationUnit=" + idAdministrationUnit + " ]";
    }
    
}

