/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.carloscortina.demo.service;

import com.carloscortina.demo.dao.appointmentStatusDao;
import com.carloscortina.demo.model.Appointmentstatus;
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
    public void create(Appointmentstatus item) {
        apsDao.create(item);
    }

    @Override
    public void delete(Appointmentstatus item) {
        apsDao.delete(item);
    }

    @Override
    public Appointmentstatus getById(int id) {
        return apsDao.getById(id);
    }

    @Override
    public void updateItem(Appointmentstatus item) {
        apsDao.updateItem(item);
    }

    @Override
    public List<Appointmentstatus> getAll(String table) {
        return apsDao.getAll(table);
    }

    @Override
    public List<Appointmentstatus> getListOfItem(String query) {
        return apsDao.getListOfItem(query);
    }

    @Override
    public List<Appointmentstatus> getSpecificColumnsList(List<String> columns, Criterion restrictions) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Appointmentstatus> getSpecificColumnsList(List<String> columns, LogicalExpression restrictions) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mergeItem(Appointmentstatus item) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Appointmentstatus> getAllActiveItems() {
        return apsDao.getAllActiveItems();
    }

    
    
}
