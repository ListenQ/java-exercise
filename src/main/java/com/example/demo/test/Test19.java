package com.example.demo.test;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
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
				"  \"date\":1617763594515,\r\n" + 
				"  \"code\":\"700\"\r\n" + 
				"}";
		
		
		Person object = JSONUtil.toJSONObject(json, Person.class);
		Person object2 = JSONObject.parseObject(json,Person.class);
//		System.out.println(object2);
		
		
		BigDecimal [] a = {BigDecimal.ZERO};
		cal(a);
		for (BigDecimal bigDecimal : a) {
			System.out.println(bigDecimal);
		}
		
		
	}
	
	static void cal(BigDecimal[] a) {
		a[0] = a[0].add(BigDecimal.TEN);
	}
	
	
	
	@Data
	static class Person {
		private String name;
		
		private String address;
		
		@JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
//		@DateTimeFormat(pattern = "yyyy-MM-dd")
//		@JSONField(format="yyyy-MM-dd")
		private Date date;
		
//		@JsonDeserialize(using = CodeConvert.class)
//		@JsonSerialize(using = CodeConvert2.class)
		private String code;
		
		
//		public void setCode(String code) {
//			this.code = StringUtil.codeFillZero(code);
//		}
		
	}

}



