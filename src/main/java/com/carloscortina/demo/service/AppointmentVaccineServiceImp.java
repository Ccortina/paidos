/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.carloscortina.demo.service;

import com.carloscortina.demo.dao.AppointmentVaccineDao;
import com.carloscortina.demo.model.Appointmentvaccine;
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
public class AppointmentVaccineServiceImp implements AppointmentVaccineService{
    @Autowired
    AppointmentVaccineDao avDao;

    @Override
    public void create(Appointmentvaccine item) {
        avDao.create(item);
    }

    @Override
    public void delete(Appointmentvaccine item) {
        avDao.delete(item);
    }

    @Override
    public Appointmentvaccine getById(int id) {
        return avDao.getById(id);
    }

    @Override
    public void updateItem(Appointmentvaccine item) {
        avDao.updateItem(item);
    }

    @Override
    public List<Appointmentvaccine> getAll(String table) {
        return avDao.getAll(table);
    }

    @Override
    public List<Appointmentvaccine> getListOfItem(String query) {
        return avDao.getListOfItem(query);
    }
    
    @Override
    public List<Appointmentvaccine> getSpecificColumnsList(List<String> columns, Criterion restrictions) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Appointmentvaccine> getSpecificColumnsList(List<String> columns, LogicalExpression restrictions) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mergeItem(Appointmentvaccine item) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Appointmentvaccine> getAllActiveItems() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
