/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.carloscortina.demo.service;

import com.carloscortina.demo.dao.ActivityTypeDao;
import com.carloscortina.demo.model.ActivityType;
import java.util.List;
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
    public void create(ActivityType item) {
        activityTypeDao.create(item);
    }

    @Override
    public ActivityType getById(int id) {
        return activityTypeDao.getById(id);
    }

    @Override
    public void updateItem(ActivityType item) {
        activityTypeDao.updateItem(item);
    }

    @Override
    public List<ActivityType> getAll(String table) {
        return activityTypeDao.getAll(table);
    }

    @Override
    public List<ActivityType> getListOfItem(String query) {
        return activityTypeDao.getListOfItem(query);
    }

    @Override
    public void delete(ActivityType item) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<ActivityType> getSpecificColumnsList(List<String> columns) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
