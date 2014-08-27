/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.carloscortina.demo.service;

import com.carloscortina.demo.dao.DrugPresentationDao;
import com.carloscortina.demo.model.Drugpresentation;
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
public class ServiceDrugPresentationImp implements ServiceDrugPresentation{

    @Autowired
    private DrugPresentationDao drugPresentationDao;
    
    @Override
    public void create(Drugpresentation item) {
        drugPresentationDao.create(item);
    }

    @Override
    public void delete(Drugpresentation item) {
        drugPresentationDao.delete(item);
    }

    @Override
    public Drugpresentation getById(int id) {
        return drugPresentationDao.getById(id);
    }

    @Override
    public void updateItem(Drugpresentation item) {
        drugPresentationDao.updateItem(item);
    }

    @Override
    public void mergeItem(Drugpresentation item) {
        drugPresentationDao.mergeItem(item);
    }

    @Override
    public List<Drugpresentation> getAll(String table) {
        return drugPresentationDao.getAll(table);
    }

    @Override
    public List<Drugpresentation> getListOfItem(String query) {
        return drugPresentationDao.getListOfItem(query);
    }

    @Override
    public List<Drugpresentation> getSpecificColumnsList(List<String> columns, Criterion restrictions) {
        return drugPresentationDao.getSpecificColumnsList(columns, restrictions);
    }

    @Override
    public List<Drugpresentation> getSpecificColumnsList(List<String> columns, LogicalExpression restrictions) {
        return drugPresentationDao.getSpecificColumnsList(columns, restrictions);
    }

    @Override
    public List<Drugpresentation> getAllActiveItems() {
        return drugPresentationDao.getAllActiveItems();
    }
    
}
