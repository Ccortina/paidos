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
import com.carloscortina.demo.model.ActivityConsultation;
import com.carloscortina.demo.model.Ageweight0To36Months;
import com.carloscortina.demo.model.Appointment;
import com.carloscortina.demo.model.AppointmentStatus;
import com.carloscortina.demo.model.AppointmentVaccine;
import com.carloscortina.demo.model.AppointmentVaccinePK;
import com.carloscortina.demo.model.AuxGraphTable;
import com.carloscortina.demo.model.CIE10Doctor;
import com.carloscortina.demo.model.Cie10;
import com.carloscortina.demo.model.CommercialName;
import com.carloscortina.demo.model.Consultation;
import com.carloscortina.demo.model.ConsultationDiagnostic;
import com.carloscortina.demo.model.Diagnostic;
import com.carloscortina.demo.model.Document;
import com.carloscortina.demo.model.Drug;
import com.carloscortina.demo.model.DrugDose;
import com.carloscortina.demo.model.GraphData;
import com.carloscortina.demo.model.Holyday;
import com.carloscortina.demo.model.LaboratoryTest;
import com.carloscortina.demo.model.LaboratoryTestResult;
import com.carloscortina.demo.model.MeasureConsultation;
import com.carloscortina.demo.model.Measures;
import com.carloscortina.demo.model.Patient;
import com.carloscortina.demo.model.PatientVaccine;
import com.carloscortina.demo.model.PatientVaccinePK;
import com.carloscortina.demo.model.Patient_Relative;
import com.carloscortina.demo.model.PerBackNoPat;
import com.carloscortina.demo.model.Record;
import com.carloscortina.demo.model.Relative;
import com.carloscortina.demo.model.Treatment;
import com.carloscortina.demo.model.User;
import com.carloscortina.demo.model.Vaccine;
import com.carloscortina.demo.service.AW0to36MonthsService;
import com.carloscortina.demo.service.ActivityConsultationService;
import com.carloscortina.demo.service.ActivityService;
import com.carloscortina.demo.service.ActivityTypeService;
import com.carloscortina.demo.service.AppointmentService;
import com.carloscortina.demo.service.AppointmentStatusService;
import com.carloscortina.demo.service.AppointmentVaccineService;
import com.carloscortina.demo.service.BirthmethodService;
import com.carloscortina.demo.service.Cie10DoctorService;
import com.carloscortina.demo.service.Cie10Service;
import com.carloscortina.demo.service.CommercialNameService;
import com.carloscortina.demo.service.ConsultationDiagnosticService;
import com.carloscortina.demo.service.ConsultationService;
import com.carloscortina.demo.service.DiagnosticService;
import com.carloscortina.demo.service.DrugDoseService;
import com.carloscortina.demo.service.DrugService;
import com.carloscortina.demo.service.HolydayService;
import com.carloscortina.demo.service.LaboratoryTestResultService;
import com.carloscortina.demo.service.LaboratoryTestService;
import com.carloscortina.demo.service.MeasureConsultationService;
import com.carloscortina.demo.service.MeasuresService;
import com.carloscortina.demo.service.PatientService;
import com.carloscortina.demo.service.PatientVaccineService;
import com.carloscortina.demo.service.RecordService;
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
        ConsultationDiagnosticService cdService;
        @Autowired
        MeasureConsultationService mcService;
        @Autowired
        DiagnosticService diagnosticService;
        @Autowired
        ActivityConsultationService acService;
        @Autowired
        HolydayService holydayService;
        @Autowired
        PatientVaccineService pvService;
        @Autowired
        AppointmentStatusService apsService;
        @Autowired
        AppointmentVaccineService avService;
        @Autowired
        UserService uService;
        @Autowired
        Cie10DoctorService c10dService;
        
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
		PerBackNoPat perBackNoPat = record.getIdPerBackNoPat();
                age = calculateAge(patient.getBirthday()).split("-");
 
		model.addAttribute("father",getFather(patient.getPatientRelativeList()));
		model.addAttribute("mother",getMother(patient.getPatientRelativeList()));
		model.addAttribute("birthday",formatDate(patient.getBirthday()));
		model.addAttribute("age",age);
		model.addAttribute("date",getCurrentDate());
		model.addAttribute("patient",patient);
		model.addAttribute("record",record);
		model.addAttribute("perBackNoPat",perBackNoPat);
                model.addAttribute("doctor",doctor);
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
        public @ResponseBody JsonPack<Relative> getConsultationSibilings(){
            
            List<Relative> relatives = getBrothers(patient.getPatientRelativeList());
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
	
	@RequestMapping(value="getDiagnostics")
	public @ResponseBody JsonPack<CIE10Doctor> allDiagnostics()
	{
		String query = "FROM CIE10Doctor WHERE idUser="+doctor.getIdUser()+" AND cie10.active=1";
		return  new JsonPack<CIE10Doctor>(c10dService.getListOfItem(query));
		
	}
	
	@RequestMapping(value="getTreatmentByDiagnostic")
	public @ResponseBody JsonPack<Treatment> getTreatmentsBasedOnDiagnostic(int diagnosticId)
	{   
            String query;

            if(diagnosticId <= 0){
                query = "FROM Treatment";
            } else {
                query = "FROM Treatment t join t.cie10List d WHERE d.idCIE10 = " + diagnosticId + "AND t.idUser="+doctor.getIdUser();
            }

            
            return new JsonPack<Treatment>(treatmentService.getListOfItem(query));
	}
        
        //This method gives a json response with all the drugs related to the treatment
        @RequestMapping(value="getDrugsByTreatment")
	public @ResponseBody JsonPack<Drug> allDrugsByTreatment(int treatmentId)
	{
            String query;
            
            if( treatmentId <= 0){
                query = "FROM Drug";
            }else{
                query = "FROM Drug t join t.treatmentList d WHERE d.idTreatment = "+treatmentId+" AND t.idUser="+doctor.getIdUser();
            }
            
            return new JsonPack<Drug>(drugService.getListOfItem(query));
            
	}
	
        //This method gives a json response with all the commercial names related to a drug
        @RequestMapping(value="getDrugsCommercialNames")
	public @ResponseBody JsonPack<CommercialName> allDrugCommercialNames(@RequestParam int drugId)
	{
            String query;
            
            if( drugId <= 0){
                query = "FROM CommercialName";
            }else{
                query = "FROM CommercialName t WHERE t.drugId = "+drugId;
            }
                
            return new JsonPack<CommercialName>(commercialNameService.getListOfItem(query));
	}
        
        @RequestMapping(value="drugDose")
	public @ResponseBody JsonPack<DrugDose> getDrugDoseByDrugId(@RequestParam int drugId)
	{
		String query = "FROM DrugDose t WHERE t.idDrug = " + drugId;
                
		JsonPack<DrugDose> result = new JsonPack<DrugDose>(drugDoseService.getListOfItem(query));
		return result;
	}
        
        //Return a json with all the Drugs(Medecine) in the system
        @RequestMapping(value="getDrugs")
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
        public @ResponseBody String deleteDocument(String file){
            try{
                File fileDelete = new File("/Volumes/2nd_HDD/Documents/test/Files/paciente"+patient.getIdPatient()+"/"+file);
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
        
        @RequestMapping(value="getGraphPatientData")
        public @ResponseBody JsonPack<AuxGraphTable> getGraphPatientConsults(){
            
            String query = "FROM Consultation c where c.idPatient="+patient.getIdPatient();
            List<Consultation> list = consultationService.getListOfItem(query);
            List<AuxGraphTable> ret = new ArrayList<AuxGraphTable>();
            AuxGraphTable data;
            
            for(Consultation cs: list){
                data = new AuxGraphTable(cs.getIdConsultation(),
                                            cs.getIdAppointment().getDate(),
                                                "8-3-1",
                                                    cs.getWeigth() == null? 0: cs.getWeigth(),
                                                        cs.getSize()== null? 0: cs.getSize(),
                                                            cs.getPc() == null? 0: cs.getPc(),
                                                                cs.getTa() == null? 0: cs.getTa(),
                                                                    cs.getTa2() == null? 0: cs.getTa2(),
                                                                        cs.getTaAverage() == null? 0: cs.getTaAverage(),
                                                                            cs.getTemperature() == null? 0: cs.getTemperature(),
                                                                                cs.getWeigth() == null? 0: cs.getWeigth()/Math.pow((cs.getSize()== null? 0: cs.getSize()/100.00),2));
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
        
        @RequestMapping(value="getLaboratoryTests")
        public @ResponseBody JsonPack<LaboratoryTest> getAllLaboratoryTest(){
            return new JsonPack<LaboratoryTest> (labService.getAll("LaboratoryTest"));
        
        }
        
        @RequestMapping(value="getLaboratoryTestsPatientData")
        public @ResponseBody JsonPack<LaboratoryTestResult> getLaboratoryTestByPatient(){
            
            String query = "FROM LaboratoryTestResult l where l.idPatient="+patient.getIdPatient();
            List<LaboratoryTestResult> ret = labResService.getListOfItem(query);
            
            return new JsonPack<LaboratoryTestResult>(ret);
        }
        
        @RequestMapping(value="saveLaboratoryTestResult")
        public @ResponseBody String saveLaboratoryTestResult(String date,int idLaboratoryTest,String testResult){
            SimpleDateFormat sdf = new SimpleDateFormat("dd/M/yyyy");
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
        //Alergys
        @RequestMapping(value="getPatientAlergicDrugs")
        public @ResponseBody JsonPack<Drug> getPatientAlergicDrugs(){
            
            List<Drug> alergicList = patient.getDrugList();
            return new JsonPack<Drug>(alergicList);
        }
        
        @RequestMapping(value="getPatientAlergicAvaibleDrugs")
        public @ResponseBody JsonPack<Drug> getPatientAlergicAvaibleDrugs(){
            
            List<Drug> alergicList = patient.getDrugList();
            List<Drug> drugList = drugService.getListOfItem("FROM Drug WHERE idUser="+doctor.getIdUser());
            drugList.removeAll(alergicList);
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
        
        //Return all the vaccines in the system even not active
        @RequestMapping(value="getExpiredVaccine")
        public @ResponseBody JsonPack<PatientVaccine> getExpiredVaccine(){
            
            List<PatientVaccine> pvList = pvService.getListOfItem("FROM PatientVaccine WHERE suspended="+2);
            return new JsonPack<PatientVaccine>(pvList);
        }
        
        @RequestMapping(value="getSuspendedVaccine")
        public @ResponseBody JsonPack<PatientVaccine> getSuspendedVaccine(){
            
            List<PatientVaccine> pvList = pvService.getListOfItem("FROM PatientVaccine WHERE suspended="+1);
            return new JsonPack<PatientVaccine>(pvList);
        }
        
        @RequestMapping(value="getAvaibleVaccine")
        public @ResponseBody JsonPack<Vaccine> getAvaibleVaccine(){
            List<Vaccine> vaccineList = vaccineService.getAll("Vaccine");
            List<PatientVaccine> pvList = pvService.getAll("PatientVaccine");
            List<Vaccine> remove = new ArrayList<Vaccine>();
            for(Vaccine vaccine: vaccineList){
                for(PatientVaccine pv: pvList){
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
            PatientVaccine patientVaccine = new PatientVaccine(patient.getIdPatient(), idVaccine);
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
            PatientVaccine pv = pvService.getById(new PatientVaccinePK(patient.getIdPatient(), idVaccine));
            pvService.delete(pv);
            List<AppointmentVaccine> result = avService.getListOfItem("FROM AppointmentVaccine WHERE idPatient="+patient.getIdPatient()+" AND idVaccine="+idVaccine);
            if(!result.isEmpty()){
                Appointment ap = result.get(0).getAppointment();
                ap.setIdStatus(apsService.getById(11));
                appointmentService.updateItem(ap);
            }
            
            return "success";
        }
        
        @RequestMapping(value="suspendProgrammedVaccine")
        public @ResponseBody String suspendProgrammedVaccine(int idVaccine){
            PatientVaccine pv = pvService.getById(new PatientVaccinePK(patient.getIdPatient(), idVaccine));
            pv.setSuspended((short)1);
            pv.setProgramManual((short)1);
            pv.setSuspensionDate(new Date());
            pvService.updateItem(pv);
            return "success";
        }
        
        @RequestMapping(value="retriveProgrammedVaccine")
        public @ResponseBody String retriveProgrammedVaccine(int idVaccine){
            PatientVaccine pv = pvService.getById(new PatientVaccinePK(patient.getIdPatient(), idVaccine));
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
                                if( cal.getTime().compareTo(holyday.getDate()) == 0 ){
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
                    List<PatientVaccine> pvList = pvService.getListOfItem("FROM PatientVaccine WHERE idPatient="+patient.getIdPatient()+" AND idVaccine="+vaccine.getIdVaccine());
                    List<AppointmentVaccine> appointments = avService.getListOfItem("FROM AppointmentVaccine WHERE idPatient="+1+" AND idVaccine="+vaccine.getIdVaccine());

                    if(pvList.isEmpty()){
                        if(appointments.isEmpty()){
                            //If there are no previous inmunization appopintments or programmed vaccines
                            //Create new Programmed Vaccines
                            PatientVaccine newPV = new PatientVaccine();
                            PatientVaccinePK newPVPK = new PatientVaccinePK();
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
                                    newAppointment.setEndTime(timeFormat.parse("09:30:00"));
                                    newAppointment.setIdPatient(patient);
                                    newAppointment.setMotive(vaccine.getVaccine());
                                    newAppointment.setIdStatus(apsService.getById(9));
                                    newAppointment.setRegisteredBy(doctor);
                                    newAppointment.setIdDoctor(doctor);
                                    newAppointment.setImmunization(true);
                                    newAppointment.setProgrammedBySystem((short)1);
                                }catch(ParseException p){
                                    p.printStackTrace();
                                }
                                //Register the appointment
                                appointmentService.create(newAppointment);
                                AppointmentVaccinePK apvPK = new AppointmentVaccinePK(newAppointment.getIdAppointment(), vaccine.getIdVaccine(), patient.getIdPatient());
                                AppointmentVaccine newAV = new AppointmentVaccine(apvPK);
                                avService.create(newAV);

                            }
                            //Register the programmed vaccine
                            newPVPK.setIdPatient(patient.getIdPatient());
                            newPVPK.setIdVaccine(vaccine.getIdVaccine());
                            newPV.setPatientVaccinePK(newPVPK);
                            pvService.create(newPV);
                        }else{ // End (pv,empty),(av,empty)

                            //If there are appointments but no programmed vaccines
                            //Update appointments and create programmed vaccines
                            PatientVaccine newPV = new PatientVaccine();
                            PatientVaccinePK newPVPK = new PatientVaccinePK();
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
                            newPV.setPatientVaccinePK(newPVPK);
                            pvService.create(newPV);
                        }
                    }else{ // End (pv,empty),(av,not empty)
                        //If there are previous programmed vaccines 

                        if(appointments.isEmpty()){
                            //if there are programmed vaccine but no appointments
                            //Update the programmed vaccine
                            //if the programmed vaccine has been edited or modified by a user, dont modify

                            if(pvList.get(0).getProgramManual() != 1 || !d){
                                PatientVaccine currentPv = pvList.get(0);
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
                                        newAppointment.setEndTime(timeFormat.parse("09:30:00"));
                                        newAppointment.setIdPatient(patient);
                                        newAppointment.setMotive(vaccine.getVaccine());
                                        newAppointment.setIdStatus(apsService.getById(9));
                                        newAppointment.setRegisteredBy(doctor);
                                        newAppointment.setIdDoctor(doctor);
                                        newAppointment.setImmunization(true);
                                        newAppointment.setProgrammedBySystem((short)1);
                                    }catch(ParseException p){
                                        p.printStackTrace();
                                    }
                                    //Register the appointment
                                    appointmentService.create(newAppointment);
                                    AppointmentVaccinePK apvPK = new AppointmentVaccinePK(newAppointment.getIdAppointment(), vaccine.getIdVaccine(), patient.getIdPatient());
                                    AppointmentVaccine newAV = new AppointmentVaccine(apvPK);
                                    avService.create(newAV);

                                }

                                pvService.updateItem(currentPv);

                            } 
                        }else{
                            //if there are previous programmed vaccine and previous appointment
                            //update both
                            if(pvList.get(0).getProgramManual() != 1 || !d){
                                PatientVaccine currentPv = pvList.get(0);
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
        
        @RequestMapping(value="getProgrammedVaccine")
        public @ResponseBody JsonPack<PatientVaccine> getProgrammedVaccine(){
            List<PatientVaccine>  pv = pvService.getListOfItem("FROM PatientVaccine WHERE idPatient="+patient.getIdPatient());
            return new JsonPack<PatientVaccine>(pv);
        }
        
        @RequestMapping(value="editProgrammedVaccine")
        public @ResponseBody String editPatientProgrammedVaccine(@RequestParam Map<String,String> params){
            
            PatientVaccinePK id = new PatientVaccinePK(patient.getIdPatient(),Integer.parseInt(params.get("pvvaccine")));
            PatientVaccine currentPV = pvService.getById(id);
            
            
            PatientVaccine pv = new PatientVaccine();
            pv.setPatientVaccinePK(id);
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
                    List<AppointmentVaccine> avList = avService.getListOfItem("FROM AppointmentVaccine WHERE idVaccine="+id.getIdVaccine()+" AND idPatient="+id.getIdPatient());
                    for(AppointmentVaccine av: avList ){
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
            Measures newMeasure = new Measures(measure, units);
            newMeasure.setIdUser(doctor);
            measuresService.create(newMeasure);
            
            return "";
            
        }
        
        @RequestMapping(value="saveConsultation")
        public @ResponseBody String saveConsultation(@RequestParam Map<String,String> parameters){
            
            Consultation consultation = filterConsultation(parameters);
            List<Diagnostic> diagnostic = filterDiagnostic(parameters);
            //Save Consultation
            Integer activitySize = Integer.parseInt(parameters.get("activitySize"));
            consultation.setIdAppointment(appointment);
            consultationService.create(consultation);
            
            for(int i=0; i < activitySize; i++){
                Activity a = activityService.getById(Integer.parseInt(parameters.get("activity"+i)));
                
                ActivityConsultation ac = new ActivityConsultation(a,consultation);
                acService.create(ac);
            }
            
            for(Diagnostic d: diagnostic){
                ConsultationDiagnostic cd = new ConsultationDiagnostic();
                cd .setIdConsultation(consultation);
                cd.setIdDiagnostic(d);
                
                //Save the relation
                cdService.create(cd);
            }
            
            Integer measureSize = Integer.parseInt(parameters.get("measureSize"));
            
            for(int i=0; i < measureSize; i++){
                MeasureConsultation mes = new MeasureConsultation(parameters.get("mValue"+i), 
                                                                measuresService.getById(Integer.parseInt(parameters.get("measure"+i))),
                                                                consultation);
                mcService.create(mes);
            }

            
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
                    }
                }
                diagnosticService.create(diagnostic);
                diagnostics.add(diagnostic);
            }
           
            return diagnostics;
        }

	private Relative getFather(List<Patient_Relative> relatives)
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
	
	private Relative getMother(List<Patient_Relative> relatives)
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
	
	private List<Relative> getBrothers(List<Patient_Relative> relatives){
		
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
