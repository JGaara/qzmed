package com.zehin.dec.test;

import java.util.*;

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
public class ViewAccessStateTest {
	
	@Resource
	private ViewAccessStateServiceimpl viewAccessStateService;
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	/*@Test
	public void testSelectList() {
		ViewAccessState vas = new ViewAccessState("V_MZ_FYJS", new java.sql.Date(new java.util.Date().getTime()), "1");
		ViewAccessStateMapper vasm = SpringBeanUtil.getApplicationContext().getBean(ViewAccessStateMapper.class);
		viewAccessStateService.setMapper(vasm);
		List a = viewAccessStateService.getLastAccessTimes();
		System.out.println(a.size());
	}
	@Test
	public void testAdd() {
		ViewAccessState vas = new ViewAccessState("V_MZ_FYJS", new java.sql.Date(new java.util.Date().getTime()), "1");
		ViewAccessStateMapper vasm = SpringBeanUtil.getApplicationContext().getBean(ViewAccessStateMapper.class);
		viewAccessStateService.setMapper(vasm);
		viewAccessStateService.addNewAccessTime(vas);
	}*/
	@Test
	public void testgetByView() {
		/*ViewAccessState vas = new ViewAccessState("V_MZ_FYJS", new java.sql.Date(new Date().getTime()), "1", new java.sql.Date(new Date().getTime()));
		ViewAccessStateMapper vasm = SpringBeanUtil.getApplicationContext().getBean(ViewAccessStateMapper.class);
		viewAccessStateService.setMapper(vasm);
		System.out.println(viewAccessStateService.getLastByView(vas).getAccessDate());*/
	}
}
