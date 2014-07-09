package com.carloscortina.demo.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.carloscortina.demo.model.Appointment;
import java.util.Date;
import org.hibernate.Query;


@Repository
public class HbnAppointmentDao extends GenericHbnDao<Appointment> implements AppointmentDao {
    
    public List<Appointment> getAppointments(Date start, Date end){
        Query query = getSession().createQuery("FROM Appointment a WHERE a.date >= :start AND a.date <= :end");
        query.setParameter("start", start);
        query.setParameter("end", end);
        return query.list();
    }

    @Override
    public List<Appointment> getAppointments(Date start, Date end, int idDoctor) {
        Query query = getSession().createQuery("FROM Appointment a WHERE a.date >= :start AND a.date <= :end AND a.idDoctor= :doctor");
        query.setParameter("start", start);
        query.setParameter("end", end);
        query.setParameter("doctor", idDoctor);
        return query.list();
    }
}
