package com.carloscortina.demo.dao;

import java.util.List;

import com.carloscortina.demo.model.Appointment;
import java.util.Date;

public interface AppointmentDao extends GenericDao<Appointment> {

        public List<Appointment> getAppointments(Date start, Date end);
        public List<Appointment> getAppointments(Date start, Date end,int idDoctor);
}
