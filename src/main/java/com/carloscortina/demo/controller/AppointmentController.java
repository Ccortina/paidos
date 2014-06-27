/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.carloscortina.demo.controller;

import com.carloscortina.demo.json.JsonPack;
import com.carloscortina.demo.model.Appointment;
import com.carloscortina.demo.service.AppointmentService;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Ccortina_Mac
 */
@Controller
@RequestMapping(value="/appointment")
public class AppointmentController {
    
    @Autowired
    AppointmentService appointmentService;
    
    int idDoctor;
    
    @RequestMapping(value="")
    public String home(Model model){
        
        
        return"appointment/appointmentHome";
    }
    
    @RequestMapping(value="doctor")
    public String doctorHome(Model model){

        return"appointment/doctorAppointmentHome";
    }
    
    @RequestMapping(value="getAppointments")
    public @ResponseBody JsonPack<Appointment> getAppointments(String start,String end,String timezone){
        idDoctor=25;
        
        SimpleDateFormat parseDate = new SimpleDateFormat("yyyy-M-d");
        List<Appointment> appointments = new ArrayList<Appointment>();
        
        try{
            Date dStart = parseDate.parse(start);
            Date dEnd = parseDate.parse(end);
            appointments = appointmentService.getAppointments(dStart, dEnd);
        }catch (Exception e) {
            e.printStackTrace();
        }
        
        return new JsonPack<Appointment>(appointments);
    }
    
    @RequestMapping(value="getDoctorAppointments")
    public @ResponseBody JsonPack<Appointment> getDoctorAppointments(String start,String end,String timezone){
        idDoctor=16;
        
        SimpleDateFormat parseDate = new SimpleDateFormat("yyyy-M-d");
        List<Appointment> appointments = new ArrayList<Appointment>();
        
        try{
            Date dStart = parseDate.parse(start);
            Date dEnd = parseDate.parse(end);
            appointments = appointmentService.getAppointments(dStart, dEnd);
        }catch (Exception e) {
            e.printStackTrace();
        }
        Iterator<Appointment> filter = appointments.iterator();

        while(filter.hasNext()){
            Appointment aux = new Appointment();
            aux = filter.next();
            
            if(aux.getIdDoctor().getIdUser() != idDoctor){
                filter.remove();
            }
        }
        return new JsonPack<Appointment>(appointments);
    } 
}
