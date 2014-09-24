/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.carloscortina.demo.controller;

import com.carloscortina.demo.json.JsonPack;
import com.carloscortina.demo.model.Administrationunit;
import com.carloscortina.demo.model.Applicationmethod;
import com.carloscortina.demo.model.Commercialname;
import com.carloscortina.demo.model.Drug;
import com.carloscortina.demo.model.Drugdose;
import com.carloscortina.demo.model.Drugpresentation;
import com.carloscortina.demo.model.Drugrisk;
import com.carloscortina.demo.model.DrugriskPK;
import com.carloscortina.demo.model.Incompatibledrugs;
import com.carloscortina.demo.model.IncompatibledrugsPK;
import com.carloscortina.demo.model.Treatment;
import com.carloscortina.demo.model.User;
import com.carloscortina.demo.service.AdministrationUnitService;
import com.carloscortina.demo.service.ApplicationMethodService;
import com.carloscortina.demo.service.CommercialNameService;
import com.carloscortina.demo.service.DoseCalculationCriteriaService;
import com.carloscortina.demo.service.DrugDoseService;
import com.carloscortina.demo.service.DrugRiskService;
import com.carloscortina.demo.service.DrugService;
import com.carloscortina.demo.service.IncompatibleDrugsService;
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
    private IncompatibleDrugsService incompatibleService;
    @Autowired
    private DrugRiskService drService;
     
    private User loggedUser;
    
    @RequestMapping(value="drugHome")
    public String drugHome(Model model){
        //Get logged User
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        loggedUser = userService.getUserByUsername(auth.getName());
        
        model.addAttribute("drugPresentations",drugPresentationService.getAll("Drugpresentation"));
        model.addAttribute("applicationMethods", applicationMethodService.getAll("Applicationmethod"));
        model.addAttribute("administrationUnits", administrationUnitService.getAll("Administrationunit"));
        model.addAttribute("doseCalculationCriterias", doseCalculationService.getAll("Dosecalculationcriteria"));
        
        return ( "Drug/DrugHome" );
    }
    
    @RequestMapping(value="drugPresentationHome")
    public String drugPresentationHome(Model model){
        //Get logged User
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        loggedUser = userService.getUserByUsername(auth.getName());
        
        return ( "Drug/DrugPresentation" );
    }
    
    @RequestMapping(value="drugApplicationMethodHome")
    public String drugApplicationMethodHome(Model model){
        //Get logged User
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        loggedUser = userService.getUserByUsername(auth.getName());
        
        return ( "Drug/ApplicationMethodHome" );
    }
    
    @RequestMapping(value="drugAdministrationunitHome")
    public String drugAdministrationUnitHome(Model model){
        //Get logged User
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        loggedUser = userService.getUserByUsername(auth.getName());
        
        return ( "Drug/AdministrationUnitsHome" );
    }
    
    @RequestMapping(value="getDrugByUser")
    public @ResponseBody JsonPack<Drug> getDrugByUser(){
        return (new JsonPack<Drug>(drugService.getDrugByUser(loggedUser.getIdUser())));
    }
    
    @RequestMapping(value="getDrugsCommercialNames")
    public @ResponseBody JsonPack<Commercialname> allDrugCommercialNames()
    {
        return new JsonPack<Commercialname>(commercialNameService.getCommercialNameByUser(loggedUser.getIdUser()));
    }
    
    @RequestMapping(value="getTreatmentsByUser")
    public @ResponseBody JsonPack<Treatment> getTreatmentsByUser()
    {   
        return new JsonPack<Treatment>(treatmentService.getTreatmentByUser(loggedUser.getIdUser()));
    }
    
    @RequestMapping(value="saveNewDrug")
    public @ResponseBody String saveNewDrug(@RequestParam Map<String,String> params){

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
            
            List<Treatment> treatmentList = new ArrayList<Treatment>();
            
            for(int i = 0 ; i < Integer.parseInt(params.get("tCont")); i++){
                treatmentList.add(treatmentService.getById(Integer.parseInt(params.get("treatment"+i))));
            }
            newDrug.setTreatmentList(treatmentList);
            
            drugService.create(newDrug);
            
            //Relate new drug incompatibilities
            for(int i = 0 ; i < Integer.parseInt(params.get("iCont")); i++){
                Commercialname cn = commercialNameService.getById(Integer.parseInt(params.get("incompatibleCN"+i)));
                IncompatibledrugsPK incompatiblePK = new IncompatibledrugsPK(newDrug.getIdDrug(), cn.getIdcommercialName());
                Incompatibledrugs incompatible = new Incompatibledrugs(incompatiblePK);

                incompatibleService.create(incompatible);
            }
            
            //Realte new drug risks qith incompatible drugs
            for(int i =0; i < Integer.parseInt(params.get("rCont"));i++){
                DrugriskPK dr = new DrugriskPK(newDrug.getIdDrug(), Integer.parseInt(params.get("riskDrug"+i)));
                Drugrisk risk = new Drugrisk(dr, params.get("risk"+i));
                
                drService.create(risk);
            }
                    
            //Add Drug dose
            for(int i = 0 ; i < Integer.parseInt(params.get("doseCont")); i++){
                Drugdose dd = new Drugdose();
                switch(Integer.parseInt(params.get("doseCalculationCriteriaId"))){
                    case 4:
                            dd = new Drugdose(0, Float.parseFloat(params.get("dose"+i)), newDrug);
                        break;
                    case 5:
                            dd = new Drugdose(Integer.parseInt(params.get("criteria"+i)), Float.parseFloat(params.get("dose"+i)), newDrug);
                        break;
                }
                /*if(params.get("doseCalculationCriteriaId").isEmpty()){
                    dd = new Drugdose(0, Float.parseFloat(params.get("dose"+i)), newDrug);
                }else{
                    dd = new Drugdose(Integer.parseInt(params.get("criteria"+i)), Float.parseFloat(params.get("dose"+i)), newDrug);
                }*/
                drugDoseService.create(dd);
            }
            
            //Create new Drug commercial names
            for(int i = 0 ; i < Integer.parseInt(params.get("cnCont")); i++){
                Commercialname ncn = new Commercialname(params.get("commercialName"+i),  newDrug);
                commercialNameService.create(ncn);
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return "";
    }
    
    @RequestMapping(value="getDrugPresentation")
    public @ResponseBody JsonPack<Drugpresentation> getDrugPresentation(){
        return (new JsonPack<Drugpresentation> (drugPresentationService.getAll("Drugpresentation")));
    }
    
    @RequestMapping(value="saveNewDrugPresentation")
    public @ResponseBody String saveNewDrugPresentation(@RequestParam Map<String,String> params){
        
        drugPresentationService.create(new Drugpresentation(params.get("presentation"), params.get("active").equalsIgnoreCase("true")?1:0));
        
        return "";
    }
    
    @RequestMapping(value="saveModifyDrugPresentation")
    public @ResponseBody String saveModifyDrugPresentation(@RequestParam Map<String,String> params){
        Drugpresentation modifyDP = drugPresentationService.getById(Integer.parseInt(params.get("idPresentation")));
        modifyDP.setActive(params.get("active").equalsIgnoreCase("true")? 1 : 0 );
        modifyDP.setPresentation(params.get("presentation"));
        
        drugPresentationService.updateItem(modifyDP);
        
        return "";
    }
    
    @RequestMapping(value="getDrugPresentationRelatedInfo")
    public @ResponseBody JsonPack<Drug> getDrugPresentationRelatedInfo(int dpId){
    
        return (new JsonPack<Drug>(drugService.getDrugByPresentationAndUser(dpId,loggedUser.getIdUser())));
    }
    
    @RequestMapping(value="getDrugApplicationMethod")
    public @ResponseBody JsonPack<Applicationmethod> getDrugAdministrationMethod(){
        return (new JsonPack<Applicationmethod> (applicationMethodService.getAll("ApplicationMethod")));
    }
    
    @RequestMapping(value="saveNewDrugApplicationMethod")
    public @ResponseBody String saveNewDrugApplicationMethod(@RequestParam Map<String,String> params){
        
        applicationMethodService.create(new Applicationmethod(params.get("applicationMethod"), params.get("active").equalsIgnoreCase("true")? 1 : 0));
        
        return "";
    }
    
    @RequestMapping(value="saveModifyDrugApplicationMethod")
    public @ResponseBody String saveModifyDrugApplicationMethod(@RequestParam Map<String,String> params){
        Applicationmethod modifyAM = applicationMethodService.getById(Integer.parseInt(params.get("idApplicationMethod")));
        modifyAM.setActive(params.get("active").equalsIgnoreCase("true")? 1 : 0 );
        modifyAM.setApplicationMethod(params.get("applicationMethod"));
        
        applicationMethodService.updateItem(modifyAM);
        
        return "";
    }
    
    @RequestMapping(value="getDrugApplicationMethodRelatedInfo")
    public @ResponseBody JsonPack<Drug> getDrugApplicationMethodRelatedInfo(int dpId){
    
        return (new JsonPack<Drug>(drugService.getDrugByApplicationMethodAndUser(dpId, loggedUser.getIdUser())));
    }
    
    //Administration Unit section
    
    @RequestMapping(value="getDrugAdministrationUnit")
    public @ResponseBody JsonPack<Administrationunit> getDrugAdministrationUnit(){
        return (new JsonPack<Administrationunit> (administrationUnitService.getAll("Administrationunit")));
    }
    
    @RequestMapping(value="saveNewDrugAdministrationUnit")
    public @ResponseBody String saveNewDrugAdministrationUnit(@RequestParam Map<String,String> params){
        
        administrationUnitService.create(new Administrationunit(params.get("administrationUnit"), params.get("active").equalsIgnoreCase("true")? 1: 0));
        
        return "";
    }
    
    @RequestMapping(value="saveModifyDrugAdministrationUnit")
    public @ResponseBody String saveModifyDrugAdministrationUnit(@RequestParam Map<String,String> params){
        Administrationunit modifyAU = administrationUnitService.getById(Integer.parseInt(params.get("idAdministrationUnit")));
        modifyAU.setActive(params.get("active").equalsIgnoreCase("true")?(short)1:(short)0);
        modifyAU.setAdministrationUnit(params.get("administrationUnit"));
        
        administrationUnitService.updateItem(modifyAU);
        
        return "";
    }
    
    @RequestMapping(value="getDrugAdministrationUnitRelatedInfo")
    public @ResponseBody JsonPack<Drug> getDrugAdministrationUnitRelatedInfo(int auId){
    
        return (new JsonPack<Drug>(drugService.getDrugByAdministrationUnitAndUser(auId, loggedUser.getIdUser())));
    }
}
