/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.carloscortina.demo.dao;

import com.carloscortina.demo.model.LaboratoryTest;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Ccortina_Mac
 */
@Repository
public class HbnLaboratoryTestDao extends GenericHbnDao<LaboratoryTest> implements LaboratoryTestDao{
    
}
