package com.carloscortina.demo.service;

import java.util.List;

import com.carloscortina.demo.model.PerBackNoPat;

public interface PerBackNoPatService {

	public void create(PerBackNoPat item);
	public void updateItem(PerBackNoPat item);
	public PerBackNoPat getById(int id);
	public List<PerBackNoPat> getAll(String table);
	public List<PerBackNoPat> getListOfItem(String query);
}
