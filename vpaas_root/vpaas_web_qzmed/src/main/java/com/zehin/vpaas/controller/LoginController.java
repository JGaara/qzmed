package com.zehin.vpaas.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zehin.vpaas.beans.User;
import com.zehin.vpaas.common.util.SpringBeanUtil;
import com.zehin.vpaas.mapper.*;
import com.zehin.vpaas.service.impl.LoginServiceImpl;

@Controller

public class LoginController {

	@Resource
	private LoginServiceImpl loginService = SpringBeanUtil.getBean("loginService");

	@RequestMapping("/login")
	public String login(Model model, HttpServletRequest request, HttpServletResponse response){
		//System.out.println("login/login");
		return "login/login";
	}
	
	@ResponseBody
	@RequestMapping("/cin")
	public void main(User user, Model model, HttpServletRequest request, HttpServletResponse response) {
		String name = request.getParameter("username");
		String pwd = request.getParameter("password");
		//System.out.println(name);
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		UserMapper um = (UserMapper)SpringBeanUtil.getApplicationContext().getBean(UserMapper.class);
		loginService.setMapper(um);
		User u = null; 
		try{
			u =	loginService.selectById(name);
		}catch(Exception e){
			System.out.println(e.getMessage());
			out.print(1);
			return;
		}
		if(u == null){
			//用户不存在
			out.print(1);

		}else if(!u.getPwd().equals(pwd)){
			//登陆失败
			out.print(1);
		}else {
			//登陆成功
			model.addAttribute("user", u);
			out.print(0);
		}

		/*model.addAttribute("user", user);
		model.addAttribute("xxx", "xxxxxx");*/
		
	}
}
