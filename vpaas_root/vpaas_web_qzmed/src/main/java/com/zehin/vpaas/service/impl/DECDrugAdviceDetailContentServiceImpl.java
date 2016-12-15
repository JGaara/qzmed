package com.zehin.vpaas.service.impl;

import java.util.List;

import com.zehin.vpaas.beans.DECDrugAdviceDetailContent;
import com.zehin.vpaas.generic.BaseServiceImpl;
import com.zehin.vpaas.generic.CustomerContextHolder;
import com.zehin.vpaas.mapper.DECDrugAdviceContentMapper;
import com.zehin.vpaas.mapper.DECDrugAdviceDetailContentMapper;

public class DECDrugAdviceDetailContentServiceImpl
		extends BaseServiceImpl<DECDrugAdviceDetailContentMapper, DECDrugAdviceDetailContent, String> {
	public void setMapper(DECDrugAdviceDetailContentMapper mapper) {
		CustomerContextHolder.setCustomerType(CustomerContextHolder.DATA_SOURCE_MSSQL);
		super.mapper = mapper;
	}

}
