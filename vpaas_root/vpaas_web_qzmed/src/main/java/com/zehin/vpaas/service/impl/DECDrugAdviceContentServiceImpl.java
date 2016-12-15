package com.zehin.vpaas.service.impl;

import com.zehin.vpaas.beans.DECDrugAdviceContent;
import com.zehin.vpaas.generic.BaseServiceImpl;
import com.zehin.vpaas.generic.CustomerContextHolder;
import com.zehin.vpaas.mapper.DECDrugAdviceContentMapper;

public class DECDrugAdviceContentServiceImpl
		extends BaseServiceImpl<DECDrugAdviceContentMapper, DECDrugAdviceContent, String> {
	public void setMapper(DECDrugAdviceContentMapper mapper) {
		CustomerContextHolder.setCustomerType(CustomerContextHolder.DATA_SOURCE_MSSQL);
		super.mapper = mapper;
	}
}
