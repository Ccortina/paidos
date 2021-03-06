package com.carloscortina.demo.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="StaffMember")
public class StaffMember implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4402030728393694289L;
	private int id;
	private String firstName;
	private String lastName;
	private String phone;
	private String cellPhone;
	private String professionalNumber;
	private Set<Patient> patients; 
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="idStaffMember")
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@NotNull
	@Size(min=3,max=20)
	@Column(name="Name")
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	@NotNull
	@Size(min=3,max=20)
	@Column(name="lastName")
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	@Column(name="phone")
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	@Column(name="cellPhone")
	public String getCellPhone() {
		return cellPhone;
	}
	public void setCellPhone(String cellPhone) {
		this.cellPhone = cellPhone;
	}
	
	@Column(name="ProfessionalNumber")
	public String getProfessionalNumber() {
		return professionalNumber;
	}
	public void setProfessionalNumber(String professionalNumber) {
		this.professionalNumber = professionalNumber;
	}
	
	@JsonIgnore
	@OneToMany(mappedBy="doctor",cascade=CascadeType.ALL)
	public Set<Patient> getPatients() {
		return patients;
	}
	public void setPatients(Set<Patient> patients) {
		this.patients = patients;
	}
	
}
