/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.carloscortina.demo.dao;

import com.carloscortina.demo.model.Consultationmotive;

/**
 *
 * @author Carlos Cortina
 */
public interface ConsultationmotivesDao extends GenericDao<Consultationmotive>{
    
    public Consultationmotive getMotiveByName(String motive);
}
