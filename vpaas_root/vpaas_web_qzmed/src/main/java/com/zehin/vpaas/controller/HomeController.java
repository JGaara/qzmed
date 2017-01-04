package com.zehin.vpaas.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zehin.vpaas.beans.*;
import com.zehin.vpaas.common.util.SpringBeanUtil;
import com.zehin.vpaas.generic.PropertiesTask;
import com.zehin.vpaas.mapper.*;
import com.zehin.vpaas.service.impl.*;

@Controller
public class HomeController {
	
	@Resource
	private ConveLogServiceImpl conService = SpringBeanUtil.getBean("conveLogService");
	private FailLogServiceImpl falService = SpringBeanUtil.getBean("failLogService");
	private ErrorsServiceImpl errorService = SpringBeanUtil.getBean("errorsService");
	
	@RequestMapping("/home")
	public String home(Model model, HttpServletRequest request, HttpServletResponse response){
		//System.out.println("home");
		ConveLogMapper clm = (ConveLogMapper)SpringBeanUtil.getApplicationContext().getBean(ConveLogMapper.class);
		conService.setMapper(clm);
		List<ConveLog> clList = conService.findNew(new ConveLog());
		//List<ConveLog> clList = conService
		//System.out.println(clList.size());
		List<ConveLogBody> list = new ArrayList<ConveLogBody>();
		SimpleDateFormat formatter = new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss");
		int count = 0;
		if(clList.size() != 0){
			for(ConveLog cl : clList){
				ConveLogBody clb = new ConveLogBody();
				clb.setStartTime(formatter.format(cl.getStartTime()));
				clb.setEndTime(formatter.format(cl.getEndTime()));
				clb.setCount(cl.getCount());
				clb.setViewName(cl.getViewName());
				clb.setIsAuto(cl.getIsAtuo());
				list.add(clb);
				count += Integer.parseInt(cl.getCount());
			}
			model.addAttribute("startTime", list.get(list.size()-1).getStartTime());
			model.addAttribute("endTime", list.get(0).getEndTime());
		}else{
			model.addAttribute("startTime", null);
			model.addAttribute("endTime", null);
		}
		model.addAttribute("count", count);
		model.addAttribute("hList", list);
		/*
		 * 计算距离下次转换的时间
		 * crontab=0_0_0/2_*_*_?
		 * crontab=0_0_23_*_*_?
		 * */
		Properties p = ((PropertiesTask)SpringBeanUtil.getBean("propertiesTask")).getPro();
		String nextTime = "";
		String sysInfo = "";
		String[] str = p.getProperty("crontab").split("_");
		//上次开始的小时
		String sTime = "";
		if(list.size() != 0){
			sTime = list.get(0).getStartTime().split(" ")[1].split(":")[0];
		}
		if(str[2].length() > 2){
			//间隔执行的时间
			String s = str[2].split("/")[1];
			int hour = (new Date()).getHours();
			int lh = Integer.parseInt(s);
			int sh = 0;
			if(!sTime.equals("")){
			 sh = Integer.parseInt(sTime);
			 }
			nextTime = Integer.toString(lh-(hour-sh));
			sysInfo = "每间隔" + s + "小时进行一次数据转换！";
		}else{
			int hour = (new Date()).getHours();
			nextTime = Integer.toString(Math.abs(hour - Integer.parseInt(str[2])));
			sysInfo = "每天" + str[2] + "时"+str[1]+"分进行数据转换！";
		}
		model.addAttribute("nextTime", nextTime);
		model.addAttribute("info", sysInfo);
		/*
		 * 获取错误记录数
		 * */
		ErrorsMapper drcm = (ErrorsMapper)SpringBeanUtil.getApplicationContext().getBean(ErrorsMapper.class);
		errorService.setMapper(drcm);
		List<Errors> erList = errorService.findAll(new Errors());
		model.addAttribute("failCount", erList.size());
		return "home";
	}
}