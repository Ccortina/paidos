/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.carloscortina.demo.service;

import com.carloscortina.demo.dao.PermissionDao;
import com.carloscortina.demo.model.Permissions;
import java.util.List;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Carlos
 */
@Transactional
@Service
public class PermissionsServiceImp implements PermissionsService{

    @Autowired
    private PermissionDao dao;
    
    @Override
    public void create(Permissions item) {
        dao.create(item);
    }

    @Override
    public void delete(Permissions item) {
        dao.delete(item);
    }

    @Override
    public Permissions getById(int id) {
        return dao.getById(id);
    }

    @Override
    public void updateItem(Permissions item) {
        dao.updateItem(item);
    }

    @Override
    public void mergeItem(Permissions item) {
        dao.mergeItem(item);
    }

    @Override
    public List<Permissions> getAll(String table) {
        return dao.getAll(table);
    }

    @Override
    public List<Permissions> getAllActiveItems() {
        return dao.getAllActiveItems();
    }

    @Override
    public List<Permissions> getListOfItem(String query) {
        return dao.getListOfItem(query);
    }

    @Override
    public List<Permissions> getSpecificColumnsList(List<String> columns, Criterion restrictions) {
        return dao.getSpecificColumnsList(columns, restrictions);
    }

    @Override
    public List<Permissions> getSpecificColumnsList(List<String> columns, LogicalExpression restrictions) {
        return dao.getSpecificColumnsList(columns, restrictions);
    }
    
}
