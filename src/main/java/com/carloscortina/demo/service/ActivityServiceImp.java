/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.carloscortina.demo.service;

import com.carloscortina.demo.dao.ActivityDao;
import com.carloscortina.demo.model.Activity;
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
public class ActivityServiceImp implements ActivityService{

    @Autowired
    private ActivityDao activityDao;
    
    @Override
    public void create(Activity item) {
        activityDao.create(item);
    }

    @Override
    public Activity getById(int id) {
        return activityDao.getById(id);
    }

    @Override
    public void updateItem(Activity item) {
        activityDao.updateItem(item);
    }

    @Override
    public List<Activity> getAll(String table) {
        return activityDao.getAll(table);
    }

    @Override
    public List<Activity> getListOfItem(String query) {
        return activityDao.getListOfItem(query);
    }

    @Override
    public void delete(Activity item) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Activity> getSpecificColumnsList(List<String> columns, Criterion restrictions) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Activity> getSpecificColumnsList(List<String> columns, LogicalExpression restrictions) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Activity> getActivitiesByUser(int idUser) {
        return activityDao.getActivitiesByUser(idUser);
    }

    @Override
    public void mergeItem(Activity item) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Activity> getAllActiveItems() {
        return activityDao.getAllActiveItems();
    }

    
}
