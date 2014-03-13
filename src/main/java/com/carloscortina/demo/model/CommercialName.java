package com.carloscortina.demo.model;

import java.io.Serializable;
import java.util.Collection;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author carloscortina
 */
@Entity
@Table(name = "commercialName")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CommercialName.findAll", query = "SELECT c FROM CommercialName c"),
    @NamedQuery(name = "CommercialName.findByIdcommercialName", query = "SELECT c FROM CommercialName c WHERE c.idcommercialName = :idcommercialName"),
    @NamedQuery(name = "CommercialName.findByCommercialName", query = "SELECT c FROM CommercialName c WHERE c.commercialName = :commercialName"),
    @NamedQuery(name = "CommercialName.findByActive", query = "SELECT c FROM CommercialName c WHERE c.active = :active")})
public class CommercialName implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idcommercialName")
    private Integer idcommercialName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 80)
    @Column(name = "commercialName")
    private String commercialName;
    @Basic(optional = false)
    @NotNull
    @Column(name = "active")
    private short active;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "commercialName")
    private Collection<Diagnostic> diagnosticCollection;
    @JoinColumn(name = "drugId", referencedColumnName = "idDrug")
    @ManyToOne(optional = false)
    private Drug drugId;

    public CommercialName() {
    }

    public CommercialName(Integer idcommercialName) {
        this.idcommercialName = idcommercialName;
    }

    public CommercialName(Integer idcommercialName, String commercialName, short active) {
        this.idcommercialName = idcommercialName;
        this.commercialName = commercialName;
        this.active = active;
    }

    public Integer getIdcommercialName() {
        return idcommercialName;
    }

    public void setIdcommercialName(Integer idcommercialName) {
        this.idcommercialName = idcommercialName;
    }

    public String getCommercialName() {
        return commercialName;
    }

    public void setCommercialName(String commercialName) {
        this.commercialName = commercialName;
    }

    public short getActive() {
        return active;
    }

    public void setActive(short active) {
        this.active = active;
    }

    @XmlTransient
    public Collection<Diagnostic> getDiagnosticCollection() {
        return diagnosticCollection;
    }

    public void setDiagnosticCollection(Collection<Diagnostic> diagnosticCollection) {
        this.diagnosticCollection = diagnosticCollection;
    }

    public Drug getDrugId() {
        return drugId;
    }

    public void setDrugId(Drug drugId) {
        this.drugId = drugId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcommercialName != null ? idcommercialName.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CommercialName)) {
            return false;
        }
        CommercialName other = (CommercialName) object;
        if ((this.idcommercialName == null && other.idcommercialName != null) || (this.idcommercialName != null && !this.idcommercialName.equals(other.idcommercialName))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "diagnostic.CommercialName[ idcommercialName=" + idcommercialName + " ]";
    }
    
}

