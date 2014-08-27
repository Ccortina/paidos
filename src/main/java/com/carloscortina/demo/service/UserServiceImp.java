package com.carloscortina.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.carloscortina.demo.dao.UserDao;
import com.carloscortina.demo.model.User;
import java.util.List;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;

@Service
@Transactional(readOnly=true)
public class UserServiceImp implements UserService {
    @Autowired 
    private UserDao dao;

    @Override
    public void create(User item) {
        dao.create(item);
    }

    @Override
    public void delete(User item) {
        dao.delete(item);
    }

    @Override
    public User getById(int id) {
        return dao.getById(id);
    }

    @Override
    public void updateItem(User item) {
        dao.updateItem(item);
    }

    @Override
    public void mergeItem(User item) {
        dao.mergeItem(item);
    }

    @Override
    public List<User> getAll(String table) {
        return dao.getAll(table);
    }

    @Override
    public List<User> getListOfItem(String query) {
        return dao.getListOfItem(query);
    }

    @Override
    public List<User> getSpecificColumnsList(List<String> columns, Criterion restrictions) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<User> getSpecificColumnsList(List<String> columns, LogicalExpression restrictions) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public User getUserByUsername(String username) {
        return dao.getUserByUsername(username);
    }

    @Override
    public List<User> getUserByRole(int idRole) {
        return dao.getUserByRole(idRole);
    }

    @Override
    public List<User> getAllActiveItems() {
        return dao.getAllActiveItems();
    }

}
