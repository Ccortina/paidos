package com.carloscortina.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.carloscortina.demo.dao.RoleDao;
import com.carloscortina.demo.model.Role;

@Service
@Transactional
public class RoleServiceImp implements RoleService {

	@Autowired
	private RoleDao roleDao;
	
	@Override
	public Role getRole(int id) {
		// TODO Auto-generated method stub
		return ( roleDao.getRole(id) );
	}

	@Override
	@Transactional
	public List<Role> getRoles() {
		// TODO Auto-generated method stub
		return ( roleDao.getRoles() );
	}

}
