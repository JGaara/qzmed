package com.zehin.vpaas.beans;

import java.sql.Date;
import java.sql.Timestamp;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Content")
@XmlType(propOrder = {"AdviceSrno", "AdviceCode", "Srno", "HospitalNO",  "IDCard",
		"HealthCard", "PatientName", "HospitalCode", "Expr4HospitalName", "ItemInputDate",
		"PitemCode", "HitemCode", "HitemName", "ItemCode", "ItemName",
		"IsFee", "Execmode", "Price", "Quantity", "Pack",
		"TotalAmount", "IsDrug", "DrugSpec", "Frequency", "DosageCount",
		"DosageUnit","count", "CountUnit", "DrugWayCode", "DrugWayName",
		"SkinTestFlag", "SkinTestResult", "SkinTestOperator", "SkinTestDate", "DrugNum",
		"DrugUnit", "OperatorCode",	"OperatorName", "SubmitDate", "RecordState"})
public class DEHDrugAdviceDetailContent {
	private String AdviceSrno;
	private String AdviceCode;
	private String Srno;
	private String HospitalNO;
	private String IDCard;
	
	private String HealthCard;
	private String PatientName;
	private String HospitalCode;
	private String Expr4HospitalName;
	private String ItemInputDate;
	
	private String PitemCode;
	private String HitemCode;
	private String HitemName;
	private String ItemCode;
	private String ItemName;
	
	private String IsFee;
	private String Execmode;
	private double Price;
	private double Quantity;
	private int Pack;
	
	private double TotalAmount;	
	private String IsDrug;
	private String DrugSpec;
	private String Frequency;	
	private double DosageCount;
	
	private String DosageUnit;
	private double count;
	private String CountUnit;
	private String DrugWayCode;
	private String DrugWayName;
	
	private String SkinTestFlag;
	private String SkinTestResult;
	private String SkinTestOperator;
	private String SkinTestDate;
	private double DrugNum;
	
	private String DrugUnit;
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
	
	public Timestamp getSubmitDate() {
		return SubmitDate;
	}
	public void setSubmitDate(Timestamp submitDate) {
		SubmitDate = submitDate;
	}
	public String getExpr4HospitalName() {
		return Expr4HospitalName;
	}
	public void setExpr4HospitalName(String expr4HospitalName) {
		Expr4HospitalName = expr4HospitalName;
	}
	public String getItemInputDate() {
		return ItemInputDate;
	}
	public void setItemInputDate(String itemInputDate) {
		ItemInputDate = itemInputDate;
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
	
	public String getIsFee() {
		return IsFee;
	}
	public void setIsFee(String isFee) {
		IsFee = isFee;
	}
	public String getExecmode() {
		return Execmode;
	}
	public void setExecmode(String execmode) {
		Execmode = execmode;
	}
	public double getPrice() {
		return Price;
	}
	public void setPrice(double price) {
		Price = price;
	}
	public double getQuantity() {
		return Quantity;
	}
	public void setQuantity(double quantity) {
		Quantity = quantity;
	}
	public int getPack() {
		return Pack;
	}
	public void setPack(int pack) {
		Pack = pack;
	}
	public double getTotalAmount() {
		return TotalAmount;
	}
	public void setTotalAmount(double totalAmount) {
		TotalAmount = totalAmount;
	}
	public String getIsDrug() {
		return IsDrug;
	}
	public void setIsDrug(String isDrug) {
		IsDrug = isDrug;
	}
	public String getDrugSpec() {
		return DrugSpec;
	}
	public void setDrugSpec(String drugSpec) {
		DrugSpec = drugSpec;
	}
	public String getFrequency() {
		return Frequency;
	}
	public void setFrequency(String frequency) {
		Frequency = frequency;
	}
	public double getDosageCount() {
		return DosageCount;
	}
	public void setDosageCount(double dosageCount) {
		DosageCount = dosageCount;
	}
	public String getDosageUnit() {
		return DosageUnit;
	}
	public void setDosageUnit(String dosageUnit) {
		DosageUnit = dosageUnit;
	}
	public double getCount() {
		return count;
	}
	public void setCount(double count) {
		this.count = count;
	}
	public String getCountUnit() {
		return CountUnit;
	}
	public void setCountUnit(String countUnit) {
		CountUnit = countUnit;
	}
	public String getDrugWayCode() {
		return DrugWayCode;
	}
	public void setDrugWayCode(String drugWayCode) {
		DrugWayCode = drugWayCode;
	}
	public String getDrugWayName() {
		return DrugWayName;
	}
	public void setDrugWayName(String drugWayName) {
		DrugWayName = drugWayName;
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
	public double getDrugNum() {
		return DrugNum;
	}
	public void setDrugNum(double drugNum) {
		DrugNum = drugNum;
	}
	public String getDrugUnit() {
		return DrugUnit;
	}
	public void setDrugUnit(String drugUnit) {
		DrugUnit = drugUnit;
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

	public String getRecordState() {
		return RecordState;
	}
	public void setRecordState(String recordState) {
		RecordState = recordState;
	}
	
	
	
}
