package com.zehin.vpaas.service.impl;

import com.zehin.vpaas.beans.DECFareBalanceContent;
import com.zehin.vpaas.generic.BaseServiceImpl;
import com.zehin.vpaas.generic.CustomerContextHolder;
import com.zehin.vpaas.mapper.DECFareBalanceContentMapper;

public class DECFareBalanceContentServiceImpl
		extends BaseServiceImpl<DECFareBalanceContentMapper, DECFareBalanceContent, String> {
	public void setMapper(DECFareBalanceContentMapper mapper) {
		CustomerContextHolder.setCustomerType(CustomerContextHolder.DATA_SOURCE_MSSQL);
		super.mapper = mapper;
	}
}
