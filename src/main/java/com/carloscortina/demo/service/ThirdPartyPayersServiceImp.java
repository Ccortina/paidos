/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.carloscortina.demo.service;

import com.carloscortina.demo.dao.ThirdPartyPayersDao;
import com.carloscortina.demo.model.Thirdpartypayer;
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
public class ThirdPartyPayersServiceImp implements ThirdPartyPayersService{

    @Autowired
    private ThirdPartyPayersDao dao;
    
    @Override
    public void create(Thirdpartypayer item) {
        dao.create(item);
    }

    @Override
    public void delete(Thirdpartypayer item) {
        dao.delete(item);
    }

    @Override
    public Thirdpartypayer getById(int id) {
        return dao.getById(id);
    }

    @Override
    public void updateItem(Thirdpartypayer item) {
        dao.updateItem(item);
    }

    @Override
    public void mergeItem(Thirdpartypayer item) {
        dao.mergeItem(item);
    }

    @Override
    public List<Thirdpartypayer> getAll(String table) {
        return dao.getAll(table);
    }

    @Override
    public List<Thirdpartypayer> getAllActiveItems() {
        return dao.getAllActiveItems();
    }

    @Override
    public List<Thirdpartypayer> getListOfItem(String query) {
        return dao.getListOfItem(query);
    }

    @Override
    public List<Thirdpartypayer> getSpecificColumnsList(List<String> columns, Criterion restrictions) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Thirdpartypayer> getSpecificColumnsList(List<String> columns, LogicalExpression restrictions) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
