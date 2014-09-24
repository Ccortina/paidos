/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.carloscortina.demo.service;

import com.carloscortina.demo.dao.ConsultationtypeDao;
import com.carloscortina.demo.model.Consultationtype;
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
public class ConsultationtypeServiceImp implements ConsultationtypeService{

    @Autowired
    private ConsultationtypeDao dao;
    
    @Override
    public void create(Consultationtype item) {
        dao.create(item);
    }

    @Override
    public void delete(Consultationtype item) {
        dao.delete(item);
    }

    @Override
    public Consultationtype getById(int id) {
        return dao.getById(id);
    }

    @Override
    public void updateItem(Consultationtype item) {
        dao.updateItem(item);
    }

    @Override
    public void mergeItem(Consultationtype item) {
        dao.mergeItem(item);
    }

    @Override
    public List<Consultationtype> getAll(String table) {
        return dao.getAll(table);
    }

    @Override
    public List<Consultationtype> getAllActiveItems() {
        return dao.getAllActiveItems();
    }

    @Override
    public List<Consultationtype> getListOfItem(String query) {
        return dao.getListOfItem(query);
    }

    @Override
    public List<Consultationtype> getSpecificColumnsList(List<String> columns, Criterion restrictions) {
        return dao.getSpecificColumnsList(columns, restrictions);
    }

    @Override
    public List<Consultationtype> getSpecificColumnsList(List<String> columns, LogicalExpression restrictions) {
        return dao.getSpecificColumnsList(columns, restrictions);
    }
    
}
