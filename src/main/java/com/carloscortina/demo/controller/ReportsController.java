/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.carloscortina.demo.controller;

import com.carloscortina.demo.model.Cie10;
import com.carloscortina.demo.model.ReportDiagnosticAuxiliar;
import com.carloscortina.demo.service.ConsultationService;
import com.carloscortina.demo.service.DiagnosticService;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Carlos Cortina
 */
@Controller
@RequestMapping(value="/reports")
public class ReportsController {
    
    @Autowired
    private ConsultationService consultationService;
    @Autowired
    private DiagnosticService diagnosticService;
    
    /**
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "")
    public String home(Model model){
    
        return "Reports/ReportsHome";
    }
    
    /**
     *
     * @param year
     * @return
     */
    @RequestMapping(value="getConsultsOfMonthByYear")
    public @ResponseBody Map<Integer,Long> getConsultsOfMonthPerDay(int year){
        Map<Integer,Long> result =consultationService.getConsultsOfMonthByYear(year);
     
        return ( result );
    }
    
    /**
     *
     * @return
     */
    @RequestMapping(value="getDiagnosticsUse")
    public @ResponseBody List<ReportDiagnosticAuxiliar> getDiagnosticsUse(){
        Map<Cie10,Long> query = diagnosticService.getDiagnosticsUse();
        List<ReportDiagnosticAuxiliar> result = new ArrayList<ReportDiagnosticAuxiliar>();
        
        for (Map.Entry<Cie10, Long> entry : query.entrySet()) {
            Cie10 cie = (Cie10)entry.getKey();
            Long count = (Long)entry.getValue();
            result.add(new ReportDiagnosticAuxiliar(cie, count));
        }
        
        return ( result );
    }
    
}
