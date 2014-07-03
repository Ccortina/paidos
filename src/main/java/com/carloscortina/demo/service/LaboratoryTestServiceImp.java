/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.carloscortina.demo.service;

import com.carloscortina.demo.dao.LaboratoryTestDao;
import com.carloscortina.demo.model.LaboratoryTest;
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
public class LaboratoryTestServiceImp implements LaboratoryTestService{

    @Autowired
    LaboratoryTestDao labDao;
    
    @Override
    public void create(LaboratoryTest item) {
        
    }

    @Override
    public void delete(LaboratoryTest item) {
        labDao.delete(item);
    }

    @Override
    public LaboratoryTest getById(int id) {
        return labDao.getById(id);
    }

    @Override
    public void updateItem(LaboratoryTest item) {
        labDao.updateItem(item);
    }

    @Override
    public List<LaboratoryTest> getAll(String table) {
        return labDao.getAll(table);
    }

    @Override
    public List<LaboratoryTest> getListOfItem(String query) {
        return labDao.getListOfItem(query);
    }

     @Override
    public List<LaboratoryTest> getSpecificColumnsList(List<String> columns,Criterion restrictions) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public List<LaboratoryTest> getSpecificColumnsList(List<String> columns, LogicalExpression restrictions) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
