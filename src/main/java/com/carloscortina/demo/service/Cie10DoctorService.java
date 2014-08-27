/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.carloscortina.demo.service;

import com.carloscortina.demo.model.Cie10doctor;
import java.util.List;

/**
 *
 * @author Ccortina_Mac
 */
public interface Cie10DoctorService extends GenericService<Cie10doctor>{

    public List<Cie10doctor> getCie10ByUser(int id);
}
