/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.carloscortina.demo.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Carlos
 */
@Entity
@Table(name = "staffmember")
@NamedQueries({
    @NamedQuery(name = "Staffmember.findAll", query = "SELECT s FROM Staffmember s")})
public class Staffmember implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idStaffMember")
    private Integer idStaffMember;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "Name")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "LastName")
    private String lastName;
    // @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="Invalid phone/fax format, should be as xxx-xxx-xxxx")//if the field contains phone or fax number consider using this annotation to enforce field validation
    @Size(max = 45)
    @Column(name = "Phone")
    private String phone;
    @Size(max = 45)
    @Column(name = "CellPhone")
    private String cellPhone;
    @Size(max = 45)
    @Column(name = "ProfessionalNumber")
    private String professionalNumber;
    @Basic(optional = false)
    @NotNull
    @Column(name = "AddedDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date addedDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "presciptionNumber")
    private int presciptionNumber;
    @Basic(optional = false)
    @NotNull
    @Column(name = "receiptNumber")
    private int receiptNumber;

    public Staffmember() {
    }

    public Staffmember(Integer idStaffMember) {
        this.idStaffMember = idStaffMember;
    }

    public Staffmember(Integer idStaffMember, String name, String lastName, Date addedDate, int presciptionNumber, int receiptNumber) {
        this.idStaffMember = idStaffMember;
        this.name = name;
        this.lastName = lastName;
        this.addedDate = addedDate;
        this.presciptionNumber = presciptionNumber;
        this.receiptNumber = receiptNumber;
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

    public int getReceiptNumber() {
        return receiptNumber;
    }

    public void setReceiptNumber(int receiptNumber) {
        this.receiptNumber = receiptNumber;
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
        return "com.carloscortina.demo.model.Staffmember[ idStaffMember=" + idStaffMember + " ]";
    }
    
}
