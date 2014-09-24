/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.carloscortina.demo.dao;

import com.carloscortina.demo.model.Agesize0to36;
import com.carloscortina.demo.model.Patient;
import java.util.List;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Carlos Cortina
 */
@Repository
public class Hbnage4sizeDao extends GenericHbnDao<Agesize0to36> implements age4sizeDao{

    @Override
    public List<Agesize0to36> getAllDataFor0to36Months(Patient patient){
    
        Query query = getSession().createQuery("FROM Agesize0to36 a "
                + "WHERE a.gender.idGender=:gender");
        query.setParameter("gender", patient.getSex().getIdGender());
        
        return ( query.list() );
    }
}
