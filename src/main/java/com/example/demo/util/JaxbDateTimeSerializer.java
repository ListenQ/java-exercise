package com.example.demo.util;

import java.util.Date;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;


public class JaxbDateTimeSerializer extends XmlAdapter<String,Date>{
	
	private static final String DEFAULT_DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss"; 

	@Override
	public Date unmarshal(String v) throws Exception {
		return DateUtils.parseDate(v, DEFAULT_DATETIME_FORMAT);
	}

	@Override
	public String marshal(Date v) throws Exception {
		return DateFormatUtils.format(v, DEFAULT_DATETIME_FORMAT);
	}

}
