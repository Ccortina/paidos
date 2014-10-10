/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.carloscortina.demo.model;

/**
 *
 * @author Carlos Cortina
 */
public class ReceiptTotal {
    private double fromReceiptNumber;
    private double toReceiptNumber;
    private double total;
    private User expeditor;

    public ReceiptTotal() {
    }

    public ReceiptTotal(double fromReceiptNumber, double toReceiptNumber, double total, User expeditor) {
        this.fromReceiptNumber = fromReceiptNumber;
        this.toReceiptNumber = toReceiptNumber;
        this.total = total;
        this.expeditor = expeditor;
    }
    
    public double getFromReceiptNumber() {
        return fromReceiptNumber;
    }

    public void setFromReceiptNumber(double fromReceiptNumber) {
        this.fromReceiptNumber = fromReceiptNumber;
    }

    public double getToReceiptNumber() {
        return toReceiptNumber;
    }

    public void setToReceiptNumber(double toReceiptNumber) {
        this.toReceiptNumber = toReceiptNumber;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public User getExpeditor() {
        return expeditor;
    }

    public void setExpeditor(User expeditor) {
        this.expeditor = expeditor;
    }
    
    
}
