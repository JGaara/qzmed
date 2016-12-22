package com.zehin.vpaas.service.impl;

import com.zehin.vpaas.beans.FailLog;
import com.zehin.vpaas.generic.BaseServiceImpl;
import com.zehin.vpaas.generic.CustomerContextHolder;
import com.zehin.vpaas.mapper.FailLogMapper;

public class FailLogServiceImpl extends BaseServiceImpl<FailLogMapper, FailLog, String> {
	public void setMapper(FailLogMapper mapper) {
		CustomerContextHolder.setCustomerType(CustomerContextHolder.DATA_SOURCE_ORACLE);
		super.mapper = mapper;
	}
	
	public void Log(FailLog cl) {
		mapper.log(cl);
	}
}
