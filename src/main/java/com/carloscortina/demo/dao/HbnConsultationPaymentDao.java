/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.carloscortina.demo.dao;

import com.carloscortina.demo.model.Consultationpayment;
import com.carloscortina.demo.model.User;
import java.util.Date;
import java.util.List;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Carlos Cortina
 */
@Repository
public class HbnConsultationPaymentDao extends GenericHbnDao<Consultationpayment> implements ConsultationPaymentDao{

    @Override
    public List<Consultationpayment> getConsultationPAymentByDateRange(Date start,Date end){
        
        Query query = getSession().createQuery("FROM Consultationpayment cp WHERE cp.idConsultationCostAbstract.idConsultation.idAppointment.date >= :bDate AND "
                + "cp.idConsultationCostAbstract.idConsultation.idAppointment.date <= :eDate");
        query.setParameter("bDate", start);
        query.setParameter("eDate", end);
        
        return query.list();
    }

    @Override
    public List<Consultationpayment> getConsultationPaymentByDateRange(Date start, Date end, User doctor) {
        Query query = getSession().createQuery("FROM Consultationpayment cp WHERE cp.idConsultationCostAbstract.idConsultation.idAppointment.date >= :bDate AND "
                + "cp.idConsultationCostAbstract.idConsultation.idAppointment.date <= :eDate "
                + "AND cp.idConsultationCostAbstract.idConsultation.idDoctor.idUser=:idUser AND "
                + "idConsultationCostAbstract.idConsultationPaymentStatus.idConsultationPaymentEstatus=3");
        query.setParameter("bDate", start);
        query.setParameter("eDate", end);
        query.setParameter("idUser", doctor.getIdUser());
        
        return query.list();
    }

    @Override
    public List<Consultationpayment> getConsultationPaymentByUser(User current) {
        if(current.getIdRole().getRole().equals("Doctor")){
            Query query = getSession().createQuery("FROM Consultationpayment cp "
                    + "WHERE cp.idConsultationCostAbstract.idConsultation.idDoctor.idUser=:doctor");
            query.setParameter("doctor", current.getIdUser());
            return query.list();
        }else{
            Query query = getSession().createQuery("FROM Consultationpayment");
            return query.list();
        }
    }
    
}
