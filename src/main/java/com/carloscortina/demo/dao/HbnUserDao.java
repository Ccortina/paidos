/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.carloscortina.demo.dao;

import com.carloscortina.demo.model.User;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Carlos Cortina
 */
@Repository
public class HbnUserDao extends GenericHbnDao<User> implements UserDao{

    @Override
    public User getUserByUsername(String username) {
        Criteria criteria = getSession().createCriteria(User.class);
        criteria.add(Restrictions.eq("username", username));
        return (User) criteria.uniqueResult();
    }
    
    @Override
    public List<User> getUserByRole(int idRole){
        String hql = "SELECT new User(u.idUser,u.idStaffMember) FROM User as u"
                + " WHERE u.idRole.idRole=:idRole";
        Query query = getSession().createQuery(hql);
        query.setParameter("idRole",idRole);
        
        List<User> result = query.list();
        
        return result;
    }
}
