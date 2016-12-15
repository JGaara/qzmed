package com.zehin.vpaas.service.impl;

import java.util.List;

import com.zehin.vpaas.beans.ViewAccessState;
import com.zehin.vpaas.generic.CustomerContextHolder;
import com.zehin.vpaas.mapper.ViewAccessStateMapper;

public class ViewAccessStateServiceimpl {
	private ViewAccessStateMapper<ViewAccessState> vasm = null;
	public void setMapper(ViewAccessStateMapper<ViewAccessState> mapper) {
		CustomerContextHolder.setCustomerType(CustomerContextHolder.DATA_SOURCE_ORACLE);
		vasm = mapper;
		
	}
	
	public List<ViewAccessState> getLastAccessTimes() {
		return vasm.getLastAccessTimes();
	}
	
	public void addNewAccessTime(ViewAccessState vas) {
		vasm.outDate(vas);
		vasm.addAccessTime(vas);
	}
	
	public ViewAccessState getLastByView(ViewAccessState vas) {
		return (ViewAccessState) vasm.getLastByView(vas);
	}
}
