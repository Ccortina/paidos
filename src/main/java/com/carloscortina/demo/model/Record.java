/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.carloscortina.demo.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author Carlos Cortina
 */
@Entity
@Table(name = "record")
@NamedQueries({
    @NamedQuery(name = "Record.findAll", query = "SELECT r FROM Record r")})
public class Record implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idRecord")
    private Integer idRecord;
    @Size(max = 65535)
    @Column(name = "perinatalBackground")
    private String perinatalBackground;
    @Size(max = 65535)
    @Column(name = "developmentBackground")
    private String developmentBackground;
    @Size(max = 65535)
    @Column(name = "surgicalHistory")
    private String surgicalHistory;
    @Size(max = 65535)
    @Column(name = "hereditaryAndFamilyBackground")
    private String hereditaryAndFamilyBackground;
    @Size(max = 65535)
    @Column(name = "others")
    private String others;
    @Size(max = 65535)
    @Column(name = "pathologicalBackgorund")
    private String pathologicalBackgorund;
    @Size(max = 65535)
    @Column(name = "alergicBackground")
    private String alergicBackground;
    @JoinColumn(name = "idPerBackNoPat", referencedColumnName = "idPerinatalBackground")
    @ManyToOne(optional = false)
    private Perbacknopat idPerBackNoPat;
    @JoinColumn(name = "idPatient", referencedColumnName = "idPatient")
    @ManyToOne(optional = false)
    private Patient idPatient;

    public Record() {
    }

    public Record(Integer idRecord) {
        this.idRecord = idRecord;
    }

    public Record(Perbacknopat idPerBackNoPat, Patient idPatient) {
        this.idPerBackNoPat = idPerBackNoPat;
        this.idPatient = idPatient;
    }

    public Integer getIdRecord() {
        return idRecord;
    }

    public void setIdRecord(Integer idRecord) {
        this.idRecord = idRecord;
    }

    public String getPerinatalBackground() {
        return perinatalBackground;
    }

    public void setPerinatalBackground(String perinatalBackground) {
        this.perinatalBackground = perinatalBackground;
    }

    public String getDevelopmentBackground() {
        return developmentBackground;
    }

    public void setDevelopmentBackground(String developmentBackground) {
        this.developmentBackground = developmentBackground;
    }

    public String getSurgicalHistory() {
        return surgicalHistory;
    }

    public void setSurgicalHistory(String surgicalHistory) {
        this.surgicalHistory = surgicalHistory;
    }

    public String getHereditaryAndFamilyBackground() {
        return hereditaryAndFamilyBackground;
    }

    public void setHereditaryAndFamilyBackground(String hereditaryAndFamilyBackground) {
        this.hereditaryAndFamilyBackground = hereditaryAndFamilyBackground;
    }

    public String getOthers() {
        return others;
    }

    public void setOthers(String others) {
        this.others = others;
    }

    public String getPathologicalBackgorund() {
        return pathologicalBackgorund;
    }

    public void setPathologicalBackgorund(String pathologicalBackgorund) {
        this.pathologicalBackgorund = pathologicalBackgorund;
    }

    public String getAlergicBackground() {
        return alergicBackground;
    }

    public void setAlergicBackground(String alergicBackground) {
        this.alergicBackground = alergicBackground;
    }

    public Perbacknopat getIdPerBackNoPat() {
        return idPerBackNoPat;
    }

    public void setIdPerBackNoPat(Perbacknopat idPerBackNoPat) {
        this.idPerBackNoPat = idPerBackNoPat;
    }

    public Patient getIdPatient() {
        return idPatient;
    }

    public void setIdPatient(Patient idPatient) {
        this.idPatient = idPatient;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRecord != null ? idRecord.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Record)) {
            return false;
        }
        Record other = (Record) object;
        if ((this.idRecord == null && other.idRecord != null) || (this.idRecord != null && !this.idRecord.equals(other.idRecord))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.carloscortina.demo.model.Record[ idRecord=" + idRecord + " ]";
    }
    
}
