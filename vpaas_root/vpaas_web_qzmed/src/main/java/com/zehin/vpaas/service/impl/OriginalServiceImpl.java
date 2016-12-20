package com.zehin.vpaas.service.impl;

import java.util.List;

import org.apache.ibatis.session.*;
import org.apache.ibatis.session.defaults.DefaultSqlSessionFactory;

import com.zehin.vpaas.beans.*;
import com.zehin.vpaas.common.util.SpringBeanUtil;
import com.zehin.vpaas.generic.*;
import com.zehin.vpaas.mapper.*;
import com.zehin.vpaas.service.*;

public class OriginalServiceImpl extends BaseServiceImpl<OriginalMapper, Original, String> {
	public void setMapper(OriginalMapper mapper) {
		CustomerContextHolder.setCustomerType(CustomerContextHolder.DATA_SOURCE_ORACLE);
		super.mapper = mapper;
	}
	
	public void insertBatch(List<Original> ol) {
		DefaultSqlSessionFactory sqlSessionFac = SpringBeanUtil.getBean("sqlServerSessionFactory");
		SqlSession batchSqlSession = null;
		try{
            batchSqlSession = sqlSessionFac.openSession(ExecutorType.BATCH, false);
            int batchCount = 500;//每批commit的个数
            for(int index = 0; index < ol.size();index++){
                Original o = ol.get(index);
                batchSqlSession.getMapper(OriginalMapper.class).insert(o);
               /* if(index !=0 && index%batchCount == 0){
                    batchSqlSession.commit();
                }*/
            }
            batchSqlSession.commit();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(batchSqlSession != null){
                batchSqlSession.close();
            }
        }
	}
	
}
