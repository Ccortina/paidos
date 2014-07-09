package com.carloscortina.demo.dao;
import com.carloscortina.demo.model.User;


public interface UserDao {

	void createUser(User user);
	User getUserByUsername(String username);
        User getUserById(int id);
}
