/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.carloscortina.demo.dao;

import com.carloscortina.demo.model.Heightweight;
import com.carloscortina.demo.model.Patient;
import java.util.List;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Carlos Cortina
 */
@Repository
public class HbnHeightWeightDao extends GenericHbnDao<Heightweight> implements HeightWeightDao{
    
    @Override
    public List<Heightweight> getAllDataForPAtient(Patient patient){
    
        Query query = getSession().createQuery("FROM Heightweight a "
                + "WHERE a.gender.idGender=:gender");
        query.setParameter("gender", patient.getSex().getIdGender());
        
        return ( query.list() );
    }
}
