package com.zehin.vpaas.mapper;

import com.zehin.vpaas.beans.ConveLog;
import com.zehin.vpaas.beans.DECDrugAdviceContent;
import com.zehin.vpaas.generic.BaseDao;

public interface ConveLogMapper extends BaseDao<ConveLog, String> {
	public void log(ConveLog cl);
}
