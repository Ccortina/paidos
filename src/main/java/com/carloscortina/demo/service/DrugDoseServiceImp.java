/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.carloscortina.demo.service;

import com.carloscortina.demo.dao.DrugDoseDao;
import com.carloscortina.demo.model.CommercialName;
import com.carloscortina.demo.model.DrugDose;
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
public class DrugDoseServiceImp implements DrugDoseService{
    
    @Autowired 
    private DrugDoseDao drugDose;

    @Override
    public void create(DrugDose item) {
        drugDose.create(item);
    }

    @Override
    public DrugDose getById(int id) {
        return drugDose.getById(id);
    }

    @Override
    public void updateItem(DrugDose item) {
        drugDose.updateItem(item);
    }

    @Override
    public List<DrugDose> getAll(String table) {
        return drugDose.getAll(table);
    }

    @Override
    public List<DrugDose> getListOfItem(String query) {
        return drugDose.getListOfItem(query);
    }
    
}