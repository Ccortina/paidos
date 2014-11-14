/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.carloscortina.demo.dao;

import com.carloscortina.demo.model.PatientRelative;
import com.carloscortina.demo.model.Patientvaccine;
import com.carloscortina.demo.model.PatientvaccinePK;
import com.carloscortina.demo.model.Vaccine;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Ccortina_Mac
 */
@Repository
public class HbnPatientVaccineDao extends GenericHbnDao<Patientvaccine> implements PatientVaccineDao{

    @Override
    public Patientvaccine getById(PatientvaccinePK id) {
        return (Patientvaccine) getSession().get(Patientvaccine.class,id);
    }
    
    @Override
    public List<Patientvaccine> getAllPV(){
        Query query = getSession().createQuery("SELECT new Patientvaccine(p.programedDate,p.vaccine, p.patient) FROM Patientvaccine p");
        
        List<Patientvaccine> pv = query.list();
        for(Patientvaccine p: pv){
            p.getVaccine().setVaccineList(null);
        }
        return pv;
    }
    
    @Override
    public List<Patientvaccine> getPatientVaccineByVaccine(int idVaccine){
        Query query = getSession().createQuery("SELECT new Patientvaccine(p.vaccine, p.patient) FROM Patientvaccine p "
                + "WHERE p.vaccine.idVaccine=:idVaccine");
        query.setParameter("idVaccine", idVaccine);
        
        List<Patientvaccine> pv = query.list();
        for(Patientvaccine p: pv){
            Vaccine v = new Vaccine(p.getVaccine().getIdVaccine(),p.getVaccine().getVaccine());
            p.setVaccine(v);
        }
        return pv;
    }
    
    @Override
    public List<Patientvaccine> getPatientVaccineByPatient(int idPatient){
        Query query = getSession().createQuery("SELECT new Patientvaccine(p.patientvaccinePK,p.programedDate,p.vaccine,"
                + " p.patient,p.suspended,p.suspensionDate,p.notes,p.name,p.batch,p.expirationDate) FROM Patientvaccine p "
                + "WHERE p.patient.idPatient=:idPatient");
        query.setParameter("idPatient", idPatient);
        
        List<Patientvaccine> pv = query.list();
        for(Patientvaccine p: pv){
            Vaccine v = new Vaccine(p.getVaccine().getIdVaccine(),p.getVaccine().getVaccine(),p.getVaccine().getYearApply(),
                    p.getVaccine().getMonthApply(),p.getVaccine().getDayApply(),p.getVaccine().getMultipleShots(),p.getVaccine().getActive(),
                    p.getVaccine().getIdVaccineType());
            p.setVaccine(v);
        }
        return pv;
    }
    
    @Override
    public List<Patientvaccine> getPatientVaccineSystemProgrammedByPatient(int idPatient){
        Query query = getSession().createQuery("SELECT new Patientvaccine(p.patientvaccinePK,p.programedDate,p.vaccine,"
                + " p.patient,p.suspended,p.suspensionDate,p.notes,p.name,p.batch,p.expirationDate) FROM Patientvaccine p "
                + "WHERE p.patient.idPatient=:idPatient AND p.programManual=0");
        query.setParameter("idPatient", idPatient);
        
        List<Patientvaccine> pv = query.list();
        for(Patientvaccine p: pv){
            Vaccine v = new Vaccine(p.getVaccine().getIdVaccine(),p.getVaccine().getVaccine(),p.getVaccine().getYearApply(),
                    p.getVaccine().getMonthApply(),p.getVaccine().getDayApply(),p.getVaccine().getMultipleShots(),p.getVaccine().getActive(),
                    p.getVaccine().getIdVaccineType());
            p.setVaccine(v);
        }
        return pv;
    }

    @Override
    public List<Patientvaccine> getAllPVByFilter(Map<String, String> params) {
        boolean immunization = params.get("immunization").equals("true");
        boolean programmed = params.get("programmed").equals("true");
        boolean birthday = params.get("birthday").equals("true");
        boolean type = params.get("type").equals("true");
        boolean age = params.get("age").equals("true");
        
        Criteria criteria = getSession().createCriteria(Patientvaccine.class);
        criteria.createAlias("vaccine", "v");
        
        if( !(immunization || programmed || birthday || type || age) ){
            return criteria.list();
        }
        
        if( immunization ){
            //Check immunization id
            int id = Integer.parseInt( params.get("IM1") );
            if( id != 0 ){  // specific Immunization
                criteria.add(Restrictions.eq("v.idVaccine", Integer.parseInt(params.get("IM1"))));
            }
            // if batch isnt empty
            if( !params.get("IM2").isEmpty() ){
                criteria.add(Restrictions.eq("batch", params.get("IM2")));
            }
            // if applied
            if( params.get("IM3").equals("1")){
                //Not applied
                criteria.add(Restrictions.isNull("applicationDate"));
            }else{
                //applied
                criteria.add(Restrictions.isNotNull("applicationDate"));
            }
            
        }
        
        if( programmed ){
            
            try{
                SimpleDateFormat sdf = new SimpleDateFormat("d/M/yyyy");
                
                criteria.add(Restrictions.between("programedDate",
                        sdf.parse(params.get("PD1")),
                        sdf.parse(params.get("PD2"))));
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        
        if( birthday ){
            try{
                SimpleDateFormat sdf = new SimpleDateFormat("d/M/yyyy");
                
                criteria.createAlias("patient", "p");
                criteria.add(Restrictions.between("p.birthday",
                        sdf.parse(params.get("BD1")),
                        sdf.parse(params.get("BD2"))));
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        
        if( type && !params.get("IT1").equals("0")){
            criteria.add(Restrictions.eq("v.idVaccineType.idvaccineType", Integer.parseInt(params.get("IT1"))));
        }
        
        //Set projection
        ProjectionList prop = Projections.projectionList();
 
        prop.add(Projections.property("programedDate"),"programedDate");
        prop.add(Projections.property("patient"),"patient");
        prop.add(Projections.property("vaccine"),"vaccine");
        
        criteria.setProjection(prop).setResultTransformer(Transformers.aliasToBean(Patientvaccine.class));
        
        List<Patientvaccine> filter = criteria.list();
        if( age ){
            filter = filterAgeRange(filter,
                    Integer.parseInt(params.get("AR11")),Integer.parseInt(params.get("AR12")),
                    Integer.parseInt(params.get("AR13")),Integer.parseInt(params.get("AR21")),
                    Integer.parseInt(params.get("AR22")),Integer.parseInt(params.get("AR23")));
        }
        
        for(Patientvaccine p: filter){
            p.getVaccine().setVaccineList(null);
        }
        return filter;
    }
    
    private List<Patientvaccine> filterAgeRange(List<Patientvaccine> list,
            int as,int ms,int ds, int ae, int me, int de){
        int []age = new int[3];
        List<Patientvaccine> result = new ArrayList<Patientvaccine>();
        
        for(Patientvaccine pv: list){
            age = calculateAge(pv.getPatient().getBirthday());
            if( (age[0] >= as && age[0] <= ae) && 
                (age[1] >= ms && age[2] <= me) &&
                (age[2] >= ds && age[2] <= de)){
            
                result.add(pv);
            }
        }        
        
        return result;
    }
    
    private int[] calculateAge(Date date){
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
          
        int[] result = {ageYears,ageMonths,ageDays};
        return (result);
    }
}
