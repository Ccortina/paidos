package com.carloscortina.demo.model;

import java.sql.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class PatientRegistrationForm {

	private String firstName;
	private String secondName;
	private String fatherLastName;
	private String motherLastName;
	private String curp;
	private String nickname;
	private String sex;
	private Date birthday;
	private String notes;
	private boolean active;
	private String doctor;
	private String relatives;
	
	@Size(min=1,max=45) 
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getSecondName() {
		return secondName;
	}
	
	@Size(min=1,max=45)
	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}
	public String getFatherLastName() {
		return fatherLastName;
	}
	
	@Size(min=1,max=45)
	public void setFatherLastName(String fatherLastName) {
		this.fatherLastName = fatherLastName;
	}
	
	@Size(min=1,max=45)
	public String getMotherLastName() {
		return motherLastName;
	}
	public void setMotherLastName(String motherLastName) {
		this.motherLastName = motherLastName;
	}
	
	@Size(min=1,max=45)
	public String getCurp() {
		return curp;
	}
	
	@Size(min=1,max=45)
	public void setCurp(String curp) {
		this.curp = curp;
	}
	public String getNickname() {
		return nickname;
	}
	
	@Size(min=1,max=45)
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	@Size(min=8,max=9)
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	
	@NotNull
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	@NotNull
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	
	@NotNull
	public String getDoctor() {
		return doctor;
	}
	public void setDoctor(String doctor) {
		this.doctor = doctor;
	}

	public String getRelatives() {
		return relatives;
	}
	public void setRelatives(String relatives) {
		this.relatives = relatives;
	}
	
	

}
