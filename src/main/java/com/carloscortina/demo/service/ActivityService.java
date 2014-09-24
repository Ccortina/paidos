/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.carloscortina.demo.service;

import com.carloscortina.demo.model.Activity;
import java.util.List;

/**
 *
 * @author Ccortina_Mac
 */
public interface ActivityService extends GenericService<Activity> {

    public List<Activity> getActiveActivities();
    public List<Activity> getAllActivities();
}
