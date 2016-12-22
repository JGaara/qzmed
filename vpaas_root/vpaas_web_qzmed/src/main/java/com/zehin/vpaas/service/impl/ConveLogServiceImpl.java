package com.zehin.vpaas.service.impl;

import com.zehin.vpaas.beans.ConveLog;
import com.zehin.vpaas.generic.BaseServiceImpl;
import com.zehin.vpaas.generic.CustomerContextHolder;
import com.zehin.vpaas.mapper.ConveLogMapper;

public class ConveLogServiceImpl extends BaseServiceImpl<ConveLogMapper, ConveLog, String> {
	public void setMapper(ConveLogMapper mapper) {
		CustomerContextHolder.setCustomerType(CustomerContextHolder.DATA_SOURCE_ORACLE);
		super.mapper = mapper;
	}
	
	public void Log(ConveLog cl) {
		mapper.log(cl);
	}
}
