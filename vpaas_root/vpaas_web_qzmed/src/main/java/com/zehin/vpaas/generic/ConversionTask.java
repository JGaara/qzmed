package com.zehin.vpaas.generic;
import java.util.*;

import org.apache.log4j.Logger;
import com.github.pagehelper.PageHelper;
import com.zehin.vpaas.beans.*;
import com.zehin.vpaas.common.util.SerialNumGenerator;
import com.zehin.vpaas.common.util.SpringBeanUtil;
import com.zehin.vpaas.mapper.*;
import com.zehin.vpaas.service.impl.*;
import com.github.pagehelper.*;
public class ConversionTask {
    private SerialNumGenerator sng;
    private final int pageSize = 200;
    private static Logger logger = Logger.getLogger("ConversionTask.class");
    public ConversionTask() {
    	sng = new SerialNumGenerator();
    }
	public void convert() {
		logger.info("启动转存...");
		//门诊诊疗登记
		carryDECRegistration();
		//门诊就诊记录
		carryDECTreatmentRecords();
		//门诊处方主表
		carryDECDrugAdvice();
		//门诊处方明细
		carryDECDrugAdviceDetail();
		//门诊收费明细
		carryDECFareDetail();
		//门诊费用结算
		carryDECFareBalance();
		
		
		//住院登记
		carryDEHRegistration();
		//住院医嘱主表
		carryDEHDrugAdvice();
		//住院医嘱明细
		carryDEHDrugAdviceDetail();
		//住院费用明细
		carryDEHFareDetail();
		//住院费用结算
		carryDEHFareBalance();
		logger.info("转存完毕...");
	}
	
	
	public void carryDEHDrugAdviceDetail() {
		ViewAccessState vas = lastAccess(CommonCode.ViewNameZYYZMX);
		System.out.println(vas.getSubmitDate());
		DEHDrugAdviceDetailContentServiceImpl service = SpringBeanUtil.getBean("dehDrugAdviceDetailContentService");
		DEHDrugAdviceDetailContentMapper mapper= SpringBeanUtil.getApplicationContext().getBean(DEHDrugAdviceDetailContentMapper.class);
		//切换数据库，并设置mapper.
		service.setMapper(mapper);
		
		List<DEHDrugAdviceDetailContent> resultList = null;
		int pageNum = 1;
		Page<DEHDrugAdviceDetailContent> page = PageHelper.startPage(pageNum, pageSize);
		resultList = service.findByPage(vas);
		PageInfo<DEHDrugAdviceDetailContent> pi = new PageInfo<DEHDrugAdviceDetailContent>(page);
		//总页数
		long totalPages = pi.getPages();
		Iterator<DEHDrugAdviceDetailContent> it = resultList.iterator();
		while(it.hasNext()) {
			DEHDrugAdviceDetailContent content = (DEHDrugAdviceDetailContent) it.next();
			Original o = DEHDrugAdviceDetail.getOriginal(content, sng.getNum());
	//		//System.out.println(content.getPatientName());
			writeToOriginal(o);
		}
		pi.setPageNum(++pageNum);
		System.out.println("hh" + resultList.size());
		while(pageNum <= totalPages) {
			service.setMapper(mapper);
			PageHelper.startPage(pageNum, pageSize);
			resultList = service.findByPage(vas);
			
			it = resultList.iterator();
			while(it.hasNext()) {
				DEHDrugAdviceDetailContent content = (DEHDrugAdviceDetailContent) it.next();
				Original o = DEHDrugAdviceDetail.getOriginal(content, sng.getNum());
				writeToOriginal(o);
			}
		}
		
		//如果有新的，更新记录。
		if(resultList.size() > 0) {
			DEHDrugAdviceDetailContent content = resultList.get(resultList.size() - 1);
			ViewAccessState newLast = new ViewAccessState(CommonCode.ViewNameZYYZMX, new java.sql.Timestamp(new Date().getTime()),
					"1", new java.sql.Timestamp(content.getSubmitDate().getTime()));
			updateState(newLast);
		}
		
		logger.info("住院医嘱明细转存完毕。");
	}
	public void carryDEHFareBalance() {
		ViewAccessState vas = lastAccess(CommonCode.ViewNameZYFYJS);
		System.out.println(vas.getSubmitDate());
		DEHFareBalanceContentServiceImpl service = SpringBeanUtil.getBean("dehFareBalanceContentService");
		DEHFareBalanceContentMapper mapper= SpringBeanUtil.getApplicationContext().getBean(DEHFareBalanceContentMapper.class);
		//切换数据库，并设置mapper.
		service.setMapper(mapper);
		
		List<DEHFareBalanceContent> resultList = null;
		int pageNum = 1;
		Page<DEHFareBalanceContent> page = PageHelper.startPage(pageNum, pageSize);
		resultList = service.findByPage(vas);
		PageInfo<DEHFareBalanceContent> pi = new PageInfo<DEHFareBalanceContent>(page);
		//总页数
		long totalPages = pi.getPages();
		Iterator<DEHFareBalanceContent> it = resultList.iterator();
		while(it.hasNext()) {
			DEHFareBalanceContent content = (DEHFareBalanceContent) it.next();
			Original o = DEHFareBalance.getOriginal(content, sng.getNum());
	//		//System.out.println(content.getPatientName());
			writeToOriginal(o);
		}
		pi.setPageNum(++pageNum);
		System.out.println("hh" + resultList.size());
		while(pageNum <= totalPages) {
			service.setMapper(mapper);
			PageHelper.startPage(pageNum, pageSize);
			resultList = service.findByPage(vas);
			
			it = resultList.iterator();
			while(it.hasNext()) {
				DEHFareBalanceContent content = (DEHFareBalanceContent) it.next();
				Original o = DEHFareBalance.getOriginal(content, sng.getNum());
				writeToOriginal(o);
			}
		}
		
		//如果有新的，更新记录。
		if(resultList.size() > 0) {
			DEHFareBalanceContent content = resultList.get(resultList.size() - 1);
			ViewAccessState newLast = new ViewAccessState(CommonCode.ViewNameZYFYJS, new java.sql.Timestamp(new java.util.Date().getTime()),
					"1", new java.sql.Timestamp(content.getSubmitDate().getTime()));
			updateState(newLast);
		}
		
		logger.info("住院费用结算转存完毕。");
	}
	public void carryDEHFareDetail() {
		ViewAccessState vas = lastAccess(CommonCode.ViewNameZYSFMX);
		System.out.println(vas.getSubmitDate() + "----" + vas.getAccessDate());
		DEHFareDetailContentServiceImpl service = SpringBeanUtil.getBean("dehFareDetailContentService");
		DEHFareDetailContentMapper mapper= SpringBeanUtil.getApplicationContext().getBean(DEHFareDetailContentMapper.class);
		//切换数据库，并设置mapper.
		service.setMapper(mapper);
		
		List<DEHFareDetailContent> resultList = null;
		int pageNum = 1;
		Page<DEHFareDetailContent> page = PageHelper.startPage(pageNum, pageSize);
		resultList = service.findByPage(vas);
		PageInfo<DEHFareDetailContent> pi = new PageInfo<DEHFareDetailContent>(page);
		//总页数
		long totalPages = pi.getPages();
		Iterator<DEHFareDetailContent> it = resultList.iterator();
		while(it.hasNext()) {
			DEHFareDetailContent content = (DEHFareDetailContent) it.next();
			Original o = DEHFareDetail.getOriginal(content, sng.getNum());
			writeToOriginal(o);
		}
		pi.setPageNum(++pageNum);
		System.out.println("hh" + resultList.size());
		while(pageNum <= totalPages) {
			service.setMapper(mapper);
			PageHelper.startPage(pageNum, pageSize);
			resultList = service.findByPage(vas);
			
			it = resultList.iterator();
			while(it.hasNext()) {
				DEHFareDetailContent content = (DEHFareDetailContent) it.next();
				Original o = DEHFareDetail.getOriginal(content, sng.getNum());
				writeToOriginal(o);
			}
		}
		
		//如果有新的，更新记录。
		if(resultList.size() > 0) {
			DEHFareDetailContent content = resultList.get(resultList.size() - 1);
			ViewAccessState newLast = new ViewAccessState(CommonCode.ViewNameZYSFMX, new java.sql.Timestamp(new java.util.Date().getTime()),
					"1", new java.sql.Timestamp(content.getSubmitDate().getTime()));
			updateState(newLast);
		}
		logger.info("住院费用明细转存完毕。");
	}
	public void carryDEHDrugAdvice() {
		ViewAccessState vas = lastAccess(CommonCode.ViewNameZYYZZB);
		System.out.println(vas.getSubmitDate());
		DEHDrugAdviceContentServiceImpl service = SpringBeanUtil.getBean("dehDrugAdviceContentService");
		DEHDrugAdviceContentMapper mapper= SpringBeanUtil.getApplicationContext().getBean(DEHDrugAdviceContentMapper.class);
		//切换数据库，并设置mapper.
		service.setMapper(mapper);
		
		List<DEHDrugAdviceContent> resultList = null;
		int pageNum = 1;
		Page<DEHDrugAdviceContent> page = PageHelper.startPage(pageNum, pageSize);
		resultList = service.findByPage(vas);
		PageInfo<DEHDrugAdviceContent> pi = new PageInfo<DEHDrugAdviceContent>(page);
		//总页数
		long totalPages = pi.getPages();
		Iterator<DEHDrugAdviceContent> it = resultList.iterator();
		while(it.hasNext()) {
			DEHDrugAdviceContent content = (DEHDrugAdviceContent) it.next();
			Original o = DEHDrugAdvice.getOriginal(content, sng.getNum());
	//		//System.out.println(content.getPatientName());
			writeToOriginal(o);
		}
		pi.setPageNum(++pageNum);
		System.out.println("hh" + resultList.size());
		while(pageNum <= totalPages) {
			service.setMapper(mapper);
			PageHelper.startPage(pageNum, pageSize);
			resultList = service.findByPage(vas);
			
			it = resultList.iterator();
			while(it.hasNext()) {
				DEHDrugAdviceContent content = (DEHDrugAdviceContent) it.next();
				Original o = DEHDrugAdvice.getOriginal(content, sng.getNum());
				writeToOriginal(o);
			}
		}
		
		//如果有新的，更新记录。
		if(resultList.size() > 0) {
			DEHDrugAdviceContent content = resultList.get(resultList.size() - 1);
			ViewAccessState newLast = new ViewAccessState(CommonCode.ViewNameZYYZZB, new java.sql.Timestamp(new java.util.Date().getTime()),
					"1", new java.sql.Timestamp(content.getSubmitDate().getTime()));
			updateState(newLast);
		}
		logger.info("住院医嘱主表转存完毕。");
	}
	public void carryDEHRegistration() {
		ViewAccessState vas = lastAccess(CommonCode.ViewNameZYFWDJ);
		System.out.println(vas.getSubmitDate());
		DEHRegistrationContentServiceImpl service = SpringBeanUtil.getBean("dehRegistrationContentService");
		DEHRegistrationContentMapper mapper= SpringBeanUtil.getApplicationContext().getBean(DEHRegistrationContentMapper.class);
		//切换数据库，并设置mapper.
		service.setMapper(mapper);
		
		List<DEHRegistrationContent> resultList = null;
		int pageNum = 1;
		Page<DEHRegistrationContent> page = PageHelper.startPage(pageNum, pageSize);
		resultList = service.findByPage(vas);
		PageInfo<DEHRegistrationContent> pi = new PageInfo<DEHRegistrationContent>(page);
		//总页数
		long totalPages = pi.getPages();
		Iterator<DEHRegistrationContent> it = resultList.iterator();
		while(it.hasNext()) {
			DEHRegistrationContent content = (DEHRegistrationContent) it.next();
			Original o = DEHRegistration.getOriginal(content, sng.getNum());
	//		//System.out.println(content.getPatientName());
			writeToOriginal(o);
		}
		pi.setPageNum(++pageNum);
		System.out.println("hh" + resultList.size());
		while(pageNum <= totalPages) {
			service.setMapper(mapper);
			PageHelper.startPage(pageNum, pageSize);
			resultList = service.findByPage(vas);
			
			it = resultList.iterator();
			while(it.hasNext()) {
				DEHRegistrationContent content = (DEHRegistrationContent) it.next();
				Original o = DEHRegistration.getOriginal(content, sng.getNum());
				writeToOriginal(o);
			}
		}
		
		//如果有新的，更新记录。
		if(resultList.size() > 0) {
			DEHRegistrationContent content = resultList.get(resultList.size() - 1);
			ViewAccessState newLast = new ViewAccessState(CommonCode.ViewNameZYFWDJ, new java.sql.Timestamp(new java.util.Date().getTime()),
					"1", new java.sql.Timestamp(content.getSubmitDate().getTime()));
			updateState(newLast);
		}
		logger.info("住院登记转存完毕。");
	}
	
	
	
	
	
	public void carryDECTreatmentRecords() {
		ViewAccessState vas = lastAccess(CommonCode.ViewNameMZJZJL);
		System.out.println(vas.getSubmitDate());
		DECTreatmentRecordsContentServiceImpl service = SpringBeanUtil.getBean("decTreatmentRecordsContentService");
		DECTreatmentRecordsContentMapper mapper= SpringBeanUtil.getApplicationContext().getBean(DECTreatmentRecordsContentMapper.class);
		//切换数据库，并设置mapper.
		service.setMapper(mapper);
		
		List<DECTreatmentRecordsContent> resultList = null;
		int pageNum = 1;
		Page<DECTreatmentRecordsContent> page = PageHelper.startPage(pageNum, pageSize);
		resultList = service.findByPage(vas);
		PageInfo<DECTreatmentRecordsContent> pi = new PageInfo<DECTreatmentRecordsContent>(page);
		//总页数
		long totalPages = pi.getPages();
		Iterator<DECTreatmentRecordsContent> it = resultList.iterator();
		while(it.hasNext()) {
			DECTreatmentRecordsContent content = (DECTreatmentRecordsContent) it.next();
			Original o = DECTreatmentRecords.getOriginal(content, sng.getNum());
	//		//System.out.println(content.getPatientName());
			writeToOriginal(o);
		}
		pi.setPageNum(++pageNum);
		System.out.println("hh" + resultList.size());
		while(pageNum <= totalPages) {
			service.setMapper(mapper);
			PageHelper.startPage(pageNum, pageSize);
			resultList = service.findByPage(vas);
			
			it = resultList.iterator();
			while(it.hasNext()) {
				DECTreatmentRecordsContent content = (DECTreatmentRecordsContent) it.next();
				Original o = DECTreatmentRecords.getOriginal(content, sng.getNum());
				writeToOriginal(o);
			}
		}
		
		//如果有新的，更新记录。
		if(resultList.size() > 0) {
			DECTreatmentRecordsContent content = resultList.get(resultList.size() - 1);
			ViewAccessState newLast = new ViewAccessState(CommonCode.ViewNameMZJZJL, new java.sql.Timestamp(new java.util.Date().getTime()),
					"1", new java.sql.Timestamp(content.getSubmitDate().getTime()));
			updateState(newLast);
		}
		logger.info("门诊诊疗记录转存完毕。");
	}
	
	public void carryDECRegistration() {
		ViewAccessState vas = lastAccess(CommonCode.ViewNameMZZLDJ);
		System.out.println(vas.getSubmitDate());
		DECRegistrationContentServiceImpl service = SpringBeanUtil.getBean("decRegistrationContentService");
		DECRegistrationContentMapper mapper= SpringBeanUtil.getApplicationContext().getBean(DECRegistrationContentMapper.class);
		//切换数据库，并设置mapper.
		service.setMapper(mapper);
		
		List<DECRegistrationContent> resultList = null;
		int pageNum = 1;
		Page<DECRegistrationContent> page = PageHelper.startPage(pageNum, pageSize);
		resultList = service.findByPage(vas);
		PageInfo<DECRegistrationContent> pi = new PageInfo<DECRegistrationContent>(page);
		//总页数
		long totalPages = pi.getPages();
		Iterator<DECRegistrationContent> it = resultList.iterator();
		while(it.hasNext()) {
			DECRegistrationContent content = (DECRegistrationContent) it.next();
			Original o = DECRegistration.getOriginal(content, sng.getNum());
	//		//System.out.println(content.getPatientName());
			writeToOriginal(o);
		}
		pi.setPageNum(++pageNum);
		System.out.println("hh" + resultList.size());
		while(pageNum <= totalPages) {
			service.setMapper(mapper);
			PageHelper.startPage(pageNum, pageSize);
			resultList = service.findByPage(vas);
			
			it = resultList.iterator();
			while(it.hasNext()) {
				DECRegistrationContent content = (DECRegistrationContent) it.next();
				Original o = DECRegistration.getOriginal(content, sng.getNum());
				writeToOriginal(o);
			}
		}
		
		//如果有新的，更新记录。
		if(resultList.size() > 0) {
			DECRegistrationContent content = resultList.get(resultList.size() - 1);
			ViewAccessState newLast = new ViewAccessState(CommonCode.ViewNameMZZLDJ, new java.sql.Timestamp(new java.util.Date().getTime()),
					"1", new java.sql.Timestamp(content.getSubmitDate().getTime()));
			updateState(newLast);
		}
		logger.info("门诊诊疗登记转存完毕。");
	}
	
	public void carryDECFareDetail() {
		ViewAccessState vas = lastAccess(CommonCode.ViewNameMZSFMX);
		System.out.println(vas.getSubmitDate());
		DECFareDetailContentServiceImpl service = SpringBeanUtil.getBean("decFareDetailContentService");
		DECFareDetailContentMapper mapper= SpringBeanUtil.getApplicationContext().getBean(DECFareDetailContentMapper.class);
		//切换数据库，并设置mapper.
		service.setMapper(mapper);
		
		List<DECFareDetailContent> resultList = null;
		int pageNum = 1;
		Page<DECFareDetailContent> page = PageHelper.startPage(pageNum, pageSize);
		resultList = service.findByPage(vas);
		PageInfo<DECFareDetailContent> pi = new PageInfo<DECFareDetailContent>(page);
		//总页数
		long totalPages = pi.getPages();
		Iterator<DECFareDetailContent> it = resultList.iterator();
		while(it.hasNext()) {
			DECFareDetailContent content = (DECFareDetailContent) it.next();
			Original o = DECFareDetail.getOriginal(content, sng.getNum());
	//		//System.out.println(content.getPatientName());
			writeToOriginal(o);
		}
		pi.setPageNum(++pageNum);
		System.out.println("hh" + resultList.size());
		while(pageNum <= totalPages) {
			service.setMapper(mapper);
			PageHelper.startPage(pageNum, pageSize);
			resultList = service.findByPage(vas);
			
			it = resultList.iterator();
			while(it.hasNext()) {
				DECFareDetailContent content = (DECFareDetailContent) it.next();
				Original o = DECFareDetail.getOriginal(content, sng.getNum());
				writeToOriginal(o);
			}
		}
		
		//如果有新的，更新记录。
		if(resultList.size() > 0) {
			DECFareDetailContent content = resultList.get(resultList.size() - 1);
			ViewAccessState newLast = new ViewAccessState(CommonCode.ViewNameMZSFMX, new java.sql.Timestamp(new java.util.Date().getTime()),
					"1", new java.sql.Timestamp(content.getSubmitDate().getTime()));
			updateState(newLast);
		}
		logger.info("门诊费用明细转存完毕。");
	}
	public void carryDECFareBalance() {
		ViewAccessState vas = lastAccess(CommonCode.ViewNameMZFYJS);
		System.out.println(vas.getSubmitDate());
		DECFareBalanceContentServiceImpl service = SpringBeanUtil.getBean("decFareBalanceContentService");
		DECFareBalanceContentMapper mapper= SpringBeanUtil.getApplicationContext().getBean(DECFareBalanceContentMapper.class);
		//切换数据库，并设置mapper.
		service.setMapper(mapper);
		
		List<DECFareBalanceContent> resultList = null;
		int pageNum = 1;
		Page<DECFareBalanceContent> page = PageHelper.startPage(pageNum, pageSize);
		resultList = service.findByPage(vas);
		PageInfo<DECFareBalanceContent> pi = new PageInfo<DECFareBalanceContent>(page);
		//总页数
		long totalPages = pi.getPages();
		Iterator<DECFareBalanceContent> it = resultList.iterator();
		while(it.hasNext()) {
			DECFareBalanceContent content = (DECFareBalanceContent) it.next();
			Original o = DECFareBalance.getOriginal(content, sng.getNum());
	//		//System.out.println(content.getPatientName());
			writeToOriginal(o);
		}
		pi.setPageNum(++pageNum);
		System.out.println("hh" + resultList.size());
		while(pageNum <= totalPages) {
			service.setMapper(mapper);
			PageHelper.startPage(pageNum, pageSize);
			resultList = service.findByPage(vas);
			
			it = resultList.iterator();
			while(it.hasNext()) {
				DECFareBalanceContent content = (DECFareBalanceContent) it.next();
				Original o = DECFareBalance.getOriginal(content, sng.getNum());
				writeToOriginal(o);
			}
		}
		
		//如果有新的，更新记录。
		if(resultList.size() > 0) {
			DECFareBalanceContent content = resultList.get(resultList.size() - 1);
			ViewAccessState newLast = new ViewAccessState(CommonCode.ViewNameMZFYJS, new java.sql.Timestamp(new java.util.Date().getTime()),
					"1", new java.sql.Timestamp(content.getSubmitDate().getTime()));
			updateState(newLast);
		}
		logger.info("门诊费用结算转存完毕。");
	}
	
	public void carryDECDrugAdvice() {
		ViewAccessState vas = lastAccess(CommonCode.ViewNameMZCFZB);
		System.out.println(vas.getSubmitDate());
		DECDrugAdviceContentServiceImpl service = SpringBeanUtil.getBean("decDrugAdviceContentService");
		DECDrugAdviceContentMapper mapper= SpringBeanUtil.getApplicationContext().getBean(DECDrugAdviceContentMapper.class);
		//切换数据库，并设置mapper.
		service.setMapper(mapper);
		
		List<DECDrugAdviceContent> resultList = null;
		int pageNum = 1;
		Page<DECDrugAdviceContent> page = PageHelper.startPage(pageNum, pageSize);
		resultList = service.findByPage(vas);
		PageInfo<DECDrugAdviceContent> pi = new PageInfo<DECDrugAdviceContent>(page);
		//总页数
		long totalPages = pi.getPages();
		Iterator<DECDrugAdviceContent> it = resultList.iterator();
		while(it.hasNext()) {
			DECDrugAdviceContent content = (DECDrugAdviceContent) it.next();
			Original o = DECDrugAdvice.getOriginal(content, sng.getNum());
	//		//System.out.println(content.getPatientName());
			writeToOriginal(o);
		}
		pi.setPageNum(++pageNum);
		System.out.println("hh" + resultList.size());
		while(pageNum <= totalPages) {
			service.setMapper(mapper);
			PageHelper.startPage(pageNum, pageSize);
			resultList = service.findByPage(vas);
			
			it = resultList.iterator();
			while(it.hasNext()) {
				DECDrugAdviceContent content = (DECDrugAdviceContent) it.next();
				Original o = DECDrugAdvice.getOriginal(content, sng.getNum());
				writeToOriginal(o);
			}
		}
		
		//如果有新的，更新记录。
		if(resultList.size() > 0) {
			DECDrugAdviceContent content = resultList.get(resultList.size() - 1);
			ViewAccessState newLast = new ViewAccessState(CommonCode.ViewNameMZCFZB, new java.sql.Timestamp(new java.util.Date().getTime()),
					"1", new java.sql.Timestamp(content.getSubmitDate().getTime()));
			updateState(newLast);
		}
		logger.info("门诊医嘱主表转存完毕。");
	}
	public void carryDECDrugAdviceDetail() {
		ViewAccessState vas = lastAccess(CommonCode.ViewNameMZCFMX);
		System.out.println(vas.getSubmitDate());
		DECDrugAdviceDetailContentServiceImpl service = SpringBeanUtil.getBean("decDrugAdviceDetailContentService");
		DECDrugAdviceDetailContentMapper mapper= SpringBeanUtil.getApplicationContext().getBean(DECDrugAdviceDetailContentMapper.class);
		//切换数据库，并设置mapper.
		service.setMapper(mapper);
		
		List<DECDrugAdviceDetailContent> resultList = null;
		int pageNum = 1;
		Page<DECDrugAdviceDetailContent> page = PageHelper.startPage(pageNum, pageSize);
		resultList = service.findByPage(vas);
		PageInfo<DECDrugAdviceDetailContent> pi = new PageInfo<DECDrugAdviceDetailContent>(page);
		//总页数
		long totalPages = pi.getPages();
		Iterator<DECDrugAdviceDetailContent> it = resultList.iterator();
		while(it.hasNext()) {
			DECDrugAdviceDetailContent content = (DECDrugAdviceDetailContent) it.next();
			Original o = DECDrugAdviceDetail.getOriginal(content, sng.getNum());
	//		//System.out.println(content.getPatientName());
			writeToOriginal(o);
		}
		pi.setPageNum(++pageNum);
		System.out.println("hh" + resultList.size());
		while(pageNum <= totalPages) {
			service.setMapper(mapper);
			PageHelper.startPage(pageNum, pageSize);
			resultList = service.findByPage(vas);
			
			it = resultList.iterator();
			while(it.hasNext()) {
				DECDrugAdviceDetailContent content = (DECDrugAdviceDetailContent) it.next();
				Original o = DECDrugAdviceDetail.getOriginal(content, sng.getNum());
				writeToOriginal(o);
			}
		}
		
		//如果有新的，更新记录。
		if(resultList.size() > 0) {
			DECDrugAdviceDetailContent content = resultList.get(resultList.size() - 1);
			ViewAccessState newLast = new ViewAccessState(CommonCode.ViewNameMZCFMX, new java.sql.Timestamp(new java.util.Date().getTime()),
					"1", new java.sql.Timestamp(content.getSubmitDate().getTime()));
			updateState(newLast);
		}
		logger.info("门诊医嘱明细转存完毕。");
	}
		
	private void writeToOriginal(Original o) {
		OriginalServiceImpl service = SpringBeanUtil.getBean("originalService");
		OriginalMapper mapper = SpringBeanUtil.getApplicationContext().getBean(OriginalMapper.class);
		service.setMapper(mapper);
		service.insert(o);
	}

	private ViewAccessState lastAccess(String viewName) {
		ViewAccessStateServiceimpl service = SpringBeanUtil.getBean("viewAccessStateService");
		ViewAccessStateMapper mapper = SpringBeanUtil.getApplicationContext().getBean(ViewAccessStateMapper.class);
		service.setMapper(mapper);
		ViewAccessState vas = new ViewAccessState(viewName, new java.sql.Timestamp(new Date().getTime()), "1", new java.sql.Timestamp(0L));
		ViewAccessState lastVas = service.getLastByView(vas);
		if(lastVas == null) {
			return vas;
		} else {
			lastVas.setAccessDate(new java.sql.Timestamp(new Date().getTime()));
			return lastVas;
		}
	}
	
	private void updateState(ViewAccessState vas) {
		ViewAccessStateServiceimpl service = SpringBeanUtil.getBean("viewAccessStateService");
		ViewAccessStateMapper mapper = SpringBeanUtil.getApplicationContext().getBean(ViewAccessStateMapper.class);
		service.setMapper(mapper);
		service.addNewAccessTime(vas);
	}
}
