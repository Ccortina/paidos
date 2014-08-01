/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.carloscortina.demo.dao;

import com.carloscortina.demo.model.LaboratoryTestResult;
import com.carloscortina.demo.service.LaboratoryTestResultService;
import java.util.List;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Ccortina_Mac
 */
public class LaboratoryTestResultServiceImp implements LaboratoryTestResultService{

    @Autowired
    private LaboratoryTestResultDao labResDao;

    @Override
    public void delete(LaboratoryTestResult item) {
        labResDao.delete(item);
    }

    @Override
    public LaboratoryTestResult getById(int id) {
        return labResDao.getById(id);
    }

    @Override
    public void updateItem(LaboratoryTestResult item) {
        labResDao.updateItem(item);
    }

    @Override
    public List<LaboratoryTestResult> getAll(String table) {
        return labResDao.getAll(table);
    }

    @Override
    public List<LaboratoryTestResult> getListOfItem(String query) {
        return labResDao.getListOfItem(query);
    }

    @Override
    public List<LaboratoryTestResult> getSpecificColumnsList(List<String> columns,Criterion restrictions) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public List<LaboratoryTestResult> getSpecificColumnsList(List<String> columns, LogicalExpression restrictions) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void create(LaboratoryTestResult item) {
        labResDao.create(item);
    }

    @Override
    public void mergeItem(LaboratoryTestResult item) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
