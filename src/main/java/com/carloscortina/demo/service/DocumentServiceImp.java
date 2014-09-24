/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.carloscortina.demo.service;

import com.carloscortina.demo.dao.DocumentDao;
import com.carloscortina.demo.model.Documents;
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
public class DocumentServiceImp implements DocumentService{
    @Autowired
    private DocumentDao dao;
    
    @Override
    public void create(Documents item) {
        dao.create(item);
    }

    @Override
    public void delete(Documents item) {
        dao.delete(item);
    }

    @Override
    public Documents getById(int id) {
        return dao.getById(id);
    }

    @Override
    public void updateItem(Documents item) {
        dao.updateItem(item);
    }

    @Override
    public void mergeItem(Documents item) {
        dao.mergeItem(item);
    }

    @Override
    public List<Documents> getAll(String table) {
        return dao.getAll(table);
    }

    @Override
    public List<Documents> getListOfItem(String query) {
        return dao.getListOfItem(query);
    }

    @Override
    public List<Documents> getSpecificColumnsList(List<String> columns, Criterion restrictions) {
        return dao.getSpecificColumnsList(columns, restrictions);
    }

    @Override
    public List<Documents> getSpecificColumnsList(List<String> columns, LogicalExpression restrictions) {
        return dao.getSpecificColumnsList(columns, restrictions);
    }

    @Override
    public List<Documents> getAllActiveItems() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Documents> getDocumentByType(int idType) {
        return dao.getDocumentByType(idType);
    }

    @Override
    public List<Documents> getDocumentByPatient(int idPatient) {
        return dao.getDocumentByPatient(idPatient);
    }
    
}
