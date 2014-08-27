/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.carloscortina.demo.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
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
@Table(name = "incompatibledrugs")
@NamedQueries({
    @NamedQuery(name = "Incompatibledrugs.findAll", query = "SELECT i FROM Incompatibledrugs i")})
public class Incompatibledrugs implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected IncompatibledrugsPK incompatibledrugsPK;
    @Size(max = 65535)
    @Column(name = "risk")
    private String risk;
    @JoinColumn(name = "idIncompatibleDrug", referencedColumnName = "idcommercialName", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Commercialname commercialname;
    @JoinColumn(name = "idDrug", referencedColumnName = "idDrug", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Drug drug;

    public Incompatibledrugs() {
    }

    public Incompatibledrugs(IncompatibledrugsPK incompatibledrugsPK) {
        this.incompatibledrugsPK = incompatibledrugsPK;
    }

    public Incompatibledrugs(int idDrug, int idIncompatibleDrug) {
        this.incompatibledrugsPK = new IncompatibledrugsPK(idDrug, idIncompatibleDrug);
    }

    public IncompatibledrugsPK getIncompatibledrugsPK() {
        return incompatibledrugsPK;
    }

    public void setIncompatibledrugsPK(IncompatibledrugsPK incompatibledrugsPK) {
        this.incompatibledrugsPK = incompatibledrugsPK;
    }

    public String getRisk() {
        return risk;
    }

    public void setRisk(String risk) {
        this.risk = risk;
    }

    public Commercialname getCommercialname() {
        return commercialname;
    }

    public void setCommercialname(Commercialname commercialname) {
        this.commercialname = commercialname;
    }

    public Drug getDrug() {
        return drug;
    }

    public void setDrug(Drug drug) {
        this.drug = drug;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (incompatibledrugsPK != null ? incompatibledrugsPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Incompatibledrugs)) {
            return false;
        }
        Incompatibledrugs other = (Incompatibledrugs) object;
        if ((this.incompatibledrugsPK == null && other.incompatibledrugsPK != null) || (this.incompatibledrugsPK != null && !this.incompatibledrugsPK.equals(other.incompatibledrugsPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.carloscortina.demo.model.Incompatibledrugs[ incompatibledrugsPK=" + incompatibledrugsPK + " ]";
    }
    
}
