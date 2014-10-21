/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.carloscortina.demo.service;

import com.carloscortina.demo.model.Cie10;
import com.carloscortina.demo.model.Diagnostic;
import com.carloscortina.demo.model.User;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Ccortina_Mac
 */
public interface DiagnosticService extends GenericService<Diagnostic>{
    
    public Map<Cie10,Long> getDiagnosticsUse();
    public List<Diagnostic> getDiagnosticsUseByRange(Date start,Date end,User doctor);
}
