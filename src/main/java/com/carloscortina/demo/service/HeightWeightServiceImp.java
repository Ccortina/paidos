/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.carloscortina.demo.service;

import com.carloscortina.demo.dao.HeightWeightDao;
import com.carloscortina.demo.model.Heightweight;
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
public class HeightWeightServiceImp implements HeightWeightService{
    
    @Autowired
    private HeightWeightDao dao;

    @Override
    public List<Heightweight> getAllDataForPAtient(Patient patient) {
        return dao.getAllDataForPAtient(patient);
    }

    @Override
    public void create(Heightweight item) {
        dao.create(item);
    }

    @Override
    public void delete(Heightweight item) {
        dao.delete(item);
    }

    @Override
    public Heightweight getById(int id) {
        return dao.getById(id);
    }

    @Override
    public void updateItem(Heightweight item) {
        dao.updateItem(item);
    }

    @Override
    public void mergeItem(Heightweight item) {
        dao.mergeItem(item);
    }

    @Override
    public List<Heightweight> getAll(String table) {
        return dao.getAll(table);
    }

    @Override
    public List<Heightweight> getAllActiveItems() {
        return dao.getAllActiveItems();
    }

    @Override
    public List<Heightweight> getListOfItem(String query) {
        return dao.getListOfItem(query);
    }

    @Override
    public List<Heightweight> getSpecificColumnsList(List<String> columns, Criterion restrictions) {
        return dao.getSpecificColumnsList(columns, restrictions);
    }

    @Override
    public List<Heightweight> getSpecificColumnsList(List<String> columns, LogicalExpression restrictions) {
        return dao.getSpecificColumnsList(columns, restrictions);
    }
    
}
