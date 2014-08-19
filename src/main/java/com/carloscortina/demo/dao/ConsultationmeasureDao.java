/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.carloscortina.demo.dao;

import com.carloscortina.demo.model.Consultationmeasure;
import com.carloscortina.demo.model.ConsultationmeasurePK;
import java.util.List;

/**
 *
 * @author Carlos Cortina
 */
public interface ConsultationmeasureDao extends GenericDao<Consultationmeasure>{

    public Consultationmeasure getById(ConsultationmeasurePK id);
    public List<Consultationmeasure> getConsultationsByMeasure(int idMeasure);
}
