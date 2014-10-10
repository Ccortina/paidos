/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.carloscortina.demo.service;

import com.carloscortina.demo.model.Consultationpaymentreceipt;
import com.carloscortina.demo.model.ReceiptTotal;
import com.carloscortina.demo.model.User;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Carlos Cortina
 */
public interface ConsultationPaymentReceiptService extends GenericService<Consultationpaymentreceipt>{

    public List<Consultationpaymentreceipt> getConsultationPAymentByDateRange(Date start,Date end,User user);
    public List<Consultationpaymentreceipt> getConsultationPaymentReceiptTotals(Date start,Date end,User user);
}
