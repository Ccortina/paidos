/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.carloscortina.demo.service;

import com.carloscortina.demo.model.Agepc;
import com.carloscortina.demo.model.Patient;
import java.util.List;

/**
 *
 * @author Carlos Cortina
 */
public interface AgePcService extends GenericService<Agepc>{
    
    public List<Agepc> getAllDataFor0to36Months(Patient patient);
}
