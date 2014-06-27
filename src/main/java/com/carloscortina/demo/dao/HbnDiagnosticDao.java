/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.carloscortina.demo.dao;

import com.carloscortina.demo.model.Diagnostic;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Ccortina_M
 * ac
 */
@Repository
public class HbnDiagnosticDao extends GenericHbnDao<Diagnostic> implements DiagnosticDao{
    
}
