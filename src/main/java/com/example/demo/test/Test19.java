package com.example.demo.test;

import java.io.IOException;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import com.example.demo.test.Test19.Person;
import com.example.demo.util.JSONUtil;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.Data;
import net.sf.json.JSON;
import net.sf.json.util.JSONUtils;

public class Test19 {
	
	
	public static void main(String[] args) throws IOException {
		String json = "{	\r\n" + 
				"  \"name\":\"阿斯蒂芬\",\r\n" + 
				"   \"address\":\"asdf\",\r\n" + 
				"   \"date\":20210312\r\n" + 
				"}";
		
		
		Person object = JSONUtil.toJSONObject(json, Person.class);
		System.out.println(object);
		
		System.out.println(DateUtil.parseDate("20210320", DateUtil.YYYMMDD_FILL_DATETIME_FORMAT));
	}
	
	
	
	@Data
	static class Person {
		private String name;
		
		private String address;
		
		@JsonFormat(shape=JsonFormat.Shape.NUMBER_INT, pattern="yyyy-MM-dd",timezone = "GMT+8")
		private Date date;

	}

}



