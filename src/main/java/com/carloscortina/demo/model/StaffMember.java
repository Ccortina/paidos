package com.carloscortina.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="StaffMember")
public class StaffMember {

	private int id;
	private String firstName;
	private String lastName;
	private String phone;
	private String cellPhone;
	private String professionalNumber;
	
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
	@Column(name="firstName")
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
	
	@Column(name="professionalNumer")
	public String getProfessionalNumber() {
		return professionalNumber;
	}
	public void setProfessionalNumber(String professionalNumber) {
		this.professionalNumber = professionalNumber;
	}
	
}
