package com.carloscortina.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.carloscortina.demo.dao.TreatmentDao;
import com.carloscortina.demo.model.Treatment;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;

@Service
@Transactional
public class TreatmentServiceImp implements TreatmentService{

    @Autowired
    private TreatmentDao treatmentDao;

    @Override
    public void create(Treatment item) {
            // TODO Auto-generated method stub
            treatmentDao.create(item);
    }

    @Override
    public Treatment getById(int id) {
            // TODO Auto-generated method stub
            return treatmentDao.getById(id);
    }

    @Override
    public void updateItem(Treatment item) {
            // TODO Auto-generated method stub
            treatmentDao.updateItem(item);
    }

    @Override
    public List<Treatment> getAll(String table) {
            // TODO Auto-generated method stub
            return treatmentDao.getAll(table);
    }

    @Override
    public List<Treatment> getListOfItem(String query) {
            // TODO Auto-generated method stub
            return treatmentDao.getListOfItem(query);
    }

    @Override
    public void delete(Treatment item) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Treatment> getSpecificColumnsList(List<String> columns, Criterion restrictions) {
        return treatmentDao.getSpecificColumnsList(columns, restrictions);
    }

    @Override
    public List<Treatment> getSpecificColumnsList(List<String> columns, LogicalExpression restrictions) {
        return treatmentDao.getSpecificColumnsList(columns, restrictions);
    }

    @Override
    public List<Treatment> getTreatmentByCIE10AndUser(int diagnoticId, int userId) {
        return treatmentDao.getTreatmentByCIE10AndUser(diagnoticId, userId);
    }

    @Override
    public List<Treatment> getTreatmentByUser(int userId) {
        return treatmentDao.getTreatmentByUser(userId);
    }
	
    
}
