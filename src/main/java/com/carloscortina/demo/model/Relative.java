/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.carloscortina.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Carlos Cortina
 */
@Entity
@Table(name = "relative")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Relative.findAll", query = "SELECT r FROM Relative r"),
    @NamedQuery(name = "Relative.findByIdRelative", query = "SELECT r FROM Relative r WHERE r.idRelative = :idRelative"),
    @NamedQuery(name = "Relative.findByFirstName", query = "SELECT r FROM Relative r WHERE r.firstName = :firstName"),
    @NamedQuery(name = "Relative.findBySecondName", query = "SELECT r FROM Relative r WHERE r.secondName = :secondName"),
    @NamedQuery(name = "Relative.findByFatherLastName", query = "SELECT r FROM Relative r WHERE r.fatherLastName = :fatherLastName"),
    @NamedQuery(name = "Relative.findByMotherLastName", query = "SELECT r FROM Relative r WHERE r.motherLastName = :motherLastName"),
    @NamedQuery(name = "Relative.findByCurp", query = "SELECT r FROM Relative r WHERE r.curp = :curp"),
    @NamedQuery(name = "Relative.findByOccupation", query = "SELECT r FROM Relative r WHERE r.occupation = :occupation"),
    @NamedQuery(name = "Relative.findByRfc", query = "SELECT r FROM Relative r WHERE r.rfc = :rfc"),
    @NamedQuery(name = "Relative.findByHomePhone", query = "SELECT r FROM Relative r WHERE r.homePhone = :homePhone"),
    @NamedQuery(name = "Relative.findByOfficePhone", query = "SELECT r FROM Relative r WHERE r.officePhone = :officePhone"),
    @NamedQuery(name = "Relative.findByOfficeExt", query = "SELECT r FROM Relative r WHERE r.officeExt = :officeExt"),
    @NamedQuery(name = "Relative.findByEmail", query = "SELECT r FROM Relative r WHERE r.email = :email"),
    @NamedQuery(name = "Relative.findByCellPhone", query = "SELECT r FROM Relative r WHERE r.cellPhone = :cellPhone"),
    @NamedQuery(name = "Relative.findByOtherPhone", query = "SELECT r FROM Relative r WHERE r.otherPhone = :otherPhone"),
    @NamedQuery(name = "Relative.findByNotes", query = "SELECT r FROM Relative r WHERE r.notes = :notes"),
    @NamedQuery(name = "Relative.findByRecommendedBy", query = "SELECT r FROM Relative r WHERE r.recommendedBy = :recommendedBy"),
    @NamedQuery(name = "Relative.findByGinecologist", query = "SELECT r FROM Relative r WHERE r.ginecologist = :ginecologist"),
    @NamedQuery(name = "Relative.findByAddedDate", query = "SELECT r FROM Relative r WHERE r.addedDate = :addedDate"),
    @NamedQuery(name = "Relative.findByCp", query = "SELECT r FROM Relative r WHERE r.cp = :cp"),
    @NamedQuery(name = "Relative.findByStreet", query = "SELECT r FROM Relative r WHERE r.street = :street"),
    @NamedQuery(name = "Relative.findByColony", query = "SELECT r FROM Relative r WHERE r.colony = :colony"),
    @NamedQuery(name = "Relative.findByCountry", query = "SELECT r FROM Relative r WHERE r.country = :country"),
    @NamedQuery(name = "Relative.findByState", query = "SELECT r FROM Relative r WHERE r.state = :state"),
    @NamedQuery(name = "Relative.findByCity", query = "SELECT r FROM Relative r WHERE r.city = :city"),
    @NamedQuery(name = "Relative.findByNumber", query = "SELECT r FROM Relative r WHERE r.number = :number")})
public class Relative implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idRelative")
    private Integer idRelative;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "FirstName")
    private String firstName;
    @Size(max = 45)
    @Column(name = "SecondName")
    private String secondName;
    @Size(max = 45)
    @Column(name = "FatherLastName")
    private String fatherLastName;
    @Size(max = 45)
    @Column(name = "MotherLastName")
    private String motherLastName;
    @Size(max = 18)
    @Column(name = "CURP")
    private String curp;
    @Size(max = 45)
    @Column(name = "Occupation")
    private String occupation;
    @Size(max = 45)
    @Column(name = "RFC")
    private String rfc;
    @Size(max = 45)
    @Column(name = "HomePhone")
    private String homePhone;
    @Size(max = 45)
    @Column(name = "OfficePhone")
    private String officePhone;
    @Size(max = 45)
    @Column(name = "OfficeExt")
    private String officeExt;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 45)
    @Column(name = "Email")
    private String email;
    @Size(max = 45)
    @Column(name = "CellPhone")
    private String cellPhone;
    @Size(max = 45)
    @Column(name = "OtherPhone")
    private String otherPhone;
    @Size(max = 45)
    @Column(name = "Notes")
    private String notes;
    @Size(max = 45)
    @Column(name = "RecommendedBy")
    private String recommendedBy;
    @Size(max = 45)
    @Column(name = "Ginecologist")
    private String ginecologist;
    @Basic(optional = false)
    @NotNull
    @Column(name = "AddedDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date addedDate;
    @Size(max = 45)
    @Column(name = "Cp")
    private String cp;
    @Size(max = 45)
    @Column(name = "Street")
    private String street;
    @Size(max = 45)
    @Column(name = "Colony")
    private String colony;
    @Size(max = 45)
    @Column(name = "Country")
    private String country;
    @Size(max = 45)
    @Column(name = "State")
    private String state;
    @Size(max = 45)
    @Column(name = "City")
    private String city;
    @Size(max = 45)
    @Column(name = "number")
    private String number;
    @Column(name = "Birthday")
    @Temporal(TemporalType.DATE)
    private Date birthday;
    @Column(name = "active")
    private Short active;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "relative")
    @JsonIgnore
    private List<PatientRelative> patientRelativeList;
    @JoinColumn(name = "Religion", referencedColumnName = "idReligion")
    @ManyToOne(optional = false)
    private Religion religion;
    @JoinColumn(name = "idPatient", referencedColumnName = "idPatient")
    @ManyToOne
    private Patient idPatient;

    public Relative() {
    }

    public Relative(Integer idRelative) {
        this.idRelative = idRelative;
    }

    public Relative(Integer idRelative, String firstName, Date addedDate) {
        this.idRelative = idRelative;
        this.firstName = firstName;
        this.addedDate = addedDate;
    }

    public Integer getIdRelative() {
        return idRelative;
    }

    public void setIdRelative(Integer idRelative) {
        this.idRelative = idRelative;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getFatherLastName() {
        return fatherLastName;
    }

    public void setFatherLastName(String fatherLastName) {
        this.fatherLastName = fatherLastName;
    }

    public String getMotherLastName() {
        return motherLastName;
    }

    public void setMotherLastName(String motherLastName) {
        this.motherLastName = motherLastName;
    }

    public String getCurp() {
        return curp;
    }

    public void setCurp(String curp) {
        this.curp = curp;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public String getHomePhone() {
        return homePhone;
    }

    public void setHomePhone(String homePhone) {
        this.homePhone = homePhone;
    }

    public String getOfficePhone() {
        return officePhone;
    }

    public void setOfficePhone(String officePhone) {
        this.officePhone = officePhone;
    }

    public String getOfficeExt() {
        return officeExt;
    }

    public void setOfficeExt(String officeExt) {
        this.officeExt = officeExt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getCellPhone() {
        return cellPhone;
    }

    public void setCellPhone(String cellPhone) {
        this.cellPhone = cellPhone;
    }

    public String getOtherPhone() {
        return otherPhone;
    }

    public void setOtherPhone(String otherPhone) {
        this.otherPhone = otherPhone;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getRecommendedBy() {
        return recommendedBy;
    }

    public void setRecommendedBy(String recommendedBy) {
        this.recommendedBy = recommendedBy;
    }

    public String getGinecologist() {
        return ginecologist;
    }

    public void setGinecologist(String ginecologist) {
        this.ginecologist = ginecologist;
    }

    public Date getAddedDate() {
        return addedDate;
    }

    public void setAddedDate(Date addedDate) {
        this.addedDate = addedDate;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getColony() {
        return colony;
    }

    public void setColony(String colony) {
        this.colony = colony;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Short getActive() {
        return active;
    }

    public void setActive(Short active) {
        this.active = active;
    }

    @XmlTransient
    public List<PatientRelative> getPatientRelativeList() {
        return patientRelativeList;
    }

    public void setPatientRelativeList(List<PatientRelative> patientRelativeList) {
        this.patientRelativeList = patientRelativeList;
    }

    public Religion getReligion() {
        return religion;
    }

    public void setReligion(Religion religion) {
        this.religion = religion;
    }

    public Patient getIdPatient() {
        return idPatient;
    }

    public void setIdPatient(Patient idPatient) {
        this.idPatient = idPatient;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRelative != null ? idRelative.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Relative)) {
            return false;
        }
        Relative other = (Relative) object;
        if ((this.idRelative == null && other.idRelative != null) || (this.idRelative != null && !this.idRelative.equals(other.idRelative))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.carloscortina.model.Relative[ idRelative=" + idRelative + " ]";
    }
    
}
