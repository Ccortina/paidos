/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.carloscortina.demo.controller;

import com.carloscortina.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Carlos Cortina
 */
@Controller
@RequestMapping(value="/administration")
public class AdministrationController {
    
    @Autowired
    private UserService userService;
    
    @RequestMapping(value="userHome")
    public String userHome(Model model){
        //Get logged User
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("loggedUser", userService.getUserByUsername(auth.getName()));
    
        return "Administration/Users/UserHome";
    }
    
}
