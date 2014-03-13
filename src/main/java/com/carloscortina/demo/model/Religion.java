package com.carloscortina.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="Religion")
public class Religion {

	private int id;
	private String religion;
	
	public Religion(){
		this.id=0;
		this.religion="";
	}
	
	public Religion(Religion religion){
		this.id=religion.getId();
		this.religion=religion.getReligion();
	}
	
	/**
	 * @return the id
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="idReligion")
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the religion
	 */
	@NotNull
	@Column(name="Religion")
	public String getReligion() {
		return religion;
	}
	/**
	 * @param religion the religion to set
	 */
	public void setReligion(String religion) {
		this.religion = religion;
	}
	
	
}
