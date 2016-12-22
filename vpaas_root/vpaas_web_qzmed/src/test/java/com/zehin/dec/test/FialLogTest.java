package com.zehin.dec.test;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zehin.vpaas.beans.*;
import com.zehin.vpaas.common.util.JaxbUtil;
import com.zehin.vpaas.common.util.SpringBeanUtil;
import com.zehin.vpaas.generic.ConversionTask;
import com.zehin.vpaas.mapper.*;
import com.zehin.vpaas.service.impl.*;
import com.github.pagehelper.Page;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class FialLogTest {
	
	@Resource
	private DECDrugAdviceDetailContentServiceImpl decDrugAdviceDetailContentService;
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testConvertion() {
		ConversionTask task = new ConversionTask();
	//	task.updateFailLog(null, new Date(), "nothing");
	}
}
