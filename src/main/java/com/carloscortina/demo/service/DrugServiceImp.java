/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.carloscortina.demo.service;

import com.carloscortina.demo.dao.DrugDao;
import com.carloscortina.demo.model.Drug;
import java.util.List;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Ccortina
 */
@Service
@Transactional
public class DrugServiceImp implements DrugService
{
    
    @Autowired
    private DrugDao drugDao;

    @Override
    public void create(Drug item) {
        drugDao.create(item);
    }

    @Override
    public Drug getById(int id) {
        return drugDao.getById(id);
    }

    @Override
    public void updateItem(Drug item) {
        drugDao.updateItem(item);
    }

    @Override
    public List<Drug> getAll(String table) {
        return drugDao.getAll(table);
    }

    @Override
    public List<Drug> getListOfItem(String query) {
        return drugDao.getListOfItem(query);
    }

    @Override
    public void delete(Drug item) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Drug> getSpecificColumnsList(List<String> columns, Criterion restrictions) {
        return drugDao.getSpecificColumnsList(columns, restrictions);
    }

    @Override
    public List<Drug> getSpecificColumnsList(List<String> columns, LogicalExpression restrictions) {
        return drugDao.getSpecificColumnsList(columns, restrictions);
    }
    
    @Override
    public List<Drug> getDrugByUser(int id){
        return drugDao.getDrugByUser(id);
    }

    @Override
    public List<Drug> getDrugByTreatment(int treatmentId) {
        return drugDao.getDrugByTreatment(treatmentId);
    }

    @Override
    public void mergeItem(Drug item) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Drug> getDrugByPresentationAndUser(int presentationId, int userId) {
        return drugDao.getDrugByPresentationAndUser(presentationId, userId);
    }

    @Override
    public List<Drug> getDrugByApplicationMethodAndUser(int applicationId, int userId) {
        return drugDao.getDrugByApplicationMethodAndUser(applicationId, userId);
    }

    @Override
    public List<Drug> getDrugByAdministrationUnitAndUser(int unitId, int userId) {
        return drugDao.getDrugByAdministrationUnitAndUser(unitId, userId);
    }

    @Override
    public List<Drug> getAllActiveItems() {
        return drugDao.getAllActiveItems();
    }

    @Override
    public List<Drug> getAllActiveDrugBasicInfo() {
        return drugDao.getAllActiveDrugBasicInfo();
    }

}
