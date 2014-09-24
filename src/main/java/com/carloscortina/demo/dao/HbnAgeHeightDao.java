/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.carloscortina.demo.dao;

import com.carloscortina.demo.model.Agesize24to240;
import com.carloscortina.demo.model.Patient;
import java.util.List;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Carlos Cortina
 */
@Repository
public class HbnAgeHeightDao extends GenericHbnDao<Agesize24to240> implements AgeHeightDao{

    @Override
    public List<Agesize24to240> getAllDataFor24to240Months(Patient patient){
    
        Query query = getSession().createQuery("FROM Agesize24to240 a "
                + "WHERE a.gender.idGender=:gender");
        query.setParameter("gender", patient.getSex().getIdGender());
        
        return ( query.list() );
    }
}
