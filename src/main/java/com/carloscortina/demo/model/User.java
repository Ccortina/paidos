package com.carloscortina.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Ccortina_Mac
 */
@Entity
@Table(name = "user")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "User.findAll", query = "SELECT u FROM User u"),
    @NamedQuery(name = "User.findByIdUser", query = "SELECT u FROM User u WHERE u.idUser = :idUser"),
    @NamedQuery(name = "User.findByUsername", query = "SELECT u FROM User u WHERE u.username = :username"),
    @NamedQuery(name = "User.findByPassword", query = "SELECT u FROM User u WHERE u.password = :password"),
    @NamedQuery(name = "User.findByEmail", query = "SELECT u FROM User u WHERE u.email = :email"),
    @NamedQuery(name = "User.findByAddedDate", query = "SELECT u FROM User u WHERE u.addedDate = :addedDate")})
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idUser")
    private Integer idUser;
    @Basic(optional = false)
    @Column(name = "Username")
    private String username;
    @Basic(optional = false)
    @Column(name = "Password")
    private String password;
    @Basic(optional = false)
    @Column(name = "Email")
    private String email;
    @Basic(optional = false)
    @Column(name = "AddedDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date addedDate;
    @JoinTable(name = "DoctorCommercialNamesCatalog", joinColumns = {
        @JoinColumn(name = "idUser", referencedColumnName = "idUser")}, inverseJoinColumns = {
        @JoinColumn(name = "idCommercialName", referencedColumnName = "idcommercialName")})
    @ManyToMany
    @JsonIgnore
    private List<CommercialName> commercialnameList;
    @JoinTable(name = "DoctorDrugCatalog", joinColumns = {
        @JoinColumn(name = "idUser", referencedColumnName = "idUser")}, inverseJoinColumns = {
        @JoinColumn(name = "IdDrug", referencedColumnName = "idDrug")})
    @ManyToMany
    @JsonIgnore
    private List<Drug> drugList;
    @JoinTable(name = "DoctorTreatmentCatalog", joinColumns = {
        @JoinColumn(name = "idUser", referencedColumnName = "idUser")}, inverseJoinColumns = {
        @JoinColumn(name = "idTreatment", referencedColumnName = "IdTreatment")})
    @ManyToMany
    @JsonIgnore
    private List<Treatment> treatmentList;
    @JoinColumn(name = "idStaffMember", referencedColumnName = "idStaffMember")
    @ManyToOne(optional = false)
    private Staffmember idStaffMember;
    @JoinColumn(name = "idRole", referencedColumnName = "idRole")
    @ManyToOne(optional = false)
    private Role idRole;

    public User() {
    }

    public User(Integer idUser) {
        this.idUser = idUser;
    }

    public User(Integer idUser, String username, String password, String email, Date addedDate) {
        this.idUser = idUser;
        this.username = username;
        this.password = password;
        this.email = email;
        this.addedDate = addedDate;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getAddedDate() {
        return addedDate;
    }

    public void setAddedDate(Date addedDate) {
        this.addedDate = addedDate;
    }

    @XmlTransient
    public List<CommercialName> getCommercialnameList() {
        return commercialnameList;
    }

    public void setCommercialnameList(List<CommercialName> commercialnameList) {
        this.commercialnameList = commercialnameList;
    }

    @XmlTransient
    public List<Drug> getDrugList() {
        return drugList;
    }

    public void setDrugList(List<Drug> drugList) {
        this.drugList = drugList;
    }

    @XmlTransient
    public List<Treatment> getTreatmentList() {
        return treatmentList;
    }

    public void setTreatmentList(List<Treatment> treatmentList) {
        this.treatmentList = treatmentList;
    }

    public Staffmember getIdStaffMember() {
        return idStaffMember;
    }

    public void setIdStaffMember(Staffmember idStaffMember) {
        this.idStaffMember = idStaffMember;
    }

    public Role getIdRole() {
        return idRole;
    }

    public void setIdRole(Role idRole) {
        this.idRole = idRole;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUser != null ? idUser.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        if ((this.idUser == null && other.idUser != null) || (this.idUser != null && !this.idUser.equals(other.idUser))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pruebas1.User[ idUser=" + idUser + " ]";
    }
    
}
