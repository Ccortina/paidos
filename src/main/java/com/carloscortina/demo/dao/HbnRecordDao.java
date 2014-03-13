package com.carloscortina.demo.dao;



import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.carloscortina.demo.model.Patient;
import com.carloscortina.demo.model.Record;

@Repository
public class HbnRecordDao extends GenericHbnDao<Record> implements RecordDao {

	@SuppressWarnings("unchecked")
	@Override
	public Record getByPatientId(Patient patient) {
		// TODO Auto-generated method stub
		Query query = getSession().createQuery("from Record where idPatient = :id");
		query.setParameter("id", patient);
		
		List<Record> list = query.list();
		if (list.size() > 0){
			return list.get(0);
		}
		return null;
	}

	
}
