/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.carloscortina.demo.dao;

import com.carloscortina.demo.model.Patientvaccine;
import com.carloscortina.demo.model.PatientvaccinePK;
import java.util.List;

/**
 *
 * @author Ccortina_Mac
 */
public interface PatientVaccineDao extends GenericDao<Patientvaccine>{

    public Patientvaccine getById(PatientvaccinePK id);
    public List<Patientvaccine> getPatientVaccineByVaccine(int idVaccine);
    public List<Patientvaccine> getAllPV();
}
