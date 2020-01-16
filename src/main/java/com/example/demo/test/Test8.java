package com.example.demo.test;


import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.apache.commons.lang3.time.DateUtils;

public class Test8 {
	
	public static void main(String[] args) throws Exception {
		List<String> list= new ArrayList<>();
		list.add(null);
		list.add("asdf");
		list.add("sadfa");
//		list.stream().map(ll ->{return "";}).collect(Collectors.toList());
		List<String> l = new ArrayList<String>();
		
		List<String> collect = IntStream.range(0, list.size()).mapToObj(ll ->{
			return list.get(ll);
		}).collect(Collectors.toList());

		System.out.println(DateUtils.parseDate("2020", "YYYY"));
	}

}
