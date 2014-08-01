package com.carloscortina.demo.service;

import java.util.List;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;

public interface GenericService<T> {
	
	public void create(T item);
        public void delete(T item);
	public T getById(int id);
	public void updateItem(T item);
        public void mergeItem(T item);
	public List<T> getAll(String table);
	public List<T> getListOfItem(String query);
        public List<T> getSpecificColumnsList(List<String> columns,Criterion restrictions);
        public List<T> getSpecificColumnsList(List<String> columns, LogicalExpression restrictions);

}
