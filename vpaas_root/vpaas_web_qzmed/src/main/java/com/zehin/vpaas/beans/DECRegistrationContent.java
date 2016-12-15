package com.zehin.vpaas.beans;

import java.sql.Date;
import java.sql.Timestamp;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * 门诊诊疗服务登记
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Content")
@XmlType(propOrder = { "Srno", "RegNo", "IDCard", "HealthCard", "PatientName", "PatientType", "HospitalCode",
		"HospitalName", "TreatmentType", "RegTypeCode", "RegTypeName", "IsApp", "AppTypeCode", "AppTypeName", "RegDate",
		"DeptCode", "DeptName", "GenderCode", "GenderName", "Birthday", "ClinicFee", "RegFee", "BookFee", "OtherFee",
		"RegFlag", "OperatorCode", "OperatorName", "SubmitDate", "RecordState", "ContactPhone", "ContactAddress" })
public class DECRegistrationContent {
	private String Srno;
	private String RegNo;
	private String IDCard;
	private String HealthCard;
	private String PatientName;
	private String PatientType;
	private String HospitalCode;
	private String HospitalName;
	private String TreatmentType;
	private String RegTypeCode;
	private String RegTypeName;
	private String IsApp;
	private String AppTypeCode;
	private String AppTypeName;
	private String RegDate;
	private String DeptCode;
	private String DeptName;
	private String GenderCode;
	private String GenderName;
	private String Birthday;
	private String ClinicFee;
	private String RegFee;
	private String BookFee;
	private String OtherFee;
	private String RegFlag;
	private String OperatorCode;
	private String OperatorName;
	private Timestamp SubmitDate;
	private String RecordState;
	private String ContactPhone;
	private String ContactAddress;
	public String getSrno() {
		return Srno;
	}
	public void setSrno(String srno) {
		Srno = srno;
	}
	public String getRegNo() {
		return RegNo;
	}
	public void setRegNo(String regNo) {
		RegNo = regNo;
	}
	public String getIDCard() {
		return IDCard;
	}
	public void setIDCard(String iDCard) {
		IDCard = iDCard;
	}
	public String getHealthCard() {
		return HealthCard;
	}
	public void setHealthCard(String healthCard) {
		HealthCard = healthCard;
	}
	public String getPatientName() {
		return PatientName;
	}
	public void setPatientName(String patientName) {
		PatientName = patientName;
	}
	public String getPatientType() {
		return PatientType;
	}
	public void setPatientType(String patientType) {
		PatientType = patientType;
	}
	public String getHospitalCode() {
		return HospitalCode;
	}
	public void setHospitalCode(String hospitalCode) {
		HospitalCode = hospitalCode;
	}
	public String getHospitalName() {
		return HospitalName;
	}
	public void setHospitalName(String hospitalName) {
		HospitalName = hospitalName;
	}
	public String getTreatmentType() {
		return TreatmentType;
	}
	public void setTreatmentType(String treatmentType) {
		TreatmentType = treatmentType;
	}
	public String getRegTypeCode() {
		return RegTypeCode;
	}
	public void setRegTypeCode(String regTypeCode) {
		RegTypeCode = regTypeCode;
	}
	public String getRegTypeName() {
		return RegTypeName;
	}
	public void setRegTypeName(String regTypeName) {
		RegTypeName = regTypeName;
	}
	public String getIsApp() {
		return IsApp;
	}
	public void setIsApp(String isApp) {
		IsApp = isApp;
	}
	public String getAppTypeCode() {
		return AppTypeCode;
	}
	public void setAppTypeCode(String appTypeCode) {
		AppTypeCode = appTypeCode;
	}
	public String getAppTypeName() {
		return AppTypeName;
	}
	public void setAppTypeName(String appTypeName) {
		AppTypeName = appTypeName;
	}
	public String getRegDate() {
		return RegDate;
	}
	public void setRegDate(String regDate) {
		RegDate = regDate;
	}
	public String getDeptCode() {
		return DeptCode;
	}
	public void setDeptCode(String deptCode) {
		DeptCode = deptCode;
	}
	public String getDeptName() {
		return DeptName;
	}
	public void setDeptName(String deptName) {
		DeptName = deptName;
	}
	public String getGenderCode() {
		return GenderCode;
	}
	public void setGenderCode(String genderCode) {
		GenderCode = genderCode;
	}
	public String getGenderName() {
		return GenderName;
	}
	public void setGenderName(String genderName) {
		GenderName = genderName;
	}
	public String getBirthday() {
		return Birthday;
	}
	public void setBirthday(String birthDay) {
		Birthday = birthDay;
	}
	public String getClinicFee() {
		return ClinicFee;
	}
	public void setClinicFee(String clinicFee) {
		ClinicFee = clinicFee;
	}
	public String getRegFee() {
		return RegFee;
	}
	public void setRegFee(String regFee) {
		RegFee = regFee;
	}
	public String getBookFee() {
		return BookFee;
	}
	public void setBookFee(String bookFee) {
		BookFee = bookFee;
	}
	public String getOtherFee() {
		return OtherFee;
	}
	public void setOtherFee(String otherFee) {
		OtherFee = otherFee;
	}
	public String getRegFlag() {
		return RegFlag;
	}
	public void setRegFlag(String regFlag) {
		RegFlag = regFlag;
	}
	public String getOperatorCode() {
		return OperatorCode;
	}
	public void setOperatorCode(String operatorCode) {
		OperatorCode = operatorCode;
	}
	public String getOperatorName() {
		return OperatorName;
	}
	public void setOperatorName(String operatorName) {
		OperatorName = operatorName;
	}
	
	public Timestamp getSubmitDate() {
		return SubmitDate;
	}
	public void setSubmitDate(Timestamp submitDate) {
		SubmitDate = submitDate;
	}
	public String getContactPhone() {
		return ContactPhone;
	}
	public void setContactPhone(String contactPhone) {
		ContactPhone = contactPhone;
	}
	public String getContactAddress() {
		return ContactAddress;
	}
	public void setContactAddress(String contactAddress) {
		ContactAddress = contactAddress;
	}
	private String getRecordState() {
		return RecordState;
	}
	private void setRecordState(String recordState) {
		RecordState = recordState;
	}
}
