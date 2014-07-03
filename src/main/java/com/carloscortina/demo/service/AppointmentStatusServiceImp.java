/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.carloscortina.demo.service;

import com.carloscortina.demo.dao.appointmentStatusDao;
import com.carloscortina.demo.model.AppointmentStatus;
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
@Transactional
@Service
public class AppointmentStatusServiceImp implements AppointmentStatusService{

    @Autowired
    appointmentStatusDao apsDao; 
    
    @Override
    public void create(AppointmentStatus item) {
        apsDao.create(item);
    }

    @Override
    public void delete(AppointmentStatus item) {
        apsDao.delete(item);
    }

    @Override
    public AppointmentStatus getById(int id) {
        return apsDao.getById(id);
    }

    @Override
    public void updateItem(AppointmentStatus item) {
        apsDao.updateItem(item);
    }

    @Override
    public List<AppointmentStatus> getAll(String table) {
        return apsDao.getAll(table);
    }

    @Override
    public List<AppointmentStatus> getListOfItem(String query) {
        return apsDao.getListOfItem(query);
    }

    @Override
    public List<AppointmentStatus> getSpecificColumnsList(List<String> columns, Criterion restrictions) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<AppointmentStatus> getSpecificColumnsList(List<String> columns, LogicalExpression restrictions) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    
}
