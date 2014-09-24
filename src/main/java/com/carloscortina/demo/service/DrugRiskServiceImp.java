/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.carloscortina.demo.service;

import com.carloscortina.demo.dao.DrugRiskDao;
import com.carloscortina.demo.model.Drugrisk;
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
@Transactional
@Service
public class DrugRiskServiceImp implements DrugRiskService{
    @Autowired
    private DrugRiskDao dao;
    
    @Override
    public void create(Drugrisk item) {
        dao.create(item);
    }

    @Override
    public void delete(Drugrisk item) {
        dao.delete(item);
    }

    @Override
    public Drugrisk getById(int id) {
        return dao.getById(id);
    }

    @Override
    public void updateItem(Drugrisk item) {
        dao.updateItem(item);
    }

    @Override
    public void mergeItem(Drugrisk item) {
        dao.mergeItem(item);
    }

    @Override
    public List<Drugrisk> getAll(String table) {
        return dao.getAll(table);
    }

    @Override
    public List<Drugrisk> getAllActiveItems() {
        return dao.getAllActiveItems();
    }

    @Override
    public List<Drugrisk> getListOfItem(String query) {
        return dao.getListOfItem(query);
    }

    @Override
    public List<Drugrisk> getSpecificColumnsList(List<String> columns, Criterion restrictions) {
        return dao.getSpecificColumnsList(columns, restrictions);
    }

    @Override
    public List<Drugrisk> getSpecificColumnsList(List<String> columns, LogicalExpression restrictions) {
        return dao.getSpecificColumnsList(columns, restrictions);
    }
    
}
