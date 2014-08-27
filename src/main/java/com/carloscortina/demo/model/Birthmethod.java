/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.carloscortina.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Carlos Cortina
 */
@Entity
@Table(name = "birthmethod")
@NamedQueries({
    @NamedQuery(name = "Birthmethod.findAll", query = "SELECT b FROM Birthmethod b")})
public class Birthmethod implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idBirthMethod")
    private Integer idBirthMethod;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "birthMethod")
    private String birthMethod;
    @Basic(optional = false)
    @NotNull
    @Column(name = "active")
    private int active;
    @JsonIgnore
    @OneToMany(mappedBy = "birthMethod")
    private List<Perbacknopat> perbacknopatList;

    public Birthmethod() {
    }

    public Birthmethod(Integer idBirthMethod) {
        this.idBirthMethod = idBirthMethod;
    }

    public Birthmethod(Integer idBirthMethod, String birthMethod, int active) {
        this.idBirthMethod = idBirthMethod;
        this.birthMethod = birthMethod;
        this.active = active;
    }

    public Birthmethod(String birthMethod, int active) {
        this.birthMethod = birthMethod;
        this.active = active;
    }

    public Integer getIdBirthMethod() {
        return idBirthMethod;
    }

    public void setIdBirthMethod(Integer idBirthMethod) {
        this.idBirthMethod = idBirthMethod;
    }

    public String getBirthMethod() {
        return birthMethod;
    }

    public void setBirthMethod(String birthMethod) {
        this.birthMethod = birthMethod;
    }

    public List<Perbacknopat> getPerbacknopatList() {
        return perbacknopatList;
    }

    public void setPerbacknopatList(List<Perbacknopat> perbacknopatList) {
        this.perbacknopatList = perbacknopatList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idBirthMethod != null ? idBirthMethod.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Birthmethod)) {
            return false;
        }
        Birthmethod other = (Birthmethod) object;
        if ((this.idBirthMethod == null && other.idBirthMethod != null) || (this.idBirthMethod != null && !this.idBirthMethod.equals(other.idBirthMethod))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.carloscortina.demo.model.Birthmethod[ idBirthMethod=" + idBirthMethod + " ]";
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }
    
}
