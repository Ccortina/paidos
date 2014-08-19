/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.carloscortina.demo.dao;

import com.carloscortina.demo.model.Vaccine;
import java.util.List;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Ccortina_Mac
 */
@Repository
public class HbnVaccineDao extends GenericHbnDao<Vaccine> implements VaccineDao {
    
    @Override
    public List<Vaccine> getActiveVaccines(){
        String hql = "FROM Vaccine v WHERE v.active=1";
        Query query = getSession().createQuery(hql);
        return query.list();
    }
}
