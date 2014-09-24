/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.carloscortina.demo.dao;

import com.carloscortina.demo.model.Ageweight0to240;
import com.carloscortina.demo.model.Patient;
import java.util.List;

/**
 *
 * @author Carlos Cortina
 */
public interface AgeWeightDao extends GenericDao<Ageweight0to240>{
    
    public List<Ageweight0to240> getAllDataFor0to36Months(Patient patient);
    public List<Ageweight0to240> getAllDataFor24to240Months(Patient patient);
    
}
