package com.carloscortina.demo.model;

import java.io.Serializable;
import java.util.Collection;
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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author carloscortina
 */
@Entity
@Table(name = "consultation")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Consultation.findAll", query = "SELECT c FROM Consultation c"),
    @NamedQuery(name = "Consultation.findByIdConsultation", query = "SELECT c FROM Consultation c WHERE c.idConsultation = :idConsultation"),
    @NamedQuery(name = "Consultation.findByMotive", query = "SELECT c FROM Consultation c WHERE c.motive = :motive")})
public class Consultation implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idConsultation")
    private Integer idConsultation;
    @Size(max = 100)
    @Column(name = "motive")
    private String motive;
    @JoinColumn(name = "idPatient", referencedColumnName = "idPatient")
    @ManyToOne(optional = false)
    private Patient idPatient;
    @JoinColumn(name = "idDoctor", referencedColumnName = "idStaffMember")
    @ManyToOne(optional = false)
    private StaffMember idDoctor;
    @JoinColumn(name = "idAppointment", referencedColumnName = "idAppointment")
    @ManyToOne(optional = false)
    private Appointment idAppointment;
    @ManyToMany(mappedBy = "consultationCollection")
    private Collection<Activity> activityCollection;
    @Column(name = "weigth")
    private Integer weigth;
    @Column(name = "size")
    private Integer kidSize;
    @Column(name = "temperature")
    private Integer temperature;
    @Column(name = "pc")
    private Integer pc;
    @Column(name = "ta")
    private Integer ta;
    @Column(name = "ta2")
    private String ta2;
    @Column(name = "taAverage")
    private String taAverage;

    public Consultation() {
    }

    public Consultation(Integer idConsultation) {
        this.idConsultation = idConsultation;
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
    
    public Patient getIdPatient() {
        return idPatient;
    }

    public void setIdPatient(Patient idPatient) {
        this.idPatient = idPatient;
    }

    public StaffMember getIdDoctor() {
        return idDoctor;
    }

    public void setIdDoctor(StaffMember idDoctor) {
        this.idDoctor = idDoctor;
    }

    public Appointment getIdAppointment() {
        return idAppointment;
    }

    public void setIdAppointment(Appointment idAppointment) {
        this.idAppointment = idAppointment;
    }

    @XmlTransient
    public Collection<Activity> getActivityCollection() {
        return activityCollection;
    }

    public void setActivityCollection(Collection<Activity> activityCollection) {
        this.activityCollection = activityCollection;
    }
    
    public Integer getWeigth() {
        return weigth;
    }

    public void setWeigth(Integer weigth) {
        this.weigth = weigth;
    }

    public Integer getSize() {
        return kidSize;
    }

    public void setSize(Integer size) {
        this.kidSize = size;
    }

    public Integer getTemperature() {
        return temperature;
    }

    public void setTemperature(Integer temperature) {
        this.temperature = temperature;
    }

    public Integer getPc() {
        return pc;
    }

    public void setPc(Integer pc) {
        this.pc = pc;
    }

    public Integer getTa() {
        return ta;
    }

    public void setTa(Integer ta) {
        this.ta = ta;
    }

    public String getTa2() {
        return ta2;
    }

    public void setTa2(String ta2) {
        this.ta2 = ta2;
    }

    public String getTaAverage() {
        return taAverage;
    }

    public void setTaAverage(String taAverage) {
        this.taAverage = taAverage;
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
        return "diagnostic.Consultation[ idConsultation=" + idConsultation + " ]";
    }
    
}

