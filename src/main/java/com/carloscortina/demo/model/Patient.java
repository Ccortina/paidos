package com.carloscortina.demo.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

/**
 *
 * @author Ccortina_Mac
 */
@Entity
@Table(name = "patient")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Patient.findAll", query = "SELECT p FROM Patient p"),
    @NamedQuery(name = "Patient.findByIdPatient", query = "SELECT p FROM Patient p WHERE p.idPatient = :idPatient"),
    @NamedQuery(name = "Patient.findByFirstName", query = "SELECT p FROM Patient p WHERE p.firstName = :firstName"),
    @NamedQuery(name = "Patient.findBySecondName", query = "SELECT p FROM Patient p WHERE p.secondName = :secondName"),
    @NamedQuery(name = "Patient.findByFatherLastName", query = "SELECT p FROM Patient p WHERE p.fatherLastName = :fatherLastName"),
    @NamedQuery(name = "Patient.findByMotherLastName", query = "SELECT p FROM Patient p WHERE p.motherLastName = :motherLastName"),
    @NamedQuery(name = "Patient.findByCurp", query = "SELECT p FROM Patient p WHERE p.curp = :curp"),
    @NamedQuery(name = "Patient.findByNickName", query = "SELECT p FROM Patient p WHERE p.nickName = :nickName"),
    @NamedQuery(name = "Patient.findBySex", query = "SELECT p FROM Patient p WHERE p.sex = :sex"),
    @NamedQuery(name = "Patient.findByBirthday", query = "SELECT p FROM Patient p WHERE p.birthday = :birthday"),
    @NamedQuery(name = "Patient.findByActive", query = "SELECT p FROM Patient p WHERE p.active = :active"),
    @NamedQuery(name = "Patient.findByAddedDate", query = "SELECT p FROM Patient p WHERE p.addedDate = :addedDate")})
public class Patient implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idPatient")
    private Integer idPatient;
    @Basic(optional = false)
    @Column(name = "FirstName")
    private String firstName;
    @Column(name = "SecondName")
    private String secondName;
    @Column(name = "FatherLastName")
    private String fatherLastName;
    @Column(name = "MotherLastName")
    private String motherLastName;
    @Column(name = "CURP")
    private String curp;
    @Column(name = "NickName")
    private String nickName;
    @Basic(optional = false)
    @Column(name = "Sex")
    private String sex;
    @Basic(optional = false)
    @Column(name = "Birthday")
    @Temporal(TemporalType.DATE)
    private Date birthday;
    @Lob
    @Column(name = "Notes")
    private String notes;
    @Basic(optional = false)
    @Column(name = "Active")
    private short active;
    @Basic(optional = false)
    @Column(name = "AddedDate")
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date addedDate;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "patient")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Patient_Relative> patientRelativeList;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPatient")
    private List<Consultation> consultationList;
    @JoinColumn(name = "idDoctor", referencedColumnName = "idStaffMember")
    @ManyToOne(optional = false)
    private Staffmember idDoctor;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPatient")
    private List<LaboratoryTestResult> laboratoryTestResultList;
    @JsonIgnore
    @OneToMany(mappedBy = "idPatient")
    private List<Relative> relativeList;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPatient")
    private List<Appointment> appointmentList;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPatient")
    private List<Record> recordList;
    @JoinTable(name = "patientDrugAlergic", joinColumns = {
        @JoinColumn(name = "idPatient", referencedColumnName = "idPatient")}, inverseJoinColumns = {
        @JoinColumn(name = "idDrug", referencedColumnName = "idDrug")})
    @ManyToMany
    @JsonIgnore
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Drug> drugList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "patient")
    @JsonIgnore
    private List<PatientVaccine> patientVaccineList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "patient")
    @JsonIgnore
    private List<AppointmentVaccine> appointmentVaccineList;

    public Patient() {
    }

    public Patient(Integer idPatient) {
        this.idPatient = idPatient;
    }

    public Patient(Integer idPatient, String firstName, String sex, java.util.Date birthday, short active, java.util.Date addedDate) {
        this.idPatient = idPatient;
        this.firstName = firstName;
        this.sex = sex;
        this.birthday = birthday;
        this.active = active;
        this.addedDate = addedDate;
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

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public java.util.Date getBirthday() {
        return birthday;
    }

    public void setBirthday(java.util.Date birthday) {
        this.birthday = birthday;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public short getActive() {
        return active;
    }

    public void setActive(short active) {
        this.active = active;
    }

    public java.util.Date getAddedDate() {
        return addedDate;
    }

    public void setAddedDate(java.util.Date addedDate) {
        this.addedDate = addedDate;
    }

    @XmlTransient
    @JsonIgnore
    public List<Patient_Relative> getPatientRelativeList() {
        return patientRelativeList;
    }

    public void setPatientRelativeList(List<Patient_Relative> patientRelativeList) {
        this.patientRelativeList = patientRelativeList;
    }

    @XmlTransient
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

    @XmlTransient
    public List<LaboratoryTestResult> getLaboratoryTestResultList() {
        return laboratoryTestResultList;
    }

    public void setLaboratoryTestResultList(List<LaboratoryTestResult> laboratoryTestResultList) {
        this.laboratoryTestResultList = laboratoryTestResultList;
    }

    @XmlTransient
    public List<Relative> getRelativeList() {
        return relativeList;
    }

    public void setRelativeList(List<Relative> relativeList) {
        this.relativeList = relativeList;
    }

    @XmlTransient
    public List<Appointment> getAppointmentList() {
        return appointmentList;
    }

    public void setAppointmentList(List<Appointment> appointmentList) {
        this.appointmentList = appointmentList;
    }

    @XmlTransient
    public List<Record> getRecordList() {
        return recordList;
    }

    public void setRecordList(List<Record> recordList) {
        this.recordList = recordList;
    }

    public List<Drug> getDrugList() {
        return drugList;
    }

    public void setDrugList(List<Drug> drugList) {
        this.drugList = drugList;
    }

    public List<PatientVaccine> getPatientVaccineList() {
        return patientVaccineList;
    }

    public void setPatientVaccineList(List<PatientVaccine> patientVaccineList) {
        this.patientVaccineList = patientVaccineList;
    }

    public List<AppointmentVaccine> getAppointmentVaccineList() {
        return appointmentVaccineList;
    }

    public void setAppointmentVaccineList(List<AppointmentVaccine> appointmentVaccineList) {
        this.appointmentVaccineList = appointmentVaccineList;
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
        return "pruebas1.Patient[ idPatient=" + idPatient + " ]";
    }
    
}