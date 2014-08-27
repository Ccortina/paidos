package com.carloscortina.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.carloscortina.demo.dao.StaffMemberDao;
import com.carloscortina.demo.model.Staffmember;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;

@Service
@Transactional
public class StaffMemberServiceImp implements StaffMemberService {

    @Autowired
    private StaffMemberDao staffMemberDao;

    @Override
    public void create(Staffmember item) {
        staffMemberDao.create(item);
    }

    @Override
    public void delete(Staffmember item) {
        staffMemberDao.delete(item);
    }

    @Override
    public Staffmember getById(int id) {
        return staffMemberDao.getById(id);
    }

    @Override
    public void updateItem(Staffmember item) {
        staffMemberDao.updateItem(item);
    }

    @Override
    public void mergeItem(Staffmember item) {
        staffMemberDao.mergeItem(item);
    }

    @Override
    public List<Staffmember> getAll(String table) {
        return staffMemberDao.getAll(table);
    }

    @Override
    public List<Staffmember> getListOfItem(String query) {
        return staffMemberDao.getListOfItem(query);
    }

    @Override
    public List<Staffmember> getSpecificColumnsList(List<String> columns, Criterion restrictions) {
        return staffMemberDao.getSpecificColumnsList(columns, restrictions);
    }

    @Override
    public List<Staffmember> getSpecificColumnsList(List<String> columns, LogicalExpression restrictions) {
        return staffMemberDao.getSpecificColumnsList(columns, restrictions);
    }

    @Override
    public List<Staffmember> getAllActiveItems() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
	
}
