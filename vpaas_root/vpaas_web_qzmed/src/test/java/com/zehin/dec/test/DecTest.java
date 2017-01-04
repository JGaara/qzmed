package com.zehin.dec.test;

import java.sql.Timestamp;

import javax.annotation.Resource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zehin.vpaas.beans.DECRegistration;
import com.zehin.vpaas.beans.DECRegistrationContent;
import com.zehin.vpaas.common.util.JaxbUtil;
import com.zehin.vpaas.service.DECRegistrationContentService;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class DecTest {
	
	@Resource
	private DECRegistrationContentService decRegistrationContentService;
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testXML() {
		DECRegistrationContent content=new DECRegistrationContent();
		content.setSrno("1692");
		content.setRegNo("540003859");
		
		DECRegistration dec=new DECRegistration();
		dec.setHospitalCode("1");
		dec.setHospitalLicense("2");
		dec.setContent(content);
		
		String decXml = JaxbUtil.convertToXml(dec);
		System.out.println(decXml);
		
		DECRegistration dec2= JaxbUtil.converyToJavaBean(decXml, DECRegistration.class);
		
		System.out.println(dec2.getHospitalCode());
	}
	
	@Test
	public void testInsert(){
		//DECRegistrationContent content=new DECRegistrationContent();
		//content.setSrno("1692");
		//content.setRegNo("540003859");
		//decRegistrationContentService.insert(content);
	}

	@Test
	public void testSelect(){
		//DECRegistrationContent content=decRegistrationContentService.selectById("1692");
		//System.out.println(content.getRegNo());
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		String tsStr = "2011-05-09 11:49:45";
		try {
			ts = Timestamp.valueOf(tsStr);
			System.out.println(ts);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
