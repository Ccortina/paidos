package com.carloscortina.demo.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.ScriptAssert;

@ScriptAssert(
		lang="javascript",
		script="_this.confirmPassword.equals(_this.password)",
		message="account.password.mismatch.message"
		)

public class StaffRegistrationForm {

	private String username;
	private String password;
	private String confirmPassword;
	private String email;
	private String firstName;
	private String lastName;
	private String phone;
	private String cellPhone;
	private String professionalNumber;
	private String role;
	
	public StaffRegistrationForm(){
		this.username = "";
		this.password = "";
		this.confirmPassword = "";
		this.email = "";
		this.firstName = "";
		this.lastName = "";
		this.phone = "";
		this.cellPhone = "";
		this.professionalNumber = "";
		this.role = "";
	}
	
	@NotNull
	@Size(min = 1,max = 20)
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	@NotNull
	@Size(min = 6,max = 20)
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@NotNull
	@Size(min = 6,max = 20)
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	
	@NotNull
	@Size(min = 6,max = 50)
	@Email
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@NotNull
	@Size(min = 3,max = 30)
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	@NotNull
	@Size(min = 3,max = 30)
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	@NotNull
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	@NotNull
	public String getCellPhone() {
		return cellPhone;
	}
	public void setCellPhone(String cellPhone) {
		this.cellPhone = cellPhone;
	}
	
	@NotNull
	public String getProfessionalNumber() {
		return professionalNumber;
	}
	public void setProfessionalNumber(String professionalNumber) {
		this.professionalNumber = professionalNumber;
	}
	
	@NotNull
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	@Override
	public String toString() {
		return "username=" + username + ", password="
				+ password + ", confirmPassword=" + confirmPassword
				+ ", firstName=" + firstName + ", lastName=" + lastName
				+ ", phone=" + phone + ", cellPhone=" + cellPhone
				+ ", professionalNumber=" + professionalNumber + ", role="
				+ role ;
	}
	
	
	
}
