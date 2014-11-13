/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.carloscortina.demo.service;

import com.carloscortina.demo.dao.ConsultationCostAbstracDao;
import com.carloscortina.demo.model.Consultationcostabstract;
import com.carloscortina.demo.model.User;
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
public class ConsultationCostAbstractServiceImp implements ConsultationCostAbstractService{
    
    @Autowired
    private ConsultationCostAbstracDao dao;

    @Override
    public void create(Consultationcostabstract item) {
        dao.create(item);
    }

    @Override
    public void delete(Consultationcostabstract item) {
        dao.delete(item);
    }

    @Override
    public Consultationcostabstract getById(int id) {
        return dao.getById(id);
    }

    @Override
    public void updateItem(Consultationcostabstract item) {
        dao.updateItem(item);
    }

    @Override
    public void mergeItem(Consultationcostabstract item) {
        dao.mergeItem(item);
    }

    @Override
    public List<Consultationcostabstract> getAll(String table) {
        return dao.getAll(table);
    }

    @Override
    public List<Consultationcostabstract> getAllActiveItems() {
        return dao.getAllActiveItems();
    }

    @Override
    public List<Consultationcostabstract> getListOfItem(String query) {
        return dao.getListOfItem(query);
    }

    @Override
    public List<Consultationcostabstract> getSpecificColumnsList(List<String> columns, Criterion restrictions) {
        return dao.getSpecificColumnsList(columns, restrictions);
    }

    @Override
    public List<Consultationcostabstract> getSpecificColumnsList(List<String> columns, LogicalExpression restrictions) {
        return dao.getSpecificColumnsList(columns, restrictions);
    }

    @Override
    public List<Consultationcostabstract> getConsultationCostAbstractSmall(User current) {
        return dao.getConsultationCostAbstractSmall(current);
    }

    @Override
    public List<Consultationcostabstract> getALLCCASmall(User current) {
        return dao.getALLCCASmall(current);
    }
    
}
