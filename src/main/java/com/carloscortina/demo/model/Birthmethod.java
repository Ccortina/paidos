/*
 * To change this template, choose Tools | Templates
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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ccortina_Mac
 */
@Entity
@Table(name = "birthmethod")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Birthmethod.findAll", query = "SELECT b FROM Birthmethod b"),
    @NamedQuery(name = "Birthmethod.findByIdBirthMethod", query = "SELECT b FROM Birthmethod b WHERE b.idBirthMethod = :idBirthMethod"),
    @NamedQuery(name = "Birthmethod.findByBirthMethod", query = "SELECT b FROM Birthmethod b WHERE b.birthMethod = :birthMethod"),
    @NamedQuery(name = "Birthmethod.findByDescription", query = "SELECT b FROM Birthmethod b WHERE b.description = :description")})
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
    @Size(max = 45)
    @Column(name = "description")
    private String description;

    public Birthmethod() {
    }

    public Birthmethod(Integer idBirthMethod) {
        this.idBirthMethod = idBirthMethod;
    }

    public Birthmethod(Integer idBirthMethod, String birthMethod) {
        this.idBirthMethod = idBirthMethod;
        this.birthMethod = birthMethod;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
    
}
