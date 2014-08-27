/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.carloscortina.demo.service;

import com.carloscortina.demo.dao.MeasuresDao;
import com.carloscortina.demo.model.Measures;
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
public class MeasuresServiceImp implements MeasuresService{

    @Autowired
    MeasuresDao measuresDao;
    
    @Override
    public void create(Measures item) {
        measuresDao.create(item);
    }

    @Override
    public void delete(Measures item) {
        measuresDao.delete(item);
    }

    @Override
    public Measures getById(int id) {
        return  measuresDao.getById(id);
    }

    @Override
    public void updateItem(Measures item) {
        measuresDao.updateItem(item);
    }

    @Override
    public List<Measures> getAll(String table) {
        return measuresDao.getAll(table);
    }

    @Override
    public List<Measures> getListOfItem(String query) {
        return measuresDao.getListOfItem(query);
    }

    @Override
    public List<Measures> getSpecificColumnsList(List<String> columns, Criterion restrictions) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Measures> getSpecificColumnsList(List<String> columns, LogicalExpression restrictions) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mergeItem(Measures item) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Measures> getMeasureByUser(int idUser) {
        return measuresDao.getMeasureByUser(idUser);
    }

    @Override
    public List<Measures> getAllActiveItems() {
        return measuresDao.getAllActiveItems();
    }
    
}
