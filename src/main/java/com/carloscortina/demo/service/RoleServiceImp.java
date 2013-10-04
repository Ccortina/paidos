package com.carloscortina.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carloscortina.demo.dao.RoleDao;
import com.carloscortina.demo.model.Role;

@Service
public class RoleServiceImp implements RoleService {

	@Autowired
	private RoleDao roleDao;
	
	@Override
	public Role getRole(int id) {
		// TODO Auto-generated method stub
		return ( roleDao.getRole(id) );
	}

	@Override
	public List<Role> getRoles() {
		// TODO Auto-generated method stub
		return ( roleDao.getRoles() );
	}

}
