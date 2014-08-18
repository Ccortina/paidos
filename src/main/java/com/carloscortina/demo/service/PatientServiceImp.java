package com.carloscortina.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.carloscortina.demo.dao.PatientDao;
import com.carloscortina.demo.model.Patient;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;

@Service
@Transactional
public class PatientServiceImp implements PatientService {

    @Autowired
    PatientDao patientDao;

    @Override
    public void create(Patient item) {
        patientDao.create(item);
    }

    @Override
    public Patient getById(int id) {
        return patientDao.getById(id);
    }

    @Override
    public void updateItem(Patient item) {
        patientDao.updateItem(item);
    }

    @Override
    public List<Patient> getAll(String table) {
        return patientDao.getAll(table);
    }

    @Override
    public List<Patient> getListOfItem(String query) {
        return patientDao.getListOfItem(query);
    }

    @Override
    public void delete(Patient item) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Patient mergePatient(Patient patient) {
        return patientDao.mergePatient(patient);
    }

    @Override
    public List<Patient> getSpecificColumnsList(List<String> columns, Criterion restrictions) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Patient> getSpecificColumnsList(List<String> columns, LogicalExpression restrictions) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mergeItem(Patient item) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Patient> getAllPatientsByDoctor(int idStaffMember) {
        return patientDao.getAllPatientsByDoctor(idStaffMember);
    }

    @Override
    public List<Patient> getAllPatients() {
        return patientDao.getAllPatients();
    }

    @Override
    public Patient getPatientBasicData(int idPatient) {
        return patientDao.getPatientBasicData(idPatient);
    }

    @Override
    public List<Patient> getPatientByLaboratoryTest(int idLaboratory) {
        return patientDao.getPatientByLaboratoryTest(idLaboratory);
    }

    @Override
    public List<Patient> getPatientByBirthmethod(int idBirthmethod) {
        return patientDao.getPatientByBirthmethod(idBirthmethod);
    }
	
	
}
