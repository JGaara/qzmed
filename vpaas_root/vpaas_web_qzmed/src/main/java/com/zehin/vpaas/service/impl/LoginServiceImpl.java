package com.zehin.vpaas.service.impl;

import org.springframework.stereotype.Service;

import com.zehin.vpaas.beans.User;
import com.zehin.vpaas.generic.BaseServiceImpl;
import com.zehin.vpaas.generic.CustomerContextHolder;
import com.zehin.vpaas.mapper.*;

@Service("loginService")
public class LoginServiceImpl extends BaseServiceImpl<UserMapper, User, String> {
	public void setMapper(UserMapper mapper) {
		CustomerContextHolder.setCustomerType(CustomerContextHolder.DATA_SOURCE_ORACLE);
		super.mapper = mapper;
	}

}
