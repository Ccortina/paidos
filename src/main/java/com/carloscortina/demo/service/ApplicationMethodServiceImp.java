/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.carloscortina.demo.service;

import com.carloscortina.demo.dao.ApplicationMethodDao;
import com.carloscortina.demo.model.ApplicationMethod;
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
public class ApplicationMethodServiceImp implements ApplicationMethodService{

    @Autowired
    private ApplicationMethodDao dao;
    
    @Override
    public void create(ApplicationMethod item) {
        dao.create(item);
    }

    @Override
    public void delete(ApplicationMethod item) {
        dao.delete(item);
    }

    @Override
    public ApplicationMethod getById(int id) {
        return dao.getById(id);
    }

    @Override
    public void updateItem(ApplicationMethod item) {
        dao.updateItem(item);
    }

    @Override
    public void mergeItem(ApplicationMethod item) {
        dao.mergeItem(item);
    }

    @Override
    public List<ApplicationMethod> getAll(String table) {
        return dao.getAll(table);
    }

    @Override
    public List<ApplicationMethod> getListOfItem(String query) {
        return dao.getListOfItem(query);
    }

    @Override
    public List<ApplicationMethod> getSpecificColumnsList(List<String> columns, Criterion restrictions) {
        return dao.getSpecificColumnsList(columns, restrictions);
    }

    @Override
    public List<ApplicationMethod> getSpecificColumnsList(List<String> columns, LogicalExpression restrictions) {
        return dao.getSpecificColumnsList(columns, restrictions);
    }
    
}
