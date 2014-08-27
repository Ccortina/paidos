/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.carloscortina.demo.service;

import com.carloscortina.demo.dao.PatientRelativeDao;
import com.carloscortina.demo.model.PatientRelative;
import com.carloscortina.demo.model.PatientRelativePK;
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
public class PatientRelativeServiceImp implements PatientRelativeService{

    @Autowired
    PatientRelativeDao patientRelativeDao;
    
    @Override
    public void create(PatientRelative item) {
        patientRelativeDao.create(item);
    }

    @Override
    public PatientRelative getById(int id) {
        return patientRelativeDao.getById(id);
    }

    @Override
    public void updateItem(PatientRelative item) {
        patientRelativeDao.updateItem(item);
    }

    @Override
    public List<PatientRelative> getAll(String table) {
        return patientRelativeDao.getAll(table);
    }

    @Override
    public List<PatientRelative> getListOfItem(String query) {
        return patientRelativeDao.getListOfItem(query);
    }

    @Override
    public void delete(PatientRelative item) {
        patientRelativeDao.delete(item);
    }

    @Override
    public List<PatientRelative> getSpecificColumnsList(List<String> columns, Criterion restrictions) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<PatientRelative> getSpecificColumnsList(List<String> columns, LogicalExpression restrictions) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mergeItem(PatientRelative item) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PatientRelative getByPK(PatientRelativePK id) {
        return patientRelativeDao.getByPK(id);
    }

    @Override
    public List<PatientRelative> getSibilingsByPatient(int idPatient) {
        return patientRelativeDao.getSibilingsByPatient(idPatient);
    }

    @Override
    public List<PatientRelative> getAllActiveItems() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
