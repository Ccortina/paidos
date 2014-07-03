package com.carloscortina.demo.dao;

import org.springframework.stereotype.Repository;

import com.carloscortina.demo.model.Treatment;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;

@Repository
public class HbnTreatmentDao extends GenericHbnDao<Treatment> implements TreatmentDao {

    @Override
    public List<Treatment> getTreatmentByCIE10AndUser(int diagnoticId,int userId){
        Criteria criteria = getSession().createCriteria(Treatment.class);
            
        ProjectionList prop = Projections.projectionList();
 
        prop.add(Projections.property("idTreatment"),"idTreatment");
        prop.add(Projections.property("treatment"),"treatment");
        prop.add(Projections.property("directions"),"directions");
        prop.add(Projections.property("active"),"active");
        
        criteria.createAlias("userList", "ul");
        criteria.createAlias("cie10List", "c10");
        Criterion user = Restrictions.eq("ul.idUser", userId) ;
        Criterion cie10 = Restrictions.eq("c10.idCIE10", diagnoticId);
        
        LogicalExpression andRestriction = Restrictions.and(user,cie10);
        
        criteria.add(andRestriction);
        criteria.setProjection(prop).setResultTransformer(Transformers.aliasToBean(Treatment.class));

        List<Treatment> result = criteria.list();
        return result;
    }
    
    @Override
    public List<Treatment> getTreatmentByUser(int userId){
        Criteria criteria = getSession().createCriteria(Treatment.class);
            
        ProjectionList prop = Projections.projectionList();
 
        prop.add(Projections.property("idTreatment"),"idTreatment");
        prop.add(Projections.property("treatment"),"treatment");
        prop.add(Projections.property("directions"),"directions");
        prop.add(Projections.property("active"),"active");
        
        criteria.createAlias("userList", "ul");
        Criterion user = Restrictions.eq("ul.idUser", userId) ;
        
        criteria.setProjection(prop).setResultTransformer(Transformers.aliasToBean(Treatment.class));

        List<Treatment> result = criteria.list();
        return result;
    }
}
