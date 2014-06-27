/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.carloscortina.demo.dao;

import com.carloscortina.demo.model.PatientVaccine;
import com.carloscortina.demo.model.PatientVaccinePK;

/**
 *
 * @author Ccortina_Mac
 */
public interface PatientVaccineDao extends GenericDao<PatientVaccine>{

    public PatientVaccine getById(PatientVaccinePK id);
}
