package com.carloscortina.demo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.carloscortina.demo.model.Relative;
import java.util.HashSet;
import java.util.Set;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.CriteriaSpecification;

@Repository
public class HbnRelativeDao implements RelativeDao {

    @Autowired
    SessionFactory sessionFactory;

    private Session getSession()
    {
            return sessionFactory.getCurrentSession();
    }

    @Override
    public void createRelative(Relative relative) {
            // TODO Auto-generated method stub
            getSession().save(relative);
    }

    @Override
    public Relative getRelative(int id) {
            // TODO Auto-generated method stu
            return (Relative) getSession().get(Relative.class, id);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Relative> getAllRelatives() {
            // TODO Auto-generated method stub
            return ( getSession().createQuery("from Relative").list());
    }


    @Override
    public List<Relative> getRelativesByPatientId(int id) {
            // TODO Auto-generated method stub
            return null;
    }

    @Override
    public void updateRelative(Relative item){
        getSession().update(item);
    }

    @Override
    public List<Relative> getRelativeByRelationship(int idRelationship) {
        String hql = "SELECT DISTINCT new Relative(r.firstName,r.fatherLastName,"
                + "r.motherLastName) FROM Relative as r JOIN r.patientRelativeList pr"
                +" WHERE pr.idRelationship.idRelationship=:idRelationship";
        Query query = getSession().createQuery(hql);
        query.setParameter("idRelationship", idRelationship);
        
        return query.list();
    }

}
