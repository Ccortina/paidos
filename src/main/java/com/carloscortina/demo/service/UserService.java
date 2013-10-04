package com.carloscortina.demo.service;

import org.springframework.validation.Errors;

import com.carloscortina.demo.model.User;

public interface UserService {

	boolean registerUser(User user,Errors errors);
	
}
