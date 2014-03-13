package com.carloscortina.demo.dao;

import java.util.List;

import com.carloscortina.demo.model.Patient;

public interface PatientDao {

	public void createPatient(Patient patient);
	public Patient getPatientById(int id);
	public List<Patient> getPatients();
}
