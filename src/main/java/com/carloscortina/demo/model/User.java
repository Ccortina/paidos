package com.carloscortina.demo.model;

public class User {

	private int id;
	private String username;
	private String password;
	private String email;
	private Role role;
	private StaffMember staff;
	
	public User() {
		this.id = 0;
		this.username = "";
		this.password = "";
		this.email = "";
		this.role = null;
		this.staff = null;
	}
	
	public User(int id, String username, String password, String email,
			Role role, StaffMember staff) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
		this.role = role;
		this.staff = staff;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public StaffMember getStaff() {
		return staff;
	}
	public void setStaff(StaffMember staff) {
		this.staff = staff;
	}
	
	
}
