/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.carloscortina.demo.service;

import com.carloscortina.demo.dao.Cie10DoctorDao;
import com.carloscortina.demo.model.Cie10doctor;
import java.util.List;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Ccortina_Mac
 */
@Service
@Transactional
public class Cie10DoctorServiceImp implements Cie10DoctorService{

    @Autowired
    Cie10DoctorDao cdDao;
    
    @Override
    public void create(Cie10doctor item) {
        cdDao.create(item);
    }

    @Override
    public void delete(Cie10doctor item) {
        cdDao.delete(item);
    }

    @Override
    public Cie10doctor getById(int id) {
        return cdDao.getById(id);
    }

    @Override
    public void updateItem(Cie10doctor item) {
        cdDao.updateItem(item);
    }

    @Override
    public List<Cie10doctor> getAll(String table) {
        return cdDao.getAll(table);
    }

    @Override
    public List<Cie10doctor> getListOfItem(String query) {
        return cdDao.getListOfItem(query);
    }

    @Override
    public List<Cie10doctor> getSpecificColumnsList(List<String> columns,Criterion restrictions) {
        return cdDao.getSpecificColumnsList(columns,restrictions);
    }
    
    @Override
    public List<Cie10doctor> getSpecificColumnsList(List<String> columns, LogicalExpression restrictions) {
        return cdDao.getSpecificColumnsList(columns,restrictions);
    }

    @Override
    public void mergeItem(Cie10doctor item) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Cie10doctor> getCie10ByUser(int id) {
        return cdDao.getCie10ByUser(id);
    }

    @Override
    public List<Cie10doctor> getAllActiveItems() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
