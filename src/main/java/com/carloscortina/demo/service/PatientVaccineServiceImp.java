/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.carloscortina.demo.service;

import com.carloscortina.demo.dao.PatientVaccineDao;
import com.carloscortina.demo.model.Patientvaccine;
import com.carloscortina.demo.model.PatientvaccinePK;
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
    public void create(Patientvaccine item) {
        pvDao.create(item);
    }

    @Override
    public void delete(Patientvaccine item) {
        pvDao.delete(item);
    }

    @Override
    public Patientvaccine getById(int id) {
        return pvDao.getById(id);
    }

    @Override
    public void updateItem(Patientvaccine item) {
        pvDao.updateItem(item);
    }
    

    @Override
    public List<Patientvaccine> getAll(String table) {
        return pvDao.getAll(table);
    }

    @Override
    public List<Patientvaccine> getListOfItem(String query) {
        return pvDao.getListOfItem(query);
    }

    @Override
    public Patientvaccine getById(PatientvaccinePK id) {
        return pvDao.getById(id);
    }

    @Override
    public List<Patientvaccine> getSpecificColumnsList(List<String> columns, Criterion restrictions) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Patientvaccine> getSpecificColumnsList(List<String> columns, LogicalExpression restrictions) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mergeItem(Patientvaccine item) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Patientvaccine> getAllActiveItems() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Patientvaccine> getPatientVaccineByVaccine(int idVaccine) {
        return pvDao.getPatientVaccineByVaccine(idVaccine);
    }

    @Override
    public List<Patientvaccine> getAllPV() {
        return pvDao.getAllPV();
    }
    
    
    
}
