package com.zehin.vpaas.beans;

import java.sql.Timestamp;
import java.util.Date;

public class Errors {
	private String ServiceType;
	private String RecordId;
	private String IDCard;
	private String BusinessTypeNo;
	private byte[] BodyXml;
	private String License;
	private String AreaCode;
	private String OrgCode;
	private String OrgName;
	private String DomainCode;
	private String DomainName;
	private Timestamp CreateDate;
	private Timestamp UpdateDate;
	private String ErrorInfo;
	private String ModifyMark;
	public String getServiceType() {
		return ServiceType;
	}
	public void setServiceType(String serviceType) {
		ServiceType = serviceType;
	}
	public String getRecordId() {
		return RecordId;
	}
	public void setRecordId(String recordId) {
		RecordId = recordId;
	}
	public String getIDCard() {
		return IDCard;
	}
	public void setIDCard(String iDCard) {
		IDCard = iDCard;
	}
	public String getBusinessTypeNo() {
		return BusinessTypeNo;
	}
	public void setBusinessTypeNo(String businessTypeNo) {
		BusinessTypeNo = businessTypeNo;
	}
	public byte[] getBodyXml() {
		return BodyXml;
	}
	public void setBodyXml(byte[] bodyXml) {
		BodyXml = bodyXml;
	}
	public String getLicense() {
		return License;
	}
	public void setLicense(String license) {
		License = license;
	}
	public String getAreaCode() {
		return AreaCode;
	}
	public void setAreaCode(String areaCode) {
		AreaCode = areaCode;
	}
	public String getOrgCode() {
		return OrgCode;
	}
	public void setOrgCode(String orgCode) {
		OrgCode = orgCode;
	}
	public String getOrgName() {
		return OrgName;
	}
	public void setOrgName(String orgName) {
		OrgName = orgName;
	}
	public String getDomainCode() {
		return DomainCode;
	}
	public void setDomainCode(String domainCode) {
		DomainCode = domainCode;
	}
	public String getDomainName() {
		return DomainName;
	}
	public void setDomainNmae(String domainNmae) {
		DomainName = domainNmae;
	}
	public Timestamp getCreateDate() {
		return CreateDate;
	}
	public void setCreateDate(Timestamp createDate) {
		CreateDate = createDate;
	}
	public Timestamp getUpdateDate() {
		return UpdateDate;
	}
	public void setUpdateDate(Timestamp updateDate) {
		UpdateDate = updateDate;
	}
	public String getErrorInfo() {
		return ErrorInfo;
	}
	public void setErrorInfo(String errorInfo) {
		ErrorInfo = errorInfo;
	}
	public String getModifyMark() {
		return ModifyMark;
	}
	public void setModifyMark(String modifyMark) {
		ModifyMark = modifyMark;
	}
	
}
