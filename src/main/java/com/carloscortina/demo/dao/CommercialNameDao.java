/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.carloscortina.demo.dao;

import com.carloscortina.demo.model.Commercialname;
import java.util.List;

/**
 *
 * @author Ccortina
 */
public interface CommercialNameDao extends GenericDao<Commercialname>{

    public List<Commercialname> getCommercialNameByUser(int id);
    
}
