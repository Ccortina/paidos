/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.carloscortina.demo.controller;

import com.carloscortina.demo.json.JsonPack;
import com.carloscortina.demo.model.Appointment;
import com.carloscortina.demo.model.Cie10;
import com.carloscortina.demo.model.Consultationactivity;
import com.carloscortina.demo.model.Consultationcostabstract;
import com.carloscortina.demo.model.Consultationpayment;
import com.carloscortina.demo.model.Consultationpaymentreceipt;
import com.carloscortina.demo.model.Diagnostic;
import com.carloscortina.demo.model.Report7Helper;
import com.carloscortina.demo.model.ReportDiagnosticAuxiliar;
import com.carloscortina.demo.model.User;
import com.carloscortina.demo.service.AppointmentService;
import com.carloscortina.demo.service.ConsultationPaymentReceiptService;
import com.carloscortina.demo.service.ConsultationPaymentService;
import com.carloscortina.demo.service.ConsultationService;
import com.carloscortina.demo.service.ConsultationactivityService;
import com.carloscortina.demo.service.DiagnosticService;
import com.carloscortina.demo.service.UserService;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    @Autowired
    private ConsultationPaymentService cpService;
    @Autowired
    private ConsultationPaymentReceiptService cprService;
    @Autowired
    private ConsultationactivityService caService;
    @Autowired
    private UserService userService;
    @Autowired
    private AppointmentService appointmentService;
    
    private User loggedUser;
    
    /**
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "")
    public String home(Model model){
        //Get logged User
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        loggedUser = userService.getUserByUsername(auth.getName());
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

    @RequestMapping(value="getConsultationPayments")
    public @ResponseBody JsonPack<Consultationpayment> getConsultationPayments(@RequestParam String start, @RequestParam String end){
       SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy"); 
       Date s = new Date();
       Date e = new Date();

       try{
           s = sdf.parse(start);
           e = sdf.parse(end);
       }catch(Exception err){
           err.printStackTrace();
       }
       List<Consultationpayment> result = cpService.getConsultationPAymentByDateRange(s, e);
       double cac = 0.0 , cec = 0.0;
       int cont =0;
       for(Consultationpayment cp: result){
           cac = 0.0;
           cec = 0.0;
           cp.setPatient(cp.getIdConsultationCostAbstract().getIdConsultation().getIdPatient());
           cp.setConsultationDate(cp.getIdConsultationCostAbstract().getIdConsultation().getIdAppointment().getDate());
           
           for(Consultationactivity ca :cp.getIdConsultationCostAbstract().getIdConsultation().getConsultationactivityList()){
               ca.setConsultation(null);
               if(ca.getActivity().getIdActivityType().getIdActivityType() == 3){
                   cec += ca.getCost();
               }else{
                   cac += ca.getCost();
               }
           }
           cp.setConsultationCAT(cac);
           cp.setConsultationEAT(cec);
           cp.setConsultationTotal(cac+cec);
           cp.setRest(cac+cec-cp.getPaymentTotal());
           cp.setActivities(cp.getIdConsultationCostAbstract().getIdConsultation().getConsultationactivityList());    
       }
       
       return new JsonPack<Consultationpayment>(result); 
    }
    
    @RequestMapping(value="getConsultationReceipts")
    public @ResponseBody JsonPack<Consultationpaymentreceipt> getConsultationReceipts(@RequestParam String start, @RequestParam String end){
       SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy"); 
       Date s = new Date();
       Date e = new Date();

       try{
           s = sdf.parse(start);
           e = sdf.parse(end);
       }catch(Exception err){
           err.printStackTrace();
       }
       List<Consultationpaymentreceipt> result = cprService.getConsultationPAymentByDateRange(s, e, loggedUser);
       
       for(Consultationpaymentreceipt cpr: result){
           cpr.setConsultationDate(cpr.getIdPayment().getIdConsultationCostAbstract().getIdConsultation().getIdAppointment().getDate());
       }
       
       return new JsonPack<Consultationpaymentreceipt>(result); 
    }
    
    @RequestMapping(value="getConsultationReceiptsTotals")
    public @ResponseBody JsonPack<Consultationpaymentreceipt> getConsultationReceiptsTotals(@RequestParam String start, @RequestParam String end){
       SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy"); 
       Date s = new Date();
       Date e = new Date();

       try{
           s = sdf.parse(start);
           e = sdf.parse(end);
       }catch(Exception err){
           err.printStackTrace();
       }

       return new JsonPack<Consultationpaymentreceipt>(cprService.getConsultationPaymentReceiptTotals(s, e, loggedUser));
    }
    
    @RequestMapping(value="getGlobalIncomeReport")
    public @ResponseBody JsonPack<Consultationactivity> getGlobalIncomeReport(@RequestParam String start, @RequestParam String end){
       SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy"); 
       Date s = new Date();
       Date e = new Date();
       double consultoryTotal=0.0,externalTotal=0.0;
       
       try{
           s = sdf.parse(start);
           e = sdf.parse(end);
       }catch(Exception err){
           err.printStackTrace();
       }
       List<Consultationactivity> result = caService.getGlobalReport(s, e, loggedUser);
       for(Consultationactivity ca : result){
           if(ca.getActivity().getIdActivityType().getIdActivityType() == 3){   //External
               externalTotal = externalTotal + ca.getCost();
           }else{
               consultoryTotal = consultoryTotal + ca.getCost();
           }
       }
       List<Consultationactivity> totals = new ArrayList<Consultationactivity>();
       totals.add(new Consultationactivity(consultoryTotal, externalTotal, externalTotal+consultoryTotal));
       return new JsonPack<Consultationactivity>(totals);
    }
    
    @RequestMapping(value="getIncomeByActivity")
    public @ResponseBody JsonPack<Report7Helper> getGlobalIncomeReport(@RequestParam String start, @RequestParam String end,@RequestParam int level,@RequestParam int type){
       SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy"); 
       Date s = new Date();
       Date e = new Date();
       
       try{
           s = sdf.parse(start);
           e = sdf.parse(end);
       }catch(Exception err){
           err.printStackTrace();
       }
       
       //List<Consultationpayment> cpList = cpService.getConsultationPaymentByDateRange(s, e, loggedUser);
       List<Consultationactivity> caList ;
       List<Report7Helper> result = new ArrayList<Report7Helper>();
       
       if( type == 1){
           caList = caService.getIncomeByActivityDetails(s, e, loggedUser,4);
           
       }else{
           caList = caService.getIncomeByActivityDetails(s, e, loggedUser,3);
       }
       
       for(Consultationactivity ca: caList){
           for(Consultationcostabstract cc: ca.getConsultation().getConsultationcostabstractList()){
               for(Consultationpayment cp: cc.getConsultationpaymentList()){
                   if(cp.getIdPaymentType().getIdConsultationPaymentType() == 1){
                       result.add(new Report7Helper(ca.getActivity(), ca.getConsultation().getIdPatient(), 
                        ca.getConsultation().getIdAppointment().getDate(), 
                        cp.getDate(), ca.getCost()));
                   }
               }
           }
       }
       
       return new JsonPack<Report7Helper>(result);
    }
    
    @RequestMapping(value="getIncomeByActivityTotals")
    public @ResponseBody JsonPack<Consultationactivity> getGlobalIncomeReportTotals(@RequestParam String start, @RequestParam String end,@RequestParam int level,@RequestParam int type){
       SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy"); 
       Date s = new Date();
       Date e = new Date();
       
       try{
           s = sdf.parse(start);
           e = sdf.parse(end);
       }catch(Exception err){
           err.printStackTrace();
       }
       
       if(type == 1){
            return new JsonPack<Consultationactivity>(caService.getIncomeByActivityTotals(s, e, loggedUser,4));
       }else{
            return new JsonPack<Consultationactivity>(caService.getIncomeByActivityTotals(s, e, loggedUser,3));
       }
    }
    
    @RequestMapping(value="getDiagnosticsUseTable")
    public @ResponseBody JsonPack<Diagnostic> getDiagnosticsUseTable(@RequestParam String start, @RequestParam String end){
       SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy"); 
       Date s = new Date();
       Date e = new Date();
       
       try{
           s = sdf.parse(start);
           e = sdf.parse(end);
       }catch(Exception err){
           err.printStackTrace();
       }
       
       return new JsonPack<Diagnostic>(diagnosticService.getDiagnosticsUseByRange(s, e, loggedUser));
       
    }
    
    @RequestMapping(value="getAppointmentsRelation")
    public @ResponseBody JsonPack<Appointment> getAppointmentsRelation(@RequestParam String start, @RequestParam String end){
       SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy"); 
       Date s = new Date();
       Date e = new Date();
       
       try{
           s = sdf.parse(start);
           e = sdf.parse(end);
       }catch(Exception err){
           err.printStackTrace();
       }
       
       return new JsonPack<Appointment>(appointmentService.getAppointmentsByDateRange(s, e, loggedUser));
       
    }
}
