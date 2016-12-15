package com.zehin.vpaas.beans;

import java.sql.Date;
import java.sql.Timestamp;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Content")
@XmlType(propOrder = { "AdviceCode", "Srno", "HospitalNO",  "IDCard", "HealthCard",
		"PatientName", "HospitalCode", "HospitalName", "AdviceName", "InputDate",
		"InputEmpCode", "InputEmpName", "ReviewerCode", "ReviewerName", "OpenDeptCode",
		"OpenDeptName", "ExeDeptCode", "ExeDeptName", "StartDate", "ExecDoctorCode", 
		"ExecDoctorName", "StopcDoctorCode", "StopcDoctorName", "StopDate","TotalAmount",
		"OperatorCode", "OperatorName", "SubmitDate", "RecordState"})
public class DEHDrugAdviceContent {
	private String AdviceCode;
	private String Srno;
	private String HospitalNO;
	private String IDCard;
	private String HealthCard;
	
	private String PatientName;
	private String HospitalCode;
	private String HospitalName;
	private String AdviceName;
	private String InputDate;
	
	private String InputEmpCode;
	private String InputEmpName;
	private String ReviewerCode;
	private String ReviewerName;	
	private String OpenDeptCode;
	
	private String OpenDeptName;
	private String ExeDeptCode;
	private String ExeDeptName;
	private String StartDate;
	private String ExecDoctorCode;
	
	private String ExecDoctorName;
	private String StopcDoctorCode;
	private String StopcDoctorName;
	private String StopDate;
	private double TotalAmount;
	

	private String OperatorCode;
	private String OperatorName;
	private Timestamp SubmitDate;
	private String RecordState;
	public String getAdviceCode() {
		return AdviceCode;
	}
	public void setAdviceCode(String adviceCode) {
		AdviceCode = adviceCode;
	}
	public String getSrno() {
		return Srno;
	}
	public void setSrno(String srno) {
		Srno = srno;
	}
	public String getHospitalNO() {
		return HospitalNO;
	}
	public void setHospitalNO(String hospitalNO) {
		HospitalNO = hospitalNO;
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
	public String getAdviceName() {
		return AdviceName;
	}
	public void setAdviceName(String adviceName) {
		AdviceName = adviceName;
	}
	public String getInputDate() {
		return InputDate;
	}
	public void setInputDate(String inputDate) {
		InputDate = inputDate;
	}
	public String getInputEmpCode() {
		return InputEmpCode;
	}
	public void setInputEmpCode(String inputEmpCode) {
		InputEmpCode = inputEmpCode;
	}
	public String getInputEmpName() {
		return InputEmpName;
	}
	public void setInputEmpName(String inputEmpName) {
		InputEmpName = inputEmpName;
	}
	public String getReviewerCode() {
		return ReviewerCode;
	}
	public void setReviewerCode(String reviewerCode) {
		ReviewerCode = reviewerCode;
	}
	public String getReviewerName() {
		return ReviewerName;
	}
	public void setReviewerName(String reviewerName) {
		ReviewerName = reviewerName;
	}
	public String getOpenDeptCode() {
		return OpenDeptCode;
	}
	public void setOpenDeptCode(String openDeptCode) {
		OpenDeptCode = openDeptCode;
	}
	public String getOpenDeptName() {
		return OpenDeptName;
	}
	public void setOpenDeptName(String openDeptName) {
		OpenDeptName = openDeptName;
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
	public String getStartDate() {
		return StartDate;
	}
	public void setStartDate(String startDate) {
		StartDate = startDate;
	}
	public String getExecDoctorCode() {
		return ExecDoctorCode;
	}
	public void setExecDoctorCode(String execDoctorCode) {
		ExecDoctorCode = execDoctorCode;
	}
	public String getExecDoctorName() {
		return ExecDoctorName;
	}
	public void setExecDoctorName(String execDoctorName) {
		ExecDoctorName = execDoctorName;
	}
	public String getStopcDoctorCode() {
		return StopcDoctorCode;
	}
	public void setStopcDoctorCode(String stopcDoctorCode) {
		StopcDoctorCode = stopcDoctorCode;
	}
	public String getStopcDoctorName() {
		return StopcDoctorName;
	}
	public void setStopcDoctorName(String stopcDoctorName) {
		StopcDoctorName = stopcDoctorName;
	}
	public String getStopDate() {
		return StopDate;
	}
	public void setStopDate(String stopDate) {
		StopDate = stopDate;
	}
	public double getTotalAmount() {
		return TotalAmount;
	}
	public void setTotalAmount(double totalAmount) {
		TotalAmount = totalAmount;
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
