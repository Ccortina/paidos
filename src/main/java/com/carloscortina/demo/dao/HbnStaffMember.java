package com.carloscortina.demo.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.carloscortina.demo.model.StaffMember;

@Repository
public class HbnStaffMember implements StaffMemberDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getSession(){
		return sessionFactory.getCurrentSession();
	}
	
	@Override
	public void createStaffMember(StaffMember staff) {
		// TODO Auto-generated method stub
		getSession().save(staff);
	}

}
