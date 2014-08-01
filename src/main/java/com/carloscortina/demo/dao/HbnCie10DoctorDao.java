/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.carloscortina.demo.dao;

import com.carloscortina.demo.model.CIE10Doctor;
import java.util.List;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Ccortina_Mac
 */
@Repository
public class HbnCie10DoctorDao extends GenericHbnDao<CIE10Doctor> implements Cie10DoctorDao{

    @Override
    public List<CIE10Doctor> getCie10ByUser(int id){
        String hql = "SELECT new CIE10Doctor(cie.lastUsed,cie.cie10) FROM CIE10Doctor as cie"
                + " WHERE cie.user.idUser=:idUser";
        Query query = getSession().createQuery(hql);
        query.setParameter("idUser",id);
        
        List<CIE10Doctor> result = query.list();
        
        return result;
        
    }
}
