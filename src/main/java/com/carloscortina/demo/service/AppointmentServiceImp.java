package com.carloscortina.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.carloscortina.demo.dao.AppointmentDao;
import com.carloscortina.demo.model.Appointment;

@Service
@Transactional
public class AppointmentServiceImp implements AppointmentService {

	@Autowired
	private AppointmentDao appointmentDao;
	
	@Override
	public List<Appointment> getRelatedData() {
		
		return appointmentDao.getRelatedData();
	}

	@Override
	public void create(Appointment item) {
		appointmentDao.create(item);
		
	}

	@Override
	public Appointment getById(int id) {
		// TODO Auto-generated method stub
		return appointmentDao.getById(id);
	}

	@Override
	public List<Appointment> getAll(String table) {
		// TODO Auto-generated method stub
		return appointmentDao.getAll(table);
	}

	@Override
	public List<Appointment> getListOfItem(String query) {
		// TODO Auto-generated method stub
		return appointmentDao.getListOfItem(query);
	}

}
