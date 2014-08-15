/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.carloscortina.demo.dao;

import com.carloscortina.demo.model.Drug;
import com.carloscortina.demo.model.DrugDose;
import com.carloscortina.demo.model.Treatment;
import java.util.List;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Ccortina
 */
@Repository
public class HbnDrugDao extends GenericHbnDao<Drug> implements DrugDao{

    @Override
    public List<Drug> getDrugByUser(int id){
        String hql = "SELECT new Drug(drug.idDrug,drug.drug,drug.concentration,drug.treatmentDays,drug.applicationSchedule,drug.dailyFrequency,drug.notes,"
                + "drug.active,drug.drugPresentationId,drug.doseCalculationCriteriaId,drug.applicationMethodId,drug.administrationUnitId) FROM Drug as drug"
                + " JOIN drug.userList u WHERE u.idUser=:idUser";
        Query query = getSession().createQuery(hql);
        query.setParameter("idUser",id);
        
        List<Drug> result = query.list();
        
        for(Drug temp: result){
            String hql1 = "SELECT new DrugDose(dose.idDrugDose,dose.age,dose.dose) FROM DrugDose as dose WHERE idDrug.idDrug = :idDrug ";
            String hql2 = "SELECT new Treatment(treatment.idTreatment) FROM Treatment as treatment JOIN treatment.drugList d WHERE d.idDrug = :idDrug ";
            List<DrugDose> drugDoseList = getSession().createQuery(hql1).setParameter("idDrug", temp.getIdDrug()).list();
            List<Treatment> treatmentList = getSession().createQuery(hql2).setParameter("idDrug", temp.getIdDrug()).list();
            temp.setDrugDoseList(drugDoseList);
            temp.setTreatmentList(treatmentList);
        }
        
        return result;
        
    }
    
    @Override
    public List<Drug> getDrugByTreatmentAndUser(int treatmentId,int userId){
        String hql = "SELECT new Drug(drug.idDrug,drug.drug,drug.concentration,drug.treatmentDays,drug.applicationSchedule,drug.dailyFrequency,drug.notes,"
                + "drug.active,drug.drugPresentationId,drug.doseCalculationCriteriaId,drug.applicationMethodId,drug.administrationUnitId) FROM Drug as drug"
                + " JOIN drug.userList u  JOIN drug.treatmentList t WHERE u.idUser=:idUser AND t.idTreatment=:idTreatment";
        Query query = getSession().createQuery(hql);
        query.setParameter("idUser",userId);
        query.setParameter("idTreatment",treatmentId);
        
        List<Drug> result = query.list();
        
        for(Drug temp: result){
            String hql1 = "SELECT new DrugDose(dose.idDrugDose,dose.age,dose.dose) FROM DrugDose as dose WHERE idDrug.idDrug = :idDrug ";
            List<DrugDose> drugDoseList = getSession().createQuery(hql1).setParameter("idDrug", temp.getIdDrug()).list();
            temp.setDrugDoseList(drugDoseList);
        }
        
        return result;
    }

    @Override
    public List<Drug> getDrugByPresentationAndUser(int presentationId, int userId) {
        String hql = "SELECT new Drug(drug.idDrug,drug.drug,drug.concentration,"
                + "drug.drugPresentationId,drug.doseCalculationCriteriaId,drug.administrationUnitId) FROM Drug as drug"
                + " JOIN drug.userList u WHERE u.idUser=:idUser AND drug.drugPresentationId.drugPresentationId=:presentationId";
        Query query = getSession().createQuery(hql);
        query.setParameter("idUser",userId);
        query.setParameter("presentationId",presentationId);
        
        return query.list();
    }
    
    @Override
    public List<Drug> getDrugByApplicationMethodAndUser(int applicationId, int userId) {
        String hql = "SELECT new Drug(drug.idDrug,drug.drug,drug.concentration,"
                + "drug.drugPresentationId,drug.doseCalculationCriteriaId,drug.administrationUnitId) FROM Drug as drug"
                + " JOIN drug.userList u WHERE u.idUser=:idUser AND drug.applicationMethodId.idApplicationMethod=:applicationId";
        Query query = getSession().createQuery(hql);
        query.setParameter("idUser",userId);
        query.setParameter("applicationId",applicationId);
        
        return query.list();
    }

    @Override
    public List<Drug> getDrugByAdministrationUnitAndUser(int unitId, int userId) {
        String hql = "SELECT new Drug(drug.idDrug,drug.drug,drug.concentration,"
                + "drug.drugPresentationId,drug.doseCalculationCriteriaId,drug.administrationUnitId) FROM Drug as drug"
                + " JOIN drug.userList u WHERE u.idUser=:idUser AND drug.administrationUnitId.idAdministrationUnit=:unitId";
        Query query = getSession().createQuery(hql);
        query.setParameter("idUser",userId);
        query.setParameter("unitId",unitId);
        
        return query.list();
    }
 }
