package com.carloscortina.demo.service;


import com.carloscortina.demo.model.Patient;
import java.util.Date;
import java.util.List;

public interface PatientService extends GenericService<Patient>{

    public Patient mergePatient(Patient patient);
    public List<Patient> getAllPatientsByDoctor(int idStaffMember);
    public List<Patient> getAllActivePatientsByDoctor(int idStaffMember);
    public List<Patient> getAllPatients();
    public Patient getPatientBasicData(int idPatient);
    public List<Patient> getPatientByLaboratoryTest(int idLaboratory);
    public List<Patient> getPatientByBirthmethod(int idBirthmethod);
    public List<Patient> getPatientWithoutVaccine(int idVaccine);
    public List<Patient> getPatientsByBirthdayRange(int  month);
}
