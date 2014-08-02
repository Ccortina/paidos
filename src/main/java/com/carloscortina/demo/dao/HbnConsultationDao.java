/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.carloscortina.demo.dao;

import com.carloscortina.demo.model.Consultation;
import java.util.ArrayList;
import java.util.List;
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
    public List<Consultation> getConsultationsByPatient(int idPatient){
        Query query = getSession().createQuery("SELECT new Consultation(c.idConsultation,c.weigth,c.size,c.temperature,c.pc,"
                + "c.ta,c.ta2,c.taAverage,c.peea,c.ef,c.prescription,c.idAppointment) FROM Consultation c "
                + "WHERE c.idPatient.idPatient=:idPatient");
        query.setParameter("idPatient", idPatient);
        List<Consultation> consultations = query.list();
        for(Consultation c:consultations){
            Query query2 = getSession().createQuery("SELECT new Consultationactivity(c.cost,c.activity,c.includeInBill) FROM Consultationactivity c"
                + " WHERE c.consultation.idConsultation=:idConsultation");
            query2.setParameter("idConsultation", c.getIdConsultation());
            c.setConsultationactivityList(query2.list());
        }
        return consultations;
    }
    
    @Override
    public List<Consultation> getConsultationByCie(int idCie){
        Query query = getSession().createQuery("SELECT new Consultation(c.idAppointment) FROM Consultation c JOIN c.consultationdiagnosticList d"
                + " WHERE d.idDiagnostic.idCIE10.idCIE10=:idCie");
        query.setParameter("idCie", idCie);
        return query.list();
    }
}
