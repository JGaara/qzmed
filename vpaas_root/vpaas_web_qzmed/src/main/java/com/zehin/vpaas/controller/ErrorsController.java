package com.zehin.vpaas.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zehin.vpaas.beans.*;
import com.zehin.vpaas.common.util.SpringBeanUtil;
import com.zehin.vpaas.generic.ConversionTask;
import com.zehin.vpaas.mapper.*;
import com.zehin.vpaas.service.impl.*;

@Controller
@RequestMapping(value = "/")
public class ErrorsController {
	
	@Resource
	private ErrorsServiceImpl errorService = SpringBeanUtil.getBean("errorsService");
	private OriginalServiceImpl originalService = SpringBeanUtil.getBean("originalService");
	private ConversionTask converService = SpringBeanUtil.getBean("conversionTask");
	private Pager pager = null;
	
	@RequestMapping("/error")
	public String testFindAll(Model model, HttpServletRequest request, HttpServletResponse response){
		ErrorsMapper drcm = (ErrorsMapper) 
				SpringBeanUtil.getApplicationContext().getBean(ErrorsMapper.class);
		errorService.setMapper(drcm);
		List<Errors> erList = errorService.findAll(new Errors());
		List<ErrorBody> list = new ArrayList<ErrorBody>();
		SimpleDateFormat formatter = new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss");;
		pager = PagerHelper.getPager(request.getParameter("m"), request.getParameter("c"), erList.size());
		erList = errorService.findPage(pager);
		for(Errors er : erList){
			ErrorBody eb = new ErrorBody();
			eb.setServiceType(er.getServiceType());
			eb.setRecordId(er.getRecordId());
			eb.setBusinessTypeNo(er.getBusinessTypeNo());
			eb.setAreaCode(er.getAreaCode());
			eb.setIDCard(er.getIDCard());
			eb.setDomainCode(er.getDomainCode());
			eb.setDomainName(er.getDomainName());
			eb.setLicense(er.getLicense());
			try {
				eb.setBodyXml(new String(er.getBodyXml(), "utf-8").trim().substring(0, 10)+"……");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			eb.setOrgCode(er.getOrgCode());
			eb.setOrgName(er.getOrgName());
			eb.setErrorInfo(er.getErrorInfo());
			eb.setModifyMark(er.getModifyMark());
			if(er.getCreateDate() != null){
				eb.setCreateDate(formatter.format(er.getCreateDate()));
			}
			if(er.getUpdateDate() != null){
				eb.setUpdateDate(formatter.format(er.getUpdateDate()));
			}
			//System.out.println("serviceType:" + eb.getServiceType());
			list.add(eb);
		}
		model.addAttribute("eorList", list);
		model.addAttribute("pager", pager);
		//System.out.println("size:" + erList.size());
		return "error";
	}
	
	@RequestMapping("/modify")
	public String modifyError(Model model, HttpServletRequest request, HttpServletResponse response){
		String record = (String)request.getParameter("record");
		//System.out.println(record);
		ErrorsMapper drcm = (ErrorsMapper) 
				SpringBeanUtil.getApplicationContext().getBean(ErrorsMapper.class);
		errorService.setMapper(drcm);
		Errors eor = errorService.selectById(record);
		String bodyXml = "";
		if(eor.getBodyXml() != null){
			try {
				bodyXml = new String(eor.getBodyXml(), "utf-8").trim();
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		model.addAttribute("eor", eor);
		model.addAttribute("bodyxml", bodyXml);
		return "modifyerror";
	}
	
	@RequestMapping("/save")
	public void saveModify(ErrorBody error, Model model, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		String serviceType = error.getServiceType();
		String recordId = error.getRecordId();
		String bodyxml = error.getBodyXml().trim();
		ErrorsMapper drcm = (ErrorsMapper) 
				SpringBeanUtil.getApplicationContext().getBean(ErrorsMapper.class);
		errorService.setMapper(drcm);
		//System.out.println("121212121212121===" + serviceType +"====="+ bodyxml);
		/**
		 *根据主键更新error表的内容 
		 */
		Errors er = errorService.selectById(recordId);
		byte []bXml = bodyxml.getBytes("utf-8");
		Date dt = new Date();
		er.setBodyXml(bXml);
		er.setUpdateDate(new java.sql.Timestamp(dt.getTime()));
		er.setModifyMark("1");
		errorService.update(er);
		/**
		 * 重新上传到original表中
		 */
		Original or = new Original();
		or.setServiceType(er.getServiceType());
		or.setRecordId(er.getRecordId());
		or.setBusinessTypeNo(er.getBusinessTypeNo());
		or.setAreaCode(er.getAreaCode());
		or.setDomainCode(er.getDomainCode());
		or.setDomainName(er.getDomainName());
		or.setIdCard(er.getIDCard());
		or.setCreateDate(er.getCreateDate());
		or.setUpdateDate(new java.sql.Timestamp(dt.getTime()));
		or.setLicense(er.getLicense());
		or.setBodyXml(er.getBodyXml());
		or.setOrgCode(er.getOrgCode());
		or.setOrgName(er.getOrgName());
		//System.out.println("121212121212121===" + er.getOrgCode() +"====="+ er.getOrgName());
		or.setHandleMark("0");
		OriginalMapper ocm = (OriginalMapper)SpringBeanUtil.getApplicationContext().getBean(OriginalMapper.class);
		originalService.setMapper(ocm);
		int res =  originalService.insert(or);
		
		
		List<Errors> erList = errorService.findAll(new Errors());
		model.addAttribute("eorList", erList);
		//return res;
	}
	@RequestMapping("/submit")
	public void handSubmit(Model model, HttpServletRequest request, HttpServletResponse response) throws IOException {
		//System.out.println("121212121212121=== ajax submit");
		converService = SpringBeanUtil.getBean("conversionTask");
		int res = converService.couvertByHand();
		PrintWriter out = response.getWriter();
		out.print(res);
		//return 0;
	}
	
	
}
