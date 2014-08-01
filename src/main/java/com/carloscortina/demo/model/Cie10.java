/**
*
* @author carloscortina
*/

package com.carloscortina.demo.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;

/**
 *
 * @author carloscortina
 */
@Entity
@Table(name = "CIE10")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cie10.findAll", query = "SELECT c FROM Cie10 c"),
    @NamedQuery(name = "Cie10.findByIdCIE10", query = "SELECT c FROM Cie10 c WHERE c.idCIE10 = :idCIE10"),
    @NamedQuery(name = "Cie10.findByCieCode", query = "SELECT c FROM Cie10 c WHERE c.cieCode = :cieCode"),
    @NamedQuery(name = "Cie10.findByActive", query = "SELECT c FROM Cie10 c WHERE c.active = :active")})
public class Cie10 implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idCIE10")
    private Integer idCIE10;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "cieCode")
    private String cieCode;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "diagnostic")
    private String diagnostic;
    @Lob
    @Size(max = 65535)
    @Column(name = "description")
    private String description;
    @Basic(optional = false)
    @NotNull
    @Column(name = "active")
    private short active;
    @JsonIgnore
    @JoinTable(name = "diagnostictreatment", joinColumns = {
        @JoinColumn(name = "diagnosticId", referencedColumnName = "idCIE10")}, inverseJoinColumns = {
        @JoinColumn(name = "treatmentId", referencedColumnName = "IdTreatment")})
    @ManyToMany
    private List<Treatment> treatmentList;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCIE10")
    private List<Diagnostic> diagnosticList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cie10")
    @JsonIgnore
    private List<CIE10Doctor> cie10doctorList;

    public Cie10() {
    }

    public Cie10(Integer idCIE10) {
        this.idCIE10 = idCIE10;
    }

    public Cie10(Integer idCIE10, String cieCode, String diagnostic, short active) {
        this.idCIE10 = idCIE10;
        this.cieCode = cieCode;
        this.diagnostic = diagnostic;
        this.active = active;
    }

    public Cie10(Integer idCIE10, String cieCode, String diagnostic, String description, short active) {
        this.idCIE10 = idCIE10;
        this.cieCode = cieCode;
        this.diagnostic = diagnostic;
        this.description = description;
        this.active = active;
    }

    public Integer getIdCIE10() {
        return idCIE10;
    }

    public void setIdCIE10(Integer idCIE10) {
        this.idCIE10 = idCIE10;
    }

    public String getCieCode() {
        return cieCode;
    }

    public void setCieCode(String cieCode) {
        this.cieCode = cieCode;
    }

    public String getDiagnostic() {
        return diagnostic;
    }

    public void setDiagnostic(String diagnostic) {
        this.diagnostic = diagnostic;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public short getActive() {
        return active;
    }

    public void setActive(short active) {
        this.active = active;
    }

    @XmlTransient
    public List<Treatment> getTreatmentList() {
        return treatmentList;
    }

    public void setTreatmentList(List<Treatment> treatmentList) {
        this.treatmentList = treatmentList;
    }

    @XmlTransient
    public List<Diagnostic> getDiagnosticList() {
        return diagnosticList;
    }

    public void setDiagnosticList(List<Diagnostic> diagnosticList) {
        this.diagnosticList = diagnosticList;
    }

    public List<CIE10Doctor> getCie10doctorList() {
        return cie10doctorList;
    }

    public void setCie10doctorList(List<CIE10Doctor> cie10doctorList) {
        this.cie10doctorList = cie10doctorList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCIE10 != null ? idCIE10.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cie10)) {
            return false;
        }
        Cie10 other = (Cie10) object;
        if ((this.idCIE10 == null && other.idCIE10 != null) || (this.idCIE10 != null && !this.idCIE10.equals(other.idCIE10))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "diagnostic.Cie10[ idCIE10=" + idCIE10 + " ]";
    }
    
}