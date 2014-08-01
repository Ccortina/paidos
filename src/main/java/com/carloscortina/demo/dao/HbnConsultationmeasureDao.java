/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.carloscortina.demo.dao;

import com.carloscortina.demo.model.Consultationmeasure;
import com.carloscortina.demo.model.ConsultationmeasurePK;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Carlos Cortina
 */
@Repository
public class HbnConsultationmeasureDao extends GenericHbnDao<Consultationmeasure> implements ConsultationmeasureDao{

    @Override
    public Consultationmeasure getById(ConsultationmeasurePK id) {
        return (Consultationmeasure)getSession().get(Consultationmeasure.class,id);
    }
    
}
