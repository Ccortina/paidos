/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.carloscortina.demo.service;

import com.carloscortina.demo.dao.MeasureConsultationDao;
import com.carloscortina.demo.model.MeasureConsultation;
import java.util.List;
import java.util.Map;
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
public class MeasureConsultationServiceImp implements MeasureConsultationService{

    @Autowired
    MeasureConsultationDao dao;
    
    @Override
    public void create(MeasureConsultation item) {
        dao.create(item);
    }

    @Override
    public void delete(MeasureConsultation item) {
        dao.delete(item);
    }

    @Override
    public MeasureConsultation getById(int id) {
        return dao.getById(id);
    }

    @Override
    public void updateItem(MeasureConsultation item) {
        dao.updateItem(item);
    }

    @Override
    public List<MeasureConsultation> getAll(String table) {
        return dao.getAll(table);
    }

    @Override
    public List<MeasureConsultation> getListOfItem(String query) {
        return dao.getListOfItem(query);
    }

    @Override
    public List<MeasureConsultation> getSpecificColumnsList(List<String> columns,Criterion restrictions) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public List<MeasureConsultation> getSpecificColumnsList(List<String> columns, LogicalExpression restrictions) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
