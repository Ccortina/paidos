/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.carloscortina.demo.service;

import com.carloscortina.demo.dao.PatientRelativeDao;
import com.carloscortina.demo.model.Patient_Relative;
import java.util.List;
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
    public void create(Patient_Relative item) {
        patientRelativeDao.create(item);
    }

    @Override
    public Patient_Relative getById(int id) {
        return patientRelativeDao.getById(id);
    }

    @Override
    public void updateItem(Patient_Relative item) {
        patientRelativeDao.updateItem(item);
    }

    @Override
    public List<Patient_Relative> getAll(String table) {
        return patientRelativeDao.getAll(table);
    }

    @Override
    public List<Patient_Relative> getListOfItem(String query) {
        return patientRelativeDao.getListOfItem(query);
    }

    @Override
    public void delete(Patient_Relative item) {
        patientRelativeDao.delete(item);
    }
    
}
