/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.carloscortina.demo.dao;

import com.carloscortina.demo.model.Administrationunit;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Carlos Cortina
 */
@Repository
public class HbnAdministrationUnitDao extends GenericHbnDao<Administrationunit> implements AdministrationUnitDao{
    
}
