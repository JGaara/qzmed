package com.zehin.vpaas.service;

import com.zehin.vpaas.beans.User;

public interface LoginService {
	User login(String name,String pwd);
}
