package com.example.demo;

import org.apache.commons.lang3.StringUtils;

public class Test7 {
	public static void main(String[] args) {
		String code = "10850";
		System.out.println(code.compareTo("00001") >=0 && code.compareTo("03999")<=0);
		System.out.println(code.startsWith("08", 0));
	}

}
