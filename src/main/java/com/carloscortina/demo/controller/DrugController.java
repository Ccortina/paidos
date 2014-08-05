/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.carloscortina.demo.controller;

import com.carloscortina.demo.json.JsonPack;
import com.carloscortina.demo.model.AdministrationUnit;
import com.carloscortina.demo.model.Drug;
import com.carloscortina.demo.model.DrugPresentation;
import com.carloscortina.demo.model.User;
import com.carloscortina.demo.service.AdministrationUnitService;
import com.carloscortina.demo.service.ApplicationMethodService;
import com.carloscortina.demo.service.DoseCalculationCriteriaService;
import com.carloscortina.demo.service.DrugService;
import com.carloscortina.demo.service.ServiceDrugPresentation;
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
@RequestMapping(value="/drug")
public class DrugController {
    
    @Autowired
    private UserService userService;
    @Autowired
    private DrugService drugService;
    @Autowired
    private ServiceDrugPresentation drugPresentationService;
    @Autowired
    private ApplicationMethodService applicationMethodService;
    @Autowired
    private AdministrationUnitService administrationUnitService; 
    @Autowired
    private DoseCalculationCriteriaService doseCalculationService;
     
    private User loggedUser;
    
    @RequestMapping(value="drugHome")
    public String cie10Home(Model model){
        //Get logged User
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        loggedUser = userService.getUserByUsername(auth.getName());
        
        model.addAttribute("drugPresentations",drugPresentationService.getAll("DrugPresentation"));
        model.addAttribute("applicationMethods", applicationMethodService.getAll("ApplicationMethod"));
        model.addAttribute("administrationUnits", administrationUnitService.getAll("AdministrationUnit"));
        model.addAttribute("doseCalculationCriterias", doseCalculationService.getAll("DoseCalculationCriteria"));
        
        return ( "Drug/DrugHome" );
    }
    
    @RequestMapping(value="getDrugByUser")
    public @ResponseBody JsonPack<Drug> getDrugByUser(){
        return (new JsonPack<Drug>(drugService.getDrugByUser(loggedUser.getIdUser())));
    }
}
