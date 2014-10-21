/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.carloscortina.demo.service;

import com.carloscortina.demo.dao.ConsultationactivityDao;
import com.carloscortina.demo.model.Consultationactivity;
import com.carloscortina.demo.model.User;
import java.util.Date;
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
public class ConsultationactivityServiceImp implements ConsultationactivityService{
    
    @Autowired
    ConsultationactivityDao caDao;
    
    @Override
    public void create(Consultationactivity item) {
        caDao.create(item);
    }

    @Override
    public void delete(Consultationactivity item) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Consultationactivity getById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateItem(Consultationactivity item) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Consultationactivity> getAll(String table) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Consultationactivity> getListOfItem(String query) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Consultationactivity> getSpecificColumnsList(List<String> columns, Criterion restrictions) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Consultationactivity> getSpecificColumnsList(List<String> columns, LogicalExpression restrictions) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mergeItem(Consultationactivity item) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Consultationactivity> getConsultationsByActivity(int idActivity) {
        return caDao.getConsultationsByActivity(idActivity);
    }

    @Override
    public List<Consultationactivity> getAllActiveItems() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Consultationactivity> getGlobalReport(Date start, Date end, User user) {
        return caDao.getGlobalReport(start, end, user);
    }

    @Override
    public List<Consultationactivity> getIncomeByActivityTotals(Date start, Date end, User user,int type) {
        return caDao.getIncomeByActivityTotals(start, end, user,type);
    }

    @Override
    public List<Consultationactivity> getIncomeByActivityDetails(Date start, Date end, User user, int type) {
        return caDao.getIncomeByActivityDetails(start, end, user, type);
    }
    
}
