/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.carloscortina.demo.service;

import com.carloscortina.demo.model.Consultationmeasure;
import com.carloscortina.demo.model.ConsultationmeasurePK;
import java.util.List;

/**
 *
 * @author Carlos Cortina
 */
public interface ConsultationmeasureService extends GenericService<Consultationmeasure>{
   
    public Consultationmeasure getById(ConsultationmeasurePK id);
    public List<Consultationmeasure> getConsultationsByMeasure(int idMeasure);
}
