package com.zehin.vpaas.service.impl;

import com.zehin.vpaas.beans.DECTreatmentRecordsContent;
import com.zehin.vpaas.generic.BaseServiceImpl;
import com.zehin.vpaas.generic.CustomerContextHolder;
import com.zehin.vpaas.mapper.DECTreatmentRecordsContentMapper;

public class DECTreatmentRecordsContentServiceImpl
		extends BaseServiceImpl<DECTreatmentRecordsContentMapper, DECTreatmentRecordsContent, String> {
	public void setMapper(DECTreatmentRecordsContentMapper mapper) {
		CustomerContextHolder.setCustomerType(CustomerContextHolder.DATA_SOURCE_MSSQL);
		super.mapper = mapper;
	}
}
