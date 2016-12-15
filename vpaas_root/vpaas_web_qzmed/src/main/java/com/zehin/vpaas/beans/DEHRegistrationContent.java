package com.zehin.vpaas.beans;

import java.sql.Date;
import java.sql.Timestamp;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Content")
@XmlType(propOrder = { "Srno", "HospitalNO", "IDCard", "HealthCard", "PatientName",
		"HospitalCode", "HospitalName", "InDate", "OutDate", "InDeptCode",
		"InDeptName", "BedNO", "HInDiseaaseCode", "HInDiseaaseName", "InDiseaaseCode", 
		"InDiseaaseName", "InSymptomCode", "InSymptomName", "GenderCode", "GenderName",
		"Birthday", "DoctorCode","DoctorName","NurseCode","NurseName",
		"OperatorCode","OperatorName","SubmitDate","RecordState","ContactPhone",
		"ContactAddress", "WayInCode", "WayInName"})
public class DEHRegistrationContent {
	private String Srno;
	private String HospitalNO;
	private String IDCard;
	private String HealthCard;
	private String PatientName;
	
	private String HospitalCode;
	private String HospitalName;
	private String InDate;
	private String OutDate;
	private String InDeptCode;
	
	private String InDeptName;
	private String BedNO;
	private String HInDiseaaseCode;
	private String HInDiseaaseName;
	private String InDiseaaseCode;
	
	private String InDiseaaseName;
	private String InSymptomCode;
	private String InSymptomName;
	private String GenderCode;
	private String GenderName;
	
	private String Birthday;
	private String DoctorCode;
	private String DoctorName;
	private String NurseCode;
	private String NurseName;
	
	private String OperatorCode;
	private String OperatorName;
	private Timestamp SubmitDate;
	private String RecordState;
	private String ContactPhone;
	
	private String ContactAddress;
	private String WayInCode;
	private String WayInName;

	public String getWayInCode() {
		return WayInCode;
	}

	public void setWayInCode(String wayInCode) {
		WayInCode = wayInCode;
	}

	public String getWayInName() {
		return WayInName;
	}

	public void setWayInName(String wayInName) {
		WayInName = wayInName;
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

	public String getInDate() {
		return InDate;
	}

	public void setInDate(String inDate) {
		InDate = inDate;
	}

	public String getOutDate() {
		return OutDate;
	}

	public void setOutDate(String outDate) {
		OutDate = outDate;
	}

	public String getInDeptCode() {
		return InDeptCode;
	}

	public void setInDeptCode(String inDeptCode) {
		InDeptCode = inDeptCode;
	}

	public String getInDeptName() {
		return InDeptName;
	}

	public void setInDeptName(String inDeptName) {
		InDeptName = inDeptName;
	}

	public String getBedNO() {
		return BedNO;
	}

	public void setBedNO(String bedNO) {
		BedNO = bedNO;
	}

	public String getHInDiseaaseCode() {
		return HInDiseaaseCode;
	}

	public void setHInDiseaaseCode(String hInDiseaaseCode) {
		HInDiseaaseCode = hInDiseaaseCode;
	}

	public String getHInDiseaaseName() {
		return HInDiseaaseName;
	}

	public void setHInDiseaaseName(String hInDiseaaseName) {
		HInDiseaaseName = hInDiseaaseName;
	}

	public String getInDiseaaseCode() {
		return InDiseaaseCode;
	}

	public void setInDiseaaseCode(String inDiseaaseCode) {
		InDiseaaseCode = inDiseaaseCode;
	}

	public String getInDiseaaseName() {
		return InDiseaaseName;
	}

	public void setInDiseaaseName(String inDiseaaseName) {
		InDiseaaseName = inDiseaaseName;
	}

	public String getInSymptomCode() {
		return InSymptomCode;
	}

	public void setInSymptomCode(String inSymptomCode) {
		InSymptomCode = inSymptomCode;
	}

	public String getInSymptomName() {
		return InSymptomName;
	}

	public void setInSymptomName(String inSymptomName) {
		InSymptomName = inSymptomName;
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

	public void setBirthday(String birthday) {
		Birthday = birthday;
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

	public String getNurseCode() {
		return NurseCode;
	}

	public void setNurseCode(String nurseCode) {
		NurseCode = nurseCode;
	}

	public String getNurseName() {
		return NurseName;
	}

	public void setNurseName(String nurseName) {
		NurseName = nurseName;
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
	
	
}
