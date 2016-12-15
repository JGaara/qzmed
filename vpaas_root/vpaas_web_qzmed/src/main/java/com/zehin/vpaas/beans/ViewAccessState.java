package com.zehin.vpaas.beans;

import java.sql.Date;
import java.sql.Timestamp;
import java.sql.Date;


public class ViewAccessState {
	private String ViewName;
	private Timestamp SubmitDate;
	private Timestamp AccessDate;
	private String IsLast;
	public ViewAccessState() {}
	public ViewAccessState(String viewName, Timestamp accessDate, String isLast, Timestamp submitDate) {
		this.ViewName = viewName;
		this.AccessDate = accessDate;
		this.IsLast = isLast;
		this.SubmitDate = submitDate;
	}
	
	
	public Timestamp getSubmitDate() {
		return SubmitDate;
	}
	public void setSubmitDate(Timestamp submitDate) {
		SubmitDate = submitDate;
	}
	public void setAccessDate(Timestamp accessDate) {
		AccessDate = accessDate;
	}
	public String getViewName() {
		return ViewName;
	}
	public void setViewName(String viewName) {
		ViewName = viewName;
	}
	
	public Timestamp getAccessDate() {
		return AccessDate;
	}
	public String getIsLast() {
		return IsLast;
	}
	public void setIsLast(String isLast) {
		IsLast = isLast;
	}
	
}
