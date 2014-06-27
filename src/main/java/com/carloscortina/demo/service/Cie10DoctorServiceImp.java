/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.carloscortina.demo.service;

import com.carloscortina.demo.dao.Cie10DoctorDao;
import com.carloscortina.demo.model.CIE10Doctor;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Ccortina_Mac
 */
@Service
@Transactional
public class Cie10DoctorServiceImp implements Cie10DoctorService{

    @Autowired
    Cie10DoctorDao cdDao;
    
    @Override
    public void create(CIE10Doctor item) {
        cdDao.create(item);
    }

    @Override
    public void delete(CIE10Doctor item) {
        cdDao.delete(item);
    }

    @Override
    public CIE10Doctor getById(int id) {
        return cdDao.getById(id);
    }

    @Override
    public void updateItem(CIE10Doctor item) {
        cdDao.updateItem(item);
    }

    @Override
    public List<CIE10Doctor> getAll(String table) {
        return cdDao.getAll(table);
    }

    @Override
    public List<CIE10Doctor> getListOfItem(String query) {
        return cdDao.getListOfItem(query);
    }

    @Override
    public List<CIE10Doctor> getSpecificColumnsList(List<String> columns) {
        return cdDao.getSpecificColumnsList(columns);
    }
    
}
