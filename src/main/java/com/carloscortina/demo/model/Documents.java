/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.carloscortina.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Carlos Cortina
 */
@Entity
@Table(name = "documents")
@NamedQueries({
    @NamedQuery(name = "Documents.findAll", query = "SELECT d FROM Documents d")})
public class Documents implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idDocuments")
    private Integer idDocuments;
    @Size(max = 200)
    @Column(name = "description")
    private String description;
    @Size(max = 65535)
    @Column(name = "notes")
    private String notes;
    @JsonIgnore
    @Basic(optional = false)
    @NotNull
    @Size(min = 1,max = 200)
    @Column(name = "path")
    private String path;
    @JsonIgnore
    @JoinColumn(name = "idPatient", referencedColumnName = "idPatient")
    @ManyToOne(optional = false)
    private Patient idPatient;
    @JoinColumn(name = "idDocumentCategory", referencedColumnName = "idDocumentCategory")
    @ManyToOne(optional = false)
    private Documentcategory idDocumentCategory;
    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    private Date date;
    @Column(name = "addedDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date addedDate;
    @Column(name = "tempClaveDocumento")
    private Integer tempClaveDocumento;

    public Documents() {
    }

    public Documents(Integer idDocuments) {
        this.idDocuments = idDocuments;
    }

    public Documents(String description, String notes, String path, Patient idPatient, Documentcategory idDocumentCategory, Date date, Date addedDate) {
        this.description = description;
        this.notes = notes;
        this.path = path;
        this.idPatient = idPatient;
        this.idDocumentCategory = idDocumentCategory;
        this.date = date;
        this.addedDate = addedDate;
    }

    public Integer getIdDocuments() {
        return idDocuments;
    }

    public void setIdDocuments(Integer idDocuments) {
        this.idDocuments = idDocuments;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Patient getIdPatient() {
        return idPatient;
    }

    public void setIdPatient(Patient idPatient) {
        this.idPatient = idPatient;
    }

    public Documentcategory getIdDocumentCategory() {
        return idDocumentCategory;
    }

    public void setIdDocumentCategory(Documentcategory idDocumentCategory) {
        this.idDocumentCategory = idDocumentCategory;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDocuments != null ? idDocuments.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Documents)) {
            return false;
        }
        Documents other = (Documents) object;
        if ((this.idDocuments == null && other.idDocuments != null) || (this.idDocuments != null && !this.idDocuments.equals(other.idDocuments))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.carloscortina.demo.model.Documents[ idDocuments=" + idDocuments + " ]";
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getAddedDate() {
        return addedDate;
    }

    public void setAddedDate(Date addedDate) {
        this.addedDate = addedDate;
    }

    public Integer getTempClaveDocumento() {
        return tempClaveDocumento;
    }

    public void setTempClaveDocumento(Integer tempClaveDocumento) {
        this.tempClaveDocumento = tempClaveDocumento;
    }
    
}
