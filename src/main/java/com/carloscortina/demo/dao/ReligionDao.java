package com.carloscortina.demo.dao;

import java.util.List;

import com.carloscortina.demo.model.Religion;

public interface ReligionDao {

	public void createReligion(Religion religion);
	public Religion getReligion(int id);
	public List<Religion> getAllReligions();
}
