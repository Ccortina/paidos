package com.carloscortina.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="role")
public class Role implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 689838359954516615L;
	private int id;
	private String role;
	private String description;
	private Set<User> users;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="idRole")
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@Column(name="Role")
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	@Column(name="Description")
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@OneToMany
	@JoinColumn(name="idRole")
        @JsonIgnore
	public Set<User> getUsers() {
		return users;
	}
	public void setUsers(Set<User> users) {
		this.users = users;
	}
	
	
}
