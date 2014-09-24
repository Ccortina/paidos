/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.carloscortina.demo.service;

import com.carloscortina.demo.dao.AgebmiDao;
import com.carloscortina.demo.model.Agebmi;
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
public class AgebmiServiceImp implements AgebmiService{
    
    @Autowired
    private AgebmiDao dao;

    @Override
    public List<Agebmi> getAllDataForPatient(Patient patient) {
        return dao.getAllDataForPatient(patient);
    }

    @Override
    public void create(Agebmi item) {
        dao.create(item);
    }

    @Override
    public void delete(Agebmi item) {
        dao.delete(item);
    }

    @Override
    public Agebmi getById(int id) {
        return dao.getById(id);
    }

    @Override
    public void updateItem(Agebmi item) {
        dao.updateItem(item);
    }

    @Override
    public void mergeItem(Agebmi item) {
        dao.mergeItem(item);
    }

    @Override
    public List<Agebmi> getAll(String table) {
        return dao.getAll(table);
    }

    @Override
    public List<Agebmi> getAllActiveItems() {
        return dao.getAllActiveItems();
    }

    @Override
    public List<Agebmi> getListOfItem(String query) {
        return dao.getListOfItem(query);
    }

    @Override
    public List<Agebmi> getSpecificColumnsList(List<String> columns, Criterion restrictions) {
        return dao.getSpecificColumnsList(columns, restrictions);
    }

    @Override
    public List<Agebmi> getSpecificColumnsList(List<String> columns, LogicalExpression restrictions) {
        return dao.getSpecificColumnsList(columns, restrictions);
    }
    
}
