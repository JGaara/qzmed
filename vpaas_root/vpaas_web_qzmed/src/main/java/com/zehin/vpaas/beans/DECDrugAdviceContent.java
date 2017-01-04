package com.zehin.vpaas.beans;

import java.sql.Date;
import java.sql.Timestamp;
import com.zehin.vpaas.common.util.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * 门诊处方主表
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Content")
@XmlType(propOrder = { "Srno", "RegNo", "AdviceCode", "IDCard", "HealthCard", "PatientName", "HospitalCode",
		"HospitalName", "DoctorCode", "DoctorName", "CreateDate", "ReqDeptCode", "ReqDeptName", "ExeDeptCode", "ExeDeptName",
		"Pack", "CancelFlag", "TotalAmount", "SevenNote", "OperatorCode", "OperatorName", "SubmitDate", "RecordState" })

public class DECDrugAdviceContent {
	private String Srno;
	private String RegNo;
	private String AdviceCode;
	private String IDCard;
	private String HealthCard;
	private String PatientName;
	private String HospitalCode;
	private String HospitalName;
	private String DoctorCode;
	private String DoctorName;
	private String CreateDate;
	private String ReqDeptCode;
	private String ReqDeptName;
	private String ExeDeptCode;
	private String ExeDeptName;
	private double Pack;
	private String CancelFlag;
	private double TotalAmount;
	private String SevenNote;
	private String OperatorCode;
	private String OperatorName;
	@XmlJavaTypeAdapter(JaxbTimestampAdapter.class)
	private Timestamp SubmitDate;
	private String RecordState;
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
	public String getAdviceCode() {
		return AdviceCode;
	}
	public void setAdviceCode(String adviceCode) {
		AdviceCode = adviceCode;
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
	public String getDoctorCode() {
		return DoctorCode;
	}
	public void setDoctorCode(String doctorCode) {
		DoctorCode = doctorCode;
	}
	public String getDoctorName() {
		return DoctorName;
	}
	public void setDoctorName(String doctorName) {
		DoctorName = doctorName;
	}
	public String getCreateDate() {
		return CreateDate;
	}
	public void setCreateDate(String createDate) {
		CreateDate = createDate;
	}
	public String getReqDeptCode() {
		return ReqDeptCode;
	}
	public void setReqDeptCode(String reqDeptCode) {
		ReqDeptCode = reqDeptCode;
	}
	public String getReqDeptName() {
		return ReqDeptName;
	}
	public void setReqDeptName(String reqDeptName) {
		ReqDeptName = reqDeptName;
	}
	public String getExeDeptCode() {
		return ExeDeptCode;
	}
	public void setExeDeptCode(String exeDeptCode) {
		ExeDeptCode = exeDeptCode;
	}
	public String getExeDeptName() {
		return ExeDeptName;
	}
	public void setExeDeptName(String exeDeptName) {
		ExeDeptName = exeDeptName;
	}
	public double getPack() {
		return Pack;
	}
	public void setPack(double pack) {
		Pack = pack;
	}
	public String getCancelFlag() {
		return CancelFlag;
	}
	public void setCancelFlag(String cancelFlag) {
		CancelFlag = cancelFlag;
	}
	public double getTotalAmount() {
		return TotalAmount;
	}
	public void setTotalAmount(double totalAmount) {
		TotalAmount = totalAmount;
	}
	public String getSevenNote() {
		return SevenNote;
	}
	public void setSevenNote(String sevenNote) {
		SevenNote = sevenNote;
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
	public String getRecordState() {
		return RecordState;
	}
	public void setRecordState(String recordState) {
		RecordState = recordState;
	}
	
}
