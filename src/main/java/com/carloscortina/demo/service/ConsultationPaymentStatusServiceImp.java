/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.carloscortina.demo.service;

import com.carloscortina.demo.dao.ConsultationPaymentStatusDao;
import com.carloscortina.demo.model.Consultationpaymentestatus;
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
public class ConsultationPaymentStatusServiceImp implements ConsultationPaymentStatusService{

    @Autowired
    private ConsultationPaymentStatusDao dao;
    
    @Override
    public void create(Consultationpaymentestatus item) {
        dao.create(item);
    }

    @Override
    public void delete(Consultationpaymentestatus item) {
        dao.delete(item);
    }

    @Override
    public Consultationpaymentestatus getById(int id) {
        return dao.getById(id);
    }

    @Override
    public void updateItem(Consultationpaymentestatus item) {
        dao.updateItem(item);
    }

    @Override
    public void mergeItem(Consultationpaymentestatus item) {
        dao.mergeItem(item);
    }

    @Override
    public List<Consultationpaymentestatus> getAll(String table) {
        return dao.getAll(table);
    }

    @Override
    public List<Consultationpaymentestatus> getAllActiveItems() {
        return dao.getAllActiveItems();
    }

    @Override
    public List<Consultationpaymentestatus> getListOfItem(String query) {
        return dao.getListOfItem(query);
    }

    @Override
    public List<Consultationpaymentestatus> getSpecificColumnsList(List<String> columns, Criterion restrictions) {
        return dao.getSpecificColumnsList(columns, restrictions);
    }

    @Override
    public List<Consultationpaymentestatus> getSpecificColumnsList(List<String> columns, LogicalExpression restrictions) {
        return dao.getSpecificColumnsList(columns, restrictions);
    }
    
}
