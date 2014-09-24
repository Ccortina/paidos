/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.carloscortina.demo.service;

import com.carloscortina.demo.dao.SizeWeightDao;
import com.carloscortina.demo.model.Patient;
import com.carloscortina.demo.model.Sizeweight;
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
public class SizeWeightServiceImp implements SizeWeightService{

    @Autowired
    private SizeWeightDao dao;
    
    @Override
    public void create(Sizeweight item) {
        dao.create(item);
    }

    @Override
    public void delete(Sizeweight item) {
        dao.delete(item);
    }

    @Override
    public Sizeweight getById(int id) {
        return dao.getById(id);
    }

    @Override
    public void updateItem(Sizeweight item) {
        dao.updateItem(item);
    }

    @Override
    public void mergeItem(Sizeweight item) {
        dao.mergeItem(item);
    }

    @Override
    public List<Sizeweight> getAll(String table) {
        return dao.getAll(table);
    }

    @Override
    public List<Sizeweight> getAllActiveItems() {
        return dao.getAllActiveItems();
    }

    @Override
    public List<Sizeweight> getListOfItem(String query) {
        return dao.getListOfItem(query);
    }

    @Override
    public List<Sizeweight> getSpecificColumnsList(List<String> columns, Criterion restrictions) {
        return dao.getSpecificColumnsList(columns, restrictions);
    }

    @Override
    public List<Sizeweight> getSpecificColumnsList(List<String> columns, LogicalExpression restrictions) {
        return dao.getSpecificColumnsList(columns, restrictions);
    }

    @Override
    public List<Sizeweight> getAllDataForPatient(Patient patient) {
        return dao.getAllDataForPatient(patient);
    }
    
}
