/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.carloscortina.demo.dao;

import com.carloscortina.demo.model.Drug;
import com.carloscortina.demo.model.Drugrisk;
import java.util.List;

/**
 *
 * @author Carlos Cortina
 */
public interface DrugRiskDao extends GenericDao<Drugrisk>{
    
    public List<Drugrisk> getDrugRisksByDrug(Drug drug);
    
}
