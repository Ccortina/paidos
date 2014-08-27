/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.carloscortina.demo.service;

import com.carloscortina.demo.dao.UserroleDao;
import com.carloscortina.demo.model.Userrole;
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
public class UserroleServiceImp implements UserroleService{

    @Autowired
    UserroleDao dao;
    
    @Override
    public void create(Userrole item) {
        dao.create(item);
    }

    @Override
    public void delete(Userrole item) {
        dao.delete(item);
    }

    @Override
    public Userrole getById(int id) {
        return dao.getById(id);
    }

    @Override
    public void updateItem(Userrole item) {
        dao.updateItem(item);
    }

    @Override
    public void mergeItem(Userrole item) {
        dao.mergeItem(item);
    }

    @Override
    public List<Userrole> getAll(String table) {
        return dao.getAll(table);
    }

    @Override
    public List<Userrole> getListOfItem(String query) {
        return dao.getListOfItem(query);
    }

    @Override
    public List<Userrole> getSpecificColumnsList(List<String> columns, Criterion restrictions) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Userrole> getSpecificColumnsList(List<String> columns, LogicalExpression restrictions) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Userrole> getAllActiveItems() {
        return dao.getAllActiveItems();
    }
    
}
