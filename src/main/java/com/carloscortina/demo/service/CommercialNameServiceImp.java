/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.carloscortina.demo.service;

import com.carloscortina.demo.dao.CommercialNameDao;
import com.carloscortina.demo.model.Commercialname;
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
public class CommercialNameServiceImp implements CommercialNameService{

    @Autowired
    private CommercialNameDao commercialName;
    
    @Override
    public void create(Commercialname item) {
        commercialName.create(item);
    }

    @Override
    public Commercialname getById(int id) {
        return commercialName.getById(id);
    }

    @Override
    public void updateItem(Commercialname item) {
        commercialName.updateItem(item);
    }

    @Override
    public List<Commercialname> getAll(String table) {
        return commercialName.getAll(table);
    }

    @Override
    public List<Commercialname> getListOfItem(String query) {
        return commercialName.getListOfItem(query);
    }

    @Override
    public void delete(Commercialname item) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Commercialname> getSpecificColumnsList(List<String> columns, Criterion restrictions) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Commercialname> getSpecificColumnsList(List<String> columns, LogicalExpression restrictions) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Commercialname> getCommercialNameByUser(int id) {
        return commercialName.getCommercialNameByUser(id);
    }

    @Override
    public void mergeItem(Commercialname item) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Commercialname> getAllActiveItems() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Commercialname> getCommercialNameByDrug(Drug drug) {
        return commercialName.getCommercialNameByDrug(drug);
    }

    @Override
    public List<Commercialname> getAvaibleCommercialNamesForDrug(Drug drug) {
        return commercialName.getAvaibleCommercialNamesForDrug(drug);
    }

    @Override
    public List<Commercialname> getIncompatibleCommercialNamesForDrug(Drug drug) {
        return commercialName.getIncompatibleCommercialNamesForDrug(drug);
    }
    
}
