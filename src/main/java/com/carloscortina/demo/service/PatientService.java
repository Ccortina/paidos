package com.carloscortina.demo.service;

import java.util.List;

import com.carloscortina.demo.model.Patient;

public interface PatientService {

	public void createPatient(Patient patient);
	public List<Patient> getPatients();
	public Patient getPatientById(int id);
}
