package com.carloscortina.demo.controller;

import com.carloscortina.demo.json.JsonPack;
import com.carloscortina.demo.model.Appointment;
import com.carloscortina.demo.model.Appointmentstatus;
import com.carloscortina.demo.model.Appointmentvaccine;
import com.carloscortina.demo.model.AppointmentvaccinePK;
import com.carloscortina.demo.model.Consultation;
import com.carloscortina.demo.model.Consultationmotive;
import com.carloscortina.demo.model.Documents;
import com.carloscortina.demo.model.Holyday;
import com.carloscortina.demo.model.Laboratorytest;
import com.carloscortina.demo.model.Laboratorytestresult;
import com.carloscortina.demo.model.Patient;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.carloscortina.demo.model.PatientRelative;
import com.carloscortina.demo.model.PatientRelativePK;
import com.carloscortina.demo.model.Patientvaccine;
import com.carloscortina.demo.model.PatientvaccinePK;
import com.carloscortina.demo.model.Perbacknopat;
import com.carloscortina.demo.model.Record;
import com.carloscortina.demo.model.Relative;
import com.carloscortina.demo.model.Staffmember;
import com.carloscortina.demo.model.User;
import com.carloscortina.demo.model.Vaccine;
import com.carloscortina.demo.service.AppointmentService;
import com.carloscortina.demo.service.AppointmentStatusService;
import com.carloscortina.demo.service.AppointmentVaccineService;
import com.carloscortina.demo.service.BirthmethodService;
import com.carloscortina.demo.service.ConsultationService;
import com.carloscortina.demo.service.ConsultationmotiveService;
import com.carloscortina.demo.service.ConsultationtypeService;
import com.carloscortina.demo.service.DocumentService;
import com.carloscortina.demo.service.DocumentcategoryService;
import com.carloscortina.demo.service.GenderService;
import com.carloscortina.demo.service.HolydayService;
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
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.net.URLDecoder;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
        @Autowired
        private DocumentcategoryService dcService;
        @Autowired
        private DocumentService documentService;
        @Autowired
        AppointmentVaccineService avService;
        @Autowired
        AppointmentStatusService apsService;
        @Autowired
        GenderService genderService;
        @Autowired
        HolydayService holydayService;
        @Autowired
        ConsultationtypeService ctService;
        
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
                model.addAttribute("relationshipType",relationshipService.getAllActiveItems());
                model.addAttribute("appointmentStatus",appointmentStatusService.getAllActiveItems());
                model.addAttribute("religions",religionService.getAllReligions());
                model.addAttribute("genders",genderService.getAllActiveItems());
                
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
        
        /*
         * This method renders the patient file, based on the patient. This is 
         * mainly when the user is looking info of the patient only.
         */
        @RequestMapping(value="file/{idPatient}",method= RequestMethod.GET)
        public String patientFile(Model model,@PathVariable int idPatient,HttpSession session){
            
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            doctor = userService.getUserByUsername(auth.getName());
            patient = patientService.getById( idPatient );
            
            session.setAttribute("patientForGraph", patient);
            
            Record record = recordService.getByPatientId( patient );
            
            Perbacknopat perBackNoPat = record.getIdPerBackNoPat();
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
            model.addAttribute("documentCategories",dcService.getAll("Documentcategory"));
            model.addAttribute("consultationTypes",ctService.getAll(""));
            model.addAttribute("staff",doctor.getIdStaffMember());
            model.addAttribute("doctors",userService.getUserByRole(2));
            model.addAttribute("appointmentStatus",appointmentStatusService.getAllActiveItems());
            
            return("patients/PatientFile");
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
                        params.get("curp"), birthday, params.get("notes"), genderService.getById(Integer.parseInt(params.get("gender"))));
                newPatient.setIdDoctor(userService.getById(Integer.parseInt(params.get("doctor"))).getIdStaffMember());
                newPatient.setActive( 1 );
                newPatient.setAddedDate(new java.util.Date());
                patientService.create(newPatient);
                for(int i = 0; i < Integer.parseInt(params.get("relativesCounter"));i++ ){
                    PatientRelativePK rPK = new PatientRelativePK(newPatient.getIdPatient(), Integer.parseInt(params.get("idRelative"+(i+1))));
                    PatientRelative pr= new PatientRelative(rPK,relationshipService.getById(Integer.parseInt(params.get("idRelationship"+(i+1)))));
                    relatives.add(pr);
                }
                newPatient.setPatientRelativeList(relatives);
                Perbacknopat newPerBackNoPat= new Perbacknopat();
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
                updatePatient.setSex(genderService.getById(Integer.parseInt(params.get("gender"))));
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
        public @ResponseBody JsonPack<Appointmentstatus> getAppointmentStatus(){
            List<Appointmentstatus> allAS = appointmentStatusService.getAll("Appointmentstatus");
            
            return new JsonPack<Appointmentstatus>(allAS);
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
        
        @RequestMapping(value="getAvaibleAppointment", produces = "application/json")
        public @ResponseBody JsonPack<Appointment> getAvaibleAppointmentsByPatient(){
            Calendar cal = Calendar.getInstance();
            cal.setTime(new Date());
            cal.add(Calendar.DATE, 1); //minus number would decrement the days
            
            return new JsonPack<Appointment>(appointmentService.getAvaibleAppointmentsByPatient(new Date(), cal.getTime(), patient.getIdPatient()));
        }
        
        @RequestMapping(value="getAppointmentsByPatient", produces = "application/json")
        public @ResponseBody JsonPack<Appointment> getAppointmentsByPatient(String patient){
            List<Appointment> apppointments = new ArrayList<Appointment>();
            try{
                apppointments = appointmentService.getAppointmentsByPatient(Integer.parseInt(patient));
            }catch (Exception e){ e.printStackTrace(); }
            
            return new JsonPack<Appointment>(apppointments);
        }
        
        @RequestMapping(value="getAppointmentsFileByPatient", produces = "application/json")
        public @ResponseBody JsonPack<Appointment> getAppointmentsFileByPatient(){
            List<Appointment> apppointments = new ArrayList<Appointment>();
            try{
                apppointments = appointmentService.getAppointmentsByPatient( patient.getIdPatient() );
            }catch (Exception e){ e.printStackTrace(); }
            
            return new JsonPack<Appointment>(apppointments);
        }
        
        @RequestMapping(value="modifyAppointment")
        public @ResponseBody void modifyAppointment(@RequestParam Map<String,String> params){
            SimpleDateFormat stf = new SimpleDateFormat("kk:mm");
            SimpleDateFormat sdf = new SimpleDateFormat("dd/M/yyyy");
           
            Appointment appointment = appointmentService.getById(Integer.parseInt(params.get("idAppointment")));
            Date date = new Date();
            Date time = new Date();
            try{
                date = sdf.parse(params.get("date"));
                time = stf.parse(params.get("startTime"));
            }catch(Exception e){ e.printStackTrace(); } 
            
                appointment.setDate(date);
                appointment.setStartTime(time);
                appointment.setMotive(params.get("motive"));
                appointment.setIdDoctor(userService.getById(Integer.parseInt(params.get("idDoctor"))));
                appointment.setIdStatus(apsService.getById(Integer.parseInt(params.get("idStatus"))));
                appointment.setImmunization(params.get("immunization").equalsIgnoreCase("true")? 1:0);
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
                
                appointmentService.updateItem(appointment);
        }
        
        @RequestMapping(value="deleteSystemProgrammedAppointments")
        public @ResponseBody void deleteSystemProgrammedAppointments(){
            //First get patient vaccine
            List<Patientvaccine> pvList = pvService.getPatientVaccineSystemProgrammedByPatient(patient.getIdPatient());
            List<Appointment> appointmentList = new ArrayList();
            //Patient vaccine
            List<Appointmentvaccine> avList = avService.getListOfItem("FROM Appointmentvaccine WHERE idPatient="+patient.getIdPatient());
            for(Appointmentvaccine av: avList){
                for(Patientvaccine pv : pvList){
                    if(pv.getPatientvaccinePK().getIdVaccine() == av.getAppointmentvaccinePK().getIdVaccine()){
                        appointmentList.add(av.getAppointment());
                        avService.delete(av);
                    }
                }
            }
            
            for(Patientvaccine pv : pvList){
                pvService.delete(pv);
            }
            
            for(Appointment ap: appointmentList){
                appointmentService.delete(ap);
            }
            
        }
        
        @RequestMapping(value="getConsultationsByPatient", produces = "application/json")
        public @ResponseBody JsonPack<Consultation> getConsultationsByPatien(int patient){
            List<Consultation> consultations = new ArrayList<Consultation>();
            
            consultations = consultationService.getConsultationsByPatient(patient);
            
            return new JsonPack<Consultation>(consultations);
        }
        
        @RequestMapping(value="getConsultationsByPatientFile", produces = "application/json")
        public @ResponseBody JsonPack<Consultation> getConsultationsByPatientFile(){

            return new JsonPack<Consultation>( consultationService.getConsultationsByPatient( patient.getIdPatient() ) );
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
                appointment.setMotive(params.get("motive"));
                appointment.setIdDoctor(userService.getById(Integer.parseInt(params.get("idDoctor"))));
                appointment.setIdPatient(patientService.getById(Integer.parseInt(params.get("idPatient"))));
                appointment.setIdStatus(appointmentStatusService.getById(Integer.parseInt(params.get("idStatus"))));
                appointment.setImmunization(params.get("immunization").equalsIgnoreCase("true")? 1 : 0);
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
        public @ResponseBody JsonPack<Patientvaccine> getPatientProgrammedVaccine(int idPatient){
            Date currentDate = new Date();
            List<Patientvaccine>  pv = pvService.getPatientVaccineByPatient(idPatient);
            //Check if any vaccine is outdated
            for(Patientvaccine vaccine: pv){
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
            //pv = pvService.getListOfItem("FROM Patientvaccine WHERE idPatient="+idPatient);
            return new JsonPack<Patientvaccine>(pv);
        }
        
        @RequestMapping(value="getPatientFileProgrammedVaccine")
        public @ResponseBody JsonPack<Patientvaccine> getPatientProgrammedVaccine(){
            Date currentDate = new Date();
            
            List<Patientvaccine>  pv = pvService.getPatientVaccineByPatient(patient.getIdPatient());
            
            //Check if any vaccine is outdated
            for(Patientvaccine vaccine: pv){
                //check if there's a programmed date
                if(vaccine.getProgramedDate() != null){
                    //check if not applied
                    if(vaccine.getApplicationDate() == null){
                        //Check if not alreadey suspended or outdated
                        if( ( vaccine.getSuspended() == 0 ) ){
                            //check if vaccine is outdated 
                            if( vaccine.getProgramedDate().compareTo( currentDate ) < 1 ){
                                vaccine.setSuspended(2);
                                pvService.updateItem(vaccine);
                            }
                        }
                    }
                }
            }
            
            return new JsonPack<Patientvaccine>(pv);
        }
        
        @RequestMapping(value="getExpiredVaccine")
        public @ResponseBody JsonPack<Patientvaccine> getExpiredVaccine(){
            
            List<Patientvaccine> pvList = pvService.getListOfItem("FROM Patientvaccine WHERE suspended="+2+" AND patient.idPatient="+patient.getIdPatient());
            for(Patientvaccine pv:pvList){
                pv.getVaccine().setVaccineList(null);
                pv.getVaccine().setVaccineList1(null);
            }
            return new JsonPack<Patientvaccine>(pvList);
        }
        
        @RequestMapping(value="getSuspendedVaccine")
        public @ResponseBody JsonPack<Patientvaccine> getSuspendedVaccine(){
            
            List<Patientvaccine> pvList = pvService.getListOfItem("FROM Patientvaccine WHERE suspended="+1+" AND patient.idPatient="+patient.getIdPatient());
            for(Patientvaccine pv:pvList){
                pv.getVaccine().setVaccineList(null);
                pv.getVaccine().setVaccineList1(null);
            }
            
            return new JsonPack<Patientvaccine>(pvList);
        }
        
        @RequestMapping(value="getAvaibleVaccine")
        public @ResponseBody JsonPack<Vaccine> getAvaibleVaccine(){
            List<Vaccine> vaccineList = vaccineService.getAllActiveVaccines();
            List<Patientvaccine> pvList = pvService.getPatientVaccineByPatient(patient.getIdPatient());
            List<Vaccine> remove = new ArrayList<Vaccine>();
            
            for(Patientvaccine pv: pvList){
                remove.add(pv.getVaccine());
            }
            
            vaccineList.removeAll(remove);
            return new JsonPack<Vaccine>(vaccineList);
        }
        
        @RequestMapping(value="deleteProgrammedVaccine")
        public @ResponseBody void deleteProgrammedVaccine(int idVaccine){
            
            Patientvaccine pv = pvService.getById(new PatientvaccinePK(patient.getIdPatient(), idVaccine));
            pvService.delete(pv);
            
            List<Appointmentvaccine> result = avService.getListOfItem("FROM Appointmentvaccine WHERE idPatient="+patient.getIdPatient()+" AND idVaccine="+idVaccine);
            if(!result.isEmpty()){
                Appointment ap = result.get(0).getAppointment();
                ap.setIdStatus(apsService.getById(3));
                appointmentService.updateItem(ap);
            }
        }
        
        @RequestMapping(value="editProgrammedVaccine",method=RequestMethod.POST)
        public @ResponseBody String editPatientProgrammedVaccine(@RequestParam Map<String,String> params){
            
            PatientvaccinePK id = new PatientvaccinePK(patient.getIdPatient(),Integer.parseInt(params.get("idVaccine")));
            Patientvaccine currentPV = pvService.getById(id);

            Patientvaccine pv = new Patientvaccine();
            pv.setPatientvaccinePK(id);
            
            try{
                Date applicationDate = (params.get("applicationDate").isEmpty()) ? null : new SimpleDateFormat("dd/MM/yyyy").parse(params.get("applicationDate"));
                Date programmedDate = (params.get("programedDate").isEmpty()) ? null : new SimpleDateFormat("dd/MM/yyyy").parse(params.get("programedDate"));
                Date expirationDate = (params.get("expirationDate").isEmpty()) ? null : new SimpleDateFormat("dd/MM/yyyy").parse(params.get("expirationDate"));
                pv.setApplicationDate(applicationDate);
                pv.setProgramedDate(programmedDate);
                pv.setBatch(params.get("batch"));
                pv.setName(params.get("name"));
                pv.setExpirationDate(expirationDate);
                pv.setProgramManual(1);
                if( params.get("suspended").compareToIgnoreCase("ture") != 0 ){
                    pv.setSuspended(1);
                }else{
                    pv.setSuspended(currentPV.getSuspended());
                }
                pv.setNotes(params.get("notes"));
                
            }catch(Exception e){
                e.printStackTrace();
            }
            
            if(currentPV.getApplicationDate() == null){
                if(pv.getApplicationDate() != null){
                    //The vaccine was applied
                    pv.setSuspended((short)0);
                    List<Appointmentvaccine> avList = avService.getListOfItem("FROM AppointmentVaccine WHERE idVaccine="+id.getIdVaccine()+" AND idPatient="+id.getIdPatient());
                    for(Appointmentvaccine av: avList ){
                        av.getAppointment().setIdStatus(apsService.getById(1));
                    }
                }
            }

            pvService.updateItem(pv);
            
            return "";
        }
	
        //Get neares appointment that has not been used or create a new appointment
        @RequestMapping(value="patientConsultation")
        public @ResponseBody String patientConsultation(int idPatient){
            
            SimpleDateFormat stf = new SimpleDateFormat("kk:mm");
            Appointment appointment = new Appointment();

            //Create a default appointment
            try{
                appointment = new Appointment(new Date(), new Date(), "Consulta",
                    "Programada pro el sistema", 0,0.0,0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1 , doctor,
                        doctor, appointmentStatusService.getById(10),patientService.getById(idPatient));
            }catch(Exception e){ e.printStackTrace(); }
            appointmentService.create(appointment);
            
            return (appointment.getIdAppointment().toString());
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
            
            List<Patient> sibilings = new ArrayList<Patient>();
            for(PatientRelative pr: prService.getSibilingsByPatient(patient.getIdPatient())){
                sibilings.add(patientService.getById(pr.getPatientRelativePK().getIdPatient()));
            }
            return new JsonPack<Patient>(sibilings);   
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
	public @ResponseBody String addPatientFamilyRelative(//@RequestParam(value="idPatient")int idPatient,
                                                             @RequestParam(value="idRelative")int idRelative,
                                                             @RequestParam(value="idRelationship")int idRelationship)
	{
		PatientRelative patientRelative = new PatientRelative();
                //PatientRelativePK id = new PatientRelativePK(idPatient, idRelative);
                PatientRelativePK id = new PatientRelativePK(patient.getIdPatient(), idRelative);
                patientRelative.setPatientRelativePK(id);
                patientRelative.setIdRelationship(relationshipService.getById(idRelationship));
                //patientRelative.setPatient(patientService.getById(idPatient));
                patientRelative.setPatient(patient);
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
        
            PatientRelativePK id = new PatientRelativePK(idPatient, idRelative);
            PatientRelative patientRelative = new PatientRelative(id);
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
        
        //Section: Documents
        @RequestMapping(value="uploadFile",method=RequestMethod.POST,produces = "application/json")
        public @ResponseBody String uploadFile(MultipartHttpServletRequest request) throws UnsupportedEncodingException{
            InputStream inputStream = null;
            OutputStream outputStream = null;
                       
            Iterator<String> itr = request.getFileNames();
            MultipartFile file = request.getFile(itr.next());
            
            String fileName = file.getOriginalFilename();
            String filePath = "";
            //Get Application path
            String path = this.getClass().getClassLoader().getResource("").getPath();
            String fullPath = URLDecoder.decode(path, "UTF-8");
            
            String pathArr[] = fullPath.split("classes/");
            fullPath = pathArr[0];
            try{
                //Verify if the Files Folder exists
                inputStream = file.getInputStream();
                File newFolder = new File(fullPath+"\\Files");
                if(!newFolder.exists()){
                    newFolder.mkdir();
                }
                
                //Check if the patient already has a folder
                newFolder = new File(fullPath+"\\Files\\paciente"+patient.getIdPatient());
                if(!newFolder.exists()){
                    newFolder.mkdir();
                }
                
                //Check if that file already exist
                File newFile = new File(fullPath+"\\Files\\paciente"+patient.getIdPatient()+"/"+fileName);
                if(!newFile.exists()){
                    newFile.createNewFile();
                }else{
                    return ("FAE"); //El archivo ya existe
                }
                outputStream = new FileOutputStream(newFile);
                int read =0;
                byte[] bytes = new byte[1024];

                while((read = inputStream.read(bytes)) != -1){
                    outputStream.write(bytes,0,read);
                }
                filePath = newFile.getAbsolutePath();
            } catch(IOException e){
                e.printStackTrace();
            }
            
            return (filePath);
        }
        
        @RequestMapping(value="uploadFileAdditionalInfo",method=RequestMethod.POST,produces = "application/json")
        public @ResponseBody String uploadFileAdditionalInfo(@RequestParam Map<String,String>params){
            SimpleDateFormat sdf = new SimpleDateFormat("dd/M/yyyy");
            Date uDate = new Date();
            try{
                uDate = sdf.parse(params.get("date"));
            }catch(Exception e){  e.printStackTrace(); }
            
            Documents document = new Documents(params.get("description"), params.get("notes"),
                    params.get("path"), patient, dcService.getById( Integer.parseInt( params.get("category") ) ),
                    uDate, new Date());
            
            documentService.create(document);
            return "";
        }
        
        @RequestMapping(value="modifyFileAdditionalInfo",method=RequestMethod.POST)
        public @ResponseBody void modifyFileAdditionalInfo(@RequestParam Map<String,String>params){
            Documents document = documentService.getById(Integer.parseInt(params.get("idDocument")));
            
            SimpleDateFormat sdf = new SimpleDateFormat("dd/M/yyyy");
            Date uDate = new Date();
            try{
                uDate = sdf.parse(params.get("date"));
            }catch(Exception e){  e.printStackTrace(); }
            
            document.setDate(uDate);
            document.setDescription( params.get("description") );
            document.setNotes( params.get("notes") );
            document.setIdDocumentCategory( dcService.getById( Integer.parseInt( params.get( "category" ) ) ) );
            
            documentService.updateItem(document);
            
        }
	
        @RequestMapping(value="getPatientDocument")
        public @ResponseBody JsonPack<Documents> getDocuments(){             
            return new JsonPack<Documents>(documentService.getDocumentByPatient(patient.getIdPatient()));
        }
        
        @RequestMapping(value="deletePatientDocument")
        public @ResponseBody void deleteDocument(int idDocument){
            Documents document = documentService.getById(idDocument);
            
            try{
                File fileDelete = new File(document.getPath());
                fileDelete.delete();
                
            }catch(Exception e){
                e.printStackTrace();
            }
            documentService.delete(document);
        }
        
        @RequestMapping(value = "openFile", method = RequestMethod.GET)
        @ResponseBody void downloadFile(int idDocument,HttpServletRequest request, HttpServletResponse response) throws IOException{
            ServletContext context = request.getSession().getServletContext();
            
            Documents document = documentService.getById(idDocument);
            
            File file = new File(document.getPath());
            FileInputStream inputStream = new FileInputStream(file);
            
            String mimeType = context.getMimeType(document.getPath());
            if (mimeType == null) {
                // set to binary type if MIME mapping not found
                mimeType = "application/octet-stream";
            }
            
            response.setContentType(mimeType);
            response.setContentLength((int) file.length());
            
            String headerKey = "Content-Disposition";
            String headerValue = String.format("attachment; filename=\"%s\"",file.getName());
            response.setHeader(headerKey, headerValue);
            
            OutputStream outStream = response.getOutputStream();
 
            byte[] buffer = new byte[4096];
            int bytesRead = -1;
            
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outStream.write(buffer, 0, bytesRead);
            }
 
            inputStream.close();
            outStream.close();   
        }
        
        //Section: Vaccine
        @RequestMapping(value="suspendProgrammedVaccine")
        public @ResponseBody void suspendProgrammedVaccine(@RequestParam Map<String,String> params){
            Patientvaccine pv = pvService.getById(new PatientvaccinePK( patient.getIdPatient(), Integer.parseInt( params.get( "idDocument" ) ) ) );
            pv.setSuspended(1);
            pv.setProgramManual(1);
            pv.setSuspensionDate(new Date());
            pv.setNotes(params.get("motive"));
            pvService.updateItem(pv);
            
        }
        
        @RequestMapping(value="retriveProgrammedVaccine")
        public @ResponseBody String retriveProgrammedVaccine(int idVaccine){
            Patientvaccine pv = pvService.getById(new PatientvaccinePK(patient.getIdPatient(), idVaccine));
            pv.setSuspended(2);
            pv.setProgramManual(1);
            pv.setSuspensionDate(null);
            pvService.updateItem(pv);
            return "success";
        }
        
        
        //This method generates automatically the appointments for the vaccine application and the chkbx are the options
        @RequestMapping(value="programVaccines")
        public @ResponseBody String programVaccines(boolean a,boolean b,boolean c,boolean d,boolean e,boolean f ){
            /*options:
             * a - No programar fechas en sabados
             * b - No programar fechas en domingos
             * c - No programar fechas en dias no habiles
             * d - No modificar vacunas editadas y/o modificadas
             * e - Suspender vacunas cuya fecha de aplicacion haya pasado
             * f - No reprogramar citas de vacunas editadas y/o modificadas
             */
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
            Calendar cal = Calendar.getInstance();
            cal.setTime(patient.getBirthday());
            Date currentDate = new Date();
            List<Vaccine> vaccines = vaccineService.getAllActiveVaccines();
            List<Holyday> holydays = holydayService.getAll("");
              
            for(Vaccine vaccine: vaccines){
                if(vaccine.getActive() ==1){
                    cal.setTime(patient.getBirthday());

                    //Add the date of the vaccine apply date
                    cal.add(Calendar.DAY_OF_MONTH, vaccine.getDayApply());
                    cal.add(Calendar.MONTH, vaccine.getMonthApply());
                    cal.add(Calendar.YEAR, vaccine.getYearApply());

                    //If the appointment is sunday move it to monday and the option is true
                    if(cal.get(Calendar.DAY_OF_WEEK) == 1 && a){ 
                        cal.add(Calendar.DAY_OF_MONTH, 1);
                    }

                    //If the appointment is saturday move it to monday and the option is true
                    if(cal.get(Calendar.DAY_OF_WEEK) == 7 && b){
                        cal.add(Calendar.DAY_OF_MONTH,2);
                    }

                    //If appointment is on a holyday, move to the next avaible day
                    if(c){
                        boolean checkedHolydays = false; 
                        do{
                            for(Holyday holyday: holydays){
                                checkedHolydays = true;
                                if( (cal.get(Calendar.MONTH)+1) == holyday.getMont() && cal.get(Calendar.DAY_OF_MONTH) == holyday.getDay()){
                                    cal.add(Calendar.DAY_OF_MONTH, 1);

                                    //Check if didnt land on saturday or sunday    
                                    if(cal.get(Calendar.DAY_OF_WEEK) == 1){ 
                                        cal.add(Calendar.DAY_OF_MONTH, 1);
                                    }else if(cal.get(Calendar.DAY_OF_WEEK) == 7){
                                        cal.add(Calendar.DAY_OF_MONTH,2);
                                    }
                                    checkedHolydays = false;
                                    break;
                                }
                            }
                        }while(!checkedHolydays);
                    }

                    //Check if there are previous appointments or programmed vaccines
                    List<Patientvaccine> pvList = pvService.getListOfItem("FROM Patientvaccine WHERE idPatient="+patient.getIdPatient()+" AND idVaccine="+vaccine.getIdVaccine());
                    List<Appointmentvaccine> appointments = avService.getListOfItem("FROM Appointmentvaccine WHERE idPatient="+patient.getIdPatient()+" AND idVaccine="+vaccine.getIdVaccine());

                    if(pvList.isEmpty()){
                        if(appointments.isEmpty()){
                            //If there are no previous inmunization appopintments or programmed vaccines
                            //Create new Programmed Vaccines
                            Patientvaccine newPV = new Patientvaccine();
                            PatientvaccinePK newPVPK = new PatientvaccinePK();
                            newPV.setProgramedDate(cal.getTime());
                            newPV.setProgramManual((short)0);

                            //Check if the vaccine must be suspended
                            if(cal.getTime().compareTo(currentDate) <= 0){
                                if(e){
                                    newPV.setSuspended(1);
                                    newPV.setSuspensionDate(currentDate);
                                    newPV.setNotes("Suspendida por el sistema");
                                }else{
                                    newPV.setSuspended(2);
                                }

                                //If the vaccine has been suspended , dont program an appointment
                            }else{
                                newPV.setSuspended(0);
                                //Program a new appointment for each vaccine
                                Appointment newAppointment = new Appointment();

                                try{
                                    newAppointment.setDate(cal.getTime());
                                    newAppointment.setStartTime(timeFormat.parse("09:00:00"));
                                    newAppointment.setIdPatient(patient);
                                    newAppointment.setMotive(vaccine.getVaccine());
                                    newAppointment.setIdStatus(apsService.getById(2));
                                    newAppointment.setRegisteredBy(doctor);
                                    newAppointment.setIdDoctor(doctor);
                                    newAppointment.setImmunization(1);
                                    newAppointment.setProgrammedBySystem(1);
                                }catch(ParseException p){
                                    p.printStackTrace();
                                }
                                //Register the appointment
                                appointmentService.create(newAppointment);
                                AppointmentvaccinePK apvPK = new AppointmentvaccinePK(newAppointment.getIdAppointment(), vaccine.getIdVaccine(), patient.getIdPatient());
                                Appointmentvaccine newAV = new Appointmentvaccine(apvPK);
                                avService.create(newAV);

                            }
                            //Register the programmed vaccine
                            newPVPK.setIdPatient(patient.getIdPatient());
                            newPVPK.setIdVaccine(vaccine.getIdVaccine());
                            newPV.setPatientvaccinePK(newPVPK);
                            pvService.create(newPV);
                        }else{ // End (pv,empty),(av,empty)

                            //If there are appointments but no programmed vaccines
                            //Update appointments and create programmed vaccines
                            Patientvaccine newPV = new Patientvaccine();
                            PatientvaccinePK newPVPK = new PatientvaccinePK();
                            newPV.setProgramedDate(cal.getTime());
                            newPV.setProgramManual((short)0);

                            //Check if the vaccine must be suspended
                            if(cal.getTime().compareTo(currentDate) <= 0){
                                if(e){
                                    newPV.setSuspended((short)1);
                                    newPV.setSuspensionDate(currentDate);
                                    newPV.setNotes("Suspendida por el sistema");
                                }else{
                                    newPV.setSuspended((short)2);
                                }

                                //If the vaccine has been suspended , dont program an appointment
                                //Cancel the previous appointment if not programmed by user
                                if(f){
                                    if(appointments.get(0).getAppointment().getProgrammedBySystem() == 1){
                                        Appointment currentAppointment = appointments.get(0).getAppointment();
                                        currentAppointment.setNotes("Cancelada por el sistema");
                                        currentAppointment.setIdStatus(apsService.getById(3));
                                        appointmentService.updateItem(currentAppointment);
                                    }
                                }else{
                                    Appointment currentAppointment = appointments.get(0).getAppointment();
                                    currentAppointment.setNotes("Cancelada por el sistema");
                                    currentAppointment.setIdStatus(apsService.getById(3));
                                    appointmentService.updateItem(currentAppointment);
                                }

                            }else{
                                newPV.setSuspended((short)0);

                                //if theres a previous appointment update it, if hasnt been modified or added by a user.
                                if(f){
                                    if(appointments.get(0).getAppointment().getProgrammedBySystem() == 1){
                                        Appointment currentAppointment = appointments.get(0).getAppointment();
                                        currentAppointment.setDate(cal.getTime());
                                        currentAppointment.setIdStatus(apsService.getById(2));
                                        appointmentService.updateItem(currentAppointment);
                                    }
                                }else{
                                    Appointment currentAppointment = appointments.get(0).getAppointment();
                                    currentAppointment.setDate(cal.getTime());
                                    currentAppointment.setIdStatus(apsService.getById(2));
                                    appointmentService.updateItem(currentAppointment);
                                }
                            }
                            newPVPK.setIdPatient(patient.getIdPatient());
                            newPVPK.setIdVaccine(vaccine.getIdVaccine());
                            newPV.setPatientvaccinePK(newPVPK);
                            pvService.create(newPV);
                        }
                    }else{ // End (pv,empty),(av,not empty)
                        //If there are previous programmed vaccines 

                        if(appointments.isEmpty()){
                            //if there are programmed vaccine but no appointments
                            //Update the programmed vaccine
                            //if the programmed vaccine has been edited or modified by a user, dont modify

                            if(pvList.get(0).getProgramManual() != 1 || !d){
                                Patientvaccine currentPv = pvList.get(0);
                                currentPv.setProgramedDate(cal.getTime());
                                if(cal.getTime().compareTo(currentDate) <= 0){
                                    if(e){
                                        currentPv.setSuspended((short)1);
                                        currentPv.setSuspensionDate(currentDate);
                                        currentPv.setNotes("Suspendida por el sistema");
                                    }else{
                                        currentPv.setSuspended((short)2);
                                        currentPv.setSuspensionDate(null);
                                        currentPv.setNotes("");
                                    }

                                }else{
                                    currentPv.setSuspended((short)0);
                                    currentPv.setSuspensionDate(null);
                                    currentPv.setNotes("");
                                    //Program a new appointment
                                    Appointment newAppointment = new Appointment();

                                    try{
                                        newAppointment.setDate(cal.getTime());
                                        newAppointment.setStartTime(timeFormat.parse("09:00:00"));
                                        newAppointment.setIdPatient(patient);
                                        newAppointment.setMotive(vaccine.getVaccine());
                                        newAppointment.setIdStatus(apsService.getById(2));
                                        newAppointment.setRegisteredBy(doctor);
                                        newAppointment.setIdDoctor(doctor);
                                        newAppointment.setImmunization(1);
                                        newAppointment.setProgrammedBySystem((short)1);
                                    }catch(ParseException p){
                                        p.printStackTrace();
                                    }
                                    //Register the appointment
                                    appointmentService.create(newAppointment);
                                    AppointmentvaccinePK apvPK = new AppointmentvaccinePK(newAppointment.getIdAppointment(), vaccine.getIdVaccine(), patient.getIdPatient());
                                    Appointmentvaccine newAV = new Appointmentvaccine(apvPK);
                                    avService.create(newAV);

                                }

                                pvService.updateItem(currentPv);

                            } 
                        }else{
                            //if there are previous programmed vaccine and previous appointment
                            //update both
                            if(pvList.get(0).getProgramManual() != 1 || !d){
                                Patientvaccine currentPv = pvList.get(0);
                                currentPv.setProgramedDate(cal.getTime());
                                if(cal.getTime().compareTo(currentDate) <= 0){
                                    if(e){
                                        currentPv.setSuspended((short)1);
                                        currentPv.setSuspensionDate(currentDate);
                                        currentPv.setNotes("Suspendida por el sistema");
                                    }else{
                                        currentPv.setSuspended((short)2);
                                        currentPv.setSuspensionDate(null);
                                        currentPv.setNotes("");
                                    }

                                    if(f){
                                        //If the appointment was not programmed by a user , cancel it
                                        if(appointments.get(0).getAppointment().getProgrammedBySystem() == 1){
                                            Appointment currentAppointment = appointments.get(0).getAppointment();
                                            currentAppointment.setNotes("Cancelada por el sistema");
                                            currentAppointment.setIdStatus(apsService.getById(3));
                                            appointmentService.updateItem(currentAppointment);
                                        }
                                    }else{
                                        Appointment currentAppointment = appointments.get(0).getAppointment();
                                        currentAppointment.setNotes("Cancelada por el sistema");
                                        currentAppointment.setIdStatus(apsService.getById(3));
                                        appointmentService.updateItem(currentAppointment);
                                    }

                                }else{
                                    currentPv.setSuspended((short)0);
                                    currentPv.setSuspensionDate(null);
                                    currentPv.setNotes("");
                                    if(f){
                                        //Update appointment
                                        if(appointments.get(0).getAppointment().getProgrammedBySystem() == 1){
                                            Appointment currentAppointment = appointments.get(0).getAppointment();
                                            currentAppointment.setDate(cal.getTime());
                                            currentAppointment.setIdStatus(apsService.getById(2));
                                            appointmentService.updateItem(currentAppointment);
                                        }
                                    }else{
                                        Appointment currentAppointment = appointments.get(0).getAppointment();
                                        currentAppointment.setDate(cal.getTime());
                                        currentAppointment.setIdStatus(apsService.getById(2));
                                        appointmentService.updateItem(currentAppointment);
                                    }

                                }
                                pvService.updateItem(currentPv);
                            }
                        }
                    }
                }
            }
            
            return "ProgramVaccinesFinished";
        }
        
        @RequestMapping(value="addProgrammedVaccine")
        public @ResponseBody void addProgrammedVaccine(int idVaccine){
            Vaccine vaccine = vaccineService.getById(idVaccine);
            Patientvaccine patientVaccine = new Patientvaccine(patient.getIdPatient(), idVaccine);
            patientVaccine.setPatient(patient);
            patientVaccine.setVaccine(vaccine);
            patientVaccine.setProgramManual((short)1);
            //Add the date of the vaccine apply date
            Calendar cal = Calendar.getInstance();
            Date currentDay = new Date();
            cal.setTime(patient.getBirthday());
            cal.add(Calendar.DAY_OF_MONTH, vaccine.getDayApply());
            cal.add(Calendar.MONTH, vaccine.getMonthApply());
            cal.add(Calendar.YEAR, vaccine.getYearApply());
            patientVaccine.setProgramedDate(cal.getTime());
            
            //Check if the vaccine must be suspended
            if(cal.getTime().compareTo(currentDay) <= 0){
                patientVaccine.setSuspended((short)2);
                patientVaccine.setSuspensionDate(currentDay);
                patientVaccine.setNotes("Vencida cuando se programo");
            }
           
            pvService.create(patientVaccine);
        }
        
        //Laboratory Tests
        
        @RequestMapping(value="getLaboratoryTests")
        public @ResponseBody JsonPack<Laboratorytest> getAllLaboratoryTest(){
            return new JsonPack<Laboratorytest> (labService.getAllActiveItems());
        
        }
        
        @RequestMapping(value="getLaboratoryTestsPatientData")
        public @ResponseBody JsonPack<Laboratorytestresult> getLaboratoryTestByPatient(){
            
            String query = "FROM Laboratorytestresult l where l.idPatient="+patient.getIdPatient();
            List<Laboratorytestresult> ret = labResService.getListOfItem(query);
            
            return new JsonPack<Laboratorytestresult>(ret);
        }
        
        @RequestMapping(value="saveLaboratoryTestResult")
        public @ResponseBody void saveLaboratoryTestResult(String date,int idLaboratoryTest,String result){
            SimpleDateFormat sdf = new SimpleDateFormat("dd/M/yyyy");
            Date fDate = new Date();
            try{
                sdf.parse(date);   
            }catch (Exception e){ e.printStackTrace();
            }
            Laboratorytest lab = labService.getById(idLaboratoryTest);
            Laboratorytestresult labRes = new Laboratorytestresult(result, fDate, patient, lab);
            labResService.create(labRes);

        }
        
        @RequestMapping(value="editLaboratoryTestResult")
        public @ResponseBody void editLaboratoryTestResult(int idLaboratoryTestResult,String date,String result){
            SimpleDateFormat sdf = new SimpleDateFormat("dd/M/yyyy");
            Date fDate = new Date();
            try{
                sdf.parse(date);
            }catch (Exception e){ e.printStackTrace();}
            Laboratorytestresult labRes = labResService.getById(idLaboratoryTestResult);
            labRes.setDate(fDate);
            labRes.setResult(result);

            labResService.updateItem(labRes);
        }
        
        @RequestMapping(value="deleteLaboratoryTestResult")
        public @ResponseBody void delteLaboratoryTestResult(int idResult){
            Laboratorytestresult labRes = labResService.getById(idResult);
            labResService.delete(labRes);
            
        }
        
        @RequestMapping(value="previousConsultation")
        public @ResponseBody JsonPack<Consultation> getPreviousPatientConsultation(){
            
            return new JsonPack<Consultation>(consultationService.getListOfItem("FROM Consultation c where idPatient="+patient.getIdPatient()));

        }
        
        @RequestMapping(value="saveNewConsultation")
        public @ResponseBody void saveNewConsultation(@RequestParam Map<String,String> params){
            DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            DateFormat timeFormat = new SimpleDateFormat("H:mm");
            Date date = new Date();
            Date time = new Date();
            try{
                date = format.parse(params.get("date"));
                time = timeFormat.parse("09:00");
            }catch(Exception e){ e.printStackTrace(); }

            Appointment appointment = new Appointment(date, time, params.get("motive"), "", 0,
                    params.get("weight").isEmpty()? 0:Double.parseDouble(params.get("weight")),
                    params.get("temperature").isEmpty()? 0:Double.parseDouble(params.get("temperature")),
                    params.get("size").isEmpty()? 0:Double.parseDouble(params.get("size")),
                    params.get("ta").isEmpty()? 0:Double.parseDouble(params.get("ta")),
                    params.get("ta2").isEmpty()? 0:Double.parseDouble(params.get("ta2")),
                    params.get("taaverage").isEmpty()? 0:Double.parseDouble(params.get("taaverage")),
                    params.get("pc").isEmpty()? 0:Double.parseDouble(params.get("pc")), 0,
                    doctor, doctor, appointmentStatusService.getById(1), patient);
            appointmentService.create(appointment);
            
            Consultation consultation = new Consultation(
                    params.get("weight").isEmpty()? 0:Double.parseDouble(params.get("weight")),
                    params.get("size").isEmpty()? 0:Double.parseDouble(params.get("size")),
                    params.get("bmi").isEmpty()? 0:Double.parseDouble(params.get("bmi")),
                    params.get("temperature").isEmpty()? 0:Double.parseDouble(params.get("temperature")),
                    params.get("pc").isEmpty()? 0:Double.parseDouble(params.get("pc")),
                    params.get("ta").isEmpty()? 0:Double.parseDouble(params.get("ta")),
                    params.get("ta2").isEmpty()? 0:Double.parseDouble(params.get("ta2")),
                    params.get("taaverage").isEmpty()? 0:Double.parseDouble(params.get("taaverage")),
                    params.get("peea"), params.get("ef"), params.get("prescription"), doctor.getIdStaffMember().getPresciptionNumber(),
                    ctService.getById(Integer.parseInt(params.get("type"))), params.get("abstract"), patient, doctor, appointment);
            consultationService.create(consultation);
            
            //update prescription numbers
            Staffmember staff = doctor.getIdStaffMember();
            int pn = staff.getPresciptionNumber()+1;
            staff.setPresciptionNumber(pn);
            staffService.updateItem(staff);
            
            Consultationmotive cm = cmService.getMotiveByName(params.get("motive"));
            if(cm != null){
                cm.setLastUsed(new Date());
                cmService.updateItem(cm);
            }
            
        }
        
        @RequestMapping(value="saveModifyConsultation")
        public @ResponseBody void saveModifyConsultation(@RequestParam Map<String,String> params){
            Consultation consultation = consultationService.getById(Integer.parseInt(params.get("idConsultation")));
            DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            Date date = new Date();
            try{
                date = format.parse(params.get("date"));
            }catch(Exception e){ e.printStackTrace(); }
            Appointment appointment = consultation.getIdAppointment();
            appointment.setDate(date);
            appointment.setWeight(params.get("weight").isEmpty()? 0:Double.parseDouble(params.get("weight")));
            appointment.setSize(params.get("size").isEmpty()? 0:Double.parseDouble(params.get("size")));
            appointment.setPc(params.get("pc").isEmpty()? 0:Double.parseDouble(params.get("pc")));
            appointment.setTa(params.get("ta").isEmpty()? 0:Double.parseDouble(params.get("ta")));
            appointment.setTa2(params.get("ta2").isEmpty()? 0:Double.parseDouble(params.get("ta2")));
            appointment.setTaAverage(params.get("taaverage").isEmpty()? 0:Double.parseDouble(params.get("taaverage")));
            appointment.setMotive(params.get("motive"));
            appointmentService.updateItem(appointment);
            
            consultation.setWeigth(params.get("weight").isEmpty()? 0:Double.parseDouble(params.get("weight")));
            consultation.setSize(params.get("size").isEmpty()? 0:Double.parseDouble(params.get("size")));
            consultation.setBmi(params.get("bmi").isEmpty()? 0:Double.parseDouble(params.get("bmi")));
            consultation.setPc(params.get("pc").isEmpty()? 0:Double.parseDouble(params.get("pc")));
            consultation.setTa(params.get("ta").isEmpty()? 0:Double.parseDouble(params.get("ta")));
            consultation.setTa2(params.get("ta2").isEmpty()? 0:Double.parseDouble(params.get("ta2")));
            consultation.setTaAverage(params.get("taaverage").isEmpty()? 0:Double.parseDouble(params.get("taaverage")));
            consultation.setPeea(params.get("peea"));
            consultation.setEf(params.get("ef"));
            consultation.setPrescription(params.get("prescription"));
            consultation.setType(ctService.getById(Integer.parseInt(params.get("type"))));
            consultation.setAbstract1(params.get("abstract"));
            consultationService.updateItem(consultation);
            
            Consultationmotive cm = cmService.getMotiveByName(params.get("motive"));
            if(cm != null){
                cm.setLastUsed(new Date());
                cmService.updateItem(cm);
            }
            
        }
        
        /*
        * Method to get a list of all the programmed vaccines
        */
        @RequestMapping(value="getAllPatientImmunization")
        public @ResponseBody JsonPack<Patientvaccine> getImmunization(){
            
            return new JsonPack<Patientvaccine>(pvService.getAllPV());

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
