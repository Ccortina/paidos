/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.carloscortina.demo.dao;

import com.carloscortina.demo.model.Drug;
import com.carloscortina.demo.model.Drugrisk;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Carlos Cortina
 */
@Repository
public class HbnDrugRiskDao extends GenericHbnDao<Drugrisk> implements DrugRiskDao{

    @Override
    public List<Drugrisk> getDrugRisksByDrug(Drug drug) {
        List<Drugrisk> list = getSession().createQuery("FROM Drugrisk dr WHERE dr.drug.idDrug=:drug").setParameter("drug", drug.getIdDrug()).list();
        
        for(Drugrisk d: list){
            d.getDrug().setIncompatibledrugsList(null);
            d.getDrug1().setIncompatibledrugsList(null);
        }
        return list;
    }
    
}
