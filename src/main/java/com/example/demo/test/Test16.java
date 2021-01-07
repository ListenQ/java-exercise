package com.example.demo.test;

import java.math.BigDecimal;

public class Test16 {
	
	public static void main(String[] args) {
		BigDecimal a = new BigDecimal("10");
		BigDecimal b = new BigDecimal("20");
		BigDecimal c = new BigDecimal("200");
		System.out.println(a.subtract(b).multiply(c));
	}

}
