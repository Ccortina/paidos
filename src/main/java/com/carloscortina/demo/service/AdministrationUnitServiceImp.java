/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.carloscortina.demo.service;

import com.carloscortina.demo.dao.AdministrationUnitDao;
import com.carloscortina.demo.model.AdministrationUnit;
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
    public void create(AdministrationUnit item) {
        dao.create(item);
    }

    @Override
    public void delete(AdministrationUnit item) {
        dao.delete(item);
    }

    @Override
    public AdministrationUnit getById(int id) {
        return dao.getById(id);
    }

    @Override
    public void updateItem(AdministrationUnit item) {
        dao.updateItem(item);
    }

    @Override
    public void mergeItem(AdministrationUnit item) {
        dao.mergeItem(item);
    }

    @Override
    public List<AdministrationUnit> getAll(String table) {
        return dao.getAll(table);
    }

    @Override
    public List<AdministrationUnit> getListOfItem(String query) {
        return dao.getListOfItem(query);
    }

    @Override
    public List<AdministrationUnit> getSpecificColumnsList(List<String> columns, Criterion restrictions) {
        return dao.getSpecificColumnsList(columns, restrictions);
    }

    @Override
    public List<AdministrationUnit> getSpecificColumnsList(List<String> columns, LogicalExpression restrictions) {
        return dao.getSpecificColumnsList(columns, restrictions);
    }
    
}
