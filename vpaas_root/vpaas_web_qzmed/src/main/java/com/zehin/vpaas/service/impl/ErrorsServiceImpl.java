package com.zehin.vpaas.service.impl;

import com.zehin.vpaas.generic.*;
import com.zehin.vpaas.mapper.*;

import java.util.List;

import com.zehin.vpaas.beans.*;

public class ErrorsServiceImpl extends BaseServiceImpl<ErrorsMapper, Errors, String>{
	public void setMapper(ErrorsMapper mapper) {
		CustomerContextHolder.setCustomerType(CustomerContextHolder.DATA_SOURCE_ORACLE);
		super.mapper = mapper;
	}
	
	public List<Errors> findPage(Pager model){
		List<Errors> list = mapper.findPage(model);
		return list;
	}
}
