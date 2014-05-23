package com.carloscortina.demo.dao;

import java.util.List;

import com.carloscortina.demo.model.Relative;

public interface RelativeDao {

	public void createRelative(Relative relative);
	public Relative getRelative(int id);
	public List<Relative> getAllRelatives();
	public List<Relative> getRelativesByPatientId(int id);
        public void updateRelative(Relative item);
}
