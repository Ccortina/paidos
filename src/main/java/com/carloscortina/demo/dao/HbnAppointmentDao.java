package com.carloscortina.demo.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.carloscortina.demo.model.Appointment;
import java.util.Date;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;


@Repository
public class HbnAppointmentDao extends GenericHbnDao<Appointment> implements AppointmentDao {
    
    @Override
    public List<Appointment> getAppointments(Date start, Date end){
        Query query = getSession().createQuery("FROM Appointment a WHERE a.date >= :start AND a.date <= :end");
        query.setParameter("start", start);
        query.setParameter("end", end);
        return query.list();
    }

    @Override
    public List<Appointment> getAppointments(Date start, Date end, int idDoctor) {
        Query query = getSession().createQuery("FROM Appointment a WHERE a.date >= :start AND a.date <= :end AND a.idDoctor.idUser= :doctor");
        query.setParameter("start", start);
        query.setParameter("end", end);
        query.setParameter("doctor", idDoctor);
        Criteria criteria = getSession().createCriteria(Appointment.class);
        criteria.add(Restrictions.eq("idDoctor.idUser", idDoctor));
        criteria.add(Restrictions.between("date", start, end));
        System.out.println(criteria.list());
        return query.list();
    }
    
    @Override
    public List<Appointment> getAppointmentsByPatient(Date start, Date end, int idPatient) {
        Query query = getSession().createQuery("FROM Appointment a WHERE a.date >= :start AND a.date <= :end AND a.idPatient= :idPatient");
        query.setParameter("start", start);
        query.setParameter("end", end);
        query.setParameter("idPatient", idPatient);
        return query.list();
    }
    
    @Override
    public List<Appointment> getAppointmentsByPatient(int idPatient) {
        Query query = getSession().createQuery("FROM Appointment a WHERE a.idPatient.idPatient= :idPatient");
        query.setParameter("idPatient", idPatient);
        return query.list();
    }
    
    @Override
    public List<Appointment> getAppointmentsByDay(Date start, Date end){
        Query query = getSession().createQuery("FROM Appointment a WHERE a.date >= :start AND a.date <= :end");
        query.setParameter("start", start);
        query.setParameter("end", end);
        return query.list();
    }

    @Override
    public List<Appointment> getAppointmentsByStatus(int idStatus) {
        Query query = getSession().createQuery("FROM Appointment a WHERE a.idStatus.idAppointmentStatus=:idStatus");
        query.setParameter("idStatus", idStatus);
        return query.list();
    }
}
