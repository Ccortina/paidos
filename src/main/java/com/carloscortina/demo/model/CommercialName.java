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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

/**
 *
 * @author Ccortina_Mac
 */
@Entity
@Table(name = "commercialname")
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
    @Column(name = "commercialName")
    private String commercialName;
    @Basic(optional = false)
    @Column(name = "active")
    private short active;
    @ManyToMany(mappedBy = "commercialnameList")
    @JsonIgnore
    private List<User> userList;
    @JoinColumn(name = "drugId", referencedColumnName = "idDrug")
    @ManyToOne(optional = false)
    @LazyCollection(LazyCollectionOption.FALSE)
    private Drug drugId;
    @JoinTable(name = "incompatibledrugs", joinColumns = {
        @JoinColumn(name = "idIncompatibleDrug", referencedColumnName = "idcommercialName")}, inverseJoinColumns = {
        @JoinColumn(name = "idDrug", referencedColumnName = "idDrug")})
    @ManyToMany
    @JsonIgnore
    private List<Drug> incompatibleDrugList;

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

    public CommercialName(String commercialName, short active, List<User> userList, Drug drugId) {
        this.commercialName = commercialName;
        this.active = active;
        this.userList = userList;
        this.drugId = drugId;
    }

    public CommercialName(Integer idcommercialName, String commercialName, short active, Drug drugId) {
        this.idcommercialName = idcommercialName;
        this.commercialName = commercialName;
        this.active = active;
        this.drugId = drugId;
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

    public List<Drug> getDrugList() {
        return incompatibleDrugList;
    }

    public void setDrugList(List<Drug> drugList) {
        this.incompatibleDrugList = drugList;
    }

    public short getActive() {
        return active;
    }

    public void setActive(short active) {
        this.active = active;
    }

    @XmlTransient
    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
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
        return "pruebas1.Commercialname[ idcommercialName=" + idcommercialName + " ]";
    }
    
}