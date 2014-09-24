/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.carloscortina.demo.service;

import com.carloscortina.demo.dao.AgeHeightDao;
import com.carloscortina.demo.model.Agesize24to240;
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
public class AgeHeightServiceImp implements AgeHeightService{
    
    @Autowired
    private AgeHeightDao dao;

    @Override
    public List<Agesize24to240> getAllDataFor24to240Months(Patient patient) {
        return dao.getAllDataFor24to240Months(patient);
    }

    @Override
    public void create(Agesize24to240 item) {
        dao.create(item);
    }

    @Override
    public void delete(Agesize24to240 item) {
        dao.delete(item);
    }

    @Override
    public Agesize24to240 getById(int id) {
        return dao.getById(id);
    }

    @Override
    public void updateItem(Agesize24to240 item) {
        dao.updateItem(item);
    }

    @Override
    public void mergeItem(Agesize24to240 item) {
        dao.mergeItem(item);
    }

    @Override
    public List<Agesize24to240> getAll(String table) {
        return dao.getAll(table);
    }

    @Override
    public List<Agesize24to240> getAllActiveItems() {
        return dao.getAllActiveItems();
    }

    @Override
    public List<Agesize24to240> getListOfItem(String query) {
        return dao.getListOfItem(query);
    }

    @Override
    public List<Agesize24to240> getSpecificColumnsList(List<String> columns, Criterion restrictions) {
        return dao.getSpecificColumnsList(columns, restrictions);
    }

    @Override
    public List<Agesize24to240> getSpecificColumnsList(List<String> columns, LogicalExpression restrictions) {
        return dao.getSpecificColumnsList(columns, restrictions);
    }
    
}
