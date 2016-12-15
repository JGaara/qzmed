package com.zehin.vpaas.beans;

import java.sql.Date;
import java.sql.Timestamp;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * 门诊处方明细表
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Content")
@XmlType(propOrder = {"AdviceSrno", "AdviceCode", "Srno", "RegNo",  "IDCard",
		"HealthCard", "PatientName", "HospitalCode", "HospitalName", "PItemCode", 
		"HItemCode", "HItemName", "ItemCode", "ItemName", "InputDate", 
		"IsDrugs", "DosageCode", "DosageName", "DrugSpec", "QuantityNumber", 
		"DrugUnitCode", "DrugUnitName", "Price", "TotalAmount",/*"Frequency",
		"DosageCount", "DosageUnit", */"Count", "CountUnit","BoilName",
		"SkinTestFlag", "SkinTestResult", "SkinTestOperator", "SkinTestDate", "OperatorCode",
		"OperatorName", "SubmitDate", "RecordState"})
public class DECDrugAdviceDetailContent {
	private String AdviceSrno;
	private String AdviceCode;
	private String Srno;
	private String RegNo;
	private String IDCard;
	
	private String HealthCard;
	private String PatientName;
	private String HospitalCode;
	private String HospitalName;
	private String PItemCode;
	
	private String HItemCode;
	private String HItemName;
	private String ItemCode;
	private String ItemName;
	private String InputDate;
	
	private String IsDrugs;
	private String DosageCode;
	private String DosageName;
	private String DrugSpec;
	private double QuantityNumber;
	
	private String DrugUnitCode;
	private String DrugUnitName;
	private double Price;
	private double TotalAmount;
/*	private String Frequency;
	
	private int DosageCount;
	private String DosageUnit; 
	*/
	private double Count;
	private String CountUnit;
	private String BoilName;
	
	private String SkinTestFlag;
	private String SkinTestResult;
	private String SkinTestOperator;
	private String SkinTestDate;
	private String OperatorCode;
	
	private String OperatorName;
	private Timestamp SubmitDate;
	private String RecordState;
	public String getAdviceSrno() {
		return AdviceSrno;
	}
	public void setAdviceSrno(String adviceSrno) {
		AdviceSrno = adviceSrno;
	}
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
	public String getPItemCode() {
		return PItemCode;
	}
	public void setPItemCode(String pItemCode) {
		PItemCode = pItemCode;
	}
	public String getHItemCode() {
		return HItemCode;
	}
	public void setHItemCode(String hItemCode) {
		HItemCode = hItemCode;
	}
	public String getHItemName() {
		return HItemName;
	}
	public void setHItemName(String hItemName) {
		HItemName = hItemName;
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
	public String getInputDate() {
		return InputDate;
	}
	public void setInputDate(String inputDate) {
		InputDate = inputDate;
	}
	public String getIsDrugs() {
		return IsDrugs;
	}
	public void setIsDrugs(String isDrugs) {
		IsDrugs = isDrugs;
	}
	public String getDosageCode() {
		return DosageCode;
	}
	public void setDosageCode(String dosageCode) {
		DosageCode = dosageCode;
	}
	public String getDosageName() {
		return DosageName;
	}
	public void setDosageName(String dosageName) {
		DosageName = dosageName;
	}
	public String getDrugSpec() {
		return DrugSpec;
	}
	public void setDrugSpec(String drugSpec) {
		DrugSpec = drugSpec;
	}
	
	public String getDrugUnitCode() {
		return DrugUnitCode;
	}
	public void setDrugUnitCode(String drugUnitCode) {
		DrugUnitCode = drugUnitCode;
	}
	public String getDrugUnitName() {
		return DrugUnitName;
	}
	public void setDrugUnitName(String drugUnitName) {
		DrugUnitName = drugUnitName;
	}
	
	public double getQuantityNumber() {
		return QuantityNumber;
	}
	public void setQuantityNumber(double quantityNumber) {
		QuantityNumber = quantityNumber;
	}
	public double getPrice() {
		return Price;
	}
	public void setPrice(double price) {
		Price = price;
	}
	public double getTotalAmount() {
		return TotalAmount;
	}
	public void setTotalAmount(double totalAmount) {
		TotalAmount = totalAmount;
	}
	public double getCount() {
		return Count;
	}
	public void setCount(double count) {
		Count = count;
	}
	public String getCountUnit() {
		return CountUnit;
	}
	public void setCountUnit(String countUnit) {
		CountUnit = countUnit;
	}
	public String getBoilName() {
		return BoilName;
	}
	public void setBoilName(String boilName) {
		BoilName = boilName;
	}
	public String getSkinTestFlag() {
		return SkinTestFlag;
	}
	public void setSkinTestFlag(String skinTestFlag) {
		SkinTestFlag = skinTestFlag;
	}
	public String getSkinTestResult() {
		return SkinTestResult;
	}
	public void setSkinTestResult(String skinTestResult) {
		SkinTestResult = skinTestResult;
	}
	public String getSkinTestOperator() {
		return SkinTestOperator;
	}
	public void setSkinTestOperator(String skinTestOperator) {
		SkinTestOperator = skinTestOperator;
	}
	public String getSkinTestDate() {
		return SkinTestDate;
	}
	public void setSkinTestDate(String skinTestDate) {
		SkinTestDate = skinTestDate;
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
