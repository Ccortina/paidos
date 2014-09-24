package com.carloscortina.demo.controller;

import com.carloscortina.demo.json.JsonPack;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.RequestMapping;


import com.carloscortina.demo.model.Relative;
import com.carloscortina.demo.model.Religion;
import com.carloscortina.demo.model.User;
import com.carloscortina.demo.model.forms.RelativeRegistrationForm;
import com.carloscortina.demo.service.RelativeService;
import com.carloscortina.demo.service.ReligionService;
import com.carloscortina.demo.service.UserService;
import java.lang.reflect.Method;
import java.util.Map;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value="/relatives")
public class RelativesController {

	@Autowired
	private RelativeService relativeService; 
	@Autowired
	private ReligionService religionService;
        @Autowired
        private UserService userService;
	
        private User loggedUser;
        
	@RequestMapping(value="home")
	public String getRelativesMainPage(Model model){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
                String currentPrincipalName = authentication.getName();
                loggedUser = userService.getUserByUsername(currentPrincipalName);
                model.addAttribute("religions",religionService.getAllReligions());
		return ("relatives/relativesHome");
	}
        
        @RequestMapping(value="getAllRelatives")
        public @ResponseBody JsonPack<Relative> getAllRelatives(){

            return new JsonPack<Relative>(relativeService.getAllRelatives());
        }
        
        @RequestMapping(value="saveNewRelative",produces = "application/json")
        public @ResponseBody Relative saveNewRelative(@RequestParam Map<String,String> params){
            Relative relative = new Relative();
            
            //Travel the map entries
            for (Map.Entry<String, String> entry : params.entrySet()) {
                //Fill relative data
                try{
                    for(Method m: relative.getClass().getMethods()){
                        if(m.getName().startsWith("set")){
                            if( m.getName().equalsIgnoreCase("set"+entry.getKey())){
                                if(!entry.getValue().isEmpty()){
                                    if( m.getName().equalsIgnoreCase("setReligion")){
                                        m.invoke(relative,religionService.getReligion(Integer.parseInt(entry.getValue())));
                                    }else{
                                        if( m.getName().equalsIgnoreCase("setActive") ){
                                            m.invoke( relative,entry.getValue().equalsIgnoreCase("true")? 1 : 0 );
                                        }else{
                                            m.invoke( relative,entry.getValue() );
                                        }
                                    }
                                }
                            }
                        }
                    }
                    relative.setAddedDate(new java.util.Date());
                    relativeService.createRelative(relative);
                }catch(Exception e){ 
                    e.printStackTrace();
                }
            }
            
            return (relative);
        }
	
        @RequestMapping(value="saveModifyRelative",produces = "application/json")
        public @ResponseBody Relative saveModifyRelative(@RequestParam Map<String,String> params){
            Relative relative = relativeService.getRelative(Integer.parseInt(params.get("idRelative")));
            
            //Travel the map entries
            for (Map.Entry<String, String> entry : params.entrySet()) {
                //Fill relative data
                try{
                    for(Method m: relative.getClass().getMethods()){
                        if(m.getName().startsWith("set")){
                            if( m.getName().equalsIgnoreCase("set"+entry.getKey())){
                                if( m.getName().equalsIgnoreCase("setReligion")){
                                    m.invoke(relative,religionService.getReligion(Integer.parseInt( entry.getValue() )));
                                }else if( !m.getName().equalsIgnoreCase("setIdRelative")){
                                    if( m.getName().equalsIgnoreCase("setActive") ){
                                        m.invoke( relative,entry.getValue().equalsIgnoreCase("true")? 1 : 0 );
                                    }else{
                                        m.invoke( relative,entry.getValue() );
                                    }
                                }    
                            }
                        }
                    }
                    relativeService.updateRelative(relative);
                }catch(Exception e){ 
                    e.printStackTrace();
                }
            }
            
            return (relative);
        }
        
	private Relative toRelative(RelativeRegistrationForm form){
		
		Religion religion = religionService.getReligion(1);
		
		Relative newRelative = new Relative();
		newRelative.setFirstName(form.getFirstName());
		newRelative.setFatherLastName(form.getFatherLastName());
		newRelative.setMotherLastName(form.getMotherLastName());
		newRelative.setCurp(form.getCurp());
		newRelative.setCellPhone(form.getCellPhone());
		newRelative.setEmail(form.getEmail());
		newRelative.setGinecologist(form.getGinecologist());
		newRelative.setHomePhone(form.getHomePhone());
		newRelative.setOfficePhone(form.getOfficePhone());
		newRelative.setOfficeExt(form.getOfficeExt());
		newRelative.setNotes(form.getNotes());
		newRelative.setOccupation(form.getOccupation());
		newRelative.setRfc(form.getRfc());
		newRelative.setStreet(form.getStreet());
		newRelative.setNumber(form.getNumber());
		newRelative.setColony(form.getColony());
		newRelative.setCity(form.getCity());
		newRelative.setState(form.getState());
		newRelative.setCountry(form.getCountry());
		newRelative.setCp(form.getCp());
		//newRelative.setr(religion);
		
		return newRelative;
	}
        
        
}
