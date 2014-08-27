/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.carloscortina.demo.service;

import com.carloscortina.demo.dao.AdministrationUnitDao;
import com.carloscortina.demo.model.Administrationunit;
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
public class AdministrationUnitServiceImp implements AdministrationUnitService{

    @Autowired
    private AdministrationUnitDao dao;
    
    @Override
    public void create(Administrationunit item) {
        dao.create(item);
    }

    @Override
    public void delete(Administrationunit item) {
        dao.delete(item);
    }

    @Override
    public Administrationunit getById(int id) {
        return dao.getById(id);
    }

    @Override
    public void updateItem(Administrationunit item) {
        dao.updateItem(item);
    }

    @Override
    public void mergeItem(Administrationunit item) {
        dao.mergeItem(item);
    }

    @Override
    public List<Administrationunit> getAll(String table) {
        return dao.getAll(table);
    }

    @Override
    public List<Administrationunit> getListOfItem(String query) {
        return dao.getListOfItem(query);
    }

    @Override
    public List<Administrationunit> getSpecificColumnsList(List<String> columns, Criterion restrictions) {
        return dao.getSpecificColumnsList(columns, restrictions);
    }

    @Override
    public List<Administrationunit> getSpecificColumnsList(List<String> columns, LogicalExpression restrictions) {
        return dao.getSpecificColumnsList(columns, restrictions);
    }

    @Override
    public List<Administrationunit> getAllActiveItems() {
        return dao.getAllActiveItems();
    }
    
}
