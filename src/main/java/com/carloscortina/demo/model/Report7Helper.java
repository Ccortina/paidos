/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.carloscortina.demo.model;

import java.util.Date;

/**
 *
 * @author Carlos Cortina
 */
public class Report7Helper {
    
    private Activity activity;
    private Patient patient;
    private Date consutlationDate;
    private Date paymentDate;
    private double value;

    public Report7Helper(Activity activity, Patient patient, Date consutlationDate, Date paymentDate, double value) {
        this.activity = activity;
        this.patient = patient;
        this.consutlationDate = consutlationDate;
        this.paymentDate = paymentDate;
        this.value = value;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Date getConsutlationDate() {
        return consutlationDate;
    }

    public void setConsutlationDate(Date consutlationDate) {
        this.consutlationDate = consutlationDate;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
    
    
    
}
