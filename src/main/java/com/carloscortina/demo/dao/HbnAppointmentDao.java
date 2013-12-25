package com.carloscortina.demo.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.carloscortina.demo.model.Appointment;


@Repository
public class HbnAppointmentDao extends GenericHbnDao<Appointment> implements AppointmentDao {
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Appointment> getRelatedData() {
		List<Appointment> list = getSession().createQuery("from appointment").list();
		return list;
	}
}