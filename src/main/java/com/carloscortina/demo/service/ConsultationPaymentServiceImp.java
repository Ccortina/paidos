/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.carloscortina.demo.service;

import com.carloscortina.demo.dao.ConsultationPaymentDao;
import com.carloscortina.demo.model.Consultationpayment;
import com.carloscortina.demo.model.User;
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
public class ConsultationPaymentServiceImp implements ConsultationPaymentService{

    @Autowired
    private ConsultationPaymentDao dao;
    
    @Override
    public void create(Consultationpayment item) {
        dao.create(item);
    }

    @Override
    public void delete(Consultationpayment item) {
        dao.delete(item);
    }

    @Override
    public Consultationpayment getById(int id) {
        return dao.getById(id);
    }

    @Override
    public void updateItem(Consultationpayment item) {
        dao.updateItem(item);
    }

    @Override
    public void mergeItem(Consultationpayment item) {
        dao.mergeItem(item);
    }

    @Override
    public List<Consultationpayment> getAll(String table) {
        return dao.getAll(table);
    }

    @Override
    public List<Consultationpayment> getAllActiveItems() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Consultationpayment> getListOfItem(String query) {
        return dao.getListOfItem(query);
    }

    @Override
    public List<Consultationpayment> getSpecificColumnsList(List<String> columns, Criterion restrictions) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Consultationpayment> getSpecificColumnsList(List<String> columns, LogicalExpression restrictions) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Consultationpayment> getConsultationPAymentByDateRange(Date start, Date end) {
        return dao.getConsultationPAymentByDateRange(start, end);
    }

    @Override
    public List<Consultationpayment> getConsultationPaymentByDateRange(Date start, Date end, User doctor) {
        return dao.getConsultationPaymentByDateRange(start, end, doctor);
    }
    
}
