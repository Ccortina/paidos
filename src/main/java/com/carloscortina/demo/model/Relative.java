package com.carloscortina.demo.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Size;

@Entity
@Table(name="Relative")
public class Relative implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1332041093349090810L;
	private int id;
	private String firstName;
	private String secondName;
	private String fatherLastName;
	private String motherLastName;
	private String curp;
	private String occupation;
	private String rfc;
	private String homePhone,officePhone,officeExt,cellPhone,otherPhone;
	private String email;
	private Religion religion;
	private String notes;
	private String street,number,colony,city,state,country,cp;
	private String recommendedBy;
	private String ginecologist;
	private Timestamp addedDate;
	private Set<Patient_Relative> patientRelative;
	
	public Relative(){
		
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="idRelative")
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@Column(name="FirstName")
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	@Column(name="SecondName")
	public String getSecondName() {
		return secondName;
	}
	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}
	
	@Column(name="FatherLastName")
	public String getFatherLastName() {
		return fatherLastName;
	}
	public void setFatherLastName(String fatherLastName) {
		this.fatherLastName = fatherLastName;
	}
	
	@Column(name="MotherLastName")
	public String getMotherLastName() {
		return motherLastName;
	}
	public void setMotherLastName(String motherLastName) {
		this.motherLastName = motherLastName;
	}
	
	@Column(name="CURP")
	public String getCurp() {
		return curp;
	}
	public void setCurp(String curp) {
		this.curp = curp;
	}
	
	@Column(name="Occupation")
	public String getOccupation() {
		return occupation;
	}
	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}
	
	@Column(name="RFC")
	public String getRfc() {
		return rfc;
	}
	public void setRfc(String rfc) {
		this.rfc = rfc;
	}
	
	@Column(name="HomePhone")
	public String getHomePhone() {
		return homePhone;
	}
	public void setHomePhone(String homePhone) {
		this.homePhone = homePhone;
	}
	
	@Column(name="officePhone")
	public String getOfficePhone() {
		return officePhone;
	}
	public void setOfficePhone(String officePhone) {
		this.officePhone = officePhone;
	}
	
	@Column(name="OfficeExt")
	public String getOfficeExt() {
		return officeExt;
	}
	public void setOfficeExt(String officeExt) {
		this.officeExt = officeExt;
	}
	
	@Column(name="CellPhone")
	public String getCellPhone() {
		return cellPhone;
	}
	public void setCellPhone(String cellPhone) {
		this.cellPhone = cellPhone;
	}
	
	@Column(name="OtherPhone")
	public String getOtherPhone() {
		return otherPhone;
	}
	public void setOtherPhone(String otherPhone) {
		this.otherPhone = otherPhone;
	}
	
	@Column(name="Email")
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@ManyToOne
	@JoinColumn(name = "Religion",referencedColumnName="idReligion")
	public Religion getReligion() {
		return religion;
	}
	public void setReligion(Religion religion) {
		this.religion = religion;
	}
	
	@Column(name="Notes")
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	
	@Column(name="RecommendedBy")
	public String getRecommendedBy() {
		return recommendedBy;
	}
	public void setRecommendedBy(String recommendedBy) {
		this.recommendedBy = recommendedBy;
	}
	
	@Column(name="Ginecologist")
	public String getGinecologist() {
		return ginecologist;
	}
	public void setGinecologist(String ginecologist) {
		this.ginecologist = ginecologist;
	}
	
	@Column(name="AddedDate")
	public Timestamp getAddedDate() {
		return addedDate;
	}
	public void setAddedDate(Timestamp addedDate) {
		this.addedDate = addedDate;
	}
	
	@OneToMany(mappedBy="relative")
	public Set<Patient_Relative> getPatientRelative() {
		return patientRelative;
	}

	public void setPatientRelative(Set<Patient_Relative> patientRelative) {
		this.patientRelative = patientRelative;
	}
	
	@Size(min=1,max=45)
	@Column(name="street")
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	
	@Size(min=1,max=45)
	@Column(name="number")
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	
	@Size(min=1,max=45)
	@Column(name="colony")
	public String getColony() {
		return colony;
	}
	public void setColony(String colony) {
		this.colony = colony;
	}
	
	@Size(min=1,max=45)
	@Column(name="city")
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	
	@Size(min=1,max=45)
	@Column(name="state")
	public String getState() {
		return state;
	}
	
	public void setState(String state) {
		this.state = state;
	}
	
	@Size(min=1,max=45)
	@Column(name="country")
	public String getCountry() {
		return country;
	}
	
	public void setCountry(String country) {
		this.country = country;
	}

	public String getCp() {
		return cp;
	}

	public void setCp(String cp) {
		this.cp = cp;
	}
	
	@Transient
	public String getFullName(){
		return (firstName + " " + secondName + " " + fatherLastName + " " + motherLastName);
	}
	
	
}
