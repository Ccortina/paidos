/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.carloscortina.demo.dao;

import com.carloscortina.demo.model.CommercialName;
import java.util.List;

/**
 *
 * @author Ccortina
 */
public interface CommercialNameDao extends GenericDao<CommercialName>{

    public List<CommercialName> getCommercialNameByUser(int id);
    
}
