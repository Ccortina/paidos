/*
 * To change this template, choose Tools | Templates
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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
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
@Table(name = "consultation")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Consultation.findAll", query = "SELECT c FROM Consultation c"),
    @NamedQuery(name = "Consultation.findByIdConsultation", query = "SELECT c FROM Consultation c WHERE c.idConsultation = :idConsultation"),
    @NamedQuery(name = "Consultation.findByMotive", query = "SELECT c FROM Consultation c WHERE c.motive = :motive"),
    @NamedQuery(name = "Consultation.findByWeigth", query = "SELECT c FROM Consultation c WHERE c.weigth = :weigth"),
    @NamedQuery(name = "Consultation.findBySize", query = "SELECT c FROM Consultation c WHERE c.size = :size"),
    @NamedQuery(name = "Consultation.findByTemperature", query = "SELECT c FROM Consultation c WHERE c.temperature = :temperature"),
    @NamedQuery(name = "Consultation.findByPc", query = "SELECT c FROM Consultation c WHERE c.pc = :pc"),
    @NamedQuery(name = "Consultation.findByTa", query = "SELECT c FROM Consultation c WHERE c.ta = :ta"),
    @NamedQuery(name = "Consultation.findByTa2", query = "SELECT c FROM Consultation c WHERE c.ta2 = :ta2"),
    @NamedQuery(name = "Consultation.findByTaAverage", query = "SELECT c FROM Consultation c WHERE c.taAverage = :taAverage"),
    @NamedQuery(name = "Consultation.findByPeea", query = "SELECT c FROM Consultation c WHERE c.peea = :peea"),
    @NamedQuery(name = "Consultation.findByEf", query = "SELECT c FROM Consultation c WHERE c.ef = :ef")})
public class Consultation implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idConsultation")
    private Integer idConsultation;
    @Column(name = "motive")
    private String motive;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "weigth")
    private Double weigth;
    @Column(name = "size")
    private Double size;
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
    @Column(name = "peea")
    private String peea;
    @Column(name = "ef")
    private String ef;
    @Column(name = "prescription")
    private String prescription;
    @Column(name = "prescriptionNotes")
    private String prescriptionNotes;
    @Column(name = "prescriptionNumber")
    private Integer prescriptionNumber;
    @JoinColumn(name = "idPatient", referencedColumnName = "idPatient")
    @ManyToOne(optional = false)
    private Patient idPatient;
    @JoinColumn(name = "idDoctor", referencedColumnName = "idUser")
    @ManyToOne(optional = false)
    private User idDoctor;
    @JoinColumn(name = "idAppointment", referencedColumnName = "idAppointment")
    @ManyToOne(optional = false)
    private Appointment idAppointment;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "consultation")
    private List<ConsultationDiagnostic> consultationdiagnosticList;
    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "consultation")
    private List<Consultationactivity> consultationactivityList;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "consultation")
    private List<Consultationmeasure> consultationmeasureList;
    

    public Consultation() {
    }

    public Consultation(Integer idConsultation) {
        this.idConsultation = idConsultation;
    }

    public Consultation(Integer idConsultation, Double weigth, Double size, Double temperature, Double pc, Double ta, Double ta2, Double taAverage, String peea, String ef, String prescription, Appointment idAppointment) {
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
    }

    public Integer getIdConsultation() {
        return idConsultation;
    }

    public void setIdConsultation(Integer idConsultation) {
        this.idConsultation = idConsultation;
    }

    public String getMotive() {
        return motive;
    }

    public void setMotive(String motive) {
        this.motive = motive;
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

    @XmlTransient
    public List<Consultationactivity> getConsultationactivityList() {
        return consultationactivityList;
    }

    public void setConsultationactivityList(List<Consultationactivity> consultationactivityList) {
        this.consultationactivityList = consultationactivityList;
    }

    @XmlTransient
    public List<Consultationmeasure> getConsultationmeasureList() {
        return consultationmeasureList;
    }

    @XmlTransient
    public void setConsultationmeasureList(List<Consultationmeasure> consultationmeasureList) {
        this.consultationmeasureList = consultationmeasureList;
    }

    public List<ConsultationDiagnostic> getConsultationdiagnosticList() {
        return consultationdiagnosticList;
    }

    public void setConsultationdiagnosticList(List<ConsultationDiagnostic> consultationdiagnosticList) {
        this.consultationdiagnosticList = consultationdiagnosticList;
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
        return "pruebas1.Consultation[ idConsultation=" + idConsultation + " ]";
    }
    
}
