/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.carloscortina.demo.dao;

import com.carloscortina.demo.model.PatientRelative;
import com.carloscortina.demo.model.PatientRelativePK;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Ccortina_Mac
 */
@Repository
public class HbnPatientRelative extends GenericHbnDao<PatientRelative> implements PatientRelativeDao{

    @Override
    public PatientRelative getByPK(PatientRelativePK id){
        Criteria criteria = getSession().createCriteria(PatientRelative.class);
        criteria.add(Restrictions.eq("patientRelativePK", id));
        return (PatientRelative)criteria.uniqueResult();
    }
    
    @Override
    public List<PatientRelative> getSibilingsByPatient(int idPatient){
        Criteria criteria = getSession().createCriteria(PatientRelative.class);
        
        Criterion motherRestriction = Restrictions.eq("idRelationship.idRelationship", 1);
        Criterion fatherRestriction = Restrictions.eq("idRelationship.idRelationship", 2);
        LogicalExpression orExp = Restrictions.or(motherRestriction,fatherRestriction);
        criteria.add(Restrictions.eq("patient.idPatient", idPatient));
        criteria.add(orExp);
        
        Criteria criteria2 = getSession().createCriteria(PatientRelative.class);
        criteria2.add(orExp);
        criteria2.add(Restrictions.ne("patient.idPatient",idPatient));
        List<PatientRelative> parents = criteria.list();
        List<PatientRelative> sibilings = new ArrayList<PatientRelative>();
        for(PatientRelative relative: parents){
            criteria2.add(Restrictions.eq("relative.idRelative",relative.getRelative().getIdRelative()));
            sibilings.addAll(criteria2.list());
        }
        return sibilings;
    }
}
