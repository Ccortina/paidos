/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.carloscortina.demo.service;

import com.carloscortina.demo.dao.ConsultationPaymentTypeDao;
import com.carloscortina.demo.model.Consultationpaymenttype;
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
public class ConsultationPaymentTypeServiceImp implements ConsultationPaymentTypeService{

    @Autowired
    private ConsultationPaymentTypeDao dao;
    
    @Override
    public void create(Consultationpaymenttype item) {
        dao.create(item);
    }

    @Override
    public void delete(Consultationpaymenttype item) {
        dao.delete(item);
    }

    @Override
    public Consultationpaymenttype getById(int id) {
        return dao.getById(id);
    }

    @Override
    public void updateItem(Consultationpaymenttype item) {
        dao.updateItem(item);
    }

    @Override
    public void mergeItem(Consultationpaymenttype item) {
        dao.mergeItem(item);
    }

    @Override
    public List<Consultationpaymenttype> getAll(String table) {
        return dao.getAll(table);
    }

    @Override
    public List<Consultationpaymenttype> getAllActiveItems() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Consultationpaymenttype> getListOfItem(String query) {
        return dao.getListOfItem(query);
    }

    @Override
    public List<Consultationpaymenttype> getSpecificColumnsList(List<String> columns, Criterion restrictions) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Consultationpaymenttype> getSpecificColumnsList(List<String> columns, LogicalExpression restrictions) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
