/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.carloscortina.demo.dao;

import com.carloscortina.demo.model.Vaccine;
import java.util.List;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Ccortina_Mac
 */
@Repository
public class HbnVaccineDao extends GenericHbnDao<Vaccine> implements VaccineDao {
    
    @Override
    public List<Vaccine> getActiveVaccines(){
        String hql = "FROM Vaccine v WHERE v.active=1";
        Query query = getSession().createQuery(hql);
        return query.list();
    }
    
    @Override
    public List<Vaccine> getAllVaccines(){
        Query query = getSession().createQuery( "Select new Vaccine(v.idVaccine,v.vaccine,v.yearApply,"
                + "v.monthApply,v.dayApply,v.multipleShots,v.active,"
                + "v.idVaccineType) FROM Vaccine v");
        List<Vaccine> vaccines = query.list();
        
        for(Vaccine v: vaccines){
            Query subquery = getSession().createQuery( "Select new Vaccine(v.idVaccine,v.vaccine) "
                    + "FROM Vaccine v JOIN v.vaccineList vt WHERE vt.idVaccine=:idVaccine");
            subquery.setParameter("idVaccine", v.getIdVaccine());
            v.setVaccineList(subquery.list());
        }
        //Integer idVaccine, String vaccine, int yearApply, int monthApply, int dayApply, int multipleShots, int active, List<Vaccine> vaccineList, Vaccinetype idVaccineType
        return vaccines;
    }
    
    @Override
    public List<Vaccine> getAllActiveVaccines(){
        Query query = getSession().createQuery( "Select new Vaccine(v.idVaccine,v.vaccine,v.yearApply,"
                + "v.monthApply,v.dayApply,v.multipleShots,v.active,"
                + "v.idVaccineType) FROM Vaccine v WHERE v.active=1");
        List<Vaccine> vaccines = query.list();
        
        for(Vaccine v: vaccines){
            Query subquery = getSession().createQuery( "Select new Vaccine(v.idVaccine,v.vaccine) "
                    + "FROM Vaccine v JOIN v.vaccineList vt WHERE vt.idVaccine=:idVaccine");
            subquery.setParameter("idVaccine", v.getIdVaccine());
            v.setVaccineList(subquery.list());
        }
        
        return vaccines;
    }
}
