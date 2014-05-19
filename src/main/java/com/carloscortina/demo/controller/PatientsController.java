package com.carloscortina.demo.controller;

import com.carloscortina.demo.json.JsonPack;
import com.carloscortina.demo.model.Patient;
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

import com.carloscortina.demo.model.PatientRegistrationForm;
import com.carloscortina.demo.model.Patient_Relative;
import com.carloscortina.demo.model.PerBackNoPat;
import com.carloscortina.demo.model.Record;
import com.carloscortina.demo.model.Relative;
import com.carloscortina.demo.service.PatientRelativeService;
import com.carloscortina.demo.service.PatientService;
import com.carloscortina.demo.service.RecordService;
import com.carloscortina.demo.service.RelationshipService;
import com.carloscortina.demo.service.RelativeService;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Set;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value="/patients")
public class PatientsController {
	
	@Autowired
	private PatientService patientService;
        @Autowired
        private RecordService recordService;
        @Autowired
        private RelationshipService relationshipService;
        @Autowired
        private RelativeService relativeService;
	@Autowired
        private PatientRelativeService prService;
        
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
		List<Patient> patients = patientService.getAll("Patient");
		
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
			patientService.create(patient);	
			model.addAttribute("response","Paciente Agregado correctamente");
			return ( "redirect:patientHome" );
		}
		
		return ("patients/patientsRegistrationForm");
	}

	private Patient toPatient(PatientRegistrationForm form){
		Patient patient = new Patient();
		
		
		return patient;
	}
        
        @RequestMapping(value="file/{idPatient}",method= RequestMethod.GET)
        public String patientFile(Model model,@PathVariable int idPatient){
            Patient patient = patientService.getById(idPatient);
            Record record = recordService.getByPatientId(patient);
            PerBackNoPat perBackNoPat = record.getIdPerBackNoPat();
            String[] age = calculateAge(patient.getBirthday()).split("-");
            List<Relative> sibilings = new ArrayList<Relative>();
            sibilings = getSibilings(patient.getRelatives());
            
            model.addAttribute("birthday",formatDate(patient.getBirthday()));
            model.addAttribute("father",getFather(patient.getRelatives()));
            model.addAttribute("mother",getMother(patient.getRelatives()));
            model.addAttribute("age",age);
            model.addAttribute("date",getCurrentDate());
            model.addAttribute("patient",patient);
            model.addAttribute("record",record);
            model.addAttribute("perBackNoPat",perBackNoPat);
            model.addAttribute("sibilings",sibilings );
            model.addAttribute("relationshipType",relationshipService.getAll("Relationship"));
            return("patients/PatientFile");
        }
        
        
        
        //Query methods
        @RequestMapping(value="getPatientRelatives")
	public @ResponseBody JsonPack<Relative> getPatientRelatives(@RequestParam int idPatient)
	{
                Patient patient = patientService.getById(idPatient);
                List<Relative> relatives = new ArrayList();
                for(Patient_Relative r: patient.getRelatives())
                {
                    relatives.add(r.getRelative());
                }
                
		JsonPack<Relative> result = new JsonPack<Relative>(relatives);
                
		return result;
	}
        
        //Query methods
        @RequestMapping(value="getAllRelatives")
	public @ResponseBody JsonPack<Relative> getAllRelatives()
	{
		JsonPack<Relative> result = new JsonPack<Relative>(relativeService.getAllRelatives());
                
		return result;
	}
        
        //Create Query Methods
        @RequestMapping(value="addPatientFamilyRelative",method = RequestMethod.POST)
	public @ResponseBody String addPatientFamilyRelative(@RequestParam(value="idPatient")int idPatient,
                                                             @RequestParam(value="idRelative")int idRelative,
                                                             @RequestParam(value="idRelationship")int idRelationship)
	{
		Patient_Relative patientRelative = new Patient_Relative();
                patientRelative.setIdRelationship(relationshipService.getById(idRelationship));
                patientRelative.setPatient(patientService.getById(idPatient));
                patientRelative.setRelative(relativeService.getRelative(idRelative));
                

                prService.create(patientRelative);
                
		return "$('#tblPatientFamilyPatientRelatives').ajax.reload();$('#tblPatientRelativesList').ajax.reload()";
	}
        
        @RequestMapping(value="unrelateRelative")
        public @ResponseBody String unrelateRelative(@RequestParam(value="idPatient")int idPatient,
                                                     @RequestParam(value="idRelative")int idRelative,
                                                     @RequestParam(value="idRelationship")int idRelationship){
        
            Patient_Relative patientRelative = new Patient_Relative();
            patientRelative.setIdRelationship(relationshipService.getById(idRelationship));
            patientRelative.setPatient(patientService.getById(idPatient));
            patientRelative.setRelative(relativeService.getRelative(idRelative));
            
            prService.delete(patientRelative);
            
            return "$('#tblPatientFamilyPatientRelatives').ajax.reload();$('#tblPatientRelativesList').ajax.reload()";
        }
        
        //Generic Methods
        private Relative getFather(Set<Patient_Relative> relatives)
	{
		for(Patient_Relative r: relatives)
		{
			if(r.getIdRelationship().getRelationship().equals("Padre"))
			{
				return r.getRelative();
			}
		}
		
		return ( null );
	}
	
	private Relative getMother(Set<Patient_Relative> relatives)
	{
		for(Patient_Relative r: relatives)
		{
			if(r.getIdRelationship().getRelationship().equals("Madre"))
			{
				return r.getRelative();
			}
		}
		
		return ( null );
	}
        
        private List<Relative> getSibilings(Set<Patient_Relative> relatives){
		
		List<Relative> list = new ArrayList<Relative>();
		
		for(Patient_Relative r: relatives)
		{
                        
			if(r.getIdRelationship().getRelationship().equals("Hermano"))
			{
                                System.out.println("hemrano:"+r.getRelative().getFirstName());
				list.add(r.getRelative() );
			}
		}
		
		return ( list );
	}
        
        //Return current system date
	private String getCurrentDate(){
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();
		return ( format.format(date));
	}
	
        //Recives a date and formats in day month year format
	private String formatDate(Date date){
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		return ( format.format(date));
	}
	
        //This method process the current date and calculates the age in years,months and days of the pacient
	private String calculateAge(Date date){
                //The format of the date years months days
		SimpleDateFormat temp = new SimpleDateFormat("yyyy-MM-dd");
		String[] birthdayElements = temp.format(date).split("-");
                //Get current date
		Calendar cd = Calendar.getInstance();
		Calendar bd = new GregorianCalendar(Integer.parseInt(birthdayElements[0]),
                                                    Integer.parseInt(birthdayElements[1]),
                                                    Integer.parseInt(birthdayElements[2]));
		
		int month = Integer.parseInt(birthdayElements[1]);
		int day = Integer.parseInt(birthdayElements[2]);
		int ageYears, ageMonths,ageDays;
		
		
		ageYears = cd.get(Calendar.YEAR) - bd.get(Calendar.YEAR);
		if(cd.before(new GregorianCalendar(cd.get(Calendar.YEAR), month, day))){
		  ageYears--;
		  ageMonths = (12 - (bd.get(Calendar.MONTH) + 1)) + (bd.get(Calendar.MONTH));
		  if(day > cd.get(Calendar.DAY_OF_MONTH))
		  {
			  ageDays = day - cd.get(Calendar.DAY_OF_MONTH);
		  }
		  else if(day < cd.get(Calendar.DAY_OF_MONTH))
		  {
			  ageDays = cd.get(Calendar.DAY_OF_MONTH) - day;
		  }
		  else
		  {
			  ageDays = 0;
		  }
		} else if(cd.after(new GregorianCalendar(cd.get(Calendar.YEAR), month, day)))
			{
			  ageMonths = (cd.get(Calendar.MONTH) - (bd.get(Calendar.MONTH)-1));
			  if(day > cd.get(Calendar.DAY_OF_MONTH))
				  ageDays = day - cd.get(Calendar.DAY_OF_MONTH) - day;
		  		else if(day < cd.get(Calendar.DAY_OF_MONTH)){
		  			ageDays = cd.get(Calendar.DAY_OF_MONTH) - day;
		  		} else
		  			ageDays = 0;
			}
		  else{
			  ageYears = cd.get(Calendar.YEAR) - bd.get(Calendar.YEAR);
			  ageMonths = 0;
			  ageDays = 0;
		  }
		  return (ageYears + "-" + ageMonths + "-" + ageDays);
	}
}
