/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.carloscortina.demo.service;

import com.carloscortina.demo.dao.RelationshipDao;
import com.carloscortina.demo.model.Relationship;
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
public class RelationshipServiceImp implements RelationshipService{

    @Autowired
    RelationshipDao relationshipDao;
    
    @Override
    public void create(Relationship item) {
        relationshipDao.create(item);
    }

    @Override
    public Relationship getById(int id) {
        return relationshipDao.getById(id);
    }

    @Override
    public void updateItem(Relationship item) {
        relationshipDao.updateItem(item);
    }

    @Override
    public List<Relationship> getAll(String table) {
       return relationshipDao.getAll(table);
    }

    @Override
    public List<Relationship> getListOfItem(String query) {
        return relationshipDao.getListOfItem(query);
    }

    @Override
    public void delete(Relationship item) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Relationship> getSpecificColumnsList(List<String> columns, Criterion restrictions) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Relationship> getSpecificColumnsList(List<String> columns, LogicalExpression restrictions) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mergeItem(Relationship item) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
