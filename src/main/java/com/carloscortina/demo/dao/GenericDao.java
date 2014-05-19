package com.carloscortina.demo.dao;

import java.util.List;

public interface GenericDao<T> {
	
	public void create(T item);
        public void delete(T item);
	public void updateItem(T item);
	public T getById(int id);
	public List<T> getAll(String table);
	public List<T> getListOfItem(String query);
}
