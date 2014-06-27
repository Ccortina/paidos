/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.carloscortina.demo.service;

import com.carloscortina.demo.dao.ActivityConsultationDao;
import com.carloscortina.demo.model.ActivityConsultation;
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
public class ActivityConsultationServiceImp implements ActivityConsultationService{

    @Autowired
    ActivityConsultationDao dao;
    
    @Override
    public void create(ActivityConsultation item) {
        dao.create(item);
    }

    @Override
    public void delete(ActivityConsultation item) {
        dao.delete(item);
    }

    @Override
    public ActivityConsultation getById(int id) {
        return dao.getById(id);
    }

    @Override
    public void updateItem(ActivityConsultation item) {
        dao.updateItem(item);
    }

    @Override
    public List<ActivityConsultation> getAll(String table) {
        return dao.getAll(table);
    }

    @Override
    public List<ActivityConsultation> getListOfItem(String query) {
        return dao.getListOfItem(query);
    }

    @Override
    public List<ActivityConsultation> getSpecificColumnsList(List<String> columns) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
