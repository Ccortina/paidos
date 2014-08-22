/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.carloscortina.demo.service;

import com.carloscortina.demo.dao.DocumentCategoryDao;
import com.carloscortina.demo.model.Documentcategory;
import java.util.List;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Carlos Cortina
 */
@Service
@Transactional
public class DocumentcategoryServiceImp implements DocumentcategoryService{

    @Autowired
    private DocumentCategoryDao dao;
    
    @Override
    public void create(Documentcategory item) {
        dao.create(item);
    }

    @Override
    public void delete(Documentcategory item) {
        dao.delete(item);
    }

    @Override
    public Documentcategory getById(int id) {
        return dao.getById(id);
    }

    @Override
    public void updateItem(Documentcategory item) {
        dao.updateItem(item);
    }

    @Override
    public void mergeItem(Documentcategory item) {
        dao.mergeItem(item);
    }

    @Override
    public List<Documentcategory> getAll(String table) {
        return dao.getAll(table);
    }

    @Override
    public List<Documentcategory> getListOfItem(String query) {
        return dao.getListOfItem(query);
    }

    @Override
    public List<Documentcategory> getSpecificColumnsList(List<String> columns, Criterion restrictions) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Documentcategory> getSpecificColumnsList(List<String> columns, LogicalExpression restrictions) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
