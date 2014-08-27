/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.carloscortina.demo.service;

import com.carloscortina.demo.model.Commercialname;
import java.util.List;

/**
 *
 * @author Ccortina
 */
public interface CommercialNameService extends GenericService<Commercialname>{

    public List<Commercialname> getCommercialNameByUser(int id);
}
