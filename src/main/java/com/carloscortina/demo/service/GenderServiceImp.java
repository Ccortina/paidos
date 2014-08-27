/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.carloscortina.demo.service;

import com.carloscortina.demo.dao.GenderDao;
import com.carloscortina.demo.model.Gender;
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
public class GenderServiceImp implements GenderService{

    @Autowired
    private GenderDao dao;
    
    @Override
    public void create(Gender item) {
        dao.create(item);
    }

    @Override
    public void delete(Gender item) {
        dao.delete(item);
    }

    @Override
    public Gender getById(int id) {
        return dao.getById(id);
    }

    @Override
    public void updateItem(Gender item) {
        dao.updateItem(item);
    }

    @Override
    public void mergeItem(Gender item) {
        dao.mergeItem(item);
    }

    @Override
    public List<Gender> getAll(String table) {
        return dao.getAll(table);
    }

    @Override
    public List<Gender> getAllActiveItems() {
        return dao.getAllActiveItems();
    }

    @Override
    public List<Gender> getListOfItem(String query) {
        return dao.getListOfItem(query);
    }

    @Override
    public List<Gender> getSpecificColumnsList(List<String> columns, Criterion restrictions) {
        return dao.getSpecificColumnsList(columns, restrictions);
    }

    @Override
    public List<Gender> getSpecificColumnsList(List<String> columns, LogicalExpression restrictions) {
        return dao.getSpecificColumnsList(columns, restrictions);
    }
    
}
