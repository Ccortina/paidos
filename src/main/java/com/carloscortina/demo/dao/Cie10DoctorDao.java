/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.carloscortina.demo.dao;

import com.carloscortina.demo.model.CIE10Doctor;
import java.util.List;

/**
 *
 * @author Ccortina_Mac
 */
public interface Cie10DoctorDao extends GenericDao<CIE10Doctor> {

    public List<CIE10Doctor> getCie10ByUser(int id);
}
