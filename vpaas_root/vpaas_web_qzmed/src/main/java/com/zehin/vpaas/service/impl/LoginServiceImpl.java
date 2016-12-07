package com.zehin.vpaas.service.impl;

import org.springframework.stereotype.Service;

import com.zehin.vpaas.beans.User;
import com.zehin.vpaas.service.LoginService;

@Service("loginService")
public class LoginServiceImpl implements LoginService {

	@Override
	public User login(String name, String pwd) {
		User user = new User();
		user.setName(name+"2");
		user.setPwd(pwd);
		return user;
	}

}
