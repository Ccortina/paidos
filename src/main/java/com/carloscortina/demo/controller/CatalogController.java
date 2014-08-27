/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.carloscortina.demo.controller;

import com.carloscortina.demo.json.JsonPack;
import com.carloscortina.demo.model.Activity;
import com.carloscortina.demo.model.Appointment;
import com.carloscortina.demo.model.Appointmentstatus;
import com.carloscortina.demo.model.Birthmethod;
import com.carloscortina.demo.model.Consultationactivity;
import com.carloscortina.demo.model.Consultationmeasure;
import com.carloscortina.demo.model.Holyday;
import com.carloscortina.demo.model.Laboratorytest;
import com.carloscortina.demo.model.Measures;
import com.carloscortina.demo.model.Patient;
import com.carloscortina.demo.model.Patientvaccine;
import com.carloscortina.demo.model.Relationship;
import com.carloscortina.demo.model.Relative;
import com.carloscortina.demo.model.User;
import com.carloscortina.demo.model.Vaccine;
import com.carloscortina.demo.service.ActivityService;
import com.carloscortina.demo.service.ActivityTypeService;
import com.carloscortina.demo.service.AppointmentService;
import com.carloscortina.demo.service.AppointmentStatusService;
import com.carloscortina.demo.service.BirthmethodService;
import com.carloscortina.demo.service.ConsultationactivityService;
import com.carloscortina.demo.service.ConsultationmeasureService;
import com.carloscortina.demo.service.HolydayService;
import com.carloscortina.demo.service.LaboratoryTestService;
import com.carloscortina.demo.service.MeasuresService;
import com.carloscortina.demo.service.PatientService;
import com.carloscortina.demo.service.PatientVaccineService;
import com.carloscortina.demo.service.RelationshipService;
import com.carloscortina.demo.service.RelativeService;
import com.carloscortina.demo.service.UserService;
import com.carloscortina.demo.service.VaccineService;
import com.carloscortina.demo.service.VaccineTypeService;
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
@RequestMapping(value="/catalogs")
public class CatalogController {
    
    @Autowired
    private UserService userService;
    @Autowired
    private LaboratoryTestService laboratoryTestService;
    @Autowired 
    private PatientService patientService;
    @Autowired
    private BirthmethodService birthmethodService;
    @Autowired
    private RelationshipService relationshipService;
    @Autowired
    private RelativeService relativeService;
    @Autowired
    AppointmentStatusService apsService;
    @Autowired
    private AppointmentService appointmentService;
    @Autowired
    private MeasuresService measureService;
    @Autowired
    private ConsultationmeasureService cmService;
    @Autowired
    private ActivityService activityService;
    @Autowired
    private ActivityTypeService atService;
    @Autowired
    private VaccineService vaccineService;
    @Autowired
    private ConsultationactivityService caService;
    @Autowired
    private HolydayService holydayService;
    @Autowired
    private VaccineTypeService vtService;
    @Autowired
    private PatientVaccineService pvService;
    
    private User loggedUser;
    
    @RequestMapping(value="imunizationHome")
    public String imunizationHome(Model model){
        //Get logged User
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        loggedUser = userService.getUserByUsername(auth.getName());
        model.addAttribute("inmmunizationType", vtService.getAll("")); 
        
        return "Catalog/ImunizationHome";
    }
    
    @RequestMapping(value="birthMethodHome")
    public String birthMethodHome(Model model){
        //Get logged User
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        loggedUser = userService.getUserByUsername(auth.getName());
        
        return "Catalog/BirthMethodHome";
    }   
     
    @RequestMapping(value="laboratoryHome")
    public String laboratoryHome(Model model){
        //Get logged User
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        loggedUser = userService.getUserByUsername(auth.getName());
        
        return "Catalog/LaboratoryHome";
    }      
    
    @RequestMapping(value="relationshipHome")
    public String relationshipHome(Model model){
        //Get logged User
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        loggedUser = userService.getUserByUsername(auth.getName());
        
        return "Catalog/RelationshipHome";
    }
    
    @RequestMapping(value="appointmentStatusHome")
    public String appointmentStatusHome(Model model){
        //Get logged User
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        loggedUser = userService.getUserByUsername(auth.getName());
        
        return "Catalog/AppointmentStatusHome";
    }  
   
    @RequestMapping(value="consultationMeasureHome")
    public String consultationMeasureHome(Model model){
        //Get logged User
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        loggedUser = userService.getUserByUsername(auth.getName());
        
        return "Catalog/MeasureHome";
    }
    
    @RequestMapping(value="activityHome")
    public String activityHome(Model model){
        //Get logged User
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        loggedUser = userService.getUserByUsername(auth.getName());
        
        model.addAttribute("activitiesType", atService.getAll("ActivityType"));
        
        return "Catalog/ActivityHome";
    }
    
    @RequestMapping(value="holydayHome")
    public String holydayHome(Model model){
        //Get logged User
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        loggedUser = userService.getUserByUsername(auth.getName());
        
        return "Catalog/HolydayHome";
    }  
    
    //Section: Laboratory test
    
    @RequestMapping(value="getLaboratoryTest")
    public @ResponseBody JsonPack<Laboratorytest> getDrugAdministrationUnit(){
        return (new JsonPack<Laboratorytest> (laboratoryTestService.getAll("LaboratoryTest")));
    }
    
    @RequestMapping(value="saveNewLaboratoryTest")
    public @ResponseBody String saveNewLaboratoryTest(@RequestParam Map<String,String> params){
        
        laboratoryTestService.create(new Laboratorytest(params.get("itemName"), params.get("active").equalsIgnoreCase("true")?1:0));        
        return "";
    }
    
    @RequestMapping(value="saveModifyLaboratoryTest")
    public @ResponseBody String saveModifyLaboratoryTest(@RequestParam Map<String,String> params){
        Laboratorytest item = laboratoryTestService.getById(Integer.parseInt(params.get("idItem")));
        item.setActive(params.get("active").equalsIgnoreCase("true")?1:0);
        item.setLaboratoryTest(params.get("itemName"));
        
        laboratoryTestService.updateItem(item);
        return "";
    }
    
    @RequestMapping(value="getLaboratoryTestRelatedInfo")
    public @ResponseBody JsonPack<Patient> getLaboratoryTestRelatedInfo(int id){
    
        return (new JsonPack<Patient>(patientService.getPatientByLaboratoryTest(id)));
    }
    
    //Section: Birth method
    
    @RequestMapping(value="getBirthmethod")
    public @ResponseBody JsonPack<Birthmethod> getBirthmethod(){
        return (new JsonPack<Birthmethod> (birthmethodService.getAll("Birthmethod")));
    }
    
    @RequestMapping(value="saveNewBirthmethod")
    public @ResponseBody String saveNewBirthmethod(@RequestParam Map<String,String> params){
        birthmethodService.create(new Birthmethod(params.get("itemName"), params.get("active").equalsIgnoreCase("true")?1:0));
      
        return "";
    }
    
    @RequestMapping(value="saveModifyBirthmethod")
    public @ResponseBody String saveModifyBirthmethod(@RequestParam Map<String,String> params){
        Birthmethod item = birthmethodService.getById(Integer.parseInt(params.get("idItem")));
        item.setActive(params.get("active").equalsIgnoreCase("true")?(short)1:(short)0);
        item.setBirthMethod(params.get("itemName"));
        
        birthmethodService.updateItem(item);
        return "";
    }
    
    @RequestMapping(value="getBirthmethodRelatedInfo")
    public @ResponseBody JsonPack<Patient> getBirthmethodRelatedInfo(int id){
    
        return (new JsonPack<Patient>(patientService.getPatientByBirthmethod(id)));
    }
    
    //Section: Relationship
    
    @RequestMapping(value="getRelationship")
    public @ResponseBody JsonPack<Relationship> getRelationship(){
        return (new JsonPack<Relationship> (relationshipService.getAll("Relationship")));
    }
    
    @RequestMapping(value="saveNewRelationship")
    public @ResponseBody String saveNewRelationship(@RequestParam Map<String,String> params){
        relationshipService.create(new Relationship(params.get("itemName"), params.get("active").equalsIgnoreCase("true")?(short)1:(short)0));
      
        return "";
    }
    
    @RequestMapping(value="saveModifyRelationship")
    public @ResponseBody String saveModifyRelationship(@RequestParam Map<String,String> params){
        Relationship item = relationshipService.getById(Integer.parseInt(params.get("idItem")));
        item.setActive(params.get("active").equalsIgnoreCase("true")? 1:0);
        item.setRelationship(params.get("itemName"));
        
        relationshipService.updateItem(item);
        return "";
    }
    
    @RequestMapping(value="getRelationshipRelatedInfo")
    public @ResponseBody JsonPack<Relative> getRelationshipRelatedInfo(int id){
    
        return (new JsonPack<Relative>(relativeService.getRelativeByRelationship(id)));
    }
    
    //Section: Appointment Status
    
    @RequestMapping(value="getAppointmentStatus")
    public @ResponseBody JsonPack<Appointmentstatus> getAppointmentStatus(){
        return (new JsonPack<Appointmentstatus> (apsService.getAll("AppointmentStatus")));
    }
    
    @RequestMapping(value="saveNewAppointmentStatus")
    public @ResponseBody String saveNewAppointmentStatus(@RequestParam Map<String,String> params){
        apsService.create(new Appointmentstatus(params.get("itemName"), params.get("active").equalsIgnoreCase("true")?1:0));
      
        return "";
    }
    
    @RequestMapping(value="saveModifyAppointmentStatus")
    public @ResponseBody String saveModifyAppointmentStatus(@RequestParam Map<String,String> params){
        Appointmentstatus item = apsService.getById(Integer.parseInt(params.get("idItem")));
        item.setActive(params.get("active").equalsIgnoreCase("true")?(short)1:(short)0);
        item.setStatus(params.get("itemName"));
        
        apsService.updateItem(item);
        return "";
    }
    
    @RequestMapping(value="getAppointmentStatusRelatedInfo")
    public @ResponseBody JsonPack<Appointment> getAppointmentStatusRelatedInfo(int id){
    
        return (new JsonPack<Appointment>(appointmentService.getAppointmentsByStatus(id)));
    }
    
    //Section: Consultation Measure
    @RequestMapping(value="getMeasure")
    public @ResponseBody JsonPack<Measures> getMeasure(){
        return (new JsonPack<Measures> (measureService.getMeasureByUser(loggedUser.getIdUser())));
    }
    
    @RequestMapping(value="saveNewMeasure")
    public @ResponseBody String saveNewMeasure(@RequestParam Map<String,String> params){
        measureService.create(new Measures(params.get("itemName"), params.get("unit"), params.get("include").equalsIgnoreCase("true")?1:0, params.get("active").equalsIgnoreCase("true")?1:0));
        
        return "";
    }
    
    @RequestMapping(value="saveModifyMeasure")
    public @ResponseBody String saveModifyMeasure(@RequestParam Map<String,String> params){
        Measures item = measureService.getById(Integer.parseInt(params.get("idItem")));
        item.setActive(params.get("active").equalsIgnoreCase("true")?(short)1:(short)0);
        item.setMeasure(params.get("itemName"));
        item.setIncludePrescription(params.get("include").equalsIgnoreCase("true")?(short)1:(short)0);
        
        measureService.updateItem(item);
        return "";
    }
    
    @RequestMapping(value="getMeasureRelatedInfo")
    public @ResponseBody JsonPack<Appointment> getMeasureRelatedInfo(int id){
    
        List<Consultationmeasure> cmList = cmService.getConsultationsByMeasure(id);
        List<Appointment> aList = new ArrayList<Appointment>();
        
        for(Consultationmeasure cm: cmList){
            aList.add(cm.getConsultation().getIdAppointment());
        }
        
        return (new JsonPack<Appointment>(aList));
    }
    
    //Section: Activity
    @RequestMapping(value="getActivity")
    public @ResponseBody JsonPack<Activity> getActivity(){
        return (new JsonPack<Activity> (activityService.getAll("Activity")));
    }
    
    @RequestMapping(value="getVaccine")
    public @ResponseBody JsonPack<Vaccine> getVaccine(){
        return (new JsonPack<Vaccine> (vaccineService.getAllActiveVaccines()));
    }
    
    @RequestMapping(value="saveNewActivity")
    public @ResponseBody String saveNewActivity(@RequestParam Map<String,String> params){
        activityService.create(new Activity(params.get("itemName"), Double.parseDouble(params.get("cost")),
                params.get("include").equalsIgnoreCase("true")?(short)1:(short)0, 
                params.get("active").equalsIgnoreCase("true")?(short)1:(short)0,
                params.get("vaccine").isEmpty()? null : vaccineService.getById(Integer.parseInt(params.get("vaccine"))),
                atService.getById(Integer.parseInt(params.get("type")))));
        
        return "";
    }
    
    @RequestMapping(value="saveModifyActivity")
    public @ResponseBody String saveModifyActivity(@RequestParam Map<String,String> params){
        Activity item = activityService.getById(Integer.parseInt(params.get("idItem")));
        item.setActive(params.get("active").equalsIgnoreCase("true")?(short)1:(short)0);
        item.setActivity(params.get("itemName"));
        item.setActivityCost(Double.parseDouble(params.get("cost")));
        item.setConsultationDefault(params.get("include").equalsIgnoreCase("true")?(short)1:(short)0);
        item.setIdActivityType(atService.getById(Integer.parseInt(params.get("type"))));
        item.setIdVaccine(params.get("vaccine").isEmpty()? null : vaccineService.getById(Integer.parseInt(params.get("vaccine"))));
        
        activityService.updateItem(item);
        return "";
    }
    
    @RequestMapping(value="getActivityRelatedInfo")
    public @ResponseBody JsonPack<Appointment> getActivityRelatedInfo(int id){
    
        List<Consultationactivity> caList = caService.getConsultationsByActivity(id);
        List<Appointment> aList = new ArrayList<Appointment>();
        
        for(Consultationactivity ca: caList){
            aList.add(ca.getConsultation().getIdAppointment());
        }
        
        return (new JsonPack<Appointment>(aList));
    }
    
     //Section: Holyday
    @RequestMapping(value="getHolyday")
    public @ResponseBody JsonPack<Holyday> getHolyday(){
        return (new JsonPack<Holyday> (holydayService.getAll("Holyday")));
    }
    
    @RequestMapping(value="saveNewHolyday")
    public @ResponseBody String saveNewHolyday(@RequestParam Map<String,String> params){
        
        
        holydayService.create(new Holyday(params.get("itemName"), Integer.parseInt(params.get("month")), Integer.parseInt(params.get("day"))));

        
        return "";
    }
    
    @RequestMapping(value="saveModifyHolyday")
    public @ResponseBody String saveModifyHolyday(@RequestParam Map<String,String> params){
        
        Holyday item = holydayService.getById( Integer.parseInt( params.get( "idItem" ) ) );
        item.setDay(Integer.parseInt( params.get( "day" ) ) );
        item.setMont(Integer.parseInt( params.get( "month" ) ) );
        item.setHolyday( params.get("itemName") );
        holydayService.updateItem( item );
        
        return "";
    }
    
    @RequestMapping(value="deleteHolyday")
    public @ResponseBody String deleteHolyday(@RequestParam Map<String,String> params){
        
        holydayService.delete(holydayService.getById(Integer.parseInt(params.get("idItem"))));
        
        return "";
    }
    
    //Section: Immunization
    @RequestMapping(value="getAllVacine")
    public @ResponseBody JsonPack<Vaccine> getAllImmunization(){
        return new JsonPack<Vaccine>(vaccineService.getAllVaccines()); 
    }
    
    @RequestMapping(value="saveNewVaccine")
    public @ResponseBody String saveNewVaccine(@RequestParam Map<String,String> params){
        Vaccine vaccine = new Vaccine(params.get("itemName"), Integer.parseInt(params.get("itemAppYear")), Integer.parseInt(params.get("itemAppMonth")),
                Integer.parseInt(params.get("itemAppDay")), Integer.parseInt(params.get("itemAppYear")),
                Integer.parseInt(params.get("itemAppMonth")), Integer.parseInt(params.get("itemAppDay")),
                params.get("multiple").equalsIgnoreCase("true")? 1 : 0,
                params.get("active").equalsIgnoreCase("true")? 1 : 0,
                vtService.getById(Integer.parseInt(params.get("type"))));
        
        vaccineService.create(vaccine);
        
        for(int i = 0; i < Integer.parseInt(params.get("cont")); i++){
            Vaccine updateV = vaccineService.getById(Integer.parseInt(params.get("eq"+i)));
            updateV.getVaccineList().add(vaccine);
            vaccineService.updateItem(updateV);
        }
        
        return "";
    }
    
    @RequestMapping(value="saveModifyVaccine")
    public @ResponseBody String saveModifyVaccine(@RequestParam Map<String,String> params){
        Vaccine vaccine = vaccineService.getById(Integer.parseInt(params.get("inputIdItem")));
        vaccine.setVaccine(params.get("itemName"));
        vaccine.setYearApply(Integer.parseInt(params.get("itemAppYear")));
        vaccine.setMonthApply(Integer.parseInt(params.get("itemAppMonth")));
        vaccine.setDayApply(Integer.parseInt(params.get("itemAppDay")));
        vaccine.setMultipleShots(params.get("multiple").equalsIgnoreCase("true")? 1 : 0);
        vaccine.setActive(params.get("active").equalsIgnoreCase("true")? 1 : 0);

        for(int i = 0; i < Integer.parseInt(params.get("cont")); i++){
            Vaccine updateV = vaccineService.getById(Integer.parseInt(params.get("eq"+i)));
            //List<Vaccine> equivalentList = .getVaccineList();
            //equivalentList.add(vaccine);
            updateV.getVaccineList().add(vaccine);
            vaccineService.updateItem(updateV);
        }

        return "";
    }
    
    @RequestMapping(value="getVaccineRelatedInfo")
    public @ResponseBody JsonPack<Patientvaccine> getVaccineRelatedInfo(int idVaccine){
        return new JsonPack<Patientvaccine>(pvService.getPatientVaccineByVaccine(idVaccine));
    }
    
    @RequestMapping(value="getPatientWOVaccine")
    public @ResponseBody JsonPack<Patient> getPatientWithoutVaccine(int idVaccine){
        return new JsonPack<Patient>(patientService.getPatientWithoutVaccine(idVaccine));
    }
    
}
