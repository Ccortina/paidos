/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.carloscortina.demo.controller;

import com.carloscortina.demo.json.JsonPack;
import com.carloscortina.demo.model.Cie10doctor;
import com.carloscortina.demo.model.Cie10doctorPK;
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
import java.util.ArrayList;
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
    
    @RequestMapping(value="getDrugs")
    public @ResponseBody JsonPack<Drug> getDrugs()
    {
        return new JsonPack<Drug>(drugService.getAllActiveDrugBasicInfo());
    }

    @RequestMapping(value="addCieToUserCatalog")
    public @ResponseBody String addCieToUserCatalog(int idCie){
        Cie10doctorPK id = new Cie10doctorPK(idCie, loggedUser.getIdUser());
        Cie10doctor add = new Cie10doctor(id);
        cieDoctorService.create(add);
        
        return "";
    }
    
    @RequestMapping(value="removeCieFromUserCatalog")
    public @ResponseBody String removeCieFromUserCatalog(int idCie){
        Cie10doctorPK id = new Cie10doctorPK(idCie, loggedUser.getIdUser());
        Cie10doctor add = new Cie10doctor(id);
        cieDoctorService.delete(add);
        
        return "";
    }
    
    @RequestMapping(value="saveNewTreatment")
    public @ResponseBody String saveNewTreatment(@RequestParam Map<String,String> params){
        Treatment newTreatment = new Treatment();
        newTreatment.setActive(params.get("active").compareTo("true") == 0? 1:0);
        newTreatment.setTreatment(params.get("treatment"));
        newTreatment.setDirections(params.get("directions"));
        
        treatmentService.create(newTreatment);
        
        for(int i=0; i < Integer.parseInt(params.get("diagnosticCont")); i++){
            Cie10 up = cieService.getById(Integer.parseInt(params.get("diagnostic"+i)));
            up.getTreatmentList().add(newTreatment);
            cieService.updateItem(up);
        }
        
        for(int i=0; i < Integer.parseInt(params.get("drugCont")); i++){
            Drug up= drugService.getById(Integer.parseInt(params.get("drug"+i)));
            up.getTreatmentList().add(newTreatment);
            drugService.updateItem(up);
        }
        
        return "";
    }
    
    @RequestMapping(value="saveModifyTreatment")
    public @ResponseBody String saveModifyTreatment(@RequestParam Map<String,String> params){
        Treatment newTreatment = treatmentService.getById(Integer.parseInt(params.get("idTreatment")));
        
        newTreatment.setActive(params.get("active").compareTo("true") == 0? 1:0);
        newTreatment.setTreatment(params.get("treatment"));
        newTreatment.setDirections(params.get("directions"));
        
        treatmentService.updateItem(newTreatment);
        
        /*Treatment doesnt have the list , so it is necessary to know wich
        diagnostic and drugs where removed from the asociation and
        delete the current drug on each one.
        */
        List<Cie10> actualCieList = newTreatment.getCie10List();    //Current Cie10 diagnostics related to the treatment
        List<Drug> actualDrugList = newTreatment.getDrugList();     //Current drugs related to the treatment
        
        List<Cie10> modifiedCieList = new ArrayList<Cie10>();   //The modified cie10 diagnostic list
        List<Drug> modifiedDrugList = new ArrayList<Drug>();    //The modified drug list
        
        for(int i=0; i < Integer.parseInt(params.get("diagnosticCont")); i++){
            modifiedCieList.add(cieService.getById(Integer.parseInt(params.get("diagnostic"+i))));
        }
        
        for(int i=0; i < Integer.parseInt(params.get("drugCont")); i++){
            modifiedDrugList.add(drugService.getById(Integer.parseInt(params.get("drug"+i))));
        }
        
        /*Remove repeated element from the current lists
        this way only the new and the deleted elements will remain
        */
        actualCieList.removeAll(modifiedCieList);
        actualDrugList.removeAll(modifiedDrugList);
        
        //Delete all the extra elements , the new elements will not be affected
        //as they dont hold any relationship yet
        for(Cie10 cie: actualCieList){
            cie.getTreatmentList().remove(newTreatment);
            cieService.updateItem(cie);
        }
        
        for(Drug drug: actualDrugList){
            drug.getTreatmentList().remove(newTreatment);
            drugService.updateItem(drug);
        }
        
        //Reload the current relationships
        actualCieList = treatmentService.getById(Integer.parseInt(params.get("idTreatment"))).getCie10List();
        actualDrugList = treatmentService.getById(Integer.parseInt(params.get("idTreatment"))).getDrugList();
        
        //Remove the elements already on the list 
        modifiedCieList.removeAll(actualCieList);
        modifiedDrugList.removeAll(actualDrugList);
        
        //add the new relationship 
        for(Cie10 cie: modifiedCieList){
            cie.getTreatmentList().add(newTreatment);
            cieService.updateItem(cie);
        }
        
        for(Drug drug: modifiedDrugList){
            drug.getTreatmentList().add(newTreatment);
            drugService.updateItem(drug);
        }
        
        return "";
    }
    
    @RequestMapping(value="removeTreatment")
    public @ResponseBody String removeTreatment(int idTreatment){
        Treatment treatment = treatmentService.getById(idTreatment);

        treatmentService.updateItem(treatment);
        
        return "";
    }
    
    @RequestMapping(value="removeCieOfUserCatalog")
    public @ResponseBody String removeCieOfUserCatalog(int idCie){
        Cie10doctorPK id = new Cie10doctorPK(idCie, loggedUser.getIdUser());
        Cie10doctor add = new Cie10doctor(id);
        cieDoctorService.delete(add);
        
        return "";
    }
    
    @RequestMapping(value="getConsultationByCie")
    public @ResponseBody JsonPack<Consultation> getConsultationByCie(int idCie){
        
        return (new JsonPack<Consultation>(consultationService.getConsultationByCie(idCie)));
    }
    
    @RequestMapping(value="getConsultationByTreatment")
    public @ResponseBody JsonPack<Consultation> getConsultationByTreatment(int idTreatment){
        
        return (new JsonPack<Consultation>(consultationService.getConsultationByTreatment(idTreatment)));
    }
    
    @RequestMapping(value="getAllTreatments")
    public @ResponseBody JsonPack<Treatment> getTreatments()
    {   
        return new JsonPack<Treatment>(treatmentService.getAll(""));
    }
    
    @RequestMapping(value="getDrugsByTreatment")
    public @ResponseBody JsonPack<Drug> getDrugsByTreatment(int idTreatment)
    {   
        return new JsonPack<Drug>(drugService.getDrugByTreatment(idTreatment));
    }
    
    @RequestMapping(value="getAvaibleDrugsByTreatment")
    public @ResponseBody JsonPack<Drug> geAvaibleDrugsByTreatment(int idTreatment)
    {   
        List<Drug> allDrugs = drugService.getAllActiveDrugBasicInfo();
        
        allDrugs.removeAll(drugService.getDrugByTreatment(idTreatment));
        return new JsonPack<Drug>(allDrugs);
    }
    
    @RequestMapping(value="getDiagnosticByTreatment")
    public @ResponseBody JsonPack<Cie10> getDiagnosticByTreatment(int idTreatment)
    {   
        return new JsonPack<Cie10>(cieService.getCie10ByTreatmentAndUser(loggedUser.getIdUser(), idTreatment));
    }
    
    @RequestMapping(value="getAvaibleDiagnosticByTreatment")
    public @ResponseBody JsonPack<Cie10> getAvaibleDiagnosticByTreatment(int idTreatment)
    {   
        List<Cie10> all = cieService.getCie10ByUser(loggedUser.getIdUser());
        all.removeAll(cieService.getCie10ByTreatmentAndUser(loggedUser.getIdUser(), idTreatment));
        return (new JsonPack<Cie10>(all));
    }
}
