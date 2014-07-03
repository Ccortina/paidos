/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.carloscortina.demo.service;

import com.carloscortina.demo.dao.HolydayDao;
import com.carloscortina.demo.model.Holyday;
import java.util.List;
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
public class HolydayServiceImp implements HolydayService{

    @Autowired
    HolydayDao holydayDao;
    
    @Override
    public void create(Holyday item) {
        holydayDao.create(item);
    }

    @Override
    public void delete(Holyday item) {
        holydayDao.delete(item);
    }

    @Override
    public Holyday getById(int id) {
        return holydayDao.getById(id);
    }

    @Override
    public void updateItem(Holyday item) {
        holydayDao.updateItem(item);
    }

    @Override
    public List<Holyday> getAll(String table) {
        return holydayDao.getAll(table);
    }

    @Override
    public List<Holyday> getListOfItem(String query) {
        return holydayDao.getListOfItem(query);
    }

    @Override
    public List<Holyday> getSpecificColumnsList(List<String> columns, Criterion restrictions) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Holyday> getSpecificColumnsList(List<String> columns, LogicalExpression restrictions) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
