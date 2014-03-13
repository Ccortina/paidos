package com.carloscortina.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.carloscortina.demo.dao.ReligionDao;
import com.carloscortina.demo.model.Religion;

@Service
@Transactional
public class ReligionServiceImp implements ReligionService {

	@Autowired
	ReligionDao religionDao;
	
	@Override
	public void createReligion(Religion religion) {
		// TODO Auto-generated method stub
		religionDao.createReligion(religion);
	}

	@Override
	public Religion getReligion(int id) {
		// TODO Auto-generated method stub
		return religionDao.getReligion(id);
	}

	@Override
	public List<Religion> getAllReligions() {
		// TODO Auto-generated method stub
		return religionDao.getAllReligions();
	}

}
