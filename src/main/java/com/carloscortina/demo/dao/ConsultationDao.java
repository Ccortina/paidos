/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.carloscortina.demo.dao;

import com.carloscortina.demo.model.Consultation;
import com.carloscortina.demo.model.Patient;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Ccortina_Mac
 */
public interface ConsultationDao extends GenericDao<Consultation>{
    
    public Consultation getConsultationActivitiesById(int id);
    public List<Consultation> getConsultationsByPatient(int idPatient);
    public List<Consultation> getConsultationByCie(int idCie);
    public List<Consultation> getConsultationByTreatment(int idTreatment);
    public List<Consultation> getConsultationByActivity(int idActivity);
    public List<Consultation> getConsultationMeasureHistory(int idPatient);
    public Map<Patient,Long> getPatientsConsultationNumber();
    public Map<Date,Long> getConsultsOfMonthPerDay(int month,int year);
    public Map<Integer,Long> getConsultsOfMonthByYear(int year);
}
