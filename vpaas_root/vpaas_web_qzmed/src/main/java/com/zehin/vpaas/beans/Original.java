package com.zehin.vpaas.beans;

import java.sql.Date;
import java.sql.Timestamp;

public class Original {
	private String recordId;
	private String serviceType;
	private String idCard;
	private String businessTypeNo;
	private String license;
	private String areaCode;
	private String orgCode;
	private String orgName;
	private String domainCode;
	private String domainName;
	private Timestamp createDate;
	private Timestamp updateDate;
	private String handleMark;
	private byte[] bodyXml;
	public String getRecordId() {
		return recordId;
	}
	public void setRecordId(String recordId) {
		this.recordId = recordId;
	}
	public String getServiceType() {
		return serviceType;
	}
	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}
	public String getIdCard() {
		return idCard;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	public String getBusinessTypeNo() {
		return businessTypeNo;
	}
	public void setBusinessTypeNo(String businessTypeNo) {
		this.businessTypeNo = businessTypeNo;
	}
	public String getLicense() {
		return license;
	}
	public void setLicense(String license) {
		this.license = license;
	}
	public String getAreaCode() {
		return areaCode;
	}
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}
	public String getOrgCode() {
		return orgCode;
	}
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
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
	public Timestamp getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}
	public Timestamp getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Timestamp updateDate) {
		this.updateDate = updateDate;
	}
	public String getHandleMark() {
		return handleMark;
	}
	public void setHandleMark(String handleMark) {
		this.handleMark = handleMark;
	}
	public byte[] getBodyXml() {
		return bodyXml;
	}
	public void setBodyXml(byte[] bodyXml) {
		this.bodyXml = bodyXml;
	}
	
}
