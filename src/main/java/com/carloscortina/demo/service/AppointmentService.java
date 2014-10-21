package com.carloscortina.demo.service;

import java.util.List;

import com.carloscortina.demo.model.Appointment;
import com.carloscortina.demo.model.User;
import java.util.Date;


public interface AppointmentService extends GenericService<Appointment>{
    public List<Appointment> getAppointments(Date start, Date end);
    public List<Appointment> getAppointments(Date start, Date end,int idDoctor);
    public List<Appointment> getAvaibleAppointmentsByPatient(Date start, Date end, int idPatient);
    public List<Appointment> getAppointmentsByPatient(Date start, Date end, int idPatient);
    public List<Appointment> getAppointmentsByDay(Date start, Date end);
    public List<Appointment> getAppointmentsByPatient(int idPatient);
    public List<Appointment> getAppointmentsByStatus(int idStatus);
    public List<Appointment> getAppointmentsByDateRange(Date start, Date end, User doctor);
}
