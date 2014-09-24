/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.carloscortina.demo.dao;

import com.carloscortina.demo.model.Ageweight0to240;
import com.carloscortina.demo.model.Patient;
import java.util.List;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Carlos Cortina
 */
@Repository
public class HbnAgeWeightDao extends GenericHbnDao<Ageweight0to240> implements AgeWeightDao{

    @Override
    public List<Ageweight0to240> getAllDataFor0to36Months(Patient patient){
    
        Query query = getSession().createQuery("FROM Ageweight0to240 a "
                + "WHERE a.gender.idGender=:gender AND "
                + "months >= 0 AND months <= 36 ORDER BY months");
        query.setParameter("gender", patient.getSex().getIdGender());
        
        return ( query.list() );
    }
    
    @Override
    public List<Ageweight0to240> getAllDataFor24to240Months(Patient patient){
    
        Query query = getSession().createQuery("FROM Ageweight0to240 a "
                + "WHERE a.gender.idGender=:gender AND "
                + "months >= 24 ORDER BY months");
        query.setParameter("gender", patient.getSex().getIdGender());
        
        return ( query.list() );
    }
}
