package com.zehin.dec.test;

import java.util.List;

import javax.annotation.Resource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zehin.vpaas.beans.DECDrugAdviceContent;
import com.zehin.vpaas.beans.DECRegistration;
import com.zehin.vpaas.beans.DECRegistrationContent;
import com.zehin.vpaas.common.util.JaxbUtil;
import com.zehin.vpaas.common.util.SpringBeanUtil;
import com.zehin.vpaas.mapper.DECDrugAdviceContentMapper;
import com.zehin.vpaas.service.impl.DECDrugAdviceContentServiceImpl;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class DecDrugAdviceTest {
	
	@Resource
	private DECDrugAdviceContentServiceImpl decDrugAdviceContentService;
	
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
	
/*	@Test
	public void testInsert(){
		DECRegistrationContent content=new DECRegistrationContent();
		content.setSrno("1692");
		content.setRegNo("540003859");
		decRegistrationContentService.insert(content);
	}
*/
/*	@Test
	public void testSelect(){
		DECRegistrationContentMapper drcm = (DECRegistrationContentMapper) 
				SpringBeanUtil.getApplicationContext().getBean(DECRegistrationContentMapper.class);
		decRegistrationContentService.setMapper(drcm);
		DECRegistrationContent content=decRegistrationContentService.selectById("1692");
		DECRegistration dec = new DECRegistration();
		dec.setHospitalCode(content.getHospitalCode());
		//证书?
		dec.setHospitalLicense(content.getHospitalCode());
		dec.setContent(content);
		Original o = new Original();
		o.setRecordId("hahah");
		o.setBusinessTypeNo("heh");
		o.setCreateDate(new java.sql.Date(new java.util.Date().getTime()));
		o.setDomainCode("2");
		o.setDomainName("22");
		o.setIdCard("3707");
		o.setLicense("good");
		o.setOrgCode("3303");
		o.setOrgName("haha");
		o.setServiceType("1");
		o.setHandleMark("2");
		o.setAreaCode("3");
		o.setBodyXml(JaxbUtil.convertToXml(dec).getBytes());
		OriginalMapper om = SpringBeanUtil.getApplicationContext().getBean(OriginalMapper.class);
		OriginalServiceImpl osi = SpringBeanUtil.getBean("originalService");
		osi.setDb(null);
		osi.setMapper(om);
		osi.insert(o);
		System.out.println(content.getAppTypeCode());
	}
	*/
	@Test
	public void testFindAll() {
		DECDrugAdviceContentMapper drcm = (DECDrugAdviceContentMapper) 
				SpringBeanUtil.getApplicationContext().getBean(DECDrugAdviceContentMapper.class);
		decDrugAdviceContentService.setMapper(drcm);
		List<DECDrugAdviceContent> decRegContent = decDrugAdviceContentService.findAll(new DECDrugAdviceContent());
		System.out.println("size:" + decRegContent.get(0).getSubmitDate());
	}
}
