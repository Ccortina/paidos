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
import com.carloscortina.demo.model.User;
import com.carloscortina.demo.service.Cie10DoctorService;
import com.carloscortina.demo.service.Cie10Service;
import com.carloscortina.demo.service.ConsultationService;
import com.carloscortina.demo.service.UserService;
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
    
    @RequestMapping(value="getAllCie")
    public @ResponseBody JsonPack<Cie10> getAllCie(){
        return (new JsonPack<Cie10>(cieService.getAvaibleCie10ByUser(loggedUser.getIdUser())));
    }
    
    @RequestMapping(value="getCieByUser")
    public @ResponseBody JsonPack<Cie10> getCieByUser(){
        return (new JsonPack < Cie10 >(cieService.getCie10ByUser(loggedUser.getIdUser())));
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
}
