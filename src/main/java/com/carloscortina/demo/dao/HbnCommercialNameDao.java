/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.carloscortina.demo.dao;

import com.carloscortina.demo.model.Commercialname;
import com.carloscortina.demo.model.Drug;
import com.carloscortina.demo.model.Incompatibledrugs;
import java.util.ArrayList;
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

    @Override
    public List<Commercialname> getCommercialNameByDrug(Drug drug) {

        Query query = getSession().createQuery("SELECT new Commercialname(commercialName.idcommercialName,commercialName.commercialName,"
                                                + "commercialName.drugId) FROM Commercialname as cn "
                                                + "WHERE cn.drugId.idDrug=:drug");
        query.setParameter("drug", drug.getIdDrug());
        
        List<Commercialname> result = query.list();
        for(Commercialname cm: result){
            cm.setIncompatibledrugsList(null);
            cm.getDrugId().setIncompatibledrugsList(null);
        }
        
        return result;
    }
    
    /*
        This metod returns a list of commercials name that dont present
        any risk or incompatibility with the passed drug
    */
    @Override
    public List<Commercialname> getAvaibleCommercialNamesForDrug( Drug drug ){
        /*String hql = "SELECT new Commercialname(commercialName.idcommercialName,commercialName.commercialName,"
                + "commercialName.drugId) FROM Commercialname as commercialName JOIN commercialName.incompatibledrugsList nd "
                + "WHERE nd.incompatibledrugsPK.idDrug!=:drug";*/
        String hql = "FROM Commercialname as commercialName";
        
        //List<Commercialname> list= getSession().createQuery(hql).setParameter("drug", drug.getIdDrug()).list();
        List<Commercialname> list= getSession().createQuery(hql).list();
        List<Commercialname> result= new ArrayList<Commercialname>();
        //check all the commercial names 
        for(Commercialname cm: list){
            //if the commercial name has no related incomptibilities , its avaible
            if(cm.getIncompatibledrugsList() == null){
                cm.setIncompatibledrugsList(null);
                cm.getDrugId().setIncompatibledrugsList(null);
                result.add(cm);
            }else{
                if(cm.getIncompatibledrugsList().isEmpty() ){
                    cm.setIncompatibledrugsList(null);
                    cm.getDrugId().setIncompatibledrugsList(null);
                    result.add(cm);
                }else{
                    boolean exist = false; //variable to keep track if the drug exist int he incompatible lsit
                    for(Incompatibledrugs id: cm.getIncompatibledrugsList()){
                        if(id.getIncompatibledrugsPK().getIdDrug() == drug.getIdDrug()){
                            exist = true;
                        }
                    }
                    if( !exist ){
                        cm.setIncompatibledrugsList(null);
                        cm.getDrugId().setIncompatibledrugsList(null);
                        result.add(cm);
                }
                }
            }
        }
        
        
        return result;
        
    }
    
    @Override
    public List<Commercialname> getIncompatibleCommercialNamesForDrug( Drug drug ){
        String hql = "SELECT new Commercialname(commercialName.idcommercialName,commercialName.commercialName,"
                + "commercialName.drugId) FROM Commercialname as commercialName JOIN commercialName.incompatibledrugsList nd "
                + "WHERE nd.incompatibledrugsPK.idDrug=:drug";
        List<Commercialname> list= getSession().createQuery(hql).setParameter("drug", drug.getIdDrug()).list();
        for(Commercialname cm: list){
            cm.setIncompatibledrugsList(null);
            cm.getDrugId().setIncompatibledrugsList(null);
        }
        
        
        return list;
        
    }
}
