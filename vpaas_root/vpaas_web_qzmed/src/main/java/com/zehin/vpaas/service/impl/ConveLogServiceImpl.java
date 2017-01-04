package com.zehin.vpaas.service.impl;

import java.util.List;

import com.zehin.vpaas.beans.ConveLog;
import com.zehin.vpaas.beans.Pager;
import com.zehin.vpaas.generic.BaseServiceImpl;
import com.zehin.vpaas.generic.CustomerContextHolder;
import com.zehin.vpaas.mapper.ConveLogMapper;

public class ConveLogServiceImpl extends BaseServiceImpl<ConveLogMapper, ConveLog, String> {
	public void setMapper(ConveLogMapper mapper) {
		CustomerContextHolder.setCustomerType(CustomerContextHolder.DATA_SOURCE_ORACLE);
		super.mapper = mapper;
	}
	
	public void Log(ConveLog cl) {
		mapper.log(cl);
	}
	
	public List<ConveLog> findNew(ConveLog model){
		List<ConveLog> list = mapper.findNew(model);
		return list;
	}
	public List<ConveLog> findAllConvert(ConveLog model){
		List<ConveLog> list = mapper.findAllConvert(model);
		return list;
	}
	public List<ConveLog> findConvert(Pager model){
		List<ConveLog> list = mapper.findConvert(model);
		return list;
	}
	public List<ConveLog> findHand(Pager model){
		List<ConveLog> list = mapper.findHand(model);
		return list;
	}
	public List<ConveLog> findAllHand(ConveLog model){
		List<ConveLog> list = mapper.findAllHand(model);
		return list;
	}
}
