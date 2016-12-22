package com.zehin.vpaas.beans;

import java.sql.Timestamp;

public class FailLog {
	private String ViewName;
	private String FailMsg;
	private Timestamp FailTime;
	public String getViewName() {
		return ViewName;
	}
	public void setViewName(String viewName) {
		ViewName = viewName;
	}
	public String getFailMsg() {
		return FailMsg;
	}
	public void setFailMsg(String failMsg) {
		FailMsg = failMsg;
	}
	public Timestamp getFailTime() {
		return FailTime;
	}
	public void setFailTime(Timestamp failTime) {
		FailTime = failTime;
	}
	
	
}
