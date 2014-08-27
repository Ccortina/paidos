package com.carloscortina.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.carloscortina.demo.dao.PerBackNoPatDao;
import com.carloscortina.demo.model.Perbacknopat;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;

@Service
@Transactional
public class PerBackNoPatServiceImp implements PerBackNoPatService {

    @Autowired
    private PerBackNoPatDao dao;
    
    @Override
    public void create(Perbacknopat item) {
        dao.create(item);
    }

    @Override
    public void delete(Perbacknopat item) {
        dao.delete(item);
    }

    @Override
    public Perbacknopat getById(int id) {
        return dao.getById(id);
    }

    @Override
    public void updateItem(Perbacknopat item) {
        dao.updateItem(item);
    }

    @Override
    public void mergeItem(Perbacknopat item) {
        dao.mergeItem(item);
    }

    @Override
    public List<Perbacknopat> getAll(String table) {
        return dao.getAll(table);
    }

    @Override
    public List<Perbacknopat> getListOfItem(String query) {
        return dao.getListOfItem(query);
    }

    @Override
    public List<Perbacknopat> getSpecificColumnsList(List<String> columns, Criterion restrictions) {
        return dao.getSpecificColumnsList(columns, restrictions);
    }

    @Override
    public List<Perbacknopat> getSpecificColumnsList(List<String> columns, LogicalExpression restrictions) {
        return dao.getSpecificColumnsList(columns, restrictions);
    }

    @Override
    public List<Perbacknopat> getAllActiveItems() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
