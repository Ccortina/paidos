package com.carloscortina.demo.json;

import java.util.List;

public class JsonPack<T> {

	List<T> aaData;
	
	
	public JsonPack(List<T> aaData) {
		super();
		this.aaData = aaData;
	}
        
	public List<T> getAaData() {
		return aaData;
	}
	public void setAaData(List<T> aaData) {
		this.aaData = aaData;
	}

	
	
}
