/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.carloscortina.demo.service;

import com.carloscortina.demo.dao.CommercialNameDao;
import com.carloscortina.demo.model.CommercialName;
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
public class CommercialNameServiceImp implements CommercialNameService{

    @Autowired
    private CommercialNameDao commercialName;
    
    @Override
    public void create(CommercialName item) {
        commercialName.create(item);
    }

    @Override
    public CommercialName getById(int id) {
        return commercialName.getById(id);
    }

    @Override
    public void updateItem(CommercialName item) {
        commercialName.updateItem(item);
    }

    @Override
    public List<CommercialName> getAll(String table) {
        return commercialName.getAll(table);
    }

    @Override
    public List<CommercialName> getListOfItem(String query) {
        return commercialName.getListOfItem(query);
    }
    
}