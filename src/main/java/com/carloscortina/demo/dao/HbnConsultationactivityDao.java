/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.carloscortina.demo.dao;

import com.carloscortina.demo.model.Consultationactivity;
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
}
