/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.carloscortina.demo.dao;

import com.carloscortina.demo.model.AppointmentStatus;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Ccortina_Mac
 */
@Repository
public class HbnAppointmentStatusDao extends GenericHbnDao<AppointmentStatus> implements appointmentStatusDao{
    
}
