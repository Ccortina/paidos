/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.carloscortina.demo.controller;

import com.carloscortina.demo.model.Perbacknopat;
import com.carloscortina.demo.model.Record;
import com.carloscortina.demo.service.PerBackNoPatService;
import com.carloscortina.demo.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Ccortina_Mac
 */
@Controller
public class PatientConsultationTabs {
    
    @Autowired
    private PerBackNoPatService perBackNoService;
    @Autowired
    private RecordService recordService;
    
    
    //Updates the No Pathological perinatal background table individually 
    @RequestMapping(value="savePerBackNoPat",method=RequestMethod.POST)
    public @ResponseBody String savePerBackNoPat(@ModelAttribute(value="changes") Perbacknopat changes, BindingResult result){
            perBackNoService.updateItem(changes);
            String message = " <div class='alert alert-success alert-dismissable'> " +
                                                            "<button type='button' class='close' data-dismiss='alert' aria-hidden='true'>&times;</button>" +
                                                            "<div>Se han guardado los cambios</div>" +
                                              "</div>";
            return "";
    }

    //Update record alergic background field
    @RequestMapping(value="saveAlergicBackground",method=RequestMethod.POST)
    public @ResponseBody String saveAlergicBackground(@ModelAttribute(value="changes") Record changes, BindingResult result){
            Record currentRecord = recordService.getById(changes.getIdRecord());
            currentRecord.setAlergicBackground(changes.getAlergicBackground());
            recordService.updateItem(currentRecord);
            String message = " <div class='alert alert-success alert-dismissable'> " +
                                                            "<button type='button' class='close' data-dismiss='alert' aria-hidden='true'>&times;</button>" +
                                                            "<div>Se han guardado los cambios</div>" +
                                              "</div>";
            return "";
    }

    @RequestMapping(value="savePerinatalBackground",method=RequestMethod.POST)
    public @ResponseBody String savePerinatalBackground(@ModelAttribute(value="changes") Record changes, BindingResult result){
            Record currentRecord = recordService.getById(changes.getIdRecord());
            currentRecord.setPerinatalBackground(changes.getPerinatalBackground());
            recordService.updateItem(currentRecord);
            String message = " <div class='alert alert-success alert-dismissable'> " +
                                    "<button type='button' class='close' data-dismiss='alert' aria-hidden='true'>&times;</button>" +
                                        "<div>Se han guardado los cambios</div>" +
                                            "</div>";
            return "";
    }

    @RequestMapping(value="saveDevelopmentBackground",method=RequestMethod.POST)
    public @ResponseBody String saveDevelopmentBackground(@ModelAttribute(value="changes") Record changes, BindingResult result){
            Record currentRecord = recordService.getById(changes.getIdRecord());
            currentRecord.setDevelopmentBackground(changes.getDevelopmentBackground());
            recordService.updateItem(currentRecord);
            String message = " <div class='alert alert-success alert-dismissable'> " +
                                                            "<button type='button' class='close' data-dismiss='alert' aria-hidden='true'>&times;</button>" +
                                                            "<div>Se han guardado los cambios</div>" +
                                              "</div>";
            return "";
    }

    @RequestMapping(value="saveSurgicalHistory",method=RequestMethod.POST)
    public @ResponseBody String saveSurgicalHistory(@ModelAttribute(value="changes") Record changes, BindingResult result){
            Record currentRecord = recordService.getById(changes.getIdRecord());
            currentRecord.setSurgicalHistory(changes.getSurgicalHistory());
            recordService.updateItem(currentRecord);
            String message = " <div class='alert alert-success alert-dismissable'> " +
                                                            "<button type='button' class='close' data-dismiss='alert' aria-hidden='true'>&times;</button>" +
                                                            "<div>Se han guardado los cambios</div>" +
                                              "</div>";
            return "";
    }

    @RequestMapping(value="saveHereditaryAndFamilyBackground",method=RequestMethod.POST)
    public @ResponseBody String saveHereditaryAndFamilyBackground(@ModelAttribute(value="changes") Record changes, BindingResult result){
            Record currentRecord = recordService.getById(changes.getIdRecord());
            currentRecord.setHereditaryAndFamilyBackground(changes.getHereditaryAndFamilyBackground());
            recordService.updateItem(currentRecord);
            String message = " <div class='alert alert-success alert-dismissable'> " +
                                                            "<button type='button' class='close' data-dismiss='alert' aria-hidden='true'>&times;</button>" +
                                                            "<div>Se han guardado los cambios</div>" +
                                              "</div>";
            return "";
    }

    @RequestMapping(value="savePathologicalBackground",method=RequestMethod.POST)
    public @ResponseBody String savePathologicalBackground(@ModelAttribute(value="changes") Record changes, BindingResult result){
            Record currentRecord = recordService.getById(changes.getIdRecord());
            currentRecord.setPathologicalBackgorund(changes.getPathologicalBackgorund());
            recordService.updateItem(currentRecord);
            String message = " <div class='alert alert-success alert-dismissable'> " +
                                                            "<button type='button' class='close' data-dismiss='alert' aria-hidden='true'>&times;</button>" +
                                                            "<div>Se han guardado los cambios</div>" +
                                              "</div>";
            return "";
    }

    @RequestMapping(value="saveOthers",method=RequestMethod.POST)
    public @ResponseBody String saveOthers(@ModelAttribute(value="changes") Record changes, BindingResult result){
            Record currentRecord = recordService.getById(changes.getIdRecord());
            currentRecord.setOthers(changes.getOthers());
            recordService.updateItem(currentRecord);
            String message = " <div class='alert alert-success alert-dismissable'> " +
                                                            "<button type='button' class='close' data-dismiss='alert' aria-hidden='true'>&times;</button>" +
                                                            "<div>Se han guardado los cambios</div>" +
                                              "</div>";
            return "";
    }
}