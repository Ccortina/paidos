package com.carloscortina.demo.service;

import java.util.List;

public interface GenericService<T> {
	
	public void create(T item);
        public void delete(T item);
	public T getById(int id);
	public void updateItem(T item);
	public List<T> getAll(String table);
	public List<T> getListOfItem(String query);

}
