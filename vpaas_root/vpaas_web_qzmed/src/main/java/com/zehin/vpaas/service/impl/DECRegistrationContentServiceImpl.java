package com.zehin.vpaas.service.impl;

import org.springframework.stereotype.Service;

import com.zehin.vpaas.beans.DECRegistrationContent;
import com.zehin.vpaas.generic.BaseServiceImpl;
import com.zehin.vpaas.mapper.DECRegistrationContentMapper;
import com.zehin.vpaas.service.DECRegistrationContentService;

@Service("decRegistrationContentService")
public class DECRegistrationContentServiceImpl  extends BaseServiceImpl<DECRegistrationContentMapper, DECRegistrationContent, String> implements DECRegistrationContentService {
	
}
