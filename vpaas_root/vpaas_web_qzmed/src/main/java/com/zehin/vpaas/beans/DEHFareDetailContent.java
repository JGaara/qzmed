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
@XmlType(propOrder = { "FareSrno", "Srno", "HospitalNO", "AdviceCode", "InvoiceCode",
		"IDCard", "HealthCard", "PatientName", "HospitalCode", "HospitalName",
		"RefundFlag", "PitemCode", "HitemCode", "HitemName", "ItemCode",
		"ItemName", "IsDrugs", "SettleTime", "ItemUnit", "ItemPrice",
		"ItemCount", "ItemAmount", "RealAmount", "AccountorCode", "AccountorName", 
		"OperatorCode", "OperatorName", "SubmitDate", "RecordState"})
public class DEHFareDetailContent {
	private String FareSrno;
	private String Srno;
	private String HospitalNO;
	private String AdviceCode;
	private String InvoiceCode;
	
	private String IDCard;	
	private String HealthCard;	
	private String PatientName;
	private String HospitalCode;
	private String HospitalName;
	
	private String RefundFlag;	
	private String PitemCode;	
	private String HitemCode;
	private String HitemName;
	private String ItemCode;
	
	private String ItemName;	
	private String IsDrugs;	
	private String SettleTime;
	private String ItemUnit;
	private double ItemPrice;
	
	private double ItemCount;	
	private double ItemAmount;	
	private double RealAmount;
	private String AccountorCode;
	private String AccountorName;
	
	private String OperatorCode;
	private String OperatorName;
	@XmlJavaTypeAdapter(JaxbTimestampAdapter.class)
	private Timestamp SubmitDate;
	private String RecordState;
	public String getFareSrno() {
		return FareSrno;
	}
	public void setFareSrno(String fareSrno) {
		FareSrno = fareSrno;
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
	public String getAdviceCode() {
		return AdviceCode;
	}
	public void setAdviceCode(String adviceCode) {
		AdviceCode = adviceCode;
	}
	public String getInvoiceCode() {
		return InvoiceCode;
	}
	public void setInvoiceCode(String invoiceCode) {
		InvoiceCode = invoiceCode;
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
	public String getRefundFlag() {
		return RefundFlag;
	}
	public void setRefundFlag(String refundFlag) {
		RefundFlag = refundFlag;
	}
	public String getPitemCode() {
		return PitemCode;
	}
	public void setPitemCode(String pitemCode) {
		PitemCode = pitemCode;
	}
	public String getHitemCode() {
		return HitemCode;
	}
	public void setHitemCode(String hitemCode) {
		HitemCode = hitemCode;
	}
	public String getHitemName() {
		return HitemName;
	}
	public void setHitemName(String hitemName) {
		HitemName = hitemName;
	}
	public String getItemCode() {
		return ItemCode;
	}
	public void setItemCode(String itemCode) {
		ItemCode = itemCode;
	}
	public String getItemName() {
		return ItemName;
	}
	public void setItemName(String itemName) {
		ItemName = itemName;
	}
	public String getIsDrugs() {
		return IsDrugs;
	}
	public void setIsDrugs(String isDrugs) {
		IsDrugs = isDrugs;
	}
	public String getSettleTime() {
		return SettleTime;
	}
	public void setSettleTime(String settleTime) {
		SettleTime = settleTime;
	}
	public String getItemUnit() {
		return ItemUnit;
	}
	public void setItemUnit(String itemUnit) {
		ItemUnit = itemUnit;
	}
	public double getItemPrice() {
		return ItemPrice;
	}
	public void setItemPrice(double itemPrice) {
		ItemPrice = itemPrice;
	}
	public double getItemCount() {
		return ItemCount;
	}
	public void setItemCount(double itemCount) {
		ItemCount = itemCount;
	}
	public double getItemAmount() {
		return ItemAmount;
	}
	public void setItemAmount(double itemAmount) {
		ItemAmount = itemAmount;
	}
	public double getRealAmount() {
		return RealAmount;
	}
	public void setRealAmount(double realAmount) {
		RealAmount = realAmount;
	}
	public String getAccountorCode() {
		return AccountorCode;
	}
	public void setAccountorCode(String accountorCode) {
		AccountorCode = accountorCode;
	}
	public String getAccountorName() {
		return AccountorName;
	}
	public void setAccountorName(String accountorName) {
		AccountorName = accountorName;
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
