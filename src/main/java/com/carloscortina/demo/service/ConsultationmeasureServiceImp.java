/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.carloscortina.demo.service;

import com.carloscortina.demo.dao.ConsultationmeasureDao;
import com.carloscortina.demo.model.Consultationmeasure;
import com.carloscortina.demo.model.ConsultationmeasurePK;
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
public class ConsultationmeasureServiceImp implements ConsultationmeasureService{

    @Autowired
    ConsultationmeasureDao cmDao;
    
    @Override
    public void create(Consultationmeasure item) {
        cmDao.create(item);
    }

    @Override
    public void delete(Consultationmeasure item) {
        cmDao.delete(item);
    }

    @Override
    public Consultationmeasure getById(int id) {
        return cmDao.getById(id);
    }

    @Override
    public void updateItem(Consultationmeasure item) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Consultationmeasure> getAll(String table) {
        return cmDao.getAll(table);
    }

    @Override
    public List<Consultationmeasure> getListOfItem(String query) {
        return cmDao.getListOfItem(query);
    }

    @Override
    public List<Consultationmeasure> getSpecificColumnsList(List<String> columns, Criterion restrictions) {
        return cmDao.getSpecificColumnsList(columns, restrictions);
    }

    @Override
    public List<Consultationmeasure> getSpecificColumnsList(List<String> columns, LogicalExpression restrictions) {
        return cmDao.getSpecificColumnsList(columns, restrictions);
    }

    @Override
    public Consultationmeasure getById(ConsultationmeasurePK id) {
        return cmDao.getById(id);
    }

    @Override
    public void mergeItem(Consultationmeasure item) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Consultationmeasure> getConsultationsByMeasure(int idMeasure) {
        return cmDao.getConsultationsByMeasure(idMeasure);
    }
    
}
