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
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Carlos Cortina
 */
@Entity
@Table(name = "consultationactivity")
@NamedQueries({
    @NamedQuery(name = "Consultationactivity.findAll", query = "SELECT c FROM Consultationactivity c")})
public class Consultationactivity implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ConsultationactivityPK consultationactivityPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cost")
    private double cost;
    @Basic(optional = false)
    @NotNull
    @Column(name = "includeInBill")
    private int includeInBill;
    @JoinColumn(name = "idConsultation", referencedColumnName = "idConsultation", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Consultation consultation;
    @JoinColumn(name = "idActivity", referencedColumnName = "IdActivity", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Activity activity;
    @Transient
    private double globalConsultorySum;
    @Transient
    private double globalExternalSum;
    @Transient
    private double globalTotal;
    @Transient
    private long count;

    public Consultationactivity() {
    }

    public Consultationactivity(ConsultationactivityPK consultationactivityPK) {
        this.consultationactivityPK = consultationactivityPK;
    }

    public Consultationactivity(Activity activity, double globalTotal, long count) {
        this.activity = activity;
        this.globalTotal = globalTotal;
        this.count = count;
    }

    public Consultationactivity(double globalConsultorySum, double globalExternalSum, double globalTotal) {
        this.globalConsultorySum = globalConsultorySum;
        this.globalExternalSum = globalExternalSum;
        this.globalTotal = globalTotal;
    }

    public Consultationactivity(double cost, Consultation consultation, Activity activity) {
        this.cost = cost;
        this.consultation = consultation;
        this.activity = activity;
    }

    public Consultationactivity(double cost, Activity activity, int includeInBill) {
        this.cost = cost;
        this.includeInBill = includeInBill;
        this.activity = activity;
    }

    public Consultationactivity(ConsultationactivityPK consultationactivityPK, double cost, int includeInBill) {
        this.consultationactivityPK = consultationactivityPK;
        this.cost = cost;
        this.includeInBill = includeInBill;
    }

    public Consultationactivity(int idConsultation, int idActivity) {
        this.consultationactivityPK = new ConsultationactivityPK(idConsultation, idActivity);
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

    public int getIncludeInBill() {
        return includeInBill;
    }

    public void setIncludeInBill(int includeInBill) {
        this.includeInBill = includeInBill;
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

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public double getGlobalConsultorySum() {
        return globalConsultorySum;
    }

    public void setGlobalConsultorySum(double globalConsultorySum) {
        this.globalConsultorySum = globalConsultorySum;
    }

    public double getGlobalExternalSum() {
        return globalExternalSum;
    }

    public void setGlobalExternalSum(double globalExternalSum) {
        this.globalExternalSum = globalExternalSum;
    }

    public double getGlobalTotal() {
        return globalTotal;
    }

    public void setGlobalTotal(double globalTotal) {
        this.globalTotal = globalTotal;
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
        return "com.carloscortina.demo.model.Consultationactivity[ consultationactivityPK=" + consultationactivityPK + " ]";
    }
    
}
