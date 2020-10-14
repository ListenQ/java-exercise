package com.example.demo.test;

import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Test15 {
	
	public static void main(String[] args) {
//		try {
//			System.out.println(1/0);
//		} catch (Throwable e) {
//			log.error("异常:{},{}","45",e);
//		}
		
		String s = " {\r\n" + 
				"    \"header\":{\r\n" + 
				"        \"reqId\":\"8bde5d19-1b9a-4b76-8557-b4bec3f356cd\",\r\n" + 
				"        \"path\":\"auth.auth\",\r\n" + 
				"        \"language\":\"ZN\",\r\n" + 
				"        \"version\":\"1.0.0\"\r\n" + 
				"    },\r\n" + 
				"    \"body\":{\r\n" + 
				"        \"deviceId\":\"4baf90f9-84c2-405f-bf25-378988d3c009\",\r\n" + 
				"        \"timestamp\":1586866102935,\r\n" + 
				"        \"token\":\"zq666666\",\r\n" + 
				"        \"osType\":\"ios\",\r\n" + 
				"		\"accCode\":\"T870886080\",\r\n" + 
				"		\"pwd\":\"123456\"\r\n" + 
				"    }\r\n" + 
				"  }";
		
		 s = "{阿斯顿发生asdf:1245}";
		System.out.println(StrUtil.subAfter(s,"asdf",true));
		System.out.println(StrUtil.subPre(s,s.indexOf("kk")));
		System.out.println(StrUtil.subBetween(s, ":", "}"));
		System.out.println(StrUtil.unWrap(s, ":", "}"));
	}
	


}
