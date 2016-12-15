package com.zehin.vpaas.service.impl;

import com.zehin.vpaas.beans.DECFareDetailContent;
import com.zehin.vpaas.generic.BaseServiceImpl;
import com.zehin.vpaas.generic.CustomerContextHolder;
import com.zehin.vpaas.mapper.DECFareDetailContentMapper;

public class DECFareDetailContentServiceImpl
		extends BaseServiceImpl<DECFareDetailContentMapper, DECFareDetailContent, String> {
	public void setMapper(DECFareDetailContentMapper mapper) {
		CustomerContextHolder.setCustomerType(CustomerContextHolder.DATA_SOURCE_MSSQL);
		super.mapper = mapper;
	}
}
