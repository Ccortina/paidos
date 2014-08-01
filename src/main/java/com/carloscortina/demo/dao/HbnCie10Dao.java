package com.carloscortina.demo.dao;

import org.springframework.stereotype.Repository;

import com.carloscortina.demo.model.Cie10;
import java.util.List;
import org.hibernate.Query;

@Repository
public class HbnCie10Dao extends GenericHbnDao<Cie10> implements Cie10Dao {

    @Override
    public List<Cie10> getCie10ByUser(int id){
        String hql = "SELECT new Cie10(cie.idCIE10,cie.cieCode,cie.diagnostic,cie.description,cie.active) FROM Cie10 as cie"
                + " JOIN cie.cie10doctorList u WHERE u.user.idUser=:idUser";
        Query query = getSession().createQuery(hql);
        query.setParameter("idUser",id);
        
        List<Cie10> result = query.list();
        
        return result;
        
    }
}
