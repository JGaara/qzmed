package com.zehin.vpaas.generic;
import java.sql.Timestamp;

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
    private final int pageSize = 10000;
    private final int taskSize = 1000000;
    private int isAuto = -1;
    private int taskCurrent = 0;
    private static Logger logger = Logger.getLogger("ConversionTask.class");
    public ConversionTask() {
    	isAuto = -1;
    	taskCurrent = 0;
    	sng = new SerialNumGenerator();
    	System.out.println("start the task");
    }
    
    public int couvertByHand() {
    	if (isAuto != -1) {
    		//其他任务正在运行。
    		updateFailLog(null,  new Date(), "task aready running");
    		return 2;
    	}
    	isAuto = 0;
    	int state = 0;
    	try {
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
    		isAuto = -1;
    		state = 0;
    		} catch(Exception e) {
    			isAuto = -1;
    			e.printStackTrace();
    			updateFailLog(null,  new Date(), e.getMessage());
    			logger.info(e.getStackTrace());
    			state = 1;
    			return state;
    			
    		}
    	return state;
    }
    /**
     * 
     * @return 0.正常结束     1.失败。
     */
	public int convert() {
		if (isAuto != -1) {
			//其他任务正在运行。
    		updateFailLog(null,  new Date(), "task aready running");
    		return 2;
		}
		try {
		isAuto = 1;
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
		isAuto = -1;
		return 0;
		} catch(Exception e) {
			isAuto = -1;
			e.printStackTrace();
			updateFailLog(null,  new Date(), e.getMessage());
			logger.info(e.getStackTrace());
			return 1;
			
		}
	}
	
	
	public void carryDEHDrugAdviceDetail() {
		//如果任务数量完成，退出。
		if(taskCurrent >= taskSize) {return;}
		int count = 0;
		Date startTime = new Date();
		//获取数据库操作类.
		DEHDrugAdviceDetailContentServiceImpl service = SpringBeanUtil.getBean("dehDrugAdviceDetailContentService");
		DEHDrugAdviceDetailContentMapper mapper= SpringBeanUtil.getApplicationContext().getBean(DEHDrugAdviceDetailContentMapper.class);
		
		
		//数据库list
		List<DEHDrugAdviceDetailContent> resultList = null;
		List<Original> originalList = new ArrayList();
		
		int pageNum = 1;
		
		//上次访问状态.
		ViewAccessState vas = lastAccess(CommonCode.ViewNameZYYZMX);
		//获取第一页
		service.setMapper(mapper);
		Page<DEHDrugAdviceDetailContent> page = PageHelper.startPage(pageNum, pageSize);		
		System.out.println("find a page:" + pageNum + ", time:"+ new Date());
		resultList = service.findByPage(vas);
		PageInfo<DEHDrugAdviceDetailContent> pi = new PageInfo<DEHDrugAdviceDetailContent>(page);
		taskCurrent += resultList.size();
		
		System.out.println("after find a page:" + pageNum + ", time:"+ new Date());
		
		//转换第一页
		for(int i = 0; i < resultList.size(); i++) {
			DEHDrugAdviceDetailContent content = resultList.get(i);
			Original o = DEHDrugAdviceDetail.getOriginal(content, sng.getNum());
			originalList.add(o);
		}
		
		//写入第一页
		System.out.println("before write:" + pageNum + ", time:"+ new Date());
		writeToOriginal(originalList);
		count += resultList.size();
		
		//判断任务完成量,如果完成，退出，如果未完成继续。
		if (taskCurrent >= taskSize) {
			//记录
			DEHDrugAdviceDetailContent contentLast = resultList.get(resultList.size() - 1);
			ViewAccessState newLast = new ViewAccessState(CommonCode.ViewNameZYYZMX, new java.sql.Timestamp(new java.util.Date().getTime()),
					"1", new java.sql.Timestamp(contentLast.getSubmitDate().getTime()));
			updateState(newLast);
			//获取下一页，取出时间相同的。
			pi.setPageNum(++pageNum);
			service.setMapper(mapper);
			PageHelper.startPage(pageNum, pageSize);	
			resultList = service.findByPage(vas);
			originalList = new ArrayList();
			for(int i = 0; i < resultList.size(); i++) {
				DEHDrugAdviceDetailContent content = resultList.get(i);
				if(!content.getSubmitDate().equals(contentLast.getSubmitDate())) {
					break;
				}
				Original o = DEHDrugAdviceDetail.getOriginal(content, sng.getNum());
				originalList.add(o);
			}
			if(originalList.size() > 0) {
				writeToOriginal(originalList);
				count += resultList.size();
			}
			Date endTime = new Date();
			updateConveLog(CommonCode.ViewNameZYYZMX, count, startTime, endTime);
			return;
		}
		System.out.println("after write:" + pageNum + ", time:"+ new Date());
		
		//获取后续页
		long totalPages = pi.getPages();
		pi.setPageNum(++pageNum);
		while(pageNum <= totalPages) {
			originalList = new ArrayList();
			service.setMapper(mapper);
			
			System.out.println("find a page:" + pageNum + ", time:"+ new Date());
			PageHelper.startPage(pageNum, pageSize);	
			resultList = service.findByPage(vas);
			taskCurrent += resultList.size();
			count += resultList.size();
			System.out.println("after find a page:" + pageNum + ", time:"+ new Date());
			for(int i = 0; i < resultList.size(); i++) {
				DEHDrugAdviceDetailContent content = resultList.get(i);
				Original o = DEHDrugAdviceDetail.getOriginal(content, sng.getNum());
				originalList.add(o);
			}
			System.out.println("before write:" + pageNum + ", time:"+ new Date());
			writeToOriginal(originalList);
			count += resultList.size();
			if (taskCurrent >= taskSize) {
				//记录
				DEHDrugAdviceDetailContent contentLast = resultList.get(resultList.size() - 1);
				ViewAccessState newLast = new ViewAccessState(CommonCode.ViewNameZYYZMX, new java.sql.Timestamp(new java.util.Date().getTime()),
						"1", new java.sql.Timestamp(contentLast.getSubmitDate().getTime()));
				updateState(newLast);
				//获取下一页，取出时间相同的。
				pi.setPageNum(++pageNum);
				service.setMapper(mapper);
				PageHelper.startPage(pageNum, pageSize);	
				resultList = service.findByPage(vas);
				originalList = new ArrayList();
				for(int i = 0; i < resultList.size(); i++) {
					DEHDrugAdviceDetailContent content = resultList.get(i);
					if(!content.getSubmitDate().equals(contentLast.getSubmitDate())) {
						break;
					}
					Original o = DEHDrugAdviceDetail.getOriginal(content, sng.getNum());
					originalList.add(o);
				}
				if(originalList.size() > 0) {
					
					writeToOriginal(originalList);
					count += resultList.size();
				}
				Date endTime = new Date();
				updateConveLog(CommonCode.ViewNameZYYZMX, count, startTime, endTime);
				return;
			}
			System.out.println("after write:" + pageNum + ", time:"+ new Date());
			pi.setPageNum(++pageNum);
		}
		
		
		//如果有新的，更新记录。
		if(resultList.size() > 0) {
			DEHDrugAdviceDetailContent content = resultList.get(resultList.size() - 1);
			ViewAccessState newLast = new ViewAccessState(CommonCode.ViewNameZYYZMX, new java.sql.Timestamp(new java.util.Date().getTime()),
					"1", new java.sql.Timestamp(content.getSubmitDate().getTime()));
			updateState(newLast);
		}	
		
		Date endTime = new Date();
		updateConveLog(CommonCode.ViewNameZYYZMX, count, startTime, endTime);
		logger.info("住院医嘱明细转存完毕。");
	}
	public void carryDEHFareBalance() {
		//如果任务数量完成，退出。
		if(taskCurrent >= taskSize) {return;}
		int count = 0;
		Date startTime = new Date();
		//获取数据库操作类.
		DEHFareBalanceContentServiceImpl service = SpringBeanUtil.getBean("dehFareBalanceContentService");
		DEHFareBalanceContentMapper mapper= SpringBeanUtil.getApplicationContext().getBean(DEHFareBalanceContentMapper.class);
		
		
		//数据库list
		List<DEHFareBalanceContent> resultList = null;
		List<Original> originalList = new ArrayList();
		
		int pageNum = 1;
		
		//上次访问状态.
		ViewAccessState vas = lastAccess(CommonCode.ViewNameZYFYJS);
		//获取第一页
		service.setMapper(mapper);
		Page<DEHFareBalanceContent> page = PageHelper.startPage(pageNum, pageSize);		
		System.out.println("find a page:" + pageNum + ", time:"+ new Date());
		resultList = service.findByPage(vas);
		PageInfo<DEHFareBalanceContent> pi = new PageInfo<DEHFareBalanceContent>(page);
		taskCurrent += resultList.size();
		System.out.println("after find a page:" + pageNum + ", time:"+ new Date());
		
		//转换第一页
		for(int i = 0; i < resultList.size(); i++) {
			DEHFareBalanceContent content = resultList.get(i);
			Original o = DEHFareBalance.getOriginal(content, sng.getNum());
			originalList.add(o);
		}
		
		//写入第一页
		System.out.println("before write:" + pageNum + ", time:"+ new Date());
		writeToOriginal(originalList);
		count += resultList.size();
		
		
		//判断任务完成量,如果完成，退出，如果未完成继续。
		if (taskCurrent >= taskSize) {
			//记录
			DEHFareBalanceContent contentLast = resultList.get(resultList.size() - 1);
			ViewAccessState newLast = new ViewAccessState(CommonCode.ViewNameZYFYJS, new java.sql.Timestamp(new java.util.Date().getTime()),
					"1", new java.sql.Timestamp(contentLast.getSubmitDate().getTime()));
			updateState(newLast);
			//获取下一页，取出时间相同的。
			pi.setPageNum(++pageNum);
			service.setMapper(mapper);
			PageHelper.startPage(pageNum, pageSize);	
			resultList = service.findByPage(vas);
			originalList = new ArrayList();
			for(int i = 0; i < resultList.size(); i++) {
				DEHFareBalanceContent content = resultList.get(i);
				if(!content.getSubmitDate().equals(contentLast.getSubmitDate())) {
					break;
				}
				Original o = DEHFareBalance.getOriginal(content, sng.getNum());
				originalList.add(o);
			}
			if(originalList.size() > 0) {
				writeToOriginal(originalList);
				count += resultList.size();
			}
			Date endTime = new Date();
			updateConveLog(CommonCode.ViewNameZYFYJS, count, startTime, endTime);
			return;
		}
		System.out.println("after write:" + pageNum + ", time:"+ new Date());
		
		//获取后续页
		long totalPages = pi.getPages();
		pi.setPageNum(++pageNum);
		while(pageNum <= totalPages) {
			originalList = new ArrayList();
			service.setMapper(mapper);
			
			System.out.println("find a page:" + pageNum + ", time:"+ new Date());
			PageHelper.startPage(pageNum, pageSize);	
			resultList = service.findByPage(vas);
			taskCurrent += resultList.size();
			System.out.println("after find a page:" + pageNum + ", time:"+ new Date());
			for(int i = 0; i < resultList.size(); i++) {
				DEHFareBalanceContent content = resultList.get(i);
				Original o = DEHFareBalance.getOriginal(content, sng.getNum());
				originalList.add(o);
			}
			System.out.println("before write:" + pageNum + ", time:"+ new Date());
			writeToOriginal(originalList);
			count += resultList.size();
			if (taskCurrent >= taskSize) {
				//记录
				DEHFareBalanceContent contentLast = resultList.get(resultList.size() - 1);
				ViewAccessState newLast = new ViewAccessState(CommonCode.ViewNameZYFYJS, new java.sql.Timestamp(new java.util.Date().getTime()),
						"1", new java.sql.Timestamp(contentLast.getSubmitDate().getTime()));
				updateState(newLast);
				//获取下一页，取出时间相同的。
				pi.setPageNum(++pageNum);
				service.setMapper(mapper);
				PageHelper.startPage(pageNum, pageSize);	
				resultList = service.findByPage(vas);
				originalList = new ArrayList();
				for(int i = 0; i < resultList.size(); i++) {
					DEHFareBalanceContent content = resultList.get(i);
					if(!content.getSubmitDate().equals(contentLast.getSubmitDate())) {
						break;
					}
					Original o = DEHFareBalance.getOriginal(content, sng.getNum());
					originalList.add(o);
				}
				if(originalList.size() > 0) {
					writeToOriginal(originalList);
					count += resultList.size();
				}
				Date endTime = new Date();
				updateConveLog(CommonCode.ViewNameZYFYJS, count, startTime, endTime);
				return;
			}
			System.out.println("after write:" + pageNum + ", time:"+ new Date());
			pi.setPageNum(++pageNum);
		}
		
		//如果有新的，更新记录。
		if(resultList.size() > 0) {
			DEHFareBalanceContent content = resultList.get(resultList.size() - 1);
			ViewAccessState newLast = new ViewAccessState(CommonCode.ViewNameZYFYJS, new java.sql.Timestamp(new java.util.Date().getTime()),
					"1", new java.sql.Timestamp(content.getSubmitDate().getTime()));
			updateState(newLast);
		}	
		Date endTime = new Date();
		updateConveLog(CommonCode.ViewNameZYFYJS, count, startTime, endTime);
		logger.info("住院费用结算转存完毕。");
	}
	public void carryDEHFareDetail() {
		//如果任务数量完成，退出。
		if(taskCurrent >= taskSize) {return;}
		int count = 0;
		Date startTime = new Date();
		//获取数据库操作类.
		DEHFareDetailContentServiceImpl service = SpringBeanUtil.getBean("dehFareDetailContentService");
		DEHFareDetailContentMapper mapper= SpringBeanUtil.getApplicationContext().getBean(DEHFareDetailContentMapper.class);
		
		
		//数据库list
		List<DEHFareDetailContent> resultList = null;
		List<Original> originalList = new ArrayList();
		
		int pageNum = 1;
		
		//上次访问状态.
		ViewAccessState vas = lastAccess(CommonCode.ViewNameZYSFMX);
		//获取第一页
		service.setMapper(mapper);
		Page<DEHFareDetailContent> page = PageHelper.startPage(pageNum, pageSize);		
		System.out.println("find a page:" + pageNum + ", time:"+ new Date());
		resultList = service.findByPage(vas);
		PageInfo<DEHFareDetailContent> pi = new PageInfo<DEHFareDetailContent>(page);
		taskCurrent += resultList.size();
		System.out.println("after find a page:" + pageNum + ", time:"+ new Date());
		
		//转换第一页
		for(int i = 0; i < resultList.size(); i++) {
			DEHFareDetailContent content = resultList.get(i);
			Original o = DEHFareDetail.getOriginal(content, sng.getNum());
			originalList.add(o);
		}
		
		//写入第一页
		System.out.println("before write:" + pageNum + ", time:"+ new Date());
		writeToOriginal(originalList);
		count += resultList.size();
		
		//判断任务完成量,如果完成，退出，如果未完成继续。
		if (taskCurrent >= taskSize) {
			//记录
			DEHFareDetailContent contentLast = resultList.get(resultList.size() - 1);
			ViewAccessState newLast = new ViewAccessState(CommonCode.ViewNameZYSFMX, new java.sql.Timestamp(new java.util.Date().getTime()),
					"1", new java.sql.Timestamp(contentLast.getSubmitDate().getTime()));
			updateState(newLast);
			//获取下一页，取出时间相同的。
			pi.setPageNum(++pageNum);
			service.setMapper(mapper);
			PageHelper.startPage(pageNum, pageSize);	
			resultList = service.findByPage(vas);
			originalList = new ArrayList();
			for(int i = 0; i < resultList.size(); i++) {
				DEHFareDetailContent content = resultList.get(i);
				if(!content.getSubmitDate().equals(contentLast.getSubmitDate())) {
					break;
				}
				Original o = DEHFareDetail.getOriginal(content, sng.getNum());
				originalList.add(o);
			}
			if(originalList.size() > 0) {
				writeToOriginal(originalList);
				count += resultList.size();
			}
			Date endTime = new Date();
			updateConveLog(CommonCode.ViewNameZYSFMX, count, startTime, endTime);
			return;
		}
		System.out.println("after write:" + pageNum + ", time:"+ new Date());
		
		//获取后续页
		long totalPages = pi.getPages();
		pi.setPageNum(++pageNum);
		while(pageNum <= totalPages) {
			originalList = new ArrayList();
			service.setMapper(mapper);
			
			System.out.println("find a page:" + pageNum + ", time:"+ new Date());
			PageHelper.startPage(pageNum, pageSize);	
			resultList = service.findByPage(vas);
			taskCurrent += resultList.size();
			System.out.println("after find a page:" + pageNum + ", time:"+ new Date());
			for(int i = 0; i < resultList.size(); i++) {
				DEHFareDetailContent content = resultList.get(i);
				Original o = DEHFareDetail.getOriginal(content, sng.getNum());
				originalList.add(o);
			}
			System.out.println("before write:" + pageNum + ", time:"+ new Date());
			writeToOriginal(originalList);
			count += resultList.size();
			if (taskCurrent >= taskSize) {
				//记录
				DEHFareDetailContent contentLast = resultList.get(resultList.size() - 1);
				ViewAccessState newLast = new ViewAccessState(CommonCode.ViewNameZYSFMX, new java.sql.Timestamp(new java.util.Date().getTime()),
						"1", new java.sql.Timestamp(contentLast.getSubmitDate().getTime()));
				updateState(newLast);
				//获取下一页，取出时间相同的。
				pi.setPageNum(++pageNum);
				service.setMapper(mapper);
				PageHelper.startPage(pageNum, pageSize);	
				resultList = service.findByPage(vas);
				originalList = new ArrayList();
				for(int i = 0; i < resultList.size(); i++) {
					DEHFareDetailContent content = resultList.get(i);
					if(!content.getSubmitDate().equals(contentLast.getSubmitDate())) {
						break;
					}
					Original o = DEHFareDetail.getOriginal(content, sng.getNum());
					originalList.add(o);
				}
				if(originalList.size() > 0) {
					writeToOriginal(originalList);
					count += resultList.size();
				}
				Date endTime = new Date();
				updateConveLog(CommonCode.ViewNameZYSFMX, count, startTime, endTime);
				return;
			}
			System.out.println("after write:" + pageNum + ", time:"+ new Date());
			pi.setPageNum(++pageNum);
		}
		
		//如果有新的，更新记录。
		if(resultList.size() > 0) {
			DEHFareDetailContent content = resultList.get(resultList.size() - 1);
			ViewAccessState newLast = new ViewAccessState(CommonCode.ViewNameZYSFMX, new java.sql.Timestamp(new java.util.Date().getTime()),
					"1", new java.sql.Timestamp(content.getSubmitDate().getTime()));
			updateState(newLast);
		}	
		Date endTime = new Date();
		updateConveLog(CommonCode.ViewNameZYSFMX, count, startTime, endTime);
		logger.info("住院费用明细转存完毕。");
	}
	public void carryDEHDrugAdvice() {
		//如果任务数量完成，退出。
		if(taskCurrent >= taskSize) {return;}
		int count = 0;
		Date startTime = new Date();
		//获取数据库操作类.
		DEHDrugAdviceContentServiceImpl service = SpringBeanUtil.getBean("dehDrugAdviceContentService");
		DEHDrugAdviceContentMapper mapper= SpringBeanUtil.getApplicationContext().getBean(DEHDrugAdviceContentMapper.class);
		
		
		//数据库list
		List<DEHDrugAdviceContent> resultList = null;
		List<Original> originalList = new ArrayList();
		
		int pageNum = 1;
		
		//上次访问状态.
		ViewAccessState vas = lastAccess(CommonCode.ViewNameZYYZZB);
		//获取第一页
		service.setMapper(mapper);
		Page<DEHDrugAdviceContent> page = PageHelper.startPage(pageNum, pageSize);		
		System.out.println("find a page:" + pageNum + ", time:"+ new Date());
		resultList = service.findByPage(vas);
		PageInfo<DEHDrugAdviceContent> pi = new PageInfo<DEHDrugAdviceContent>(page);
		taskCurrent += resultList.size();
		System.out.println("after find a page:" + pageNum + ", time:"+ new Date());
		
		//转换第一页
		for(int i = 0; i < resultList.size(); i++) {
			DEHDrugAdviceContent content = resultList.get(i);
			Original o = DEHDrugAdvice.getOriginal(content, sng.getNum());
			originalList.add(o);
		}
		
		//写入第一页
		System.out.println("before write:" + pageNum + ", time:"+ new Date());
		writeToOriginal(originalList);
		count += resultList.size();
		
		//判断任务完成量,如果完成，退出，如果未完成继续。
		if (taskCurrent >= taskSize) {
			//记录
			DEHDrugAdviceContent contentLast = resultList.get(resultList.size() - 1);
			ViewAccessState newLast = new ViewAccessState(CommonCode.ViewNameZYYZZB, new java.sql.Timestamp(new java.util.Date().getTime()),
					"1", new java.sql.Timestamp(contentLast.getSubmitDate().getTime()));
			updateState(newLast);
			//获取下一页，取出时间相同的。
			pi.setPageNum(++pageNum);
			service.setMapper(mapper);
			PageHelper.startPage(pageNum, pageSize);	
			resultList = service.findByPage(vas);
			originalList = new ArrayList();
			for(int i = 0; i < resultList.size(); i++) {
				DEHDrugAdviceContent content = resultList.get(i);
				if(!content.getSubmitDate().equals(contentLast.getSubmitDate())) {
					break;
				}
				Original o = DEHDrugAdvice.getOriginal(content, sng.getNum());
				originalList.add(o);
			}
			if(originalList.size() > 0) {
				writeToOriginal(originalList);
				count += resultList.size();
			}
			Date endTime = new Date();
			updateConveLog(CommonCode.ViewNameZYYZZB, count, startTime, endTime);
			return;
		}
		System.out.println("after write:" + pageNum + ", time:"+ new Date());
		
		//获取后续页
		long totalPages = pi.getPages();
		pi.setPageNum(++pageNum);
		while(pageNum <= totalPages) {
			originalList = new ArrayList();
			service.setMapper(mapper);
			
			System.out.println("find a page:" + pageNum + ", time:"+ new Date());
			PageHelper.startPage(pageNum, pageSize);	
			resultList = service.findByPage(vas);
			taskCurrent += resultList.size();
			System.out.println("after find a page:" + pageNum + ", time:"+ new Date());
			for(int i = 0; i < resultList.size(); i++) {
				DEHDrugAdviceContent content = resultList.get(i);
				Original o = DEHDrugAdvice.getOriginal(content, sng.getNum());
				originalList.add(o);
			}
			System.out.println("before write:" + pageNum + ", time:"+ new Date());
			writeToOriginal(originalList);
			count += resultList.size();
			if (taskCurrent >= taskSize) {
				//记录
				DEHDrugAdviceContent contentLast = resultList.get(resultList.size() - 1);
				ViewAccessState newLast = new ViewAccessState(CommonCode.ViewNameZYYZZB, new java.sql.Timestamp(new java.util.Date().getTime()),
						"1", new java.sql.Timestamp(contentLast.getSubmitDate().getTime()));
				updateState(newLast);
				//获取下一页，取出时间相同的。
				pi.setPageNum(++pageNum);
				service.setMapper(mapper);
				PageHelper.startPage(pageNum, pageSize);	
				resultList = service.findByPage(vas);
				originalList = new ArrayList();
				for(int i = 0; i < resultList.size(); i++) {
					DEHDrugAdviceContent content = resultList.get(i);
					if(!content.getSubmitDate().equals(contentLast.getSubmitDate())) {
						break;
					}
					Original o = DEHDrugAdvice.getOriginal(content, sng.getNum());
					originalList.add(o);
				}
				if(originalList.size() > 0) {
					writeToOriginal(originalList);
					count += resultList.size();
				}
				Date endTime = new Date();
				updateConveLog(CommonCode.ViewNameZYYZZB, count, startTime, endTime);
				return;
			}
			System.out.println("after write:" + pageNum + ", time:"+ new Date());
			pi.setPageNum(++pageNum);
		}
		
		//如果有新的，更新记录。
		if(resultList.size() > 0) {
			DEHDrugAdviceContent content = resultList.get(resultList.size() - 1);
			ViewAccessState newLast = new ViewAccessState(CommonCode.ViewNameZYYZZB, new java.sql.Timestamp(new java.util.Date().getTime()),
					"1", new java.sql.Timestamp(content.getSubmitDate().getTime()));
			updateState(newLast);
		}
		Date endTime = new Date();
		updateConveLog(CommonCode.ViewNameZYYZZB, count, startTime, endTime);
		logger.info("住院医嘱主表转存完毕。");
	}
	public void carryDEHRegistration() {
		//如果任务数量完成，退出。
		if(taskCurrent >= taskSize) {return;}
		int count = 0;
		Date startTime = new Date();
		//获取数据库操作类.
		DEHRegistrationContentServiceImpl service = SpringBeanUtil.getBean("dehRegistrationContentService");
		DEHRegistrationContentMapper mapper= SpringBeanUtil.getApplicationContext().getBean(DEHRegistrationContentMapper.class);
		
		
		//数据库list
		List<DEHRegistrationContent> resultList = null;
		List<Original> originalList = new ArrayList();
		
		int pageNum = 1;
		
		//上次访问状态.
		ViewAccessState vas = lastAccess(CommonCode.ViewNameZYFWDJ);
		//获取第一页
		service.setMapper(mapper);
		Page<DEHRegistrationContent> page = PageHelper.startPage(pageNum, pageSize);		
		System.out.println("find a page:" + pageNum + ", time:"+ new Date());
		resultList = service.findByPage(vas);
		PageInfo<DEHRegistrationContent> pi = new PageInfo<DEHRegistrationContent>(page);
		taskCurrent += resultList.size();
		System.out.println("after find a page:" + pageNum + ", time:"+ new Date());
		
		//转换第一页
		for(int i = 0; i < resultList.size(); i++) {
			DEHRegistrationContent content = resultList.get(i);
			Original o = DEHRegistration.getOriginal(content, sng.getNum());
			originalList.add(o);
		}
		
		//写入第一页
		System.out.println("before write:" + pageNum + ", time:"+ new Date());
		writeToOriginal(originalList);
		count += resultList.size();
		
		//判断任务完成量,如果完成，退出，如果未完成继续。
		if (taskCurrent >= taskSize) {
			//记录
			DEHRegistrationContent contentLast = resultList.get(resultList.size() - 1);
			ViewAccessState newLast = new ViewAccessState(CommonCode.ViewNameZYFWDJ, new java.sql.Timestamp(new java.util.Date().getTime()),
					"1", new java.sql.Timestamp(contentLast.getSubmitDate().getTime()));
			updateState(newLast);
			//获取下一页，取出时间相同的。
			pi.setPageNum(++pageNum);
			service.setMapper(mapper);
			PageHelper.startPage(pageNum, pageSize);	
			resultList = service.findByPage(vas);
			originalList = new ArrayList();
			for(int i = 0; i < resultList.size(); i++) {
				DEHRegistrationContent content = resultList.get(i);
				if(!content.getSubmitDate().equals(contentLast.getSubmitDate())) {
					break;
				}
				Original o = DEHRegistration.getOriginal(content, sng.getNum());
				originalList.add(o);
			}
			if(originalList.size() > 0) {
				writeToOriginal(originalList);
				count += resultList.size();
			}
			Date endTime = new Date();
			updateConveLog(CommonCode.ViewNameZYFWDJ, count, startTime, endTime);
			return;
		}
		System.out.println("after write:" + pageNum + ", time:"+ new Date());
		
		//获取后续页
		long totalPages = pi.getPages();
		pi.setPageNum(++pageNum);
		while(pageNum <= totalPages) {
			originalList = new ArrayList();
			service.setMapper(mapper);
			
			System.out.println("find a page:" + pageNum + ", time:"+ new Date());
			PageHelper.startPage(pageNum, pageSize);	
			resultList = service.findByPage(vas);
			taskCurrent += resultList.size();
			System.out.println("after find a page:" + pageNum + ", time:"+ new Date());
			for(int i = 0; i < resultList.size(); i++) {
				DEHRegistrationContent content = resultList.get(i);
				Original o = DEHRegistration.getOriginal(content, sng.getNum());
				originalList.add(o);
			}
			System.out.println("before write:" + pageNum + ", time:"+ new Date());
			writeToOriginal(originalList);
			count += resultList.size();
			if (taskCurrent >= taskSize) {
				//记录
				DEHRegistrationContent contentLast = resultList.get(resultList.size() - 1);
				ViewAccessState newLast = new ViewAccessState(CommonCode.ViewNameZYFWDJ, new java.sql.Timestamp(new java.util.Date().getTime()),
						"1", new java.sql.Timestamp(contentLast.getSubmitDate().getTime()));
				updateState(newLast);
				//获取下一页，取出时间相同的。
				pi.setPageNum(++pageNum);
				service.setMapper(mapper);
				PageHelper.startPage(pageNum, pageSize);	
				resultList = service.findByPage(vas);
				originalList = new ArrayList();
				for(int i = 0; i < resultList.size(); i++) {
					DEHRegistrationContent content = resultList.get(i);
					if(!content.getSubmitDate().equals(contentLast.getSubmitDate())) {
						break;
					}
					Original o = DEHRegistration.getOriginal(content, sng.getNum());
					originalList.add(o);
				}
				if(originalList.size() > 0) {
					writeToOriginal(originalList);
					count += resultList.size();
				}
				Date endTime = new Date();
				updateConveLog(CommonCode.ViewNameZYFWDJ, count, startTime, endTime);
				return;
			}
			System.out.println("after write:" + pageNum + ", time:"+ new Date());
			pi.setPageNum(++pageNum);
		}
		
		//如果有新的，更新记录。
		if(resultList.size() > 0) {
			DEHRegistrationContent content = resultList.get(resultList.size() - 1);
			ViewAccessState newLast = new ViewAccessState(CommonCode.ViewNameZYFWDJ, new java.sql.Timestamp(new java.util.Date().getTime()),
					"1", new java.sql.Timestamp(content.getSubmitDate().getTime()));
			updateState(newLast);
		}	
		Date endTime = new Date();
		updateConveLog(CommonCode.ViewNameZYFWDJ, count, startTime, endTime);
		logger.info("住院登记转存完毕。");
	}
	
	
	
	
	
	public void carryDECTreatmentRecords() {
		//如果任务数量完成，退出。
		if(taskCurrent >= taskSize) {return;}
		int count  = 0;
		Date startTime = new Date();
		//获取数据库操作类.
		DECTreatmentRecordsContentServiceImpl service = SpringBeanUtil.getBean("decTreatmentRecordsContentService");
		DECTreatmentRecordsContentMapper mapper= SpringBeanUtil.getApplicationContext().getBean(DECTreatmentRecordsContentMapper.class);
		
		
		//数据库list
		List<DECTreatmentRecordsContent> resultList = null;
		List<Original> originalList = new ArrayList();
		
		int pageNum = 1;
		
		//上次访问状态.
		ViewAccessState vas = lastAccess(CommonCode.ViewNameMZJZJL);
		//获取第一页
		service.setMapper(mapper);
		Page<DECTreatmentRecordsContent> page = PageHelper.startPage(pageNum, pageSize);		
		System.out.println("find a page:" + pageNum + ", time:"+ new Date());
		resultList = service.findByPage(vas);
		PageInfo<DECTreatmentRecordsContent> pi = new PageInfo<DECTreatmentRecordsContent>(page);
		taskCurrent += resultList.size();
		System.out.println("after find a page:" + pageNum + ", time:"+ new Date());
		
		//转换第一页
		for(int i = 0; i < resultList.size(); i++) {
			DECTreatmentRecordsContent content = resultList.get(i);
			Original o = DECTreatmentRecords.getOriginal(content, sng.getNum());
			originalList.add(o);
		}
		
		//写入第一页
		System.out.println("before write:" + pageNum + ", time:"+ new Date());
		writeToOriginal(originalList);
		count += resultList.size();
		
		//判断任务完成量,如果完成，退出，如果未完成继续。
		if (taskCurrent >= taskSize) {
			//记录
			DECTreatmentRecordsContent contentLast = resultList.get(resultList.size() - 1);
			ViewAccessState newLast = new ViewAccessState(CommonCode.ViewNameMZJZJL, new java.sql.Timestamp(new java.util.Date().getTime()),
					"1", new java.sql.Timestamp(contentLast.getSubmitDate().getTime()));
			updateState(newLast);
			//获取下一页，取出时间相同的。
			pi.setPageNum(++pageNum);
			service.setMapper(mapper);
			PageHelper.startPage(pageNum, pageSize);	
			resultList = service.findByPage(vas);
			originalList = new ArrayList();
			for(int i = 0; i < resultList.size(); i++) {
				DECTreatmentRecordsContent content = resultList.get(i);
				if(!content.getSubmitDate().equals(contentLast.getSubmitDate())) {
					break;
				}
				Original o = DECTreatmentRecords.getOriginal(content, sng.getNum());
				originalList.add(o);
			}
			if(originalList.size() > 0) {
				writeToOriginal(originalList);
				count += resultList.size();
			}
			Date endTime = new Date();
			updateConveLog(CommonCode.ViewNameMZJZJL, count, startTime, endTime);
			return;
		}
		System.out.println("after write:" + pageNum + ", time:"+ new Date());
		
		//获取后续页
		long totalPages = pi.getPages();
		pi.setPageNum(++pageNum);
		while(pageNum <= totalPages) {
			originalList = new ArrayList();
			service.setMapper(mapper);
			
			System.out.println("find a page:" + pageNum + ", time:"+ new Date());
			PageHelper.startPage(pageNum, pageSize);	
			resultList = service.findByPage(vas);
			taskCurrent += resultList.size();
			System.out.println("after find a page:" + pageNum + ", time:"+ new Date());
			for(int i = 0; i < resultList.size(); i++) {
				DECTreatmentRecordsContent content = resultList.get(i);
				Original o = DECTreatmentRecords.getOriginal(content, sng.getNum());
				originalList.add(o);
			}
			System.out.println("before write:" + pageNum + ", time:"+ new Date());
			writeToOriginal(originalList);
			count += resultList.size();
			if (taskCurrent >= taskSize) {
				//记录
				DECTreatmentRecordsContent contentLast = resultList.get(resultList.size() - 1);
				ViewAccessState newLast = new ViewAccessState(CommonCode.ViewNameMZJZJL, new java.sql.Timestamp(new java.util.Date().getTime()),
						"1", new java.sql.Timestamp(contentLast.getSubmitDate().getTime()));
				updateState(newLast);
				//获取下一页，取出时间相同的。
				pi.setPageNum(++pageNum);
				service.setMapper(mapper);
				PageHelper.startPage(pageNum, pageSize);	
				resultList = service.findByPage(vas);
				originalList = new ArrayList();
				for(int i = 0; i < resultList.size(); i++) {
					DECTreatmentRecordsContent content = resultList.get(i);
					if(!content.getSubmitDate().equals(contentLast.getSubmitDate())) {
						break;
					}
					Original o = DECTreatmentRecords.getOriginal(content, sng.getNum());
					originalList.add(o);
				}
				if(originalList.size() > 0) {
					writeToOriginal(originalList);
					count += resultList.size();
				}
				Date endTime = new Date();
				updateConveLog(CommonCode.ViewNameMZJZJL, count, startTime, endTime);
				return;
			}
			System.out.println("after write:" + pageNum + ", time:"+ new Date());
			pi.setPageNum(++pageNum);
		}
		
		//如果有新的，更新记录。
		if(resultList.size() > 0) {
			DECTreatmentRecordsContent content = resultList.get(resultList.size() - 1);
			ViewAccessState newLast = new ViewAccessState(CommonCode.ViewNameMZJZJL, new java.sql.Timestamp(new java.util.Date().getTime()),
					"1", new java.sql.Timestamp(content.getSubmitDate().getTime()));
			updateState(newLast);
		}	
		Date endTime = new Date();
		updateConveLog(CommonCode.ViewNameMZJZJL, count, startTime, endTime);
		logger.info("门诊诊疗记录转存完毕。");
	}
	
	public void carryDECRegistration() {		

		//如果任务数量完成，退出。
		if(taskCurrent >= taskSize) {return;}
		int count = 0;
		Date startTime = new Date();
		//获取数据库操作类.
		DECRegistrationContentServiceImpl service = SpringBeanUtil.getBean("decRegistrationContentService");
		DECRegistrationContentMapper mapper= SpringBeanUtil.getApplicationContext().getBean(DECRegistrationContentMapper.class);
		
		//数据库list
		List<DECRegistrationContent> resultList = null;
		List<Original> originalList = new ArrayList();
		
		int pageNum = 1;
		
		//上次访问状态.
		ViewAccessState vas = lastAccess(CommonCode.ViewNameMZZLDJ);
		//获取第一页
		service.setMapper(mapper);
		Page<DECRegistrationContent> page = PageHelper.startPage(pageNum, pageSize);		
		System.out.println("find a page:" + pageNum + ", time:"+ new Date());
		resultList = service.findByPage(vas);
		PageInfo<DECRegistrationContent> pi = new PageInfo<DECRegistrationContent>(page);
		taskCurrent += resultList.size();
		System.out.println("after find a page:" + pageNum + ", time:"+ new Date());
		
		//转换第一页
		for(int i = 0; i < resultList.size(); i++) {
			DECRegistrationContent content = resultList.get(i);
			Original o = DECRegistration.getOriginal(content, sng.getNum());
			originalList.add(o);
		}
		
		//写入第一页
		System.out.println("before write:" + pageNum + ", time:"+ new Date());
		writeToOriginal(originalList);
		count += resultList.size();
		
		//判断任务完成量,如果完成，退出，如果未完成继续。
		if (taskCurrent >= taskSize) {
			//记录
			DECRegistrationContent contentLast = resultList.get(resultList.size() - 1);
			ViewAccessState newLast = new ViewAccessState(CommonCode.ViewNameMZZLDJ, new java.sql.Timestamp(new java.util.Date().getTime()),
					"1", new java.sql.Timestamp(contentLast.getSubmitDate().getTime()));
			updateState(newLast);
			//获取下一页，取出时间相同的。
			pi.setPageNum(++pageNum);
			service.setMapper(mapper);
			PageHelper.startPage(pageNum, pageSize);	
			resultList = service.findByPage(vas);
			originalList = new ArrayList();
			for(int i = 0; i < resultList.size(); i++) {
				DECRegistrationContent content = resultList.get(i);
				if(!content.getSubmitDate().equals(contentLast.getSubmitDate())) {
					break;
				}
				Original o = DECRegistration.getOriginal(content, sng.getNum());
				originalList.add(o);
			}
			if(originalList.size() > 0) {
				writeToOriginal(originalList);
				count += resultList.size();
			}
			Date endTime = new Date();
			updateConveLog(CommonCode.ViewNameMZZLDJ, count, startTime, endTime);
			return;
		}
		System.out.println("after write:" + pageNum + ", time:"+ new Date());
		
		//获取后续页
		long totalPages = pi.getPages();
		pi.setPageNum(++pageNum);
		while(pageNum <= totalPages) {
			originalList = new ArrayList();
			service.setMapper(mapper);
			
			System.out.println("find a page:" + pageNum + ", time:"+ new Date());
			PageHelper.startPage(pageNum, pageSize);	
			resultList = service.findByPage(vas);
			taskCurrent += resultList.size();
			System.out.println("after find a page:" + pageNum + ", time:"+ new Date());
			for(int i = 0; i < resultList.size(); i++) {
				DECRegistrationContent content = resultList.get(i);
				Original o = DECRegistration.getOriginal(content, sng.getNum());
				originalList.add(o);
			}
			System.out.println("before write:" + pageNum + ", time:"+ new Date());
			writeToOriginal(originalList);
			count += resultList.size();
			if (taskCurrent >= taskSize) {
				//记录
				DECRegistrationContent contentLast = resultList.get(resultList.size() - 1);
				ViewAccessState newLast = new ViewAccessState(CommonCode.ViewNameMZZLDJ, new java.sql.Timestamp(new java.util.Date().getTime()),
						"1", new java.sql.Timestamp(contentLast.getSubmitDate().getTime()));
				updateState(newLast);
				//获取下一页，取出时间相同的。
				pi.setPageNum(++pageNum);
				service.setMapper(mapper);
				PageHelper.startPage(pageNum, pageSize);	
				resultList = service.findByPage(vas);
				originalList = new ArrayList();
				for(int i = 0; i < resultList.size(); i++) {
					DECRegistrationContent content = resultList.get(i);
					if(!content.getSubmitDate().equals(contentLast.getSubmitDate())) {
						break;
					}
					Original o = DECRegistration.getOriginal(content, sng.getNum());
					originalList.add(o);
				}
				if(originalList.size() > 0) {
					writeToOriginal(originalList);
					count += resultList.size();
				}
				Date endTime = new Date();
				updateConveLog(CommonCode.ViewNameMZZLDJ, count, startTime, endTime);
				return;
			}
			System.out.println("after write:" + pageNum + ", time:"+ new Date());
			pi.setPageNum(++pageNum);
		}
		
		//如果有新的，更新记录。
		if(resultList.size() > 0) {
			DECRegistrationContent content = resultList.get(resultList.size() - 1);
			ViewAccessState newLast = new ViewAccessState(CommonCode.ViewNameMZZLDJ, new java.sql.Timestamp(new java.util.Date().getTime()),
					"1", new java.sql.Timestamp(content.getSubmitDate().getTime()));
			updateState(newLast);
		}	
		Date endTime = new Date();
		updateConveLog(CommonCode.ViewNameMZZLDJ, count, startTime, endTime);
		logger.info("门诊诊疗登记转存完毕。");
	}
	
	public void carryDECFareDetail() {
		//如果任务数量完成，退出。
		if(taskCurrent >= taskSize) {return;}
		int count = 0;
		Date startTime = new Date();
		//获取数据库操作类.
		DECFareDetailContentServiceImpl service = SpringBeanUtil.getBean("decFareDetailContentService");
		DECFareDetailContentMapper mapper= SpringBeanUtil.getApplicationContext().getBean(DECFareDetailContentMapper.class);
		service.setMapper(mapper);
		
		//数据库list
		List<DECFareDetailContent> resultList = null;
		List<Original> originalList = new ArrayList();
		
		int pageNum = 1;
		
		//上次访问状态.
		ViewAccessState vas = lastAccess(CommonCode.ViewNameMZSFMX);
		//获取第一页
		service.setMapper(mapper);
		Page<DECFareDetailContent> page = PageHelper.startPage(pageNum, pageSize);		
		System.out.println("find a page:" + pageNum + ", time:"+ new Date());
		resultList = service.findByPage(vas);
		PageInfo<DECFareDetailContent> pi = new PageInfo<DECFareDetailContent>(page);
		taskCurrent += resultList.size();
		System.out.println("after find a page:" + pageNum + ", time:"+ new Date());
		
		//转换第一页
		for(int i = 0; i < resultList.size(); i++) {
			DECFareDetailContent content = resultList.get(i);
			Original o = DECFareDetail.getOriginal(content, sng.getNum());
			originalList.add(o);
		}
		
		//写入第一页
		System.out.println("before write:" + pageNum + ", time:"+ new Date());
		writeToOriginal(originalList);
		count += resultList.size();
		
		//判断任务完成量,如果完成，退出，如果未完成继续。
		if (taskCurrent >= taskSize) {
			//记录
			DECFareDetailContent contentLast = resultList.get(resultList.size() - 1);
			ViewAccessState newLast = new ViewAccessState(CommonCode.ViewNameMZSFMX, new java.sql.Timestamp(new java.util.Date().getTime()),
					"1", new java.sql.Timestamp(contentLast.getSubmitDate().getTime()));
			updateState(newLast);
			//获取下一页，取出时间相同的。
			pi.setPageNum(++pageNum);
			service.setMapper(mapper);
			PageHelper.startPage(pageNum, pageSize);	
			resultList = service.findByPage(vas);
			originalList = new ArrayList();
			for(int i = 0; i < resultList.size(); i++) {
				DECFareDetailContent content = resultList.get(i);
				if(!content.getSubmitDate().equals(contentLast.getSubmitDate())) {
					break;
				}
				Original o = DECFareDetail.getOriginal(content, sng.getNum());
				originalList.add(o);
			}
			if(originalList.size() > 0) {
				writeToOriginal(originalList);
				count += resultList.size();
			}
			Date endTime = new Date();
			updateConveLog(CommonCode.ViewNameMZSFMX, count, startTime, endTime);
			return;
		}
		System.out.println("after write:" + pageNum + ", time:"+ new Date());
		
		//获取后续页
		long totalPages = pi.getPages();
		pi.setPageNum(++pageNum);
		while(pageNum <= totalPages) {
			originalList = new ArrayList();
			service.setMapper(mapper);
			
			System.out.println("find a page:" + pageNum + ", time:"+ new Date());
			PageHelper.startPage(pageNum, pageSize);	
			resultList = service.findByPage(vas);
			taskCurrent += resultList.size();
			System.out.println("after find a page:" + pageNum + ", time:"+ new Date());
			for(int i = 0; i < resultList.size(); i++) {
				DECFareDetailContent content = resultList.get(i);
				Original o = DECFareDetail.getOriginal(content, sng.getNum());
				originalList.add(o);
			}
			System.out.println("before write:" + pageNum + ", time:"+ new Date());
			writeToOriginal(originalList);
			count += resultList.size();
			if (taskCurrent >= taskSize) {
				//记录
				DECFareDetailContent contentLast = resultList.get(resultList.size() - 1);
				ViewAccessState newLast = new ViewAccessState(CommonCode.ViewNameMZSFMX, new java.sql.Timestamp(new java.util.Date().getTime()),
						"1", new java.sql.Timestamp(contentLast.getSubmitDate().getTime()));
				updateState(newLast);
				//获取下一页，取出时间相同的。
				pi.setPageNum(++pageNum);
				service.setMapper(mapper);
				PageHelper.startPage(pageNum, pageSize);	
				resultList = service.findByPage(vas);
				originalList = new ArrayList();
				for(int i = 0; i < resultList.size(); i++) {
					DECFareDetailContent content = resultList.get(i);
					if(!content.getSubmitDate().equals(contentLast.getSubmitDate())) {
						break;
					}
					Original o = DECFareDetail.getOriginal(content, sng.getNum());
					originalList.add(o);
				}
				if(originalList.size() > 0) {
					writeToOriginal(originalList);
					count += resultList.size();
				}
				Date endTime = new Date();
				updateConveLog(CommonCode.ViewNameMZSFMX, count, startTime, endTime);
				return;
			}
			System.out.println("after write:" + pageNum + ", time:"+ new Date());
			pi.setPageNum(++pageNum);
		}
		
		//如果有新的，更新记录。
		if(resultList.size() > 0) {
			DECFareDetailContent content = resultList.get(resultList.size() - 1);
			ViewAccessState newLast = new ViewAccessState(CommonCode.ViewNameMZSFMX, new java.sql.Timestamp(new java.util.Date().getTime()),
					"1", new java.sql.Timestamp(content.getSubmitDate().getTime()));
			updateState(newLast);
		}
		Date endTime = new Date();
		updateConveLog(CommonCode.ViewNameMZSFMX, count, startTime, endTime);
		logger.info("门诊费用明细转存完毕。");
	}
	
	
	public void carryDECFareBalance() {
		//如果任务数量完成，退出。
		if(taskCurrent >= taskSize) {return;}
		int count = 0;
		Date startTime = new Date();
		//获取数据库操作类.
		DECFareBalanceContentServiceImpl service = SpringBeanUtil.getBean("decFareBalanceContentService");
		DECFareBalanceContentMapper mapper= SpringBeanUtil.getApplicationContext().getBean(DECFareBalanceContentMapper.class);
		
		
		//数据库list
		List<DECFareBalanceContent> resultList = null;
		List<Original> originalList = new ArrayList();
		
		int pageNum = 1;
		
		//上次访问状态.
		ViewAccessState vas = lastAccess(CommonCode.ViewNameMZFYJS);
		//获取第一页
		service.setMapper(mapper);
		Page<DECFareBalanceContent> page = PageHelper.startPage(pageNum, pageSize);		
		System.out.println("find a page:" + pageNum + ", time:"+ new Date());
		resultList = service.findByPage(vas);
		PageInfo<DECFareBalanceContent> pi = new PageInfo<DECFareBalanceContent>(page);
		taskCurrent += resultList.size();
		System.out.println("after find a page:" + pageNum + ", time:"+ new Date());
		
		//转换第一页
		for(int i = 0; i < resultList.size(); i++) {
			DECFareBalanceContent content = resultList.get(i);
			Original o = DECFareBalance.getOriginal(content, sng.getNum());
			originalList.add(o);
		}
		
		//写入第一页
		System.out.println("before write:" + pageNum + ", time:"+ new Date());
		writeToOriginal(originalList);
		count += resultList.size();
		
		//判断任务完成量,如果完成，退出，如果未完成继续。
		if (taskCurrent >= taskSize) {
			//记录
			DECFareBalanceContent contentLast = resultList.get(resultList.size() - 1);
			ViewAccessState newLast = new ViewAccessState(CommonCode.ViewNameMZFYJS, new java.sql.Timestamp(new java.util.Date().getTime()),
					"1", new java.sql.Timestamp(contentLast.getSubmitDate().getTime()));
			updateState(newLast);
			//获取下一页，取出时间相同的。
			pi.setPageNum(++pageNum);
			service.setMapper(mapper);
			PageHelper.startPage(pageNum, pageSize);	
			resultList = service.findByPage(vas);
			originalList = new ArrayList();
			for(int i = 0; i < resultList.size(); i++) {
				DECFareBalanceContent content = resultList.get(i);
				if(!content.getSubmitDate().equals(contentLast.getSubmitDate())) {
					break;
				}
				Original o = DECFareBalance.getOriginal(content, sng.getNum());
				originalList.add(o);
			}
			if(originalList.size() > 0) {
				writeToOriginal(originalList);
				count += resultList.size();
			}
			Date endTime = new Date();
			updateConveLog(CommonCode.ViewNameMZFYJS, count, startTime, endTime);
			return;
		}
		System.out.println("after write:" + pageNum + ", time:"+ new Date());
		
		//获取后续页
		long totalPages = pi.getPages();
		pi.setPageNum(++pageNum);
		while(pageNum <= totalPages) {
			originalList = new ArrayList();
			service.setMapper(mapper);
			
			System.out.println("find a page:" + pageNum + ", time:"+ new Date());
			PageHelper.startPage(pageNum, pageSize);	
			resultList = service.findByPage(vas);
			taskCurrent += resultList.size();
			System.out.println("after find a page:" + pageNum + ", time:"+ new Date());
			for(int i = 0; i < resultList.size(); i++) {
				DECFareBalanceContent content = resultList.get(i);
				Original o = DECFareBalance.getOriginal(content, sng.getNum());
				originalList.add(o);
			}
			System.out.println("before write:" + pageNum + ", time:"+ new Date());
			writeToOriginal(originalList);
			count += resultList.size();
			if (taskCurrent >= taskSize) {
				//记录
				DECFareBalanceContent contentLast = resultList.get(resultList.size() - 1);
				ViewAccessState newLast = new ViewAccessState(CommonCode.ViewNameMZFYJS, new java.sql.Timestamp(new java.util.Date().getTime()),
						"1", new java.sql.Timestamp(contentLast.getSubmitDate().getTime()));
				updateState(newLast);
				//获取下一页，取出时间相同的。
				pi.setPageNum(++pageNum);
				service.setMapper(mapper);
				PageHelper.startPage(pageNum, pageSize);	
				resultList = service.findByPage(vas);
				originalList = new ArrayList();
				for(int i = 0; i < resultList.size(); i++) {
					DECFareBalanceContent content = resultList.get(i);
					if(!content.getSubmitDate().equals(contentLast.getSubmitDate())) {
						break;
					}
					Original o = DECFareBalance.getOriginal(content, sng.getNum());
					originalList.add(o);
				}
				if(originalList.size() > 0) {
					writeToOriginal(originalList);
					count += resultList.size();
				}
				Date endTime = new Date();
				updateConveLog(CommonCode.ViewNameMZFYJS, count, startTime, endTime);
				return;
			}
			System.out.println("after write:" + pageNum + ", time:"+ new Date());
			pi.setPageNum(++pageNum);
		}
		
		//如果有新的，更新记录。
		if(resultList.size() > 0) {
			DECFareBalanceContent content = resultList.get(resultList.size() - 1);
			ViewAccessState newLast = new ViewAccessState(CommonCode.ViewNameMZFYJS, new java.sql.Timestamp(new java.util.Date().getTime()),
					"1", new java.sql.Timestamp(content.getSubmitDate().getTime()));
			updateState(newLast);
		}
		Date endTime = new Date();
		updateConveLog(CommonCode.ViewNameMZFYJS, count, startTime, endTime);
		logger.info("门诊费用结算转存完毕。");
	}
	
	public void carryDECDrugAdvice() {
		//如果任务数量完成，退出。
		if(taskCurrent >= taskSize) {return;}
		int count = 0;
		Date startTime = new Date();
		//获取数据库操作类.
		DECDrugAdviceContentServiceImpl service = SpringBeanUtil.getBean("decDrugAdviceContentService");
		DECDrugAdviceContentMapper mapper= SpringBeanUtil.getApplicationContext().getBean(DECDrugAdviceContentMapper.class);
		
		
		//数据库list
		List<DECDrugAdviceContent> resultList = null;
		List<Original> originalList = new ArrayList();
		
		int pageNum = 1;
		
		//上次访问状态.
		ViewAccessState vas = lastAccess(CommonCode.ViewNameMZCFZB);
		//获取第一页
		service.setMapper(mapper);
		Page<DECDrugAdviceContent> page = PageHelper.startPage(pageNum, pageSize);		
		System.out.println("find a page:" + pageNum + ", time:"+ new Date());
		resultList = service.findByPage(vas);
		PageInfo<DECDrugAdviceContent> pi = new PageInfo<DECDrugAdviceContent>(page);
		taskCurrent += resultList.size();
		System.out.println("after find a page:" + pageNum + ", time:"+ new Date());
		
		//转换第一页
		for(int i = 0; i < resultList.size(); i++) {
			DECDrugAdviceContent content = resultList.get(i);
			Original o = DECDrugAdvice.getOriginal(content, sng.getNum());
			originalList.add(o);
		}
		
		//写入第一页
		System.out.println("before write:" + pageNum + ", time:"+ new Date());
		writeToOriginal(originalList);
		count += resultList.size();
		
		//判断任务完成量,如果完成，退出，如果未完成继续。
		if (taskCurrent >= taskSize) {
			//记录
			DECDrugAdviceContent contentLast = resultList.get(resultList.size() - 1);
			ViewAccessState newLast = new ViewAccessState(CommonCode.ViewNameMZCFZB, new java.sql.Timestamp(new java.util.Date().getTime()),
					"1", new java.sql.Timestamp(contentLast.getSubmitDate().getTime()));
			updateState(newLast);
			//获取下一页，取出时间相同的。
			pi.setPageNum(++pageNum);
			service.setMapper(mapper);
			PageHelper.startPage(pageNum, pageSize);	
			resultList = service.findByPage(vas);
			originalList = new ArrayList();
			for(int i = 0; i < resultList.size(); i++) {
				DECDrugAdviceContent content = resultList.get(i);
				if(!content.getSubmitDate().equals(contentLast.getSubmitDate())) {
					break;
				}
				Original o = DECDrugAdvice.getOriginal(content, sng.getNum());
				originalList.add(o);
			}
			if(originalList.size() > 0) {
				writeToOriginal(originalList);
				count += resultList.size();
			}
			Date endTime = new Date();
			updateConveLog(CommonCode.ViewNameMZCFZB, count, startTime, endTime);
			return;
		}
		System.out.println("after write:" + pageNum + ", time:"+ new Date());
		
		//获取后续页
		long totalPages = pi.getPages();
		pi.setPageNum(++pageNum);
		while(pageNum <= totalPages) {
			originalList = new ArrayList();
			service.setMapper(mapper);
			
			System.out.println("find a page:" + pageNum + ", time:"+ new Date());
			PageHelper.startPage(pageNum, pageSize);	
			resultList = service.findByPage(vas);
			taskCurrent += resultList.size();
			System.out.println("after find a page:" + pageNum + ", time:"+ new Date());
			for(int i = 0; i < resultList.size(); i++) {
				DECDrugAdviceContent content = resultList.get(i);
				Original o = DECDrugAdvice.getOriginal(content, sng.getNum());
				originalList.add(o);
			}
			System.out.println("before write:" + pageNum + ", time:"+ new Date());
			writeToOriginal(originalList);
			count += resultList.size();
			if (taskCurrent >= taskSize) {
				//记录
				DECDrugAdviceContent contentLast = resultList.get(resultList.size() - 1);
				ViewAccessState newLast = new ViewAccessState(CommonCode.ViewNameMZCFZB, new java.sql.Timestamp(new java.util.Date().getTime()),
						"1", new java.sql.Timestamp(contentLast.getSubmitDate().getTime()));
				updateState(newLast);
				//获取下一页，取出时间相同的。
				pi.setPageNum(++pageNum);
				service.setMapper(mapper);
				PageHelper.startPage(pageNum, pageSize);	
				resultList = service.findByPage(vas);
				originalList = new ArrayList();
				for(int i = 0; i < resultList.size(); i++) {
					DECDrugAdviceContent content = resultList.get(i);
					if(!content.getSubmitDate().equals(contentLast.getSubmitDate())) {
						break;
					}
					Original o = DECDrugAdvice.getOriginal(content, sng.getNum());
					originalList.add(o);
				}
				if(originalList.size() > 0) {
					writeToOriginal(originalList);
					count += resultList.size();
				}
				Date endTime = new Date();
				updateConveLog(CommonCode.ViewNameMZCFZB, count, startTime, endTime);
				return;
			}
			System.out.println("after write:" + pageNum + ", time:"+ new Date());
			pi.setPageNum(++pageNum);
		}
		
		//如果有新的，更新记录。
		if(resultList.size() > 0) {
			DECDrugAdviceContent content = resultList.get(resultList.size() - 1);
			ViewAccessState newLast = new ViewAccessState(CommonCode.ViewNameMZCFZB, new java.sql.Timestamp(new java.util.Date().getTime()),
					"1", new java.sql.Timestamp(content.getSubmitDate().getTime()));
			updateState(newLast);
		}
		Date endTime = new Date();
		updateConveLog(CommonCode.ViewNameMZCFZB, count, startTime, endTime);
		logger.info("门诊医嘱主表转存完毕。");
	}
	public void carryDECDrugAdviceDetail() {
		//如果任务数量完成，退出。
		if(taskCurrent >= taskSize) {return;}
		int count = 0;
		Date startTime = new Date();
		//获取数据库操作类.
		DECDrugAdviceDetailContentServiceImpl service = SpringBeanUtil.getBean("decDrugAdviceDetailContentService");
		DECDrugAdviceDetailContentMapper mapper= SpringBeanUtil.getApplicationContext().getBean(DECDrugAdviceDetailContentMapper.class);
		
		
		//数据库list
		List<DECDrugAdviceDetailContent> resultList = null;
		List<Original> originalList = new ArrayList();
		
		int pageNum = 1;
		
		//上次访问状态.
		ViewAccessState vas = lastAccess(CommonCode.ViewNameMZCFMX);
		//获取第一页
		service.setMapper(mapper);
		Page<DECDrugAdviceDetailContent> page = PageHelper.startPage(pageNum, pageSize);		
		System.out.println("find a page:" + pageNum + ", time:"+ new Date());
		resultList = service.findByPage(vas);
		PageInfo<DECDrugAdviceDetailContent> pi = new PageInfo<DECDrugAdviceDetailContent>(page);
		taskCurrent += resultList.size();
		System.out.println("after find a page:" + pageNum + ", time:"+ new Date());
		
		//转换第一页
		for(int i = 0; i < resultList.size(); i++) {
			DECDrugAdviceDetailContent content = resultList.get(i);
			Original o = DECDrugAdviceDetail.getOriginal(content, sng.getNum());
			originalList.add(o);
		}
		
		//写入第一页
		System.out.println("before write:" + pageNum + ", time:"+ new Date());
		writeToOriginal(originalList);
		count += resultList.size();
		
		//判断任务完成量,如果完成，退出，如果未完成继续。
		if (taskCurrent >= taskSize) {
			//记录
			DECDrugAdviceDetailContent contentLast = resultList.get(resultList.size() - 1);
			ViewAccessState newLast = new ViewAccessState(CommonCode.ViewNameMZCFMX, new java.sql.Timestamp(new java.util.Date().getTime()),
					"1", new java.sql.Timestamp(contentLast.getSubmitDate().getTime()));
			updateState(newLast);
			//获取下一页，取出时间相同的。
			pi.setPageNum(++pageNum);
			service.setMapper(mapper);
			PageHelper.startPage(pageNum, pageSize);	
			resultList = service.findByPage(vas);
			originalList = new ArrayList();
			for(int i = 0; i < resultList.size(); i++) {
				DECDrugAdviceDetailContent content = resultList.get(i);
				if(!content.getSubmitDate().equals(contentLast.getSubmitDate())) {
					break;
				}
				Original o = DECDrugAdviceDetail.getOriginal(content, sng.getNum());
				originalList.add(o);
			}
			if(originalList.size() > 0) {
				writeToOriginal(originalList);
				count += resultList.size();
			}
			Date endTime = new Date();
			updateConveLog(CommonCode.ViewNameMZCFMX, count, startTime, endTime);
			return;
		}
		System.out.println("after write:" + pageNum + ", time:"+ new Date());
		
		//获取后续页
		long totalPages = pi.getPages();
		pi.setPageNum(++pageNum);
		while(pageNum <= totalPages) {
			originalList = new ArrayList();
			service.setMapper(mapper);
			
			System.out.println("find a page:" + pageNum + ", time:"+ new Date());
			PageHelper.startPage(pageNum, pageSize);	
			resultList = service.findByPage(vas);
			taskCurrent += resultList.size();
			System.out.println("after find a page:" + pageNum + ", time:"+ new Date());
			for(int i = 0; i < resultList.size(); i++) {
				DECDrugAdviceDetailContent content = resultList.get(i);
				Original o = DECDrugAdviceDetail.getOriginal(content, sng.getNum());
				originalList.add(o);
			}
			System.out.println("before write:" + pageNum + ", time:"+ new Date());
			writeToOriginal(originalList);
			count += resultList.size();
			if (taskCurrent >= taskSize) {
				//记录
				DECDrugAdviceDetailContent contentLast = resultList.get(resultList.size() - 1);
				ViewAccessState newLast = new ViewAccessState(CommonCode.ViewNameMZCFMX, new java.sql.Timestamp(new java.util.Date().getTime()),
						"1", new java.sql.Timestamp(contentLast.getSubmitDate().getTime()));
				updateState(newLast);
				//获取下一页，取出时间相同的。
				pi.setPageNum(++pageNum);
				service.setMapper(mapper);
				PageHelper.startPage(pageNum, pageSize);	
				resultList = service.findByPage(vas);
				originalList = new ArrayList();
				for(int i = 0; i < resultList.size(); i++) {
					DECDrugAdviceDetailContent content = resultList.get(i);
					if(!content.getSubmitDate().equals(contentLast.getSubmitDate())) {
						break;
					}
					Original o = DECDrugAdviceDetail.getOriginal(content, sng.getNum());
					originalList.add(o);
				}
				if(originalList.size() > 0) {
					System.out.println("same time size" + originalList.size());
					writeToOriginal(originalList);
					count += resultList.size();
				}
				Date endTime = new Date();
				updateConveLog(CommonCode.ViewNameMZCFMX, count, startTime, endTime);
				return;
			}
			System.out.println("after write:" + pageNum + ", time:"+ new Date());
			pi.setPageNum(++pageNum);
		}
		
		//如果有新的，更新记录。
		if(resultList.size() > 0) {
			DECDrugAdviceDetailContent content = resultList.get(resultList.size() - 1);
			ViewAccessState newLast = new ViewAccessState(CommonCode.ViewNameMZCFMX, new java.sql.Timestamp(new java.util.Date().getTime()),
					"1", new java.sql.Timestamp(content.getSubmitDate().getTime()));
			updateState(newLast);
		}
		Date endTime = new Date();
		updateConveLog(CommonCode.ViewNameMZCFMX, count, startTime, endTime);
		logger.info("门诊医嘱明细转存完毕。");
	}
		
	private void writeToOriginal(List<Original> ol) {
		OriginalServiceImpl service = SpringBeanUtil.getBean("originalService");
		OriginalMapper mapper = SpringBeanUtil.getApplicationContext().getBean(OriginalMapper.class);
		service.setMapper(mapper);
		service.insertBatch(ol);
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
	
	private void updateConveLog(String viewName, int count, Date startTime, Date endTime) {
		ConveLogServiceImpl service = SpringBeanUtil.getBean("conveLogService");
		ConveLogMapper mapper = SpringBeanUtil.getApplicationContext().getBean(ConveLogMapper.class);
		service.setMapper(mapper);
		ConveLog cl = new ConveLog();
		cl.setViewName(viewName);
		cl.setIsAtuo(isAuto + "");
		cl.setCount(count + "");
		cl.setStartTime(new Timestamp(startTime.getTime()));
		cl.setEndTime(new Timestamp(endTime.getTime()));
		
		service.Log(cl);
	}
	
	private void updateFailLog(String viewName, Date failTime, String failMsg) {
		FailLogServiceImpl service = SpringBeanUtil.getBean("failLogService");
		FailLogMapper mapper = SpringBeanUtil.getApplicationContext().getBean(FailLogMapper.class);
		service.setMapper(mapper);
		FailLog fl = new FailLog();
		fl.setFailMsg(failMsg);
		fl.setViewName(viewName);
		fl.setFailTime(new Timestamp(failTime.getTime()));
		service.Log(fl);
	}
}
