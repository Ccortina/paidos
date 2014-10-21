/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.carloscortina.demo.dao;

import com.carloscortina.demo.model.Consultation;
import com.carloscortina.demo.model.Consultationactivity;
import com.carloscortina.demo.model.Patient;
import com.carloscortina.demo.model.User;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Ccortina_Mac
 */
@Repository
public class HbnConsultationDao extends  GenericHbnDao<Consultation> implements ConsultationDao{

    @Override
    public void create(Consultation item) {
        // TODO Auto-generated method stub
        getSession().save(item);
    }
    
    @Override
    public Consultation getConsultationActivitiesById(int id){
        Query query = getSession().createQuery("SELECT new Consultation(c.idConsultation,"
                + "c.idPatient,c.idDoctor,c.consultationactivityList) FROM Consultation c "
                + "WHERE c.idConsultation=:idConsultation");
        query.setParameter("idConsultation", id);
        Consultation consultation = (Consultation) query.uniqueResult();
        
        
        Query query2 = getSession().createQuery("SELECT new Consultationactivity(c.cost,c.activity,c.includeInBill) FROM Consultationactivity c"
            + " WHERE c.consultation.idConsultation=:idConsultation");
        query2.setParameter("idConsultation", id);

        List<Consultationactivity> temp = query2.list();
        for(Consultationactivity ca: temp){
            ca.getActivity().setIdVaccine(null);
        }
        consultation.setConsultationactivityList(temp);
        
        return consultation;
    }
    
    @Override
    public List<Consultation> getConsultationsByPatient(int idPatient){
        Query query = getSession().createQuery("SELECT new Consultation(c.idConsultation,c.weigth,c.size,c.bmi,c.temperature,c.pc,"
                + "c.ta,c.ta2,c.taAverage,c.peea,c.ef,c.prescription,c.idAppointment,c.abstract1,c.type,c.prescriptionNumber) FROM Consultation c "
                + "WHERE c.idPatient.idPatient=:idPatient");
        query.setParameter("idPatient", idPatient);
        List<Consultation> consultations = query.list();
        
        for(Consultation c:consultations){
            Query query2 = getSession().createQuery("SELECT new Consultationactivity(c.cost,c.activity,c.includeInBill) FROM Consultationactivity c"
                + " WHERE c.consultation.idConsultation=:idConsultation");
            query2.setParameter("idConsultation", c.getIdConsultation());
            List<Consultationactivity> temp = query2.list();
            for(Consultationactivity ca: temp){
                ca.getActivity().setIdVaccine(null);
            }
            c.setConsultationactivityList(temp);
        }
        return consultations;
    }
    
    @Override
    public List<Consultation> getConsultationByCie(int idCie){
        Query query = getSession().createQuery("SELECT new Consultation(c.idAppointment) FROM Consultation c JOIN c.diagnosticList d"
                + " WHERE d.idCIE10.idCIE10=:idCie");
        query.setParameter("idCie", idCie);
        return query.list();
    }
    
    @Override
    public List<Consultation> getConsultationByTreatment(int idTreatment){
        Query query = getSession().createQuery("SELECT new Consultation(c.idAppointment) FROM Consultation c JOIN c.diagnosticList d"
                + " WHERE d.idTreatment.idTreatment=:idTreatment");
        query.setParameter("idTreatment", idTreatment);
        return query.list();
    }
    
    @Override
    public List<Consultation> getConsultationByActivity(int idActivity){
        Query query = getSession().createQuery("FROM Consultation c JOIN c.consultationactivityList d"
                + " WHERE d.activity.idActivity=:idActivity");
        query.setParameter("idActivity", idActivity);
        return query.list();
    }

    @Override
    public List<Consultation> getConsultationMeasureHistory(int idPatient) {
        Query query = getSession().createQuery("SELECT new Consultation(c.weigth, c.size, c.bmi, c.temperature, c.pc, c.idPatient, c.idAppointment) FROM Consultation c"
                + " WHERE c.idPatient.idPatient=:idPatient");
        query.setParameter("idPatient", idPatient);
        return query.list();
    }
    
    @Override
    public Map<Patient,Long> getPatientsConsultationNumber(){
        Query query = getSession().createQuery("SELECT c.idPatient,COUNT(*) as consults "
                + "FROM Consultation c GROUP BY c.idPatient ORDER BY consults DESC");
        
        List<Object[]> rows = query.list();
        
        Map<Patient, Long> result = new HashMap<Patient, Long>(rows.size());
        for (Object[] row : rows) {
            Patient patient = (Patient) row[0];
            Long number = (Long) row[1];
            result.put(patient, number);
        }
        
        return result;
    }
    
    @Override
    public Map<Date,Long> getConsultsOfMonthPerDay(int month,int year){
        
        Calendar queryMonth = Calendar.getInstance();
        queryMonth.set(year, month, 1);
        
        Calendar firstDay=Calendar.getInstance();
        Calendar lastDay=Calendar.getInstance();
        firstDay.set(year, month, queryMonth.getActualMinimum(Calendar.DAY_OF_MONTH));
        lastDay.set(year, month, queryMonth.getActualMaximum(Calendar.DAY_OF_MONTH));

        Query query = getSession().createQuery("SELECT c.idAppointment.date,COUNT(*) as consults "
                + "FROM Consultation c WHERE c.idAppointment.date >= :monthStart AND c.idAppointment.date <= :monthEnd "
                + "GROUP BY c.idAppointment.date");
        
        query.setParameter("monthStart", firstDay.getTime());
        query.setParameter("monthEnd", lastDay.getTime());
        
        List<Object[]> rows = query.list();
        
        Map<Date, Long> result = new HashMap<Date, Long>(lastDay.get(Calendar.DAY_OF_MONTH));
        for (Object[] row : rows) {
            Date date = (Date) row[0];
            Long number = (Long) row[1];
            result.put(date, number);
        }
        System.out.println(result);
        return result;
    }
    
    @Override
    public Map<Integer,Long> getConsultsOfMonthByYear(int year){

        Calendar firstDay=Calendar.getInstance();
        Calendar lastDay=Calendar.getInstance();
        Calendar cal = Calendar.getInstance();
        
        firstDay.set(year, 0, 31);  // 01 Jan Year
        lastDay.set(year, 11, 31); // 31 Dec Year

        Query query = getSession().createQuery("SELECT c.idAppointment.date,COUNT(*) as consults "
                + "FROM Consultation c WHERE c.idAppointment.date >= :dayStart AND c.idAppointment.date <= :dayEnd "
                + "GROUP BY c.idAppointment.date");
        
        query.setParameter("dayStart", firstDay.getTime());
        query.setParameter("dayEnd", lastDay.getTime());
        
        List<Object[]> rows = query.list();
        
        Map<Integer, Long> result = new HashMap<Integer, Long>(lastDay.get(Calendar.DAY_OF_MONTH));
        for (Object[] row : rows) {
            Date date = (Date) row[0];
            cal.setTime(date);
            Long number = (Long) row[1];
            result.put(cal.get(Calendar.MONTH)+1, number);
        }
        
        return result;
    }

}
