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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ccortina_Mac
 */
@Entity
@Table(name = "ActivityConsultation")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ActivityConsultation.findAll", query = "SELECT a FROM ActivityConsultation a"),
    @NamedQuery(name = "ActivityConsultation.findByIdActivityConsultation", query = "SELECT a FROM ActivityConsultation a WHERE a.idActivityConsultation = :idActivityConsultation")})
public class ActivityConsultation implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idActivityConsultation")
    private Integer idActivityConsultation;
    @JoinColumn(name = "idActivity", referencedColumnName = "IdActivity")
    @ManyToOne(optional = false)
    private Activity idActivity;
    @JoinColumn(name = "idConsultation", referencedColumnName = "idConsultation")
    @ManyToOne(optional = false)
    private Consultation idConsultation;

    public ActivityConsultation() {
    }

    public ActivityConsultation(Integer idActivityConsultation) {
        this.idActivityConsultation = idActivityConsultation;
    }

    public ActivityConsultation(Activity idActivity, Consultation idConsultation) {
        this.idActivity = idActivity;
        this.idConsultation = idConsultation;
    }

    public Integer getIdActivityConsultation() {
        return idActivityConsultation;
    }

    public void setIdActivityConsultation(Integer idActivityConsultation) {
        this.idActivityConsultation = idActivityConsultation;
    }

    public Activity getIdActivity() {
        return idActivity;
    }

    public void setIdActivity(Activity idActivity) {
        this.idActivity = idActivity;
    }

    public Consultation getIdConsultation() {
        return idConsultation;
    }

    public void setIdConsultation(Consultation idConsultation) {
        this.idConsultation = idConsultation;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idActivityConsultation != null ? idActivityConsultation.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ActivityConsultation)) {
            return false;
        }
        ActivityConsultation other = (ActivityConsultation) object;
        if ((this.idActivityConsultation == null && other.idActivityConsultation != null) || (this.idActivityConsultation != null && !this.idActivityConsultation.equals(other.idActivityConsultation))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pruebas1.ActivityConsultation[ idActivityConsultation=" + idActivityConsultation + " ]";
    }
    
}
