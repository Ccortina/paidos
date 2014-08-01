/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.carloscortina.demo.dao;

import com.carloscortina.demo.model.PatientRelative;
import com.carloscortina.demo.model.PatientRelativePK;
import java.util.List;

/**
 *
 * @author Ccortina_Mac
 */
public interface PatientRelativeDao extends GenericDao<PatientRelative>{
    
    public PatientRelative getByPK(PatientRelativePK id);
    public List<PatientRelative> getSibilingsByPatient(int idPatient);
}
