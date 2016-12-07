package com.zehin.vpaas.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zehin.vpaas.beans.User;
import com.zehin.vpaas.service.LoginService;

@Controller
@RequestMapping(value = "/")
public class LoginController {

	@Resource
	private LoginService loginService;

	@RequestMapping("/login")
	public String main(Model model, HttpServletRequest request, HttpServletResponse response) {

		User userTest = loginService.login("tom","123");

		model.addAttribute("user", userTest);
		model.addAttribute("xxx", "xxxxxx");
		return "login/index";
	}
}
