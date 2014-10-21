/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.carloscortina.demo.dao;

import com.carloscortina.demo.model.Cie10;
import com.carloscortina.demo.model.Diagnostic;
import com.carloscortina.demo.model.User;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Ccortina_M
 * ac
 */
@Repository
public class HbnDiagnosticDao extends GenericHbnDao<Diagnostic> implements DiagnosticDao{

    @Override
    public Map<Cie10, Long> getDiagnosticsUse() {
        
        Query query = getSession().createQuery("SELECT d.idCIE10,COUNT(*) FROM Diagnostic d "
                + "WHERE d.idCIE10 IS NOT NULL GROUP BY d.idCIE10");
        
        List<Object[]> rows = query.list();
        Map<Cie10,Long> result = new HashMap<Cie10, Long>();
        
        for (Object[] row : rows){
            result.put((Cie10)row[0],(Long) row[1]);
        }
        
        return result;
    }
    
    @Override
    public List<Diagnostic> getDiagnosticsUseByRange(Date start,Date end,User doctor) {
        Query query;
        if(doctor == null){
             query = getSession().createQuery("SELECT new Diagnostic(d.idCIE10,COUNT(*)) "
                    + "FROM Diagnostic d "
                    + "INNER JOIN d.consultationList cons "
                    + "WHERE d.idCIE10 IS NOT NULL AND "
                    + "cons.idAppointment.date >= :start AND "
                    + "cons.idAppointment.date <= :end " 
                    + "GROUP BY d.idCIE10");
             
             query.setParameter("start", start);
             query.setParameter("end", end);
        
             return query.list();
        }
        
        query = getSession().createQuery("SELECT new Diagnostic(d.idCIE10,COUNT(*)) "
                    + "FROM Diagnostic d "
                    + "INNER JOIN d.consultationList cons "
                    + "WHERE d.idCIE10 IS NOT NULL AND "
                    + "cons.idAppointment.date >= :start AND "
                    + "cons.idAppointment.date <= :end AND "
                    + "cons.idDoctor.idUser = :doctor " 
                    + "GROUP BY d.idCIE10");
        
        query.setParameter("start", start);
        query.setParameter("end", end);
        query.setParameter("doctor", doctor.getIdUser());
        
        return query.list();
    }

    
}
