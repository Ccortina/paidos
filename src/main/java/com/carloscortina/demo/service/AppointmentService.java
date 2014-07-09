package com.carloscortina.demo.service;

import java.util.List;

import com.carloscortina.demo.model.Appointment;
import java.util.Date;


public interface AppointmentService extends GenericService<Appointment>{
    public List<Appointment> getAppointments(Date start, Date end);
    public List<Appointment> getAppointments(Date start, Date end,int idDoctor);
}
