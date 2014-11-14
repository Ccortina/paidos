/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.carloscortina.demo.service;

import com.carloscortina.demo.model.Patientvaccine;
import com.carloscortina.demo.model.PatientvaccinePK;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Ccortina_Mac
 */
public interface PatientVaccineService extends GenericService<Patientvaccine>{
    
    public Patientvaccine getById(PatientvaccinePK id);
    public List<Patientvaccine> getPatientVaccineByVaccine(int idVaccine);
    public List<Patientvaccine> getPatientVaccineByPatient(int idPatient);
    public List<Patientvaccine> getPatientVaccineSystemProgrammedByPatient(int idPatient);
    public List<Patientvaccine> getAllPV();
    public List<Patientvaccine> getAllPVByFilter(Map<String, String> params);
}
