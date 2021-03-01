package com.example.demo.test;

import com.example.demo.test.Test16.Person;
import com.example.demo.util.JacksonUtil;

public class Test18 {
	
	public static void main(String[] args) {
		Person person = new Test16.Person();
		person.setAddress("撒旦发");
		person.setName("哈哈哈");
		Object c = person;
		
		System.out.println(JacksonUtil.toJSon(c));
		
		
	}
	
	
	

}
