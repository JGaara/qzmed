package com.zehin.vpaas.mapper;

import java.util.List;

import com.zehin.vpaas.beans.Errors;
import com.zehin.vpaas.beans.Pager;
import com.zehin.vpaas.generic.BaseDao;

public interface ErrorsMapper extends BaseDao<Errors, String>{
	public List<Errors> findPage(Pager pager);
}
