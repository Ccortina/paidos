package com.carloscortina.demo.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.carloscortina.demo.json.JsonPack;
import com.carloscortina.demo.model.Appointment;
import com.carloscortina.demo.model.Cie10;
import com.carloscortina.demo.model.CommercialName;
import com.carloscortina.demo.model.Drug;
import com.carloscortina.demo.model.Patient;
import com.carloscortina.demo.model.Patient_Relative;
import com.carloscortina.demo.model.PerBackNoPat;
import com.carloscortina.demo.model.Record;
import com.carloscortina.demo.model.Relative;
import com.carloscortina.demo.model.Treatment;
import com.carloscortina.demo.service.AppointmentService;
import com.carloscortina.demo.service.Cie10Service;
import com.carloscortina.demo.service.CommercialNameService;
import com.carloscortina.demo.service.DrugService;
import com.carloscortina.demo.service.PatientService;
import com.carloscortina.demo.service.PerBackNoPatService;
import com.carloscortina.demo.service.RecordService;
import com.carloscortina.demo.service.RelativeService;
import com.carloscortina.demo.service.TreatmentService;


@Controller
@RequestMapping(value="/consultation")
public class ConsultationController {

	@Autowired
	private AppointmentService appointmentService;
	@Autowired
	private PatientService patientService;
	@Autowired
	private RelativeService relativesService;
	@Autowired
	private RecordService recordService;
	@Autowired
	private PerBackNoPatService perBackNoService;
	@Autowired
	private Cie10Service cie10Service;
	@Autowired
	private TreatmentService treatmentService;
        @Autowired
	private DrugService drugService;
        @Autowired
        private CommercialNameService commercialNameService;
        
	
	@RequestMapping(value="appointments")
	public String loadAppointments(Model model){
		
		return ("consultation/appointments");
	}
	
	
	@RequestMapping(value="")
	public String startConsultation(Model model){
		int IdAppointment = 1;
		Appointment appointment = appointmentService.getById(IdAppointment);
		Patient patient = patientService.getPatientById(appointment.getIdPatient().getId());
		Record record = recordService.getByPatientId(patient);
		PerBackNoPat perBackNoPat = record.getIdPerBackNoPat();
		
		model.addAttribute("father",getFather(patient.getRelatives()));
		model.addAttribute("mother",getMother(patient.getRelatives()));
		
		List<Relative> brothers = new ArrayList<Relative>();
		brothers = getBrothers(patient.getRelatives());
		model.addAttribute("hermanos",brothers);
		
		model.addAttribute("birthday",formatDate(patient.getBirthday()));
		String[] age = calculateAge(patient.getBirthday()).split("-");
		
		model.addAttribute("age",age);
		model.addAttribute("date",getCurrentDate());
		model.addAttribute("patient",patient);
		model.addAttribute("appointment",appointment);
		model.addAttribute("record",record);
		model.addAttribute("perBackNoPat",perBackNoPat);
		
		return ( "consultation/consultation" );
	}
	
	//Updates the No Pathological perinatal background table individually 
	@RequestMapping(value="savePerBackNoPat",method=RequestMethod.POST)
	public @ResponseBody String savePerBackNoPat(@ModelAttribute(value="changes") PerBackNoPat changes, BindingResult result){
		perBackNoService.updateItem(changes);
		String message = " <div class='alert alert-success alert-dismissable'> " +
								"<button type='button' class='close' data-dismiss='alert' aria-hidden='true'>&times;</button>" +
								"<div>Se han guardado los cambios</div>" +
						  "</div>";
		return message;
	}
	
	//Update record alergic background field
	@RequestMapping(value="saveAlergicBackground",method=RequestMethod.POST)
	public @ResponseBody String saveAlergicBackground(@ModelAttribute(value="changes") Record changes, BindingResult result){
		Record currentRecord = recordService.getById(changes.getIdRecord());
		currentRecord.setAlergicBackground(changes.getAlergicBackground());
		recordService.updateItem(currentRecord);
		String message = " <div class='alert alert-success alert-dismissable'> " +
								"<button type='button' class='close' data-dismiss='alert' aria-hidden='true'>&times;</button>" +
								"<div>Se han guardado los cambios</div>" +
						  "</div>";
		return message;
	}
	
	@RequestMapping(value="savePerinatalBackground",method=RequestMethod.POST)
	public @ResponseBody String savePerinatalBackground(@ModelAttribute(value="changes") Record changes, BindingResult result){
		Record currentRecord = recordService.getById(changes.getIdRecord());
		currentRecord.setPerinatalBackground(changes.getPerinatalBackground());
		recordService.updateItem(currentRecord);
		String message = " <div class='alert alert-success alert-dismissable'> " +
								"<button type='button' class='close' data-dismiss='alert' aria-hidden='true'>&times;</button>" +
								"<div>Se han guardado los cambios</div>" +
						  "</div>";
		return message;
	}
	
	@RequestMapping(value="saveDevelopmentBackground",method=RequestMethod.POST)
	public @ResponseBody String saveDevelopmentBackground(@ModelAttribute(value="changes") Record changes, BindingResult result){
		Record currentRecord = recordService.getById(changes.getIdRecord());
		currentRecord.setDevelopmentBackground(changes.getDevelopmentBackground());
		recordService.updateItem(currentRecord);
		String message = " <div class='alert alert-success alert-dismissable'> " +
								"<button type='button' class='close' data-dismiss='alert' aria-hidden='true'>&times;</button>" +
								"<div>Se han guardado los cambios</div>" +
						  "</div>";
		return message;
	}
	
	@RequestMapping(value="saveSurgicalHistory",method=RequestMethod.POST)
	public @ResponseBody String saveSurgicalHistory(@ModelAttribute(value="changes") Record changes, BindingResult result){
		Record currentRecord = recordService.getById(changes.getIdRecord());
		currentRecord.setSurgicalHistory(changes.getSurgicalHistory());
		recordService.updateItem(currentRecord);
		String message = " <div class='alert alert-success alert-dismissable'> " +
								"<button type='button' class='close' data-dismiss='alert' aria-hidden='true'>&times;</button>" +
								"<div>Se han guardado los cambios</div>" +
						  "</div>";
		return message;
	}
	
	@RequestMapping(value="saveHereditaryAndFamilyBackground",method=RequestMethod.POST)
	public @ResponseBody String saveHereditaryAndFamilyBackground(@ModelAttribute(value="changes") Record changes, BindingResult result){
		Record currentRecord = recordService.getById(changes.getIdRecord());
		currentRecord.setHereditaryAndFamilyBackground(changes.getHereditaryAndFamilyBackground());
		recordService.updateItem(currentRecord);
		String message = " <div class='alert alert-success alert-dismissable'> " +
								"<button type='button' class='close' data-dismiss='alert' aria-hidden='true'>&times;</button>" +
								"<div>Se han guardado los cambios</div>" +
						  "</div>";
		return message;
	}
	
	@RequestMapping(value="savePathologicalBackground",method=RequestMethod.POST)
	public @ResponseBody String savePathologicalBackground(@ModelAttribute(value="changes") Record changes, BindingResult result){
		Record currentRecord = recordService.getById(changes.getIdRecord());
		currentRecord.setPathologicalBackgorund(changes.getPathologicalBackgorund());
		recordService.updateItem(currentRecord);
		String message = " <div class='alert alert-success alert-dismissable'> " +
								"<button type='button' class='close' data-dismiss='alert' aria-hidden='true'>&times;</button>" +
								"<div>Se han guardado los cambios</div>" +
						  "</div>";
		return message;
	}
	
	@RequestMapping(value="saveOthers",method=RequestMethod.POST)
	public @ResponseBody String saveOthers(@ModelAttribute(value="changes") Record changes, BindingResult result){
		Record currentRecord = recordService.getById(changes.getIdRecord());
		currentRecord.setOthers(changes.getOthers());
		recordService.updateItem(currentRecord);
		String message = " <div class='alert alert-success alert-dismissable'> " +
								"<button type='button' class='close' data-dismiss='alert' aria-hidden='true'>&times;</button>" +
								"<div>Se han guardado los cambios</div>" +
						  "</div>";
		return message;
	}
	
	@RequestMapping(value="frequentDiagnostics")
	public @ResponseBody JsonPack<Cie10> frequentDiagnostics()
	{
		String query = "FROM Cie10 c WHERE (day(current_date()) - day(c.lastUsed)) <= 3 AND c.active = 1";
		JsonPack<Cie10> result = new JsonPack<Cie10>(cie10Service.getListOfItem(query));
		return result;
	}
	
	@RequestMapping(value="diagnostics")
	public @ResponseBody JsonPack<Cie10> allDiagnostics()
	{
		String query = "FROM Cie10 c WHERE c.active = 1";
		JsonPack<Cie10> result = new JsonPack<Cie10>(cie10Service.getListOfItem(query));
		return result;
	}
	
	@RequestMapping(value="diagnosticTreatment")
	public @ResponseBody JsonPack<Treatment> allDiagnostics(@RequestParam int diagnosticId)
	{
		String query = "FROM Treatment t join t.cie10Collection d WHERE d.idCIE10 = "+diagnosticId;
                
		JsonPack<Treatment> result = new JsonPack<Treatment>(treatmentService.getListOfItem(query));
		return result;
	}
        
        //This method gives a json response with all the drugs related to the treatment
        @RequestMapping(value="drugsByTreatment")
	public @ResponseBody JsonPack<Drug> allDrugs(@RequestParam int treatmentId)
	{
		String query = "FROM Drug t join t.treatmentCollection d WHERE d.idTreatment = "+treatmentId;
                
		JsonPack<Drug> result = new JsonPack<Drug>(drugService.getListOfItem(query));
		return result;
	}
	
        //This method gives a json response with all the commercial names related to a drug
        @RequestMapping(value="drugsCommercialNames")
	public @ResponseBody JsonPack<CommercialName> allDrugCommercialNames(@RequestParam int drugId)
	{
		String query = "FROM CommercialName t WHERE t.drugId = "+drugId;
                
		JsonPack<CommercialName> result = new JsonPack<CommercialName>(commercialNameService.getListOfItem(query));
		return result;
	}
	
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
	
	private List<Relative> getBrothers(Set<Patient_Relative> relatives){
		
		List<Relative> list = new ArrayList<Relative>();
		
		for(Patient_Relative r: relatives)
		{
			if(r.getIdRelationship().getRelationship().equals("Hermano"))
			{
				list.add(r.getRelative() );
			}
		}
		
		return ( list );
	}
	
	private String getCurrentDate(){
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();
		return ( format.format(date));
	}
	
	private String formatDate(Date date){
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		return ( format.format(date));
	}
	
	private String calculateAge(Date date){
		SimpleDateFormat temp = new SimpleDateFormat("yyyy-MM-dd");
		String[] birthdayElements = temp.format(date).split("-");
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
