package com.zehin.vpaas.mapper;

import java.util.List;

import com.zehin.vpaas.beans.ConveLog;
import com.zehin.vpaas.beans.DECDrugAdviceContent;
import com.zehin.vpaas.beans.Pager;
import com.zehin.vpaas.generic.BaseDao;

public interface ConveLogMapper extends BaseDao<ConveLog, String> {
	public void log(ConveLog cl);
	
	public List<ConveLog> findNew(ConveLog model);
	public List<ConveLog> findAllConvert(ConveLog model);
	public List<ConveLog> findConvert(Pager model);
	public List<ConveLog> findHand(Pager model);
	public List<ConveLog> findAllHand(ConveLog model);
}
