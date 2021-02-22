package com.example.demo.test;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

public class Test17 {
	
	private static Map<String, BiConsumer<String,String>> map = new HashMap<>();
	static {
		map.put("1", (c,v) -> test1(c, v));
	}
	
	public static void main(String[] args) {
		BiConsumer<String, String> biConsumer = map.get("2");
		if (null != biConsumer) {
			
		}
	}
	
	
	private static void test1(String c,String v) {
		System.out.println(c+"***"+v);
	}
	
	private static void test2() {
		System.out.println("另外的事情了");
	}

}
