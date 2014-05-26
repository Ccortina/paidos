/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.carloscortina.demo.model;

import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Ccortina_Mac
 */
public class FileUpload {
    
    private MultipartFile file;
    
    public FileUpload(){
        this.file =null;
    }

    public FileUpload(MultipartFile file) {
        this.file = file;
    }
    
    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }
    
}
