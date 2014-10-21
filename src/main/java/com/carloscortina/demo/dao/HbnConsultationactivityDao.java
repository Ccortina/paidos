/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.carloscortina.demo.dao;

import com.carloscortina.demo.model.Consultationactivity;
import com.carloscortina.demo.model.User;
import java.util.Date;
import java.util.List;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Carlos Cortina
 */
@Repository
public class HbnConsultationactivityDao extends GenericHbnDao<Consultationactivity> implements ConsultationactivityDao{

    @Override
    public List<Consultationactivity> getConsultationsByActivity(int idActivity){
        Query query = getSession().createQuery("FROM Consultationactivity c"
                + " WHERE c.activity.idActivity=:idActivity");
        query.setParameter("idActivity", idActivity);

        return query.list();
    }
    
        
    @Override
    public List<Consultationactivity> getGlobalReport(Date start, Date end,User user){
        Query query = getSession().createQuery("FROM Consultationactivity a WHERE a.consultation.idAppointment.date >= :start AND a.consultation.idAppointment.date <= :end "
                + " AND consultation.idAppointment.idDoctor.idUser=:idUser ");
        query.setParameter("start", start);
        query.setParameter("end", end);
        query.setParameter("idUser", user.getIdUser());
        
        return query.list();
    }

    @Override
    public List<Consultationactivity> getIncomeByActivityTotals(Date start, Date end, User user,int type) {
        Query query = getSession().createQuery("SELECT new Consultationactivity(a.activity,SUM(a.cost),COUNT(*)) FROM "
                + "Consultationactivity a "
                + "INNER JOIN a.consultation.consultationcostabstractList liq "
                + "INNER JOIN a.consultation.consultationactivityList act "
                + "WHERE a.consultation.idAppointment.date >= :start AND a.consultation.idAppointment.date <= :end "
                + "AND a.consultation.idDoctor.idUser=:idUser "
                + "AND liq.idConsultationPaymentStatus.idConsultationPaymentEstatus = 3 "
                + "AND act.activity.idActivityType.idActivityType = :type "
                + "GROUP BY a.activity.idActivity");
        query.setParameter("start", start);
        query.setParameter("end", end);
        query.setParameter("idUser", user.getIdUser());
        query.setParameter("type", type);
        
        return query.list();
    }
    
    @Override
    public List<Consultationactivity> getIncomeByActivityDetails(Date start, Date end, User user,int type) {
        Query query = getSession().createQuery("SELECT new Consultationactivity(a.cost,a.consultation,a.activity) "
                + "FROM Consultationactivity a "
                + "INNER JOIN a.consultation.consultationcostabstractList liq "
                + "INNER JOIN a.consultation.consultationactivityList act "
                + "WHERE a.consultation.idAppointment.date >= :start AND a.consultation.idAppointment.date <= :end "
                + "AND a.consultation.idDoctor.idUser=:idUser "
                + "AND liq.idConsultationPaymentStatus.idConsultationPaymentEstatus = 3 "
                + "AND act.activity.idActivityType.idActivityType = :type ");
        query.setParameter("start", start);
        query.setParameter("end", end);
        query.setParameter("idUser", user.getIdUser());
        query.setParameter("type", type);
        
        return query.list();
    }
}
