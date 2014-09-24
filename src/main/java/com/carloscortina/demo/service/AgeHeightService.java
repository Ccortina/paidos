/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.carloscortina.demo.service;

import com.carloscortina.demo.model.Agesize24to240;
import com.carloscortina.demo.model.Patient;
import java.util.List;

/**
 *
 * @author Carlos Cortina
 */
public interface AgeHeightService extends GenericService<Agesize24to240>{
    
    public List<Agesize24to240> getAllDataFor24to240Months(Patient patient);
    
}
