/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.carloscortina.demo.service;

import com.carloscortina.demo.model.Agebmi;
import com.carloscortina.demo.model.Patient;
import java.util.List;

/**
 *
 * @author Carlos Cortina
 */
public interface AgebmiService extends GenericService<Agebmi>{
    
    public List<Agebmi> getAllDataForPatient(Patient patient);
}
