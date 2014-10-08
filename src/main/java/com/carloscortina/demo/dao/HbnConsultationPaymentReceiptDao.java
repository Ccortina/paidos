/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.carloscortina.demo.dao;

import com.carloscortina.demo.model.Consultationpaymentreceipt;
import java.util.Date;
import java.util.List;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Carlos Cortina
 */
@Repository
public class HbnConsultationPaymentReceiptDao extends GenericHbnDao<Consultationpaymentreceipt> implements ConsultationPaymentReceiptDao{

    @Override
    public List<Consultationpaymentreceipt> getConsultationPAymentByDateRange(Date start,Date end){
        
        Query query = getSession().createQuery("FROM Consultationpaymentreceipt cpr WHERE cpr.date >= :bDate AND "
                + "cpr.date <= :eDate");
        query.setParameter("bDate", start);
        query.setParameter("eDate", end);
        
        return query.list();
    }
}
