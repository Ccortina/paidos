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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
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
    @Basic(optional = false)
    @NotNull
    @Column(name = "idDocuments")
    private Integer idDocuments;
    @Size(max = 200)
    @Column(name = "description")
    private String description;
    @Size(max = 65535)
    @Column(name = "notes")
    private String notes;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "path")
    private String path;
    @JoinColumn(name = "idPatient", referencedColumnName = "idPatient")
    @ManyToOne(optional = false)
    private Patient idPatient;
    @JoinColumn(name = "idDocumentCategory", referencedColumnName = "idDocumentCategory")
    @ManyToOne(optional = false)
    private Documentcategory idDocumentCategory;

    public Documents() {
    }

    public Documents(Integer idDocuments) {
        this.idDocuments = idDocuments;
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
    
}
