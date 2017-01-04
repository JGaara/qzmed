package com.zehin.vpaas.beans;

import java.sql.Date;
import java.sql.Timestamp;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.zehin.vpaas.common.util.JaxbTimestampAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Content")
@XmlType(propOrder = { "HospitalNO", "Srno","IDCard", "HealthCard", "PatientName",
		"HospitalCode", "HospitalName",  "InvoiceCode", "ExecDate", "ExeDeptCode", 
		"ExeDeptName", "BalOperCode", "BalOperName", "TotalAmount", "RealAmount",
		"PayTypeCode", "PayTypeName", "Remark", "OperatorCode", "OperatorName",
	    "SubmitDate", "RecordState"})
public class DEHFareBalanceContent {
	private String HospitalNO;
	private String Srno;
	private String IDCard;	
	private String HealthCard;		
	private String PatientName;
	
	
	private String HospitalCode;
	private String HospitalName;
	private String InvoiceCode;
	private String ExecDate;	
	private String ExeDeptCode;	
	
	private String ExeDeptName;
	private String BalOperCode;
	private String BalOperName;
	private double TotalAmount;	
	private double RealAmount;
	
	private String PayTypeCode;	
	private String PayTypeName;
	private String Remark;
	private String OperatorCode;
	private String OperatorName;
	@XmlJavaTypeAdapter(JaxbTimestampAdapter.class)
	private Timestamp SubmitDate;
	private String RecordState;
	public String getHospitalNO() {
		return HospitalNO;
	}
	public void setHospitalNO(String hospitalNO) {
		HospitalNO = hospitalNO;
	}
	public String getSrno() {
		return Srno;
	}
	public void setSrno(String srno) {
		Srno = srno;
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
	public String getInvoiceCode() {
		return InvoiceCode;
	}
	public void setInvoiceCode(String invoiceCode) {
		InvoiceCode = invoiceCode;
	}
	public String getExecDate() {
		return ExecDate;
	}
	public void setExecDate(String execDate) {
		ExecDate = execDate;
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
	public String getBalOperCode() {
		return BalOperCode;
	}
	public void setBalOperCode(String balOperCode) {
		BalOperCode = balOperCode;
	}
	public String getBalOperName() {
		return BalOperName;
	}
	public void setBalOperName(String balOperName) {
		BalOperName = balOperName;
	}
	public double getTotalAmount() {
		return TotalAmount;
	}
	public void setTotalAmount(double totalAmount) {
		TotalAmount = totalAmount;
	}
	public double getRealAmount() {
		return RealAmount;
	}
	public void setRealAmount(double realAmount) {
		RealAmount = realAmount;
	}
	public String getPayTypeCode() {
		return PayTypeCode;
	}
	public void setPayTypeCode(String payTypeCode) {
		PayTypeCode = payTypeCode;
	}
	public String getPayTypeName() {
		return PayTypeName;
	}
	public void setPayTypeName(String payTypeName) {
		PayTypeName = payTypeName;
	}
	public String getRemark() {
		return Remark;
	}
	public void setRemark(String remark) {
		Remark = remark;
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
