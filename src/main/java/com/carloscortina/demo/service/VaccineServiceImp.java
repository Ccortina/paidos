/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.carloscortina.demo.service;

import com.carloscortina.demo.dao.VaccineDao;
import com.carloscortina.demo.model.Vaccine;
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
public class VaccineServiceImp implements VaccineService{

    @Autowired
    VaccineDao vaccineDao;
    
    @Override
    public void create(Vaccine item) {
        vaccineDao.create(item);
    }

    @Override
    public Vaccine getById(int id) {
        return vaccineDao.getById(id);
    }

    @Override
    public void updateItem(Vaccine item) {
        vaccineDao.updateItem(item);
    }

    @Override
    public List<Vaccine> getAll(String table) {
        return vaccineDao.getAll(table);
    }

    @Override
    public List<Vaccine> getListOfItem(String query) {
        return vaccineDao.getListOfItem(query);
    }

    @Override
    public void delete(Vaccine item) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Vaccine> getSpecificColumnsList(List<String> columns,Criterion restrictions) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Vaccine> getSpecificColumnsList(List<String> columns, LogicalExpression restrictions) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

  
    
}
