package com.carloscortina.demo.controller;

import com.carloscortina.demo.json.JsonPack;
import com.carloscortina.demo.model.Appointment;
import com.carloscortina.demo.model.AppointmentStatus;
import com.carloscortina.demo.model.Consultation;
import com.carloscortina.demo.model.Consultationmotive;
import com.carloscortina.demo.model.Document;
import com.carloscortina.demo.model.LaboratoryTest;
import com.carloscortina.demo.model.LaboratoryTestResult;
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
import com.carloscortina.demo.model.PatientRelative;
import com.carloscortina.demo.model.PatientRelativePK;
import com.carloscortina.demo.model.PatientVaccine;
import com.carloscortina.demo.model.PerBackNoPat;
import com.carloscortina.demo.model.Record;
import com.carloscortina.demo.model.Relative;
import com.carloscortina.demo.model.Staffmember;
import com.carloscortina.demo.model.User;
import com.carloscortina.demo.service.AppointmentService;
import com.carloscortina.demo.service.AppointmentStatusService;
import com.carloscortina.demo.service.BirthmethodService;
import com.carloscortina.demo.service.ConsultationService;
import com.carloscortina.demo.service.ConsultationmotiveService;
import com.carloscortina.demo.service.LaboratoryTestResultService;
import com.carloscortina.demo.service.LaboratoryTestService;
import com.carloscortina.demo.service.PatientRelativeService;
import com.carloscortina.demo.service.PatientService;
import com.carloscortina.demo.service.PatientVaccineService;
import com.carloscortina.demo.service.PerBackNoPatService;
import com.carloscortina.demo.service.RecordService;
import com.carloscortina.demo.service.RelationshipService;
import com.carloscortina.demo.service.RelativeService;
import com.carloscortina.demo.service.ReligionService;
import com.carloscortina.demo.service.StaffMemberService;
import com.carloscortina.demo.service.UserService;
import com.carloscortina.demo.service.VaccineService;
import com.carloscortina.demo.service.VaccineTypeService;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.Map;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Controller
@RequestMapping(value="/patients")
public class PatientsController {
	
        static final long ONE_MINUTE_IN_MILLIS=60000;//millisecs
    
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
        @Autowired
        private ReligionService religionService; 
        @Autowired
        private AppointmentService appointmentService;
        @Autowired
        private StaffMemberService staffService;
        @Autowired
        private PatientVaccineService pvService;
        @Autowired
        private AppointmentStatusService appointmentStatusService;
        @Autowired
        private BirthmethodService birthMethodService;
        @Autowired
        private LaboratoryTestService labService;
        @Autowired
        private LaboratoryTestResultService labResService;
        @Autowired
        private ConsultationService consultationService;
        @Autowired
        private ConsultationmotiveService cmService;
        @Autowired
        private UserService userService;
        @Autowired
        PerBackNoPatService pbService;
        @Autowired
        private VaccineService vaccineService;
        @Autowired
        private VaccineTypeService vtService;
        
        private Patient patient;
        private User doctor;
        
        
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
	public String patientHome(Model model){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
                doctor = userService.getUserByUsername(auth.getName());
                
                model.addAttribute("user",doctor.getIdUser());
                model.addAttribute("doctors",userService.getUserByRole(2));
                model.addAttribute("relationshipType",relationshipService.getAll("Relationship"));
                model.addAttribute("appointmentStatus",appointmentStatusService.getAll("AppointmentStatus"));
                model.addAttribute("religions",religionService.getAllReligions());
                
		return ( "patients/patientHome" );
	}
        
        @RequestMapping(value="immunizationHome")
	public String immunizationHome(Model model){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
                doctor = userService.getUserByUsername(auth.getName());
                
                model.addAttribute("vaccines",vaccineService.getAll("Vaccine"));
                model.addAttribute("vaccineTypes", vtService.getAll("VaccineType"));
                
		return ( "patients/ImmunizationHome" );
	}
        
        @RequestMapping(value="getPatientsByDoctor")
        public @ResponseBody JsonPack<Patient> getAllPatientsByDoctor(){
            return new JsonPack<Patient>(patientService.getAllPatientsByDoctor(doctor.getIdStaffMember().getIdStaffMember()));
        }
        
        @RequestMapping(value="getAllPatients")
        public @ResponseBody JsonPack<Patient> getAllPatients(){
            return new JsonPack<Patient>(patientService.getAllPatients());
        }
        
        @RequestMapping(value="addNewPatient")
        public @ResponseBody String addNewPatient(@RequestParam Map<String,String> params){
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            List<PatientRelative> relatives = new ArrayList<PatientRelative>();
            
            try{
                Date birthday =  df.parse(params.get("birthday"));
                Patient newPatient = new Patient(params.get("fName"),params.get("flName"), params.get("mlName"),
                        params.get("curp"), params.get("gender"), birthday, params.get("notes"));
                newPatient.setIdDoctor(userService.getById(Integer.parseInt(params.get("doctor"))).getIdStaffMember());
                newPatient.setActive((short)1);
                patientService.create(newPatient);
                for(int i = 0; i < Integer.parseInt(params.get("relativesCounter"));i++ ){
                    PatientRelativePK rPK = new PatientRelativePK(newPatient.getIdPatient(), Integer.parseInt(params.get("idRelative"+(i+1))));
                    PatientRelative pr= new PatientRelative(rPK,relationshipService.getById(Integer.parseInt(params.get("idRelationship"+(i+1)))));
                    relatives.add(pr);
                }
                newPatient.setPatientRelativeList(relatives);
                PerBackNoPat newPerBackNoPat= new PerBackNoPat();
                pbService.create(newPerBackNoPat);
                
                Record newRecord = new Record(newPerBackNoPat, newPatient);
                patientService.updateItem(newPatient);
                
                recordService.create(newRecord);
            }catch(Exception e){ e.printStackTrace(); }

            return "";
        }
        
        @RequestMapping(value="deletePatient")
        public @ResponseBody String deletePatient(int idPatient){
            Patient patientDelete = patientService.getById(idPatient);
            patientDelete.setActive((short)0);
            List<Appointment> appointments = appointmentService.getListOfItem("FROM Appointment a WHERE a.idPatient.idPatient="+patientDelete.getIdPatient());
            for(Appointment ap : appointments){
                ap.setIdStatus(appointmentStatusService.getById(11));
                appointmentService.updateItem(ap);
            }
            patientService.updateItem(patientDelete);
            return "";
        }
        
        @RequestMapping(value="saveModifyRelative")
        public @ResponseBody String saveModifyRelative(@RequestParam Map<String,String> params){
            Relative relative = relativeService.getRelative(Integer.parseInt(params.get("idRelative")));
            
            //Travel the map entries
            for (Map.Entry<String, String> entry : params.entrySet()) {
                //Fill relative data
                try{
                    for(Method m: relative.getClass().getMethods()){
                        if(m.getName().startsWith("set")){
                            if( m.getName().equalsIgnoreCase("set"+entry.getKey())){
                                if( m.getName().equalsIgnoreCase("setReligion") ){
                                    m.invoke(relative,religionService.getReligion(Integer.parseInt(entry.getValue())));
                                }else if( !m.getName().equalsIgnoreCase("setIdRelative")){
                                    m.invoke(relative,entry.getValue());
                                }    
                            }
                        }
                    }
                    relativeService.updateRelative(relative);
                    
                    PatientRelativePK rPK = new PatientRelativePK(patient.getIdPatient(), relative.getIdRelative());
                    PatientRelative pr= prService.getByPK(rPK);
                    pr.setIdRelationship(relationshipService.getById(Integer.parseInt(params.get("idRelationship"))));
                    prService.updateItem(pr);
                    
                }catch(Exception e){ 
                    e.printStackTrace();
                }
            }
            
            return ("");
        }
        
        
        @RequestMapping(value="updatePatient")
        public @ResponseBody String updatePatient(@RequestParam Map<String,String> params){
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            List<PatientRelative> toDelete = new ArrayList<PatientRelative>();
            
            try{
                Date birthday =  df.parse(params.get("birthday"));
                Patient updatePatient = patientService.getById(Integer.parseInt(params.get("idPatient")));
                updatePatient.setFirstName(params.get("firstName"));
                updatePatient.setFatherLastName(params.get("fatherlastName"));
                updatePatient.setMotherLastName(params.get("mlName"));
                updatePatient.setCurp(params.get("curp"));
                updatePatient.setSex(params.get("gender"));
                updatePatient.setBirthday(birthday);
                updatePatient.setNotes(params.get("notes"));
                Staffmember staff =  staffService.getById(Integer.parseInt(params.get("doctor")));
                updatePatient.setIdDoctor(staff);
                
                for(int i = 0; i < Integer.parseInt(params.get("relativesCounter"));i++ ){
                    PatientRelativePK rPK = new PatientRelativePK(updatePatient.getIdPatient(), Integer.parseInt(params.get("idRelative"+(i+1))));
                    PatientRelative pr= prService.getByPK(rPK);
                    //If there no relation create it
                    if(pr == null){
                        pr = new PatientRelative(rPK, relationshipService.getById(Integer.parseInt(params.get("idRelationship"+(i+1)))));
                        prService.create(pr);
                    }else{
                        toDelete.add(pr);
                    }
                }
                List<PatientRelative> prUpdate = updatePatient.getPatientRelativeList();
                prUpdate.removeAll(toDelete);
                
                patientService.updateItem(updatePatient);
                
                //if theres still data in the list, delete it
                for(PatientRelative pr: prUpdate){
                    prService.delete(pr);
                }
                
            }catch(Exception e){ e.printStackTrace(); }

            return "";
        }
        
        @RequestMapping(value="getPatientBasicData", produces = "application/json")
        public @ResponseBody Patient getPatientBasicData(int idPatient){
            return patientService.getPatientBasicData(idPatient);
        }
        
        @RequestMapping(value="getPatientRelatives", produces = "application/json")
        public @ResponseBody JsonPack<PatientRelative> getPatientRelatives(int idPatient){
            return new JsonPack<PatientRelative>(patientService.getById(idPatient).getPatientRelativeList());
        }
        
        @RequestMapping(value="getPatientAvaibleRelatives", produces = "application/json")
        public @ResponseBody JsonPack<PatientRelative> getPatientAvaibleRelatives(int idPatient){
            List<PatientRelative> allPR = prService.getAll("PatientRelative");
            allPR.removeAll(patientService.getById(idPatient).getPatientRelativeList());
            
            return new JsonPack<PatientRelative>(allPR);
        }
        
        @RequestMapping(value="getAppointmentStatus", produces = "application/json")
        public @ResponseBody JsonPack<AppointmentStatus> getAppointmentStatus(){
            List<AppointmentStatus> allAS = appointmentStatusService.getAll("AppointmentStatus");
            
            return new JsonPack<AppointmentStatus>(allAS);
        }
        
        @RequestMapping(value="getConsultationMotives", produces = "application/json")
        public @ResponseBody JsonPack<Consultationmotive> getConsultationMotives(){
            List<Consultationmotive> allCM = cmService.getAll("Consultationmotive");
            return new JsonPack<Consultationmotive>(allCM);
        }
        
        @RequestMapping(value="getAppointmentsByDate", produces = "application/json")
        public @ResponseBody JsonPack<Appointment> getAppointmentsByDate(String date){
            SimpleDateFormat sdf = new SimpleDateFormat("dd/M/yyyy");
             List<Appointment> apppointments = new ArrayList<Appointment>();
            try{
                Date day = sdf.parse(date);
                apppointments = appointmentService.getAppointmentsByDay(day, day);
            }catch (Exception e){ e.printStackTrace(); }
            
            return new JsonPack<Appointment>(apppointments);
        }
        
        @RequestMapping(value="getAppointmentsByPatient", produces = "application/json")
        public @ResponseBody JsonPack<Appointment> getAppointmentsByPatient(String patient){
            List<Appointment> apppointments = new ArrayList<Appointment>();
            try{
                apppointments = appointmentService.getAppointmentsByPatient(Integer.parseInt(patient));
            }catch (Exception e){ e.printStackTrace(); }
            
            return new JsonPack<Appointment>(apppointments);
        }
        
        @RequestMapping(value="getConsultationsByPatient", produces = "application/json")
        public @ResponseBody JsonPack<Consultation> getConsultationsByPatien(String patient){
            List<Consultation> consultations = new ArrayList<Consultation>();
            try{
                consultations = consultationService.getConsultationsByPatient(Integer.parseInt(patient));
            }catch (Exception e){ e.printStackTrace(); }
            
            return new JsonPack<Consultation>(consultations);
        }
        
        @RequestMapping(value="savePatientAppointment")
        public @ResponseBody String savePatientAppointment(@RequestParam Map<String,String> params){
            SimpleDateFormat stf = new SimpleDateFormat("kk:mm");
            SimpleDateFormat sdf = new SimpleDateFormat("dd/M/yyyy");

            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String currentPrincipalName = authentication.getName();
           
            Appointment appointment = new Appointment();
            
            try{
                appointment.setDate(sdf.parse(params.get("date")));
                appointment.setStartTime(stf.parse(params.get("startTime")));
                appointment.setEndTime(new Date(stf.parse(params.get("startTime")).getTime()+(30*ONE_MINUTE_IN_MILLIS)));
                appointment.setMotive(params.get("motive"));
                appointment.setIdDoctor(userService.getById(Integer.parseInt(params.get("idDoctor"))));
                appointment.setIdPatient(patientService.getById(Integer.parseInt(params.get("idPatient"))));
                appointment.setIdStatus(appointmentStatusService.getById(Integer.parseInt(params.get("idStatus"))));
                appointment.setImmunization(params.get("immunization").equalsIgnoreCase("true"));
                appointment.setProgrammedBySystem((short)0);
                appointment.setRegisteredBy(userService.getUserByUsername(currentPrincipalName));
                appointment.setNotes(params.get("notes"));
                if(!params.get("pc").isEmpty()){appointment.setPc(Double.parseDouble(params.get("pc")));}
                if(!params.get("size").isEmpty()){appointment.setSize(Double.parseDouble(params.get("size")));}
                if(!params.get("ta").isEmpty()){appointment.setTa(Double.parseDouble(params.get("ta")));}
                if(!params.get("ta2").isEmpty()){appointment.setTa2(Double.parseDouble(params.get("ta2")));}
                if(!params.get("taAverage").isEmpty()){appointment.setTaAverage(Double.parseDouble(params.get("taAverage")));}
                if(!params.get("temperature").isEmpty()){appointment.setTemperature(Double.parseDouble(params.get("temperature")));}
                if(!params.get("weight").isEmpty()){appointment.setWeight(Double.parseDouble(params.get("weight")));}
                
                Consultationmotive cm = cmService.getMotiveByName(params.get("motive"));
                if( cm != null){
                    cm.setLastUsed(new Date());
                    cmService.updateItem(cm);
                }
                
                appointmentService.create(appointment);
 
            }catch(Exception e){ e.printStackTrace(); } 
            return "";
        }
        
        @RequestMapping(value="getPatientProgrammedVaccine")
        public @ResponseBody JsonPack<PatientVaccine> getPatientProgrammedVaccine(int idPatient){
            Date currentDate = new Date();
            List<PatientVaccine>  pv = pvService.getListOfItem("FROM PatientVaccine WHERE idPatient="+idPatient);
            //Check if any vaccine is outdated
            for(PatientVaccine vaccine: pv){
                //check if there's a programmed date
                if(vaccine.getProgramedDate() != null){
                    //check if not applied
                    if(vaccine.getApplicationDate() == null){
                        if(!(vaccine.getSuspended() > 0)){
                            if(vaccine.getProgramedDate().compareTo(currentDate) < 1 ){
                                vaccine.setSuspended((short)2);
                                pvService.updateItem(vaccine);
                            }
                        }
                    }
                }
            }
            pv = pvService.getListOfItem("FROM PatientVaccine WHERE idPatient="+idPatient);
            return new JsonPack<PatientVaccine>(pv);
        }
	
        @RequestMapping(value="patientConsultation")
        public @ResponseBody String patientConsultation(int idPatient){
            SimpleDateFormat stf = new SimpleDateFormat("kk:mm");
            Appointment appointment = new Appointment();
            //Create a default appointment
            try{
                appointment = new Appointment(new Date(), stf.parse("09:00"), stf.parse("09:30"), "Consulta",
                    "Programada pro el sistema", false, appointmentStatusService.getById(3), doctor, patientService.getById(idPatient), 
                    doctor, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0);
            }catch(Exception e){ e.printStackTrace(); }
            appointmentService.create(appointment);
            
            return (appointment.getIdAppointment().toString());
        }
	@RequestMapping(value="new",method=RequestMethod.GET)
	public String loadRegistrationForm(Model model){
		
		model.addAttribute("form", new PatientRegistrationForm());
		return ("patients/patientsRegistrationForm");
	}
	
        /*
         * 
         */
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
        
        /*
         * This method renders the patient file, based on the patient. This is 
         * mainly when the user is looking info of the patient only.
         */
        @RequestMapping(value="file/{idPatient}",method= RequestMethod.GET)
        public String patientFile(Model model,@PathVariable int idPatient){
            patient = patientService.getById( idPatient );
            
            Record record = recordService.getByPatientId( patient );
            
            PerBackNoPat perBackNoPat = record.getIdPerBackNoPat();
            String[] age = calculateAge(patient.getBirthday()).split("-");
            
            model.addAttribute("birthday",formatDate(patient.getBirthday()));
            model.addAttribute("father",getFather(patient.getPatientRelativeList()));
            model.addAttribute("mother",getMother(patient.getPatientRelativeList()));
            model.addAttribute("age",age);
            model.addAttribute("date",getCurrentDate());
            model.addAttribute("patient",patient);
            model.addAttribute("record",record);
            model.addAttribute("perBackNoPat",perBackNoPat);
            model.addAttribute("relationshipType",relationshipService.getAll("Relationship"));
            model.addAttribute("religionType", religionService.getAllReligions());
            model.addAttribute("birthMethods",birthMethodService.getAll("Birthmethod"));
            model.addAttribute("jsFile","patientFile.js");
            
            return("patients/PatientFile");
        }
        
        
        /*
         * This method renders the patient file based on a appointment, this is done, so
         * a appointment can be related to a consultation, even if a patient is new.
         */
        @RequestMapping(value="file/appointment/{idAppointment}",method= RequestMethod.GET)
        public String patientFileBasedOnAppointment(Model model,@PathVariable int idAppointment){
            Appointment appointment = appointmentService.getById(idAppointment);
            patient = appointment.getIdPatient();
            Record record = recordService.getByPatientId(patient);
            PerBackNoPat perBackNoPat = record.getIdPerBackNoPat();
            String[] age = calculateAge(patient.getBirthday()).split("-");
            List<Relative> sibilings = getSibilings(patient.getPatientRelativeList());
            
            model.addAttribute("birthday",formatDate(patient.getBirthday()));
            model.addAttribute("father",getFather(patient.getPatientRelativeList()));
            model.addAttribute("mother",getMother(patient.getPatientRelativeList()));
            model.addAttribute("age",age);
            model.addAttribute("date",getCurrentDate());
            model.addAttribute("patient",patient);
            model.addAttribute("record",record);
            model.addAttribute("perBackNoPat",perBackNoPat);
            model.addAttribute("sibilings",sibilings );
            model.addAttribute("relationshipType",relationshipService.getAll("Relationship"));
            model.addAttribute("religionType", religionService.getAllReligions());
            model.addAttribute("birthMethods",birthMethodService.getAll("Birthmethod"));
            model.addAttribute("jsFile","patientFileOnAppointment.js");
            model.addAttribute("idAppointment",appointment.getIdAppointment());
            
            return("patients/PatientFile");
        }
        
        
        
        /*
         * Query methods
         * This section contains mainly methods that run querys on the database.
        */
        
        /*
        * This method uses the global variable patient to get the relatives
        */
        @RequestMapping(value="getPatientSibilings")
        public @ResponseBody JsonPack<Patient> getPatientSibilings(){
            //Patient patient = patientService.getById(idPatient);
            List<Patient> relatives = new ArrayList();
            
            for(PatientRelative r: patient.getPatientRelativeList())
            {
                if(r.getRelative().getIdPatient() != null){
                    relatives.add(r.getRelative().getIdPatient());
                }
            }

            return new JsonPack<Patient>(relatives);
            
        }
        
        /*
         * This method returns a JSON with all the relatives of a patient , based
         * on the patient id.
         */
        @RequestMapping(value="getPatientRelatives2")
	public @ResponseBody JsonPack<Relative> getPatientRelatives2(@RequestParam int idPatient)
	{
                //Patient patient = patientService.getById(idPatient);
                List<Relative> relatives = new ArrayList();
                for(PatientRelative r: patient.getPatientRelativeList())
                {
                    relatives.add(r.getRelative());
                }
                
		JsonPack<Relative> result = new JsonPack<Relative>(relatives);
                
		return result;
	}
        
        /*
         * This methods returns all the relaties from all the patients on the database.
         */
        @RequestMapping(value="getAllRelatives")
	public @ResponseBody JsonPack<Relative> getAllRelatives()
	{
		JsonPack<Relative> result = new JsonPack<Relative>(relativeService.getAllRelatives());
                
		return result;
	}
        
        /*
         * This method sets a relationship between a patient and a relative.
         */
        @RequestMapping(value="addPatientFamilyRelative")
	public @ResponseBody String addPatientFamilyRelative(@RequestParam(value="idPatient")int idPatient,
                                                             @RequestParam(value="idRelative")int idRelative,
                                                             @RequestParam(value="idRelationship")int idRelationship)
	{
		PatientRelative patientRelative = new PatientRelative();
                PatientRelativePK id = new PatientRelativePK(idPatient, idRelative);
                patientRelative.setPatientRelativePK(id);
                patientRelative.setIdRelationship(relationshipService.getById(idRelationship));
                patientRelative.setPatient(patientService.getById(idPatient));
                patientRelative.setRelative(relativeService.getRelative(idRelative));
                

                prService.create(patientRelative);
                
		return "addRelative";
	}
        
        /*
         * This method deletes the relationship between a patient and a relative. 
         */
        @RequestMapping(value="unrelateRelative")
        public @ResponseBody String unrelateRelative(@RequestParam(value="idPatient")int idPatient,
                                                     @RequestParam(value="idRelative")int idRelative,
                                                     @RequestParam(value="idRelationship")int idRelationship){
        
            PatientRelative patientRelative = new PatientRelative();
            PatientRelativePK id = new PatientRelativePK(idPatient, idRelative);
            patientRelative.setPatienRelativePK(id);
            patientRelative.setIdRelationship(relationshipService.getById(idRelationship));
            patientRelative.setPatient(patientService.getById(idPatient));
            patientRelative.setRelative(relativeService.getRelative(idRelative));
            
            prService.delete(patientRelative);
            
            return "";
        }
        
        /*
         * 
         */
        @RequestMapping(value="updatePatientRelative")
        public @ResponseBody String updateRelative(@RequestParam(value="cellPhone",required=false)String cellPhone,
                                                   @RequestParam(value="city",required=false)String city,
                                                   @RequestParam(value="colony",required=false)String colony,
                                                   @RequestParam(value="country",required=false)String country,
                                                   @RequestParam(value="cp",required=false)String cp,
                                                   @RequestParam(value="curp",required=false)String curp,
                                                   @RequestParam(value="email",required=false)String email,
                                                   @RequestParam(value="fatherLastName")String fatherLastName,
                                                   @RequestParam(value="firstName")String firstName,
                                                   @RequestParam(value="homephone",required=false)String homePhone,
                                                   @RequestParam(value="id")int idRelative,
                                                   @RequestParam(value="patientRelative")String idRelationship,
                                                   @RequestParam(value="motherLastName",required=false)String motherLastName,
                                                   @RequestParam(value="notes",required=false)String notes,
                                                   @RequestParam(value="number",required=false)String number,
                                                   @RequestParam(value="occupation",required=false)String occupation,
                                                   @RequestParam(value="officeExt",required=false)String officeExt,
                                                   @RequestParam(value="officePhone",required=false)String officePhone,
                                                   @RequestParam(value="otherPhone",required=false)String otherPhone,
                                                   @RequestParam(value="recommendedBy",required=false)String recommendedBy,
                                                   @RequestParam(value="religion")String religion,
                                                   @RequestParam(value="rfc",required=false)String rfc,
                                                   @RequestParam(value="secondName",required=false)String secondName,
                                                   @RequestParam(value="state",required=false)String state,
                                                   @RequestParam(value="street",required=false)String street
                                                   ){
        
            Relative updateRelative = relativeService.getRelative(idRelative);
            updateRelative.setFirstName(firstName);
            updateRelative.setFatherLastName(fatherLastName);
            updateRelative.setMotherLastName(motherLastName);
            updateRelative.setCurp(curp);
            updateRelative.setOccupation(occupation);
            updateRelative.setRfc(rfc);
            updateRelative.setHomePhone(homePhone);
            updateRelative.setOfficePhone(officePhone);
            updateRelative.setOfficeExt(officeExt);
            updateRelative.setCellPhone(cellPhone);
            updateRelative.setOtherPhone(otherPhone);
            updateRelative.setEmail(email);
            updateRelative.setReligion(religionService.getReligion(Integer.parseInt(religion)));
            updateRelative.setCity(city);
            updateRelative.setColony(colony);
            updateRelative.setCountry(country);
            updateRelative.setCp(cp);
            updateRelative.setCurp(curp);
            updateRelative.setNotes(notes);
            updateRelative.setStreet(street);
            updateRelative.setState(state);
            updateRelative.setRecommendedBy(recommendedBy);
            updateRelative.setNumber(number);
            for(PatientRelative r: updateRelative.getPatientRelativeList()){
                if(r.getRelative().getIdRelative() == idRelative){
                    r.setIdRelationship(relationshipService.getById(Integer.parseInt(idRelationship)));
                }
            }
            
            relativeService.updateRelative(updateRelative);
            
            return "";
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
                File newFolder = new File("/Volumes/2nd_HDD/Documents/test/Files/paciente"+patient.getIdPatient());
                newFolder.mkdir();
                File newFile = new File("/Volumes/2nd_HDD/Documents/test/Files/paciente"+patient.getIdPatient()+"/"+fileName);
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
        public @ResponseBody JsonPack<Document> getDocuments(){
            File folder = new File("/Volumes/2nd_HDD/Documents/test/Files/paciente"+patient.getIdPatient());
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
                File fileDelete = new File("/Volumes/2nd_HDD/Documents/test/Files/paciente"+patient.getIdPatient()+"/"+file);
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
                    Desktop.getDesktop().open(new File("/Volumes/2nd_HDD/Documents/test/Files/paciente"+patient.getIdPatient()+"/"+file));
                }
            }catch (IOException ioe){
                ioe.printStackTrace();
            }
            
            return "";
        }
        
        //Laboratory Tests
        
        @RequestMapping(value="getLaboratoryTestsPatientData")
        public @ResponseBody JsonPack<LaboratoryTestResult> getLaboratoryTestByPatient(){
            
            String query = "FROM LaboratoryTestResult l where l.idPatient="+patient.getIdPatient();
            List<LaboratoryTestResult> ret = labResService.getListOfItem(query);
            
            return new JsonPack<LaboratoryTestResult>(ret);
        }
        
        @RequestMapping(value="saveLaboratoryTestResult")
        public @ResponseBody String saveLaboratoryTestResult(String date,int idLaboratoryTest,String testResult){
            SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy");
            try{
                Date fDate = sdf.parse(date);
                LaboratoryTest lab = labService.getById(idLaboratoryTest);
                System.out.println(fDate);
                LaboratoryTestResult labRes = new LaboratoryTestResult(fDate, testResult, patient, lab);
                labResService.create(labRes);
                
            }catch (Exception e){ e.printStackTrace();}

            return "Se ha guardado la informacion correctamente";
        }
        
        @RequestMapping(value="editLaboratoryTestResult")
        public @ResponseBody String editLaboratoryTestResult(int idLaboratoryTestResult,String date,int idLaboratoryTest,String result){
            SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy");
            try{
                Date fDate = sdf.parse(date);
                LaboratoryTestResult labRes = labResService.getById(idLaboratoryTestResult);
                labRes.setDate(fDate);
                labRes.setIdLaboratoryTest(labService.getById(idLaboratoryTest));
                labRes.setResult(result);
                
                labResService.updateItem(labRes);
                
            }catch (Exception e){ e.printStackTrace();}

            return "Se ha guardado la informacion correctamente";
        }
        
        @RequestMapping(value="deleteLaboratoryTestResult")
        public @ResponseBody String delteLaboratoryTestResult(int idResult){
            LaboratoryTestResult labRes = labResService.getById(idResult);
            labResService.delete(labRes);
            
            return "Se ha borrado la informacion correctamente";
        }
        
        @RequestMapping(value="previousConsultation")
        public @ResponseBody JsonPack<Consultation> getPreviousPatientConsultation(){
            
            return new JsonPack<Consultation>(consultationService.getListOfItem("FROM Consultation c where idPatient="+patient.getIdPatient()));

        }
        
        /*
        * Method to get a list of all the programmed vaccines
        */
        @RequestMapping(value="getAllPatientImmunization")
        public @ResponseBody JsonPack<PatientVaccine> getImmunization(){
            
            return new JsonPack<PatientVaccine>(pvService.getAll("PatientVaccine"));

        }
        
        //Generic Methods
        private Relative getFather(List<PatientRelative> relatives)
	{
		for(PatientRelative r: relatives)
		{
			if(r.getIdRelationship().getRelationship().equals("Padre"))
			{
				return r.getRelative();
			}
		}
		
		return ( null );
	}
	
	private Relative getMother(List<PatientRelative> relatives)
	{
		for(PatientRelative r: relatives)
		{
			if(r.getIdRelationship().getRelationship().equals("Madre"))
			{
				return r.getRelative();
			}
		}
		
		return ( null );
	}
        
        private List<Relative> getSibilings(List<PatientRelative> relatives){
		
		List<Relative> list = new ArrayList<Relative>();
		
		for(PatientRelative r: relatives)
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
        
	/*
         * This method process the current date and calculates the age of the patient in years,months and days. 
         */
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
