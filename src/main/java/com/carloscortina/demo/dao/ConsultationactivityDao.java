/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.carloscortina.demo.dao;

import com.carloscortina.demo.model.Consultationactivity;
import com.carloscortina.demo.model.User;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Carlos Cortina
 */
public interface ConsultationactivityDao extends GenericDao<Consultationactivity>{

    public List<Consultationactivity> getConsultationsByActivity(int idActivity);
    public List<Consultationactivity> getGlobalReport(Date start, Date end,User user);
    public List<Consultationactivity> getIncomeByActivityTotals(Date start, Date end,User user,int type);
    public List<Consultationactivity> getIncomeByActivityDetails(Date start, Date end, User user,int type);
}
