/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.carloscortina.demo.service;

import com.carloscortina.demo.dao.ConsultationDiagnosticDao;
import com.carloscortina.demo.model.ConsultationDiagnostic;
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
public class ConsultationDiagnosticServiceImp implements ConsultationDiagnosticService{

    @Autowired
    ConsultationDiagnosticDao dao;
    
    @Override
    public void create(ConsultationDiagnostic item) {
        dao.create(item);
    }

    @Override
    public void delete(ConsultationDiagnostic item) {
        dao.delete(item);
    }

    @Override
    public ConsultationDiagnostic getById(int id) {
        return dao.getById(id);
    }

    @Override
    public void updateItem(ConsultationDiagnostic item) {
        dao.updateItem(item);
    }

    @Override
    public List<ConsultationDiagnostic> getAll(String table) {
        return dao.getAll(table);
    }

    @Override
    public List<ConsultationDiagnostic> getListOfItem(String query) {
        return dao.getListOfItem(query);
    }

    @Override
    public List<ConsultationDiagnostic> getSpecificColumnsList(List<String> columns, Criterion restrictions) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<ConsultationDiagnostic> getSpecificColumnsList(List<String> columns, LogicalExpression restrictions) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mergeItem(ConsultationDiagnostic item) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
