/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.carloscortina.demo.controller;

import com.carloscortina.demo.json.JsonPack;
import com.carloscortina.demo.model.CommercialName;
import com.carloscortina.demo.model.Drug;
import com.carloscortina.demo.model.DrugDose;
import com.carloscortina.demo.model.DrugPresentation;
import com.carloscortina.demo.model.Drugrisk;
import com.carloscortina.demo.model.DrugriskPK;
import com.carloscortina.demo.model.Treatment;
import com.carloscortina.demo.model.User;
import com.carloscortina.demo.service.AdministrationUnitService;
import com.carloscortina.demo.service.ApplicationMethodService;
import com.carloscortina.demo.service.CommercialNameService;
import com.carloscortina.demo.service.DoseCalculationCriteriaService;
import com.carloscortina.demo.service.DrugDoseService;
import com.carloscortina.demo.service.DrugService;
import com.carloscortina.demo.service.DrugriskService;
import com.carloscortina.demo.service.ServiceDrugPresentation;
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
    @Autowired
    private CommercialNameService commercialNameService;
    @Autowired
    private TreatmentService treatmentService;
    @Autowired
    private DrugDoseService drugDoseService;
    @Autowired
    private DrugriskService drugriskService;
     
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
    
    @RequestMapping(value="drugPresentationHome")
    public String drugPresentationHome(Model model){
        //Get logged User
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        loggedUser = userService.getUserByUsername(auth.getName());
        
        return ( "Drug/DrugPresentation" );
    }
    
    @RequestMapping(value="getDrugByUser")
    public @ResponseBody JsonPack<Drug> getDrugByUser(){
        return (new JsonPack<Drug>(drugService.getDrugByUser(loggedUser.getIdUser())));
    }
    
    @RequestMapping(value="getDrugsCommercialNames")
    public @ResponseBody JsonPack<CommercialName> allDrugCommercialNames()
    {
        return new JsonPack<CommercialName>(commercialNameService.getCommercialNameByUser(loggedUser.getIdUser()));
    }
    
    @RequestMapping(value="getTreatmentsByUser")
    public @ResponseBody JsonPack<Treatment> getTreatmentsByUser()
    {   
        return new JsonPack<Treatment>(treatmentService.getTreatmentByUser(loggedUser.getIdUser()));
    }
    
    @RequestMapping(value="saveNewDrug")
    public @ResponseBody String saveNewDrug(@RequestParam Map<String,String> params){
        List<User> userList = new ArrayList<User>();
        userList.add(loggedUser);
        
        try{
            Drug newDrug = new Drug();
            newDrug.setActive(params.get("active").equalsIgnoreCase("true")? (short)1: (short)0);
            newDrug.setDrug(params.get("drug"));
            newDrug.setDrugPresentationId(drugPresentationService.getById(Integer.parseInt(params.get("drugPresentationId"))));
            newDrug.setApplicationMethodId(applicationMethodService.getById(Integer.parseInt(params.get("applicationMethodId"))));
            newDrug.setAdministrationUnitId(administrationUnitService.getById(Integer.parseInt(params.get("administrationUnitId"))));
            newDrug.setDailyFrequency(Integer.parseInt(params.get("dailyFrequency")));
            newDrug.setTreatmentDays(Integer.parseInt(params.get("treatmentDays")));
            newDrug.setApplicationSchedule(params.get("applicationSchedule"));
            newDrug.setDoseCalculationCriteriaId(doseCalculationService.getById(Integer.parseInt(params.get("doseCalculationCriteriaId"))));
            newDrug.setNotes(params.get("notes"));
            newDrug.setConcentration(Double.parseDouble(params.get("concentration")));
            newDrug.setUserList(userList);
            List<Treatment> treatmentList = new ArrayList<Treatment>();
            
            for(int i = 0 ; i < Integer.parseInt(params.get("tCont")); i++){
                treatmentList.add(treatmentService.getById(Integer.parseInt(params.get("treatment"+i))));
            }
            newDrug.setTreatmentList(treatmentList);
            
            drugService.create(newDrug);
            
            for(int i = 0 ; i < Integer.parseInt(params.get("iCont")); i++){
                CommercialName cn = commercialNameService.getById(Integer.parseInt(params.get("incompatibleCN"+i)));
                List<Drug> incompatible = cn.getIncompatibleDrugList();
                incompatible.add(newDrug);
                cn.setIncompatibleDrugList(incompatible);
                commercialNameService.updateItem(cn);
            }
                    
            //Add Drug dose
            for(int i = 0 ; i < Integer.parseInt(params.get("doseCont")); i++){
                DrugDose dd = new DrugDose();
                if(params.get("criteria"+i).isEmpty()){
                    dd = new DrugDose(0, Float.parseFloat(params.get("dose"+i)), newDrug);
                }else{
                    dd = new DrugDose(Integer.parseInt(params.get("criteria"+i)), Float.parseFloat(params.get("dose"+i)), newDrug);
                }
                drugDoseService.create(dd);
            }
            
            for(int i = 0 ; i < Integer.parseInt(params.get("cnCont")); i++){
                CommercialName ncn = new CommercialName(params.get("commercialName"+i), (short)1, userList, newDrug);
                commercialNameService.create(ncn);
            }
            
            for(int i = 0 ; i < Integer.parseInt(params.get("rCont")); i++){
                DrugriskPK drPK = new DrugriskPK(newDrug.getIdDrug(), Integer.parseInt(params.get("riskDrug"+i)));
                Drugrisk dr = new Drugrisk(drPK);
                dr.setRisk(params.get("risk"+i));
                drugriskService.create(dr);
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return "";
    }
    
    @RequestMapping(value="getDrugPresentation")
    public @ResponseBody JsonPack<DrugPresentation> getDrugPresentation(){
        return (new JsonPack<DrugPresentation> (drugPresentationService.getAll("DrugPresentation")));
    }
    
    @RequestMapping(value="saveNewDrugPresentation")
    public @ResponseBody String saveNewDrugPresentation(@RequestParam Map<String,String> params){
        
        drugPresentationService.create(new DrugPresentation(params.get("presentation"), params.get("active").equalsIgnoreCase("true")?(short)1:(short)0));
        
        return "";
    }
    
    @RequestMapping(value="saveModifyDrugPresentation")
    public @ResponseBody String saveModifyDrugPresentation(@RequestParam Map<String,String> params){
        DrugPresentation modifyDP = drugPresentationService.getById(Integer.parseInt(params.get("idPresentation")));
        modifyDP.setActive(params.get("active").equalsIgnoreCase("true")?(short)1:(short)0);
        modifyDP.setPresentation(params.get("presentation"));
        
        drugPresentationService.updateItem(modifyDP);
        
        return "";
    }
    
}
