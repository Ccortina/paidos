/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.carloscortina.demo.service;

import com.carloscortina.demo.model.Consultationpayment;
import com.carloscortina.demo.model.Consultationpaymentreceipt;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Carlos Cortina
 */
public interface ConsultationPaymentService extends GenericService<Consultationpayment>{

    public List<Consultationpayment> getConsultationPAymentByDateRange(Date start,Date end);
}
