/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.carloscortina.demo.service;

import com.carloscortina.demo.dao.IncompatibleDrugsDao;
import com.carloscortina.demo.model.Incompatibledrugs;
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
public class IncompatibleDrugsServiceImp implements IncompatibleDrugsService{

    @Autowired
    private IncompatibleDrugsDao dao;
    
    @Override
    public void create(Incompatibledrugs item) {
        dao.create(item);
    }

    @Override
    public void delete(Incompatibledrugs item) {
        dao.delete(item);
    }

    @Override
    public Incompatibledrugs getById(int id) {
        return dao.getById(id);
    }

    @Override
    public void updateItem(Incompatibledrugs item) {
        dao.updateItem(item);
    }

    @Override
    public void mergeItem(Incompatibledrugs item) {
        dao.mergeItem(item);
    }

    @Override
    public List<Incompatibledrugs> getAll(String table) {
        return dao.getAll(table);
    }

    @Override
    public List<Incompatibledrugs> getAllActiveItems() {
        return dao.getAllActiveItems();
    }

    @Override
    public List<Incompatibledrugs> getListOfItem(String query) {
        return dao.getListOfItem(query);
    }

    @Override
    public List<Incompatibledrugs> getSpecificColumnsList(List<String> columns, Criterion restrictions) {
        return dao.getSpecificColumnsList(columns, restrictions);
    }

    @Override
    public List<Incompatibledrugs> getSpecificColumnsList(List<String> columns, LogicalExpression restrictions) {
        return dao.getSpecificColumnsList(columns, restrictions);
    }
    
}
