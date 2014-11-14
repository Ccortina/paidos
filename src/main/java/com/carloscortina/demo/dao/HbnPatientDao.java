package com.carloscortina.demo.dao;

import com.carloscortina.demo.model.Patient;
import com.carloscortina.demo.model.PatientRelative;
import java.util.Date;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

@Repository
public class HbnPatientDao extends GenericHbnDao<Patient> implements PatientDao {

    @Override
    public Patient mergePatient(Patient patient) {
        return (Patient)getSession().merge(patient);
    }
    
    @Override
    public List<Patient> getAllPatientsByDoctor(int idStaffMember){
        String hql = "SELECT new Patient(p.idPatient,p.firstName,p.fatherLastName,"
                + "p.motherLastName,p.sex,p.birthday,p.active,p.idDoctor) FROM Patient as p"
                + " WHERE p.idDoctor.idStaffMember=:idStaffMember";
        Query query = getSession().createQuery(hql);
        query.setParameter("idStaffMember",idStaffMember);
        
        List<Patient> result = query.list();
        
        return result;
    }
    
    @Override
    public List<Patient> getAllActivePatientsByDoctor(int idStaffMember){
        String hql = "SELECT new Patient(p.idPatient,p.firstName,p.fatherLastName,"
                + "p.motherLastName,p.sex,p.birthday,p.active,p.idDoctor) FROM Patient as p"
                + " WHERE p.idDoctor.idStaffMember=:idStaffMember AND p.active=1";
        Query query = getSession().createQuery(hql);
        query.setParameter("idStaffMember",idStaffMember);
        
        List<Patient> result = query.list();
        
        return result;
    }

    @Override
    public List<Patient> getAllPatients() {
        String hql = "SELECT new Patient(p.idPatient,p.firstName,p.fatherLastName,"
                + "p.motherLastName,p.sex,p.birthday,p.active,p.idDoctor) FROM Patient as p";
        Query query = getSession().createQuery(hql);
        
        List<Patient> result = query.list();
        
        return result;
    }
    
    @Override
    public Patient getPatientBasicData(int idPatient){
        String hql = "SELECT new Patient(p.idPatient,p.firstName,p.fatherLastName,"
                + "p.motherLastName,p.curp,p.sex,p.birthday,p.notes,p.active,p.idDoctor) FROM Patient as p"
                +" WHERE p.idPatient=:idPatient";
        Query query = getSession().createQuery(hql);
        query.setParameter("idPatient",idPatient);
        
        Criteria criteria = getSession().createCriteria(PatientRelative.class);
        criteria.add(Restrictions.eq("patient.idPatient", idPatient));
        List<PatientRelative> prList = criteria.list();

        
        Patient result = (Patient) query.uniqueResult();
        result.setPatientRelativeList(prList);
        return (Patient)result;
    }

    @Override
    public List<Patient> getPatientByLaboratoryTest(int idLaboratory) {
        String hql = "SELECT new Patient(p.firstName,p.fatherLastName,"
                + "p.motherLastName) FROM Patient as p JOIN p.laboratorytestresultList l"
                +" WHERE l.idLaboratoryTest.idLaboratoryTest=:idLaboratory";
        Query query = getSession().createQuery(hql);
        query.setParameter("idLaboratory", idLaboratory);
        return query.list();
    }

    @Override
    public List<Patient> getPatientByBirthmethod(int idBirthmethod) {
        String hql = "SELECT new Patient(p.firstName,p.fatherLastName,"
                + "p.motherLastName) FROM Patient as p JOIN p.recordList r"
                +" WHERE r.idPerBackNoPat.birthMethod.idBirthMethod=:idBirthmethod";
        Query query = getSession().createQuery(hql);
        query.setParameter("idBirthmethod", idBirthmethod);
        return query.list();
    }
    
    @Override
    public List<Patient> getPatientWithoutVaccine(int idVaccine){
        Query query = getSession().createQuery("SELECT new Patient(p.idPatient,p.firstName,p.fatherLastName,p.motherLastName) FROM Patient p "
                + "JOIN p.patientvaccineList pv "
                + "WHERE pv.vaccine.idVaccine=:idVaccine AND pv.applicationDate is not null");
        Query secondquery = getSession().createQuery("SELECT new Patient(p.idPatient,p.firstName,p.fatherLastName,p.motherLastName) FROM Patient p");
        query.setParameter("idVaccine", idVaccine);
        List<Patient> result = secondquery.list();
        result.removeAll(query.list());
        
        return result;
    }

    @Override
    public List<Patient> getPatientsByBirthdayRange(int month) {
        Query query = getSession().createQuery("SELECT new Patient(p.firstName,p.fatherLastName,p.motherLastName,p.birthday) FROM Patient p "
                + "WHERE MONTH(p.birthday)=:month AND p.active = 1");
        query.setParameter("month", month);
        
        return query.list();
    }
    
}
