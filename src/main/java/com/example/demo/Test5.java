package com.example.demo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test5 {
	
	private static String StockType ="CN";
	
	public static void main(String[] args) {
//		Double d= null;
//		BigDecimal b =new BigDecimal(d+"");
//		System.out.println(b.setScale(2, BigDecimal.ROUND_UP));
//		int month = 0,day = 0;
//		String stockType = "CN";
//		Map<String, Object> value = new HashMap<String, Object>();
//		value.put("name", "124578");
//		value.put("time2", "124578");
//		value.put("date", "124578");
//		boolean result = month>12 | month <1 & day >31 | day <1;
//		boolean timeFlag = value != null && !value.isEmpty() && value.get("time")!=null&& (value.get("time")+"").length() >0;
//        timeFlag &= (StockType.equals(stockType) && value != null &&null!=value.get("date")&& (value.get("date")+"").length()>0);
//        if (!timeFlag) {
//            System.out.println("不符合要删除*");
//        }
		
		Map<String, Object> value = new HashMap<String, Object>();
		value.put("name", "张三");
		value.put("time2", "123");
		value.put("date", "124578");
		
		Map<String, Object> value2 = new HashMap<String, Object>();
		value2.put("name", "张三");
		value2.put("time2", "456");
		value2.put("date", "124578");
		
		Map<String, Object> value3 = new HashMap<String, Object>();
		value3.put("name", "李四");
		value3.put("time2", "124578");
		value3.put("date", "124578");
		
		Map<String, Object> value4 = new HashMap<String, Object>();
		value4.put("name", "张三");
		value4.put("time2", "789");
		value4.put("date", "124578");
		
		List<Map<String, Object>> list = new ArrayList<>();
		list.add(value);
		list.add(value2);
		list.add(value3);
		list.add(value4);
		
		Map<String, List<Map<String,Object>>> mapp = new HashMap<>();
		List<Map<String,Object>> l = null;
		for (Map<String, Object> map : list) {
			
			if(!mapp.containsKey(map.get("name"))) {
				l  = new ArrayList<>();
			}else {
				l = mapp.get(map.get("name"));//这行不加会造成错乱
			}
			l.add(map);
			mapp.put(map.get("name")+"", l);
		}
		System.out.println(mapp);
    	
	}

}
