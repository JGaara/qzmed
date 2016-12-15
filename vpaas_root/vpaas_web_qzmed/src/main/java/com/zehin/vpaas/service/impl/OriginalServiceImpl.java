package com.zehin.vpaas.service.impl;

import com.zehin.vpaas.beans.*;
import com.zehin.vpaas.generic.*;
import com.zehin.vpaas.mapper.*;
import com.zehin.vpaas.service.*;

public class OriginalServiceImpl extends BaseServiceImpl<OriginalMapper, Original, String> {
	public void setMapper(OriginalMapper mapper) {
		CustomerContextHolder.setCustomerType(CustomerContextHolder.DATA_SOURCE_ORACLE);
		super.mapper = mapper;
	}
	
}
