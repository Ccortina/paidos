package com.carloscortina.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.carloscortina.demo.dao.Cie10Dao;
import com.carloscortina.demo.model.Cie10;
import java.util.Map;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;

@Service
@Transactional
public class Cie10ServiceImp implements Cie10Service{

    @Autowired
    private Cie10Dao cie10Dao;

    @Override
    public void create(Cie10 item) {
            // TODO Auto-generated method stub
            cie10Dao.create(item);
    }

    @Override
    public Cie10 getById(int id) {
            // TODO Auto-generated method stub
            return cie10Dao.getById(id);
    }

    @Override
    public void updateItem(Cie10 item) {
            // TODO Auto-generated method stub
            cie10Dao.updateItem(item);
    }

    @Override
    public List<Cie10> getAll(String table) {
            // TODO Auto-generated method stub
            return cie10Dao.getAll(table);
    }

    @Override
    public List<Cie10> getListOfItem(String query) {
            // TODO Auto-generated method stub
            return cie10Dao.getListOfItem(query);
    }

    @Override
    public void delete(Cie10 item) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Cie10> getSpecificColumnsList(List<String> columns, Criterion restrictions) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Cie10> getSpecificColumnsList(List<String> columns, LogicalExpression restrictions) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
