/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.carloscortina.demo.model;

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
import javax.validation.constraints.Size;

/**
 *
 * @author Carlos Cortina
 */
@Entity
@Table(name = "perbacknopat")
@NamedQueries({
    @NamedQuery(name = "Perbacknopat.findAll", query = "SELECT p FROM Perbacknopat p")})
public class Perbacknopat implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idPerinatalBackground")
    private Integer idPerinatalBackground;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "birthweight")
    private Double birthweight;
    @Column(name = "birthsize")
    private Double birthsize;
    @Column(name = "headCircumference")
    private Double headCircumference;
    @Column(name = "apgar1Minute")
    private Integer apgar1Minute;
    @Column(name = "apgar5Minute")
    private Integer apgar5Minute;
    @Column(name = "apgar10Minute")
    private Integer apgar10Minute;
    @Column(name = "breastFeed")
    private Integer breastFeed;
    @Size(max = 45)
    @Column(name = "supplemented")
    private String supplemented;
    @Column(name = "supplementedAt")
    private Integer supplementedAt;
    @Column(name = "weaning")
    private Integer weaning;
    @Size(max = 100)
    @Column(name = "currentlyEats")
    private String currentlyEats;
    @Size(max = 100)
    @Column(name = "intolerance")
    private String intolerance;
    @Size(max = 45)
    @Column(name = "motherAge")
    private String motherAge;
    @Column(name = "gestationNumber")
    private Integer gestationNumber;
    @Column(name = "births")
    private Integer births;
    @Column(name = "abortions")
    private Integer abortions;
    @Column(name = "cesareanNumber")
    private Integer cesareanNumber;
    @Column(name = "followsObjects")
    private Integer followsObjects;
    @Column(name = "smiles")
    private Integer smiles;
    @Column(name = "crawls")
    private Integer crawls;
    @Column(name = "standsUp")
    private Integer standsUp;
    @Column(name = "disyllabics")
    private Integer disyllabics;
    @Column(name = "holdsHead")
    private Integer holdsHead;
    @Column(name = "rolls")
    private Integer rolls;
    @Column(name = "sitsDown")
    private Integer sitsDown;
    @Column(name = "sphincterControl")
    private Integer sphincterControl;
    @Column(name = "wanders")
    private Integer wanders;
    @Size(max = 65535)
    @Column(name = "positiveFacts")
    private String positiveFacts;
    @JoinColumn(name = "birthMethod", referencedColumnName = "idBirthMethod")
    @ManyToOne
    private Birthmethod birthMethod;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPerBackNoPat")
    private List<Record> recordList;

    public Perbacknopat() {
    }

    public Perbacknopat(Integer idPerinatalBackground) {
        this.idPerinatalBackground = idPerinatalBackground;
    }

    public Integer getIdPerinatalBackground() {
        return idPerinatalBackground;
    }

    public void setIdPerinatalBackground(Integer idPerinatalBackground) {
        this.idPerinatalBackground = idPerinatalBackground;
    }

    public Double getBirthweight() {
        return birthweight;
    }

    public void setBirthweight(Double birthweight) {
        this.birthweight = birthweight;
    }

    public Double getBirthsize() {
        return birthsize;
    }

    public void setBirthsize(Double birthsize) {
        this.birthsize = birthsize;
    }

    public Double getHeadCircumference() {
        return headCircumference;
    }

    public void setHeadCircumference(Double headCircumference) {
        this.headCircumference = headCircumference;
    }

    public Integer getApgar1Minute() {
        return apgar1Minute;
    }

    public void setApgar1Minute(Integer apgar1Minute) {
        this.apgar1Minute = apgar1Minute;
    }

    public Integer getApgar5Minute() {
        return apgar5Minute;
    }

    public void setApgar5Minute(Integer apgar5Minute) {
        this.apgar5Minute = apgar5Minute;
    }

    public Integer getApgar10Minute() {
        return apgar10Minute;
    }

    public void setApgar10Minute(Integer apgar10Minute) {
        this.apgar10Minute = apgar10Minute;
    }

    public Integer getBreastFeed() {
        return breastFeed;
    }

    public void setBreastFeed(Integer breastFeed) {
        this.breastFeed = breastFeed;
    }

    public String getSupplemented() {
        return supplemented;
    }

    public void setSupplemented(String supplemented) {
        this.supplemented = supplemented;
    }

    public Integer getSupplementedAt() {
        return supplementedAt;
    }

    public void setSupplementedAt(Integer supplementedAt) {
        this.supplementedAt = supplementedAt;
    }

    public Integer getWeaning() {
        return weaning;
    }

    public void setWeaning(Integer weaning) {
        this.weaning = weaning;
    }

    public String getCurrentlyEats() {
        return currentlyEats;
    }

    public void setCurrentlyEats(String currentlyEats) {
        this.currentlyEats = currentlyEats;
    }

    public String getIntolerance() {
        return intolerance;
    }

    public void setIntolerance(String intolerance) {
        this.intolerance = intolerance;
    }

    public String getMotherAge() {
        return motherAge;
    }

    public void setMotherAge(String motherAge) {
        this.motherAge = motherAge;
    }

    public Integer getGestationNumber() {
        return gestationNumber;
    }

    public void setGestationNumber(Integer gestationNumber) {
        this.gestationNumber = gestationNumber;
    }

    public Integer getBirths() {
        return births;
    }

    public void setBirths(Integer births) {
        this.births = births;
    }

    public Integer getAbortions() {
        return abortions;
    }

    public void setAbortions(Integer abortions) {
        this.abortions = abortions;
    }

    public Integer getCesareanNumber() {
        return cesareanNumber;
    }

    public void setCesareanNumber(Integer cesareanNumber) {
        this.cesareanNumber = cesareanNumber;
    }

    public Integer getFollowsObjects() {
        return followsObjects;
    }

    public void setFollowsObjects(Integer followsObjects) {
        this.followsObjects = followsObjects;
    }

    public Integer getSmiles() {
        return smiles;
    }

    public void setSmiles(Integer smiles) {
        this.smiles = smiles;
    }

    public Integer getCrawls() {
        return crawls;
    }

    public void setCrawls(Integer crawls) {
        this.crawls = crawls;
    }

    public Integer getStandsUp() {
        return standsUp;
    }

    public void setStandsUp(Integer standsUp) {
        this.standsUp = standsUp;
    }

    public Integer getDisyllabics() {
        return disyllabics;
    }

    public void setDisyllabics(Integer disyllabics) {
        this.disyllabics = disyllabics;
    }

    public Integer getHoldsHead() {
        return holdsHead;
    }

    public void setHoldsHead(Integer holdsHead) {
        this.holdsHead = holdsHead;
    }

    public Integer getRolls() {
        return rolls;
    }

    public void setRolls(Integer rolls) {
        this.rolls = rolls;
    }

    public Integer getSitsDown() {
        return sitsDown;
    }

    public void setSitsDown(Integer sitsDown) {
        this.sitsDown = sitsDown;
    }

    public Integer getSphincterControl() {
        return sphincterControl;
    }

    public void setSphincterControl(Integer sphincterControl) {
        this.sphincterControl = sphincterControl;
    }

    public Integer getWanders() {
        return wanders;
    }

    public void setWanders(Integer wanders) {
        this.wanders = wanders;
    }

    public String getPositiveFacts() {
        return positiveFacts;
    }

    public void setPositiveFacts(String positiveFacts) {
        this.positiveFacts = positiveFacts;
    }

    public Birthmethod getBirthMethod() {
        return birthMethod;
    }

    public void setBirthMethod(Birthmethod birthMethod) {
        this.birthMethod = birthMethod;
    }

    public List<Record> getRecordList() {
        return recordList;
    }

    public void setRecordList(List<Record> recordList) {
        this.recordList = recordList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPerinatalBackground != null ? idPerinatalBackground.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Perbacknopat)) {
            return false;
        }
        Perbacknopat other = (Perbacknopat) object;
        if ((this.idPerinatalBackground == null && other.idPerinatalBackground != null) || (this.idPerinatalBackground != null && !this.idPerinatalBackground.equals(other.idPerinatalBackground))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.carloscortina.demo.model.Perbacknopat[ idPerinatalBackground=" + idPerinatalBackground + " ]";
    }
    
}
