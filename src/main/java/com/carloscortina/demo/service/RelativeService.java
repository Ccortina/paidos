package com.carloscortina.demo.service;

import java.util.List;

import com.carloscortina.demo.model.Relative;

public interface RelativeService {

	public void createRelative(Relative relative);
	public Relative getRelative(int id);
	public List<Relative> getAllRelatives();
}
