package com.example.demo.test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.example.demo.test.Test16.Person;
import com.example.demo.util.JacksonUtil;

public class Test18 {
	
	public static void main(String[] args) {
		Person person = new Test16.Person();
		person.setAddress("撒旦发");
		person.setName("哈哈哈");
		Object c = person;
		
//		System.out.println(JacksonUtil.toJSon(c));
		
		person.setAmount(new BigDecimal("236.5454"));
//		System.out.println(person.getAmount().negate());
		
		List<String> a = new ArrayList<>();
		a.add("132");
		List<String> b = new ArrayList<>();
		b.add("465");
		
		a = null;
		Optional.ofNullable(a).orElse(new ArrayList<>()).addAll(b);
		System.out.println(a);
	}
	
	
	

}
