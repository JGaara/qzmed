package com.zehin.vpaas.service.impl;

import com.zehin.vpaas.beans.*;
import com.zehin.vpaas.generic.BaseServiceImpl;
import com.zehin.vpaas.generic.CustomerContextHolder;
import com.zehin.vpaas.mapper.*;

public class DEHDrugAdviceContentServiceImpl
		extends BaseServiceImpl<DEHDrugAdviceContentMapper, DEHDrugAdviceContent, String> {
	public void setMapper(DEHDrugAdviceContentMapper mapper) {
		CustomerContextHolder.setCustomerType(CustomerContextHolder.DATA_SOURCE_MSSQL);
		super.mapper = mapper;
	}
}
