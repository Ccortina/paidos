/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.carloscortina.demo.service;

import com.carloscortina.demo.model.Patient;
import com.carloscortina.demo.model.Sizeweight;
import java.util.List;

/**
 *
 * @author Carlos Cortina
 */
public interface SizeWeightService extends GenericService<Sizeweight>{
    
    public List<Sizeweight> getAllDataForPatient(Patient patient);
    
}
