/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.carloscortina.demo.dao;

import com.carloscortina.demo.model.Patientvaccine;
import com.carloscortina.demo.model.PatientvaccinePK;
import com.carloscortina.demo.model.Vaccine;
import java.util.List;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Ccortina_Mac
 */
@Repository
public class HbnPatientVaccineDao extends GenericHbnDao<Patientvaccine> implements PatientVaccineDao{

    @Override
    public Patientvaccine getById(PatientvaccinePK id) {
        return (Patientvaccine) getSession().get(Patientvaccine.class,id);
    }
    
    @Override
    public List<Patientvaccine> getAllPV(){
        Query query = getSession().createQuery("SELECT new Patientvaccine(p.vaccine, p.patient) FROM Patientvaccine p");
        
        List<Patientvaccine> pv = query.list();
        for(Patientvaccine p: pv){
            p.getVaccine().setVaccineList(null);
        }
        return pv;
    }
    
    @Override
    public List<Patientvaccine> getPatientVaccineByVaccine(int idVaccine){
        Query query = getSession().createQuery("SELECT new Patientvaccine(p.vaccine, p.patient) FROM Patientvaccine p "
                + "WHERE p.vaccine.idVaccine=:idVaccine");
        query.setParameter("idVaccine", idVaccine);
        
        List<Patientvaccine> pv = query.list();
        for(Patientvaccine p: pv){
            Vaccine v = new Vaccine(p.getVaccine().getIdVaccine(),p.getVaccine().getVaccine());
            p.setVaccine(v);
        }
        return pv;
    }
    
    @Override
    public List<Patientvaccine> getPatientVaccineByPatient(int idPatient){
        Query query = getSession().createQuery("SELECT new Patientvaccine(p.patientvaccinePK,p.programedDate,p.vaccine,"
                + " p.patient,p.suspended,p.suspensionDate,p.notes,p.name,p.batch,p.expirationDate) FROM Patientvaccine p "
                + "WHERE p.patient.idPatient=:idPatient");
        query.setParameter("idPatient", idPatient);
        
        List<Patientvaccine> pv = query.list();
        for(Patientvaccine p: pv){
            Vaccine v = new Vaccine(p.getVaccine().getIdVaccine(),p.getVaccine().getVaccine(),p.getVaccine().getYearApply(),
                    p.getVaccine().getMonthApply(),p.getVaccine().getDayApply(),p.getVaccine().getMultipleShots(),p.getVaccine().getActive(),
                    p.getVaccine().getIdVaccineType());
            p.setVaccine(v);
        }
        return pv;
    }
    
    @Override
    public List<Patientvaccine> getPatientVaccineSystemProgrammedByPatient(int idPatient){
        Query query = getSession().createQuery("SELECT new Patientvaccine(p.patientvaccinePK,p.programedDate,p.vaccine,"
                + " p.patient,p.suspended,p.suspensionDate,p.notes,p.name,p.batch,p.expirationDate) FROM Patientvaccine p "
                + "WHERE p.patient.idPatient=:idPatient AND p.programManual=0");
        query.setParameter("idPatient", idPatient);
        
        List<Patientvaccine> pv = query.list();
        for(Patientvaccine p: pv){
            Vaccine v = new Vaccine(p.getVaccine().getIdVaccine(),p.getVaccine().getVaccine(),p.getVaccine().getYearApply(),
                    p.getVaccine().getMonthApply(),p.getVaccine().getDayApply(),p.getVaccine().getMultipleShots(),p.getVaccine().getActive(),
                    p.getVaccine().getIdVaccineType());
            p.setVaccine(v);
        }
        return pv;
    }
}
