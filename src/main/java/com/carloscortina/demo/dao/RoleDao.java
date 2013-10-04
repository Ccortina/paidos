package com.carloscortina.demo.dao;

import java.util.List;

import com.carloscortina.demo.model.Role;

public interface RoleDao {

	void createRole(Role role);
	Role getRole(int id);
	List<Role> getRoles();
	
}
