/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.carloscortina.demo.dao;

import com.carloscortina.demo.model.Consultationcostabstract;
import com.carloscortina.demo.model.User;
import java.util.List;

/**
 *
 * @author Carlos Cortina
 */
public interface ConsultationCostAbstracDao extends GenericDao<Consultationcostabstract>{
    
    public List<Consultationcostabstract> getConsultationCostAbstractSmall(User current);
    public List<Consultationcostabstract> getALLCCASmall(User current);
}
