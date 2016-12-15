package com.zehin.vpaas.beans;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.zehin.vpaas.common.util.JaxbUtil;
import com.zehin.vpaas.common.util.StringUtil;
import com.zehin.vpaas.generic.HospitalBaseInfo;

/**
 * 
 * 住院医嘱主表。
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "DEHDrugAdvice")
@XmlType(propOrder = { "HospitalCode", "HospitalLicense", "Content" })

public class DEHDrugAdvice {
	private String HospitalCode;
	private String HospitalLicense;
	private DEHDrugAdviceContent Content;
	public String getHospitalCode() {
		return HospitalCode;
	}
	public void setHospitalCode(String hospitalCode) {
		HospitalCode = hospitalCode;
	}
	public String getHospitalLicense() {
		return HospitalLicense;
	}
	public void setHospitalLicense(String hospitalLicense) {
		HospitalLicense = hospitalLicense;
	}
	public DEHDrugAdviceContent getContent() {
		return Content;
	}
	public void setContent(DEHDrugAdviceContent content) {
		Content = content;
	}
	
	
	/**
	 * 
	 * @param serialNum 流水号, 
	 * @return
	 */

	public static Original getOriginal(DEHDrugAdviceContent content, String serialNum) {
		DEHDrugAdvice dec = new DEHDrugAdvice();
		//这个编码用视图中。
		dec.setHospitalCode(content.getHospitalCode());
		String hosName = content.getHospitalName();
		HospitalBaseInfo hosInfo = new HospitalBaseInfo();
		//平台配置的license?
		dec.setHospitalLicense(hosInfo.getHosLicense());
		dec.setContent(content);
		
		String bodyXml = JaxbUtil.convertToXml(dec);
		
		Original o = new Original();
		
		
		o.setBodyXml(bodyXml.getBytes());
		o.setBusinessTypeNo(CommonCode.BusinessTypeZYYZZB);
		
		//平台分配.
		o.setDomainCode(hosInfo.getDomainCode());
		o.setDomainName(hosInfo.getDomainName());
		//身份证
		String idCard = content.getIDCard();
		if(StringUtil.isEmpty(idCard) || idCard.equals("未提供")) {
			idCard = "000000" + "000000" + "000000";
		}
		o.setIdCard(idCard);
		
		o.setLicense(hosInfo.getHosLicense());
		o.setOrgCode(hosInfo.getHosCode());
		o.setOrgName(hosInfo.getHosName());
		o.setAreaCode(hosInfo.getHosAreaCode());
		
		SimpleDateFormat dateFormater = new SimpleDateFormat("yyyyMMddHHmm");
		String date = dateFormater.format(new Date());
		//这个地方是用hosCode还是hosLicense？
		String recordId = hosInfo.getHosCode() + date + serialNum;
		o.setRecordId(recordId);
		//卫生采集
		o.setServiceType(CommonCode.ServiceTypeHealthCollect);
		o.setCreateDate(new java.sql.Timestamp(new java.util.Date().getTime()));
//		o.setUpdateDate(updateDate);
		o.setHandleMark("0");
		return o;
	}
	
}
