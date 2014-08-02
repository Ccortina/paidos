/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.carloscortina.demo.service;

import com.carloscortina.demo.model.Consultation;
import java.util.List;

/**
 *
 * @author Ccortina_Mac
 */
public interface ConsultationService extends GenericService<Consultation> {
    
    public List<Consultation> getConsultationsByPatient(int idPatient);
    public List<Consultation> getConsultationByCie(int idCie);
}
