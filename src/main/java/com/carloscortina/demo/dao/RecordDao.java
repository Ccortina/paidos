package com.carloscortina.demo.dao;

import com.carloscortina.demo.model.Patient;
import com.carloscortina.demo.model.Record;

public interface RecordDao extends GenericDao<Record> {

	public Record getByPatientId(Patient patient);
	
}
