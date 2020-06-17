package com.example.demo.test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.example.demo.cp.Lucky;
import com.example.demo.enums.PlateFormEnum;

public class Test10 {
	
	private static List<HashMap<String, String>> list = new ArrayList<>();
	
	public static void main(String[] args) {
//		Lucky lucky = new Lucky("1");
//		PlateFormEnum.getMethodByType(lucky.getType()).settCase(lucky);
		
		
		System.out.println("HKD".contains("hk".toUpperCase()));
		
		HashMap<String, String> map = new HashMap<>();
		map.put("batch_id", "asdfasd");
		map.put("sqrialno", "1");
		list.add(map);
		
		List<HashMap<String, String>> mList = list.stream().map(l ->{return new HashMap<String,String>() {{put("kkk", "12346");}}; }).collect(Collectors.toList());
		System.out.println(mList);
		
		// 使用get() 会有 java.util.NoSuchElementException: No value present
		// 替换orElse 就会返回null
		Map<String, String> string = list.stream().filter(l-> l.get("batch_id").equals("123")).findFirst().orElse(null);
		System.out.println(string);
		
	}

}
