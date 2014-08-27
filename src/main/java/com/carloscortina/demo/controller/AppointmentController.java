/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.carloscortina.demo.controller;

import static com.carloscortina.demo.controller.PatientsController.ONE_MINUTE_IN_MILLIS;
import com.carloscortina.demo.json.JsonPack;
import com.carloscortina.demo.model.Appointment;
import com.carloscortina.demo.model.Consultationmotive;
import com.carloscortina.demo.model.Patient;
import com.carloscortina.demo.model.User;
import com.carloscortina.demo.service.AppointmentService;
import com.carloscortina.demo.service.AppointmentStatusService;
import com.carloscortina.demo.service.ConsultationmotiveService;
import com.carloscortina.demo.service.PatientService;
import com.carloscortina.demo.service.UserService;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
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
 * @author Ccortina_Mac
 */
@Controller
@RequestMapping(value="/appointment")
public class AppointmentController {
    
    @Autowired
    AppointmentService appointmentService;
    @Autowired
    UserService userService;   
    @Autowired
    PatientService patientService;
    @Autowired
    AppointmentStatusService apsService;   
    @Autowired
    ConsultationmotiveService cmService; 
    
    
    int idDoctor;
    
    @RequestMapping(value="")
    public String home(Model model){
        User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("scriptJS","doctorAppointmentHome.js");
        
        return"appointment/appointmentHome";
    }
    
    @RequestMapping(value="doctor")
    public String doctorHome(Model model){
        User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        
        return"appointment/doctorAppointmentHome";
    }
    
    @RequestMapping(value="getAppointments")
    public @ResponseBody JsonPack<Appointment> getAppointments(String start,String end,String timezone){
        
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
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        idDoctor=userService.getUserByUsername(currentPrincipalName).getIdUser();
        
        SimpleDateFormat parseDate = new SimpleDateFormat("yyyy-M-d");
        List<Appointment> appointments = new ArrayList<Appointment>();
        Date dStart = new Date();
        Date dEnd = new Date();
        try{
            dStart = parseDate.parse(start);
            dEnd = parseDate.parse(end);       
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
    
    @RequestMapping(value="getDoctorAppointmentsList")
    public @ResponseBody JsonPack<Appointment> getDoctorAppointmentsByStart(String start){
        
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        idDoctor=userService.getUserByUsername(currentPrincipalName).getIdUser();
        
        SimpleDateFormat parseDate = new SimpleDateFormat("d/M/yyyy");
        List<Appointment> appointments = new ArrayList<Appointment>();
        Date dStart = new Date();
        try{
            if(start.isEmpty()){
                dStart= new Date();
            }else{
                dStart = parseDate.parse(start);
            }
            Calendar c = Calendar.getInstance();
            c.setTime(dStart);
            c.add(Calendar.DATE, 1);
            Date dEnd = c.getTime();
                    
            appointments = appointmentService.getAppointments(dStart, dStart, idDoctor);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return new JsonPack<Appointment>(appointments);
    }
    
    @RequestMapping(value="saveAppointment")
        public @ResponseBody String savePatientAppointment(@RequestParam Map<String,String> params){
            SimpleDateFormat stf = new SimpleDateFormat("kk:mm");
            SimpleDateFormat sdf = new SimpleDateFormat("dd/M/yyyy");

            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String currentPrincipalName = authentication.getName();
           
            Appointment appointment = new Appointment();
            
            try{
                appointment.setDate(sdf.parse(params.get("date")));
                appointment.setStartTime(stf.parse(params.get("startTime")));
                appointment.setMotive(params.get("motive"));
                appointment.setIdDoctor(userService.getById(Integer.parseInt(params.get("idDoctor"))));
                appointment.setIdPatient(patientService.getById(Integer.parseInt(params.get("idPatient"))));
                appointment.setIdStatus(apsService.getById(Integer.parseInt(params.get("idStatus"))));
                appointment.setImmunization(params.get("immunization").equalsIgnoreCase("true")? 1:0);
                appointment.setProgrammedBySystem((short)0);
                appointment.setRegisteredBy(userService.getUserByUsername(currentPrincipalName)); //Mod
                appointment.setNotes(params.get("notes"));
                if(!params.get("pc").isEmpty()){appointment.setPc(Double.parseDouble(params.get("pc")));}
                if(!params.get("size").isEmpty()){appointment.setSize(Double.parseDouble(params.get("size")));}
                if(!params.get("ta").isEmpty()){appointment.setTa(Double.parseDouble(params.get("ta")));}
                if(!params.get("ta2").isEmpty()){appointment.setTa2(Double.parseDouble(params.get("ta2")));}
                if(!params.get("taAverage").isEmpty()){appointment.setTaAverage(Double.parseDouble(params.get("taAverage")));}
                if(!params.get("temperature").isEmpty()){appointment.setTemperature(Double.parseDouble(params.get("temperature")));}
                if(!params.get("weight").isEmpty()){appointment.setWeight(Double.parseDouble(params.get("weight")));}
                
                Consultationmotive cm = cmService.getMotiveByName(params.get("motive"));
                if( cm != null){
                    cm.setLastUsed(new Date());
                    cmService.updateItem(cm);
                }
                
                appointmentService.create(appointment);
 
            }catch(Exception e){ e.printStackTrace(); } 
            return "";
        }
        
        @RequestMapping(value="modifyAppointment")
        public @ResponseBody String modifyAppointment(@RequestParam Map<String,String> params){
            SimpleDateFormat stf = new SimpleDateFormat("kk:mm");
            SimpleDateFormat sdf = new SimpleDateFormat("dd/M/yyyy");

            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String currentPrincipalName = authentication.getName();
           
            Appointment appointment = appointmentService.getById(Integer.parseInt(params.get("idAppointment")));
            
            try{
                appointment.setDate(sdf.parse(params.get("date")));
                appointment.setStartTime(stf.parse(params.get("startTime")));
                appointment.setMotive(params.get("motive"));
                appointment.setIdDoctor(userService.getById(Integer.parseInt(params.get("idDoctor"))));
                appointment.setIdPatient(patientService.getById(Integer.parseInt(params.get("idPatient"))));
                appointment.setIdStatus(apsService.getById(Integer.parseInt(params.get("idStatus"))));
                appointment.setImmunization(params.get("immunization").equalsIgnoreCase("true")? 1:0);
                appointment.setProgrammedBySystem((short)0);
                appointment.setRegisteredBy(userService.getUserByUsername(currentPrincipalName));
                appointment.setNotes(params.get("notes"));
                if(!params.get("pc").isEmpty()){appointment.setPc(Double.parseDouble(params.get("pc")));}
                if(!params.get("size").isEmpty()){appointment.setSize(Double.parseDouble(params.get("size")));}
                if(!params.get("ta").isEmpty()){appointment.setTa(Double.parseDouble(params.get("ta")));}
                if(!params.get("ta2").isEmpty()){appointment.setTa2(Double.parseDouble(params.get("ta2")));}
                if(!params.get("taAverage").isEmpty()){appointment.setTaAverage(Double.parseDouble(params.get("taAverage")));}
                if(!params.get("temperature").isEmpty()){appointment.setTemperature(Double.parseDouble(params.get("temperature")));}
                if(!params.get("weight").isEmpty()){appointment.setWeight(Double.parseDouble(params.get("weight")));}
                
                Consultationmotive cm = cmService.getMotiveByName(params.get("motive"));
                if( cm != null){
                    cm.setLastUsed(new Date());
                    cmService.updateItem(cm);
                }
                
                appointmentService.updateItem(appointment);
 
            }catch(Exception e){ e.printStackTrace(); } 
            return "";
        }
        
        @RequestMapping(value="getPatients")
        public @ResponseBody JsonPack<Patient> getAllPatientsByDoctor(){
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String currentPrincipalName = authentication.getName();
            
            return new JsonPack<Patient>(patientService.getAllActivePatientsByDoctor(userService.getUserByUsername(currentPrincipalName).getIdStaffMember().getIdStaffMember()));
        }
}
