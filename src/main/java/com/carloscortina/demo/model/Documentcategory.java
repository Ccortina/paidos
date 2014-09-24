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

/**
 *
 * @author Carlos Cortina
 */
@Entity
@Table(name = "documentcategory")
@NamedQueries({
    @NamedQuery(name = "Documentcategory.findAll", query = "SELECT d FROM Documentcategory d")})
public class Documentcategory implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idDocumentCategory")
    private Integer idDocumentCategory;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "category")
    private String category;
    @Basic(optional = false)
    @NotNull
    @Column(name = "active")
    private int active;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idDocumentCategory")
    private List<Documents> documentsList;
    @Column(name = "tempClaveCategoriaDocumento")
    private Integer tempClaveCategoriaDocumento;

    public Documentcategory() {
    }

    public Documentcategory(Integer idDocumentCategory) {
        this.idDocumentCategory = idDocumentCategory;
    }

    public Documentcategory(String category, int active) {
        this.category = category;
        this.active = active;
    }

    public Documentcategory(Integer idDocumentCategory, String category, int active) {
        this.idDocumentCategory = idDocumentCategory;
        this.category = category;
        this.active = active;
    }

    public Integer getIdDocumentCategory() {
        return idDocumentCategory;
    }

    public void setIdDocumentCategory(Integer idDocumentCategory) {
        this.idDocumentCategory = idDocumentCategory;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public List<Documents> getDocumentsList() {
        return documentsList;
    }

    public void setDocumentsList(List<Documents> documentsList) {
        this.documentsList = documentsList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDocumentCategory != null ? idDocumentCategory.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Documentcategory)) {
            return false;
        }
        Documentcategory other = (Documentcategory) object;
        if ((this.idDocumentCategory == null && other.idDocumentCategory != null) || (this.idDocumentCategory != null && !this.idDocumentCategory.equals(other.idDocumentCategory))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.carloscortina.demo.model.Documentcategory[ idDocumentCategory=" + idDocumentCategory + " ]";
    }

    public Integer getTempClaveCategoriaDocumento() {
        return tempClaveCategoriaDocumento;
    }

    public void setTempClaveCategoriaDocumento(Integer tempClaveCategoriaDocumento) {
        this.tempClaveCategoriaDocumento = tempClaveCategoriaDocumento;
    }
    
}
