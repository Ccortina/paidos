/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.carloscortina.demo.service;

import com.carloscortina.demo.dao.ConsultationmotivesDao;
import com.carloscortina.demo.model.Consultationmotive;
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
public class ConsultationmotiveServiceImp implements ConsultationmotiveService{

    @Autowired
    ConsultationmotivesDao cmDao;
    
    @Override
    public void create(Consultationmotive item) {
        cmDao.create(item);
    }

    @Override
    public void delete(Consultationmotive item) {
        cmDao.delete(item);
    }

    @Override
    public Consultationmotive getById(int id) {
        return cmDao.getById(id);
    }

    @Override
    public void updateItem(Consultationmotive item) {
        cmDao.updateItem(item);
    }

    @Override
    public void mergeItem(Consultationmotive item) {
        cmDao.mergeItem(item);
    }

    @Override
    public List<Consultationmotive> getAll(String table) {
        return cmDao.getAll(table);
    }

    @Override
    public List<Consultationmotive> getListOfItem(String query) {
        return cmDao.getListOfItem(query);
    }

    @Override
    public List<Consultationmotive> getSpecificColumnsList(List<String> columns, Criterion restrictions) {
        return cmDao.getSpecificColumnsList(columns, restrictions);
    }

    @Override
    public List<Consultationmotive> getSpecificColumnsList(List<String> columns, LogicalExpression restrictions) {
        return cmDao.getSpecificColumnsList(columns, restrictions);
    }

    @Override
    public Consultationmotive getMotiveByName(String motive) {
        return cmDao.getMotiveByName(motive);
    }
    
}
