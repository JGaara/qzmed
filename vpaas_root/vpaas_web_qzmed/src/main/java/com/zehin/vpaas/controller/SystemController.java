package com.zehin.vpaas.controller;

import java.util.Properties;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zehin.vpaas.common.util.SpringBeanUtil;
import com.zehin.vpaas.generic.*;

@Controller
@RequestMapping(value = "/")
public class SystemController {
	
	@Resource
	private DynamicScheduler dyService = SpringBeanUtil.getBean("dynamicScheduler");
	
	@RequestMapping("/system")
	public String system(Model model, HttpServletRequest request, HttpServletResponse response){
		//System.out.println("system");
		return "system";
	}
	
	@ResponseBody
	@RequestMapping("/systemSave")
	public String systemSave(Model model, HttpServletRequest request, HttpServletResponse response){
		String hour = request.getParameter("hour");
		String min = request.getParameter("min");
		String inte = request.getParameter("inte");
		
		String cronb = "";
		String cron = "";
		if(!inte.equals("")){
			cronb = "0_0_0/" + inte.trim() + "_*_*_?";
			cron = "0 0 0/" + inte.trim() + " * * ?";
		}else if(!hour.equals("")){
			if(min.equals("")){
				min = "0";
			}
			cronb = "0_" + min.trim() +"_" + hour.trim() + "_*_*_?";
			cron = "0 " + min.trim() +" " + hour.trim() + " * * ?";
		}
		PropertiesTask p = (PropertiesTask)SpringBeanUtil.getBean("propertiesTask");
		p.setPro("crontab", cronb);
		//System.out.println("systemSave" + cronb);
		//
		dyService.setCron(cron);
		
		return "success";
	}
}
