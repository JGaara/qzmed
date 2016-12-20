package com.zehin.vpaas.mapper;

import java.util.List;

import com.zehin.vpaas.beans.Original;
import com.zehin.vpaas.generic.BaseDao;

public interface OriginalMapper extends BaseDao<Original, String>  {
	public void insertBatch(List<Original> oriList);
	public void insert(Original o);
}
