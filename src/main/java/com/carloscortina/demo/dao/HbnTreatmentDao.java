package com.carloscortina.demo.dao;

import com.carloscortina.demo.model.Cie10;
import org.springframework.stereotype.Repository;

import com.carloscortina.demo.model.Treatment;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
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
        
        String hql = "SELECT new Treatment(treatment.idTreatment,treatment.treatment,treatment.directions,treatment.active) FROM Treatment as treatment"
                + " WHERE treatment.active=1";
        Query query = getSession().createQuery(hql);
        List<Treatment> result = query.list();
        
        for(Treatment temp: result){
            String hql1 = "SELECT new Cie10(cie.idCIE10) FROM Cie10 as cie JOIN cie.treatmentList t WHERE t.idTreatment=:treatmentId";
            List<Cie10> cieList = getSession().createQuery(hql1).setParameter("treatmentId", temp.getIdTreatment()).list();
            temp.setCie10List(cieList);
        }
        
        return result;
    }
}
