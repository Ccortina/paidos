package com.carloscortina.demo.json;

import java.util.List;

public class JsonPack<T> {
	
	//int sEcho;
	//int iTotalRecords;
	//int iTotalDisplayRecords;
	List<T> aaData;
	
	
	public JsonPack(List<T> aaData) {
		super();
		//this.sEcho = 1;
		this.aaData = aaData;
		//iTotalRecords = aaData.size();
		//iTotalDisplayRecords = aaData.size();
	}
	
	/*public JsonPack(int sEcho, List<T> aaData) {
		super();
		this.sEcho = sEcho;
		this.aaData = aaData;
		iTotalRecords = aaData.size();
		iTotalDisplayRecords = aaData.size();
	}
	
	public int getsEcho() {
		return sEcho;
	}
	public void setsEcho(int sEcho) {
		this.sEcho = sEcho;
	}
	public int getiTotalRecords() {
		return iTotalRecords;
	}
	public void setiTotalRecords(int iTotalRecords) {
		this.iTotalRecords = iTotalRecords;
	}
	public int getiTotalDisplayRecords() {
		return iTotalDisplayRecords;
	}
	public void setiTotalDisplayRecords(int iTotalDisplayRecords) {
		this.iTotalDisplayRecords = iTotalDisplayRecords;
	}*/
	public List<T> getAaData() {
		return aaData;
	}
	public void setAaData(List<T> aaData) {
		this.aaData = aaData;
	}

	
	
}
