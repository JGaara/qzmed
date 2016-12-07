package com.zehin.vpaas.beans;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "DECRegistration")
@XmlType(propOrder = { "HospitalCode", "HospitalLicense", "content" })
public class DECRegistration {
	private String HospitalCode;
	private String HospitalLicense;
	private DECRegistrationContent content;
	public String getHospitalCode() {
		return HospitalCode;
	}
	public void setHospitalCode(String hospitalCode) {
		HospitalCode = hospitalCode;
	}
	public String getHospitalLicense() {
		return HospitalLicense;
	}
	public void setHospitalLicense(String hospitalLicense) {
		HospitalLicense = hospitalLicense;
	}
	public DECRegistrationContent getContent() {
		return content;
	}
	public void setContent(DECRegistrationContent content) {
		this.content = content;
	}
}
