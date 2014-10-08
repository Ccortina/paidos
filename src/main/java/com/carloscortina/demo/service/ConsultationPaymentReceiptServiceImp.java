/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.carloscortina.demo.service;

import com.carloscortina.demo.dao.ConsultationPaymentReceiptDao;
import com.carloscortina.demo.model.Consultationpaymentreceipt;
import java.util.Date;
import java.util.List;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Carlos Cortina
 */
@Service
@Transactional
public class ConsultationPaymentReceiptServiceImp implements ConsultationPaymentReceiptService{

    @Autowired
    private ConsultationPaymentReceiptDao dao;
    
    @Override
    public void create(Consultationpaymentreceipt item) {
        dao.create(item);
    }

    @Override
    public void delete(Consultationpaymentreceipt item) {
        dao.delete(item);
    }

    @Override
    public Consultationpaymentreceipt getById(int id) {
        return dao.getById(id);
    }

    @Override
    public void updateItem(Consultationpaymentreceipt item) {
        dao.updateItem(item);
    }

    @Override
    public void mergeItem(Consultationpaymentreceipt item) {
        dao.mergeItem(item);
    }

    @Override
    public List<Consultationpaymentreceipt> getAll(String table) {
        return dao.getAll(table);
    }

    @Override
    public List<Consultationpaymentreceipt> getAllActiveItems() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Consultationpaymentreceipt> getListOfItem(String query) {
        return dao.getListOfItem(query);
    }

    @Override
    public List<Consultationpaymentreceipt> getSpecificColumnsList(List<String> columns, Criterion restrictions) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Consultationpaymentreceipt> getSpecificColumnsList(List<String> columns, LogicalExpression restrictions) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Consultationpaymentreceipt> getConsultationPAymentByDateRange(Date start, Date end) {
        return dao.getConsultationPAymentByDateRange(start, end);
    }
    
}
