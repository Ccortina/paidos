/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.carloscortina.demo.service;

import com.carloscortina.demo.dao.VaccineTypeDao;
import com.carloscortina.demo.model.Vaccinetype;
import java.util.List;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Carlos Cortina
 */
@Service
@Transactional
public class VaccineTypeServiceImp implements VaccineTypeService{

    @Autowired
    private VaccineTypeDao dao; 
    
    @Override
    public void create(Vaccinetype item) {
        dao.create(item);
    }

    @Override
    public void delete(Vaccinetype item) {
        dao.delete(item);
    }

    @Override
    public Vaccinetype getById(int id) {
        return dao.getById(id);
    }

    @Override
    public void updateItem(Vaccinetype item) {
        dao.updateItem(item);
    }

    @Override
    public void mergeItem(Vaccinetype item) {
        dao.mergeItem(item);
    }

    @Override
    public List<Vaccinetype> getAll(String table) {
        return dao.getAll(table);
    }

    @Override
    public List<Vaccinetype> getListOfItem(String query) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Vaccinetype> getSpecificColumnsList(List<String> columns, Criterion restrictions) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Vaccinetype> getSpecificColumnsList(List<String> columns, LogicalExpression restrictions) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Vaccinetype> getAllActiveItems() {
        return dao.getAllActiveItems();
    }
    
}
