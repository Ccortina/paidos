/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.carloscortina.demo.controller;

import com.carloscortina.demo.json.JsonPack;
import com.carloscortina.demo.model.Consultationcostabstract;
import com.carloscortina.demo.model.User;
import com.carloscortina.demo.service.ConsultationCostAbstractService;
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
@RequestMapping(value="/income")
public class IncomeController {
    
    @Autowired
    private UserService userService;
    @Autowired
    private ConsultationCostAbstractService ccaService;
    
    private User loggedUser;
    
    @RequestMapping(value="consultations")
    public String graph(Model model){
        //Get logged User
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        loggedUser = userService.getUserByUsername(auth.getName());
        
        return ( "Income/Consultation" );
    }
    
    @RequestMapping(value="getConsultationCostAbstract")
    public @ResponseBody JsonPack<Consultationcostabstract> getConsultationCostAbstract(){
        
       return new JsonPack<Consultationcostabstract>(ccaService.getConsultationCostAbstractSmall()); 
    }
}
