/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.carloscortina.demo.controller;

import com.carloscortina.demo.json.JsonPack;
import com.carloscortina.demo.model.Consultation;
import com.carloscortina.demo.model.Consultationactivity;
import com.carloscortina.demo.model.Consultationcostabstract;
import com.carloscortina.demo.model.Consultationpayment;
import com.carloscortina.demo.model.Consultationpaymentreceipt;
import com.carloscortina.demo.model.Patient;
import com.carloscortina.demo.model.PatientRelative;
import com.carloscortina.demo.model.Relative;
import com.carloscortina.demo.model.Staffmember;
import com.carloscortina.demo.model.Thirdpartypayer;
import com.carloscortina.demo.model.User;
import com.carloscortina.demo.service.ConsultationCostAbstractService;
import com.carloscortina.demo.service.ConsultationPaymentReceiptService;
import com.carloscortina.demo.service.ConsultationPaymentReceiptTypeService;
import com.carloscortina.demo.service.ConsultationPaymentService;
import com.carloscortina.demo.service.ConsultationPaymentStatusService;
import com.carloscortina.demo.service.ConsultationPaymentTypeService;
import com.carloscortina.demo.service.ConsultationService;
import com.carloscortina.demo.service.PatientService;
import com.carloscortina.demo.service.RelativeService;
import com.carloscortina.demo.service.StaffMemberService;
import com.carloscortina.demo.service.ThirdPartyPayersService;
import com.carloscortina.demo.service.UserService;
import com.sun.javafx.scene.control.skin.VirtualFlow;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Carlos Cortina
 */
@Controller
@RequestMapping(value="/income")
public class IncomeController {
    
    @Autowired
    private UserService userService;
    @Autowired
    private ConsultationCostAbstractService ccaService;
    @Autowired
    private ConsultationService consultationService;
    @Autowired
    private ConsultationPaymentService cpService;
    @Autowired
    private ConsultationPaymentTypeService cptService;
    @Autowired 
    private PatientService patientService;
    @Autowired
    private ThirdPartyPayersService tppService;
    @Autowired
    private RelativeService relativeService;
    @Autowired
    private ConsultationPaymentReceiptTypeService cprtService; 
    @Autowired
    private ConsultationPaymentReceiptService cprService;
    @Autowired
    private ConsultationPaymentStatusService cpsService;
    @Autowired
    private StaffMemberService smService;
    
    private User loggedUser;
    
    @RequestMapping(value="consultations")
    public String consultationCostAbstractHome(Model model){
        //Get logged User
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        loggedUser = userService.getUserByUsername(auth.getName());
        
        return ( "Income/Consultation" );
    }
    
    @RequestMapping(value="payment")
    public String consultationPaymentHome(Model model){
        //Get logged User
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        loggedUser = userService.getUserByUsername(auth.getName());
        
        return ( "Income/Payment" );
    }
    
    @RequestMapping(value="receipt")
    public String consultationReceiptHome(Model model){
        //Get logged User
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        loggedUser = userService.getUserByUsername(auth.getName());
        
        return ( "Income/Receipt" );
    }
    
    @RequestMapping(value="receiptPreview/{cprId}")
    public String receiptPreview(Model model,@PathVariable int cprId){
        //Get logged User
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        loggedUser = userService.getUserByUsername(auth.getName());
        Consultationpaymentreceipt cpr =  cprService.getById(cprId);
        Patient patient = cpr.getIdPayment().getIdConsultationCostAbstract().getIdConsultation().getIdPatient();
        
        model.addAttribute("name", cpr.getPayerName());
        model.addAttribute("rfc",cpr.getRfc());
        if(cpr.getRetention() > 0){
            model.addAttribute("net", "Honorarios: $ "+ (cpr.getTotal()+cpr.getIsr()) 
                    + " Retencion ISR: $ "+cpr.getIsr()+" Neto recibido: $ "+cpr.getTotal());
        }else{
            model.addAttribute("net","Neto recibido: $ "+cpr.getTotal());
        }
        model.addAttribute("totalStr",cpr.getTotalText());
        model.addAttribute("address",cpr.getStreet());
        model.addAttribute("address2",cpr.getColony()+" " +cpr.getCity()+" , "+
                cpr.getState()+" , "+cpr.getCountry()+" , "+cpr.getZip());
        model.addAttribute("patient",patient.getFirstName()+" "+patient.getFatherLastName()+" "+patient.getMotherLastName());
        model.addAttribute("concept",cpr.getConcept());
        
        model.addAttribute("date",cpr.getDate());
        
        return ( "Income/ReceiptPreview" );
    }
    
    @RequestMapping(value="getConsultationCostAbstract")
    public @ResponseBody JsonPack<Consultationcostabstract> getConsultationCostAbstract(){
        
       return new JsonPack<Consultationcostabstract>(ccaService.getConsultationCostAbstractSmall(loggedUser)); 
    }
    
    @RequestMapping(value="getAllCCA")
    public @ResponseBody JsonPack<Consultationcostabstract> getAllCCADoctor(){
       return new JsonPack<Consultationcostabstract>(ccaService.getALLCCASmall(loggedUser)); 
    }
    
    @RequestMapping(value="getConsultationPaymentReceipt")
    public @ResponseBody JsonPack<Consultationpaymentreceipt> getConsultationPaymentReceipt(){
       List<Consultationpaymentreceipt> all = new ArrayList<Consultationpaymentreceipt>(cprService.getAll("")); 
       for(Consultationpaymentreceipt cpr: all){
           cpr.setPatient(cpr.getIdPayment().getIdConsultationCostAbstract().getIdConsultation().getIdPatient());
       }
       
       return new JsonPack<Consultationpaymentreceipt>(all); 
    }
    
    @RequestMapping(value="getConsultationPayment")
    public @ResponseBody JsonPack<Consultationpayment> getConsultationPayment(){
        
       List<Consultationpayment> list = cpService.getConsultationPaymentByUser(loggedUser);
       
       for(Consultationpayment cp: list){
           cp.setPatient(cp.getIdConsultationCostAbstract().getIdConsultation().getIdPatient());
       }
       
       return new JsonPack<Consultationpayment>(list); 
    }
    
    @RequestMapping(value="getConsultationActivities")
    public @ResponseBody List<Consultationactivity> getConsultationActivities(int ccaId){
        Consultation consultation = ccaService.getById(ccaId).getIdConsultation();
        
        for(Consultationactivity ca: consultation.getConsultationactivityList()){
            ca.setConsultation(null);
            ca.getActivity().setIdVaccine(null);
        }
        return consultation.getConsultationactivityList();
    }
    
    @RequestMapping(value="savePayment")
    public @ResponseBody int savePayment(@RequestParam Map<String,String> params){
        Consultationcostabstract cca = ccaService.getById(Integer.parseInt(params.get("ccaId")));
        
        double cash = Double.parseDouble(params.get("cash"));
        double check = Double.parseDouble(params.get("check"));
        double card = Double.parseDouble(params.get("card"));
        double other = Double.parseDouble(params.get("other"));
        double totalPayment = cash + check+ card +other;
        double totalCost = 0.0;
        double change = 0.0;
        
        //Calculate the total cost
        for( Consultationactivity ca: cca.getIdConsultation().getConsultationactivityList()){
            
            totalCost += ca.getCost();
            
        }
        
        if(Integer.parseInt(params.get("paymentType")) == 1){ //If total payment
            change = totalCost - totalPayment;
            cca.setIdConsultationPaymentStatus(cpsService.getById(3));
            cca.setRest(0.0);
        }else{
            cca.setIdConsultationPaymentStatus(cpsService.getById(2));
            cca.setRest(cca.getRest() - totalPayment);
        }

        //Save the payment
        Consultationpayment cp = new Consultationpayment(new Date(), cash, check, params.get("checkD"),
                card, params.get("cardD"), other, params.get("otherD"), totalPayment , change, params.get("notes"),
                cptService.getById(Integer.parseInt(params.get("paymentType"))), cca);
        
        cpService.create(cp);
        ccaService.updateItem(cca);
        
        return cp.getIdConsultationPayment();
    }
    
    @RequestMapping(value="cancelPayment")
    public @ResponseBody void cancelPayment(@RequestParam int payment){
        Consultationpayment cp = cpService.getById(payment);
        List<Consultationpaymentreceipt> cprList = cp.getConsultationpaymentreceiptList();
        Consultationcostabstract cca = cp.getIdConsultationCostAbstract();
        
        //Return payment to consultation cost and if it was total then change the status
        cca.setRest(cp.getPaymentTotal() + cca.getRest());
        if(cp.getIdPaymentType().getIdConsultationPaymentType() == 1){
            cca.setIdConsultationPaymentStatus(cpsService.getById(1)); //Not paid
        }else{
            cca.setIdConsultationPaymentStatus(cpsService.getById(2)); //Partially paid
        }
        
        ccaService.updateItem(cca);
        
        //Update the receipt type to cancelled
        for(Consultationpaymentreceipt cpr : cprList){
            cpr.setIdConsultatioPaymentReceiptType(cprtService.getById(3));
            cpr.setNotes("Recibo Cancelado");
            cpr.setConcept(cpr.getConcept()+"\nRecibo Cancelado");
            cprService.updateItem(cpr);
        }
        
        //update consultation payment to cancelled
        cp.setIdPaymentType(cptService.getById(3));
        cpService.updateItem(cp);
    }
    
    @RequestMapping(value="saveReceipt")
    public @ResponseBody int saveReceipt(@RequestParam Map<String,String> params){
        Thirdpartypayer tpp = new Thirdpartypayer();
        Relative relative = new Relative();
        Consultationpaymentreceipt cpr = new Consultationpaymentreceipt();
        
        Consultationcostabstract cca=ccaService.getById(Integer.parseInt(params.get("ccaId")));
        
        Staffmember consultationDoctor = cca.getIdConsultation().getIdDoctor().getIdStaffMember();
        
        String totalStr = numberToText(params.get("receiptTotal"));
        String[] strNumber = new String[2];
        if(params.get("receiptTotal").indexOf((46)) > -1){
            strNumber = params.get("receiptTotal").split(".");
            totalStr = totalStr + " "+strNumber[1]+"/100 M.N";
        }else{
            totalStr = totalStr + " 00/100 M.N";
        }
        
        if( params.get("relative").isEmpty() ){
            if( !params.get("thirdPayer").isEmpty() ){
                tpp = tppService.getById(Integer.parseInt(params.get("thirdPayer")));
                cpr = new Consultationpaymentreceipt(new Date(), consultationDoctor.getReceiptNumber(), Double.parseDouble(params.get("receiptTotal")),
                params.get("ret").equals("true")? 1:0, Double.parseDouble(params.get("isr")), totalStr,
                params.get("payerName"), params.get("street"), params.get("colony"), params.get("city"), params.get("state"), params.get("country"), params.get("rfc"),
                params.get("concept"), params.get("notes"), tpp, cpService.getById(Integer.parseInt(params.get("payment"))),
                cca.getIdConsultation().getIdDoctor(), cprtService.getById(1));
            }else{
                cpr = new Consultationpaymentreceipt(new Date(), consultationDoctor.getReceiptNumber(), Double.parseDouble(params.get("receiptTotal")),
                params.get("ret").equals("true")? 1:0, Double.parseDouble(params.get("isr")), totalStr,
                params.get("payerName"), params.get("street"), params.get("colony"), params.get("city"), params.get("state"), params.get("country"), params.get("rfc"),
                params.get("concept"), params.get("notes"), cpService.getById(Integer.parseInt(params.get("payment"))),
                cca.getIdConsultation().getIdDoctor(), cprtService.getById(1));
            }
        }else{
            relative = relativeService.getRelative(Integer.parseInt(params.get("relative")));
                cpr = new Consultationpaymentreceipt(new Date(), consultationDoctor.getReceiptNumber(), Double.parseDouble(params.get("receiptTotal")),
                params.get("ret").equals("true")? 1:0, Double.parseDouble(params.get("isr")), totalStr,
                params.get("payerName"), params.get("street"), params.get("colony"), params.get("city"), params.get("state"), params.get("country"), params.get("rfc"),
                params.get("concept"), params.get("notes"),relative, cpService.getById(Integer.parseInt(params.get("payment"))),
                cca.getIdConsultation().getIdDoctor(), cprtService.getById(1));
        }

        cprService.create(cpr);
        
        int receiptNum = consultationDoctor.getReceiptNumber();
        consultationDoctor.setReceiptNumber(receiptNum+1);
        
        smService.updateItem(consultationDoctor);
        
        return cpr.getIdConsultationPaymentReceipt();
    }
    
    @RequestMapping(value="updateReceipt")
    public @ResponseBody int updateReceipt(@RequestParam Map<String,String> params){
        Thirdpartypayer tpp = new Thirdpartypayer();
        Relative relative = new Relative();
        Consultationpaymentreceipt cpr = cprService.getById(Integer.parseInt(params.get("cprId")));
        
        if( params.get("relative").isEmpty() ){
            if( !params.get("thirdPayer").isEmpty() ){
                tpp = tppService.getById(Integer.parseInt(params.get("thirdPayer")));
                cpr.setPayerName(params.get("payerName"));
                cpr.setStreet(params.get("street"));
                cpr.setColony(params.get("colony"));
                cpr.setCity(params.get("city"));
                cpr.setState(params.get("state"));
                cpr.setCountry(params.get("country"));
                cpr.setRfc(params.get("rfc"));
                cpr.setConcept(params.get("concept"));
                cpr.setNotes(params.get("notes"));
                cpr.setIdThirdPartyPayer(tpp);
            }else{
                cpr.setPayerName(params.get("payerName"));
                cpr.setStreet(params.get("street"));
                cpr.setColony(params.get("colony"));
                cpr.setCity(params.get("city"));
                cpr.setState(params.get("state"));
                cpr.setCountry(params.get("country"));
                cpr.setRfc(params.get("rfc"));
                cpr.setConcept(params.get("concept"));
                cpr.setNotes(params.get("notes"));
            }
        }else{
            relative = relativeService.getRelative(Integer.parseInt(params.get("relative")));
            cpr.setPayerName(params.get("payerName"));
            cpr.setStreet(params.get("street"));
            cpr.setColony(params.get("colony"));
            cpr.setCity(params.get("city"));
            cpr.setState(params.get("state"));
            cpr.setCountry(params.get("country"));
            cpr.setRfc(params.get("rfc"));
            cpr.setConcept(params.get("concept"));
            cpr.setNotes(params.get("notes"));
            cpr.setIdRelative(relative);
        }

        cprService.updateItem(cpr);
        
        return cpr.getIdConsultationPaymentReceipt();
    }
    
    @RequestMapping(value="getPatientRelatives", produces = "application/json")
    public @ResponseBody JsonPack<PatientRelative> getPatientRelatives(int idPatient){
        return new JsonPack<PatientRelative>(patientService.getById(idPatient).getPatientRelativeList());
    }
    
    @RequestMapping(value="getThirdPartyPayer")
    public @ResponseBody JsonPack<Thirdpartypayer> getThirdPartyPayer(){
        return new JsonPack<Thirdpartypayer>(tppService.getAllActiveItems());
    }
    
    private String numberToText(String number){
        String[] strNumber = new String[2];
        if(number.indexOf((46)) > -1){
            strNumber = number.split(".");
        }else{
            strNumber[0] = number;
        }
        
        switch(strNumber[0].length()){
            case 1:
                return units(Integer.parseInt(strNumber[0])) ;
            case 2:
                if(Double.valueOf(number) < 10 || Double.valueOf(number) > 19){
                    return tens(Integer.parseInt(strNumber[0].substring(0, 1))) + " y " + units(Integer.parseInt(strNumber[0].substring(1)));
                }else{
                    return tens(Integer.parseInt(strNumber[0]));
                }
            case 3:
                if(Integer.parseInt(strNumber[0]) == 100){
                    return "Cien";
                }else{
                    return hundreds(Integer.parseInt(strNumber[0].substring(0,1)))+ " "+ numberToText(strNumber[0].substring(1));
                }
            case 4:
                return units(Integer.parseInt(strNumber[0].substring(0,1))) + " mil " + numberToText(strNumber[0].substring(1));
            case 5:
                return numberToText(strNumber[0].substring(0,2)) + " mil " + numberToText(strNumber[0].substring(2));
            case 6:
                return numberToText(strNumber[0].substring(0,3)) + " mil " + numberToText(strNumber[0].substring(3));
                
        }
        
        return "";
    }
    
    private String hundreds(int number){
        
        switch(number){
            case 1:
                return "Ciento";
            case 2:
                return "Doscientos";
            case 3:
                return "Trescientos";
            case 4:
                return "Cuatroscientos";
            case 5:
                return "Quinientos";
            case 6:
                return "Seiscientos";
            case 7:
                return "Setecientos";
            case 8:
                return "Ochoscientos";
            case 9:
                return "Novescientos";
        }
        return "";
    }
    
    private String tens(int number){
        switch(number){
            case 10:
                return "Diez";
            case 11:
                return "Once";
            case 12:
                return "Doce";
            case 13:
                return "Trece";
            case 14:
                return "Catorce";
            case 15:
                return "Quince";
            case 16:
                return "Diesciseis";
            case 17:
                return "Diescisiete";
            case 18:
                return "Diesciocho";
            case 19:
                return "Diescinueve";
            case 2:
                return "Veinte";
            case 3:
                return "Treinta";
            case 4:
                return "Cuarenta";
            case 5:
                return "Cincuenta";
            case 6:
                return "Sesenta";
            case 7:
                return "Setenta";
            case 8:
                return "Ochenta";
            case 9:
                return "Noventa";    
        }
        return "";
    }
    
    private String units(int number){
        switch(number){
            case 1:
                return "Uno";
            case 2:
                return "Dos";
            case 3:
                return "Tres";
            case 4:
                return "Cuatro";
            case 5:
                return "Cinco";
            case 6:
                return "Seis";
            case 7:
                return "Siete";
            case 8:
                return "Ocho";
            case 9:
                return "Nueve";
                
        }
        return "";
    }
}
