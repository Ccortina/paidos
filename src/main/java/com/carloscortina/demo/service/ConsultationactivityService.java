/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.carloscortina.demo.service;

import com.carloscortina.demo.model.Consultationactivity;
import java.util.List;

/**
 *
 * @author Carlos Cortina
 */
public interface ConsultationactivityService extends GenericService<Consultationactivity>{
 
    public List<Consultationactivity> getConsultationsByActivity(int idActivity);
}
