/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.carloscortina.demo.service;

import com.carloscortina.demo.model.Drug;
import java.util.List;

/**
 *
 * @author Ccortina
 */
public interface DrugService extends GenericService<Drug>{

    public List<Drug> getDrugByUser(int id);
    public List<Drug> getDrugByTreatmentAndUser(int treatmentId,int userId);
    public List<Drug> getDrugIncompatibilities(int idDrug);
}
