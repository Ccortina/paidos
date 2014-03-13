/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.carloscortina.demo.service;

import com.carloscortina.demo.dao.DrugDao;
import com.carloscortina.demo.model.Drug;
import java.util.List;
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
}
