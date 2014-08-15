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
@Table(name = "applicationMethod")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ApplicationMethod.findAll", query = "SELECT a FROM ApplicationMethod a"),
    @NamedQuery(name = "ApplicationMethod.findByIdApplicationMethod", query = "SELECT a FROM ApplicationMethod a WHERE a.idApplicationMethod = :idApplicationMethod"),
    @NamedQuery(name = "ApplicationMethod.findByApplicationMethod", query = "SELECT a FROM ApplicationMethod a WHERE a.applicationMethod = :applicationMethod"),
    @NamedQuery(name = "ApplicationMethod.findByActive", query = "SELECT a FROM ApplicationMethod a WHERE a.active = :active")})
public class ApplicationMethod implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idApplicationMethod")
    private Integer idApplicationMethod;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "applicationMethod")
    private String applicationMethod;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "active")
    private String active;
    @OneToMany(mappedBy = "applicationMethodId")
    @JsonIgnore
    private Collection<Drug> drugCollection;

    public ApplicationMethod() {
    }

    public ApplicationMethod(Integer idApplicationMethod) {
        this.idApplicationMethod = idApplicationMethod;
    }

    public ApplicationMethod(Integer idApplicationMethod, String applicationMethod, String active) {
        this.idApplicationMethod = idApplicationMethod;
        this.applicationMethod = applicationMethod;
        this.active = active;
    }

    public ApplicationMethod(String applicationMethod, String active) {
        this.applicationMethod = applicationMethod;
        this.active = active;
    }

    public Integer getIdApplicationMethod() {
        return idApplicationMethod;
    }

    public void setIdApplicationMethod(Integer idApplicationMethod) {
        this.idApplicationMethod = idApplicationMethod;
    }

    public String getApplicationMethod() {
        return applicationMethod;
    }

    public void setApplicationMethod(String applicationMethod) {
        this.applicationMethod = applicationMethod;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
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
        hash += (idApplicationMethod != null ? idApplicationMethod.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ApplicationMethod)) {
            return false;
        }
        ApplicationMethod other = (ApplicationMethod) object;
        if ((this.idApplicationMethod == null && other.idApplicationMethod != null) || (this.idApplicationMethod != null && !this.idApplicationMethod.equals(other.idApplicationMethod))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "diagnostic.ApplicationMethod[ idApplicationMethod=" + idApplicationMethod + " ]";
    }
    
}
