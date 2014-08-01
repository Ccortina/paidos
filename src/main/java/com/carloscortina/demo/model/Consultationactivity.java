/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.carloscortina.demo.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Carlos Cortina
 */
@Entity
@Table(name = "consultationactivity")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Consultationactivity.findAll", query = "SELECT c FROM Consultationactivity c"),
    @NamedQuery(name = "Consultationactivity.findByIdConsultation", query = "SELECT c FROM Consultationactivity c WHERE c.consultationactivityPK.idConsultation = :idConsultation"),
    @NamedQuery(name = "Consultationactivity.findByIdActivity", query = "SELECT c FROM Consultationactivity c WHERE c.consultationactivityPK.idActivity = :idActivity"),
    @NamedQuery(name = "Consultationactivity.findByCost", query = "SELECT c FROM Consultationactivity c WHERE c.cost = :cost")})
public class Consultationactivity implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ConsultationactivityPK consultationactivityPK;
    @Basic(optional = false)
    @Column(name = "cost")
    private double cost;
    @JoinColumn(name = "idConsultation", referencedColumnName = "idConsultation", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Consultation consultation;
    @JoinColumn(name = "idActivity", referencedColumnName = "IdActivity", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Activity activity;
    @Basic(optional = false)
    @Column(name = "includeInBill")
    private int includeInBill;

    public Consultationactivity() {
    }

    public Consultationactivity(ConsultationactivityPK consultationactivityPK) {
        this.consultationactivityPK = consultationactivityPK;
    }

    public Consultationactivity(ConsultationactivityPK consultationactivityPK, double cost, int includeInBill) {
        this.consultationactivityPK = consultationactivityPK;
        this.cost = cost;
        this.includeInBill = includeInBill;
    }
    
    public Consultationactivity(double cost, Consultation consultation, Activity activity, int includeInBill) {
        this.cost = cost;
        this.consultation = consultation;
        this.activity = activity;
        this.includeInBill = includeInBill;
        this.consultationactivityPK.setIdActivity(activity.getIdActivity());
        this.consultationactivityPK.setIdConsultation(consultation.getIdConsultation());
    }

    public Consultationactivity(double cost, Activity activity, int includeInBill) {
        this.cost = cost;
        this.activity = activity;
        this.includeInBill = includeInBill;
    }

    public ConsultationactivityPK getConsultationactivityPK() {
        return consultationactivityPK;
    }

    public void setConsultationactivityPK(ConsultationactivityPK consultationactivityPK) {
        this.consultationactivityPK = consultationactivityPK;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public Consultation getConsultation() {
        return consultation;
    }

    public void setConsultation(Consultation consultation) {
        this.consultation = consultation;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public int getIncludeInBill() {
        return includeInBill;
    }

    public void setIncludeInBill(int includeInBill) {
        this.includeInBill = includeInBill;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (consultationactivityPK != null ? consultationactivityPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Consultationactivity)) {
            return false;
        }
        Consultationactivity other = (Consultationactivity) object;
        if ((this.consultationactivityPK == null && other.consultationactivityPK != null) || (this.consultationactivityPK != null && !this.consultationactivityPK.equals(other.consultationactivityPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelos.Consultationactivity[ consultationactivityPK=" + consultationactivityPK + " ]";
    }
    
}
