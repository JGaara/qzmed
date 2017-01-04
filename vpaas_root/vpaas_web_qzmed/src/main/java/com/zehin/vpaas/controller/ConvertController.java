package com.zehin.vpaas.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zehin.vpaas.beans.*;
import com.zehin.vpaas.common.util.SpringBeanUtil;
import com.zehin.vpaas.mapper.*;
import com.zehin.vpaas.service.impl.*;

@Controller
@RequestMapping(value = "/")
public class ConvertController {

	@Resource
	private ConveLogServiceImpl conService = SpringBeanUtil.getBean("conveLogService");
	private Pager pager = null;

	@RequestMapping("/convert")
	public String convert(Model model, HttpServletRequest request, HttpServletResponse response) {
		// .out.println("convert");
		// .out.println("convert");
		ConveLogMapper clm = (ConveLogMapper) SpringBeanUtil.getApplicationContext().getBean(ConveLogMapper.class);
		conService.setMapper(clm);
		List<ConveLog> clList = conService.findAllConvert(new ConveLog());
		// List<ConveLog> clList = conService.findConvert(new ConveLog());
		//System.out.println(clList.size() + "curpage:" + request.getParameter("c"));
		List<ConveLogBody> list = new ArrayList<ConveLogBody>();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		pager = PagerHelper.getPager(request.getParameter("m"), request.getParameter("c"), clList.size());
		//System.out.println("s:" + pager.getStartRow() + ".e:" + pager.getEndRow());
		clList = conService.findConvert(pager);
		int i = 0;
		for (ConveLog cl : clList) {
			ConveLogBody clb = new ConveLogBody();
			clb.setStartTime(formatter.format(cl.getStartTime()));
			clb.setEndTime(formatter.format(cl.getEndTime()));
			clb.setCount(cl.getCount());
			clb.setViewName(cl.getViewName());
			clb.setIsAuto(cl.getIsAtuo());
			list.add(clb);
			i++;
			if(i>200){
				break;
			}
		}
		model.addAttribute("conList", list);
		model.addAttribute("pager", pager);
		return "convert";
	}

	@RequestMapping("/hand")
	public String hand(Model model, HttpServletRequest request, HttpServletResponse response) {
		// System.out.println("hand");
		ConveLogMapper clm = (ConveLogMapper) SpringBeanUtil.getApplicationContext().getBean(ConveLogMapper.class);
		conService.setMapper(clm);
		List<ConveLog> clList = conService.findAllHand(new ConveLog());
		// List<ConveLog> clList = conService.findConvert(new ConveLog());
		//System.out.println(clList.size() + "curpage:" + request.getParameter("c"));
		List<ConveLogBody> list = new ArrayList<ConveLogBody>();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		pager = PagerHelper.getPager(request.getParameter("m"), request.getParameter("c"), clList.size());
		//System.out.println("s:" + pager.getStartRow() + ".e:" + pager.getEndRow());
		clList = conService.findHand(pager);
		int i = 0;
		for (ConveLog cl : clList) {
			ConveLogBody clb = new ConveLogBody();
			clb.setStartTime(formatter.format(cl.getStartTime()));
			clb.setEndTime(formatter.format(cl.getEndTime()));
			clb.setCount(cl.getCount());
			clb.setViewName(cl.getViewName());
			clb.setIsAuto(cl.getIsAtuo());
			list.add(clb);
			i++;
			if(i>200){
				break;
			}
		}
		model.addAttribute("hconList", list);
		model.addAttribute("pager", pager);
		return "hand";
	}
	
}
