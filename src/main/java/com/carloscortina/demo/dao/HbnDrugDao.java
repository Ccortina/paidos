/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.carloscortina.demo.dao;

import com.carloscortina.demo.model.Drug;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Ccortina
 */
@Repository
public class HbnDrugDao extends GenericHbnDao<Drug> implements DrugDao{

    @Override
    public List<Drug> getDrugByUser(int id){
        Criteria criteria = getSession().createCriteria(Drug.class);
            
        ProjectionList prop = Projections.projectionList();
        prop.add(Projections.property("idDrug"),"idDrug");
        prop.add(Projections.property("drug"),"drug");
        prop.add(Projections.property("concentration"),"concentration");
        prop.add(Projections.property("treatmentDays"),"treatmentDays");
        prop.add(Projections.property("applicationSchedule"),"applicationSchedule");
        prop.add(Projections.property("dailyFrequency"),"dailyFrequency");
        prop.add(Projections.property("notes"),"notes");
        prop.add(Projections.property("active"),"active");
        prop.add(Projections.property("drugPresentationId"),"drugPresentationId");
        prop.add(Projections.property("doseCalculationCriteriaId"),"doseCalculationCriteriaId");
        prop.add(Projections.property("applicationMethodId"),"applicationMethodId");
        prop.add(Projections.property("administrationUnitId"),"administrationUnitId");
        
        criteria.createAlias("userList", "ul");
        criteria.add(Restrictions.eq("ul.idUser", id));
        
        criteria.setProjection(prop).setResultTransformer(Transformers.aliasToBean(Drug.class));

        List<Drug> result = criteria.list();
        return result;
    }
    
    @Override
    public List<Drug> getDrugByTreatmentAndUser(int treatmentId,int userId){
        Criteria criteria = getSession().createCriteria(Drug.class);
            
        ProjectionList prop = Projections.projectionList();
        prop.add(Projections.property("idDrug"),"idDrug");
        prop.add(Projections.property("drug"),"drug");
        prop.add(Projections.property("concentration"),"concentration");
        prop.add(Projections.property("treatmentDays"),"treatmentDays");
        prop.add(Projections.property("applicationSchedule"),"applicationSchedule");
        prop.add(Projections.property("dailyFrequency"),"dailyFrequency");
        prop.add(Projections.property("notes"),"notes");
        prop.add(Projections.property("active"),"active");
        prop.add(Projections.property("drugPresentationId"),"drugPresentationId");
        prop.add(Projections.property("doseCalculationCriteriaId"),"doseCalculationCriteriaId");
        prop.add(Projections.property("applicationMethodId"),"applicationMethodId");
        prop.add(Projections.property("administrationUnitId"),"administrationUnitId");
            
        
        criteria.createAlias("userList", "ul");
        Criterion user = Restrictions.eq("ul.idUser", userId);
        criteria.createAlias("treatmentList", "tl");
        Criterion treatment = Restrictions.eq("tl.idTreatment", treatmentId);
        
        LogicalExpression andRestriction = Restrictions.and(user, treatment);
        criteria.add(andRestriction);
        
        criteria.setProjection(prop).setResultTransformer(Transformers.aliasToBean(Drug.class));

        List<Drug> result = criteria.list();
        return result;
    }
}
