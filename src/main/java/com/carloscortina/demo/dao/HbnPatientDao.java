package com.carloscortina.demo.dao;

import com.carloscortina.demo.model.Patient;
import org.springframework.stereotype.Repository;

@Repository
public class HbnPatientDao extends GenericHbnDao<Patient> implements PatientDao {

    @Override
    public Patient mergePatient(Patient patient) {
        return (Patient)getSession().merge(patient);
    }
    
}
