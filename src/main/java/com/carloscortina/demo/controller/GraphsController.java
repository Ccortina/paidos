/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.carloscortina.demo.controller;

import com.carloscortina.demo.model.Agebmi;
import com.carloscortina.demo.model.Agepc;
import com.carloscortina.demo.model.Agesize0to36;
import com.carloscortina.demo.model.Agesize24to240;
import com.carloscortina.demo.model.Ageweight0to240;
import com.carloscortina.demo.model.Consultation;
import com.carloscortina.demo.model.GraphDataHolder;
import com.carloscortina.demo.model.Heightweight;
import com.carloscortina.demo.model.Patient;
import com.carloscortina.demo.model.Sizeweight;
import com.carloscortina.demo.service.AgeHeightService;
import com.carloscortina.demo.service.AgePcService;
import com.carloscortina.demo.service.AgeWeightService;
import com.carloscortina.demo.service.AgebmiService;
import com.carloscortina.demo.service.ConsultationService;
import com.carloscortina.demo.service.HeightWeightService;
import com.carloscortina.demo.service.SizeWeightService;
import com.carloscortina.demo.service.age4sizeService;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Carlos Cortina
 */

@Controller
@RequestMapping(value="/graphs")
public class GraphsController {
   
    @Autowired
    private age4sizeService a4sService;
    @Autowired
    private AgeHeightService a4hService;
    @Autowired
    private AgeWeightService a4wService;
    @Autowired
    private SizeWeightService swService;
    @Autowired
    private HeightWeightService hwService;
    @Autowired
    private AgePcService apcService;
    @Autowired
    private AgebmiService abService;
    @Autowired
    private ConsultationService consultationService;
    
    private Patient patient;
    
    @RequestMapping(value="{type}")
    public String graph(Model model,HttpSession session,@PathVariable int type){
        
        patient = (Patient) session.getAttribute("patientForGraph");
        model.addAttribute("graphType",type);
        model.addAttribute("patient",patient.getFirstName()+" "+patient.getFatherLastName()+" "+patient.getMotherLastName() );
        model.addAttribute("gender",patient.getSex().getGender());
        
        return ( "Graphs/GraphsCanvas" );
    }
    
    //This method returns the data for the chart of size vs age (0 to 36 months)
    @RequestMapping(value="getPatientDataSize4Age",produces = "application/json")
    public @ResponseBody GraphDataHolder getPatientDataSize4Age(){
        List<Agesize0to36> percentiles =  a4sService.getAllDataFor0to36Months(patient);
        List<Consultation> measures = consultationService.getConsultationMeasureHistory(patient.getIdPatient());
        GraphDataHolder data = new GraphDataHolder();
        
        //Prepare data from percentiles
        for(Agesize0to36 atos: percentiles){
            data.addData(atos.getMonths(), atos.getP3(), atos.getP5(), atos.getP10(),
                    atos.getP25(), atos.getP50(), atos.getP75(), atos.getP90(), atos.getP95(), atos.getP97());
        }
        
        //Add patient data
        for(Consultation c: measures){
            double ageMonths = calculateAge(patient.getBirthday(),c.getIdAppointment().getDate());
            if(ageMonths <= 36){
                data.addPatientData(ageMonths, c.getSize());
            }
        }
        
        return data;
    }
    
    //This method returns the data for the chart of size vs age (24 to 240 months)
    @RequestMapping(value="getPatientDataHeight4Age",produces = "application/json")
    public @ResponseBody GraphDataHolder getPatientDataHeight4Age(){
        List<Agesize24to240> percentiles =  a4hService.getAllDataFor24to240Months(patient);
        List<Consultation> measures = consultationService.getConsultationMeasureHistory(patient.getIdPatient());
        GraphDataHolder data = new GraphDataHolder();
        
        //Prepare data from percentiles
        for(Agesize24to240 atos: percentiles){
            data.addData(atos.getMonths(), atos.getP3(), atos.getP5(), atos.getP10(),
                    atos.getP25(), atos.getP50(), atos.getP75(), atos.getP90(), atos.getP95(), atos.getP97());
        }
        
        //Add patient data
        for(Consultation c: measures){
            double ageMonths = calculateAge(patient.getBirthday(),c.getIdAppointment().getDate());
            if(ageMonths >= 24){
                data.addPatientData(ageMonths, c.getSize());
            }
        }
        
        return data;
    }
    
    @RequestMapping(value="getPatientDataWeight4Age",produces = "application/json")
    public @ResponseBody GraphDataHolder getPatientDataWeight4Age(){
        List<Ageweight0to240> percentiles =  a4wService.getAllDataFor0to36Months(patient);
        List<Consultation> measures = consultationService.getConsultationMeasureHistory(patient.getIdPatient());
        GraphDataHolder data = new GraphDataHolder();
        
        //Prepare data from percentiles
        for(Ageweight0to240 atos: percentiles){
            data.addData(atos.getMonths(), atos.getP3(), atos.getP5(), atos.getP10(),
                    atos.getP25(), atos.getP50(), atos.getP75(), atos.getP90(), atos.getP95(), atos.getP97());
        }
        
        //Add patient data
        for(Consultation c: measures){
            double ageMonths = calculateAge(patient.getBirthday(),c.getIdAppointment().getDate());
            if(ageMonths <= 36){
                data.addPatientData(ageMonths, c.getWeigth());
            }
        }
        
        return data;
    }
    
    @RequestMapping(value="getPatientDataWeight4Age24to240",produces = "application/json")
    public @ResponseBody GraphDataHolder getPatientDataWeight4Age24to240(){
        List<Ageweight0to240> percentiles =  a4wService.getAllDataFor24to240Months(patient);
        List<Consultation> measures = consultationService.getConsultationMeasureHistory(patient.getIdPatient());
        GraphDataHolder data = new GraphDataHolder();
        
        //Prepare data from percentiles
        for(Ageweight0to240 atos: percentiles){
            data.addData(atos.getMonths(), atos.getP3(), atos.getP5(), atos.getP10(),
                    atos.getP25(), atos.getP50(), atos.getP75(), atos.getP90(), atos.getP95(), atos.getP97());
        }
        
        //Add patient data
        for(Consultation c: measures){
            double ageMonths = calculateAge(patient.getBirthday(),c.getIdAppointment().getDate());
            if(ageMonths >= 24){
                data.addPatientData(ageMonths, c.getWeigth());
            }
        }
        
        return data;
    }
    
    @RequestMapping(value="getPatientDataSizeWeight",produces = "application/json")
    public @ResponseBody GraphDataHolder getPatientDataSizeWeight(){
        List<Sizeweight> percentiles =  swService.getAllDataForPatient(patient);
        List<Consultation> measures = consultationService.getConsultationMeasureHistory(patient.getIdPatient());
        GraphDataHolder data = new GraphDataHolder();
        
        //Prepare data from percentiles
        for(Sizeweight atos: percentiles){
            data.addData(atos.getSize(), atos.getP3(), atos.getP5(), atos.getP10(),
                    atos.getP25(), atos.getP50(), atos.getP75(), atos.getP90(), atos.getP95(), atos.getP97());
        }
        
        //Add patient data
        for(Consultation c: measures){
            double ageMonths = calculateAge(patient.getBirthday(),c.getIdAppointment().getDate());
            if(ageMonths <= 36){
                data.addPatientData(c.getSize(), c.getWeigth());
            }
        }
        
        return data;
    }
    
    @RequestMapping(value="getPatientDataHeightWeight",produces = "application/json")
    public @ResponseBody GraphDataHolder getPatientDataHeightWeight(){
        List<Heightweight> percentiles =  hwService.getAllDataForPAtient(patient);
        List<Consultation> measures = consultationService.getConsultationMeasureHistory(patient.getIdPatient());
        GraphDataHolder data = new GraphDataHolder();
        
        //Prepare data from percentiles
        for(Heightweight atos: percentiles){
            data.addData(atos.getSize(), atos.getP3(), atos.getP5(), atos.getP10(),
                    atos.getP25(), atos.getP50(), atos.getP75(), atos.getP90(), atos.getP95(), atos.getP97());
        }
        
        //Add patient data
        for(Consultation c: measures){
            
            if(c.getSize() >= 75){
                data.addPatientData(c.getSize(), c.getWeigth());
            }
        }
        
        return data;
    }
    
    @RequestMapping(value="getPatientDataAgePc",produces = "application/json")
    public @ResponseBody GraphDataHolder getPatientDataAgePc(){
        List<Agepc> percentiles =  apcService.getAllDataFor0to36Months(patient);
        List<Consultation> measures = consultationService.getConsultationMeasureHistory(patient.getIdPatient());
        GraphDataHolder data = new GraphDataHolder();
        
        //Prepare data from percentiles
        for(Agepc atos: percentiles){
            data.addData(atos.getMonths(), atos.getP3(), atos.getP5(), atos.getP10(),
                    atos.getP25(), atos.getP50(), atos.getP75(), atos.getP90(), atos.getP95(), atos.getP97());
        }
        
        //Add patient data
        for(Consultation c: measures){
            double ageMonths = calculateAge(patient.getBirthday(),c.getIdAppointment().getDate());
            if(ageMonths <= 36){
                data.addPatientData(ageMonths, c.getPc());
            }
        }
        
        return data;
    }
    
    @RequestMapping(value="getPatientDataAgeBmi",produces = "application/json")
    public @ResponseBody GraphDataHolder getPatientDataAgeBmi(){
        List<Agebmi> percentiles =  abService.getAllDataForPatient(patient);
        List<Consultation> measures = consultationService.getConsultationMeasureHistory(patient.getIdPatient());
        GraphDataHolder data = new GraphDataHolder();
        
        //Prepare data from percentiles
        for(Agebmi atos: percentiles){
            data.addData(atos.getMonths(), atos.getP3(), atos.getP5(), atos.getP10(),
                    atos.getP25(), atos.getP50(), atos.getP75(), atos.getP90(), atos.getP95(), atos.getP97());
        }
        
        //Add patient data
        for(Consultation c: measures){
            double ageMonths = calculateAge(patient.getBirthday(),c.getIdAppointment().getDate());
            if(ageMonths >= 36){
                data.addPatientData(ageMonths, c.getPc());
            }
        }
        
        return data;
    }
    
    @RequestMapping(value="getPatientsConsultationNumber")
    public @ResponseBody Map<Patient,Long> getPatientConsultationNumber(){
        return (consultationService.getPatientsConsultationNumber());
    }
    
    @RequestMapping(value="getConsultsOfMonthPerDay")
    public @ResponseBody Map<Integer,Long> getConsultsOfMonthPerDay(int month,int year){
        Map<Date,Long> mc =consultationService.getConsultsOfMonthPerDay(month,year);
        Map<Integer,Long> result = new HashMap<Integer, Long>();
        Calendar cal = Calendar.getInstance();
        
        for (Map.Entry<Date, Long> entry : mc.entrySet()) {
            cal.setTime(entry.getKey());
            result.put(cal.get(Calendar.DAY_OF_MONTH), entry.getValue());
        }
     
        return ( result );
    }
    
    private Double calculateAge(Date birthDate,Date consultationDate){
        int years = 0;
        int months = 0;
        int days = 0;
        
        //create calendar object for birth day
        Calendar birthDay = Calendar.getInstance();
        birthDay.setTimeInMillis(birthDate.getTime());
        
        //create calendar object for current day
        Calendar now = Calendar.getInstance();
        now.setTimeInMillis(consultationDate.getTime());
        
        //Get difference between years
        years = now.get(Calendar.YEAR) - birthDay.get(Calendar.YEAR);
        int currMonth = now.get(Calendar.MONTH) + 1;
        int birthMonth = birthDay.get(Calendar.MONTH) + 1;
        
        //Get difference between months
        months = currMonth - birthMonth;
        
        //if month difference is in negative then reduce years by one and calculate the number of months.
        if (months < 0)
        {
        years--;
        months = 12 - birthMonth + currMonth;
        if (now.get(Calendar.DATE) < birthDay.get(Calendar.DATE))
        months--;
        } else if (months == 0 && now.get(Calendar.DATE) < birthDay.get(Calendar.DATE))
        {
        years--;
        months = 11;
        }
        //Calculate the days
        if (now.get(Calendar.DATE) > birthDay.get(Calendar.DATE))
        days = now.get(Calendar.DATE) - birthDay.get(Calendar.DATE);
        else if (now.get(Calendar.DATE) < birthDay.get(Calendar.DATE))
        {
        int today = now.get(Calendar.DAY_OF_MONTH);
        now.add(Calendar.MONTH, -1);
        days = now.getActualMaximum(Calendar.DAY_OF_MONTH) - birthDay.get(Calendar.DAY_OF_MONTH) + today;
        } else
        {
        days = 0;
        if (months == 12)
        {
        years++;
        months = 0;
        }
        }
        
        //Create new Age object 
        return ((days/30.0)+ months + (years *12.0));
    }
}
