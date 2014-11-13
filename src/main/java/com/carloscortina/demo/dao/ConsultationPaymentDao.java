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

/**
 *
 * @author Carlos Cortina
 */
public interface ConsultationPaymentDao extends GenericDao<Consultationpayment>{

    public List<Consultationpayment> getConsultationPAymentByDateRange(Date start,Date end);
    public List<Consultationpayment> getConsultationPaymentByDateRange(Date start,Date end,User doctor);
    public List<Consultationpayment> getConsultationPaymentByUser(User current);
}
