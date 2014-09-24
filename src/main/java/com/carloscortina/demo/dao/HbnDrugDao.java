/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.carloscortina.demo.dao;

import com.carloscortina.demo.model.Drug;
import com.carloscortina.demo.model.Drugdose;
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
                + "drug.active,drug.drugPresentationId,drug.doseCalculationCriteriaId,drug.applicationMethodId,drug.administrationUnitId) FROM Drug as drug";
        Query query = getSession().createQuery(hql);
        
        List<Drug> result = query.list();
        
        for(Drug temp: result){
            List<Drugdose> drugDoseList = getSession().createQuery("SELECT new Drugdose(dose.idDrugDose,dose.age,dose.dose) "
                    + "FROM Drugdose as dose WHERE idDrug.idDrug = :idDrug ").setParameter("idDrug", temp.getIdDrug()).list();
            List<Treatment> treatmentList = getSession().createQuery("SELECT new Treatment(treatment.idTreatment) "
                    + "FROM Treatment as treatment JOIN treatment.drugList d WHERE d.idDrug = :idDrug ").setParameter("idDrug", temp.getIdDrug()).list();
            temp.setDrugdoseList(drugDoseList);
            temp.setTreatmentList(treatmentList);
        }
        
        return result;
        
    }
    
    @Override
    public List<Drug> getDrugByTreatment(int treatmentId){
        String hql = "SELECT new Drug(drug.idDrug,drug.drug,drug.drugPresentationId) FROM Drug as drug"
                + " JOIN drug.treatmentList t WHERE t.idTreatment=:idTreatment";
        Query query = getSession().createQuery(hql);
        query.setParameter("idTreatment",treatmentId);
        
        List<Drug> result = query.list();
        
        return result;
    }

    @Override
    public List<Drug> getDrugByPresentationAndUser(int presentationId, int userId) {
        String hql = "SELECT new Drug(drug.idDrug,drug.drug,drug.concentration,"
                + "drug.drugPresentationId,drug.doseCalculationCriteriaId,drug.administrationUnitId) FROM Drug as drug"
                + " WHERE drug.drugPresentationId.drugPresentationId=:presentationId";
        Query query = getSession().createQuery(hql);
        query.setParameter("presentationId",presentationId);
        
        return query.list();
    }
    
    @Override
    public List<Drug> getDrugByApplicationMethodAndUser(int applicationId, int userId) {
        String hql = "SELECT new Drug(drug.idDrug,drug.drug,drug.concentration,"
                + "drug.drugPresentationId,drug.doseCalculationCriteriaId,drug.administrationUnitId) FROM Drug as drug"
                + " WHERE drug.applicationMethodId.idApplicationMethod=:applicationId";
        Query query = getSession().createQuery(hql);
        query.setParameter("applicationId",applicationId);
        
        return query.list();
    }

    @Override
    public List<Drug> getDrugByAdministrationUnitAndUser(int unitId, int userId) {
        String hql = "SELECT new Drug(drug.idDrug,drug.drug,drug.concentration,"
                + "drug.drugPresentationId,drug.doseCalculationCriteriaId,drug.administrationUnitId) FROM Drug as drug"
                + " WHERE drug.administrationUnitId.idAdministrationUnit=:unitId";
        Query query = getSession().createQuery(hql);
        query.setParameter("unitId",unitId);
        
        return query.list();
    }

    @Override
    public List<Drug> getAllActiveDrugBasicInfo() {
        return getSession().createQuery("SELECT new Drug(drug.idDrug,drug.drug,drug.drugPresentationId) FROM Drug drug "
                + "WHERE drug.active=1").list();
    }
 }
