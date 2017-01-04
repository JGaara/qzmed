package com.zehin.vpaas.beans;

import java.sql.Timestamp;

public class ConveLogBody {
	private String ViewName;
	private String Count;
	private String IsAuto;
	private String StartTime;
	private String EndTime;
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
	public String getIsAuto() {
		return IsAuto;
	}
	public void setIsAuto(String isAuto) {
		IsAuto = isAuto;
	}
	public String getStartTime() {
		return StartTime;
	}
	public void setStartTime(String startTime) {
		StartTime = startTime;
	}
	public String getEndTime() {
		return EndTime;
	}
	public void setEndTime(String endTime) {
		EndTime = endTime;
	}
	
}
