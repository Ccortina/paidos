/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.carloscortina.demo.service;

import com.carloscortina.demo.dao.age4sizeDao;
import com.carloscortina.demo.model.Agesize0to36;
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
public class age4sizeServiceImp implements age4sizeService{

    @Autowired
    private age4sizeDao dao;
    
    @Override
    public void create(Agesize0to36 item) {
        dao.create(item);
    }

    @Override
    public void delete(Agesize0to36 item) {
        dao.delete(item);
    }

    @Override
    public Agesize0to36 getById(int id) {
        return dao.getById(id);
    }

    @Override
    public void updateItem(Agesize0to36 item) {
        dao.updateItem(item);
    }

    @Override
    public void mergeItem(Agesize0to36 item) {
        dao.mergeItem(item);
    }

    @Override
    public List<Agesize0to36> getAll(String table) {
        return dao.getAll(table);
    }

    @Override
    public List<Agesize0to36> getAllActiveItems() {
        return dao.getAllActiveItems();
    }

    @Override
    public List<Agesize0to36> getListOfItem(String query) {
        return dao.getListOfItem(query);
    }

    @Override
    public List<Agesize0to36> getSpecificColumnsList(List<String> columns, Criterion restrictions) {
        return dao.getSpecificColumnsList(columns, restrictions);
    }

    @Override
    public List<Agesize0to36> getSpecificColumnsList(List<String> columns, LogicalExpression restrictions) {
        return dao.getSpecificColumnsList(columns, restrictions);
    }

    @Override
    public List<Agesize0to36> getAllDataFor0to36Months(Patient patient) {
        return dao.getAllDataFor0to36Months(patient);
    }
    
}
