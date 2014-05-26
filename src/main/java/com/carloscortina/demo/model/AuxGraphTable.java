/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.carloscortina.demo.model;

import java.util.Date;

/**
 *
 * @author Ccortina_Mac
 */
public class AuxGraphTable {

    int idConsultation;
    Date date = null;
    String age = "";
    double weight = 0;
    double size = 0;
    double pc = 0;
    double ta = 0;
    double ta2 = 0;
    double taaverage =0;
    double temperature = 0;
    double IMC = 0;

    public AuxGraphTable() {
        date = new Date();
    }

    public AuxGraphTable(int idConsultation,Date date,String age,double weight,
                            double size,double pc, double ta,double ta2,
                            double taaverage,double temperature,double IMC) {
        this.idConsultation = idConsultation;
        this.date = date;
        this.age = age;
        this.weight = weight;
        this.size = size;
        this.pc = pc;
        this.ta = ta;
        this.ta2 = ta2;
        this.taaverage = taaverage;
        this.temperature = temperature;
        this.IMC = IMC;
    }
    
    
    
    public int getIdConsultation() {
        return idConsultation;
    }

    public void setIdConsultation(int idConsultation) {
        this.idConsultation = idConsultation;
    }
    
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public double getPc() {
        return pc;
    }

    public void setPc(double pc) {
        this.pc = pc;
    }

    public double getTa() {
        return ta;
    }

    public void setTa(double ta) {
        this.ta = ta;
    }

    public double getTa2() {
        return ta2;
    }

    public void setTa2(double ta2) {
        this.ta2 = ta2;
    }

    public double getTaaverage() {
        return taaverage;
    }

    public void setTaaverage(double taaverage) {
        this.taaverage = taaverage;
    }
    
    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public double getIMC() {
        return IMC;
    }

    public void setIMC(double IMC) {
        this.IMC = IMC;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + (this.date != null ? this.date.hashCode() : 0);
        hash = 47 * hash + (this.age != null ? this.age.hashCode() : 0);
        hash = 47 * hash + (int) (Double.doubleToLongBits(this.weight) ^ (Double.doubleToLongBits(this.weight) >>> 32));
        hash = 47 * hash + (int) (Double.doubleToLongBits(this.size) ^ (Double.doubleToLongBits(this.size) >>> 32));
        hash = 47 * hash + (int) (Double.doubleToLongBits(this.pc) ^ (Double.doubleToLongBits(this.pc) >>> 32));
        hash = 47 * hash + (int) (Double.doubleToLongBits(this.IMC) ^ (Double.doubleToLongBits(this.IMC) >>> 32));
        
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final AuxGraphTable other = (AuxGraphTable) obj;
        if ((this.date == null) ? (other.date != null) : !this.date.equals(other.date)) {
            return false;
        }
        if ((this.age == null) ? (other.age != null) : !this.age.equals(other.age)) {
            return false;
        }
        if (Double.doubleToLongBits(this.weight) != Double.doubleToLongBits(other.weight)) {
            return false;
        }
        if (Double.doubleToLongBits(this.size) != Double.doubleToLongBits(other.size)) {
            return false;
        }
        if (Double.doubleToLongBits(this.pc) != Double.doubleToLongBits(other.pc)) {
            return false;
        }
        if (Double.doubleToLongBits(this.temperature) != Double.doubleToLongBits(other.temperature)) {
            return false;
        }
        if (Double.doubleToLongBits(this.IMC) != Double.doubleToLongBits(other.IMC)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "AuxGraphTable{" + "date=" + date + ", age=" + age + ", weight=" + weight + ", size=" + size + ", pc=" + pc + ", ta=" + ta + ", temperature=" + temperature + ", IMC=" + IMC + '}';
    }
    
    
}
