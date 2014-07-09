package com.carloscortina.demo.model.forms;

public class OneFieldForm {

	String field;
	
	OneFieldForm(){
		this.field="";
	}
	
	OneFieldForm(String field){
		this.field = field;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}
	
	
	
}
