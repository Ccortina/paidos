/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.carloscortina.demo.model;

/**
 *
 * @author Ccortina_Mac
 */
public class Document {
    String name;
    String deleteBtn;

    public Document() {
    }
    
    public Document(String name, String deleteBtn) {
        this.name = name;
        this.deleteBtn = deleteBtn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDeleteBtn() {
        return deleteBtn;
    }

    public void setDeleteBtn(String deleteBtn) {
        this.deleteBtn = deleteBtn;
    }
    
    
    
    
    
}
