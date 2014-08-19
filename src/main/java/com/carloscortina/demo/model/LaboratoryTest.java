/*
 * To change this template, choose Tools | Templates
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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ccortina_Mac
 */
@Entity
@Table(name = "laboratoryTest")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LaboratoryTest.findAll", query = "SELECT l FROM LaboratoryTest l"),
    @NamedQuery(name = "LaboratoryTest.findByIdLaboratoryTest", query = "SELECT l FROM LaboratoryTest l WHERE l.idLaboratoryTest = :idLaboratoryTest"),
    @NamedQuery(name = "LaboratoryTest.findByLaboratoryTest", query = "SELECT l FROM LaboratoryTest l WHERE l.laboratoryTest = :laboratoryTest"),
    @NamedQuery(name = "LaboratoryTest.findByActive", query = "SELECT l FROM LaboratoryTest l WHERE l.active = :active")})
public class LaboratoryTest implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idLaboratoryTest")
    private Integer idLaboratoryTest;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "laboratoryTest")
    private String laboratoryTest;
    @Basic(optional = false)
    @NotNull
    @Column(name = "active")
    private int active;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idLaboratoryTest")
    private List<LaboratoryTestResult> laboratoryTestResultList;

    public LaboratoryTest() {
    }

    public LaboratoryTest(Integer idLaboratoryTest) {
        this.idLaboratoryTest = idLaboratoryTest;
    }

    public LaboratoryTest(String laboratoryTest, int active) {
        this.laboratoryTest = laboratoryTest;
        this.active = active;
    }

    public LaboratoryTest(Integer idLaboratoryTest, String laboratoryTest, int active) {
        this.idLaboratoryTest = idLaboratoryTest;
        this.laboratoryTest = laboratoryTest;
        this.active = active;
    }

    public Integer getIdLaboratoryTest() {
        return idLaboratoryTest;
    }

    public void setIdLaboratoryTest(Integer idLaboratoryTest) {
        this.idLaboratoryTest = idLaboratoryTest;
    }

    public String getLaboratoryTest() {
        return laboratoryTest;
    }

    public void setLaboratoryTest(String laboratoryTest) {
        this.laboratoryTest = laboratoryTest;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idLaboratoryTest != null ? idLaboratoryTest.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LaboratoryTest)) {
            return false;
        }
        LaboratoryTest other = (LaboratoryTest) object;
        if ((this.idLaboratoryTest == null && other.idLaboratoryTest != null) || (this.idLaboratoryTest != null && !this.idLaboratoryTest.equals(other.idLaboratoryTest))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.carloscortina.demo.model.LaboratoryTest[ idLaboratoryTest=" + idLaboratoryTest + " ]";
    }
    
}
