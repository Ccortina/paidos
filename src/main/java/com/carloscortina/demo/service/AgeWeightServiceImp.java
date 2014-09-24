/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.carloscortina.demo.service;

import com.carloscortina.demo.dao.AgeWeightDao;
import com.carloscortina.demo.model.Ageweight0to240;
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
public class AgeWeightServiceImp implements AgeWeightService{

    @Autowired
    private AgeWeightDao dao;
    
    @Override
    public List<Ageweight0to240> getAllDataFor0to36Months(Patient patient) {
        return dao.getAllDataFor0to36Months(patient);
    }

    @Override
    public void create(Ageweight0to240 item) {
        dao.create(item);
    }

    @Override
    public void delete(Ageweight0to240 item) {
        dao.delete(item);
    }

    @Override
    public Ageweight0to240 getById(int id) {
        return dao.getById(id);
    }

    @Override
    public void updateItem(Ageweight0to240 item) {
        dao.updateItem(item);
    }

    @Override
    public void mergeItem(Ageweight0to240 item) {
        dao.mergeItem(item);
    }

    @Override
    public List<Ageweight0to240> getAll(String table) {
        return dao.getAll(table);
    }

    @Override
    public List<Ageweight0to240> getAllActiveItems() {
        return dao.getAllActiveItems();
    }

    @Override
    public List<Ageweight0to240> getListOfItem(String query) {
        return dao.getListOfItem(query);
    }

    @Override
    public List<Ageweight0to240> getSpecificColumnsList(List<String> columns, Criterion restrictions) {
        return dao.getSpecificColumnsList(columns, restrictions);
    }

    @Override
    public List<Ageweight0to240> getSpecificColumnsList(List<String> columns, LogicalExpression restrictions) {
        return dao.getSpecificColumnsList(columns, restrictions);
    }

    @Override
    public List<Ageweight0to240> getAllDataFor24to240Months(Patient patient) {
        return dao.getAllDataFor24to240Months(patient);
    }
    
    
}
