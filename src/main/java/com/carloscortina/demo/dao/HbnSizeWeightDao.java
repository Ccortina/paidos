/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.carloscortina.demo.dao;

import com.carloscortina.demo.model.Patient;
import com.carloscortina.demo.model.Sizeweight;
import java.util.List;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Carlos Cortina
 */
@Repository
public class HbnSizeWeightDao extends GenericHbnDao<Sizeweight> implements SizeWeightDao{

    @Override
    public List<Sizeweight> getAllDataForPatient(Patient patient){
    
        Query query = getSession().createQuery("FROM Sizeweight a "
                + "WHERE a.gender.idGender=:gender");
        query.setParameter("gender", patient.getSex().getIdGender());
        
        return ( query.list() );
    }
}
