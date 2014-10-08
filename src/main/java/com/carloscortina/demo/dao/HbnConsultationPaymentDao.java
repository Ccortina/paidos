/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.carloscortina.demo.dao;

import com.carloscortina.demo.model.Consultationpayment;
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
}
