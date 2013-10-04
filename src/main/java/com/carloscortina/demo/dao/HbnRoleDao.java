package com.carloscortina.demo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.carloscortina.demo.model.Role;

public class HbnRoleDao implements RoleDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getSession()
	{
		return sessionFactory.getCurrentSession();
	}
	
	public void createRole(Role role) {
		// TODO Auto-generated method stub
		
	}

	public Role getRole(int id) {
		// TODO Auto-generated method stub
		Role role = (Role) getSession().get(Role.class, id);
		return role;
	}

	@SuppressWarnings("unchecked")
	public List<Role> getRoles() {
		// TODO Auto-generated method stub
		return ( getSession().createQuery("from Role").list());
	}

}
