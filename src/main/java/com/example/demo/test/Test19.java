package com.example.demo.test;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;

import com.example.demo.util.CodeConvert;
import com.example.demo.util.CodeConvert2;
import com.example.demo.util.JSONUtil;
import com.example.demo.util.StringUtil;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonValueInstantiator;

import lombok.Data;

public class Test19 {
	
	
	public static void main(String[] args) throws IOException {
		String json = "{ \r\n" + 
				"  \"name\":\"asdf\",\r\n" + 
				"   \"address\":\"asdfasdf\",\r\n" + 
				"  \"date\":\"20210324\",\r\n" + 
				"  \"code\":\"700\"\r\n" + 
				"}";
		
		
//		Person object = JSONUtil.toJSONObject(json, Person.class);
//		System.out.println(object);
		
		
		BigDecimal a = BigDecimal.ZERO;
		cal(a);
		System.out.println(a);
		
		
	}
	
	static int cal(BigDecimal a) {
		a.add(BigDecimal.TEN);
		return 1;
	}
	
	
	
	@Data
	static class Person {
		private String name;
		
		private String address;
		
		@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyyMMdd",timezone = "GMT+8")
		private Date date;
		
		@JsonDeserialize(using = CodeConvert.class)
//		@JsonSerialize(using = CodeConvert2.class)
		private String code;
		
		
//		public void setCode(String code) {
//			this.code = StringUtil.codeFillZero(code);
//		}
		
	}

}



