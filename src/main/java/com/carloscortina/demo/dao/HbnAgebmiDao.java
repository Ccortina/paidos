/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.carloscortina.demo.dao;

import com.carloscortina.demo.model.Agebmi;
import com.carloscortina.demo.model.Patient;
import java.util.List;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Carlos Cortina
 */
@Repository
public class HbnAgebmiDao extends GenericHbnDao<Agebmi> implements AgebmiDao{
    
    @Override
    public List<Agebmi> getAllDataForPatient(Patient patient){
    
        Query query = getSession().createQuery("FROM Agebmi a "
                + "WHERE a.gender.idGender=:gender");
        query.setParameter("gender", patient.getSex().getIdGender());
        
        return ( query.list() );
    }
    
}
