/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.carloscortina.demo.service;

import com.carloscortina.demo.dao.PatientVaccineDao;
import com.carloscortina.demo.model.PatientVaccine;
import com.carloscortina.demo.model.PatientVaccinePK;
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
@Transactional
@Service
public class PatientVaccineServiceImp implements PatientVaccineService{

    @Autowired
    PatientVaccineDao pvDao;
    
    @Override
    public void create(PatientVaccine item) {
        pvDao.create(item);
    }

    @Override
    public void delete(PatientVaccine item) {
        pvDao.delete(item);
    }

    @Override
    public PatientVaccine getById(int id) {
        return pvDao.getById(id);
    }

    @Override
    public void updateItem(PatientVaccine item) {
        pvDao.updateItem(item);
    }
    

    @Override
    public List<PatientVaccine> getAll(String table) {
        return pvDao.getAll(table);
    }

    @Override
    public List<PatientVaccine> getListOfItem(String query) {
        return pvDao.getListOfItem(query);
    }

    @Override
    public PatientVaccine getById(PatientVaccinePK id) {
        return pvDao.getById(id);
    }

    @Override
    public List<PatientVaccine> getSpecificColumnsList(List<String> columns, Criterion restrictions) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<PatientVaccine> getSpecificColumnsList(List<String> columns, LogicalExpression restrictions) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mergeItem(PatientVaccine item) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
