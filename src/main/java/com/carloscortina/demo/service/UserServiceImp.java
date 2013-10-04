package com.carloscortina.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;

import com.carloscortina.demo.dao.UserDao;
import com.carloscortina.demo.model.User;

@Service
@Transactional(readOnly=true)
public class UserServiceImp implements UserService {
	@Autowired 
	private UserDao userDao;
	
	@Transactional(readOnly=false)
	@Override
	public boolean registerUser(User user, Errors errors) {
		// TODO Auto-generated method stub
		boolean valid = !errors.hasErrors();
		if (valid) 
		{
			userDao.createUser(user);
		}
		
		return valid;
	}

}
