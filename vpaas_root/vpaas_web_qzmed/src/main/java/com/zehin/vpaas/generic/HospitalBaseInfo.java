package com.zehin.vpaas.generic;

import java.util.Properties;

import com.zehin.vpaas.common.util.SpringBeanUtil;

public class HospitalBaseInfo {
	private String hosName;
	private String hosCode;
	private String hosAreaCode;
	private String hosLicense;
	private String domainCode;
	private String domainName;
	
	public HospitalBaseInfo() {
		Properties p = (Properties)SpringBeanUtil.getBean("propertyConfigurer");
		this.hosName = p.getProperty("hospital.name");
		this.hosCode = p.getProperty("hospital.code");
		this.hosAreaCode = p.getProperty("hospital.areaCode");
		this.hosLicense = p.getProperty("hospital.license");
		this.domainCode = p.getProperty("hospital.domainCode");
		this.domainName = p.getProperty("hospital.domainName");
	}
	public String getHosName() {
		return hosName;
	}
	public void setHosName(String hosName) {
		this.hosName = hosName;
	}
	public String getHosCode() {
		return hosCode;
	}
	public void setHosCode(String hosCode) {
		this.hosCode = hosCode;
	}
	public String getHosLicense() {
		return hosLicense;
	}
	public void setHosLicense(String hosLicense) {
		this.hosLicense = hosLicense;
	}
	public String getHosAreaCode() {
		return hosAreaCode;
	}
	public void setHosAreaCode(String hosAreaCode) {
		this.hosAreaCode = hosAreaCode;
	}
	public String getDomainCode() {
		return domainCode;
	}
	public void setDomainCode(String domainCode) {
		this.domainCode = domainCode;
	}
	public String getDomainName() {
		return domainName;
	}
	public void setDomainName(String domainName) {
		this.domainName = domainName;
	}
	
	
}
