package com.carloscortina.demo.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@NamedQuery(
		name = "findUserByUsername",
		query = "from User where username = :username"
		)
@Entity
@Table(name="user")
public class User implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2992051433452055445L;
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
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="idUser")
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@NotNull
	@Size(min=1,max=20)
	@Column(name="username")
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	@NotNull
	@Size(min=6,max=20)
	@Column(name="password")
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@NotNull
	@Size(min=1,max=50)
	@Column(name="email")
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@ManyToOne
	@JoinColumn(name = "idRole")
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="idStaffMember")
	public StaffMember getStaff() {
		return staff;
	}
	public void setStaff(StaffMember staff) {
		this.staff = staff;
	}
	
	
}
