package com.carloscortina.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.carloscortina.demo.dao.RecordDao;
import com.carloscortina.demo.model.Patient;
import com.carloscortina.demo.model.Record;

@Service
@Transactional
public class RecordServiceImp implements RecordService{

	@Autowired
	RecordDao recordDao;
	
	@Override
	public void create(Record item) {
		// TODO Auto-generated method stub
		recordDao.create(item);
	}

	@Override
	public Record getById(int id) {
		// TODO Auto-generated method stub
		return recordDao.getById(id);
	}

	@Override
	public List<Record> getAll(String table) {
		// TODO Auto-generated method stub
		return recordDao.getAll(table);
	}

	@Override
	public List<Record> getListOfItem(String query) {
		// TODO Auto-generated method stub
		return recordDao.getListOfItem(query);
	}

	@Override
	public Record getByPatientId(Patient patient) {
		// TODO Auto-generated method stub
		return recordDao.getByPatientId(patient);
	}

	@Override
	public void updateItem(Record item) {
		// TODO Auto-generated method stub
		recordDao.updateItem(item);
	}

	
}
