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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

/**
 *
 * @author Carlos Cortina
 */
@Entity
@Table(name = "patient")
@NamedQueries({
    @NamedQuery(name = "Patient.findAll", query = "SELECT p FROM Patient p")})
public class Patient implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idPatient")
    private Integer idPatient;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "FirstName")
    private String firstName;
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
    @Column(name = "NickName")
    private String nickName;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Birthday")
    @Temporal(TemporalType.DATE)
    private Date birthday;
    @Size(max = 1000)
    @Column(name = "Notes")
    private String notes;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Active")
    private int active;
    @Basic(optional = false)
    @NotNull
    @Column(name = "AddedDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date addedDate;
    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToMany(mappedBy = "patientList")
    private List<Drug> drugList;
    @JsonIgnore
    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "patient")
    private List<PatientRelative> patientRelativeList;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPatient")
    private List<Consultation> consultationList;
    @JoinColumn(name = "idDoctor", referencedColumnName = "idStaffMember")
    @ManyToOne(optional = false)
    private Staffmember idDoctor;
    @JoinColumn(name = "Sex", referencedColumnName = "idGender")
    @ManyToOne(optional = false)
    private Gender sex;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPatient")
    private List<Documents> documentsList;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "patient")
    private List<Appointmentvaccine> appointmentvaccineList;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "patient")
    private List<Patientvaccine> patientvaccineList;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPatient")
    private List<Laboratorytestresult> laboratorytestresultList;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPatient")
    private List<Appointment> appointmentList;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPatient")
    private List<Record> recordList;
    @Column(name = "tempClavePaciente")
    private Integer tempClavePaciente;

    public Patient() {
    }

    public Patient(Integer idPatient) {
        this.idPatient = idPatient;
    }

    public Patient(String firstName, String fatherLastName, String motherLastName) {
        this.firstName = firstName;
        this.fatherLastName = fatherLastName;
        this.motherLastName = motherLastName;
    }

    public Patient(Integer idPatient, String firstName, String fatherLastName, String motherLastName) {
        this.idPatient = idPatient;
        this.firstName = firstName;
        this.fatherLastName = fatherLastName;
        this.motherLastName = motherLastName;
    }

    public Patient(Integer idPatient, String firstName, Date birthday, int active, Date addedDate) {
        this.idPatient = idPatient;
        this.firstName = firstName;
        this.birthday = birthday;
        this.active = active;
        this.addedDate = addedDate;
    }

    public Patient(Integer idPatient, String firstName, String fatherLastName, String motherLastName, String curp,Gender sex, Date birthday, String notes, int active, Staffmember idDoctor) {
        this.idPatient = idPatient;
        this.firstName = firstName;
        this.fatherLastName = fatherLastName;
        this.motherLastName = motherLastName;
        this.curp = curp;
        this.birthday = birthday;
        this.notes = notes;
        this.active = active;
        this.idDoctor = idDoctor;
        this.sex = sex;
    }

    public Patient(String firstName, String fatherLastName, String motherLastName, String curp, Date birthday, String notes, Gender sex) {
        this.firstName = firstName;
        this.fatherLastName = fatherLastName;
        this.motherLastName = motherLastName;
        this.curp = curp;
        this.birthday = birthday;
        this.notes = notes;
        this.sex = sex;
    }

    public Patient(Integer idPatient, String firstName, String fatherLastName, String motherLastName,Gender sex, Date birthday, int active, Staffmember idDoctor ) {
        this.idPatient = idPatient;
        this.firstName = firstName;
        this.fatherLastName = fatherLastName;
        this.motherLastName = motherLastName;
        this.birthday = birthday;
        this.active = active;
        this.idDoctor = idDoctor;
        this.sex = sex;
    }

    public Patient(String firstName, String fatherLastName, String motherLastName, String curp, String nickName, Date birthday, String notes, int active, Date addedDate, List<Drug> drugList, Staffmember idDoctor, Gender sex) {
        this.firstName = firstName;
        this.fatherLastName = fatherLastName;
        this.motherLastName = motherLastName;
        this.curp = curp;
        this.nickName = nickName;
        this.birthday = birthday;
        this.notes = notes;
        this.active = active;
        this.addedDate = addedDate;
        this.drugList = drugList;
        this.idDoctor = idDoctor;
        this.sex = sex;
    }

    public Integer getIdPatient() {
        return idPatient;
    }

    public void setIdPatient(Integer idPatient) {
        this.idPatient = idPatient;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
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

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public Date getAddedDate() {
        return addedDate;
    }

    public void setAddedDate(Date addedDate) {
        this.addedDate = addedDate;
    }

    public List<Drug> getDrugList() {
        return drugList;
    }

    public void setDrugList(List<Drug> drugList) {
        this.drugList = drugList;
    }

    public List<PatientRelative> getPatientRelativeList() {
        return patientRelativeList;
    }

    public void setPatientRelativeList(List<PatientRelative> patientRelativeList) {
        this.patientRelativeList = patientRelativeList;
    }

    public List<Consultation> getConsultationList() {
        return consultationList;
    }

    public void setConsultationList(List<Consultation> consultationList) {
        this.consultationList = consultationList;
    }

    public Staffmember getIdDoctor() {
        return idDoctor;
    }

    public void setIdDoctor(Staffmember idDoctor) {
        this.idDoctor = idDoctor;
    }

    public Gender getSex() {
        return sex;
    }

    public void setSex(Gender sex) {
        this.sex = sex;
    }

    public List<Documents> getDocumentsList() {
        return documentsList;
    }

    public void setDocumentsList(List<Documents> documentsList) {
        this.documentsList = documentsList;
    }

    public List<Appointmentvaccine> getAppointmentvaccineList() {
        return appointmentvaccineList;
    }

    public void setAppointmentvaccineList(List<Appointmentvaccine> appointmentvaccineList) {
        this.appointmentvaccineList = appointmentvaccineList;
    }

    public List<Patientvaccine> getPatientvaccineList() {
        return patientvaccineList;
    }

    public void setPatientvaccineList(List<Patientvaccine> patientvaccineList) {
        this.patientvaccineList = patientvaccineList;
    }

    public List<Laboratorytestresult> getLaboratorytestresultList() {
        return laboratorytestresultList;
    }

    public void setLaboratorytestresultList(List<Laboratorytestresult> laboratorytestresultList) {
        this.laboratorytestresultList = laboratorytestresultList;
    }

    public List<Appointment> getAppointmentList() {
        return appointmentList;
    }

    public void setAppointmentList(List<Appointment> appointmentList) {
        this.appointmentList = appointmentList;
    }

    public List<Record> getRecordList() {
        return recordList;
    }

    public void setRecordList(List<Record> recordList) {
        this.recordList = recordList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPatient != null ? idPatient.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Patient)) {
            return false;
        }
        Patient other = (Patient) object;
        if ((this.idPatient == null && other.idPatient != null) || (this.idPatient != null && !this.idPatient.equals(other.idPatient))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.carloscortina.demo.model.Patient[ idPatient=" + idPatient + " ]";
    }

    public Integer getTempClavePaciente() {
        return tempClavePaciente;
    }

    public void setTempClavePaciente(Integer tempClavePaciente) {
        this.tempClavePaciente = tempClavePaciente;
    }
    
}
