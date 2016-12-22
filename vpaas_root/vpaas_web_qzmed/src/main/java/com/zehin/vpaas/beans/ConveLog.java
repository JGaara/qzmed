package com.zehin.vpaas.beans;

import java.sql.*;

public class ConveLog {
	private String ViewName;
	private String Count;
	private String IsAuto;
	private Timestamp StartTime;
	private Timestamp EndTime;
	public String getViewName() {
		return ViewName;
	}
	public void setViewName(String viewName) {
		ViewName = viewName;
	}
	public String getCount() {
		return Count;
	}
	public void setCount(String count) {
		Count = count;
	}
	public String getIsAtuo() {
		return IsAuto;
	}
	public void setIsAtuo(String isAtuo) {
		IsAuto = isAtuo;
	}
	public Timestamp getStartTime() {
		return StartTime;
	}
	public void setStartTime(Timestamp startTime) {
		StartTime = startTime;
	}
	public Timestamp getEndTime() {
		return EndTime;
	}
	public void setEndTime(Timestamp endTime) {
		EndTime = endTime;
	}
	
}
