/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.carloscortina.demo.dao;

import com.carloscortina.demo.model.Drug;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Ccortina
 */
@Repository
public class HbnDrugDao extends GenericHbnDao<Drug> implements DrugDao{
    
}
