/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.carloscortina.demo.dao;

import com.carloscortina.demo.model.PatientRelativeId;
import com.carloscortina.demo.model.Patient_Relative;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Ccortina_Mac
 */
@Repository
public class HbnPatientRelative extends GenericHbnDao<Patient_Relative> implements PatientRelativeDao{

}
