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
import com.carloscortina.demo.model.Activity;
import com.carloscortina.demo.model.Ageweight0To36Months;
import com.carloscortina.demo.model.Appointment;
import com.carloscortina.demo.model.AuxGraphTable;
import com.carloscortina.demo.model.Cie10;
import com.carloscortina.demo.model.CommercialName;
import com.carloscortina.demo.model.Consultation;
import com.carloscortina.demo.model.Document;
import com.carloscortina.demo.model.Drug;
import com.carloscortina.demo.model.DrugDose;
import com.carloscortina.demo.model.GraphData;
import com.carloscortina.demo.model.Patient;
import com.carloscortina.demo.model.Patient_Relative;
import com.carloscortina.demo.model.PerBackNoPat;
import com.carloscortina.demo.model.Record;
import com.carloscortina.demo.model.Relative;
import com.carloscortina.demo.model.Treatment;
import com.carloscortina.demo.model.Vaccine;
import com.carloscortina.demo.service.AW0to36MonthsService;
import com.carloscortina.demo.service.ActivityService;
import com.carloscortina.demo.service.ActivityTypeService;
import com.carloscortina.demo.service.AppointmentService;
import com.carloscortina.demo.service.BirthmethodService;
import com.carloscortina.demo.service.Cie10Service;
import com.carloscortina.demo.service.CommercialNameService;
import com.carloscortina.demo.service.ConsultationService;
import com.carloscortina.demo.service.DrugDoseService;
import com.carloscortina.demo.service.DrugService;
import com.carloscortina.demo.service.LaboratoryTestService;
import com.carloscortina.demo.service.PatientService;
import com.carloscortina.demo.service.PerBackNoPatService;
import com.carloscortina.demo.service.RecordService;
import com.carloscortina.demo.service.RelativeService;
import com.carloscortina.demo.service.TreatmentService;
import com.carloscortina.demo.service.UserService;
import com.carloscortina.demo.service.VaccineService;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.util.Iterator;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;


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
        @Autowired
        private DrugDoseService drugDoseService;
        @Autowired
        private ActivityService activityService;
        @Autowired
        private VaccineService vaccineService;
        @Autowired
        private ActivityTypeService activityTypeService;
        @Autowired
        private UserService userService; 
        @Autowired
        private BirthmethodService birthMethodService;
        @Autowired
        private AW0to36MonthsService aw036Service;
        @Autowired
        ConsultationService consultationService;
        @Autowired
        LaboratoryTestService labService;
        
        private int idAppointment;
        private int idDoctor;
        private int idPatient;
        private String[] age;
	
	@RequestMapping(value="")
	public String startConsultation(Model model){
		idAppointment = 1;
                idDoctor =16;
		Appointment appointment = appointmentService.getById(idAppointment);
		Patient patient = patientService.getById(appointment.getIdPatient().getId());
                idPatient = patient.getId();
		Record record = recordService.getByPatientId(patient);
		PerBackNoPat perBackNoPat = record.getIdPerBackNoPat();
                
                
		model.addAttribute("father",getFather(patient.getRelatives()));
		model.addAttribute("mother",getMother(patient.getRelatives()));
		
		//List<Relative> brothers = new ArrayList<Relative>();
		//brothers = getBrothers(patient.getRelatives());
		//model.addAttribute("hermanos",brothers);
		
		model.addAttribute("birthday",formatDate(patient.getBirthday()));
		age = calculateAge(patient.getBirthday()).split("-");
		
		model.addAttribute("age",age);
		model.addAttribute("date",getCurrentDate());
		model.addAttribute("patient",patient);
		model.addAttribute("record",record);
		model.addAttribute("perBackNoPat",perBackNoPat);
                model.addAttribute("doctor",userService.getUserById(idDoctor));
                model.addAttribute("birthmethods", birthMethodService.getAll("Birthmethod"));
                model.addAttribute("laboratoryTests", labService.getAll("LaboratoryTest"));

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
		return "";
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
		return "";
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
		return "";
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
		return "";
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
		return "";
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
		return "";
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
		return "";
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
		return "";
	}
        
        //Create a new activity and save it in the DB
        @RequestMapping(value="addNewActivity", method=RequestMethod.POST)
	public @ResponseBody String addNewActivity(@RequestParam(value="activity") String activity,
                                                   @RequestParam(value="activityCost") String cost,
                                                   @RequestParam(value="idActivityType") String type,
                                                   @RequestParam(value="consultationDefault" , required = false) String cDefault,
                                                   @RequestParam(value="idVaccine" , required = false) String vaccine)
	{
		Activity newActivity = new Activity();
                newActivity.setActivity(activity);
                newActivity.setActivityCost(Double.parseDouble(cost));
                newActivity.setIdActivityType(activityTypeService.getById(Integer.parseInt(type)));
                newActivity.setActive(1);
                if( vaccine!= null ){
                    newActivity.setIdVaccine(vaccineService.getById(Integer.parseInt(vaccine)));
                }
                if( cDefault != null){ 
                    newActivity.setConsultationDefault(Integer.parseInt(cDefault));
                }
                
                activityService.create(newActivity);
                return ("function" );
	}
        
        @RequestMapping(value="editActivity", method=RequestMethod.POST)
	public @ResponseBody String editActivity(@RequestParam(value="idActivity") String idActivity,
                                                   @RequestParam(value="activity") String activity,
                                                   @RequestParam(value="activityCost") String cost,
                                                   @RequestParam(value="idActivityType") String type,
                                                   @RequestParam(value="consultationDefault" , required = false) String cDefault,
                                                   @RequestParam(value="idVaccine" , required = false) String vaccine)
        {
		Activity modifiedActivity = activityService.getById(Integer.parseInt(idActivity));
                
                modifiedActivity.setActivity(activity);
                modifiedActivity.setActivityCost(Double.parseDouble(cost));
                modifiedActivity.setIdActivityType(activityTypeService.getById(Integer.parseInt(type)));
                modifiedActivity.setActive(1);
                if( vaccine != null ){
                    if( !vaccine.isEmpty() ){
                        modifiedActivity.setIdVaccine(vaccineService.getById(Integer.parseInt(vaccine)));
                    }
                }
                if( cDefault != null){ 
                    modifiedActivity.setConsultationDefault(Integer.parseInt(cDefault));
                }
                
                activityService.updateItem(modifiedActivity);
                return ("function");
	}
        
        @RequestMapping(value="getConsultationSibilings")
        public @ResponseBody JsonPack<Relative> getConsultationSibilings(@RequestParam int idpatient){
            
            Patient patient= patientService.getById(idpatient);
            List<Relative> relatives = getBrothers(patient.getRelatives());
            List<Relative> sibiling = new ArrayList<Relative>();
            
            for(Relative r: relatives)
            {
                if(r.getIdPatient() != null)
                {
                        sibiling.add(r);
                }
            }
            
            
            return (new JsonPack<Relative>(sibiling));
        }
        
        @RequestMapping(value="updateAppointmentData")
        public @ResponseBody String updateAppointmentData(@RequestParam(value="algo")int algo){
            return "";
        }
	
	@RequestMapping(value="frequentDiagnostics")
	public @ResponseBody JsonPack<Cie10> frequentDiagnostics()
	{
		String query = "FROM Cie10 c WHERE (day(current_date()) - day(c.lastUsed)) <= 3 AND c.active = 1";
		JsonPack<Cie10> result = new JsonPack<Cie10>(cie10Service.getListOfItem(query));
		return result;
	}
	
	@RequestMapping(value="diagnostics")
	public @ResponseBody JsonPack<Cie10> allDiagnostics(@RequestParam(required = false) String cache)
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
	public @ResponseBody JsonPack<Drug> allDrugsByTreatment(@RequestParam int treatmentId)
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
        
        @RequestMapping(value="drugDose")
	public @ResponseBody JsonPack<DrugDose> getDrugDoseByDrugId(@RequestParam int drugId)
	{
		String query = "FROM DrugDose t WHERE t.idDrug = " + drugId;
                
		JsonPack<DrugDose> result = new JsonPack<DrugDose>(drugDoseService.getListOfItem(query));
		return result;
	}
        
        //Return a json with all the Drugs(Medecine) in the system
        @RequestMapping(value="getAllDrugs")
	public @ResponseBody JsonPack<Drug> allDrugs()
	{
		String query = "FROM Drug t ";
                
		JsonPack<Drug> result = new JsonPack<Drug>(drugService.getListOfItem(query));
		return result;
	}
        
        //This method returns a json with all the activities 
        @RequestMapping(value={"/getAllActivities","getAllActivities"})
	public @ResponseBody JsonPack<Activity> allActivities()
	{
		String query = "FROM Activity t ";
                
		JsonPack<Activity> result = new JsonPack<Activity>(activityService.getListOfItem(query));
		
                return result;
	}
        
        //This method returns a json with all the vaccines in the system 
        @RequestMapping(value="getAllVaccines")
	public @ResponseBody JsonPack<Vaccine> allVaccines()
	{
		//String query = "FROM Vaccine t ";
                
		JsonPack<Vaccine> result = new JsonPack<Vaccine>(vaccineService.getAll("Vaccine"));
		
                return result;
	}
        
        
        
        @RequestMapping(value="uploadFile",method=RequestMethod.POST)
        public @ResponseBody String uploadFile(MultipartHttpServletRequest request){
            InputStream inputStream = null;
            OutputStream outputStream = null;
            
            
            Iterator<String> itr = request.getFileNames();
            MultipartFile file = request.getFile(itr.next());
            
            String fileName = file.getOriginalFilename();
            System.out.println(fileName);

            try{
                inputStream = file.getInputStream();
                File newFolder = new File("/Volumes/2nd_HDD/Documents/test/Files/paciente"+idPatient);
                newFolder.mkdir();
                File newFile = new File("/Volumes/2nd_HDD/Documents/test/Files/paciente"+idPatient+"/"+fileName);
                if(!newFile.exists()){
                    newFile.createNewFile();
                }
                outputStream = new FileOutputStream(newFile);
                int read =0;
                byte[] bytes = new byte[1024];

                while((read = inputStream.read(bytes)) != -1){
                    outputStream.write(bytes,0,read);
                }
            } catch(IOException e){
                e.printStackTrace();
            }
            
            
            return "";
        }
	
        @RequestMapping(value="getPatientDocument")
        public @ResponseBody JsonPack<Document> getDocuments(@RequestParam(value="idPatient")int idPatient){
            File folder = new File("/Volumes/2nd_HDD/Documents/test/Files/paciente"+idPatient);
            File[] listOfFiles = folder.listFiles();
            List<Document> documents = new ArrayList<Document>();
            
            
            for (int i = 0; i < listOfFiles.length; i++) {
                if (listOfFiles[i].isFile()) {
                   
                    Document doc = new Document();
                    doc.setName(listOfFiles[i].getName());
                    doc.setDeleteBtn("<button type='button' class='btn btn-danger' onclick='deleteDocument(this);'>Eliminar</button>");
                    documents.add(doc);
                    
                } else if (listOfFiles[i].isDirectory()) {
                    System.out.println("Directory " + listOfFiles[i].getName());
                }
            }
            
            JsonPack<Document> result = new JsonPack<Document>(documents);
            
            return result;
        }
        
        @RequestMapping(value="deletePatientDocument")
        public @ResponseBody String delteDocument(@RequestParam(value="file")String file){
            try{
                File fileDelete = new File("/Volumes/2nd_HDD/Documents/test/Files/paciente"+idPatient+"/"+file);
                fileDelete.delete();
                
            }catch(Exception e){
                e.printStackTrace();
            }
            return "";
        }
        
        @RequestMapping(value="openPatientDocument")
        public @ResponseBody String openDocument(@RequestParam(value="file")String file){
            try{
                if(Desktop.isDesktopSupported()){
                    Desktop.getDesktop().open(new File("/Volumes/2nd_HDD/Documents/test/Files/paciente"+idPatient+"/"+file));
                }
            }catch (IOException ioe){
                ioe.printStackTrace();
            }
            
            return "";
        }
        
        //Section: Graphs/Charts
        
        @RequestMapping(value="getGraphPatientData")
        public @ResponseBody JsonPack<AuxGraphTable> getPatientConsults(@RequestParam(value="patient")int patient){
            String query = "FROM Consultation c where c.idPatient="+patient;
            List<Consultation> list = consultationService.getListOfItem(query);
            List<AuxGraphTable> ret = new ArrayList<AuxGraphTable>();
            AuxGraphTable data = new AuxGraphTable();
            
            for(Consultation cs: list){
                data = new AuxGraphTable(cs.getIdConsultation(),cs.getIdAppointment().getDate(),
                                            "8-3-1",cs.getWeigth(),cs.getSize(),cs.getPc(),
                                            cs.getTa(),cs.getTa2(),cs.getTaAverage(),cs.getTemperature(),cs.getWeigth()/Math.pow((cs.getSize()/100.00),2));
                ret.add(data);
            }
            
            return new JsonPack<AuxGraphTable>(ret);
        }
        
        @RequestMapping(value="editGraphPatientData")
        public @ResponseBody String editPatientConsults(@RequestParam(value="idConsultation")int idConsultation,
                                                        @RequestParam(value="weight")double weight,
                                                        @RequestParam(value="size",required = false)double size,
                                                        @RequestParam(value="pc",required = false)double pc,
                                                        @RequestParam(value="imc")double imc,
                                                        @RequestParam(value="ta",required = false)double ta,
                                                        @RequestParam(value="ta2",required = false)double ta2,
                                                        @RequestParam(value="taaverage",required = false)double taaverage,
                                                        @RequestParam(value="temperature",required = false)double temperature){
            Consultation cs = consultationService.getById(idConsultation);
            cs.setWeigth(weight);
            cs.setSize(size);
            cs.setPc(pc);
            cs.setTa(ta);
            cs.setTa2(ta2);
            cs.setTaAverage(taaverage);
            cs.setTemperature(temperature);
            
            consultationService.updateItem(cs);
            return "";
        }
        
        @RequestMapping(value="graph1")
        public @ResponseBody JsonPack<List<GraphData>> graphAge_Weight0_36(@RequestParam(value="patient")int idPatient){
            
            List<Ageweight0To36Months> data = aw036Service.getAll("Ageweight0To36Months");
            List<List<GraphData>> allData = new ArrayList<List<GraphData>>();
            List<GraphData> list = new ArrayList<GraphData>();
            Ageweight0To36Months aux = new Ageweight0To36Months();
            GraphData singleD = new GraphData();
            Patient patient = patientService.getById(idPatient);
            String query = "FROM Consultation c where c.idPatient=" + idPatient;
            String gender = (patient.getSex().compareTo("masculino")) == 0? "M" : "F"; 
            
            for(Method m: aux.getClass().getMethods()){
                
                if(m.getName().startsWith("getP") && m.getParameterTypes().length == 0){
                    list = new ArrayList<GraphData>();
                    for(Ageweight0To36Months aw: data)
                    {   
                        System.out.println(aw.getGender() +","+gender);
                        if(aw.getGender().compareTo(gender) == 0){
                            try{
                                Object p =  m.invoke(aw);
                                singleD  = new GraphData(aw.getAgeInMonths(),Double.valueOf(p.toString()).doubleValue());
                                list.add(singleD);
                            }catch (Exception e){
                                e.printStackTrace();
                            }
                        }
                    }
                    allData.add(list);
                }
            }
            
            list = new ArrayList<GraphData>();
            List<Consultation> patientData = consultationService.getListOfItem(query);
            //double ageMonths= Double.parseDouble(age[0]) * 12 + Double.parseDouble(age[1] + Double.parseDouble(age[2])/365);
            double ageMonths=27.01;
            for(Consultation cs: patientData){
                singleD = new GraphData(ageMonths,cs.getSize());
                list.add(singleD);
            }
            allData.add(list);

            return new JsonPack<List<GraphData>>(allData);
        }
        
        //Section: Laboratory Test Tabs

        
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
