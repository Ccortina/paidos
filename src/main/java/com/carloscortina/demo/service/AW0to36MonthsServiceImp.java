/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.carloscortina.demo.service;

import com.carloscortina.demo.dao.AW0to36MonthsDao;
import com.carloscortina.demo.model.Ageweight0to36months;
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
public class AW0to36MonthsServiceImp implements AW0to36MonthsService{

    @Autowired
    AW0to36MonthsDao aw036Dao;
    
    @Override
    public void create(Ageweight0to36months item) {
        aw036Dao.create(item);
    }

    @Override
    public void delete(Ageweight0to36months item) {
        aw036Dao.delete(item);
    }

    @Override
    public Ageweight0to36months getById(int id) {
        return aw036Dao.getById(id);
    }

    @Override
    public void updateItem(Ageweight0to36months item) {
        aw036Dao.updateItem(item);
    }

    @Override
    public List<Ageweight0to36months> getAll(String table) {
        return aw036Dao.getAll(table);
    }

    @Override
    public List<Ageweight0to36months> getListOfItem(String query) {
        return aw036Dao.getListOfItem(query);
    }

    @Override
    public List<Ageweight0to36months> getSpecificColumnsList(List<String> columns, Criterion restrictions) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Ageweight0to36months> getSpecificColumnsList(List<String> columns, LogicalExpression restrictions) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mergeItem(Ageweight0to36months item) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Ageweight0to36months> getAllActiveItems() {
        return aw036Dao.getAllActiveItems();
    }
    
}
