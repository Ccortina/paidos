package com.carloscortina.demo.dao;

import org.hibernate.Query;
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
		System.out.println("-----------"+user.getRole().getId());
		getSession().save(user);
	}

	@Override
	public User getUserByUsername(String username) {
		Query q = getSession().getNamedQuery("findUserByUsername");
		q.setParameter("username", username);
		
		return (User) q.uniqueResult();
	}

    @Override
    public User getUserById(int id) {
        return (User)getSession().get(User.class, id);
    }
        
        

}
