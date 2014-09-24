/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.carloscortina.demo.service;

import com.carloscortina.demo.model.Cie10;
import com.carloscortina.demo.model.Diagnostic;
import java.util.Map;

/**
 *
 * @author Ccortina_Mac
 */
public interface DiagnosticService extends GenericService<Diagnostic>{
    
    public Map<Cie10,Long> getDiagnosticsUse();
}
