/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.carloscortina.demo.dao;

import com.carloscortina.demo.model.Cie10;
import com.carloscortina.demo.model.Diagnostic;
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

    
}
