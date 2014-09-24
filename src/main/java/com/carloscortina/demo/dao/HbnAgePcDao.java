/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.carloscortina.demo.dao;

import com.carloscortina.demo.model.Agepc;
import com.carloscortina.demo.model.Patient;
import java.util.List;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Carlos Cortina
 */
@Repository
public class HbnAgePcDao extends GenericHbnDao<Agepc> implements AgePcDao{

    @Override
    public List<Agepc> getAllDataFor0to36Months(Patient patient){
    
        Query query = getSession().createQuery("FROM Agepc a "
                + "WHERE a.gender.idGender=:gender");
        query.setParameter("gender", patient.getSex().getIdGender());
        
        return ( query.list() );
    }
}
