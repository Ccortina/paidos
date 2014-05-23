/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.carloscortina.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Ccortina_Mac
 */
@Controller
@RequestMapping(value="/appointment")
public class AppointmentController {
    
    @RequestMapping(value="")
    public String home(Model model){
        
        
        return"appointment/home";
    }
    
}
