package com.carloscortina.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.carloscortina.demo.dao.PerBackNoPatDao;
import com.carloscortina.demo.model.PerBackNoPat;

@Service
@Transactional
public class PerBackNoPatServiceImp implements PerBackNoPatService {

	@Autowired
	private PerBackNoPatDao perBackNoPatDao;
	
	@Override
	public void create(PerBackNoPat item) {
		// TODO Auto-generated method stub
		perBackNoPatDao.create(item);
	}

	@Override
	public void updateItem(PerBackNoPat item) {
		// TODO Auto-generated method stub
		perBackNoPatDao.updateItem(item);
	}

	@Override
	public PerBackNoPat getById(int id) {
		// TODO Auto-generated method stub
		return perBackNoPatDao.getById(id);
	}

	@Override
	public List<PerBackNoPat> getAll(String table) {
		// TODO Auto-generated method stub
		return perBackNoPatDao.getAll(table);
	}

	@Override
	public List<PerBackNoPat> getListOfItem(String query) {
		// TODO Auto-generated method stub
		return perBackNoPatDao.getListOfItem(query);
	}

}
