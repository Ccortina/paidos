/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.carloscortina.demo.dao;

import com.carloscortina.demo.model.Documents;
import java.util.List;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Carlos Cortina
 */
@Repository
public class HbnDocumentDao extends GenericHbnDao<Documents> implements DocumentDao{

    @Override
    public List<Documents> getDocumentByType(int idType) {
        Query query = getSession().createQuery("From Documents d WHERE d.idDocumentCategory.idDocumentCategory = :idDocumentCategory");
        query.setParameter("idDocumentCategory", idType);
        
        return query.list();
    }
    
    @Override
    public List<Documents> getDocumentByPatient(int idPatient) {
        Query query = getSession().createQuery("From Documents d WHERE d.idPatient.idPatient = :idPatient");
        query.setParameter("idPatient", idPatient);
        
        return query.list();
    }
    
    
}
