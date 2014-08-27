/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.carloscortina.demo.service;

import com.carloscortina.demo.dao.DoseCalculationCriteriaDao;
import com.carloscortina.demo.model.Dosecalculationcriteria;
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
public class DoseCalculationCriteriaServiceImp implements DoseCalculationCriteriaService{

    @Autowired
    private DoseCalculationCriteriaDao dao;
    
    @Override
    public void create(Dosecalculationcriteria item) {
        dao.create(item);
    }

    @Override
    public void delete(Dosecalculationcriteria item) {
        dao.delete(item);
    }

    @Override
    public Dosecalculationcriteria getById(int id) {
        return dao.getById(id);
    }

    @Override
    public void updateItem(Dosecalculationcriteria item) {
        dao.updateItem(item);
    }

    @Override
    public void mergeItem(Dosecalculationcriteria item) {
        dao.mergeItem(item);
    }

    @Override
    public List<Dosecalculationcriteria> getAll(String table) {
        return dao.getAll(table);
    }

    @Override
    public List<Dosecalculationcriteria> getListOfItem(String query) {
        return dao.getListOfItem(query);
    }

    @Override
    public List<Dosecalculationcriteria> getSpecificColumnsList(List<String> columns, Criterion restrictions) {
        return dao.getSpecificColumnsList(columns, restrictions);
    }

    @Override
    public List<Dosecalculationcriteria> getSpecificColumnsList(List<String> columns, LogicalExpression restrictions) {
        return dao.getSpecificColumnsList(columns, restrictions);
    }

    @Override
    public List<Dosecalculationcriteria> getAllActiveItems() {
        return dao.getAllActiveItems();
    }
    
}
