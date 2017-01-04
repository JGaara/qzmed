package com.zehin.vpaas.common.util;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class JaxbTimestampAdapter extends XmlAdapter<String, Timestamp> {
	static final String STANDARM_Timestamp_FORMAT = "yyyy-MM-dd HH:mm:ss";

	@Override
	public Timestamp unmarshal(String v) throws Exception {
		if (v == null) {
			return null;
		}
		
		DateFormat format = new SimpleDateFormat(STANDARM_Timestamp_FORMAT);
		return (Timestamp) format.parse(v);
	}

	@Override
	public String marshal(Timestamp v) throws Exception {
		DateFormat format = new SimpleDateFormat(STANDARM_Timestamp_FORMAT);
		return format.format(v);
	}
}