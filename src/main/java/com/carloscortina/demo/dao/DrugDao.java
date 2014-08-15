/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.carloscortina.demo.dao;

import com.carloscortina.demo.model.Drug;
import java.util.List;

/**
 *
 * @author Ccortina
 */
public interface DrugDao extends GenericDao<Drug>{

    public List<Drug> getDrugByUser(int id);
    public List<Drug> getDrugByTreatmentAndUser(int treatmentId,int userId);
    public List<Drug> getDrugByPresentationAndUser(int presentationId,int userId);
    public List<Drug> getDrugByApplicationMethodAndUser(int applicationId,int userId);
}
