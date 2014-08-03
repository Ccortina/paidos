/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.carloscortina.demo.controller;

import com.carloscortina.demo.json.JsonPack;
import com.carloscortina.demo.model.CIE10Doctor;
import com.carloscortina.demo.model.CIE10DoctorPK;
import com.carloscortina.demo.model.Cie10;
import com.carloscortina.demo.model.Consultation;
import com.carloscortina.demo.model.Drug;
import com.carloscortina.demo.model.Treatment;
import com.carloscortina.demo.model.User;
import com.carloscortina.demo.service.Cie10DoctorService;
import com.carloscortina.demo.service.Cie10Service;
import com.carloscortina.demo.service.ConsultationService;
import com.carloscortina.demo.service.DrugService;
import com.carloscortina.demo.service.TreatmentService;
import com.carloscortina.demo.service.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Carlos Cortina
 */
@Controller
@RequestMapping(value="/diagnostictreatment")
public class DiagnosticTreatmentController {
    
    @Autowired
    private UserService userService;
    @Autowired
    private Cie10Service cieService;
    @Autowired
    private Cie10DoctorService cieDoctorService;
    @Autowired
    private ConsultationService consultationService;
    @Autowired
    private TreatmentService treatmentService;
    @Autowired
    private DrugService drugService;
    
    private User loggedUser;
    
    @RequestMapping(value="cie10Home")
    public String cie10Home(Model model){
        //Get logged User
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        loggedUser = userService.getUserByUsername(auth.getName());
        
        return ( "DiagnosticTreatment/CIE10/Cie10Home" );
    }
    
    @RequestMapping(value="diagnosticHome")
    public String diagnosticHome(Model model){
        //Get logged User
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        loggedUser = userService.getUserByUsername(auth.getName());
        
        return ( "DiagnosticTreatment/Diagnostic/DiagnosticHome" );
    }
    
    @RequestMapping(value="treatmentHome")
    public String treatmentHome(Model model){
        //Get logged User
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        loggedUser = userService.getUserByUsername(auth.getName());
        
        return ( "DiagnosticTreatment/Treatment/TreatmentHome" );
    }
    
    @RequestMapping(value="getAllCie")
    public @ResponseBody JsonPack<Cie10> getAllCie(){
        return (new JsonPack<Cie10>(cieService.getAvaibleCie10ByUser(loggedUser.getIdUser())));
    }
    
    @RequestMapping(value="getCieByUser")
    public @ResponseBody JsonPack<Cie10> getCieByUser(){
        return (new JsonPack < Cie10 >(cieService.getCie10ByUser(loggedUser.getIdUser())));
    }
    
    @RequestMapping(value="getDrugsByUser")
    public @ResponseBody JsonPack<Drug> getDrugsByUser()
    {
        return new JsonPack<Drug>(drugService.getDrugByUser(loggedUser.getIdUser()));
    }
    
    @RequestMapping(value="getDrugIncompatibility",produces = "application/json")
    public @ResponseBody List<Drug> getDrugIncompatibility(int idDrug){
        return drugService.getDrugIncompatibilities(idDrug);
    }
    
    @RequestMapping(value="addCieToUserCatalog")
    public @ResponseBody String addCieToUserCatalog(int idCie){
        CIE10DoctorPK id = new CIE10DoctorPK(idCie, loggedUser.getIdUser());
        CIE10Doctor add = new CIE10Doctor(id);
        cieDoctorService.create(add);
        
        return "";
    }
    
    @RequestMapping(value="removeCieOfUserCatalog")
    public @ResponseBody String removeCieOfUserCatalog(int idCie){
        CIE10DoctorPK id = new CIE10DoctorPK(idCie, loggedUser.getIdUser());
        CIE10Doctor add = new CIE10Doctor(id);
        cieDoctorService.delete(add);
        
        return "";
    }
    
    @RequestMapping(value="getConsultationByCie")
    public @ResponseBody JsonPack<Consultation> getConsultationByCie(int idCie){
        
        return (new JsonPack<Consultation>(consultationService.getConsultationByCie(idCie)));
    }
    
    @RequestMapping(value="getTreatmentsByUser")
    public @ResponseBody JsonPack<Treatment> getTreatmentsByUser()
    {   
        return new JsonPack<Treatment>(treatmentService.getTreatmentByUser(loggedUser.getIdUser()));
    }
}
