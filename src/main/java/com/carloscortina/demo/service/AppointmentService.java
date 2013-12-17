package com.carloscortina.demo.service;

import java.util.List;

import com.carloscortina.demo.model.Appointment;


public interface AppointmentService {

	public void create(Appointment item);
	public Appointment getById(int id);
	public List<Appointment> getAll(String table);
	public List<Appointment> getListOfItem(String query);
	public List<Appointment> getRelatedData();
}
