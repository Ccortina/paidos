package com.carloscortina.demo.dao;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class GenericHbnDao<T> implements GenericDao<T> {

	@Autowired
	private SessionFactory sessionFactory;
	
	private Class<T> type;
	
	protected Session getSession()
	{
		return sessionFactory.getCurrentSession();
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public GenericHbnDao() {
	        Type t = getClass().getGenericSuperclass();
	        ParameterizedType pt = (ParameterizedType) t;
	        type = (Class) pt.getActualTypeArguments()[0];
	}
	
	@Override
	public void create(T item) {
		// TODO Auto-generated method stub
		getSession().save(item);
	}

	public void updateItem(T item){
		// Update an object
		getSession().update(item);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public T getById(int id) {
		// TODO Auto-generated method stub
		return (T) getSession().get(type, id);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<T> getAll(String table) {
		// TODO Auto-generated method stub
		List<T> list = (List<T>) getSession().createQuery("from "+ table ).list();
		return (list);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<T> getListOfItem(String query) {
		return ( getSession().createQuery(query).list());
	}

}
