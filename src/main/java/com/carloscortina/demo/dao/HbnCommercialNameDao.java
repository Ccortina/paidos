/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.carloscortina.demo.dao;

import com.carloscortina.demo.model.Commercialname;
import com.carloscortina.demo.model.Drug;
import java.util.List;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Carlos Cortina
 */
@Repository
public class HbnCommercialNameDao extends GenericHbnDao<Commercialname> implements CommercialNameDao{

    @Override
    public List<Commercialname> getCommercialNameByUser(int id){
        String hql = "SELECT new Commercialname(commercialName.idcommercialName,commercialName.commercialName,"
                + "commercialName.drugId) FROM Commercialname as commercialName";
        Query query = getSession().createQuery(hql);
        
        List<Commercialname> result = query.list();
        
        for(Commercialname temp: result){
            String hql1 = "SELECT new Drug(drug.idDrug) FROM Drug as drug WHERE drug.idDrug = :idDrug ";
            List<Drug> drugList = getSession().createQuery(hql1).setParameter("idDrug", temp.getDrugId().getIdDrug()).list();
            if(drugList != null){
                temp.setDrugId(drugList.get(0));
            }
        }
        
        return result;
        
    }
}
