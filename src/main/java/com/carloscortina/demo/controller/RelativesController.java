package com.carloscortina.demo.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.carloscortina.demo.model.Relative;
import com.carloscortina.demo.model.Religion;
import com.carloscortina.demo.model.forms.RelativeRegistrationForm;
import com.carloscortina.demo.service.RelativeService;
import com.carloscortina.demo.service.ReligionService;

@Controller
@RequestMapping(value="/relatives")
public class RelativesController {

	@Autowired
	private RelativeService relativeService; 
	
	@Autowired
	private ReligionService religionService;
	
	@RequestMapping(value="home")
	public String getRelativesMainPage(Model model){
		List<Relative> relatives = relativeService.getAllRelatives();
		model.addAttribute("relatives",relatives);
		
		return ("relatives/relativesHome");
	}
	
	@RequestMapping(value="new",method=RequestMethod.GET)
	public String getRelativeRegistrationForm(Model model){
		
		model.addAttribute("form",new RelativeRegistrationForm());
		
		return ("relatives/relativeRegistrationForm");
	}
	
	@RequestMapping(value="add",method=RequestMethod.POST)
	public String addNewRelative(@ModelAttribute("form") @Valid RelativeRegistrationForm form, 
			BindingResult result,Model model){
		
		if (!result.hasErrors())	// The validation was correct?
		{
			Relative relative = toRelative(form);
			relativeService.createRelative(relative);	
			model.addAttribute("addedRelative","Familiar Agregado Correctamente");
			return ( "redirect:/relatives/home" );
		}
		
		return ("relatives/relativeRegistrationForm");
	}
	
	private Relative toRelative(RelativeRegistrationForm form){
		
		Religion religion = religionService.getReligion(1);
		
		Relative newRelative = new Relative();
		newRelative.setFirstName(form.getFirstName());
		newRelative.setSecondName(form.getSecondName());
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
		newRelative.setReligion(religion);
		
		return newRelative;
	}
}
