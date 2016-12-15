package com.zehin.dec.test;

import javax.annotation.Resource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zehin.vpaas.generic.HospitalBaseInfo;
import com.zehin.vpaas.service.impl.DECDrugAdviceDetailContentServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class HospitalBaseInfoTest {
	
	@Resource
	private DECDrugAdviceDetailContentServiceImpl decDrugAdviceDetailContentService;
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void sett() {
		HospitalBaseInfo baseInfo = new HospitalBaseInfo();
		System.out.println(baseInfo.getHosName());
		System.out.println(baseInfo.getHosCode());
		System.out.println(baseInfo.getHosLicense());
		System.out.println(baseInfo.getHosAreaCode());
		
	}
	
}
