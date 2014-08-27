package com.carloscortina.demo.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.carloscortina.demo.json.JsonPack;
import com.carloscortina.demo.model.Activity;
import com.carloscortina.demo.model.Ageweight0to36months;
import com.carloscortina.demo.model.Appointment;
import com.carloscortina.demo.model.Appointmentvaccine;
import com.carloscortina.demo.model.AppointmentvaccinePK;
import com.carloscortina.demo.model.Cie10doctor;
import com.carloscortina.demo.model.Cie10doctorPK;
import com.carloscortina.demo.model.Cie10;
import com.carloscortina.demo.model.Commercialname;
import com.carloscortina.demo.model.Consultation;
import com.carloscortina.demo.model.Diagnostic;
import com.carloscortina.demo.model.Documents;
import com.carloscortina.demo.model.Drug;
import com.carloscortina.demo.model.Drugdose;
import com.carloscortina.demo.model.Holyday;
import com.carloscortina.demo.model.Laboratorytest;
import com.carloscortina.demo.model.Laboratorytestresult;
import com.carloscortina.demo.model.Consultationmeasure;
import com.carloscortina.demo.model.ConsultationmeasurePK;
import com.carloscortina.demo.model.Measures;
import com.carloscortina.demo.model.Patient;
import com.carloscortina.demo.model.Patientvaccine;
import com.carloscortina.demo.model.PatientvaccinePK;
import com.carloscortina.demo.model.PatientRelative;
import com.carloscortina.demo.model.Perbacknopat;
import com.carloscortina.demo.model.Record;
import com.carloscortina.demo.model.Relative;
import com.carloscortina.demo.model.Treatment;
import com.carloscortina.demo.model.User;
import com.carloscortina.demo.model.Vaccine;
import com.carloscortina.demo.service.AW0to36MonthsService;
import com.carloscortina.demo.service.ActivityService;
import com.carloscortina.demo.service.ActivityTypeService;
import com.carloscortina.demo.service.AppointmentService;
import com.carloscortina.demo.service.AppointmentStatusService;
import com.carloscortina.demo.service.AppointmentVaccineService;
import com.carloscortina.demo.service.BirthmethodService;
import com.carloscortina.demo.service.Cie10DoctorService;
import com.carloscortina.demo.service.Cie10Service;
import com.carloscortina.demo.service.CommercialNameService;
import com.carloscortina.demo.service.ConsultationService;
import com.carloscortina.demo.service.DiagnosticService;
import com.carloscortina.demo.service.DrugDoseService;
import com.carloscortina.demo.service.DrugService;
import com.carloscortina.demo.service.HolydayService;
import com.carloscortina.demo.service.LaboratoryTestResultService;
import com.carloscortina.demo.service.LaboratoryTestService;
import com.carloscortina.demo.service.ConsultationmeasureService;
import com.carloscortina.demo.service.MeasuresService;
import com.carloscortina.demo.service.PatientService;
import com.carloscortina.demo.service.PatientVaccineService;
import com.carloscortina.demo.service.RecordService;
import com.carloscortina.demo.service.TreatmentService;
import com.carloscortina.demo.service.UserService;
import com.carloscortina.demo.service.VaccineService;
import com.carloscortina.demo.model.Consultationactivity;
import com.carloscortina.demo.model.ConsultationactivityPK;
import com.carloscortina.demo.service.ConsultationactivityService;
import com.carloscortina.demo.service.PatientRelativeService;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.util.Iterator;
import java.util.Map;
import org.springframework.web.bind.annotation.PathVariable;
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
	private RecordService recordService;
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
        private BirthmethodService birthMethodService;
        @Autowired
        private AW0to36MonthsService aw036Service;
        @Autowired
        ConsultationService consultationService;
        @Autowired
        LaboratoryTestService labService;
        @Autowired
        LaboratoryTestResultService labResService;
        @Autowired
        MeasuresService measuresService;
        @Autowired
        ConsultationmeasureService cmService;
        @Autowired
        DiagnosticService diagnosticService;
        @Autowired
        HolydayService holydayService;
        @Autowired
        PatientVaccineService pvService;
        @Autowired
        AppointmentStatusService apsService;
        @Autowired
        AppointmentVaccineService avService;
        @Autowired
        UserService userService;
        @Autowired
        Cie10DoctorService c10dService;
        @Autowired
        ConsultationactivityService caService;
        @Autowired
        PatientRelativeService prService;
        
        private Appointment appointment;
        private Patient patient;
        private User doctor;
        private String[] age;
	
	@RequestMapping(value="{idAppointment}")
	public String startConsultation(Model model,@PathVariable int idAppointment){
		appointment = appointmentService.getById(idAppointment);
		patient = appointment.getIdPatient();
                doctor = appointment.getIdDoctor();
		Record record = recordService.getByPatientId(patient);
		Perbacknopat perBackNoPat = record.getIdPerBackNoPat();
                age = calculateAge(patient.getBirthday()).split("-");
 
		model.addAttribute("father",getFather(patient.getPatientRelativeList()));
		model.addAttribute("mother",getMother(patient.getPatientRelativeList()));
		model.addAttribute("birthday",formatDate(patient.getBirthday()));
		model.addAttribute("age",age);
		model.addAttribute("date",getCurrentDate());
		model.addAttribute("patient",patient);
		model.addAttribute("record",record);
		model.addAttribute("perBackNoPat",perBackNoPat);
                model.addAttribute("doctor",doctor.getIdStaffMember().getName()+" "+doctor.getIdStaffMember().getLastName());
                model.addAttribute("birthmethods", birthMethodService.getAll("Birthmethod"));
                model.addAttribute("measuresCatalog",measuresService.getAll("Measures"));
                model.addAttribute("appWeight",appointment.getWeight());
                model.addAttribute("appTemperature",appointment.getTemperature());
                model.addAttribute("appSize",appointment.getSize());
                model.addAttribute("appTA",appointment.getTa());
                model.addAttribute("appTA2",appointment.getTa2());
                model.addAttribute("appTAA",appointment.getTaAverage());
                model.addAttribute("appPC",appointment.getPc());
                model.addAttribute("patientDrugAllergic",patient.getDrugList());
                model.addAttribute("prescriptionCounter",doctor.getIdStaffMember().getPresciptionNumber());

		return ( "consultation/consultation" );
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
                if( vaccine != null && vaccine.compareTo(activity) != 0 && vaccine.compareTo("") != 0){
                    newActivity.setIdVaccine(vaccineService.getById(Integer.parseInt(vaccine)));
                }
                if( cDefault != null && cDefault.compareTo("") != 0){ 
                    newActivity.setConsultationDefault(Integer.parseInt(cDefault));
                }
                
                activityService.create(newActivity);
                return (Integer.toString(newActivity.getIdActivity()));
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
        
        
        @RequestMapping(value="getConsultationSibilings2")
        public @ResponseBody JsonPack<Patient> getConsultationSibilings2(){
            List<Patient> sibilings = new ArrayList<Patient>();
            for(PatientRelative pr: prService.getSibilingsByPatient(patient.getIdPatient())){
                sibilings.add(patientService.getById(pr.getPatientRelativePK().getIdPatient()));
            }
            return new JsonPack<Patient>(sibilings);
        }
        
        @RequestMapping(value="getConsultationSibilings")
        public @ResponseBody JsonPack<Relative> getConsultationSibilings(){
            
            List<Relative> relatives = getBrothers(patient.getPatientRelativeList());
            
            return (new JsonPack<Relative>(relatives));
        }
        
        @RequestMapping(value="updateAppointmentData")
        public @ResponseBody String updateAppointmentData(@RequestParam(value="algo")int algo){
            return "";
        }
	
	@RequestMapping(value="getDiagnostics")
	public @ResponseBody JsonPack<Cie10doctor> getDiagnosticsByUser()
	{
		//String query = "FROM CIE10Doctor WHERE idUser="+doctor.getIdUser()+" AND cie10.active=1";
		return  new JsonPack<Cie10doctor>(c10dService.getCie10ByUser(doctor.getIdUser()));
		
	}
	
	@RequestMapping(value="getTreatments")
	public @ResponseBody JsonPack<Treatment> getTreatmentsByUser()
	{   
            return new JsonPack<Treatment>(treatmentService.getTreatmentByUser(16));
	}
        
        //This method gives a json response with all the drugs related to the treatment
        @RequestMapping(value="getDrugs")
	public @ResponseBody JsonPack<Drug> getDrugsByUser()
	{
            return new JsonPack<Drug>(drugService.getDrugByUser(doctor.getIdUser()));
	}
	
        //This method gives a json response with all the commercial names related to a drug
        @RequestMapping(value="getDrugsCommercialNames")
	public @ResponseBody JsonPack<Commercialname> allDrugCommercialNames()
	{
            return new JsonPack<Commercialname>(commercialNameService.getCommercialNameByUser(doctor.getIdUser()));
	}
        
        @RequestMapping(value="drugDose")
	public @ResponseBody JsonPack<Drugdose> getDrugDoseByDrugId(@RequestParam int drugId)
	{
		String query = "FROM DrugDose t WHERE t.idDrug = " + drugId;
                
		JsonPack<Drugdose> result = new JsonPack<Drugdose>(drugDoseService.getListOfItem(query));
		return result;
	}
        
        //This method returns a json with all the activities 
        @RequestMapping(value={"/getActivities","getActivities"})
	public @ResponseBody JsonPack<Activity> getActivities()
	{
            return  new JsonPack<Activity>(activityService.getActivitiesByUser(doctor.getIdUser()));
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
            InputStream inputStream;
            OutputStream outputStream;
            
            
            Iterator<String> itr = request.getFileNames();
            MultipartFile file = request.getFile(itr.next());
            
            String fileName = file.getOriginalFilename();

            try{
                inputStream = file.getInputStream();
                File newFolder = new File("/Volumes/2nd_HDD/Documents/test/Files/paciente"+patient.getIdPatient());
                newFolder.mkdir();
                File newFile = new File("/Volumes/2nd_HDD/Documents/test/Files/paciente"+patient.getIdPatient()+"/"+fileName);
                if(!newFile.exists()){
                    newFile.createNewFile();
                }
                outputStream = new FileOutputStream(newFile);
                int read;
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
        public @ResponseBody JsonPack<Documents> getDocuments(){
            File folder = new File("E:\\Documents\\Paidos\\Files\\paciente"+patient.getIdPatient());
            File[] listOfFiles = folder.listFiles();
            List<Documents> documents = new ArrayList<Documents>();
            
            if(listOfFiles != null){
                for(int i = 0; i < listOfFiles.length; i++) {
                    if (listOfFiles[i].isFile()) {

                        Documents doc = new Documents();
                        doc.setDescription(listOfFiles[i].getName());
                        //doc.setDeleteBtn("<button type='button' class='btn btn-danger' onclick='deleteDocument(this);'>Eliminar</button>");
                        documents.add(doc);

                    } else if (listOfFiles[i].isDirectory()) {
                        System.out.println("Directory " + listOfFiles[i].getName());
                    }
                }
            }
            JsonPack<Documents> result = new JsonPack<Documents>(documents);
            
            return result;
        }
        
        @RequestMapping(value="deletePatientDocument")
        public @ResponseBody String deleteDocument(String file){
            try{
                File fileDelete = new File("E:\\Documents\\Paidos\\Files\\paciente"+patient.getIdPatient()+"/"+file);
                fileDelete.delete();
                
            }catch(Exception e){
                e.printStackTrace();
            }
            return "";
        }
        
        @RequestMapping(value="openPatientDocument")
        public @ResponseBody String openDocument(String file){
            try{
                if(Desktop.isDesktopSupported()){
                    Desktop.getDesktop().open(new File("/Volumes/2nd_HDD/Documents/test/Files/paciente"+patient.getIdPatient()+"/"+file));
                }
            }catch (IOException ioe){
                ioe.printStackTrace();
            }
            
            return "success";
        }
        
        //Section: Graphs/Charts
 
        //Section: Laboratory Test Tabs
        
        @RequestMapping(value="getLaboratoryTests")
        public @ResponseBody JsonPack<Laboratorytest> getAllLaboratoryTest(){
            return new JsonPack<Laboratorytest> (labService.getAll("LaboratoryTest"));
        
        }
        
        @RequestMapping(value="getLaboratoryTestsPatientData")
        public @ResponseBody JsonPack<Laboratorytestresult> getLaboratoryTestByPatient(){
            
            String query = "FROM LaboratoryTestResult l where l.idPatient="+patient.getIdPatient();
            List<Laboratorytestresult> ret = labResService.getListOfItem(query);
            
            return new JsonPack<Laboratorytestresult>(ret);
        }
        
        @RequestMapping(value="saveLaboratoryTestResult")
        public @ResponseBody String saveLaboratoryTestResult(String date,int idLaboratoryTest,String testResult){
            SimpleDateFormat sdf = new SimpleDateFormat("dd/M/yyyy");
            try{
                Date fDate = sdf.parse(date);
                Laboratorytest lab = labService.getById(idLaboratoryTest);
                System.out.println(fDate);
                Laboratorytestresult labRes = new Laboratorytestresult(testResult, fDate, patient, lab);
                labResService.create(labRes);
                
            }catch (Exception e){ e.printStackTrace();}

            return "Se ha guardado la informacion correctamente";
        }
        
        @RequestMapping(value="editLaboratoryTestResult")
        public @ResponseBody String editLaboratoryTestResult(int idLaboratoryTestResult,String date,int idLaboratoryTest,String result){
            SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy");
            try{
                Date fDate = sdf.parse(date);
                Laboratorytestresult labRes = labResService.getById(idLaboratoryTestResult);
                labRes.setDate(fDate);
                labRes.setIdLaboratoryTest(labService.getById(idLaboratoryTest));
                labRes.setResult(result);
                
                labResService.updateItem(labRes);
                
            }catch (Exception e){ e.printStackTrace();}

            return "Se ha guardado la informacion correctamente";
        }
        
        @RequestMapping(value="deleteLaboratoryTestResult")
        public @ResponseBody String delteLaboratoryTestResult(int idResult){
            Laboratorytestresult labRes = labResService.getById(idResult);
            labResService.delete(labRes);
            
            return "Se ha borrado la informacion correctamente";
        }
        //Alergys
        @RequestMapping(value="getPatientAlergicDrugs")
        public @ResponseBody JsonPack<Drug> getPatientAlergicDrugs(){
            
            List<Drug> alergicList = patient.getDrugList();
            return new JsonPack<Drug>(alergicList);
        }
        
        @RequestMapping(value="getPatientAlergicAvaibleDrugs")
        public @ResponseBody JsonPack<Drug> getPatientAlergicAvaibleDrugs(){
            
            List<Drug> drugList = drugService.getDrugByUser(doctor.getIdUser());
            drugList.removeAll(patient.getDrugList());
            return new JsonPack<Drug>(drugList);
        }
        
        @RequestMapping(value="addPatientAlergicDrug")
        public @ResponseBody String getPatientAlergicDrug(int idDrug){
            List<Drug> alergicList = patient.getDrugList();
            alergicList.add(drugService.getById(idDrug));
            patient.setDrugList(alergicList);
            patientService.mergePatient(patient);
            return "Success";
        }
        
        @RequestMapping(value="deletePatientAlergicDrug")
        public @ResponseBody String deletePatientAlergicDrug(int idDrug){
            
            List<Drug> alergicList = patient.getDrugList();
            alergicList.remove(drugService.getById(idDrug));
            patientService.mergePatient(patient);
            return "Success";
        }
        
        //Section: inmunization
        
        @RequestMapping(value="getProgrammedVaccine")
        public @ResponseBody JsonPack<Patientvaccine> getProgrammedVaccine(){
            Date currentDate = new Date();
            List<Patientvaccine>  pv = pvService.getListOfItem("FROM PatientVaccine WHERE idPatient="+patient.getIdPatient());
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
            pv = pvService.getListOfItem("FROM PatientVaccine WHERE idPatient="+patient.getIdPatient());
            return new JsonPack<Patientvaccine>(pv);
        }
        
        //Return all the vaccines in the system even not active
        @RequestMapping(value="getExpiredVaccine")
        public @ResponseBody JsonPack<Patientvaccine> getExpiredVaccine(){
            
            List<Patientvaccine> pvList = pvService.getListOfItem("FROM PatientVaccine WHERE suspended="+2);
            return new JsonPack<Patientvaccine>(pvList);
        }
        
        @RequestMapping(value="getSuspendedVaccine")
        public @ResponseBody JsonPack<Patientvaccine> getSuspendedVaccine(){
            
            List<Patientvaccine> pvList = pvService.getListOfItem("FROM PatientVaccine WHERE suspended="+1);
            return new JsonPack<Patientvaccine>(pvList);
        }
        
        @RequestMapping(value="getAvaibleVaccine")
        public @ResponseBody JsonPack<Vaccine> getAvaibleVaccine(){
            List<Vaccine> vaccineList = vaccineService.getAll("Vaccine");
            List<Patientvaccine> pvList = pvService.getAll("PatientVaccine");
            List<Vaccine> remove = new ArrayList<Vaccine>();
            for(Vaccine vaccine: vaccineList){
                for(Patientvaccine pv: pvList){
                    if(pv.getVaccine().getIdVaccine() == vaccine.getIdVaccine()){
                        //this vaccine has already been programmed
                        remove.add(vaccine);
                    }
                }
            }
            vaccineList.removeAll(remove);
            return new JsonPack<Vaccine>(vaccineList);
        }
        
        //Add a new programmed vaccine manually
        @RequestMapping(value="addProgrammedVaccine")
        public @ResponseBody String addProgrammedVaccine(int idVaccine){
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
 
            
            return "";
        }
        
        @RequestMapping(value="deleteProgrammedVaccine")
        public @ResponseBody String deleteProgrammedVaccine(int idVaccine){
            Patientvaccine pv = pvService.getById(new PatientvaccinePK(patient.getIdPatient(), idVaccine));
            pvService.delete(pv);
            List<Appointmentvaccine> result = avService.getListOfItem("FROM AppointmentVaccine WHERE idPatient="+patient.getIdPatient()+" AND idVaccine="+idVaccine);
            if(!result.isEmpty()){
                Appointment ap = result.get(0).getAppointment();
                ap.setIdStatus(apsService.getById(11));
                appointmentService.updateItem(ap);
            }
            
            return "success";
        }
        
        @RequestMapping(value="suspendProgrammedVaccine")
        public @ResponseBody String suspendProgrammedVaccine(int idVaccine){
            Patientvaccine pv = pvService.getById(new PatientvaccinePK(patient.getIdPatient(), idVaccine));
            pv.setSuspended((short)1);
            pv.setProgramManual((short)1);
            pv.setSuspensionDate(new Date());
            pvService.updateItem(pv);
            return "success";
        }
        
        @RequestMapping(value="retriveProgrammedVaccine")
        public @ResponseBody String retriveProgrammedVaccine(int idVaccine){
            Patientvaccine pv = pvService.getById(new PatientvaccinePK(patient.getIdPatient(), idVaccine));
            pv.setSuspended((short)2);
            pv.setProgramManual((short)1);
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
            List<Vaccine> vaccines = vaccineService.getAll("Vaccine");
            List<Holyday> holydays = holydayService.getAll("Holyday");
              
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
                    List<Patientvaccine> pvList = pvService.getListOfItem("FROM PatientVaccine WHERE idPatient="+patient.getIdPatient()+" AND idVaccine="+vaccine.getIdVaccine());
                    List<Appointmentvaccine> appointments = avService.getListOfItem("FROM AppointmentVaccine WHERE idPatient="+1+" AND idVaccine="+vaccine.getIdVaccine());

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
                                    newPV.setSuspended((short)1);
                                    newPV.setSuspensionDate(currentDate);
                                    newPV.setNotes("Suspendida por el sistema");
                                }else{
                                    newPV.setSuspended((short)2);
                                }

                                //If the vaccine has been suspended , dont program an appointment
                            }else{
                                newPV.setSuspended((short)0);
                                //Program a new appointment for each vaccine
                                Appointment newAppointment = new Appointment();

                                try{
                                    newAppointment.setDate(cal.getTime());
                                    newAppointment.setStartTime(timeFormat.parse("09:00:00"));
                                    newAppointment.setIdPatient(patient);
                                    newAppointment.setMotive(vaccine.getVaccine());
                                    newAppointment.setIdStatus(apsService.getById(9));
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
                                        currentAppointment.setIdStatus(apsService.getById(11));
                                        appointmentService.updateItem(currentAppointment);
                                    }
                                }else{
                                    Appointment currentAppointment = appointments.get(0).getAppointment();
                                    currentAppointment.setNotes("Cancelada por el sistema");
                                    currentAppointment.setIdStatus(apsService.getById(11));
                                    appointmentService.updateItem(currentAppointment);
                                }

                            }else{
                                newPV.setSuspended((short)0);

                                //if theres a previous appointment update it, if hasnt been modified or added by a user.
                                if(f){
                                    if(appointments.get(0).getAppointment().getProgrammedBySystem() == 1){
                                        Appointment currentAppointment = appointments.get(0).getAppointment();
                                        currentAppointment.setDate(cal.getTime());
                                        currentAppointment.setIdStatus(apsService.getById(9));
                                        appointmentService.updateItem(currentAppointment);
                                    }
                                }else{
                                    Appointment currentAppointment = appointments.get(0).getAppointment();
                                    currentAppointment.setDate(cal.getTime());
                                    currentAppointment.setIdStatus(apsService.getById(9));
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
                                        newAppointment.setIdStatus(apsService.getById(9));
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
                                            currentAppointment.setIdStatus(apsService.getById(11));
                                            appointmentService.updateItem(currentAppointment);
                                        }
                                    }else{
                                        Appointment currentAppointment = appointments.get(0).getAppointment();
                                        currentAppointment.setNotes("Cancelada por el sistema");
                                        currentAppointment.setIdStatus(apsService.getById(11));
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
                                            currentAppointment.setIdStatus(apsService.getById(9));
                                            appointmentService.updateItem(currentAppointment);
                                        }
                                    }else{
                                        Appointment currentAppointment = appointments.get(0).getAppointment();
                                        currentAppointment.setDate(cal.getTime());
                                        currentAppointment.setIdStatus(apsService.getById(9));
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
        
        @RequestMapping(value="editProgrammedVaccine")
        public @ResponseBody String editPatientProgrammedVaccine(@RequestParam Map<String,String> params){
            
            PatientvaccinePK id = new PatientvaccinePK(patient.getIdPatient(),Integer.parseInt(params.get("pvvaccine")));
            Patientvaccine currentPV = pvService.getById(id);
            
            
            Patientvaccine pv = new Patientvaccine();
            pv.setPatientvaccinePK(id);
            try{
                Date applicationDate = (params.get("applicationDate").isEmpty()) ? null : new SimpleDateFormat("dd/MM/yyyy").parse(params.get("applicationDate"));
                Date programmedDate = (params.get("applicationDate").isEmpty()) ? null : new SimpleDateFormat("dd/MM/yyyy").parse(params.get("programedDate"));
                Date expirationDate = (params.get("applicationDate").isEmpty()) ? null : new SimpleDateFormat("dd/MM/yyyy").parse(params.get("expirationDate"));
                pv.setApplicationDate(applicationDate);
                pv.setProgramedDate(programmedDate);
                pv.setBatch(params.get("batch"));
                pv.setName(params.get("name"));
                pv.setExpirationDate(expirationDate);
                pv.setProgramManual((short)1);
                if(params.get("suspended") != null){
                    pv.setSuspended((short)1);
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
        
        //Section: Measures
        
        @RequestMapping(value="getMeasuresCatalog")
        public @ResponseBody JsonPack<Measures> getMeasuresCatalog(){
            List<Measures> catalog = measuresService.getListOfItem("FROM Measures WHERE idUser="+doctor.getIdUser());
            
            return new JsonPack<Measures>(catalog); 
            
        }
        
        @RequestMapping(value="saveNewMeasure")
        public @ResponseBody String saveMeasure(String measure,String units){
            //Measures newMeasure = new Measures(measure, units,doctor);
            //measuresService.create(newMeasure);
            
            return "";
            
        }
        
        /*
        This method recives a map of values containing all the data necessary to persist the consultation,
        activities,diagnostics and measures.
        activitySize holds the total number of activities in the consultation.
        
        */
        @RequestMapping(value="saveConsultation")
        public @ResponseBody String saveConsultation(@RequestParam Map<String,String> parameters){
            
            //As the DB is programmed with the consultation as foreign key, first the consultation must be created
            Consultation consultation = filterConsultation(parameters);
            //Save the diagnostic
            List<Diagnostic> diagnostic = filterDiagnostic(parameters);
            List<Consultationactivity> caList = new ArrayList<Consultationactivity>();
            List<Consultationmeasure> cmList = new ArrayList<Consultationmeasure>();
            
            Integer activitySize = Integer.parseInt(parameters.get("activitySize"));
            consultation.setIdAppointment(appointment);
            
            for(Diagnostic d: diagnostic){
                consultation.getDiagnosticList().add(d);
            }
            
            consultationService.create(consultation);
            
            Integer measureSize = Integer.parseInt(parameters.get("measureSize"));
            
            for(int i=0; i < measureSize; i++){
                ConsultationmeasurePK cmPK = new ConsultationmeasurePK(consultation.getIdConsultation(), Integer.parseInt(parameters.get("measure"+i)));
                Consultationmeasure cm = new Consultationmeasure(cmPK, parameters.get("mValue"+i));
                
                cmList.add(cm);

            }
            
            for(int i=0; i < activitySize; i++){
                Activity a = activityService.getById(Integer.parseInt(parameters.get("activity"+i)));
                ConsultationactivityPK caPK = new ConsultationactivityPK(consultation.getIdConsultation(), a.getIdActivity());
                Consultationactivity ca = new Consultationactivity(caPK, Double.parseDouble(parameters.get("activityPrice"+i)), Integer.parseInt(parameters.get("activityInclude"+i)));
                caList.add(ca);
            }

            consultation.setConsultationactivityList(caList);

            consultationService.updateItem(consultation);
            //Update the appointment status
            appointment.setIdStatus(apsService.getById(1));
            appointmentService.mergeItem(appointment);
            return "patients/file/"+patient.getIdPatient();
            
        }
        
        //Filter the parameters of a basic consultation based on the methods of the class Consultation
        private Consultation filterConsultation(Map<String,String> params){
            Consultation consultation = new Consultation();
            
            for (Map.Entry<String, String> entry : params.entrySet()) {
                
                //Fill consultation
                try{
                    for(Method m: consultation.getClass().getMethods()){
                        if(m.getName().startsWith("set")){
                            if( m.getName().equalsIgnoreCase("set"+entry.getKey())){
                                if(!entry.getValue().isEmpty()){
                                    if( m.getName().equalsIgnoreCase("setPeea") ||
                                            m.getName().equalsIgnoreCase("setEf") || 
                                                m.getName().equalsIgnoreCase("setPrescription")){
                                        m.invoke(consultation,entry.getValue());
                                    }else if(m.getName().equalsIgnoreCase("setPrescriptionNumber")){
                                        m.invoke(m.getName(), Integer.parseInt(entry.getValue()));
                                    }else{
                                        m.invoke(consultation,Double.parseDouble(entry.getValue()));
                                    }
                                }
                            }
                        }
                    }
                }catch(Exception e){ 
                    e.printStackTrace();
                }
            }

            consultation.setIdDoctor(doctor);
            consultation.setIdPatient(patient);
            //consultation.setIdAppointment(appointmentService.getById(idAppointment));
            
            return consultation;
        }
        
        private List<Diagnostic> filterDiagnostic(Map<String,String> params){
            
            List<Diagnostic> diagnostics= new ArrayList<Diagnostic>();
            
            Integer diagSize = Integer.parseInt(params.get("diagnosticSize"));
            
            for(int i=0; i < diagSize; i++){
                Diagnostic diagnostic = new Diagnostic();
                
                String d = params.get("idCIE10"+i);
                String t = params.get("idTreatment"+i);
                String m = params.get("idMedecine"+i);
                String n = params.get("IdCommercialName"+i);
                
                if(d.isEmpty()){
                    if(!m.isEmpty()){
                        if(n.isEmpty()){
                            //m
                            diagnostic.setIdMedecine(drugService.getById(Integer.parseInt(m)));
                        }else{
                            //mn
                            diagnostic.setIdMedecine(drugService.getById(Integer.parseInt(m)));
                            diagnostic.setIdCommercialName(commercialNameService.getById(Integer.parseInt(n)));
                        }
                    }
                }else if(m.isEmpty()){
                    //dt
                    diagnostic.setIdCIE10(cie10Service.getById(Integer.parseInt(d)));
                    diagnostic.setIdTreatment(treatmentService.getById(Integer.parseInt(t)));
                }else{
                    if(n.isEmpty()){
                        //dtm
                        diagnostic.setIdCIE10(cie10Service.getById(Integer.parseInt(d)));
                        diagnostic.setIdTreatment(treatmentService.getById(Integer.parseInt(t)));
                        diagnostic.setIdMedecine(drugService.getById(Integer.parseInt(m)));
                    }else{
                        //dtmn
                        diagnostic.setIdCIE10(cie10Service.getById(Integer.parseInt(d)));
                        diagnostic.setIdTreatment(treatmentService.getById(Integer.parseInt(t)));
                        diagnostic.setIdMedecine(drugService.getById(Integer.parseInt(m)));
                        diagnostic.setIdCommercialName(commercialNameService.getById(Integer.parseInt(n)));
                        Cie10doctorPK cdPK = new Cie10doctorPK(Integer.parseInt(d), doctor.getIdUser());
                        Cie10doctor updateCie10 = new Cie10doctor(cdPK, new Date());
                        c10dService.updateItem(updateCie10);
                    }
                }
                diagnosticService.create(diagnostic);
                diagnostics.add(diagnostic);
            }
           
            return diagnostics;
        }

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
	
	private List<Relative> getBrothers(List<PatientRelative> relatives){
		
		List<Relative> list = new ArrayList<Relative>();
		
		for(PatientRelative r: relatives)
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
