package com.example.demo.test;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Test9 {
	
	public static void main(String[] args) {
		BigDecimal a = new BigDecimal("50");
		BigDecimal b = new BigDecimal("0");
//		System.out.println(b.compareTo(BigDecimal.ZERO)==0?null:a.divide(b,5,BigDecimal.ROUND_HALF_UP));
		String str = "123";
		test(str);
		str = null;
		System.out.println(str);
	}
	
	private static void test(String str) {
		str +="465";
	}

}
