package com.carloscortina.demo.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.carloscortina.demo.model.User;

@Repository
public class HbnUserDao implements UserDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getSession(){
		return sessionFactory.getCurrentSession();
	}
	
	@Override
	public void createUser(User user) {
		// TODO Auto-generated method stub
		getSession().save(user);
	}

}
