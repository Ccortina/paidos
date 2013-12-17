package com.carloscortina.demo.model;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="Patient_Relative")
public class Patient_Relative implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2670460334767266076L;
	@EmbeddedId
	@JoinColumn(name = "idRelative", referencedColumnName = "idRelative", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Relative relative;
    @JoinColumn(name = "idRelationship", referencedColumnName = "idRelationship")
    @ManyToOne
    private Relationship idRelationship;
    @JoinColumn(name = "idPatient", referencedColumnName = "idPatient", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Patient patient;
    
	public Relative getRelative() {
		return relative;
	}
	public void setRelative(Relative relative) {
		this.relative = relative;
	}
	
	public Relationship getIdRelationship() {
		return idRelationship;
	}
	
	public void setIdRelationship(Relationship idRelationship) {
		this.idRelationship = idRelationship;
	}
	
	public Patient getPatient() {
		return patient;
	}
	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	
	
	
	
}
