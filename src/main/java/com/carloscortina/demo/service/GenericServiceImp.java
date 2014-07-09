package com.carloscortina.demo.service;

import java.util.List;


import com.carloscortina.demo.dao.GenericDao;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;

public abstract class GenericServiceImp<T> implements GenericService<T> {

	private GenericDao<T> genericDao;
	
        @Override
	public void create(T item) {
		genericDao.create(item);
	}
	
        @Override
	public T getById(int id) {
		return genericDao.getById(id);
	}
	
        @Override
	public List<T> getAll(String table) {
		return genericDao.getAll(table);
	}
	
        @Override
	public List<T> getListOfItem(String query){
		return genericDao.getListOfItem(query);
	}
        
        @Override
        public List<T> getSpecificColumnsList(List<String> columns,Criterion restrictions){
            return genericDao.getSpecificColumnsList(columns,restrictions);
        }
        
        @Override
        public List<T> getSpecificColumnsList(List<String> columns, LogicalExpression restriction){
            return genericDao.getSpecificColumnsList(columns,restriction);
        }
}
