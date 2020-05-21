package com.example.demo.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.demo.cp.Lucky;
import com.example.demo.enums.PlateFormEnum;

public class Test10 {
	
	public static void main(String[] args) {
//		Lucky lucky = new Lucky("1");
//		PlateFormEnum.getMethodByType(lucky.getType()).settCase(lucky);
		
		
		System.out.println("HKD".contains("hk".toUpperCase()));
		List<Map<String, String>> list = new ArrayList<>();
		Map<String, String> map = new HashMap<>();
		map.put("batch_id", "asdfasd");
		map.put("sqrialno", "1");
		list.add(map);
		
		// 使用get() 会有 java.util.NoSuchElementException: No value present
		// 替换orElse 就会返回null
		Map<String, String> string = list.stream().filter(l-> l.get("batch_id").equals("123")).findFirst().orElse(null);
		System.out.println(string);
	}

}
