package com.zehin.vpaas.mapper;

import com.zehin.vpaas.beans.FailLog;
import com.zehin.vpaas.generic.BaseDao;

public interface FailLogMapper extends BaseDao<FailLog, String> {
	public void log(FailLog fl);
}
