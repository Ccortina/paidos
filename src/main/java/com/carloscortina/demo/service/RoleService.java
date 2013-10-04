package com.carloscortina.demo.service;

import java.util.List;

import com.carloscortina.demo.model.Role;

public interface RoleService {

	public Role getRole(int id);
	public List<Role> getRoles();
}
