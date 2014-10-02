/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.carloscortina.demo.service;

import com.carloscortina.demo.dao.ConsultationDao;
import com.carloscortina.demo.model.Consultation;
import com.carloscortina.demo.model.Patient;
import java.util.Date;
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
public class ConsultationServiceImp implements ConsultationService{

    @Autowired
    ConsultationDao consultationDao;
    
    @Override
    public void create(Consultation item) {
        consultationDao.create(item);
    }

    @Override
    public void delete(Consultation item) {
        consultationDao.delete(item);
    }

    @Override
    public Consultation getById(int id) {
        return consultationDao.getById(id);
    }

    @Override
    public void updateItem(Consultation item) {
        consultationDao.updateItem(item);
    }

    @Override
    public List<Consultation> getAll(String table) {
        return consultationDao.getAll(table);
    }

    @Override
    public List<Consultation> getListOfItem(String query) {
        return consultationDao.getListOfItem(query);
    }

    @Override
    public List<Consultation> getSpecificColumnsList(List<String> columns, Criterion restrictions) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Consultation> getSpecificColumnsList(List<String> columns, LogicalExpression restrictions) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mergeItem(Consultation item) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Consultation> getConsultationsByPatient(int idPatient) {
        return consultationDao.getConsultationsByPatient(idPatient);
    }

    @Override
    public List<Consultation> getConsultationByCie(int idCie) {
        return consultationDao.getConsultationByCie(idCie);
    }

    @Override
    public List<Consultation> getConsultationByTreatment(int idTreatment) {
        return consultationDao.getConsultationByTreatment(idTreatment);
    }

    @Override
    public List<Consultation> getAllActiveItems() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Consultation> getConsultationByActivity(int idActivity) {
        return consultationDao.getConsultationByActivity(idActivity);
    }

    @Override
    public List<Consultation> getConsultationMeasureHistory(int idPatient) {
        return consultationDao.getConsultationMeasureHistory(idPatient);
    }

    @Override
    public Map<Patient, Long> getPatientsConsultationNumber() {
        return consultationDao.getPatientsConsultationNumber();
    }

    @Override
    public Map<Date, Long> getConsultsOfMonthPerDay(int month, int year) {
        return consultationDao.getConsultsOfMonthPerDay(month,year);
    }

    @Override
    public Map<Integer, Long> getConsultsOfMonthByYear(int year) {
        return consultationDao.getConsultsOfMonthByYear(year);
    }

    @Override
    public Consultation getConsultationActivitiesById(int id) {
        return consultationDao.getConsultationActivitiesById(id);
    }
    
}
