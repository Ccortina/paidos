/*
 * To change this template, choose Tools | Templates
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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
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
@Table(name = "staffmember")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Staffmember.findAll", query = "SELECT s FROM Staffmember s"),
    @NamedQuery(name = "Staffmember.findByIdStaffMember", query = "SELECT s FROM Staffmember s WHERE s.idStaffMember = :idStaffMember"),
    @NamedQuery(name = "Staffmember.findByName", query = "SELECT s FROM Staffmember s WHERE s.name = :name"),
    @NamedQuery(name = "Staffmember.findByLastName", query = "SELECT s FROM Staffmember s WHERE s.lastName = :lastName"),
    @NamedQuery(name = "Staffmember.findByPhone", query = "SELECT s FROM Staffmember s WHERE s.phone = :phone"),
    @NamedQuery(name = "Staffmember.findByCellPhone", query = "SELECT s FROM Staffmember s WHERE s.cellPhone = :cellPhone"),
    @NamedQuery(name = "Staffmember.findByProfessionalNumber", query = "SELECT s FROM Staffmember s WHERE s.professionalNumber = :professionalNumber"),
    @NamedQuery(name = "Staffmember.findByAddedDate", query = "SELECT s FROM Staffmember s WHERE s.addedDate = :addedDate"),
    @NamedQuery(name = "Staffmember.findByPresciptionNumber", query = "SELECT s FROM Staffmember s WHERE s.presciptionNumber = :presciptionNumber")})
public class Staffmember implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idStaffMember")
    private Integer idStaffMember;
    @Basic(optional = false)
    @Column(name = "Name")
    private String name;
    @Basic(optional = false)
    @Column(name = "LastName")
    private String lastName;
    @Column(name = "Phone")
    private String phone;
    @Column(name = "CellPhone")
    private String cellPhone;
    @Column(name = "ProfessionalNumber")
    private String professionalNumber;
    @Basic(optional = false)
    @Column(name = "AddedDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date addedDate;
    @Basic(optional = false)
    @Column(name = "presciptionNumber")
    private int presciptionNumber;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idDoctor")
    private List<Consultation> consultationList;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idDoctor")
    private List<Patient> patientList;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idStaffMember")
    private List<User> userList;

    public Staffmember() {
    }

    public Staffmember(Integer idStaffMember) {
        this.idStaffMember = idStaffMember;
    }

    public Staffmember(Integer idStaffMember, String name, String lastName, Date addedDate, int presciptionNumber) {
        this.idStaffMember = idStaffMember;
        this.name = name;
        this.lastName = lastName;
        this.addedDate = addedDate;
        this.presciptionNumber = presciptionNumber;
    }

    public Integer getIdStaffMember() {
        return idStaffMember;
    }

    public void setIdStaffMember(Integer idStaffMember) {
        this.idStaffMember = idStaffMember;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCellPhone() {
        return cellPhone;
    }

    public void setCellPhone(String cellPhone) {
        this.cellPhone = cellPhone;
    }

    public String getProfessionalNumber() {
        return professionalNumber;
    }

    public void setProfessionalNumber(String professionalNumber) {
        this.professionalNumber = professionalNumber;
    }

    public Date getAddedDate() {
        return addedDate;
    }

    public void setAddedDate(Date addedDate) {
        this.addedDate = addedDate;
    }

    public int getPresciptionNumber() {
        return presciptionNumber;
    }

    public void setPresciptionNumber(int presciptionNumber) {
        this.presciptionNumber = presciptionNumber;
    }

    @XmlTransient
    public List<Consultation> getConsultationList() {
        return consultationList;
    }

    public void setConsultationList(List<Consultation> consultationList) {
        this.consultationList = consultationList;
    }

    @XmlTransient
    public List<Patient> getPatientList() {
        return patientList;
    }

    public void setPatientList(List<Patient> patientList) {
        this.patientList = patientList;
    }

    @XmlTransient
    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idStaffMember != null ? idStaffMember.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Staffmember)) {
            return false;
        }
        Staffmember other = (Staffmember) object;
        if ((this.idStaffMember == null && other.idStaffMember != null) || (this.idStaffMember != null && !this.idStaffMember.equals(other.idStaffMember))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pruebas1.Staffmember[ idStaffMember=" + idStaffMember + " ]";
    }
    
}
