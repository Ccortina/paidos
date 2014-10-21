/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.carloscortina.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

/**
 *
 * @author Carlos Cortina
 */
@Entity
@Table(name = "consultation")
@NamedQueries({
    @NamedQuery(name = "Consultation.findAll", query = "SELECT c FROM Consultation c")})
public class Consultation implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idConsultation")
    private Integer idConsultation;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "weigth")
    private Double weigth;
    @Column(name = "size")
    private Double size;
    @Column(name = "bmi")
    private Double bmi;
    @Column(name = "temperature")
    private Double temperature;
    @Column(name = "pc")
    private Double pc;
    @Column(name = "ta")
    private Double ta;
    @Column(name = "ta2")
    private Double ta2;
    @Column(name = "taAverage")
    private Double taAverage;
    @Size(max = 65535)
    @Column(name = "peea")
    private String peea;
    @Size(max = 65535)
    @Column(name = "ef")
    private String ef;
    @Size(max = 65535)
    @Column(name = "prescription")
    private String prescription;
    @Size(max = 65535)
    @Column(name = "prescriptionNotes")
    private String prescriptionNotes;
    @Column(name = "prescriptionNumber")
    private Integer prescriptionNumber;
    @Column(name = "tempClaveConsulta")
    private Integer tempClaveConsulta;
    @JoinColumn(name = "type", referencedColumnName = "idConsultationType")
    @ManyToOne
    private Consultationtype type;
    @Size(max = 65535)
    @Column(name = "abstract")
    private String abstract1;
    @JsonIgnore
    @JoinTable(name = "consultationdiagnostic", joinColumns = {
        @JoinColumn(name = "idConsultation", referencedColumnName = "idConsultation")}, inverseJoinColumns = {
        @JoinColumn(name = "idDiagnostic", referencedColumnName = "idDiagnostic")})
    @ManyToMany
    private List<Diagnostic> diagnosticList;
    @JoinColumn(name = "idPatient", referencedColumnName = "idPatient")
    @ManyToOne(optional = false)
    private Patient idPatient;
    @JoinColumn(name = "idDoctor", referencedColumnName = "idUser")
    @ManyToOne(optional = false)
    private User idDoctor;
    @JoinColumn(name = "idAppointment", referencedColumnName = "idAppointment")
    @ManyToOne
    private Appointment idAppointment;
    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "consultation")
    private List<Consultationactivity> consultationactivityList;
    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "consultation")
    private List<Consultationmeasure> consultationmeasureList;
    @JsonIgnore
    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "idConsultation")
    private List<Consultationcostabstract> consultationcostabstractList;

    public Consultation() {
    }

    public Consultation(Integer idConsultation) {
        this.idConsultation = idConsultation;
    }

    public Consultation(Integer idConsultation, Patient idPatient, User idDoctor, List<Consultationactivity> consultationactivityList) {
        this.idConsultation = idConsultation;
        this.idPatient = idPatient;
        this.idDoctor = idDoctor;
        this.consultationactivityList = consultationactivityList;
    }

    public Consultation(Double weigth, Double size, Double bmi, Double temperature, Double pc, Patient idPatient, Appointment idAppointment) {
        this.weigth = weigth;
        this.size = size;
        this.bmi = bmi;
        this.temperature = temperature;
        this.pc = pc;
        this.idPatient = idPatient;
        this.idAppointment = idAppointment;
    }

    public Consultation(Integer idConsultation, Double weigth, Double size,Double bmi, Double temperature, Double pc, Double ta, Double ta2, Double taAverage,
            String peea, String ef, String prescription, Appointment idAppointment,String abstract1,Consultationtype type,int prescriptionNumber) {
        this.idConsultation = idConsultation;
        this.weigth = weigth;
        this.size = size;
        this.temperature = temperature;
        this.pc = pc;
        this.ta = ta;
        this.ta2 = ta2;
        this.taAverage = taAverage;
        this.peea = peea;
        this.ef = ef;
        this.prescription = prescription;
        this.idAppointment = idAppointment;
        this.abstract1 = abstract1;
        this.type = type;
        this.bmi=bmi;
        this.prescriptionNumber=prescriptionNumber;
    }

    public Consultation(Double weigth, Double size, Double bmi, Double temperature, Double pc, Double ta, Double ta2, Double taAverage, String peea, String ef, String prescription, Integer prescriptionNumber, Consultationtype type, String abstract1, Patient idPatient, User idDoctor, Appointment idAppointment) {
        this.weigth = weigth;
        this.size = size;
        this.bmi = bmi;
        this.temperature = temperature;
        this.pc = pc;
        this.ta = ta;
        this.ta2 = ta2;
        this.taAverage = taAverage;
        this.peea = peea;
        this.ef = ef;
        this.prescription = prescription;
        this.prescriptionNumber = prescriptionNumber;
        this.type = type;
        this.abstract1 = abstract1;
        this.idPatient = idPatient;
        this.idDoctor = idDoctor;
        this.idAppointment = idAppointment;
    }
    
    public Consultation(Appointment idAppointment) {
        this.idAppointment = idAppointment;
    }

    public Integer getIdConsultation() {
        return idConsultation;
    }

    public void setIdConsultation(Integer idConsultation) {
        this.idConsultation = idConsultation;
    }

    public Double getWeigth() {
        return weigth;
    }

    public void setWeigth(Double weigth) {
        this.weigth = weigth;
    }

    public Double getSize() {
        return size;
    }

    public void setSize(Double size) {
        this.size = size;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public Double getPc() {
        return pc;
    }

    public void setPc(Double pc) {
        this.pc = pc;
    }

    public Double getTa() {
        return ta;
    }

    public void setTa(Double ta) {
        this.ta = ta;
    }

    public Double getTa2() {
        return ta2;
    }

    public void setTa2(Double ta2) {
        this.ta2 = ta2;
    }

    public Double getTaAverage() {
        return taAverage;
    }

    public void setTaAverage(Double taAverage) {
        this.taAverage = taAverage;
    }

    public String getPeea() {
        return peea;
    }

    public void setPeea(String peea) {
        this.peea = peea;
    }

    public String getEf() {
        return ef;
    }

    public void setEf(String ef) {
        this.ef = ef;
    }

    public String getPrescription() {
        return prescription;
    }

    public void setPrescription(String prescription) {
        this.prescription = prescription;
    }

    public String getPrescriptionNotes() {
        return prescriptionNotes;
    }

    public void setPrescriptionNotes(String prescriptionNotes) {
        this.prescriptionNotes = prescriptionNotes;
    }

    public Integer getPrescriptionNumber() {
        return prescriptionNumber;
    }

    public void setPrescriptionNumber(Integer prescriptionNumber) {
        this.prescriptionNumber = prescriptionNumber;
    }

    public String getAbstract1() {
        return abstract1;
    }

    public void setAbstract1(String abstract1) {
        this.abstract1 = abstract1;
    }

    public List<Diagnostic> getDiagnosticList() {
        return diagnosticList;
    }

    public void setDiagnosticList(List<Diagnostic> diagnosticList) {
        this.diagnosticList = diagnosticList;
    }

    public Patient getIdPatient() {
        return idPatient;
    }

    public void setIdPatient(Patient idPatient) {
        this.idPatient = idPatient;
    }

    public User getIdDoctor() {
        return idDoctor;
    }

    public void setIdDoctor(User idDoctor) {
        this.idDoctor = idDoctor;
    }

    public Appointment getIdAppointment() {
        return idAppointment;
    }

    public void setIdAppointment(Appointment idAppointment) {
        this.idAppointment = idAppointment;
    }

    public List<Consultationactivity> getConsultationactivityList() {
        return consultationactivityList;
    }

    public void setConsultationactivityList(List<Consultationactivity> consultationactivityList) {
        this.consultationactivityList = consultationactivityList;
    }

    public List<Consultationmeasure> getConsultationmeasureList() {
        return consultationmeasureList;
    }

    public void setConsultationmeasureList(List<Consultationmeasure> consultationmeasureList) {
        this.consultationmeasureList = consultationmeasureList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idConsultation != null ? idConsultation.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Consultation)) {
            return false;
        }
        Consultation other = (Consultation) object;
        if ((this.idConsultation == null && other.idConsultation != null) || (this.idConsultation != null && !this.idConsultation.equals(other.idConsultation))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.carloscortina.demo.model.Consultation[ idConsultation=" + idConsultation + " ]";
    }

    public Integer getTempClaveConsulta() {
        return tempClaveConsulta;
    }

    public void setTempClaveConsulta(Integer tempClaveConsulta) {
        this.tempClaveConsulta = tempClaveConsulta;
    }

    public Consultationtype getType() {
        return type;
    }

    public void setType(Consultationtype type) {
        this.type = type;
    }

    public Double getBmi() {
        return bmi;
    }

    public void setBmi(Double bmi) {
        this.bmi = bmi;
    }

    public List<Consultationcostabstract> getConsultationcostabstractList() {
        return consultationcostabstractList;
    }

    public void setConsultationcostabstractList(List<Consultationcostabstract> consultationcostabstractList) {
        this.consultationcostabstractList = consultationcostabstractList;
    }
    
}
