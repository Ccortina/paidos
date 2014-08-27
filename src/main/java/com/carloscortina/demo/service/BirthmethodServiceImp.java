/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.carloscortina.demo.service;

import com.carloscortina.demo.dao.BirthmethodDao;
import com.carloscortina.demo.model.Birthmethod;
import java.util.List;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Ccortina_Mac
 */
@Service
@Transactional
public class BirthmethodServiceImp implements BirthmethodService{

    @Autowired
    private BirthmethodDao birthmethodDao; 
    
    @Override
    public void create(Birthmethod item) {
        birthmethodDao.create(item);
    }

    @Override
    public void delete(Birthmethod item) {
        birthmethodDao.delete(item);
    }

    @Override
    public Birthmethod getById(int id) {
        return birthmethodDao.getById(id);
    }

    @Override
    public void updateItem(Birthmethod item) {
        birthmethodDao.updateItem(item);
    }

    @Override
    public List<Birthmethod> getAll(String table) {
        return birthmethodDao.getAll(table);
    }

    @Override
    public List<Birthmethod> getListOfItem(String query) {
        return birthmethodDao.getListOfItem(query);
    }

    @Override
    public List<Birthmethod> getSpecificColumnsList(List<String> columns, Criterion restrictions) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Birthmethod> getSpecificColumnsList(List<String> columns, LogicalExpression restrictions) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mergeItem(Birthmethod item) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Birthmethod> getAllActiveItems() {
        return birthmethodDao.getAllActiveItems();
    }
    
}
