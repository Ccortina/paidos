/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.carloscortina.demo.service;

import com.carloscortina.demo.dao.ActivityTypeDao;
import com.carloscortina.demo.model.Activitytype;
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
public class ActivityTypeServiceImp implements ActivityTypeService{

    @Autowired
    ActivityTypeDao activityTypeDao; 
    
    @Override
    public void create(Activitytype item) {
        activityTypeDao.create(item);
    }

    @Override
    public Activitytype getById(int id) {
        return activityTypeDao.getById(id);
    }

    @Override
    public void updateItem(Activitytype item) {
        activityTypeDao.updateItem(item);
    }

    @Override
    public List<Activitytype> getAll(String table) {
        return activityTypeDao.getAll(table);
    }

    @Override
    public List<Activitytype> getListOfItem(String query) {
        return activityTypeDao.getListOfItem(query);
    }

    @Override
    public void delete(Activitytype item) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Activitytype> getSpecificColumnsList(List<String> columns, Criterion restrictions) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Activitytype> getSpecificColumnsList(List<String> columns, LogicalExpression restrictions) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mergeItem(Activitytype item) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Activitytype> getAllActiveItems() {
        return activityTypeDao.getAllActiveItems();
    }
    
}
