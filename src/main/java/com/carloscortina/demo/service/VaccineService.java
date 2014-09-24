/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.carloscortina.demo.service;

import com.carloscortina.demo.model.Vaccine;
import java.util.List;

/**
 *
 * @author Ccortina_Mac
 */
public interface VaccineService extends GenericService<Vaccine>{

    public List<Vaccine> getActiveVaccines();
    public List<Vaccine> getAllVaccines();
    public List<Vaccine> getAllActiveVaccines();
    public List<Vaccine> getAvaibleVaccinesForPatient(int idPatient);
}
