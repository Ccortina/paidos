package com.carloscortina.demo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.carloscortina.demo.model.Patient;

@Repository
public class HbnPatientDao implements PatientDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getSession()
	{
		return sessionFactory.getCurrentSession();
	}
	
	@Override
	public void createPatient(Patient patient) {
		// TODO Auto-generated method stub
		getSession().save(patient);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Patient> getPatients() {
		return ( getSession().createQuery("from Patient").list());
	}

	@Override
	public Patient getPatientById(int id) {
		
		return ( (Patient) getSession().get(Patient.class, id) );
	}

}
