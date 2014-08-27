/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.carloscortina.demo.dao;

import com.carloscortina.demo.model.Vaccine;
import java.util.List;

/**
 *
 * @author Ccortina_Mac
 */
public interface VaccineDao extends GenericDao<Vaccine> {
    
    public List<Vaccine> getActiveVaccines();
    public List<Vaccine> getAllVaccines();
    public List<Vaccine> getAllActiveVaccines();
}
