/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.carloscortina.demo.service;

import com.carloscortina.demo.dao.AgePcDao;
import com.carloscortina.demo.model.Agepc;
import com.carloscortina.demo.model.Patient;
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
public class AgePcServiceImp implements AgePcService{

    @Autowired
    private AgePcDao dao;
    
    @Override
    public List<Agepc> getAllDataFor0to36Months(Patient patient) {
        return dao.getAllDataFor0to36Months(patient);
    }

    @Override
    public void create(Agepc item) {
        dao.create(item);
    }

    @Override
    public void delete(Agepc item) {
        dao.delete(item);
    }

    @Override
    public Agepc getById(int id) {
        return dao.getById(id);
    }

    @Override
    public void updateItem(Agepc item) {
        dao.updateItem(item);
    }

    @Override
    public void mergeItem(Agepc item) {
        dao.mergeItem(item);
    }

    @Override
    public List<Agepc> getAll(String table) {
        return dao.getAll(table);
    }

    @Override
    public List<Agepc> getAllActiveItems() {
        return dao.getAllActiveItems();
    }

    @Override
    public List<Agepc> getListOfItem(String query) {
        return dao.getListOfItem(query);
    }

    @Override
    public List<Agepc> getSpecificColumnsList(List<String> columns, Criterion restrictions) {
        return dao.getSpecificColumnsList(columns, restrictions);
    }

    @Override
    public List<Agepc> getSpecificColumnsList(List<String> columns, LogicalExpression restrictions) {
        return dao.getSpecificColumnsList(columns, restrictions);
    }
    
}
