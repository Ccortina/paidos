package com.carloscortina.demo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.carloscortina.demo.model.Religion;

@Repository
public class HbnReligionDao implements ReligionDao {

	@Autowired
	SessionFactory sessionFactory;
	
	Session getSession(){
		return sessionFactory.getCurrentSession();
	}
	
	@Override
	public void createReligion(Religion religion) {
		// TODO Auto-generated method stub
		getSession().save(religion);
	}

	@Override
	public Religion getReligion(int id) {
		// TODO Auto-generated method stub
		return (Religion) getSession().get(Religion.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Religion> getAllReligions() {
		// TODO Auto-generated method stub
		return getSession().createQuery("from Religion").list();
	}

}
