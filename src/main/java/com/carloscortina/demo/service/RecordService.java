package com.carloscortina.demo.service;

import java.util.List;

import com.carloscortina.demo.model.Patient;
import com.carloscortina.demo.model.Record;

public interface RecordService {

	public void create(Record item);
	public void updateItem(Record item);
	public Record getById(int id);
	public List<Record> getAll(String table);
	public List<Record> getListOfItem(String query);
	public Record getByPatientId(Patient patient);
	
}
