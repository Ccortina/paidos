/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.carloscortina.demo.service;

import com.carloscortina.demo.dao.VaccineTypeDao;
import com.carloscortina.demo.model.VaccineType;
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
    public void create(VaccineType item) {
        dao.create(item);
    }

    @Override
    public void delete(VaccineType item) {
        dao.delete(item);
    }

    @Override
    public VaccineType getById(int id) {
        return dao.getById(id);
    }

    @Override
    public void updateItem(VaccineType item) {
        dao.updateItem(item);
    }

    @Override
    public void mergeItem(VaccineType item) {
        dao.mergeItem(item);
    }

    @Override
    public List<VaccineType> getAll(String table) {
        return dao.getAll(table);
    }

    @Override
    public List<VaccineType> getListOfItem(String query) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<VaccineType> getSpecificColumnsList(List<String> columns, Criterion restrictions) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<VaccineType> getSpecificColumnsList(List<String> columns, LogicalExpression restrictions) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
