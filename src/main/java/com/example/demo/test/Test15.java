package com.example.demo.test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.example.demo.exception.ApiException;

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
//		System.out.println(StrUtil.subAfter(s,"asdf",true));
//		System.out.println(StrUtil.subPre(s,s.indexOf("kk")));
//		System.out.println(StrUtil.subBetween(s, ":", "}"));
//		System.out.println(StrUtil.unWrap(s, ":", "}"));
		 
		 
//		 String ss = "reponse";
//		 System.out.println(ss.contains("reponse"));
//		 System.out.println(ss.startsWith("re"));
//		 System.out.println(StrUtil.containsAny(ss, "re"));
//		 System.out.println(StrUtil.join(StrUtil.COLON, "sadf",null));
		 
		 
		 List<BigDecimal> list = new ArrayList<>();
		 
		 BigDecimal total = new BigDecimal("500");
		 
		 list.add(BigDecimal.ONE);
		 list.add(new BigDecimal("2"));
		 list.add(new BigDecimal("3"));
		 total = list.stream().map(ca -> ca.multiply(BigDecimal.TEN)).reduce(total, BigDecimal::add);
//         System.out.println(total);
         
         list.addAll(Arrays.asList(BigDecimal.ONE,BigDecimal.TEN));
         //System.out.println(list);
         System.out.println("-------------------");
         
         
         List<BigDecimal> list2 = new ArrayList<>(list);
         list2.remove(1);
         
         Map<BigDecimal, BigDecimal> collect = list2.stream().peek(l ->{
        	 if (l.compareTo(BigDecimal.ONE) < 0) {
				throw new ApiException("异常");
        	 }
         }).filter(l -> {
        	 if (!list.contains(l)) {
        		 System.err.println("true"+l);
        		 return true;
        	 }
        	 System.err.println("false"+l);
        	 return false;
         }).collect(Collectors.toMap(BigDecimal::abs, d -> d,(x,y) -> y));
         
         System.out.println(collect);
	}
	


}
