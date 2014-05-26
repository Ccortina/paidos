/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.carloscortina.demo.model;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 *
 * @author Ccortina_Mac
 */
public class FileValidator implements Validator{

    @Override
    public boolean supports(Class<?> type) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void validate(Object o, Errors errors) {
        FileUpload file = (FileUpload) o;
        
        if(file.getFile().getSize() == 0){
            errors.rejectValue("file","Please select a File");
        }
    }
    
}
