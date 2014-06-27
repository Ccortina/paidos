package com.carloscortina.demo.service;

import com.carloscortina.demo.dao.AppointmentDao;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.carloscortina.demo.model.Appointment;
import java.util.Date;

@Service
@Transactional
public class AppointmentServiceImp implements AppointmentService {

    @Autowired
    private AppointmentDao appointmentDao;

    @Override
    public void create(Appointment item) {
        appointmentDao.create(item);
    }

    @Override
    public void delete(Appointment item) {
        appointmentDao.delete(item);
    }

    @Override
    public Appointment getById(int id) {
        return appointmentDao.getById(id);
    }

    @Override
    public void updateItem(Appointment item) {
        appointmentDao.updateItem(item);
    }
    
    @Override
    public List<Appointment> getAll(String table) {
        return appointmentDao.getAll(table);
    }

    @Override
    public List<Appointment> getListOfItem(String query) {
        return appointmentDao.getListOfItem(query);
    }

    @Override
    public List<Appointment> getAppointments(Date start, Date end){
        return appointmentDao.getAppointments(start, end);
    }
    
    @Override
    public List<Appointment> getAppointments(Date start, Date end,int idDoctor){
        return appointmentDao.getAppointments(start, end,idDoctor);
    }

    @Override
    public List<Appointment> getSpecificColumnsList(List<String> columns) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
