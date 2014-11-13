/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.carloscortina.demo.dao;

import com.carloscortina.demo.model.Consultationpaymentreceipt;
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
public class HbnConsultationPaymentReceiptDao extends GenericHbnDao<Consultationpaymentreceipt> implements ConsultationPaymentReceiptDao{

    @Override
    public List<Consultationpaymentreceipt> getConsultationPAymentByDateRange(Date start,Date end,User user){
        
        Query query = getSession().createQuery("FROM Consultationpaymentreceipt cpr WHERE cpr.date >= :bDate AND "
                + "cpr.date <= :eDate AND cpr.idExpeditor.idUser=:user");
        query.setParameter("bDate", start);
        query.setParameter("eDate", end);
        query.setParameter("user", user.getIdUser());
        
        return query.list();
    }
    
    @Override
    public List<Consultationpaymentreceipt> getConsultationPaymentReceiptTotals(Date start,Date end,User user){
        
        Query query = getSession().createQuery("SELECT new Consultationpaymentreceipt(cpr.idExpeditor,min(cpr.receiptNumber),max(cpr.receiptNumber),sum(cpr.total)) "
                + "FROM Consultationpaymentreceipt cpr WHERE cpr.date >= :bDate AND "
                + "cpr.date <= :eDate AND cpr.idExpeditor.idUser=:user");
        query.setParameter("bDate", start);
        query.setParameter("eDate", end);
        query.setParameter("user", user.getIdUser());
        
        Query query2 = getSession().createQuery("FROM Consultationpaymentreceipt cpr WHERE cpr.date >= :bDate AND "
                + "cpr.date <= :eDate AND cpr.idExpeditor.idUser=:user");
        query2.setParameter("bDate", start);
        query2.setParameter("eDate", end);
        query2.setParameter("user", user.getIdUser());
        
        List<Consultationpaymentreceipt> result = query.list();
        List<Consultationpaymentreceipt> result2 = query2.list();
        for(Consultationpaymentreceipt cpr: result){
            cpr.setTotalReceipts(result2.size());
        }
        
        return result;
    }

    @Override
    public List<Consultationpaymentreceipt> getCPRByUserRole(User current) {
        if(current.getIdRole().getRole().equals("Doctor")){
            Query query = getSession().createQuery("FROM Consultationpaymentreceipt cpr "
                    + "WHERE cpr.idPayment.idConsultationCostAbstract.idConsultation.idDoctor.idUser=:doctor");
            query.setParameter("doctor", current.getIdUser());
            return query.list();
        }else{
            Query query = getSession().createQuery("FROM Consultationpaymentreceipt cpr");
            return query.list();
        }
    }
}
