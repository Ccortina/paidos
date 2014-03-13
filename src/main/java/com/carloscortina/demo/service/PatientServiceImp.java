package com.carloscortina.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.carloscortina.demo.dao.PatientDao;
import com.carloscortina.demo.model.Patient;

@Service
@Transactional
public class PatientServiceImp implements PatientService {

	@Autowired
	PatientDao patientDao;
	
	@Override
	public void createPatient(Patient patient) {
		// TODO Auto-generated method stub
		patientDao.createPatient(patient);
	}

	@Override
	public List<Patient> getPatients() {
		// TODO Auto-generated method stub
		return patientDao.getPatients();
	}
	
	public Patient getPatientById(int id){
		return patientDao.getPatientById(id);
	}

}
