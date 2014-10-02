/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.carloscortina.demo.service;

import com.carloscortina.demo.dao.ConsultationPaymentReceiptTypeDao;
import com.carloscortina.demo.model.Consultationpaymentreceipttype;
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
public class ConsultationPaymentReceiptTypeServiceImp implements ConsultationPaymentReceiptTypeService{

    @Autowired
    private ConsultationPaymentReceiptTypeDao dao;
    
    @Override
    public void create(Consultationpaymentreceipttype item) {
        dao.create(item);
    }

    @Override
    public void delete(Consultationpaymentreceipttype item) {
        dao.delete(item);
    }

    @Override
    public Consultationpaymentreceipttype getById(int id) {
        return dao.getById(id);
    }

    @Override
    public void updateItem(Consultationpaymentreceipttype item) {
        dao.updateItem(item);
    }

    @Override
    public void mergeItem(Consultationpaymentreceipttype item) {
        dao.mergeItem(item);
    }

    @Override
    public List<Consultationpaymentreceipttype> getAll(String table) {
        return dao.getAll(table);
    }

    @Override
    public List<Consultationpaymentreceipttype> getAllActiveItems() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Consultationpaymentreceipttype> getListOfItem(String query) {
        return dao.getListOfItem(query);
    }

    @Override
    public List<Consultationpaymentreceipttype> getSpecificColumnsList(List<String> columns, Criterion restrictions) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Consultationpaymentreceipttype> getSpecificColumnsList(List<String> columns, LogicalExpression restrictions) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
