/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.carloscortina.demo.dao;

import com.carloscortina.demo.model.Measures;
import java.util.List;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Ccortina_Mac
 */
@Repository
public class HbnMeasuresDao extends GenericHbnDao<Measures> implements MeasuresDao{

    @Override
    public List<Measures> getMeasureByUser(int idUser){
        String hql = "FROM Measures m WHERE m.idUser.idUser=:idUser"; 
        Query query = getSession().createQuery(hql);
        query.setParameter("idUser", idUser);
        
        return query.list();
    }
}
