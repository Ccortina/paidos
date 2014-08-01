package com.carloscortina.demo.service;


import com.carloscortina.demo.model.Patient;
import java.util.List;

public interface PatientService extends GenericService<Patient>{

    public Patient mergePatient(Patient patient);
    public List<Patient> getAllPatientsByDoctor(int idStaffMember);
    public List<Patient> getAllPatients();
    public Patient getPatientBasicData(int idPatient);
}
