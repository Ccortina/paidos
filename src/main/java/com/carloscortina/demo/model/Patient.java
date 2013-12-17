package com.carloscortina.demo.model;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="Patient")
public class Patient implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 9026423808843575752L;
	private int id;
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
	private StaffMember doctor;
	private Timestamp addedDate;
	private Set<Patient_Relative> relatives;
	private Set<Record> record;
	
	public Patient() {
		super();
		this.id = 0;
		this.firstName = "";
		this.secondName = "";
		this.fatherLastName = "";
		this.motherLastName = "";
		this.curp = "";
		this.nickname = "";
		this.sex = "";
		this.birthday = null;
		this.notes = "";
		this.active = false;
		this.doctor = null;
		this.addedDate = null;
	}
	
	public Patient(int id, String firstName, String secondName,
			String fatherLastName, String motherLastName, String curp,
			String nickname, String sex, Date birthday, String notes,
			boolean active, StaffMember doctor, Timestamp addedDate) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.secondName = secondName;
		this.fatherLastName = fatherLastName;
		this.motherLastName = motherLastName;
		this.curp = curp;
		this.nickname = nickname;
		this.sex = sex;
		this.birthday = birthday;
		this.notes = notes;
		this.active = active;
		this.doctor = doctor;
		this.addedDate = addedDate;
	}

	/**
	 * @return the id
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="idPatient")
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
	 * @return the firstName
	 */
	@Size(min=3,max=45)
	@Column(name="FirstName")
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the secondName
	 */
	@Size(min=3,max=45)
	@Column(name="SecondName")
	public String getSecondName() {
		return secondName;
	}

	/**
	 * @param secondName the secondName to set
	 */
	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}

	/**
	 * @return the fatherLastName
	 */
	@Size(min=3,max=45)
	@Column(name="FatherLastName")
	public String getFatherLastName() {
		return fatherLastName;
	}

	/**
	 * @param fatherLastName the fatherLastName to set
	 */
	public void setFatherLastName(String fatherLastName) {
		this.fatherLastName = fatherLastName;
	}

	/**
	 * @return the motherLastName
	 */
	@Size(min=3,max=45)
	@Column(name="MotherLastName")
	public String getMotherLastName() {
		return motherLastName;
	}

	/**
	 * @param motherLastName the motherLastName to set
	 */
	public void setMotherLastName(String motherLastName) {
		this.motherLastName = motherLastName;
	}

	/**
	 * @return the curp
	 */
	@Size(min=18,max=18)
	@Column(name="curp")
	public String getCurp() {
		return curp;
	}

	/**
	 * @param curp the curp to set
	 */
	public void setCurp(String curp) {
		this.curp = curp;
	}

	/**
	 * @return the nickname
	 */
	@Size(min=1,max=45)
	@Column(name="NickName")
	public String getNickname() {
		return nickname;
	}

	/**
	 * @param nickname the nickname to set
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	/**
	 * @return the sex
	 */
	@Size(min=8,max=9)
	@Column(name="Sex")
	public String getSex() {
		return sex;
	}

	/**
	 * @param sex the sex to set
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}

	/**
	 * @return the birthday
	 */
	@NotNull
	@Column(name="Birthday")
	public Date getBirthday() {
		return birthday;
	}

	/**
	 * @param birthday the birthday to set
	 */
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	/**
	 * @return the notes
	 */
	@Column(name="Notes")
	public String getNotes() {
		return notes;
	}

	/**
	 * @param notes the notes to set
	 */
	public void setNotes(String notes) {
		this.notes = notes;
	}

	/**
	 * @return the active
	 */
	@NotNull
	@Column(name="Active")
	public boolean isActive() {
		return active;
	}

	/**
	 * @param active the active to set
	 */
	public void setActive(boolean active) {
		this.active = active;
	}

	/**
	 * @return the doctor
	 */
	@ManyToOne
	@JoinColumn(name="idDoctor",referencedColumnName="idStaffMember")
	@NotFound(action=NotFoundAction.IGNORE)
	public StaffMember getDoctor() {
		return doctor;
	}

	/**
	 * @param doctor the doctor to set
	 */
	public void setDoctor(StaffMember doctor) {
		this.doctor = doctor;
	}

	/**
	 * @return the addedDate
	 */
	public Timestamp getAddedDate() {
		return addedDate;
	}

	/**
	 * @param addedDate the addedDate to set
	 */
	public void setAddedDate(Timestamp addedDate) {
		this.addedDate = addedDate;
	}

	/**
	 * @return the relatives
	 */
	@JsonIgnore
	@OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL,mappedBy="patient")
	public Set<Patient_Relative> getRelatives() {
		return relatives;
	}

	/**
	 * @param relatives the relatives to set
	 */
	public void setRelatives(Set<Patient_Relative> relatives) {
		this.relatives = relatives;
	}
	
	@OneToMany(mappedBy = "idPatient")
    public Set<Record> getRecord() {
        return record;
    }

    public void setRecord(Set<Record> record) {
        this.record = record;
    }
	
	
	

}
