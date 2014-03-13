package com.carloscortina.demo.service;

import java.util.List;

import com.carloscortina.demo.model.Religion;

public interface ReligionService {

	public void createReligion(Religion religion);
	public Religion getReligion(int id);
	public List<Religion> getAllReligions();
}
