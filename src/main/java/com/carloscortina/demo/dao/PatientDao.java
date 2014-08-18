package com.carloscortina.demo.dao;


import com.carloscortina.demo.model.Patient;
import java.util.List;

public interface PatientDao extends GenericDao<Patient>{
    
    public Patient mergePatient(Patient patient);
    public List<Patient> getAllPatients();
    public List<Patient> getAllPatientsByDoctor(int idStaffMember);
    public Patient getPatientBasicData(int idPatient);
    public List<Patient> getPatientByLaboratoryTest(int idLaboratory);
    public List<Patient> getPatientByBirthmethod(int idBirthmethod);
}
