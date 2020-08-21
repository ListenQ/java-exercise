package com.example.demo.test;

import cn.hutool.core.util.StrUtil;

public class Test12 {
	
	public static void main(String[] args) {
		String s = "zsaf%s:nume= %s";
		test(s);
//		test2(s);
		
		
	}
	
	private static void test(String s) {
		long start = System.currentTimeMillis();
		for (int i = 0; i < 1000000; i++) {
			s = String.format(s, "张三",123);
		}
		System.out.println((System.currentTimeMillis()-start));
		System.out.println(s);
	}
	
	private static void test2(String s) {
		long start = System.currentTimeMillis();
		for (int i = 0; i < 1000000; i++) {
			s = StrUtil.format(s, "张三",132);
		}
		System.out.println((System.currentTimeMillis()-start));
		System.out.println(s);
	}
	
	
	
}
