/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.carloscortina.demo.dao;

import com.carloscortina.demo.model.Activity;
import com.carloscortina.demo.model.Vaccine;
import java.util.List;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Ccortina_Mac
 */
@Repository
public class HbnActivityDao extends GenericHbnDao<Activity> implements ActivityDao{
    
    @Override
    public List<Activity> getActivitiesByUser(int idUser){
        
        String hql = "SELECT new Activity(activity.idActivity,activity.activity,activity.activityCost,"
                + "activity.consultationDefault,activity.active,"
                + "activity.idActivityType) FROM Activity as activity JOIN activity.userList user WHERE user.idUser=:idUser "
                + "AND activity.active = 1";
        Query query = getSession().createQuery(hql);
        
        query.setParameter("idUser", idUser);
        List<Activity> result = query.list();
        
        for(Activity temp: result){
            String hql1 = "SELECT new Vaccine(vaccine.idVaccine) FROM Vaccine as vaccine JOIN vaccine.activityList activity WHERE activity.idActivity = :idActivity";
            List<Vaccine> vaccineList = getSession().createQuery(hql1).setParameter("idActivity", temp.getIdActivity()).list();
            if(vaccineList != null){
                if (!vaccineList.isEmpty()) {
                    temp.setIdVaccine(vaccineList.get(0));
                } 
            } 
        }
        return result;
    }
    
    @Override
    public List<Activity> getAllActivities(){
        List<Activity> activityList = getSession().createQuery("FROM Activity a").list();
        
        for(Activity a: activityList){
            if(a.getIdVaccine() != null){
                a.setIdVaccine(new Vaccine(a.getIdVaccine().getIdVaccine(), a.getIdVaccine().getVaccine()));
            }
        }
        
        return activityList;
    }
    
}
