/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.carloscortina.demo.dao;

import com.carloscortina.demo.model.PatientVaccine;
import com.carloscortina.demo.model.PatientVaccinePK;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Ccortina_Mac
 */
@Repository
public class HbnPatientVaccineDao extends GenericHbnDao<PatientVaccine> implements PatientVaccineDao{

    @Override
    public PatientVaccine getById(PatientVaccinePK id) {
        return (PatientVaccine) getSession().get(PatientVaccine.class,id);
    }  
}
