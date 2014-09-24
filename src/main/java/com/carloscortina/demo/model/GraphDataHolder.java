/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.carloscortina.demo.model;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Carlos Cortina
 */
public class GraphDataHolder {

    Map<Double,Double> p3;
    Map<Double,Double> p5;
    Map<Double,Double> p10;
    Map<Double,Double> p25;
    Map<Double,Double> p50;
    Map<Double,Double> p75;
    Map<Double,Double> p90;
    Map<Double,Double> p95;
    Map<Double,Double> p97;
    Map<Double,Double> pPatient;

    public GraphDataHolder() {
        p3 = new HashMap<Double, Double>();
        p5 = new HashMap<Double, Double>();
        p10 = new HashMap<Double, Double>();
        p25 = new HashMap<Double, Double>();
        p50 = new HashMap<Double, Double>();
        p75 = new HashMap<Double, Double>();
        p90 = new HashMap<Double, Double>();
        p95 = new HashMap<Double, Double>();
        p97 = new HashMap<Double, Double>();
        pPatient = new HashMap<Double, Double>();
    }

    public GraphDataHolder(Map<Double, Double> p3, Map<Double, Double> p5, Map<Double, Double> p10, 
            Map<Double, Double> p25, Map<Double, Double> p50, Map<Double, Double> p75, Map<Double, 
                    Double> p90, Map<Double, Double> p95, Map<Double, Double> p97, Map<Double, Double> pPatient) {
        this.p3 = p3;
        this.p5 = p5;
        this.p10 = p10;
        this.p25 = p25;
        this.p50 = p50;
        this.p75 = p75;
        this.p90 = p90;
        this.p95 = p95;
        this.p97 = p97;
        this.pPatient = pPatient;
    }

    public Map<Double, Double> getP3() {
        return p3;
    }

    public void setP3(Map<Double, Double> p3) {
        this.p3 = p3;
    }

    public Map<Double, Double> getP5() {
        return p5;
    }

    public void setP5(Map<Double, Double> p5) {
        this.p5 = p5;
    }

    public Map<Double, Double> getP10() {
        return p10;
    }

    public void setP10(Map<Double, Double> p10) {
        this.p10 = p10;
    }

    public Map<Double, Double> getP25() {
        return p25;
    }

    public void setP25(Map<Double, Double> p25) {
        this.p25 = p25;
    }

    public Map<Double, Double> getP50() {
        return p50;
    }

    public void setP50(Map<Double, Double> p50) {
        this.p50 = p50;
    }

    public Map<Double, Double> getP75() {
        return p75;
    }

    public void setP75(Map<Double, Double> p75) {
        this.p75 = p75;
    }

    public Map<Double, Double> getP90() {
        return p90;
    }

    public void setP90(Map<Double, Double> p90) {
        this.p90 = p90;
    }

    public Map<Double, Double> getP95() {
        return p95;
    }

    public void setP95(Map<Double, Double> p95) {
        this.p95 = p95;
    }

    public Map<Double, Double> getP97() {
        return p97;
    }

    public void setP97(Map<Double, Double> p97) {
        this.p97 = p97;
    }

    public Map<Double, Double> getpPatient() {
        return pPatient;
    }

    public void setpPatient(Map<Double, Double> pPatient) {
        this.pPatient = pPatient;
    }
    
    public void addData(double month,double p3,double p5, double p10, double p25,double p50,
            double p75, double p90, double p95, double p97){
    
        this.p3.put(month, p3);
        this.p5.put(month, p5);
        this.p10.put(month, p10);
        this.p25.put(month, p25);
        this.p50.put(month, p50);
        this.p75.put(month, p75);
        this.p90.put(month, p90);
        this.p95.put(month, p95);
        this.p97.put(month, p97); 
    }
    
    public void addPatientData(double month,double data){
        this.pPatient.put(month, data);
    }
}
