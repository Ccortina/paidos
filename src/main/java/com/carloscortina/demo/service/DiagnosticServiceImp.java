/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.carloscortina.demo.service;

import com.carloscortina.demo.dao.DiagnosticDao;
import com.carloscortina.demo.model.Diagnostic;
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
public class DiagnosticServiceImp implements DiagnosticService{

    @Autowired
    DiagnosticDao dao;
    
    @Override
    public void create(Diagnostic item) {
        dao.create(item);
    }

    @Override
    public void delete(Diagnostic item) {
        dao.delete(item);
    }

    @Override
    public Diagnostic getById(int id) {
        return dao.getById(id);
    }

    @Override
    public void updateItem(Diagnostic item) {
        dao.updateItem(item);
    }

    @Override
    public List<Diagnostic> getAll(String table) {
        return dao.getAll(table);
    }

    @Override
    public List<Diagnostic> getListOfItem(String query) {
        return dao.getListOfItem(query);
    }

    @Override
    public List<Diagnostic> getSpecificColumnsList(List<String> columns, Criterion restrictions) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Diagnostic> getSpecificColumnsList(List<String> columns, LogicalExpression restrictions) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
