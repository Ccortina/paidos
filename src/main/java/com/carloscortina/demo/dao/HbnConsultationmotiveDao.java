/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.carloscortina.demo.dao;

import com.carloscortina.demo.model.Consultationmotive;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Carlos Cortina
 */
@Repository
public class HbnConsultationmotiveDao extends GenericHbnDao<Consultationmotive> implements ConsultationmotivesDao{

    @Override
    public Consultationmotive getMotiveByName(String motive){
        Criteria criteria = getSession().createCriteria(Consultationmotive.class);
        criteria.add(Restrictions.eq("motive", motive));
        
        return (Consultationmotive)criteria.uniqueResult();
    }
}
