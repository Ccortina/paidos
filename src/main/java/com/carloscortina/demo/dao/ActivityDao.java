/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.carloscortina.demo.dao;

import com.carloscortina.demo.model.Activity;
import com.carloscortina.demo.model.Consultationactivity;
import java.util.List;

/**
 *
 * @author Ccortina_Mac
 */
public interface ActivityDao extends GenericDao<Activity>{
   
    public List<Activity> getActivitiesByUser(int idUser);
}
