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

/**
 *
 * @author Carlos Cortina
 */
@Entity
@Table(name = "user")
@NamedQueries({
    @NamedQuery(name = "User.findAll", query = "SELECT u FROM User u")})
public class User implements Serializable {
   
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idUser")
    private Integer idUser;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "Username")
    private String username;
    @JsonIgnore
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "Password")
    private String password;
    @JsonIgnore
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "Email")
    private String email;
    @Basic(optional = false)
    @NotNull
    @Column(name = "AddedDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date addedDate;
    @Column(name = "active")
    private int active;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idDoctor")
    private List<Consultation> consultationList;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Cie10doctor> cie10doctorList;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idDoctor")
    private List<Appointment> appointmentList;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "registeredBy")
    private List<Appointment> appointmentList1;
    @JoinColumn(name = "idStaffMember", referencedColumnName = "idStaffMember")
    @ManyToOne(optional = false)
    private Staffmember idStaffMember;
    @JsonIgnore
    @JoinColumn(name = "idRole", referencedColumnName = "idRole")
    @ManyToOne(optional = false)
    private Userrole idRole;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idExpeditor")
    private List<Consultationpaymentreceipt> consultationpaymentreceiptList;

    public User() {
    }

    public User(Integer idUser) {
        this.idUser = idUser;
    }

    public User(Integer idUser, Staffmember idStaffMember) {
        this.idUser = idUser;
        this.idStaffMember = idStaffMember;
    }

    public User(Integer idUser, String username, String password, String email, Date addedDate) {
        this.idUser = idUser;
        this.username = username;
        this.password = password;
        this.email = email;
        this.addedDate = addedDate;
    }

    public User(String username, String password, String email, Date addedDate, int active, Staffmember idStaffMember, Userrole idRole) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.addedDate = addedDate;
        this.active = active;
        this.idStaffMember = idStaffMember;
        this.idRole = idRole;
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

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public List<Consultation> getConsultationList() {
        return consultationList;
    }

    public void setConsultationList(List<Consultation> consultationList) {
        this.consultationList = consultationList;
    }

    public List<Cie10doctor> getCie10doctorList() {
        return cie10doctorList;
    }

    public void setCie10doctorList(List<Cie10doctor> cie10doctorList) {
        this.cie10doctorList = cie10doctorList;
    }

    public List<Appointment> getAppointmentList() {
        return appointmentList;
    }

    public void setAppointmentList(List<Appointment> appointmentList) {
        this.appointmentList = appointmentList;
    }

    public List<Appointment> getAppointmentList1() {
        return appointmentList1;
    }

    public void setAppointmentList1(List<Appointment> appointmentList1) {
        this.appointmentList1 = appointmentList1;
    }

    public Staffmember getIdStaffMember() {
        return idStaffMember;
    }

    public void setIdStaffMember(Staffmember idStaffMember) {
        this.idStaffMember = idStaffMember;
    }

    public Userrole getIdRole() {
        return idRole;
    }

    public void setIdRole(Userrole idRole) {
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
        return "com.carloscortina.demo.model.User[ idUser=" + idUser + " ]";
    }

    public List<Consultationpaymentreceipt> getConsultationpaymentreceiptList() {
        return consultationpaymentreceiptList;
    }

    public void setConsultationpaymentreceiptList(List<Consultationpaymentreceipt> consultationpaymentreceiptList) {
        this.consultationpaymentreceiptList = consultationpaymentreceiptList;
    }
    
}
