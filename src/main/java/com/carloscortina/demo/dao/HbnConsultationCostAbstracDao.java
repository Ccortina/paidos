/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.carloscortina.demo.dao;

import com.carloscortina.demo.model.Consultationactivity;
import com.carloscortina.demo.model.Consultationcostabstract;
import java.util.List;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Carlos Cortina
 */
@Repository
public class HbnConsultationCostAbstracDao extends GenericHbnDao<Consultationcostabstract> implements ConsultationCostAbstracDao{

    @Override
    public List<Consultationcostabstract> getConsultationCostAbstractSmall(){
        Query query = getSession().createQuery("FROM Consultationcostabstract");
        List<Consultationcostabstract> ccaList = query.list();
        
        for(Consultationcostabstract cca: ccaList){
                cca.setConsultationDate(cca.getIdConsultation().getIdAppointment().getDate());
                cca.setConsultationPatient(cca.getIdConsultation().getIdAppointment().getIdPatient());
                
                for(Consultationactivity ca: cca.getIdConsultation().getConsultationactivityList()){
                    ca.getActivity().setIdVaccine(null);
                    ca.setConsultation(null);
                }
                cca.setActivities(cca.getIdConsultation().getConsultationactivityList());
        }
        
        return ccaList;
    }
}
