package com.carloscortina.demo.dao;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import org.hibernate.Criteria;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class GenericHbnDao<T> implements GenericDao<T> {

	@Autowired
	private SessionFactory sessionFactory;
	
	private final Class<T> type;
	
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

        @Override
        public void delete(T item) {
            getSession().delete(item);
        }
        
        @Override
	public void updateItem(T item){
		// Update an object
		getSession().update(item);
	}
        
        @Override
        public void mergeItem(T item){
            //Update (merge) Item
            getSession().merge(item);
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
        
        @Override
        public List<T> getSpecificColumnsList(List<String> columns,Criterion restrictions){
            
            Criteria criteria = getSession().createCriteria(type);
            
            ProjectionList prop = Projections.projectionList();
            for(String property: columns){
                prop.add(Projections.property(property),property);
            }
            criteria.add(restrictions);
            criteria.setProjection(prop).setResultTransformer(Transformers.aliasToBean(type));
            
            List<T> result = criteria.list();
            return result;
        }
        
        @Override
        public List<T> getSpecificColumnsList(List<String> columns,LogicalExpression restriction){
            
            Criteria criteria = getSession().createCriteria(type);
            
            ProjectionList prop = Projections.projectionList();
            for(String property: columns){
                prop.add(Projections.property(property),property);
            }
            criteria.add(restriction);
            criteria.setProjection(prop).setResultTransformer(Transformers.aliasToBean(type));
            
            List<T> result = criteria.list();
            return result;
        }

}
