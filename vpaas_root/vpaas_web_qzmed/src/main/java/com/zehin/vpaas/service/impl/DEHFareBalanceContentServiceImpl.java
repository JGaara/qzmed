package com.zehin.vpaas.service.impl;

import com.zehin.vpaas.beans.*;
import com.zehin.vpaas.generic.BaseServiceImpl;
import com.zehin.vpaas.generic.CustomerContextHolder;
import com.zehin.vpaas.mapper.*;

public class DEHFareBalanceContentServiceImpl
		extends BaseServiceImpl<DEHFareBalanceContentMapper, DEHFareBalanceContent, String> {
	public void setMapper(DEHFareBalanceContentMapper mapper) {
		CustomerContextHolder.setCustomerType(CustomerContextHolder.DATA_SOURCE_MSSQL);
		super.mapper = mapper;
	}
}
