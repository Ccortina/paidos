package com.carloscortina.demo.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class PatientRelativeId implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2719758608242901070L;
	
	@Column(name = "idPatient")
	private int patientId;
	
	@Column(name = "idRelative")
	private int relativeId;
	

	public PatientRelativeId() {
	}
	
	public PatientRelativeId(int patientId, int relativeId) {
		this.patientId = patientId;
		this.relativeId = relativeId;
	}
	
	public int getPatientId() {
		return patientId;
	}
	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}
	public int getRelativeId() {
		return relativeId;
	}
	public void setRelativeId(int relativeId) {
		this.relativeId = relativeId;
	}
}
