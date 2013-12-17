package com.carloscortina.demo.service;

import java.util.List;


import com.carloscortina.demo.dao.GenericDao;

public abstract class GenericServiceImp<T> implements GenericService<T> {

	private GenericDao<T> genericDao;
	
	public void create(T item) {
		genericDao.create(item);
	}
	
	public T getById(int id) {
		return genericDao.getById(id);
	}
	
	public List<T> getAll(String table) {
		return genericDao.getAll(table);
	}
	
	public List<T> getListOfItem(String query){
		return genericDao.getListOfItem(query);
	}
}
