/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.carloscortina.demo.service;

import com.carloscortina.demo.dao.LaboratoryTestResultDao;
import com.carloscortina.demo.model.Laboratorytestresult;
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
public class LaboratoryTestResultServiceImp implements LaboratoryTestResultService{
    
    @Autowired
    LaboratoryTestResultDao labResDao;
    
    @Override
    public void create(Laboratorytestresult item) {
        labResDao.create(item);
    }

    @Override
    public void delete(Laboratorytestresult item) {
        labResDao.delete(item);
    }

    @Override
    public Laboratorytestresult getById(int id) {
        return labResDao.getById(id);
    }

    @Override
    public void updateItem(Laboratorytestresult item) {
        labResDao.updateItem(item);
    }

    @Override
    public List<Laboratorytestresult> getAll(String table) {
        return labResDao.getAll(table);
    }

    @Override
    public List<Laboratorytestresult> getListOfItem(String query) {
        return labResDao.getListOfItem(query);
    }

    @Override
    public List<Laboratorytestresult> getSpecificColumnsList(List<String> columns, Criterion restrictions) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Laboratorytestresult> getSpecificColumnsList(List<String> columns, LogicalExpression restrictions) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mergeItem(Laboratorytestresult item) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Laboratorytestresult> getAllActiveItems() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
