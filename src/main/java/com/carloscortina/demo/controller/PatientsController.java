package com.carloscortina.demo.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.carloscortina.demo.model.Patient;
import com.carloscortina.demo.model.PatientRegistrationForm;
import com.carloscortina.demo.service.PatientService;

@Controller
@RequestMapping(value="/patients")
public class PatientsController {
	
	@Autowired
	private PatientService patientService;
	
	@InitBinder
	public void initBinder(WebDataBinder binder)
	{
		binder.setAllowedFields(
				new String[]
				{
					"username","password","confirmPassword","email",
					"firstName","lastName","phone","cellPhone",
					"professionalNumber","role"
				});
	}
	
	@RequestMapping(value="home")
	public String listAllPatients(Model model){
		List<Patient> patients = patientService.getPatients();
		
		//for(Relative r : patients.get(0).getRelatives())
		    //System.out.println(r.getFirstName());
		
		model.addAttribute("patients",patients);
		return ( "patients/patientHome" );
	}
	
	@RequestMapping(value="new",method=RequestMethod.GET)
	public String loadRegistrationForm(Model model){
		
		model.addAttribute("form", new PatientRegistrationForm());
		return ("patients/patientsRegistrationForm");
	}
	
	@RequestMapping(value="addNew",method=RequestMethod.POST)
	public String processRegistrationForm(@ModelAttribute("form") @Valid PatientRegistrationForm form, 
				BindingResult result,Model model){
		
		if (!result.hasErrors())	// The validation was correct?
		{
			Patient patient = toPatient(form);
			patientService.createPatient(patient);	
			model.addAttribute("response","Paciente Agregado correctamente");
			return ( "redirect:patientHome" );
		}
		
		return ("patients/patientsRegistrationForm");
	}

	private Patient toPatient(PatientRegistrationForm form){
		Patient patient = new Patient();
		
		
		return patient;
	}
}
