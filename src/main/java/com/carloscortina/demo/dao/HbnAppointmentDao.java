package com.carloscortina.demo.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.carloscortina.demo.model.Appointment;
import com.carloscortina.demo.model.Patient;
import java.util.Date;
import org.hibernate.Query;


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
        List<Appointment> appointmentList = query.list();
        
        for(Appointment a: appointmentList){
            /*Query subQuery = getSession().createQuery("SELECT new Patient(p.idPatient,p.firstName,p.fatherLastName,"
                + "p.motherLastName,p.sex,p.birthday,p.active,p.idDoctor) FROM Patient as p"
                + " WHERE p.isUser=:idPatient");
            subQuery.setParameter("idPatient", a.getIdPatient().getIdPatient());*/
            Patient patient = new Patient(a.getIdPatient().getIdPatient(), a.getIdPatient().getFirstName(),
                    a.getIdPatient().getFatherLastName(), a.getIdPatient().getMotherLastName(),
                    a.getIdPatient().getSex(), a.getIdPatient().getBirthday(), a.getIdPatient().getActive(),
                    a.getIdPatient().getIdDoctor());
            //a.setIdPatient((Patient)subQuery.uniqueResult());
            a.setIdPatient(patient);
        }
        return appointmentList;
    }
    
    @Override
    public List<Appointment> getAvaibleAppointmentsByPatient(Date start, Date end, int idPatient) {
        Query query = getSession().createQuery("FROM Appointment a WHERE a.date >= :start AND a.date <= :end AND a.idPatient.idPatient= :idPatient AND"
                + " a.idStatus.idAppointmentStatus != 1 AND a.idStatus.idAppointmentStatus != 3 AND a.idStatus.idAppointmentStatus != 4 AND "
                + "a.idStatus.idAppointmentStatus != 11");
        query.setParameter("start", start);
        query.setParameter("end", end);
        query.setParameter("idPatient", idPatient);
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
