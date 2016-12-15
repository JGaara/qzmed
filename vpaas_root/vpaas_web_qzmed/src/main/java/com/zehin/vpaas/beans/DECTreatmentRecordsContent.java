package com.zehin.vpaas.beans;

import java.sql.Date;
import java.sql.Timestamp;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
/**
 * 门诊就诊记录
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Content")
@XmlType(propOrder = { "Srno", "RegNo", "IDCard", "HealthCard", "PatientName", "HospitalCode", "HospitalName",
		"HDiseaseCode", "HDiseaseName", "DiseaseCode", "DiseaseName", "ClinicDate", "DoctorCode", "DoctorName", "HSecDiseaseCode",
		"HSecDiseaseName", "SecDiseaseCode", "SecDiseaseName", "Temperature", "Weight", "SBloodPressure", "DBloodPressure", "Pulse", "CheckDate",
		"CheckerCode", "CheckerName", "ClinicDesc", "ClinicComplain", "SymptomDesc", "OperatorCode", "OperatorName", "SubmitDate", "RecordState" })
public class DECTreatmentRecordsContent {
	private String Srno;
	private String RegNo;
	private String IDCard;
	private String HealthCard;
	private String PatientName;
	private String HospitalCode;
	private String HospitalName;
	private String HDiseaseCode;
	private String HDiseaseName;
	private String DiseaseCode;
	private String DiseaseName;
	private String ClinicDate;
	private String DoctorCode;
	private String DoctorName;
	private String HSecDiseaseCode;
	private String HSecDiseaseName;
	private String SecDiseaseCode;
	private String SecDiseaseName;
	private double Temperature;
	private double Weight;
	private double SBloodPressure;
	private double DBloodPressure;
	private double Pulse;
	private String CheckDate;
	private String CheckerCode;
	private String CheckerName;
	private String ClinicDesc;
	private String ClinicComplain;
	private String SymptomDesc;
	private String OperatorCode;
	private String OperatorName;
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
	public String getHDiseaseCode() {
		return HDiseaseCode;
	}
	public void setHDiseaseCode(String hDiseaseCode) {
		HDiseaseCode = hDiseaseCode;
	}
	public String getHDiseaseName() {
		return HDiseaseName;
	}
	public void setHDiseaseName(String hDiseaseName) {
		HDiseaseName = hDiseaseName;
	}
	public String getDiseaseCode() {
		return DiseaseCode;
	}
	public void setDiseaseCode(String diseaseCode) {
		DiseaseCode = diseaseCode;
	}
	public String getDiseaseName() {
		return DiseaseName;
	}
	public void setDiseaseName(String diseaseName) {
		DiseaseName = diseaseName;
	}
	public String getClinicDate() {
		return ClinicDate;
	}
	public void setClinicDate(String clinicDate) {
		ClinicDate = clinicDate;
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
	public String getHSecDiseaseCode() {
		return HSecDiseaseCode;
	}
	public void setHSecDiseaseCode(String hSecDiseaseCode) {
		HSecDiseaseCode = hSecDiseaseCode;
	}
	public String getHSecDiseaseName() {
		return HSecDiseaseName;
	}
	public void setHSecDiseaseName(String hSecDiseaseName) {
		HSecDiseaseName = hSecDiseaseName;
	}
	public String getSecDiseaseCode() {
		return SecDiseaseCode;
	}
	public void setSecDiseaseCode(String secDiseaseCode) {
		SecDiseaseCode = secDiseaseCode;
	}
	public String getSecDiseaseName() {
		return SecDiseaseName;
	}
	public void setSecDiseaseName(String secDiseaseName) {
		SecDiseaseName = secDiseaseName;
	}
	
	public double getTemperature() {
		return Temperature;
	}
	public void setTemperature(double temperature) {
		Temperature = temperature;
	}
	public double getWeight() {
		return Weight;
	}
	public void setWeight(double weight) {
		Weight = weight;
	}
	public double getSBloodPressure() {
		return SBloodPressure;
	}
	public void setSBloodPressure(double sBloodPressure) {
		SBloodPressure = sBloodPressure;
	}
	public double getDBloodPressure() {
		return DBloodPressure;
	}
	public void setDBloodPressure(double dBloodPressure) {
		DBloodPressure = dBloodPressure;
	}
	public double getPulse() {
		return Pulse;
	}
	public void setPulse(double pulse) {
		Pulse = pulse;
	}
	public String getCheckDate() {
		return CheckDate;
	}
	public void setCheckDate(String checkDate) {
		CheckDate = checkDate;
	}
	public String getCheckerCode() {
		return CheckerCode;
	}
	public void setCheckerCode(String checkerCode) {
		CheckerCode = checkerCode;
	}
	public String getCheckerName() {
		return CheckerName;
	}
	public void setCheckerName(String checkerName) {
		CheckerName = checkerName;
	}
	public String getClinicDesc() {
		return ClinicDesc;
	}
	public void setClinicDesc(String clinicDesc) {
		ClinicDesc = clinicDesc;
	}
	public String getClinicComplain() {
		return ClinicComplain;
	}
	public void setClinicComplain(String clinicComplain) {
		ClinicComplain = clinicComplain;
	}
	public String getSymptomDesc() {
		return SymptomDesc;
	}
	public void setSymptomDesc(String symptomDesc) {
		SymptomDesc = symptomDesc;
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
